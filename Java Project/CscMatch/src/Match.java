
public class Match implements Comparable {

	private Member match;
	private int score;
	
	public Match(Member m, int s) {
		match = m;
		score = s;
	}
	
	public Member getMatch() {
		return match;
	}
	
	public int getScore() {
		return score;
	}
	
	public String toString() {
		return match.getName() + ", Year " + match.getYear() + ": " + score;
	}
	
	public int compareTo(Object in) {
		Match m = (Match) in;
		
		int c = 1;
		
		if(this.getScore() > m.getScore())
			c = -1;
		
		return c;
	}
	
}
