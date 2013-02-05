package de.tw.cookbook.persistence.tables;

public class RecipeTable {
	public static final String TABLE_RECIPE = "recipe";
	public static final String COLUMN_RECIPE_ID = "_id";
	public static final String COLUMN_RECIPE_NAME = "recipe_name";
	public static final String COLUMN_RECIPE_DESCRIPTION = "recipe_description";
	public static final String COLUMN_RECIPE_PRETARATIONSTEPS = "recipe_preparationSteps";
	public static final String COLUMN_RECIPE_COOKBOOK_ID = "cookbook_id";
	
	public static final String RECIPE_CREATE = "CREATE TABLE " + TABLE_RECIPE + "(" +
			COLUMN_RECIPE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + 
			COLUMN_RECIPE_NAME + " TEXT NOT NULL," +
			COLUMN_RECIPE_DESCRIPTION + " TEXT," +
			COLUMN_RECIPE_PRETARATIONSTEPS + " TEXT," +
			COLUMN_RECIPE_COOKBOOK_ID + " INTEGER NOT NULL," +
			"FOREIGN KEY(" + COLUMN_RECIPE_COOKBOOK_ID + ") REFERENCES "+ CookbookTable.TABLE_COOKBOOK + "(" + CookbookTable.COLUMN_COOKBOOK_ID + ")" +
			");";
			
	public static final String RECIPE_DROP = "DROP TABLE IF EXISTS "+ TABLE_RECIPE;
}
