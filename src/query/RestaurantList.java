package query;

public class RestaurantList extends LinkedList<Restaurant> {

	public RestaurantList getMatchingRestaurants(String keyword) {
		if (keyword == null || keyword.isEmpty()) {
			return null;
		}
		RestaurantList list = new RestaurantList();
		for (int i = 0; i < list.size(); i++) {
			Restaurant restaurant = list.get(i);
			String name = restaurant.getName().replace(" ", "");
			if (name.toLowerCase().contains(keyword.toLowerCase())) {
				list.add(restaurant);
			}
		}
		
		return list;
	}

	public RestaurantList getMatchingZip(String keyword) {
		if (keyword == null || keyword.isEmpty()) {
			return null;
		}
		RestaurantList list = new RestaurantList();
		for (int i = 0; i < list.size(); i++) {
			Restaurant restaurant = list.get(i);
			if (restaurant.getZip().toLowerCase().equals(keyword.toLowerCase())) {
				list.add(restaurant);
			}
		}
		return list;
	}

	public String toString() {
		String str = "";
		RestaurantList list = new RestaurantList();
		for (int i = 0; i < list.size(); i++) {
			Restaurant restaurant = list.get(i);
			str += restaurant.toString() + "\n";
		}
		return str;
	}


}
