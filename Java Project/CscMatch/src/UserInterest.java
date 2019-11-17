import java.io.Serializable;

@SuppressWarnings("serial")
public class UserInterest implements Comparable<UserInterest>, Serializable {

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
	
	public int compareTo(UserInterest i) { //determines the position of the element relative to m based on the interest level stored in both
		
		int c = 1;
		
		if(this.getInterest().getName().equals(i.getInterest().getName()))
			c = 0;
		else if(this.getLevel() > i.getLevel())
			c = -1;
		
		return c;
	}
	
	public String toString() { //returns name of interest along with the interest level
		return interest.toString() + ": " + this.getLevel();
	}
	
}
