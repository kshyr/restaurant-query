package query;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Date implements Comparable<Date> {
	private int day;
	private int month;
	private int year;

	public Date(String date) {
		String[] check = date.split(" ");
		date = check[0];
		if (date == null) {
			throw new IllegalArgumentException();
		}
		String regex = "^[0-3][0-9]/[0-3][0-9]/(?:[0-9][0-9])?[0-9][0-9]$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(date);
		if (matcher.matches()) {
			String[] dates = date.split("/");
			this.day = Integer.parseInt(dates[0]);
			this.month = Integer.parseInt(dates[1]);
			this.year = Integer.parseInt(dates[2]);
			if (year < 100) {
				this.year = this.year + 2000;
			}
		} 
		else {
			throw new IllegalArgumentException();
		}
	}

	public Date(int month, int day, int year) {
		if (month >= 1 && month <= 12) {
			this.month = month;
		} 
		else {
			throw new IllegalArgumentException();
		}
		if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
			if (day >= 1 && day <= 31) {
				this.day = day;
			} 
			else {
				throw new IllegalArgumentException();
			}
		} 
		else if (month == 2) {
			if (year == 2000 || year == 2004 || year == 2008 || year == 2012 || year == 2016 || year == 2020
					|| year == 2024) {
				if (day >= 1 && day <= 29) {
					this.day = day;
				} 
				else {
					throw new IllegalArgumentException();
				}
			}
			if (day >= 1 && day <= 28) {
				this.day = day;
			} 
			else {
				throw new IllegalArgumentException();
			}
		} 
		else {
			if (day >= 1 && day <= 30) {
				this.day = day;
			} 
			else {
				throw new IllegalArgumentException();
			}
		}
		if (year >= 2000 && year <= 2025) {
			this.year = year;
		} 
		else {
			throw new IllegalArgumentException();
		}

	}

	@Override
	public int compareTo(Date o) {
		if (year >= o.year && month >= o.month && day > o.day) {
			return -1;
		}
		else if (day == o.day) {
			return 0;
		}
		else {
			return 1;
		}
	}

	
	public String toString() {
		if (this.day < 10 && this.month < 10) {
			return String.format("0%1d", month) + "/" + String.format("0%1d", day) + "/" + year;
		}
		else if (this.day < 10) {
			return String.format("%2d", month) + "/" + String.format("0%1d", day) + "/" + year;
		}
		else if (this.month < 10) {
			return String.format("0%1d", month) + "/" + String.format("%2d", day) + "/" + year;
		}
		else {
			return String.format("%2d", month) + "/" + String.format("%2d", day) + "/" + year;
		}
	}
}
