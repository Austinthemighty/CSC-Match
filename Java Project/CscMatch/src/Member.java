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
	}
	
	public String getName() {
		return name;
	}
	
	public int getYear() {
		return year;
	}
	
	public void addInterest(String n, int l) {
		Interest temp = new Interest(n);
		UserInterest tempUserInterest = new UserInterest(MemberTest/*Member Test gets replaced with main() class*/.newInterest(temp), l);
		userInterests.add(tempUserInterest);
		
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
		return score;
	}
	
}
