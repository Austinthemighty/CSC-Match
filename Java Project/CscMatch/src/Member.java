import java.util.Iterator;
import java.util.LinkedList;
import java.io.Serializable;

public class Member implements Comparable, Serializable {

	private String name;
	private int year;
	private OrderedLinkedList<UserInterest> userInterests;
	private OrderedLinkedList<Match> userMatches;
	
	
	public Member(String n, int y) {
		name = n;
		year = y;
		userInterests = new OrderedLinkedList<UserInterest>();
		userMatches = new OrderedLinkedList<Match>();
	}
	
	public String getName() {
		return name;
	}
	
	public int getYear() {
		return year;
	}
	
	public void addInterest(Interest i, int l) {
		UserInterest tempUserInterest = new UserInterest(i, l);
		userInterests.add(tempUserInterest);
	}
	
	public void addMatch(Match m) {
		userMatches.add(m);
	}
	
	public int compareTo(Object in) {
		Member m = (Member) in;
		int score = 0;
		boolean intMatch = false;
		
		for(UserInterest i : m.userInterests) {
			
			Iterator<UserInterest> itr = userInterests.iterator();
			
			while(itr.hasNext() && !intMatch) {
				
				UserInterest j = itr.next();
				intMatch = i.getInterest().compareTo(j.getInterest()) == 0 ? true : false;
				score += intMatch ? i.getLevel() * j.getLevel() : 0;
				
			}
			
			score += !intMatch ? i.getLevel()/2 : 0;
			intMatch = false;
		
		}
		Match out = new Match(m, score);
		addMatch(out);
		return score;
	}
	
	public String toString() {
		
		String string = "Member ";
		string += this.getName() + ", Year " + this.getYear() + "\n";
		
		string += userInterests.size() > 0 ? "-- Interests --\n" : "\n-- No Interests --\n\n";
		for(UserInterest i : userInterests) {
			string += i.toString() + "\n";
		}
		
		string += userMatches.size() > 0 ? "\n-- Matches --\n" : "\n-- No Match, add other members--\n";
		
		for(Match m : userMatches) {
			string += m.toString() + "\n";
		}
		
		return string;
		
	}
	
}
