import java.io.Serializable;

@SuppressWarnings("serial")
public class Match implements Comparable<Match>, Serializable {

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
	
	public int compareTo(Match m) { //determines the position of the element relative to m based on the score stored in both
		
		int c = 1;
		
		if(this.getMatch().getName() == m.getMatch().getName())
			c = 0;
		else if(this.getScore() > m.getScore())
			c = -1;
		
		return c;
	}
	
	public String toString() { //returns the member's name along with their year
		return match.getName() + ", " + match.getYear() + ": " + score;
	}
	
}
