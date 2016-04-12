package entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@NamedQueries({ @NamedQuery(name = "WeeklyPlan.findAll", query = "select o from WeeklyPlan o"),
		@NamedQuery(name = "WeeklyPlan.findByDate", query = "select o from WeeklyPlan o where o.date=:date"),

})
@Entity
public class WeeklyPlan {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private String date;

	@ManyToMany(cascade = { CascadeType.ALL })
	private List<Recipe> recipe;

	public WeeklyPlan(String date, List<Recipe> recipe) {
		this.date = date;
		this.recipe = recipe;
	}

	public WeeklyPlan() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public List<Recipe> getRecipe() {
		return recipe;
	}

	public void setRecipe(List<Recipe> recipe) {
		this.recipe = recipe;
	}
}
