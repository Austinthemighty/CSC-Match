
public class Match {

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
		return match.getName() + ": " + score;
	}
	
}
