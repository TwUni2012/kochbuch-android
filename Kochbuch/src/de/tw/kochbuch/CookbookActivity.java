package de.tw.kochbuch;

import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import de.tw.kochbuch.entity.Cookbook;
import de.tw.kochbuch.persistence.CookbookDataSource;

public class CookbookActivity extends ListActivity {

	private CookbookDataSource cookbookDataSource;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cookbook);

		Log.i(CookbookActivity.class.getName(), "Datenbank geöffnet");
		cookbookDataSource = new CookbookDataSource(this);
		cookbookDataSource.open();

		List<Cookbook> values = cookbookDataSource.getAllCookbooks();
		ArrayAdapter<Cookbook> adapter = new ArrayAdapter<Cookbook>(this,
				android.R.layout.simple_list_item_1, values);
		setListAdapter(adapter);
		registerForContextMenu(getListView());
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		getMenuInflater().inflate(R.menu.context_menu_delete, menu);
		menu.setHeaderTitle("Select an Option");
		menu.setHeaderIcon(android.R.drawable.ic_delete);
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
		switch (item.getItemId()) {
		case R.id.menu_delete:
			int position = info.position;
			Log.i(CookbookActivity.class.getName(), "position: " + item.getItemId());
			ListView l = getListView();
			
			Cookbook cookbook = (Cookbook)l.getItemAtPosition(position);
			cookbookDataSource.deleteCookbook(cookbook);
			ArrayAdapter<Cookbook> adapter = (ArrayAdapter<Cookbook>) getListAdapter();
			adapter.remove(cookbook);
			// adapter.notifyDataSetChanged();
			break;
		case R.id.menu_cancel:
			break;
		default:
			break;
		}
		return super.onContextItemSelected(item);
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		Log.i(CookbookActivity.class.getName(), "id: " + id);
		Cookbook cookbook = (Cookbook)l.getItemAtPosition(position);
		Log.i(CookbookActivity.class.getName(), "c.getId(): " + cookbook.getId());
		Log.i(CookbookActivity.class.getName(), "c.getName: " + cookbook.getName());
		
		Intent intent = new Intent(this, RecipeActivity.class);
		intent.putExtra("Cookbook", cookbook);
		startActivity(intent);
		
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
			break;
		}
		adapter.notifyDataSetChanged();
	}
}
