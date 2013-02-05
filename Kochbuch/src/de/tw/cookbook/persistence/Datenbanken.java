package de.tw.cookbook.persistence;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Toast;

public class Datenbanken extends Activity {

	private MySQLiteHelper mHelper;
	private SQLiteDatabase mDatenbank;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mHelper = new MySQLiteHelper(this);
	}

	@Override
	protected void onPause() {
		super.onPause();
		mDatenbank.close();
		Toast.makeText(this, "Datenbank geschlossen", Toast.LENGTH_SHORT).show();
	
	}

	@Override
	protected void onResume() {
		super.onResume();
		mDatenbank = mHelper.getReadableDatabase();
		Toast.makeText(this, "Datenbank geöffnet", Toast.LENGTH_SHORT).show();
	}

	
}
