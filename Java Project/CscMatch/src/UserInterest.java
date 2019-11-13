import java.io.Serializable;

public class UserInterest implements Comparable, Serializable {

	private int interestLevel;
	private Interest interest;
	
	public UserInterest(Interest i, int l) {
		interest = i;
		interestLevel = l;
	}
	
	public int getLevel() {
		return interestLevel;
	}
	
	public Interest getInterest() {
		return interest;
	}
	
	public int compareTo(Object in) {
		UserInterest i = (UserInterest) in;
		
		int c = 1;
		
		if(this.getLevel() > i.getLevel())
			c = -1;
		
		return c;
	}
	
	public String toString() {
		return interest.getName() + ": " + this.getLevel();
	}
	
}
