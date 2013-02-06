package de.tw.cookbook;

import java.util.List;

import de.tw.cookbook.entity.Cookbook;
import de.tw.cookbook.entity.PreparationStep;
import de.tw.cookbook.entity.Recipe;
import de.tw.cookbook.persistence.PreparationStepDataSource;
import de.tw.cookbook.persistence.RecipeDataSource;
import de.tw.kochbuch.R;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.AdapterContextMenuInfo;

public class RecipeDetailActivity extends ListActivity {

	private RecipeDataSource recipeDataSource;
	private Recipe recipe;
	private PreparationStepDataSource preparationStepDataSource;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_details);

		Bundle bundle = getIntent().getExtras();
		recipe = (Recipe) bundle.get("recipe");

		setHeadline();
		setDescriptionText();
	}

	private void setHeadline() {
		TextView healdine = (TextView) findViewById(R.id.tv_details_headline);
		healdine.setText(recipe.getName());
	}

	// TODO refactorn see onClick-method
	private void setDescriptionText() {
		String description = recipe.getDescription();
		Log.i(RecipeDetailActivity.class.getName(), "description: " + description);
		if ((!"".equals(description)) || !(description == null)) {
			EditText descriptionEditText = (EditText) findViewById(R.id.edt_description);
			Button saveDescriptionButton = (Button) findViewById(R.id.bt_save_description);
			descriptionEditText.setVisibility(View.GONE);
			saveDescriptionButton.setVisibility(View.GONE);
			TextView descriptionTv = (TextView) findViewById(R.id.tv_description_content);
			descriptionTv.setText(description);
		}
	}

	@Override
	protected void onResume() {
		super.onResume();

		recipeDataSource = new RecipeDataSource(this);
		recipeDataSource.open();
		preparationStepDataSource = new PreparationStepDataSource(this);
		preparationStepDataSource.open();

		List<PreparationStep> values = preparationStepDataSource
				.getAllPreparationSteps(recipe);
		ArrayAdapter<PreparationStep> adapter = new ArrayAdapter<PreparationStep>(
				this, android.R.layout.simple_list_item_1, values);
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

			PreparationStep preparationStep = (PreparationStep) l
					.getItemAtPosition(position);
			preparationStepDataSource.deletePreparationStep(preparationStep);
			ArrayAdapter<PreparationStep> adapter = (ArrayAdapter<PreparationStep>) getListAdapter();
			adapter.remove(preparationStep);
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
		preparationStepDataSource.close();
	}

	public void onClick(View view) {
		@SuppressWarnings("unchecked")
		ArrayAdapter<PreparationStep> adapter = (ArrayAdapter<PreparationStep>) getListAdapter();
		PreparationStep preparationStep = null;

		switch (view.getId()) {
		case R.id.bt_save:
			EditText preparationStepNameEditText = (EditText) findViewById(R.id.edt_name);
			String preparationStepName = preparationStepNameEditText.getText()
					.toString();
			if (!"".equals(preparationStepName)) {
				preparationStep = preparationStepDataSource
						.createPreparationStep(preparationStepName, recipe);
				Log.i(RecipeActivity.class.getName(), "newRecipeName: "
						+ preparationStep.getName());
				Log.i(RecipeActivity.class.getName(), "newRecipeId: "
						+ preparationStep.getId());
				adapter.add(preparationStep);
			}
			preparationStepNameEditText.setText("");
			break;
		case R.id.bt_save_description:
			EditText descriptionEditText = (EditText) findViewById(R.id.edt_description);
			String description = descriptionEditText.getText().toString();
			if (!"".equals(description)) {
				recipe.setDescription(description);
				recipeDataSource.updateRecipe(recipe);
				TextView descriptionTv = (TextView) findViewById(R.id.tv_description_content);
				descriptionTv.setText(description);
				descriptionEditText.setVisibility(View.GONE);
				Button saveDescriptionButton = (Button) findViewById(R.id.bt_save_description);
				saveDescriptionButton.setVisibility(View.GONE);
			}
			break;
		default:
			break;
		}
		adapter.notifyDataSetChanged();
	}
}
