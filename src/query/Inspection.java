package query;

public class Inspection {
	
	private Date date;
	private int score;
	private String violation;
	private String risk;

	public Inspection(Date date, int score, String violation, String risk) {
		
		if (date == null || score < 0 || score > 100) {
			throw new IllegalArgumentException();
		}
		
		this.date = date;
		this.score = score;
		this.violation = violation;
		this.risk = risk;
	}

	@Override
	public String toString() {
		if(this.violation == null && this.risk == null) {
			return score + ", " + date.toString();
		}
		else if(this.violation == null) {
			return score + ", " + date.toString() + ", " + risk;
		}
		else if(this.risk == null) {
			return score + ", " + date.toString() + ", " + violation;
		}
		else {
			return score + ", " + date.toString() + ", " + risk + ", " + violation;
		}
	}

}
