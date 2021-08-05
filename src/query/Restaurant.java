package query;
import java.util.ArrayList;

public class Restaurant implements Comparable<Restaurant> {
	
	private String name;
	private String zip;
	private String address;
	private String phone;
	private ArrayList<Inspection> inspectList;

	public Restaurant(String name, String zip) {
		if (name == null || name.isEmpty() || zip.length() != 5) {
			throw new IllegalArgumentException();
		}
		this.name = name;
		this.zip = zip;
		setInspectList(new ArrayList<Inspection>());
	}

	public Restaurant(String name, String zip, String address, String phone) {
		this(name, zip);
		if (address == null || address.isEmpty() || phone == null || phone.isEmpty()) {
			throw new IllegalArgumentException();
		}
		this.address = address;
		this.phone = phone;
	}

	public String getName() {
		return name;
	}

	public String getZip() {
		return zip;
	}

	public String getAddress() {
		return address;
	}

	public String getPhone() {
		return phone;
	}

	public void setInspectList(ArrayList<Inspection> inspectList) {
		this.inspectList = inspectList;
	}
	
	public void addInspection(Inspection inspect) {
		if (inspect == null) {
			throw new IllegalArgumentException();
		}
		else {
		inspectList.add(inspect);
		}
	}

	@Override
	public int compareTo(Restaurant o) {
		if (o.getName().compareTo(name) == 0) {
			if (Integer.parseInt(o.getZip()) > Integer.parseInt(zip)) {
				return -1;
			} 
			else if (Integer.parseInt(o.getZip()) < Integer.parseInt(zip)) {
				return -1;
			} 
			else {
				return 0;
			}
		} 
		else if (o.getName().compareTo(name) > 1) {
			return 1;
		}
		else {
			return -1;
		}
	}

	public boolean equals(Object o) {
		Restaurant objt = (Restaurant) o;
		if (objt.getName().equals(name) && objt.getZip().equals(zip) && objt.getZip().equals(address)
				&& objt.getZip().equals(phone)) {
			return true;
		}
		else {
			return false;
		}
	}

	public String toString() {
		String string = name + "\n";
		string += "------------------------------------\n";
		string += String.format("%-20s%s\n", "address", ": " + address);
		string += String.format("%-20s%s\n", "zip", ": " + zip);
		string += String.format("%-20s%s\n", "phone", ": " + phone);
		string += String.format("%-20s\n", "recent inspection results:");
		for (Inspection inspec : inspectList) {
			string += inspec.toString() + "\n";
		}
		return string;
	}
}
