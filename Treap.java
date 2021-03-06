//Name:  CWID:
package Treap;
import java.util.Stack;
import java.util.Random;
/**
 * 
 * @author URmyArtemis
 *
 * @param <E>
 */
public class Treap<E extends Comparable<E>> {
	/**
	 *  declare inner class
	 * @author URmyArtemis
	 *
	 * @param <E>
	 */
	private static class Node<E extends Comparable<E>> {
		/**
		 * Data fields
		 */
		public E data; // key for the search
		public int priority; // random heap priority
		public Node <E> left;
		public Node <E> right;
		/**
		 * Constructors
		 * @param data
		 * @param priority
		 * @throws Exception 
		 */
		public Node(E data, int priority) {
			if (data == null) {
				throw new IllegalArgumentException("NULL Data!");
			}else{
				this.data = data;
				this.priority = priority;
				this.left = null;
				this.right = null;
			}
		}
		/**
		  * Methods
		  */
		/**
		 * rotateRight() performs a right rotation
		 * @return a reference to the root of the result. 
		 */
		Node<E> rotateRight() {
			Node<E> temp = this;
			if (this.left == null) {
				return temp;
			}else{
				Node<E> L = temp.left;
				temp.left = L.right;
				L.right = temp;
				return L;
			}		
		}
		/**
		 * rotateLeft() performs a left rotation
		 * @return a reference to the root of the result. 
		 */
		Node<E> rotateLeft() {
			Node<E> temp = this;
			if (this.right == null) {
				return temp;
			}else{
				Node<E> R = temp.right;
				temp.right = R.left;
				R.left = temp;
				return R;
			}
		}
		public String toString() {
			return "(key=" + data.toString() + ", priority=" + priority + ")";
		}
	}
	private Random priorityGenerator;
	private Node<E> root;
	/**
	 * Constructors: Treap() creates an empty treap. 
	 */
	public Treap(){
		priorityGenerator = new Random();
		//root = null;
	}
	public Treap(long seed){
		this.priorityGenerator = new Random(seed);
	}
	/**
	 * Add operation
	 * @param key
	 * @return returns true, if a node with the key was successfully added to the treap. 
	 * If there is already a node containing the given key, the method returns false and does not modify the treap.
	 */
	public boolean add(E key) {
		return add(key, priorityGenerator.nextInt());
	}
	/**
	 * To insert the given element into the tree, 
	 * create a new node containing key as its data and a random priority generated by priorityGenerator. 
	 * @param key
	 * @param priority
	 * @return returns true, if a node with the key was successfully added to the treap. 
	 * If there is already a node containing the given key, the method returns false and does not modify the treap.
	 */
	public boolean add(E key, int priority) {
		if (root == null) {
			Node<E> newRoot = new Node<E>(key, priority);
			root = newRoot;
			return true;
		}else{
			Stack<Node<E>> stack = new Stack<Node<E>>();
			Node<E> temp = root;
			while (temp != null) {
				int comparison = temp.data.compareTo(key);
				if (comparison == 0) {
					return false;
				}else{
					if (comparison < 0) {
						stack.push(temp);
						if (temp.right == null) {
							temp.right = new Node<E>(key, priority);
							reheap(temp.right, stack);
							return true;
						}else{
							temp = temp.right;
						}
					}else{
						stack.push(temp);
						if (temp.left == null) {
							temp.left = new Node<E>(key, priority);
							reheap(temp.left, stack);
							return true;
						}else{
							temp = temp.left;
						}
					}
				}
			}
			return false;
		}
	}
	/**
	 * a helper function reheap (with appropriate parameters that should include the stack) 
	 * to restore the heap invariant.
	 * @param temp
	 * @param tool
	 */
	public void reheap(Node<E> temp, Stack<Node<E>> tool) {
		while (!tool.isEmpty()) {
			Node<E> parent = tool.pop();
			if (parent.priority < temp.priority) {
				if (parent.data.compareTo(temp.data) > 0) {
					temp = parent.rotateRight();
				}else{
					temp = parent.rotateLeft();
				}
				if (!tool.isEmpty()) {
					if (tool.peek().left == parent) {
						tool.peek().left = temp;
					}else{
						tool.peek().right = temp;
					}
				}else{
					this.root = temp;
				}
			}else{
				break;
			}
		}
	}
	/**
	 * boolean delete(E key) deletes the node with the given key from the treap and returns true. 
	 * If the key was not found, the method does not modify the treap and returns false.
	 * @param key
	 * @return true, if deleted properly; false, if not found.
	 */
	public boolean delete(E key) {
		if (root == null || find(key) == false) {
			return false;
		} else {
			root = delete(root, key);
			return true;
		}
	}
	/**
	 * deletes the node with the given key from the treap
	 * @param root
	 * @param key
	 * @return true, if deleted properly; false, if not found.
	 */
	private Node<E> delete(Node<E> root, E key) {
		if (root == null) {
			return root;
		} else {
			if (root.data.compareTo(key) < 0) {
				root.right = delete(root.right, key);
			} else {
				if (root.data.compareTo(key) > 0) {
					root.left = delete(root.left, key);
				} else {
					if (root.right == null) {
						root = root.left;
					} else if (root.left == null) {
						root = root.right;
					} else {
						if (root.right.priority < root.right.priority) {
							root = root.rotateRight();
							root.right = delete(root.right, key);
						} else {
							root = root.rotateLeft();
							root.left = delete(root.left, key);
						}
					}
				}
			}
		}
		return root;
	}
	/**
	 * Finds a node with the given key in the treap rooted at root and returns true if it finds it and false otherwise.
	 * @param root
	 * @param key
	 * @return true, if found; false if not.
	 */
	private boolean find(Node<E> root, E key) {
		if (root == null) {
			return false;
		} else {
			int compResult = key.compareTo(root.data);
			if (compResult == 0) {
				return true;
			} else if (compResult < 0) {
				return find(root.left, key);
			} else {
				return find(root.right, key);
			}
		}
	}	
	/**
	 * Finds a node with the given key in the treap and returns true if it finds it and false otherwise.
	 * @param key
	 * @return true, if found; false, if not.
	 */
	public boolean find(E key){
		if (find(root, key) != false) {
			return find(root, key);
		} else {
			return false;
		}
	}
	/**
	 * Carries out a preorder traversal of the tree and returns a represen- tation of the nodes as a string.
	 * This is a OVERRIDE
	 */
	public String toString(){
		StringBuilder sb = new StringBuilder();
		preOrderTraverse(root, 1, sb);
		return sb.toString();
	}
	private void preOrderTraverse(Node<E> node, int depth, StringBuilder sb) {
		for (int i = 1; i < depth; i++) {
			sb.append("  ");
		}
		if (node == null) {
			sb.append("null\n");
			return;
		} else {
			sb.append(node.toString());
			sb.append("\n");
			preOrderTraverse(node.left, depth + 1, sb);
			preOrderTraverse(node.right, depth + 1, sb);
		}
	}
}
	
