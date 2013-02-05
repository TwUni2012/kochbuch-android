package de.tw.cookbook;

import java.util.List;

import de.tw.cookbook.entity.Cookbook;
import de.tw.cookbook.entity.Recipe;
import de.tw.cookbook.persistence.RecipeDataSource;
import de.tw.kochbuch.R;
import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

public class RecipeDetailActivity extends Activity {

	private RecipeDataSource recipeDataSource;
	private Recipe recipe;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_details);
		
		Bundle bundle = getIntent().getExtras();
		recipe = (Recipe) bundle.get("recipe");
		
		setHeadline();
	}
	
	private void setHeadline() {
		TextView healdine = (TextView) findViewById(R.id.tv_details_headline);
		healdine.setText(recipe.getName());
	}

	@Override
	protected void onResume() {
		super.onResume();

		recipeDataSource = new RecipeDataSource(this);
		recipeDataSource.open();

//		List<Recipe> values = recipeDataSource.getAllRecipes(selectedCookbook);
//		ArrayAdapter<Recipe> adapter = new ArrayAdapter<Recipe>(this,
//				android.R.layout.simple_list_item_1, values);
//		setListAdapter(adapter);
//		registerForContextMenu(getListView());

	}
	
	@Override
	protected void onPause() {
		super.onPause();
		recipeDataSource.close();
	}
	
	
	public void onClick(View view) {
//		@SuppressWarnings("unchecked")
//		ArrayAdapter<Recipe> adapter = (ArrayAdapter<Recipe>) getListAdapter();
//		Recipe newRecipe = null;
//
//		switch (view.getId()) {
//		case R.id.bt_save:
//			EditText recipeNameEditText = (EditText) findViewById(R.id.edt_name);
//			String recipeName = recipeNameEditText.getText().toString();
//			if (!"".equals(recipeName)) {
//				newRecipe = recipeDataSource.createRecipe(recipeName,
//						selectedCookbook);
//				Log.i(RecipeActivity.class.getName(), "newRecipeName: "
//						+ newRecipe.getName());
//				Log.i(RecipeActivity.class.getName(), "newRecipeId: "
//						+ newRecipe.getId());
//				adapter.add(newRecipe);
//			}
//			recipeNameEditText.setText("");
//			break;
//		default:
//			break;
//		}
//		adapter.notifyDataSetChanged();
	}
}
