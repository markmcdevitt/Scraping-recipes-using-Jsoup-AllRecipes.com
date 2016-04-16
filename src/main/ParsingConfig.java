package main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import org.apache.commons.lang.math.Fraction;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import entity.Category;
import entity.Ingredient;
import entity.Instructions;
import entity.Recipe;
import entity.User;
import persistence.PersistenceUtil;

public class ParsingConfig {

	private List<Ingredient> ingList;
	private List<Instructions> instructionList;
	private String chefs;

	private List<Recipe> recipes = new ArrayList<Recipe>();

	public void createEverything() throws IOException {
		//http://allrecipes.com/recipes/76/appetizers-and-snacks/
		//http://allrecipes.com/recipes/79/desserts/?page=4
		//http://allrecipes.com/recipes/80/main-dish/?page=6
		
		Document doc = Jsoup.connect("http://allrecipes.com/recipes/87/everyday-cooking/vegetarian/?page=7").get();// ?page=2
		Elements recipe = doc.select("article");
		for (Element r : recipe) {

			if (!r.select("h3").text().equals("") && !r.select("div.rec-card__description").text().equals("")) {// gets
				if (r.select("a").first().attr("href").contains("/recipe/")) {

					String title = r.select("h3").text();
					String titleParse = (title);

					String description = r.select("div.rec-card__description").text();
					String descriptionParse = (description);

					Elements imgSrc = r.select("img[src]");
					String imageURLParse = (imgSrc.attr("data-original-src"));

					Elements rating = r.select("div.rating-stars");
					String ratingParse = (rating.attr("data-ratingstars"));

					Element recipeLink = r.select("a").first();
					String link = recipeLink.attr("href");

					String recipeLinkParse = (link);

					/////////////////////////////////////////////////////////////////////////////////////////////////////
					try {

						Document doc2 = Jsoup.connect("http://allrecipes.com/" + recipeLinkParse).get();

						Elements creator = doc2.getElementsByAttributeValue("class", "submitter__name");
						for (Element c : creator) {
							chefs = c.text();
						}

						Elements ingredientsTable = doc2.getElementsByAttributeValue("class",
								"recipe-ingred_txt added");

						ingList = new ArrayList<Ingredient>();

						for (Element i : ingredientsTable) {

							Ingredient ingredientObject = new Ingredient();// object

							String stringIngredient = (i.text());

							String word = null;
							String rest = null;

							if (stringIngredient.contains(" ")) {
								int in = stringIngredient.indexOf(" ");

								word = stringIngredient.substring(0, in);
								rest = stringIngredient.substring(in + 1);// after
																			// the
																			// space
																			// to
																			// the
																			// rest
																			// of
																			// the
																			// line

								if (word.matches(".*\\d+.*")) {

									Fraction fraction = Fraction.getFraction(word);
									double d = fraction.doubleValue();

									String str = rest.replace(",", "");
									ingredientObject.setIngredientName(str);
									ingredientObject.setIngredientAmount(String.valueOf(d));
									ingList.add(ingredientObject);

								} else {
									ingredientObject.setIngredientName(stringIngredient);
									ingList.add(ingredientObject);

								}
							}
						}

						Elements InstructionTable = doc2.getElementsByAttributeValue("class",
								"list-numbers recipe-directions__list");
						instructionList = new ArrayList<Instructions>();

						for (Element il : InstructionTable) {
							String[] arrySteps = il.text().split(Pattern.quote("."));
							String stringInstructions = null;

							for (int i = 0; i < arrySteps.length; i++) {
								stringInstructions = (arrySteps[i].toString());// the
																				// instruction
																				// scrapped
								Instructions instructionObject = new Instructions();// object
								instructionObject.setSteps(stringInstructions); // set
																				// the
																				// instruction
																				// in
																				// the
																				// object
								instructionList.add(instructionObject);
							}
						}

					} catch (IOException e) {
						System.out.println(e);
					}

					int peopleFedNumber = randInt(2, 4);
					String peopleFed = String.valueOf(peopleFedNumber);

					Category category = new Category("Vegetarian");

					int cal = randInt(250, 750);
					String calories = String.valueOf(cal);

					ArrayList<String> complicatedWords = new ArrayList<String>();
					complicatedWords.add("chopped");
					complicatedWords.add("diced");
					complicatedWords.add("saute");
					complicatedWords.add("grated");
					complicatedWords.add("dash");
					complicatedWords.add("smidge");
					complicatedWords.add("minced");
					complicatedWords.add("sliced");
					complicatedWords.add("pan fry");
					complicatedWords.add("liquid measuring cup");
					complicatedWords.add("broiling");
					complicatedWords.add("simmer");
					complicatedWords.add("shredded");

					int score = instructionList.size() + ingList.size() + peopleFedNumber;
					System.out.println(score + " before");
					for (int i = 0; i < complicatedWords.size(); i++) {
						for(Instructions instruction:instructionList){
							if (instruction.getSteps().contains(complicatedWords.get(i))) {
								score += 5;
							}
						}
						
					}

					System.out.println(score + " after");

					String level;
					
					if(score>45){
						level = "Master Chef";
					}else if(score>40){
						level = "Executive Chef";
					}else if(score>35){
						level = "Sous Chef";
					}else if(score>30){
						level = "Prep Chef";
					}else if(score>25){
						level = "Wise Chef";
					}else if(score>20){
						level = "Gifted Chef";
					}else if(score>16){
						level = "Amatuer Cook";
					}else{
						level="Newbie";
					}
					
					Recipe recipesFromParsed = new Recipe(titleParse,level, descriptionParse, imageURLParse, peopleFed,
							calories, category, instructionList, ingList, ratingParse);
					recipes.add(recipesFromParsed);
					List<Recipe> recipes = new ArrayList<Recipe>();
					recipes.add(recipesFromParsed);

					User user = new User(chefs, chefs + "@hotmail.com", "letmein2", true, "ROLE_USER", null, null,
							recipes, null, null);

					PersistenceUtil.merge(user);

				}

			}

		}

	}

	public int randInt(int min, int max) {
		int randomNum = min + (int) (Math.random() * ((max - min) + 1));
		return randomNum;
	}
}