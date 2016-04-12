package entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class IngredientsOwned {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String ingredientOwned;

	public IngredientsOwned() {

	}

	public IngredientsOwned(String ingredientOwned) {
		super();
		this.ingredientOwned = ingredientOwned;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIngredientOwned() {
		return ingredientOwned;
	}

	public void setIngredientOwned(String ingredientOwned) {
		this.ingredientOwned = ingredientOwned;
	}

	@Override
	public String toString() {
		return "IngredientsOwned [ingredientOwned=" + ingredientOwned + "]";
	}

}
