package de.tw.cookbook.entity;

public class PreparationStep {
	private String name;
	private long id;
	private long recipeId;
	
	public PreparationStep() {
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getRecipeId() {
		return recipeId;
	}

	public void setRecipeId(long recipeId) {
		this.recipeId = recipeId;
	}

	@Override
	public String toString() {
		return name;
	}
}
