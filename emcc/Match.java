import java.io.Serializable;

public class Match implements Comparable, Serializable {

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
		
		if(this.getMatch().getName() == m.getMatch().getName())
			c = 0;
		else if(this.getScore() > m.getScore())
			c = -1;
		
		return c;
	}
	
}
