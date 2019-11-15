import java.util.LinkedList;
import java.io.Serializable;

public class OrderedLinkedList<T> extends LinkedList<T> implements OrderedListADT, Serializable {

	public OrderedLinkedList() {
		super();
	}
	
	public boolean add(Object in) {
		T m = (T) in;
		Comparable comparableElement = (Comparable)m;
		
		int i = -1;
		int c = 1;
		boolean equal = false;
		
		while(c > 0 && i < this.size() - 1) {
			i++;
			T n = this.get(i);
			c = comparableElement.compareTo(n);
		}
		
		if(c == 0) {
			this.set(i, m);
			equal = true;
		}
		else if(c == -1)
			super.add(i, m);
		else if(c == 1)
			this.addLast(m);
		
		return !equal;
		
	}
	
	public String toString() {
		String string = "";
		for(int i = 0; i < this.size(); i++) {
			string += this.get(i).toString();
			if(i < this.size() - 1)
				string += "\n";
		}
		return string;
	}
	
}
