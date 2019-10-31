import java.util.NoSuchElementException;

public class List<T> {
	private Node<T> head;
	
	public List() {
		head = null;
	}
	
	public int length() {
		Node<T> n;
		int i;
		
		for (i = 0, n = head; n != null; n = n.getNext(), i++);
		
		return i;
	}
	
	public void addFirst(T x) {
		head = new Node<T>(x, head);
	}
	
	public void addLast(T x) {
		if (head == null) {
			head = new Node<T>(x, null);
		} else {		
			Node<T> n;
			for (n = head; n.getNext() != null; n = n.getNext());
			n.setNext(new Node<T>(x, null));
		}
	}
	
	public T getFirst() {
		return head.getData();
	}
	
	public T get(int c) /* throws NoSuchElementException */ {
		int target = c; 		
		Node<T> n;
		for (n = head; n.getNext() != null && target-- > 0; n = n.getNext());
		if (target > 0) throw new NoSuchElementException("LinkedList.get: no element #" + c);
		return n.getData();
	}

	public int find(String s) /* throws NoSuchElementException */ {
		Node<T> n;
		int i;
		for (i = 0, n = head; n != null && n.getData().toString().compareTo(s) != 0; n = n.getNext(), i++);
		if (n == null) throw new NoSuchElementException("LinkedList.find: no element " + s);
		return i;
	}
	
	public void insertAfter(int c, T e) {
		int target = c;
		Node<T> n;
		for (n = head; n.getNext() != null && target-- > 0; n = n.getNext());
		if (target > 0) throw new NoSuchElementException("LinkedList.insertAfter: no element #" + c);
		n.setNext(new Node<T>(e, n.getNext()));
	}
	
	public void insertAfter(String s, T e) {
		Node<T> n;
		for (n = head; n != null && n.getData().toString().compareTo(s) != 0; n = n.getNext());
		if (n == null) throw new NoSuchElementException("LinkedList.insertAfter: no element " + s);
		n.setNext(new Node<T>(e, n.getNext()));
	}
	
	public void delete(int c) {
		int target = c;
		Node<T> n;
		if (c == 0) {
			head = head.getNext();
		} else {
			for (n = head; n.getNext() != null && target-- > 1; n = n.getNext());
			if (target > 1) throw new NoSuchElementException("LinkedList.delete: no element #" + c);
			n.setNext(n.getNext().getNext());
		}
	}
}
