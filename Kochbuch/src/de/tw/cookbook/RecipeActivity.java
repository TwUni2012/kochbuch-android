package de.tw.cookbook;

import java.util.List;

import de.tw.cookbook.entity.Cookbook;
import de.tw.cookbook.entity.Recipe;
import de.tw.cookbook.persistence.RecipeDataSource;
import de.tw.kochbuch.R;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.AdapterContextMenuInfo;

public class RecipeActivity extends ListActivity {

	private RecipeDataSource recipeDataSource;
	private Cookbook selectedCookbook;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cookbook);

		Bundle bundle = getIntent().getExtras();
		selectedCookbook = (Cookbook) bundle.get("cookbook");

		Log.i(RecipeActivity.class.getName(), "CookbookName: "
				+ selectedCookbook.getName());
		Log.i(RecipeActivity.class.getName(),
				"CookbookId: " + selectedCookbook.getId());

		setHeadline();
	}

	@Override
	protected void onResume() {
		super.onResume();

		recipeDataSource = new RecipeDataSource(this);
		recipeDataSource.open();

		List<Recipe> values = recipeDataSource.getAllRecipes(selectedCookbook);
		ArrayAdapter<Recipe> adapter = new ArrayAdapter<Recipe>(this,
				android.R.layout.simple_list_item_1, values);
		setListAdapter(adapter);
		registerForContextMenu(getListView());
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		getMenuInflater().inflate(R.menu.context_menu_delete, menu);
		menu.setHeaderTitle(getResources().getString(R.string.selectAnOption));
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		AdapterContextMenuInfo info = (AdapterContextMenuInfo) item
				.getMenuInfo();
		switch (item.getItemId()) {
		case R.id.menu_delete:
			int position = info.position;
			Log.i(RecipeActivity.class.getName(),
					"position: " + item.getItemId());
			ListView l = getListView();

			Recipe recipe = (Recipe) l.getItemAtPosition(position);
			recipeDataSource.deleteRecipe(recipe);
			ArrayAdapter<Recipe> adapter = (ArrayAdapter<Recipe>) getListAdapter();
			adapter.remove(recipe);
			adapter.notifyDataSetChanged();
			break;
		case R.id.menu_cancel:
			break;
		default:
			break;
		}
		return super.onContextItemSelected(item);
	}

	@Override
	protected void onPause() {
		super.onPause();
		recipeDataSource.close();
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		Recipe recipe = (Recipe) l.getItemAtPosition(position);

		Intent intent = new Intent(this, RecipeDetailActivity.class);
		intent.putExtra("recipe", recipe);
		startActivity(intent);
	}

	private void setHeadline() {
		TextView healdine = (TextView) findViewById(R.id.tv_headline);
		healdine.setText(selectedCookbook.getName() + " " + getResources().getString(R.string.recipes));
	}

	public void onClick(View view) {
		@SuppressWarnings("unchecked")
		ArrayAdapter<Recipe> adapter = (ArrayAdapter<Recipe>) getListAdapter();
		Recipe newRecipe = null;

		switch (view.getId()) {
		case R.id.bt_save:
			EditText recipeNameEditText = (EditText) findViewById(R.id.edt_name);
			String recipeName = recipeNameEditText.getText().toString();
			if (!"".equals(recipeName)) {
				newRecipe = recipeDataSource.createRecipe(recipeName,
						selectedCookbook);
				Log.i(RecipeActivity.class.getName(), "newRecipeName: "
						+ newRecipe.getName());
				Log.i(RecipeActivity.class.getName(), "newRecipeId: "
						+ newRecipe.getId());
				adapter.add(newRecipe);
			}
			recipeNameEditText.setText("");
			break;
		default:
			break;
		}
		adapter.notifyDataSetChanged();
	}
}
