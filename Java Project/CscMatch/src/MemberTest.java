
public class MemberTest {
	
	private static OrderedLinkedList<Interest> interests;

	public static void main(String[] args) {
		
		interests = new OrderedLinkedList<Interest>();
		
		Member memberA = new Member("A", 3);
		Member memberB = new Member("B", 3);
		
		memberA.addInterest("1", 2);
		memberA.addInterest("2", 3);
		memberA.addInterest("3", 5);
		
		memberB.addInterest("1", 5);
		memberB.addInterest("3", 3);
		memberB.addInterest("4", 7);
		memberB.addInterest("5", 1);
		memberB.addInterest("6", 9);
		
		System.out.println(memberA.compareTo(memberB));

	}
	
	public static Interest newInterest(Interest i) {
		interests.add(i);
		return interests.get(interests.indexOf(i));
	}

}
