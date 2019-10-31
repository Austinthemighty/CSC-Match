public class Node<T> {
	private T data;
	private Node<T> next;
	
	public Node (T d, Node<T> n) {
		data = d;
		next = n;
	}
	
	public Node<T> getNext() {
		return next;
	}
	
	public T getData() {
		return data;
	}
	
	public void setNext(Node<T> n) {
		next = n;
	}
}