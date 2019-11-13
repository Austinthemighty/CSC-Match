import java.util.Iterator;
import java.util.LinkedList;
import java.io.Serializable;

public class MembersList implements Serializable {
	
	private LinkedList<Member> membersList;
	
	public MembersList() {
		membersList = new LinkedList<Member>();
	}
	
	public void addMember(Member m) {
		membersList.add(m);
	}
	
	public void removeMember(Member m) {
		membersList.remove(m);
	}
	
	public String toString() {
		String string = "";
		Iterator<Member> itr = membersList.iterator();
		while(itr.hasNext()) {
			Member n = itr.next();
			string += n.getName() + ": " + n.getYear();
			string += itr.hasNext() ? "; " : "";
		}
		return string;
	}
	
}
