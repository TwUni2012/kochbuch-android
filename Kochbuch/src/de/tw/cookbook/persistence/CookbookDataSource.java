package de.tw.cookbook.persistence;

import java.util.ArrayList;
import java.util.List;

import de.tw.cookbook.entity.Cookbook;
import de.tw.cookbook.persistence.tables.CookbookTable;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class CookbookDataSource {

	private SQLiteDatabase database;
	private MySQLiteHelper dbHelper;
	private String[] allColumns = {CookbookTable.COLUMN_COOKBOOK_ID,
			CookbookTable.COLUMN_COOKBOOK_NAME };
	

	public CookbookDataSource(Context context) {
		dbHelper = new MySQLiteHelper(context);
	}
	
	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}
	
	public void close() {
		dbHelper.close();
	}

	public Cookbook createCookbook(String cookbookName) {
		Log.i(CookbookDataSource.class.getName(), "cookbookName: " + cookbookName);
		ContentValues values = new ContentValues();
		values.put(CookbookTable.COLUMN_COOKBOOK_NAME, cookbookName);
		Log.i(CookbookDataSource.class.getName(), "works bis hier");
		long insertId = database.insert(CookbookTable.TABLE_COOKBOOK, null, values);
		Log.i(CookbookDataSource.class.getName(), "works bis hier2");
		Cursor cursor = database.query(CookbookTable.TABLE_COOKBOOK, 
				allColumns, CookbookTable.COLUMN_COOKBOOK_ID + " = " + insertId, 
				null, null, null, null);
		Log.i(CookbookDataSource.class.getName(), "works bis hier3");
		cursor.moveToFirst();
		Log.i(CookbookDataSource.class.getName(), "works bis hie4");
		Cookbook newCookbook = cursorToCookbook(cursor);
		cursor.close();
		return newCookbook;
	}

	public void deleteCookbook(Cookbook cookbook) {
		long id = cookbook.getId();
		Log.i(CookbookDataSource.class.getName(), "Cookbook deleted with id: " + id);
		database.delete(CookbookTable.TABLE_COOKBOOK, CookbookTable.COLUMN_COOKBOOK_ID
				+ " = " + id, null);
	}
	
	public List<Cookbook> getAllCookbooks() {
		Log.i(CookbookDataSource.class.getName(), "getAllCookbooks() start");
		List<Cookbook> cookbooks = new ArrayList<Cookbook>();
		
		Cursor cursor = database.query(CookbookTable.TABLE_COOKBOOK, 
				allColumns, null, null, null, null, null);
		// TODO letzer Parameter kann auch "name" sein, damit die cookbooks geordnet zurueck gegeben werden
		cursor.moveToFirst();
		while(!cursor.isAfterLast()) {
			Cookbook cookbook = cursorToCookbook(cursor);
			cookbooks.add(cookbook);
			cursor.moveToNext();
		}
		Log.i(CookbookDataSource.class.getName(), "getAllCookbooks() end");
		cursor.close();
		return cookbooks;
	}
	
	private Cookbook cursorToCookbook(Cursor cursor) {
		Cookbook cookbook = new Cookbook();
		cookbook.setId(cursor.getLong(0));
		cookbook.setName(cursor.getString(1));
		return cookbook;
	}
		
}
