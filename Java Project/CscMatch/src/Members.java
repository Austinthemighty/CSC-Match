import java.util.LinkedList;

public class Members {
	
	LinkedList<Member> members = new LinkedList<Member>();
	
	public Members() {
		//LinkedList<Member> members = new LinkedList<Member>();
	}
	
	public void addMember(Member m) {
		members.add(m);
	}
	
	public void removeMember(Member m) {
		members.remove(m);
	}
	
	public String toString() {
		String string = "";
		for(Member m : members) {
			string += m.getName() + ": " + m.getYear() + "; ";
		}
		return string;
	}
	public int size() {
		
		return members.size();
		
	}
}
