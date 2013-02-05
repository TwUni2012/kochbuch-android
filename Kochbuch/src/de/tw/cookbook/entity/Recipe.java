package de.tw.cookbook.entity;

import java.util.ArrayList;

public class Recipe {

	private long id;
	private String name;
	private long cookbookId;
	private String description;
	private ArrayList<PreparationStep> preparationStep;
	
	public Recipe() {
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getCookbookId() {
		return cookbookId;
	}
	public void setCookbookId(long cookbookId) {
		this.cookbookId = cookbookId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public ArrayList<PreparationStep> getPreparationStep() {
		return preparationStep;
	}
	public void setPreparationStep(ArrayList<PreparationStep> preparationStep) {
		this.preparationStep = preparationStep;
	}
}
