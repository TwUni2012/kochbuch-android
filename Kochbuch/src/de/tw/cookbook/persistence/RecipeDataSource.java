package de.tw.cookbook.persistence;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import de.tw.cookbook.entity.Cookbook;
import de.tw.cookbook.entity.Recipe;
import de.tw.cookbook.persistence.tables.RecipeTable;

public class RecipeDataSource {

	private SQLiteDatabase database;
	private MySQLiteHelper dbHelper;
	private String[] allColumns = { RecipeTable.COLUMN_RECIPE_ID,
			RecipeTable.COLUMN_RECIPE_NAME};

	public RecipeDataSource(Context context) {
		dbHelper = new MySQLiteHelper(context);
	}

	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}

	public void close() {
		dbHelper.close();
	}

	public Recipe createRecipe(String recipeName, Cookbook cookbook) {
		Log.i(CookbookDataSource.class.getName(), "recipeName: " + recipeName);
		long cookbookId = cookbook.getId();
		ContentValues values = new ContentValues();
		values.put(RecipeTable.COLUMN_RECIPE_NAME, recipeName);
		values.put(RecipeTable.COLUMN_RECIPE_COOKBOOK_ID, cookbookId);
		long insertId = database.insert(RecipeTable.TABLE_RECIPE, null, values);
		Cursor cursor = database.query(RecipeTable.TABLE_RECIPE, allColumns,
				RecipeTable.COLUMN_RECIPE_ID + " = " + insertId, 
				null, null, null, null);
		cursor.moveToFirst();
		Recipe newRecipe = cursorToRecipe(cursor);
		cursor.close();
		return newRecipe;
	}

	public void deleteRecipe(Recipe recipe) {
		long id = recipe.getId();
		Log.i(RecipeDataSource.class.getName(), "Recipe deleted with id: " + id);
		database.delete(RecipeTable.TABLE_RECIPE, RecipeTable.COLUMN_RECIPE_ID
				+ " = " + id, null);
	}

	public List<Recipe> getAllRecipes(Cookbook cookbook) {
		Log.i(CookbookDataSource.class.getName(), "getAllRecipes() start");
		List<Recipe> recipes = new ArrayList<Recipe>();

		Cursor cursor = database.query(
				RecipeTable.TABLE_RECIPE,
				allColumns,
				RecipeTable.COLUMN_RECIPE_COOKBOOK_ID + " = "
						+ cookbook.getId(), null, null, null,
				RecipeTable.COLUMN_RECIPE_NAME);
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Recipe recipe = cursorToRecipe(cursor);
			recipes.add(recipe);
			cursor.moveToNext();
		}
		Log.i(CookbookDataSource.class.getName(), "getAllRecipes() end");
		cursor.close();

		return recipes;
	}

	private Recipe cursorToRecipe(Cursor cursor) {
		Recipe recipe = new Recipe();
		Log.i(CookbookDataSource.class.getName(), "cursor.getLong(0): " + cursor.getLong(0));
		recipe.setId(cursor.getLong(0));
		Log.i(CookbookDataSource.class.getName(), "cursor.getString(1): " + cursor.getString(1));
		recipe.setName(cursor.getString(1));
		return recipe;
	}
	
	public void updateRecipe(Recipe recipe) {
//		database.delete(RecipeTable.TABLE_RECIPE, RecipeTable.COLUMN_RECIPE_ID
//				+ " = " + id, null);
		/*
		database.update(table, 
				values, 
				whereClause, 
				whereArgs);
		 */
	}
}
