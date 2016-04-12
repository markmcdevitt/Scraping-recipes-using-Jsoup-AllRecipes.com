package main;

import java.util.ArrayList;
import java.util.List;
import entity.Ingredient;
import entity.Instructions;
import entity.Recipe;
import entity.ShoppingList;
import entity.User;
import entity.WeeklyPlan;
import persistence.PersistenceUtil;

public class NotParsedInfo {

	private User user;
	public  void fillTable(){
		
		Ingredient fakeIngredient = new Ingredient("cream cheese",2);//making the ingredients that would go into the recipe	
		List<Ingredient> ingredientList = new ArrayList<Ingredient>();
		ingredientList.add(fakeIngredient);
		

		Instructions no1 = new Instructions("Grease the dish.");
		List<Instructions> fakeInstructions= new ArrayList<Instructions>();//my arraylist for the instructions
		fakeInstructions.add(no1);//adding the instructions to the array
		
		User u = new User();
		Recipe fakeRecipe = new Recipe("pineapple cake","this is food blah blah blah","imageURL","2", "352", fakeInstructions,ingredientList,"3");//this is the recipe being created 
		///////////////////////////////////////////////////////////////////////////////////////CREATE STEPS AND ADDS TO THE RECIPE(ABOVE)
		
		List<Recipe> fakeRecipesFavorited = new ArrayList<Recipe>();// arraylist to take in the favorited recipes
		fakeRecipesFavorited.add(fakeRecipe);//this recipe is now favorited
		
		////////////////////////////////////////////////////////////////////////////////////WEEKLY PLAN NEEDS TO BE MADE WITH THE INGREDIENTS AND THE USERS

		List<Recipe> recipeList = new ArrayList<Recipe>();
		recipeList.add(fakeRecipe);
		
		WeeklyPlan weeklyPlan = new WeeklyPlan("21/10/2015" , recipeList);//the weekly plan
		
		List<WeeklyPlan> fakeWeeklyPlan =new ArrayList<WeeklyPlan>();//the list of the recipes in the persons weekly plan
		fakeWeeklyPlan.add(weeklyPlan);//adds the weekly plan to a list
		
		/////////////////////////////////////////////////////////////////////////////// this is the weekly plan being created(above)

		/////////////////////////////////////////////////////////////////////////////// 
		
		List<Ingredient> fakeShoppingListcreated = new ArrayList<Ingredient>();//is the ingredients input by the user into the shopping list
		
		Ingredient inputIngredients  = new Ingredient("Egg",4);
		Ingredient inputIngredients2 = new Ingredient("rice",2);
		Ingredient inputIngredients3 = new Ingredient("chicken",1);
		
		fakeShoppingListcreated.add(inputIngredients);//adds the ingredients to the shoppinglist list haha
		fakeShoppingListcreated.add(inputIngredients2);
		fakeShoppingListcreated.add(inputIngredients3);
		
		ShoppingList shoppingList = new ShoppingList("2","egg"); 
		
		List<ShoppingList> list = new ArrayList<ShoppingList>(); 
		list.add(shoppingList);
		User user1 = new User("Bob","bob@gmail.com", "letmein2",true,"ROLE_ADMIN",null,recipeList,fakeWeeklyPlan,list);// creates the user and has his favorites obeject with a list of recipes
		User user2 = new User("Tim","Tim@gmail.com", "letmein2",true,"ROLE_ADMIN",null,recipeList,fakeWeeklyPlan,list);// creates the user and has his favorites obeject with a list of recipes
		
		Recipe recipe = new Recipe("Spring Rolls","this is food blah blah blah 2","imageURL","1", "112", fakeInstructions,ingredientList,"4");
		PersistenceUtil.merge(shoppingList);//pushing the shopping list to the database
		PersistenceUtil.merge(recipe);
		PersistenceUtil.merge(user1);
		
	}

}
