package de.tw.cookbook.persistence.tables;

public class CookbookTable {
	public static final String TABLE_COOKBOOK = "cookbook";
	public static final String COLUMN_COOKBOOK_ID = "_id";
	public static final String COLUMN_COOKBOOK_NAME = "name";
	
	public static final String COOKBOOK_CREATE = "CREATE TABLE " + TABLE_COOKBOOK + "("
			+ COLUMN_COOKBOOK_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_COOKBOOK_NAME + " TEXT NOT NULL"
			+ ")";
	public static final String COOKBOOK_DROP = "DROP TABLE IF EXISTS "+ TABLE_COOKBOOK;
	
}
