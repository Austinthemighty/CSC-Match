import java.util.LinkedList;

public class Member {

	String name;
	int year;
	LinkedList<String> interests;
	
	public Member(String n, int y) {
		name = n;
		year = y;
		interests = null;
	}
	
	public String getName() {
		return name;
	}
	
	public int getYear() {
		return year;
	}
	
	public void addInterest(String n, int r) {
		
	}
	
}
