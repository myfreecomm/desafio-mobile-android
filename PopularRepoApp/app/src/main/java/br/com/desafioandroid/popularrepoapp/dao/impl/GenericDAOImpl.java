package br.com.desafioandroid.popularrepoapp.dao.impl;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import br.com.desafioandroid.popularrepoapp.constantes.BancoDados;
import br.com.desafioandroid.popularrepoapp.database.Base;
import br.com.desafioandroid.popularrepoapp.database.Database;


public class GenericDAOImpl<T> {

	protected String typeClass;
	protected Context context;

	
	
	public GenericDAOImpl(String typeClass, Context context) {
		this.typeClass = typeClass;
		this.context = context;
	}
	
	public GenericDAOImpl(String typeClass) {
		this.typeClass = typeClass;
	}
	
	@SuppressWarnings("unchecked")
	public void insertObj(List<Object> objects) throws Exception {
		SQLiteDatabase db = Database.getInstance();
		db.beginTransaction();
		try {			
			
			for (Object object : objects) {
				persist((T) object, db);
			}
			
			db.setTransactionSuccessful();
		} finally {
			db.endTransaction();
			
		}
	}

	public void insert(List<T> objects) throws Exception {
		SQLiteDatabase db = Database.getInstance();
		db.beginTransaction();
		try {
			for (T object : objects) {
				persist(object, db);
			}
			
			db.setTransactionSuccessful();
		} finally {
			db.endTransaction();
			
		}
	}

	public void insert(T[] objects) throws Exception {
		SQLiteDatabase db = Database.getInstance();
		db.beginTransaction();
		try {
			for (T object : objects) {
				persist(object, db);
			}
			
			db.setTransactionSuccessful();
		} finally {
			db.endTransaction();
		}
	}

	public void insert(T object) throws Exception {
		SQLiteDatabase db = Database.getInstance();
		db.beginTransaction();
		try {
			persist(object, db);
			
			db.setTransactionSuccessful();
		} finally {
			db.endTransaction();
		}
	}

	public void persist(List<T> objects, SQLiteDatabase db) throws Exception {
		for (T object : objects) {
			persist(object, db);
		}
	}
	
	public void persistOtimizado(List<T> objects, SQLiteDatabase db)
			throws Exception {
		for (T object : objects) {
			persistOtimizado(object, db);
		}
	}
	
	public void persistOtimizado(T objeto, SQLiteDatabase db) throws Exception {
		try {
			String query = "insert into " + getTypeClass();
			Base baseVO = (Base) objeto;

			query = query + " (" + baseVO.getFields() + ")" + " values ("
					+ baseVO.getValues().replaceAll("'null'", "null") + ");";
			

			SQLiteStatement liteStatement = db.compileStatement(query);

			liteStatement.execute();
		} catch (Exception e) {
			throw new Exception(
					"Banco de dados"
							+ " "
							+ getTypeClass()
							+ "\n "
							+ "Erro devido ao seguinte motivo"
							+ ":\n\t" + e.getMessage());
		}
	}
	
	public void persist(T objeto, SQLiteDatabase db) throws Exception {
		try {
			Field[] fields = objeto.getClass().getDeclaredFields();
			String query = "insert into " + getTypeClass();
			String columns = " (";
			String values = " values (";
			Object buffer;
			int count = 0;
			for (int i = 0; i < fields.length; i++) {

				buffer = fields[i].get(objeto);

				if (buffer != null) {
					if (!fields[i].getType().getName().contains("List") && !fields[i].getType().getName().contains(BancoDados.PATH_PACOTE_DOMAIN)) {

						if (count != 0) {
							columns += ", ";
							values += ", ";
						}

						columns += fields[i].getName();
						values += "?";
						

						count++;
					}
				}
			}

			query = query + columns + ")" + values + ");";
			
			int coluna = 1;
			SQLiteStatement liteStatement = db.compileStatement(query);
			for (int i = 0; i < fields.length; i++) {
				buffer = fields[i].get(objeto);
				if (buffer != null) {
					if (!fields[i].getType().getName().contains("List") && !fields[i].getType().getName().contains(BancoDados.PATH_PACOTE_DOMAIN)) {
						liteStatement.bindString(coluna, fields[i].get(objeto).toString());
						coluna++;
					}
				} 

			}

			liteStatement.execute();
		} catch (Exception e) {
			throw new Exception(
					"Banco de dados"
							+ " "
							+ getTypeClass()
							+ "\n "
							+ "Erro devido ao seguinte motivo"
							+ ":\n\t" + e.getMessage());
		}
	}

	
	public List<T> findAll() throws Exception {
		return findQTD(null);
	}

