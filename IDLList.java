//Name:  CWID:
//Homework3
import java.util.ArrayList;
/**
 * 
 * @author URmyArtemis
 *
 * @param <E>
 */
public class IDLList<E> {
	/**
	 *  declare inner class
	 * @author URmyArtemis
	 *
	 * @param <E>
	 */
	private static class Node<E> {
		private E data;
		private Node<E> next;
		private Node<E> prev;
		/**
		 *  a constructor that creates a node holding elem
		 * @param elem
		 */
		private Node(E elem) {
			this.data = elem;
		}
		/** a constructor that creates a node holding elem, with next as next and prev as prev.
		 * 
		 * @param elem
		 * @param prev
		 * @param next
		 */
		private Node(E elem, Node<E> prev, Node<E> next) {
			this.data = elem;
			this.prev = prev;
			this.next = next;
		}
	}
	
	//The class IDLList<E> should include the declaration of this inner private class Node<E>.
	//Apart from that, it should have four data fields:
	private Node<E> head;
	private Node<E> tail;
	private int size;
	private ArrayList<Node<E>> indices;

	
	//creates an empty double-linked list.
	public IDLList() {
		this.head = null;
		this.tail = null;
		this.size = 0;
		this.indices = new ArrayList<>();
	}

	/**
	 *  adds elem at position index (counting from wherever head is). It uses the index for fast access.
	 * @param index
	 * @param elem
	 * @return
	 */
	public boolean add(int index, E elem) {
		Node<E> node = new Node<E>(elem);
		if (index < 0 || index > size) {
			return false;
		}else{
			if(index == 0) {
				add(elem);
				return true;
			}else{
				if(index == size) {
					append(elem);
					return true;
				}else{
					/*
					  get the index position elements in ArrayList.
					 */
//					Node<E> node = indices.get(index);
//					indices.add(index, node);
					indices.get(index - 1).next = node;
					node.prev = indices.get(index - 1);
					node.next = indices.get(index);
					indices.get(index).prev = node;
				}
			}
		}
		size++;
		indices.add(index, node);
		return true;
	}

	/**
	 *  adds elem at the head (i.e. it becomes the first element of the list).
	 * @param elem
	 * @return
	 */
	public boolean add(E elem) {
		Node<E> node = new Node<E>(elem);
		if (size == 0) {
			head = node;
			tail = node;
		}else{
			head = node;
			node.next = indices.get(0);
			indices.get(0).prev = head;
		}
		size++;
		indices.add(0, node);
		return true;
	}

	/**
	 *  adds elem as the new last element of the list (i.e. at the tail).
	 * @param elem
	 * @return
	 */
	public boolean append(E elem) {
		Node<E> node = new Node<E>(elem);
		indices.add(size, node);
		tail = node;
		node.prev = indices.get(size - 1);
		indices.get(size - 1).next = node;
		size++;
		return true;
	}

	/**
	 *  returns the object at position index from the head.
	 *  It uses the index for fast access. Indexing starts from 0,
	 *  thus get(0) returns the head element of the list.
	 */
	public E get(int index) {
		if (index < 0 || index >= size)
			return null;
		return indices.get(index).data;
	}

	/**
	 *  returns the object at the head.
	 * @return
	 */
	public E getHead() {
		if (head == null)
			return null;
		return head.data;
	}

	/**
	 *  returns the object at the tail.
	 * @return
	 */
	public E getLast() {
		if (tail == null)
			return null;
		return tail.data;
	}

	/**
	 *  returns the list size.
	 * @return
	 */
	public int size() {
		return indices.size();
	}

	/**
	 *  removes and returns the element at the head.
	 * @return
	 */
	public E remove() {
		if (head == null) return null;
		if (size == 1) {
			E temp = head.data;
			indices.remove(0);
			head = null;
			tail = null;
			size--;
			return temp;
		}else{
			head = head.next;
			head.prev = null;
		}
		size--;
		return indices.remove(0).data;
		
		/*Node<E> temp = head; head = head.next; head.prev = null; size--;
		indices.remove(temp); return temp.data;*/
	}

	/**
	 *  removes and returns the element at the tail.
	 * @return
	 */
	public E removeLast() {
		if (size == 0)
			return null;
		if (size == 1) {
			E temp = tail.data;
			indices.remove(size - 1);
			tail = null;
			head = null;
			size--;
			return temp;
			}else{
				tail = tail.prev;
				tail.next = null;
				}
		size--;
		return indices.remove(size).data;
		}

	/**
	 *  removes and returns the element at the index "index". Use the index for fast access.
	 * @param index
	 * @return
	 */
	public E removeAt(int index){
    	if(index < 0 || index >= size) {
    		return null;
    	}else{
    		if(index == 0){
/*    			if(size == 0) {
    				return null;
    			}else{
    				if(size == 1){
    					E temp = indices.get(index).data;
    					indices.remove(0);
    					head = null;
    					tail = null;
    					size--;
    					return temp;
    				}else{
    					head = head.next;
    					head.prev = null;
    					//indices.remove(index);
    					//return indices.remove(0).data;
    				}
    			}
    			size--;
    			return indices.remove(index).data;
*/			return remove();
    		}else{
    			if(index == size - 1){
/*   				if(size == 0) {
    					return null;
    				}else{
    					if(size == 1){
    						E temp = tail.data;
    						indices.remove(0);
    						tail = null;
    						head = null;
    						size--;
    						return temp;
    					}else{
    						tail = tail.prev;
    						tail.next = null;
    					}
    				}
    				size--;
    				return indices.remove(index).data;
*/
    			return removeLast();
    			}else{
    				//index > 0 && index < this.size - 1
    				indices.get(index).prev.next = indices.get(index).next;
    				indices.get(index).next.prev = indices.get(index).prev;
    				size--;
    				return indices.remove(index).data;
    			}
    		}
    	}
    }

	/**
	 *  removes the first occurrence of elem in the list and returns true.
	 *  Return false if elem was not in the list.
	 * @param elem
	 * @return
	 */
	public boolean remove(E elem) {
		for (int i = 0; i < indices.size(); i++) {
			if (indices.get(i).data.equals(elem)) {
				removeAt(i);
				return true;
			}
		}
		return false;
	}

	/**
	 *  presents a string representation of the list.
	 */
	public String toString() {
//		Node<E> pointer = head;
		String result = "";
		for(int i = 0; i < size; i++){
			result = result + indices.get(i).data;
		}
//		while (pointer != null) {
//			result = result + pointer.data + " ";
//			pointer = pointer.next;
//		}
		return result;
	}

}
