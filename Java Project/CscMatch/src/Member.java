import java.util.LinkedList;

public class Member {

	String name;
	int year;
	int uuid;
	LinkedList<String> interests;
	
	public Member() {
		
	}
	public Member(String n) {
		this.name = n;
	}
	
	public Member(String n, int y, int u) {
		name = n;
		this.year = y;
		interests = null;
		this.uuid = u;
	}
	public void name(String n) {
		this.name = n;
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
