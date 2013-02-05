package de.tw.cookbook;

import de.tw.kochbuch.R;
import android.app.ListActivity;
import android.os.Bundle;

public class RecipeDetailActivity extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_details);
	}
}
