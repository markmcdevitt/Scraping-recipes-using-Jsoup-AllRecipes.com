package entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
public class Recipe {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private String titleParse;
	private String descriptionParse;
	private String imageURLParse;
	private String peopleFed;
	private String calories;
	private String level;
	@ManyToOne(cascade = CascadeType.ALL)
	private Category category;

	@OneToMany(cascade = CascadeType.ALL)
	private List<Instructions> instructions;// the list of steps

	@ManyToMany(cascade = { CascadeType.ALL })
	public List<Ingredient> ingredients;// list of ingredients

	@ManyToMany(cascade = CascadeType.ALL)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<WeeklyPlan> weeklyPlans;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Review> review;

	private String totalRating;

	public Recipe() {

	}

	public Recipe(String titleParse, String level, String descriptionParse, String imageURLParse, String peopleFed,
			String calories, Category category, List<Instructions> instructions, List<Ingredient> ingredients,
			String totalRating) {
		super();
		this.level = level;
		this.category = category;
		this.titleParse = titleParse;
		this.descriptionParse = descriptionParse;
		this.imageURLParse = imageURLParse;
		this.peopleFed = peopleFed;
		this.calories = calories;
		this.instructions = instructions;
		this.ingredients = ingredients;
		this.totalRating = totalRating;
	}

	public Recipe(String titleParse, String level, String descriptionParse, String imageURLParse, String peopleFed,
			String calories, Category category, List<Instructions> instructions, List<Ingredient> ingredients,
			List<WeeklyPlan> weeklyPlans, List<Review> review, String totalRating) {
		super();
		this.level = level;
		this.titleParse = titleParse;
		this.descriptionParse = descriptionParse;
		this.imageURLParse = imageURLParse;
		this.peopleFed = peopleFed;
		this.calories = calories;
		this.category = category;
		this.instructions = instructions;
		this.ingredients = ingredients;
		this.weeklyPlans = weeklyPlans;
		this.review = review;
		this.totalRating = totalRating;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitleParse() {
		return titleParse;
	}

	public void setTitleParse(String titleParse) {
		this.titleParse = titleParse;
	}

	public String getDescriptionParse() {
		return descriptionParse;
	}

	public void setDescriptionParse(String descriptionParse) {
		this.descriptionParse = descriptionParse;
	}

	public String getImageURLParse() {
		return imageURLParse;
	}

	public void setImageURLParse(String imageURLParse) {
		this.imageURLParse = imageURLParse;
	}

	public List<Instructions> getInstructions() {
		return instructions;
	}

	public void setInstructions(List<Instructions> instructions) {
		this.instructions = instructions;
	}

	public List<Ingredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}

	public List<Review> getReview() {
		return review;
	}

	public void setReview(List<Review> review) {
		this.review = review;
	}

	public List<WeeklyPlan> getWeeklyPlans() {
		return weeklyPlans;
	}

	public void setWeeklyPlans(List<WeeklyPlan> weeklyPlans) {
		this.weeklyPlans = weeklyPlans;
	}

	public String getTotalRating() {
		return totalRating;
	}

	public void setTotalRating(String totalRating) {
		this.totalRating = totalRating;
	}

	public String getPeopleFed() {
		return peopleFed;
	}

	public void setPeopleFed(String peopleFed) {
		this.peopleFed = peopleFed;
	}

	public String getCalories() {
		return calories;
	}

	public void setCalories(String calories) {
		this.calories = calories;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	@Override
	public String toString() {
		return "Recipe [id=" + id + ", titleParse=" + titleParse + ", descriptionParse=" + descriptionParse
				+ ", imageURLParse=" + imageURLParse + ", peopleFed=" + peopleFed + ", calories=" + calories
				+ ", level=" + level + ", category=" + category + ", instructions=" + instructions + ", ingredients="
				+ ingredients + ", weeklyPlans=" + weeklyPlans + ", review=" + review + ", totalRating=" + totalRating
				+ "]";
	}

}