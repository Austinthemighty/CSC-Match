import java.util.Iterator;
import java.util.LinkedList;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

@SuppressWarnings("serial")
public class MembersList extends LinkedList<Member> implements Serializable {

	public OrderedLinkedList<Interest> interests;

	public MembersList() {
		super();
		interests = new OrderedLinkedList<Interest>();
	}

	public void addMember(Member m) {
		this.add(m);
	}

	public void removeMember(Member in) {
		
		this.remove(in); //remove member from list
		
		for(Member m : this) {
			m.removeMatch(in); //remove from all current members any match which contained the removed member
		}
		
	}

	public Member get(String n) { //retrieves member using name
		boolean found = false;
		Member m = null;
		Iterator<Member> itr = this.iterator();
		while(itr.hasNext() && !found) {
			m = itr.next();
			found = m.getName().equalsIgnoreCase(n);
		}

		if(found)
			return m;
		else
			return null;


	}
	
	public String allInterests() { //return all interests within CSC Match
		return !interests.isEmpty() ? "\n-- Other Interests --\n" + interests.toString() : ""; //Display all interests if there are any, else send an empty string.
	}
	
	public String allInterests(Member m) { //returns specified member's interests and all other interests and specifies which group is which
		
		String s = m.listInterests(); //add user's interests
		
		s += "\n-- Others' Interests --\n";
		
		//add all other interests except those already listed
		for(Interest interest : interests) {
			if(!s.contains(interest.getName()))
				s += interest.getName() + "\n"; //append interest name only if it isn't already included
		}
		
		return !interests.isEmpty() ? s : ""; //Display all interests if there are any, else send an empty string.
	}

	public String toString() { //returns a string with the name of each member in the list with a new line in between each
		String string = !this.isEmpty() ? "\n-- Members --\n" : "\n-- No Members --\n";
		Iterator<Member> itr = this.iterator();
		while(itr.hasNext()) {
			Member n = itr.next();
			string += n.getName() + ", " + n.getYear() + "\n";
		}
		return string;
	}

	public String toStringSelection() { //returns a string with the name of each member in the list with an integer on each line
		String string = "\n-- Members --\n";
		int i = 0;
		Iterator<Member> itr = this.iterator();
		while(itr.hasNext()) {
			Member n = itr.next();
			string += (++i) + ": " + n.getName() + ", " + n.getYear();
			string += itr.hasNext() ? "\n" : "\n-------------\n";
		}
		return string;
	}

	public void save(String fileName) throws IOException {

		FileOutputStream fos = new FileOutputStream(fileName); 
		ObjectOutputStream os = new ObjectOutputStream(fos); 
		os.writeObject(this); 
		os.flush(); 
		os.close();
	}

	public static MembersList load(String fileName) throws IOException, ClassNotFoundException {

		FileInputStream fis = new FileInputStream(fileName);
		ObjectInputStream ois = new ObjectInputStream(fis);
		MembersList savedFile = (MembersList) ois.readObject();
		ois.close();
		return savedFile;

	}

}
