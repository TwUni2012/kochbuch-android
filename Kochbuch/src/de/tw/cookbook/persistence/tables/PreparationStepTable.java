package de.tw.cookbook.persistence.tables;

public class PreparationStepTable {
	public static final String TABLE_PreparationStep = "cookbook";
	public static final String COLUMN_PreparationStep_ID = "_id";
	public static final String COLUMN_PreparationStep_NAME = "name";
	public static final String COLUMN_PreparationStep_RECIPE_ID = "recipe_id";
	
	public static final String RECIPE_CREATE = "CREATE TABLE " + TABLE_PreparationStep + "(" +
			COLUMN_PreparationStep_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + 
			COLUMN_PreparationStep_NAME + " TEXT NOT NULL," +
			COLUMN_PreparationStep_RECIPE_ID + " INTEGER NOT NULL," +
			"FOREIGN KEY(" + COLUMN_PreparationStep_RECIPE_ID + ") REFERENCES "+ RecipeTable.TABLE_RECIPE + "(" +RecipeTable.COLUMN_RECIPE_ID + ")" +
			");";
	
	public static final String PreparationStep_DROP = "DROP TABLE IF EXISTS "+ TABLE_PreparationStep;
}
