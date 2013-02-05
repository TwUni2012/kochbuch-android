package de.tw.cookbook.entity;

import java.io.Serializable;
import java.util.ArrayList;

public class Recipe implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private long id;
	private String name;
	private long cookbookId;
	private String description;
//	private String preparationSteps;
	
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
	
//	public ArrayList<String> getPreparationStepAsArrayList() {
//		String[] splitPreparationSteps = preparationSteps.split("::");
//		ArrayList<String> result = new ArrayList<String>();
//		for(String step : splitPreparationSteps) {
//			result.add(step);
//		}
//		return result;
//	}
//	
//	public String getPreparationStep() {
//		return preparationSteps;
//	}
//	
//	public void addPreparationStep(String step) {
//		preparationSteps = preparationSteps + "::" + step;
//	}
//	
//	public void setPreparationStep(String preparationStep) {
//		this.preparationSteps = preparationStep;
//	}
	
	@Override
	public String toString() {
		return name;
	}
}
