import java.io.Serializable;

@SuppressWarnings("serial")
public class Interest implements Comparable<Interest>, Serializable {

	private String interestName;
	
	public Interest(String n) {
		interestName = n;
	}
	
	public String getName() {
		return interestName;
	}
	
	public int compareTo(Interest interest) { //determines position relative to another in alphabetical order
		int c = 0;
		int i = 0;
		String m = this.getName();
		String n = interest.getName();
		while(c == 0 && i < m.length() && i < n.length()) {
			char charM = m.charAt(i);
			char charN = n.charAt(i);
			if(charM < charN) {
				c = -1;
			}else if (charM > charN) {
				c = 1;
			}else
				i++;
		}
		
		if(c == 0 && m.length() == n.length())
			c = 0;
		else if(c == 0)
			c = m.length() < n.length() ? -1 : 1;
		
		return c;
		
	}
	
	public String toString() { //returns name of interest
		return this.getName();
	}
	
}
