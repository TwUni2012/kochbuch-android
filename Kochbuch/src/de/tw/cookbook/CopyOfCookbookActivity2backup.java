package de.tw.cookbook;

import java.util.List;

import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Toast;
import de.tw.cookbook.entity.Cookbook;
import de.tw.cookbook.persistence.CookbookDataSource;
import de.tw.kochbuch.R;

public class CopyOfCookbookActivity2backup extends ListActivity {

	private CookbookDataSource cookbookDataSource;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cookbook);

//		cookbookDataSource = new CookbookDataSource(this);
//		cookbookDataSource.open();
//
//		List<Cookbook> values = cookbookDataSource.getAllCookbooks();
//
//		ArrayAdapter<Cookbook> adapter = new ArrayAdapter<Cookbook>(this,
//				android.R.layout.simple_list_item_1, values);
//		setListAdapter(adapter);
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
		
		
		
//		cookbookDataSource.close();
	}

	@Override
	protected void onPause() {
		super.onPause();
//		cookbookDataSource.close();
	}

	public void onClick(View view) {
//		@SuppressWarnings("unchecked")
//		ArrayAdapter<Cookbook> adapter = (ArrayAdapter<Cookbook>) getListAdapter();
//		Cookbook newCookbook = null;
//
//		switch (view.getId()) {
//		case R.id.bt_save_kochbuch:
//			EditText cookbookNameEditText = (EditText) findViewById(R.id.edt_kochbuchname);
//			String cookbookName = cookbookNameEditText.getText().toString();
//			Log.i(CookbookActivity.class.getName(), cookbookName);
//			if (!"".equals(cookbookName)) {
//				
//				newCookbook = cookbookDataSource.createCookbook(cookbookName);
//				adapter.add(newCookbook);
//			}
			// String cookbookName =
			// getResources().getString(R.id.edt_kochbuchname);
			// if(!"".equals(cookbookName)) {
			// adapter.add(cookbook);
			// }
//			break;
//
//		default:
			/*
			 * if(getListAdapter().getCount() > 0 { cookbook = (Cookbook)
			 * getListAdapter().getItem(0);
			 * cookbookDataSource.deleteCookbook(cookbook);
			 * adapter.remove(cookbook);
			 */
//			break;
//		}
//		adapter.notifyDataSetChanged();
	}

	// public

}
