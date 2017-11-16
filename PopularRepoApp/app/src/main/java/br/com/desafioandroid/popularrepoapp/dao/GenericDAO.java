package br.com.desafioandroid.popularrepoapp.dao;

import java.util.List;

import android.database.sqlite.SQLiteDatabase;

public interface GenericDAO<T> {
	
	public void insertObj(List<Object> objects) throws Exception;

	public void insert(List<T> objects) throws Exception;

	public void insert(T[] objects) throws Exception;

	public void insert(T object) throws Exception;

	public void persist(List<T> objects, SQLiteDatabase db) throws Exception;
	
	public void persistOtimizado(List<T> objects, SQLiteDatabase db)
			throws Exception;
	
	public void persistOtimizado(T objeto, SQLiteDatabase db) throws Exception;
	
	public void persist(T objeto, SQLiteDatabase db) throws Exception;
	
	public List<T> findAll() throws Exception;

	public List<T> findQTD(String qydRow) throws Exception;

	public T findFirst() throws Exception;

	public List<T> findWhere(String where) throws Exception;
	
	public List<T> findWhere(String where, String orderBy) throws Exception;

	public List<T> findWhere(String where, String orderBy, String qydRow)
			throws Exception;

	public String getTypeClass();

	public void deleteWhere(String where) throws Exception;

	public void deleteWhere(String where, SQLiteDatabase db) throws Exception;

	public void updateWhere(String values, String where) throws Exception;

	public void updateWhere(String values, String where, SQLiteDatabase db)
			throws Exception;

	public void deleteAll() throws Exception;

	public void deleteAll(SQLiteDatabase db) throws Exception;

	public void updateWhere(T objeto, String where) throws Exception;

	public void updateWhere(T objeto, String where, SQLiteDatabase db)
			throws Exception;

	public void update(T objeto) throws Exception;

	public void update(T objeto, SQLiteDatabase db) throws Exception;
}
