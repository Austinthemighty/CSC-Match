import java.util.Iterator;
import java.util.LinkedList;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class MembersList extends LinkedList<Member> implements Serializable {

	public OrderedLinkedList<Interest> interests;

	public MembersList() {
		super();
		interests = new OrderedLinkedList<Interest>();
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
			found = m.getName().equalsIgnoreCase(n);
		}

		if(found)
			return m;
		else
			return null;


	}
	
	public String allInterests() {
		return !interests.isEmpty() ? "\n-- Other Interests --\n" + interests.toString() : ""; //Display all interests if there are any, else send an empty string.
	}

	public String toString() {
		String string = !this.isEmpty() ? "\n-- Members --\n" : "\n-- No Members --\n";
		Iterator<Member> itr = this.iterator();
		while(itr.hasNext()) {
			Member n = itr.next();
			string += n.getName() + ", Year " + n.getYear();
			string += itr.hasNext() ? "\n" : "\n-------------\n";
		}
		return string;
	}

	public String toStringSelection() {
		String string = "\n-- Members --\n";
		int i = 0;
		Iterator<Member> itr = this.iterator();
		while(itr.hasNext()) {
			Member n = itr.next();
			string += (++i) + ": " + n.getName() + ", Year " + n.getYear();
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
