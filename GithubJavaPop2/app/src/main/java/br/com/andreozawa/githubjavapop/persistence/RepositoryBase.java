package br.com.andreozawa.githubjavapop.persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;

import br.com.andreozawa.githubjavapop.persistence.contracts.PublicRepoContract;
import br.com.andreozawa.githubjavapop.persistence.db.DBHelper;

/**
 * Created by andre.ozawa on 08/11/2017.
 */

public abstract class RepositoryBase<T> {

    protected DBHelper dbHelper;
    protected String tableName;
    protected Context context;

    protected abstract ContentValues getValues(T object);

    protected abstract String[] getProjection();

    protected abstract T getRowMapper(Cursor cursor);

    protected abstract int getObjectId(T object);

    public RepositoryBase(Context context, String tableName) {
        this.dbHelper = new DBHelper(context);
        this.tableName = tableName;
        this.context = context;
    }

    public long save(T object) {
        SQLiteDatabase db = this.dbHelper.getWritableDatabase();

        long id = db.insert(this.tableName, null, this.getValues(object));

        db.close();

        return id;
    }

    public long saveAll(final List<T> objects) {
        SQLiteDatabase db = this.dbHelper.getWritableDatabase();

        long ret = 0;

        try {
            for (T object : objects) {
                if (this.getById(this.getObjectId(object)) != null) {
                    String whereClause = PublicRepoContract.PublicRepoEntry.ID_REPO + " = ?";
                    String[] whereArgs = {String.valueOf(this.getObjectId(object))};

                    ret = db.update(this.tableName, this.getValues(object), whereClause, whereArgs);
                } else {
                    ret = db.insert(this.tableName, null, this.getValues(object));
                }
            }

        } catch (SQLiteException ex) {
            ret = -1;
            throw ex;
        } finally {
            db.close();
        }

        return ret;
    }

    public List<T> getAll() {
        List<T> list = new ArrayList<T>();

        SQLiteDatabase db = this.dbHelper.getReadableDatabase();

        String[] projection = this.getProjection();

        String selection = null;
        String[] selectionArgs = null;
        String sortOrder = "";

        Cursor c = db.query(
                this.tableName,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder
        );

        if (c.getCount() > 0) {
            c.moveToFirst();

            do {
                list.add(this.getRowMapper(c));
            } while (c.moveToNext());
        }

        c.close();
        db.close();

        return list;
    }

    public T getById(int id) {
        SQLiteDatabase db = this.dbHelper.getReadableDatabase();
        T object = null;

        String whereClause = PublicRepoContract.PublicRepoEntry.ID_REPO + " = ?";
        String[] whereArgs = {String.valueOf(id)};

        Cursor cursor = db.query(this.tableName, getProjection(), whereClause, whereArgs, null, null, null);

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();

            object = this.getRowMapper(cursor);
        }

        cursor.close();

        return object;
    }

    public long update(T object) {
        SQLiteDatabase db = this.dbHelper.getWritableDatabase();

        ContentValues contentValues = this.getValues(object);

        String whereClause = "_id = ?";
        String[] whereArgs = {String.valueOf(this.getObjectId(object))};

        long rowsAffected = db.update(this.tableName, contentValues, whereClause, whereArgs);

        db.close();

        return rowsAffected;
    }

    public int delete(T object) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String whereClause = PublicRepoContract.PublicRepoEntry.ID_REPO + " = ?";
        String[] whereArgs = {String.valueOf(this.getObjectId(object))};

        int rowsAffectes = db.delete(this.tableName, whereClause, whereArgs);

        db.close();

        return rowsAffectes;
    }
}