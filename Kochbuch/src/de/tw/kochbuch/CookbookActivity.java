package de.tw.kochbuch;

import java.util.List;

import android.app.ListActivity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import de.tw.kochbuch.entity.Cookbook;
import de.tw.kochbuch.persistence.CookbookDataSource;
import de.tw.kochbuch.persistence.MySQLiteHelper;

public class CookbookActivity extends ListActivity {

	private CookbookDataSource cookbookDataSource;
	private MySQLiteHelper mHelper;
	private SQLiteDatabase mDatenbank;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cookbook);

//		mDatenbank = mHelper.getReadableDatabase();
		Log.i(CookbookActivity.class.getName(), "Datenbank geöffnet");
		cookbookDataSource = new CookbookDataSource(this);
		cookbookDataSource.open();
//
		List<Cookbook> values = cookbookDataSource.getAllCookbooks();
//
		ArrayAdapter<Cookbook> adapter = new ArrayAdapter<Cookbook>(this,
				android.R.layout.simple_list_item_1, values);
		setListAdapter(adapter);
	}

	// @Override
	// public boolean onCreateOptionsMenu(Menu menu) {
	// // Inflate the menu; this adds items to the action bar if it is present.
	// getMenuInflater().inflate(R.menu.activity_cooking_book, menu);
	// return true;
	// }
	

	@Override
	protected void onResume() {
		super.onResume();
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		Log.i(CookbookActivity.class.getName(), "id: " + id);
		Cookbook c = (Cookbook)l.getItemAtPosition(position);
		Log.i(CookbookActivity.class.getName(), "c.getId(): " + c.getId());
		Log.i(CookbookActivity.class.getName(), "c.getName: " + c.getName());
	}

	@Override
	protected void onPause() {
		super.onPause();
		cookbookDataSource.close();
	}

	public void onClick(View view) {
		@SuppressWarnings("unchecked")
		ArrayAdapter<Cookbook> adapter = (ArrayAdapter<Cookbook>) getListAdapter();
		Cookbook newCookbook = null;

		switch (view.getId()) {
		case R.id.bt_save_kochbuch:
			EditText cookbookNameEditText = (EditText) findViewById(R.id.edt_kochbuchname);
			String cookbookName = cookbookNameEditText.getText().toString();
			Log.i(CookbookActivity.class.getName(), cookbookName);
			if (!"".equals(cookbookName)) {
				
				newCookbook = cookbookDataSource.createCookbook(cookbookName);
				adapter.add(newCookbook);
			}
			cookbookNameEditText.setText("");
			
			break;
		default:
			/*
			 * if(getListAdapter().getCount() > 0 { cookbook = (Cookbook)
			 * getListAdapter().getItem(0);
			 * cookbookDataSource.deleteCookbook(cookbook);
			 * adapter.remove(cookbook);
			 */
			break;
		}
		adapter.notifyDataSetChanged();
	}
}
