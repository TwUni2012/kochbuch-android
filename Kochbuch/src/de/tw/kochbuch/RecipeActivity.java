package de.tw.kochbuch;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;

public class RecipeActivity extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_recipe);
		
		setHeadline();
	}

	private void setHeadline() {
		// TODO Auto-generated method stub
		
	}
	
	public void onClick(View view) {
		
	}
}
