package entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Ingredient {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private String ingredientName;

	private double ingredientAmount;

	public Ingredient() {
		super();
	}

	public Ingredient(String ingredientName, int ingredientAmount) {
		super();
		this.ingredientName = ingredientName;
		this.ingredientAmount = ingredientAmount;
	}

	public String getIngredientName() {
		return ingredientName;
	}

	public void setIngredientName(String ingredientName) {
		this.ingredientName = ingredientName;
	}

	public double getIngredientAmount() {
		return ingredientAmount;
	}

	public void setIngredientAmount(double ingredientAmount) {
		this.ingredientAmount = ingredientAmount;
	}

}
