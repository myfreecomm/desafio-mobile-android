package br.com.desafioandroid.popularrepoapp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.lang.reflect.Field;

import br.com.desafioandroid.popularrepoapp.constantes.BancoDados;


public class Database{

	private static SQLiteDatabase database = null;
	private SQLiteOpenHelper sqLiteOpenHelper;

	public Database() {
		// TODO Auto-generated constructor stub
	}

	public static SQLiteDatabase getInstance() {
		if (database == null) {
			File externalStorage = Environment.getExternalStorageDirectory();
			String dirSDCard = externalStorage.getAbsolutePath();

			File file = new File(dirSDCard + BancoDados.PATH);

			if (!file.isDirectory()) {
				file.mkdirs();
			}

			try {
				database = SQLiteDatabase.openOrCreateDatabase(dirSDCard
						+ BancoDados.PATH + BancoDados.FILE_PATH, null);
			} catch (Exception e) {
				Log.d("SQLiteDatabase", e.getMessage());
				e.printStackTrace();
			}
		}
		return database;
	}

	public static void close() {
		database.close();
	}

	public String getType(String type) {
		if (type.equals("int"))
			return "Integer";

		if (type.contains("java.lang.Integer"))
			return "Integer";

		if (type.contains("java.lang.Double"))
			return "Double";

		if (type.contains("java.lang.Long"))
			return "Long";

		if (type.contains("java.lang.String"))
			return "TEXT";

		if (type.toLowerCase().contains("boolean"))
			return "TEXT";

		if (type.toLowerCase().contains("boolean"))
			return "TEXT";

		return type;
	}

	public void createDatabases(String[] classesVO) throws Exception {
		SQLiteDatabase db = getInstance();
		String tabelaErro = "";
		String query = "";

		db.beginTransaction();
		try {

			for (int j = 0; j < classesVO.length; j++) {

				tabelaErro = classesVO[j];

				try {
					db.execSQL("DROP TABLE IF EXISTS " + classesVO[j] + ";");
				} catch (Exception e) {
					Log.e("ERRO", query);
				}

				query = "create table " + classesVO[j] + "(";

				Object objeto = Class.forName(
						BancoDados.PATH_PACOTE_DOMAIN + classesVO[j])
						.newInstance();
				Field[] fields = objeto.getClass().getDeclaredFields();
				int count = 0;
				for (int i = 0; i < fields.length; i++) {

					if (!fields[i].getType().getName().contains("List")) {

						if (count != 0)
							query += ", ";

						query += fields[i].getName() + " "
								+ getType(fields[i].getType().getName());

						count++;
					}

				}

				query += ");";

				db.execSQL(query);

			}

			db.setTransactionSuccessful();
		} catch (Exception e) {
			Log.e("ERRO", query);
			throw new Exception(
					        "Erro Banco"
							+ " "
							+ tabelaErro
							+ " "
							+ "Falha ao criar banco."
							+ ":\n\t" + e.getMessage(), e);
		} finally {
			db.endTransaction();
		}
	}
}
