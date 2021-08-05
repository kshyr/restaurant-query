package query;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class SFRestaurantData {
	
	private static ArrayList<String> strings;
	private static RestaurantList list = new RestaurantList();

	public static void main(String[] args) throws FileNotFoundException {
		File file = new File("SF_restaurant_scores_full.csv");
		if (file.exists()) {
			Scanner readFile = new Scanner(file);
			readFile.nextLine();

			while (readFile.hasNext()) {
				strings = splitCSVLine(readFile.nextLine());
				try {

					if (strings.size() > 16) {
						Restaurant restaurant = new Restaurant(strings.get(1), strings.get(5), strings.get(2), strings.get(9));
						
						Inspection inspection = new Inspection(new Date(strings.get(11)), Integer.parseInt(strings.get(12)), strings.get(15), strings.get(16));

						boolean found = false;

						for (int i = 0; i < list.size(); i++) {
							Restaurant r = list.get(i);
							if (r.getName().equals(strings.get(1)) && r.getAddress().equals(strings.get(2))) {
								found = true;
								list.get(i).addInspection(inspection);
								break;
							}
						}

						if (!found) {
							restaurant.addInspection(inspection);
							list.add(restaurant);
						}
					}
				} 
				catch (IllegalArgumentException ex) {

				}
			}
			readFile.close();
			welcome();
			while (true) {
				mainMenu();
			}
		} 
		else {
			System.err.println("Usage Error: the program expects file name as an argument.");
			System.exit(-1);
		}
	}

	private static void mainMenu() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("\n\nEnter your search query:");
		String query = scanner.nextLine();

		if (query.startsWith("name")) {
			String[] data = query.split(" ");
			String keyword = data[1];
			RestaurantList temp = list.getMatchingRestaurants(keyword);
			if (temp == null) {
				System.out.println("No matches found. Try again");
			}
			for (int i = 0; i < list.size(); i++) {
				Restaurant res = list.get(i);
				System.out.println(res);
			}

		} 
		else if (query.startsWith("zip")) {
			String[] data = query.split(" ");
			String keyword = data[1];
			RestaurantList temp = list.getMatchingZip(keyword);

			if (temp == null) {
				System.out.println("No matches found. Try again");
			}
			for (int i = 0; i < list.size(); i++) {
				Restaurant res = list.get(i);
				System.out.println(res);
			}
		} 
		else if (query.equals("quit")) {
			System.exit(0);
		} 
		else

		{
			System.out.println("This is not a valid query. Try again.");
		}

	}
	
	private static void welcome() {
		System.out.println("Search the database by matching keywords to titles or actor names.");
		System.out.println("\tTo search for matching restaurant names, enter");
		System.out.println("\t\tname KEYWORD");
		System.out.println("\tTo search for restaurants in a zip code, enter");
		System.out.println("\t\tzip KEYWORD");
		System.out.println("\tTo finish the program, enter");
		System.out.println("\t\tquit");
	}


	public static ArrayList<String> splitCSVLine(String textLine) {
		if (textLine == null)
			return null;
		ArrayList<String> entries = new ArrayList<String>();
		int lineLength = textLine.length();
		StringBuffer nextWord = new StringBuffer();
		char nextChar;
		boolean insideQuotes = false;
		boolean insideEntry = false;
		// iterate over all characters in the textLine
		for (int i = 0; i < lineLength; i++) {
			nextChar = textLine.charAt(i);
			// handle smart quotes as well as regular quotes
			if (nextChar == '"' || nextChar == '\u201C' || nextChar == '\u201D') {
				// change insideQuotes flag when nextChar is a quote
				if (insideQuotes) {
					insideQuotes = false;
					insideEntry = false;
				} else {
					insideQuotes = true;
					insideEntry = true;
				}
			} else if (Character.isWhitespace(nextChar))

			{
				if (insideQuotes || insideEntry) {
					// add it to the current entry
					nextWord.append(nextChar);
				} else { // skip all spaces between entries continue;
				}
			} else if (nextChar == ',') {
				if (insideQuotes) { // comma inside an entry
									// nextWord.append(nextChar);
				} else { // end of entry found
					insideEntry = false;
					entries.add(nextWord.toString());
					nextWord = new StringBuffer();
				}
			} else {
				// add all other characters to the nextWord
				nextWord.append(nextChar);
				insideEntry = true;
			}
		}
		// add the last word ( assuming not empty )
		// trim the white space before adding to the list if

		if (!nextWord.toString().equals("")) {
			entries.add(nextWord.toString().trim());
		}
		return entries;
	}
}