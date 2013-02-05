package de.tw.cookbook;

import de.tw.cookbook.entity.Cookbook;
import de.tw.cookbook.entity.Recipe;
import de.tw.cookbook.persistence.RecipeDataSource;
import de.tw.kochbuch.R;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

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
		/*
		  List<Recipe> values = recipeDataSource.getAllRecipes(selectedCookbook.getId());
		  ArrayAdapter<Recipe> adapter = new ArrayAdapter<Recipe>(this,
		  android.R.layout.simple_list_item_1, values);
		  setListAdapter(adapter); 
		  registerForContextMenu(getListView());
		 */
	}

	@Override
	protected void onPause() {
		super.onPause();
		recipeDataSource.close();
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		Recipe recipe = (Recipe)l.getItemAtPosition(position);
		
		Intent intent = new Intent(this, RecipeDetailActivity.class);
		intent.putExtra("recipeId", recipe.getId());
		intent.putExtra("recipeName", recipe.getName());
		startActivity(intent);
	}
	
	private void setHeadline() {
		TextView healdine = (TextView) findViewById(R.id.tv_headline);
		healdine.setText(selectedCookbook.getName() + " recipes");
	}

	public void onClick(View view) {

	}
}
