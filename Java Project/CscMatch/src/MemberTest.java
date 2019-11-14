
public class MemberTest {
	
	private static OrderedLinkedList<Interest> interests;
	private static MembersList members;
	
	public static void main(String[] args) {
		
		interests = new OrderedLinkedList<Interest>();
		members = new MembersList();
		
		Member memberA = new Member("A", 3);
		Member memberB = new Member("B", 2);
		
		members.add(memberA);
		members.add(memberB);
		
		memberA.addInterest("1", 2);
		memberA.addInterest("2", 3);
		memberA.addInterest("3", 5);
		
		memberB.addInterest("1", 5);
		memberB.addInterest("3", 3);
		memberB.addInterest("4", 7);
		memberB.addInterest("5", 1);
		memberB.addInterest("6", 9);
		
		compare(members);
		System.out.println(members.toString());
		System.out.println(members.get(1).toString());

	}
	
	public static Interest newInterest(Interest i) {
		interests.add(i);
		return interests.get(interests.indexOf(i));
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
