package de.tw.cookbook.persistence;

import de.tw.cookbook.entity.Recipe;
import de.tw.cookbook.persistence.tables.CookbookTable;
import de.tw.cookbook.persistence.tables.RecipeTable;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteHelper extends SQLiteOpenHelper {
	private static final String DB_NAME = "cookbook.db";
	private static final int DB_VERSION = 1;
	
	public MySQLiteHelper(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CookbookTable.COOKBOOK_CREATE);
		db.execSQL(RecipeTable.RECIPE_CREATE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(MySQLiteHelper.class.getName(), "Updrading database from version " + oldVersion + "to "
				+ newVersion + ", which will destroy all old data");
		db.execSQL(CookbookTable.COOKBOOK_DROP);
		db.execSQL(RecipeTable.RECIPE_DROP);
		onCreate(db);
	}
}
