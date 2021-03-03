package BinarySearchTree;

public class BST<E> {
	Node<E> root;
	
	public BST() {
		this.root = null;
	}
	
	public static void main(String[] args) {
		BST<Integer> bst = new BST<Integer>();
		bst.add(10);
		bst.add(12);
		bst.add(9);
		System.out.println(bst.contains(10));
		bst.preOrder();

	}
	
	//inorder wrapper
	public void inOrder() {
		this.inOrder(this.root);
	}
	
	private void inOrder(Node<E> current) {
		if(current != null) {
			inOrder(current.getLeft());
			System.out.println(current.toString());
			inOrder(current.getRight());
		}
		
	}
	
	//preOrder wrapper
	public void preOrder() {
		this.preOrder(this.root);
	}
		
	private void preOrder(Node<E> current) {
		if(current != null) {
			System.out.println(current.toString());
			preOrder(current.getLeft());
			preOrder(current.getRight());
		}
			
	}
	
	//postOrder wrapper
	public void postOrder() {
		this.postOrder(this.root);
	}
			
	private void postOrder(Node<E> current) {
		if(current != null) {
			postOrder(current.getLeft());
			postOrder(current.getRight());
			System.out.println(current.toString());

		}
				
	}
	
	
	
	//add wrapper
	public void add(E obj) {
		if(root == null)
			this.root = new Node<E>(obj);
		else
			this.root = this.add(this.root, obj);
	}
	
	//add E to the BST
	private Node<E> add(Node<E> current, E obj){
		//reached a leaf 
		if(current == null)
			return new Node<E>(obj);
		
		//obj > current.data move right
		if(((Comparable<E>) obj).compareTo(current.getData()) > 0)
			current.setRight(add(current.getRight(), obj));
		else // obj <= current.data move left
			current.setLeft(add(current.getLeft(), obj));
		
		return current;
		
	}
	
	//contains wrapper
	public boolean contains(E obj) {
		return this.contains(this.root, obj);
	}
	
	private boolean contains(Node<E> current, E obj) {
		//reach a leaf, obj not in tree
		if(current == null)
			return false;
		
		//found the obj
		if(((Comparable<E>) obj).compareTo(current.getData()) == 0)
			return true;
		
		//go right
		if(((Comparable<E>) obj).compareTo(current.getData()) > 0)
			return contains(current.getRight(), obj);
		else // obj <= current.data move left
			return contains(current.getLeft(), obj);
	}
	
	
	

}