	//@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<T> findQTD(String qydRow) throws Exception {
		return findWhere(null, null, qydRow);
	}

	protected int getSize(String[] strings) {
		int qtd = 0;
		for (int i = 0; i < strings.length; i++) {
			if (strings[i] != null) {
				qtd++;
			}
		}

		return qtd;
	}

	public T findFirst() throws Exception {
		List<T> list = findQTD("1");
		if (list.size() > 0) {
			return (T) list.get(0);
		}

		return null;
	}

//	public String getTypeClass() {
//		return typeClass;
//	}

	public List<T> findWhere(String where) throws Exception {
		return findWhere(where, null, null);
	}
	
	public List<T> findWhere(String where, String orderBy) throws Exception {
		return findWhere(where, orderBy, null);
	}

	@SuppressWarnings("unchecked")
	public List<T> findWhere(String where, String orderBy, String qydRow)
			throws Exception {
		SQLiteDatabase db = Database.getInstance();
		Cursor cursor = null;
		try {

			String classe = getTypeClass();
			Object objeto = Class.forName(BancoDados.PATH_PACOTE_DOMAIN + classe)
					.newInstance();
			Field[] fields = objeto.getClass().getDeclaredFields();

			int qtd = 0;
			for (int i = 0; i < fields.length; i++) {
				if (!fields[i].getType().getName().contains("List") && !fields[i].getType().getName().contains("Ranking")) {
					qtd++;
				}
			}

			String[] columns = new String[qtd];
			for (int i = 0; i < fields.length; i++) {
				if (!fields[i].getType().getName().contains("List") && !fields[i].getType().getName().contains("Ranking")) {
					columns[(getSize(columns))] = fields[i].getName();
				}
			}

			cursor = db.query(classe, columns, where, null, null, null,
					orderBy, qydRow);
			@SuppressWarnings("rawtypes")
			List list = new ArrayList();

			if (cursor.moveToFirst()) {
				do {
					Object object = Class.forName(
							BancoDados.PATH_PACOTE_DOMAIN + classe).newInstance();

					for (int i = 0; i < qtd; i++) {

						Field field = object.getClass().getDeclaredField(
								columns[i]);
						field.setAccessible(true);

						setField(field, object, cursor, i);
					}

					list.add(object);
				} while (cursor.moveToNext());
			}

			return list;
		}  catch (Exception e) {
			throw new Exception(
					"Banco de dados"
							+ " "
							+ getTypeClass()
							+ "\n "
							+ "Erro devido ao seguinte motivo"
							+ ":\n\t" + e.getMessage());
		}
	}

	private void setField(Field field, Object object, Cursor cursor, int i)
			throws IllegalArgumentException, IllegalAccessException {
		if (field.getType().getName().contains("boolean")
				|| field.getType().getName().endsWith("Boolean")) {
			field.set(object, Boolean.parseBoolean(cursor.getString(i)));
		} else {
			if (field.getType().getName().equals("int")
					|| field.getType().getName().endsWith("Integer")) {
				field.set(object, cursor.getInt(i));
			} else {
				if (field.getType().getName().equals("long")
						|| field.getType().getName().endsWith("Long")) {
					field.set(object, cursor.getLong(i));
				} else {
					if (field.getType().getName().equals("double")
							|| field.getType().getName().endsWith("Double")) {
						field.set(object, cursor.getDouble(i));
					} else {
						field.set(object, cursor.getString(i));
					}
				}
			}

		}
	}

	public String getTypeClass() {
		return typeClass;
	}

	public void deleteWhere(String where) throws Exception {

		SQLiteDatabase db = Database.getInstance();
		try {
			deleteWhere(where, db);
		} finally {
			//db.close();
		}
	}

	public void deleteWhere(String where, SQLiteDatabase db) throws Exception {

		try {
			db.execSQL("DELETE FROM " + getTypeClass() + " where " + where + " ;");
		}  catch (Exception e) {
			throw new Exception(
					"Banco de dados"
							+ " "
							+ getTypeClass()
							+ "\n "
							+ "Erro devido ao seguinte motivo"
							+ ":\n\t" + e.getMessage());
		}
	}

	public void updateWhere(String values, String where) throws Exception {

		SQLiteDatabase db = Database.getInstance();
		try {
			updateWhere(values, where, db);
		} finally {
			//db.close();
		}
	}

	public void updateWhere(String values, String where, SQLiteDatabase db)
			throws Exception {

		try {
			db.execSQL("UPDATE " + getTypeClass() + " SET " + values + " WHERE "
					+ where + ";");
		}  catch (Exception e) {
			throw new Exception(
					"Banco de dados"
							+ " "
							+ getTypeClass()
							+ "\n "
							+ "Erro devido ao seguinte motivo"
							+ ":\n\t" + e.getMessage());
		}
	}

	public void deleteAll() throws Exception {

		SQLiteDatabase db = Database.getInstance();
		try {
			deleteAll(db);
		} finally {
			//db.close();
		}
	}

	public void deleteAll(SQLiteDatabase db) throws Exception {

		try {
			db.execSQL("DELETE FROM " + getTypeClass() + " ;");

		}  catch (Exception e) {
			throw new Exception(
					"Banco de dados"
							+ " "
							+ getTypeClass()
							+ "\n "
							+ "Erro devido ao seguinte motivo"
							+ ":\n\t" + e.getMessage());
		}
	}

	public void updateWhere(T objeto, String where) throws Exception {
		SQLiteDatabase db = Database.getInstance();
		try {
			updateWhere(objeto, where, db);
		} finally {
			//db.close();
		}
	}

	public void updateWhere(T objeto, String where, SQLiteDatabase db)
			throws Exception {
		try {
			Field[] fields = objeto.getClass().getDeclaredFields();
			String query = "update " + getTypeClass();
			String columns = " set ";
			Object buffer;
			int count = 0;
			for (int i = 0; i < fields.length; i++) {

				buffer = fields[i].get(objeto);

				if (!fields[i].getType().getName().contains("List")) {

					if (count != 0) {
						columns += ", ";
					}

					columns += fields[i].getName();

					columns += " = ";

					if (buffer != null) {
						if (buffer instanceof String
								|| buffer instanceof Boolean)
							columns += "'" + fields[i].get(objeto).toString()
									+ "'";
						else
							columns += fields[i].get(objeto).toString();

					} else {
						columns += "null";
					}

					count++;
				}
			}

			query = query + columns + " where " + where + ";";

			SQLiteStatement liteStatement = db.compileStatement(query);
			liteStatement.execute();
		}  catch (Exception e) {
			throw new Exception(
					"Banco de dados"
							+ " "
							+ getTypeClass()
							+ "\n "
							+ "Erro devido ao seguinte motivo"
							+ ":\n\t" + e.getMessage());
		}
	}

	public void update(T objeto) throws Exception {
		SQLiteDatabase db = Database.getInstance();
		try {
			update(objeto, db);
		} finally {
		//	db.close();
		}
	}

	public void update(T objeto, SQLiteDatabase db) throws Exception {
		try {
			Field[] fields = objeto.getClass().getDeclaredFields();
			String query = "update " + getTypeClass();
			String columns = " set ";
			Object buffer;
			int count = 0;
			for (int i = 0; i < fields.length; i++) {

				buffer = fields[i].get(objeto);

				if (buffer != null) {
					if (!fields[i].getType().getName().contains("List")) {

						if (count != 0) {
							columns += ", ";
						}

						columns += fields[i].getName();

						columns += " = ";

						if (buffer instanceof String
								|| buffer instanceof Boolean)
							columns += "'" + fields[i].get(objeto).toString()
									+ "'";
						else
							columns += fields[i].get(objeto).toString();

						count++;

					}
				}
			}

			query = query + columns + " ;";

			SQLiteStatement liteStatement = db.compileStatement(query);
			liteStatement.execute();
		}  catch (Exception e) {
			throw new Exception(
					"Banco de dados"
							+ " "
							+ getTypeClass()
							+ "\n "
							+ "Erro devido ao seguinte motivo"
							+ ":\n\t" + e.getMessage());
		}
	}
}
