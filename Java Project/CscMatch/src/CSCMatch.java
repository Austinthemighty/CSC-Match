import java.util.Iterator;
import java.util.Scanner;

public class CSCMatch {
	
	private static OrderedLinkedList<Interest> interests;
	private static MembersList members;
	
	public static void main(String[] args) {
		
		interests = new OrderedLinkedList<Interest>();
		members = new MembersList();
		
		/*Member memberA = new Member("A", 3);
		Member memberB = new Member("B", 2);
		
		members.add(memberA);
		members.add(memberB);
		*/
		
		addMember("A", 3);
		addMember("B", 2);
		
		addInterest(members.get("A"), "1", 2);
		addInterest(members.get("A"), "2", 3);
		addInterest(members.get("A"), "3", 5);
		
		addInterest(members.get("B"), "1", 5);
		addInterest(members.get("B"), "3", 3);
		addInterest(members.get("B"), "4", 7);
		addInterest(members.get("B"), "5", 1);
		addInterest(members.get("B"), "6", 9);
		
		compare(members);
		listAllMembers(members);
		listMember(members.get("B"));
		//System.out.println(members.toString());
		//System.out.println(members.get(1).toString());

	}
	
	public static void addMember(String n, int y) {
		n = checkName(n);
		Member tempMember = new Member(n, y);
		members.add(tempMember);
	}
	
	private static String checkName(String n) {
		Scanner scnr = new Scanner(System.in);
		
		boolean unique = true;
		do {
			Iterator<Member> itr = members.iterator();
			while(itr.hasNext() && unique) {
				unique = !itr.next().getName().equalsIgnoreCase(n) ? true : false;
			}
			if(!unique) {
				System.out.print("Name taken, please enter a different name: ");
				n = scnr.nextLine();
			}
		} while(!unique);
		
		return n;
	}
	
	public static void addInterest(Member m, String n, int l) {
		Interest i = new Interest(n);
		interests.add(i);
		members.get(members.indexOf(m)).addInterest(i, l);
	}
	
	public static void listMember(Member m) {
		System.out.println(m.toString());
	}
	
	public static void listAllMembers(MembersList m) {
		System.out.println(m.toString());
	}
	
	private static void compare(MembersList m) {
		int z = 0;
		for(int i = 0; i < m.size() - 1; i++) {
			z++;
			for(int j = z; j < m.size(); j++) {
				m.get(i).compareTo(m.get(j));
				m.get(j).compareTo(m.get(i));
			}
			
		}
	}

}
