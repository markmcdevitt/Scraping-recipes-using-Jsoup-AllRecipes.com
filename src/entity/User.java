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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User {
	@Id
	private String username;
	private String email;
	private String password;
	private boolean enabled;
	private String authority;
	private int userLevel;

	@OneToMany(cascade = CascadeType.ALL)
	private List<Favorite> usersFavorites;

	@ManyToMany
	private List<Allergy> usersAllergys;

	@OneToMany
	private List<IngredientsOwned> ingredientsOwned;

	@OneToMany(cascade = CascadeType.ALL)
	private List<Recipe> recipes;

	@ManyToMany(cascade = CascadeType.ALL)
	private List<WeeklyPlan> weeklyPlan;

	@OneToMany(cascade = { CascadeType.ALL })
	private List<ShoppingList> shoppingList;

	public User() {

	}

	public User(String username, String email, String password, boolean enabled, String authority, int userLevel,
			List<Favorite> usersFavorites, List<Allergy> usersAllergys, List<IngredientsOwned> ingredientsOwned,
			List<Recipe> recipes, List<WeeklyPlan> weeklyPlan, List<ShoppingList> shoppingList) {
		super();
		this.username = username;
		this.email = email;
		this.password = password;
		this.enabled = enabled;
		this.authority = authority;
		this.userLevel = userLevel;
		this.usersFavorites = usersFavorites;
		this.usersAllergys = usersAllergys;
		this.ingredientsOwned = ingredientsOwned;
		this.recipes = recipes;
		this.weeklyPlan = weeklyPlan;
		this.shoppingList = shoppingList;
	}

	public List<Recipe> getRecipes() {
		return recipes;
	}

	public void setRecipes(List<Recipe> recipes) {
		this.recipes = recipes;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public List<WeeklyPlan> getWeeklyPlan() {
		return weeklyPlan;
	}

	public void setWeeklyPlan(List<WeeklyPlan> weeklyPlan) {
		this.weeklyPlan = weeklyPlan;
	}

	public List<ShoppingList> getShoppingList() {
		return shoppingList;
	}

	public void setShoppingList(List<ShoppingList> shoppingList) {
		this.shoppingList = shoppingList;
	}

	public List<IngredientsOwned> getIngredientsOwned() {
		return ingredientsOwned;
	}

	public void setIngredientsOwned(List<IngredientsOwned> ingredientsOwned) {
		this.ingredientsOwned = ingredientsOwned;
	}

	public List<Allergy> getUsersAllergys() {
		return usersAllergys;
	}

	public void setUsersAllergys(List<Allergy> usersAllergys) {
		this.usersAllergys = usersAllergys;
	}

	public int getUserLevel() {
		return userLevel;
	}

	public void setUserLevel(int userLevel) {
		this.userLevel = userLevel;
	}

	public List<Favorite> getUsersFavorites() {
		return usersFavorites;
	}

	public void setUsersFavorites(List<Favorite> usersFavorites) {
		this.usersFavorites = usersFavorites;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", email=" + email + ", password=" + password + ", enabled=" + enabled
				+ ", authority=" + authority + ", userLevel=" + userLevel + ", usersFavorites=" + usersFavorites
				+ ", usersAllergys=" + usersAllergys + ", ingredientsOwned=" + ingredientsOwned + ", recipes=" + recipes
				+ ", weeklyPlan=" + weeklyPlan + ", shoppingList=" + shoppingList + "]";
	}

}
