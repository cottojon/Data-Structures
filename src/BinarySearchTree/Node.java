package BinarySearchTree;

public class Node<E>  {
	private E data;
	private Node<E> left;
	private Node<E> right;
	
	
	public Node(E data) {
		this.data = data;
		this.left = null;
		this.right = null;
	}


	public E getData() {
		return data;
	}


	public void setData(E data) {
		this.data = data;
	}


	public Node<E> getLeft() {
		return left;
	}


	public void setLeft(Node<E> left) {
		this.left = left;
	}


	public Node<E> getRight() {
		return right;
	}


	public void setRight(Node<E> right) {
		this.right = right;
	}


	@Override
	public String toString() {
		return "Node [data=" + data + ", left=" + left + ", right=" + right + "]";
	}



	
	
}
