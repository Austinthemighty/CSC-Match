import java.util.Iterator;
import java.io.Serializable;

@SuppressWarnings("serial")
public class Member implements Comparable<Member>, Serializable {

	private String name;
	private String year;
	private OrderedLinkedList<UserInterest> userInterests;
	private OrderedLinkedList<Match> userMatches;
	
	
	public Member(String n, int y) {
		name = n;
		year = translateYear(y);
		userInterests = new OrderedLinkedList<UserInterest>();
		userMatches = new OrderedLinkedList<Match>();
	}
	
	public String getName() {
		return name;
	}
	
	public String getYear() {
		return year;
	}
	
	public void addInterest(Interest i, int l) {
		UserInterest tempUserInterest = new UserInterest(i, l);
		userInterests.add(tempUserInterest);
	}
	
	public int getNumInterests() { //returns number of user's interests
		return userInterests.size();
	}
	
	public String listInterests() {
		
		String s = "\n-- " + name + "'s Interests --\n";
		
		for(UserInterest i : userInterests) {
			s += i.toString() + "\n";
		}
		
		return userInterests.isEmpty() ? "\n-- No Interests Added to " + name + " yet --\n" : s;
	
	}
	
	public void addMatch(Match m) {
		userMatches.add(m);
	}
	
	public void removeMatch(Member m) {
		
		boolean removed = false;
		
		Iterator<Match> itr = userMatches.iterator();
		while(itr.hasNext() && !removed) {
			Match n = itr.next();
			if(n.getMatch().getName().equals(m.getName())) {
				itr.remove();
				removed = true;
			}
		}
		
	}
	
	public int compareTo(Member in) {
		Member m = (Member) in;
		int score = 0;
		boolean intMatch = false;
		
		for(UserInterest i : m.userInterests) {
			
			Iterator<UserInterest> itr = userInterests.iterator();
			
			while(itr.hasNext() && !intMatch) {
				
				UserInterest j = itr.next();
				intMatch = i.getInterest().compareTo(j.getInterest()) == 0;
				score += intMatch ? i.getLevel() * j.getLevel() : 0;
				
			}
			
			score += !intMatch ? i.getLevel()/2 : 0;
			intMatch = false;
		
		}
		
		Match out = new Match(m, score);
		addMatch(out);
		return score;
		
	}
		
	public String toString() { //returns user name, year, interests and top five matches
		
		String string = "Member " + this.getName() + ", " + this.getYear() + "\n";
		
		string += userInterests.size() > 0 ? "\n-- Interests --\n" : "\n-- No Interests --\n";
		for(UserInterest i : userInterests) {
			string += i.toString() + "\n";
		}
		
		string += userMatches.size() > 0 ? "\n-- Top Matches --\n" : "\n-- No Matches, add other members --\n";
		
		for(int i = 0; i < (userMatches.size() < 5 ? userMatches.size() : 5); i++) {
			string += userMatches.get(i).toString() + "\n";
		}
		
		return string;
		
	}
	
	private String translateYear(int y) { //converts integer year to freshman, sophomore etc.
		switch(y) {
		case 1:
			return "Freshman";
		case 2:
			return "Sophomore";
		case 3:
			return "Junior";
		case 4:
			return "Senior";
		case 5:
			return "Alumni";
		default:
			return "";	
		}
	}
	
}
