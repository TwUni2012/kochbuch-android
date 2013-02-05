package de.tw.kochbuch.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteHelper extends SQLiteOpenHelper {


	public static final String TABLE_COOKBOOK = "cookbook";
	public static final String COLUMN_COOKBOOK_ID = "_id";
	public static final String COLUMN_COOKBOOK_NAME = "name";
	
	private static final String DB_NAME = "cookbook.db";
	private static final int DB_VERSION = 1;
	
	// sql-statements
	private static final String COOKBOOK_CREATE = "CREATE TABLE " + TABLE_COOKBOOK + "("
			+ COLUMN_COOKBOOK_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_COOKBOOK_NAME + " TEXT NOT NULL"
			+ ")";
	private static final String COOKBOOK_DROP = "DROP TABLE IF EXISTS "+ TABLE_COOKBOOK;
	public static final String COOKBOOK_SELECT_RAW =
			"Select _id, name FROM " + TABLE_COOKBOOK;
	
	public MySQLiteHelper(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(COOKBOOK_CREATE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(MySQLiteHelper.class.getName(), "Updrading database from version " + oldVersion + "to "
				+ newVersion + ", which will destroy all old data");
		db.execSQL(COOKBOOK_DROP);
		onCreate(db);
	}
}
