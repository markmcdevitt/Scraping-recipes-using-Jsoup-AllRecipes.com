package main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.commons.lang.math.Fraction;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import entity.Ingredient;
import entity.Instructions;
import entity.Recipe;
import entity.User;
import persistence.PersistenceUtil;

public class ParsingConfig {

	private List<Ingredient> ingList; // global arraylist
	private List<Instructions> instructionList; // global arraylist
	private String chefs; // global arraylist

	public void createEverything() throws IOException {

		Document doc = Jsoup.connect("http://allrecipes.com/").get();// ?page=2
		Elements recipe = doc.select("article");// going through the table named
												// article
		for (Element r : recipe) {

			if (!r.select("h3").text().equals("") && !r.select("div.rec-card__description").text().equals("")) {// gets
				if (r.select("a").first().attr("href").contains("/recipe/")) {

					String title = r.select("h3").text();
					System.out.println("Reipe name: " + title);
					String titleParse = (title);

					String description = r.select("div.rec-card__description").text();
					System.out.println("Description: " + description);
					String descriptionParse = (description);

					Elements imgSrc = r.select("img[src]");
					String imageURLParse = (imgSrc.attr("data-original-src"));
					
					System.out.print("Rating: ");
					Elements rating = r.select("div.rating-stars");
					System.out.println(rating.attr("data-ratingstars"));
					String ratingParse = (rating.attr("data-ratingstars"));

					Element recipeLink = r.select("a").first();
					String link = recipeLink.attr("href");

					String recipeLinkParse = (link);

					// ///////////////////////////////////////////////////////////////////////////////////////////////////
					try {

						Document doc2 = Jsoup.connect("http://allrecipes.com/" + recipeLinkParse).get();

						Elements creator = doc2.getElementsByAttributeValue("class", "submitter__name");
						for (Element c : creator) {
							chefs = c.text();
						}

						Elements ingredientsTable = doc2.getElementsByAttributeValue("class",
								"recipe-ingred_txt added");
						System.out.println("There are " + ingredientsTable.size() + " Ingredients in this dish ");

						ingList = new ArrayList<Ingredient>();

						for (Element i : ingredientsTable) {

							Ingredient ingredientObject = new Ingredient();// object

							String stringIngredient = (i.text());// the
																	// ingredient
																	// scrapped

							String word = null;
							String rest = null;

							if (stringIngredient.contains(" ")) {
								int in = stringIngredient.indexOf(" "); // 4

								word = stringIngredient.substring(0, in);// from
																			// 0
																			// to
																			// 3
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
									System.out.println("The rest of the ingredient: " + str);
									ingredientObject.setIngredientAmount(d);
									System.out.println("The ingredients amount: " + word);
									ingList.add(ingredientObject);

								} else {
									System.out.println("here2");
									ingredientObject.setIngredientName(stringIngredient);
									ingList.add(ingredientObject);

								}

							}

						}

						// INGREDIENTS(ABOVE)
						// INSTRUCTIONS (BELOW)

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
								instructionList.add(instructionObject);// the
																		// object
																		// is
																		// added
																		// to
																		// the
																		// list
							}

						}

					} catch (IOException e) {
						System.out.println(e);
					}

					System.out.println("//-------------------------------------------------------//");

					int peopleFedNumber = randInt(2, 4);
					String peopleFed = String.valueOf(peopleFedNumber);
					
					int cal = randInt(250, 750);
					String calories = String.valueOf(cal);
					Recipe recipesFromParsed = new Recipe(titleParse, descriptionParse, imageURLParse,peopleFed,calories, instructionList,
							ingList,ratingParse);
					List<Recipe> recipes = new ArrayList<Recipe>();
					recipes.add(recipesFromParsed);

					 User user = new User(chefs, chefs + "@hotmail.com", "letmein2", true, "ROLE_USER",null, null,recipes, null, null);
					 
					
					 PersistenceUtil.merge(user);

				}

			}

		}

	}

	public int randInt(int min, int max) {
		

		int randomNum = min + (int)(Math.random() * ((max - min) + 1));

		return randomNum;

	}
}