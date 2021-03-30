package BinarySearchTree;

public class BST<E> {
	private Node<E> root;
	
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


	//return the deleted node
	public Node<E> delete(E obj) {
		
		//find the node we want to deleted
		Node<E> delNode = findNode(this.root, obj);
		//find the parent of the del node
		Node<E> parentNode = findParent(this.root, delNode);
		
		if(isLeaf(delNode)) {	//Case 1: delNode is a leaf node
			deleteLeafNode(delNode, parentNode);
		}else if(hasOnlyLeftChild(delNode)) {	//case 2a: has only left child 
			deleteNodeOnlyLeftChild(delNode, parentNode);
		}else if(hasOnlyRightChild(delNode)) { //case 2b: has only right child
			deleteNodeOnlyRightChild(delNode, parentNode);

		}else if(hasTwoChildren(delNode)) { //case 3: has two children
			deleteNodeWithTwoChildren(delNode, parentNode);
		}
		return delNode;
	}

	//Auxiliary functions for deletion
	private void deleteLeafNode(Node<E> delNode, Node<E> parent) {
		//we are deleting a tree with one node in it
		if(parent == null) {
			this.root = null;
		}else if(((Comparable<E>)delNode).compareTo(parent.getData()) > 0) { //delnode is the parents right child
			parent.setRight(null);
		}else {
			parent.setLeft(null); //delNode is the parents left child
		}
		
	}
	
	//del node has only one left child
	private void deleteNodeOnlyLeftChild(Node<E> delNode, Node<E> parent) {
		//we are deleting a root
		if(parent == null) {
			this.root = delNode.getLeft();
		}else if(((Comparable<E>)delNode).compareTo(parent.getData()) > 0) { //delnode is the parents right child
			parent.setRight(delNode.getLeft());
		}else {
			parent.setLeft(delNode.getLeft()); //delNode is the parents left child
		}
		
	}
	
	//del node has only one child right
	private void deleteNodeOnlyRightChild(Node<E> delNode, Node<E> parent) {
		//we are deleting a root
		if(parent == null) {
			this.root = delNode.getRight();
		}else if(((Comparable<E>)delNode).compareTo(parent.getData()) > 0) { //delnode is the parents right child
			parent.setRight(delNode.getRight());
		}else {
			parent.setLeft(delNode.getRight()); //delNode is the parents left child
		}
	}
	
	//deleting a node with two children
	private void deleteNodeWithTwoChildren(Node<E> delNode, Node<E> parent) {
		//get the min val node in the right subtree of delNode
		Node<E> newDelNode = this.minValNode(delNode.getRight());
		E newVal = newDelNode.getData();
		//delete this maxValNode
		this.delete(newDelNode.getData());
		//transfer over val
		delNode.setData(newVal);
		
	}
	//find a node with E obj as its data in the given tree
	private Node<E> findNode(Node<E> current, E obj){
		if(current != null) {
			if(((Comparable<E>)obj).compareTo(current.getData()) == 0)
				return current;
			
			//go right
			if(((Comparable<E>) obj).compareTo(current.getData()) > 0)
				return findNode(current.getRight(), obj);
			else // obj <= current.data move left
				return findNode(current.getLeft(), obj);
			
		}
		return null; //node not found
	}
	
	//find the parent node of the given child node
	private Node<E> findParent(Node<E> current, Node<E> child){
		//child is root node, no parent
		if(child == this.root)
			return null;
		
		if(current != null) {
			if(current.getLeft() == child || current.getRight() == child)
				return current;
			
			//go right
			if(((Comparable<E>) child.getData()).compareTo(current.getData()) > 0)
				return findParent(current.getRight(), child);
			else // obj <= current.data move left
				return findParent(current.getLeft(), child);
			
		}
		return null; //parent node not found
	}
	
	//find the min value from current on down
	//use this to find the min value for the right subtree when deleting a node with two children
	private Node<E> minValNode(Node<E> current) {
		if(current.getLeft() == null)
			return current;
		else
			return minValNode(current.getLeft());
	}
	
	//find the max value from current on down
	//use this to find the max value for the left subtree when deleting a node with two children
	private Node<E> maxValNode(Node<E> current){
		if(current.getRight() == null)
			return current;
		else 
			return maxValNode(current.getRight());
	}
	
	
	private boolean isLeaf(Node<E> current) {
		return current.getLeft() == null && current.getRight() == null;
	}
	
	private boolean hasOnlyRightChild(Node<E> current) {
		return (current.getLeft() == null && current.getRight() != null);
	}
	
	private boolean hasOnlyLeftChild(Node<E> current) {
		return (current.getLeft() != null && current.getRight() == null);
	}
	
	private boolean hasTwoChildren(Node<E> current) {
		return current.getLeft() != null && current.getRight() != null;
	}
	
	
}
