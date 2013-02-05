package de.tw.cookbook.persistence;

import java.util.List;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import de.tw.cookbook.entity.Cookbook;
import de.tw.cookbook.entity.Recipe;
public class RecipeDataSource {
	
	private SQLiteDatabase database;
	private MySQLiteHelper dbHelper;
	
	public RecipeDataSource(Context context) {
		dbHelper = new MySQLiteHelper(context);
	}
	
	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}
	
	public void close() {
		dbHelper.close();
	}
	
	public List<Recipe> getAllRecipes(long cookbookId) {
		return null;
		
	}
	
}
