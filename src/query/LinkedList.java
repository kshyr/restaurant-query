package query;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

public class LinkedList<E> implements Collection<E> {

	public Node<E> head = null;
	private int size = 0;
	
	public static class Node<E> {

		E data;
		Node<E> next;

		public Node(E data) {
			this.data = data;
			this.next = null;
		}
		
		public void setData(E newData) {
		    this.data = newData;
		}
		
		public void setNext(Node<E> nextNode) {
		    this.next = nextNode;
		}
		
		public E getData() {
			return this.data;
		}
		
		public Node<E> getNext() {
		    return this.next;
		}
	}
	

	
	E get(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("Invalid index");
		} 
		Node<E> curr = head;
		for (int i = 0; i < index; i++) { 
			curr = curr.next;
		}
		return curr.getData();
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public boolean contains(Object o) {
		
		return false;
	}

	@Override
	public Iterator<E> iterator() {
		
		return null;
	}

	@Override
	public Object[] toArray() {
		
		return null;
	}

	@Override
	public <T> T[] toArray(T[] a) {
		
		return null;
	}

	@Override
	public boolean add(E e) {
		Node<E> curr = head;
	    if (head != null) {
	        while(curr.getNext()!=null){
	            curr = curr.getNext();
	        }
		Node<E> node = new Node<E>(e); 
		node.setData(e);
		curr.setNext(node);
	    }
	    else {
	    	head = new Node<E>(e);
	    }
	    size++;
	    return false;
	}

	@Override
	public boolean remove(Object o) {
		Node<E> curr = head;
		if (head != null) {
	        while(curr.getNext()!=null){
	        	if (curr.getData() != o) {
	        		curr = curr.getNext();
	        	}
	        	else {
	        		curr = null;
	        	}
	        }
		}
		return false;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		
		return false;
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		
		return false;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		
		return false;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		
		return false;
	}

	@Override
	public void clear() {
		
	}
	
	public void sort ( ) {
		Object [] array = toArray();
		Arrays.sort(array);
		this.clear();
		for (Object o : array ) {
		this.add( (E)o );
		}
	}
	
	public String toString() {
        String str = "[";
        Node<E> curr = head;
        str += curr.getData();
        while(curr.getNext() != null){
            if(curr.getNext() != null){
                 str += ", ";
            }
            curr = curr.getNext();
            str += curr.getData();
        }
        return str + "]";
	}
}