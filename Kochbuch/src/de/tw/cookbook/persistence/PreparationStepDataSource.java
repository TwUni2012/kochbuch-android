package de.tw.cookbook.persistence;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import de.tw.cookbook.entity.PreparationStep;
import de.tw.cookbook.persistence.tables.PreparationStepTable;

public class PreparationStepDataSource {
	private SQLiteDatabase database;
	private MySQLiteHelper dbHelper;
	private String[] allColumns = {
			PreparationStepTable.COLUMN_PreparationStep_ID,
			PreparationStepTable.COLUMN_PreparationStep_NAME,
			PreparationStepTable.COLUMN_PreparationStep_RECIPE_ID };

	public PreparationStepDataSource(Context context) {
		dbHelper = new MySQLiteHelper(context);
	}

	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}

	public void close() {
		dbHelper.close();
	}

	public PreparationStep createPreparationStep(String preparationStepName) {
		Log.i(PreparationStepDataSource.class.getName(),
				"createPreparationStep: " + preparationStepName);
		ContentValues values = new ContentValues();
		values.put(PreparationStepTable.COLUMN_PreparationStep_NAME,
				preparationStepName);
		long insertId = database.insert(
				PreparationStepTable.TABLE_PreparationStep, null, values);
		Cursor cursor = database.query(
				PreparationStepTable.TABLE_PreparationStep, allColumns,
				PreparationStepTable.COLUMN_PreparationStep_ID + " = "
						+ insertId, null, null, null, null);
		cursor.moveToFirst();
		PreparationStep newPreparationStep = cursorToPreparationStep(cursor);
		cursor.close();
		return newPreparationStep;
	}

	public void deleteCookbook(PreparationStep preparationStep) {
		long id = preparationStep.getId();
		Log.i(CookbookDataSource.class.getName(),
				"PreparationStep deleted with id: " + id);
		database.delete(PreparationStepTable.TABLE_PreparationStep,
				PreparationStepTable.COLUMN_PreparationStep_ID + " = " + id,
				null);
	}

	public List<PreparationStep> getAllPreparationSteps() {
		Log.i(PreparationStepDataSource.class.getName(),
				"getAllPreparationSteps() start");
		List<PreparationStep> preparationSteps = new ArrayList<PreparationStep>();

		Cursor cursor = database.query(
				PreparationStepTable.TABLE_PreparationStep, allColumns, null,
				null, null, null,
				PreparationStepTable.COLUMN_PreparationStep_NAME);
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			PreparationStep preparationStep = cursorToPreparationStep(cursor);
			preparationSteps.add(preparationStep);
			cursor.moveToNext();
		}
		Log.i(PreparationStepDataSource.class.getName(),
				"getAllPreparationSteps() end");
		cursor.close();
		return preparationSteps;
	}

	private PreparationStep cursorToPreparationStep(Cursor cursor) {
		PreparationStep preparationStep = new PreparationStep();
		preparationStep.setId(cursor.getLong(0));
		preparationStep.setName(cursor.getString(1));
		preparationStep.setRecipeId(cursor.getLong(2));
		return preparationStep;
	}
}
