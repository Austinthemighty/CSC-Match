import java.util.Iterator;
import java.util.LinkedList;
import java.io.Serializable;

public class MembersList extends LinkedList<Member> implements Serializable {
	
	public MembersList() {
		super();
	}
	
	public void addMember(Member m) {
		this.add(m);
	}
	
	public void removeMember(Member m) {
		this.remove(m);
	}
	
	public Member get(String n) {
		//receives member using name
		boolean found = false;
		Member m = null;
		Iterator<Member> itr = this.iterator();
		while(itr.hasNext() && !found) {
			m = itr.next();
			found = m.getName().equalsIgnoreCase(n) ? true : false;
		}
		
		if(found)
			return m;
		else
			return null;
		
		
	}
	
	public String toString() {
		String string = "-- Members --\n";
		Iterator<Member> itr = this.iterator();
		while(itr.hasNext()) {
			Member n = itr.next();
			string += n.getName() + ", Year " + n.getYear();
			string += itr.hasNext() ? "\n" : "\n-------------\n";
		}
		return string;
	}
	
}
