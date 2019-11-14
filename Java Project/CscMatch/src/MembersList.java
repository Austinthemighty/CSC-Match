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
	
	public String toString() {
		String string = "\n-- Members --\n";
		Iterator<Member> itr = this.iterator();
		while(itr.hasNext()) {
			Member n = itr.next();
			string += n.getName() + ", Year " + n.getYear();
			string += itr.hasNext() ? "\n" : "\n-------------\n";
		}
		return string;
	}
	
}
