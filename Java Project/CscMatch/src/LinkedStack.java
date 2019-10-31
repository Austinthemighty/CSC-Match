/**
 * @author Austin Chopra
 * Date: Oct 8, 2019
 * Course and Section Number:  CSC205AB, 12189
 * Program Name:  LinkeStack.java
 * Program Description:  
 *
 */

public class LinkedStack<T> implements StackADT<T> {
	private List<T> list;
	

	public LinkedStack() {
		
		list = new List<T>();
	}

	
	
	@Override
	public void push(T element) {
		list.addLast(element);
	}

	@Override
	public T pop() {
		T temp = list.get(size()-1);
		list.delete(size()-1);
		return temp;
		

	}

	@Override
	public T peek() {
		return list.get(size()-1);
		
	}

	@Override
	public boolean isEmpty() {
		return size() == 0;
	}

	@Override
	public int size() {
		return list.length();
		
	}

}
