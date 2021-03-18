package MaxHeap;
import java.lang.reflect.Array;
import java.util.Objects;

public class MaxHeap<T> {
	private int lastPosition;
	private T[] array;
	private int capacity;

	// the first parameter tells us the type of class it is ;
	public MaxHeap(Class<T> clazz, int capacity) {
		//use reflection API to create an array of type clazz
		this.array = (T[]) Array.newInstance(clazz, capacity);
		this.lastPosition = -1;
		this.capacity = capacity;
	}

	// what if some one passes an array capacity zero
	// add exception handling next
	public MaxHeap(int capacity) {
		this.array = (T[]) new Object[capacity];
		this.lastPosition = -1;
		this.capacity = capacity;
	}

	public MaxHeap() {
		this.array = (T[]) new Object[100];
		this.lastPosition = -1;
		this.capacity = 100;
	}

	public void add(T obj) {
		// double array size if capacity is hit
		if (this.lastPosition >= capacity - 1) {
			increaseArraySize();
		}
		this.array[++this.lastPosition] = (T)obj;
		trickleUp(this.lastPosition);

	}

	// swap if parent < child
	// continue until rule is violate or we hit the root
	private void trickleUp(int position) {
		// at root position
		if (position == 0) {
			return;
		}
		int parent = (int) Math.floor((position - 1) / 2);
		// swap if parent < child
		if (((Comparable<T>) this.array[position]).compareTo(this.array[parent]) > 0) {
			swap(position, parent);
			trickleUp(parent);
		}
	}

	// swap index elements from and to
	private void swap(int from, int to) {
		T temp = this.array[from];
		this.array[from] = this.array[to];
		this.array[to] = temp;
		return;
	}

	public T remove() {
		// remove the root
		T temp = array[0];
		// swap root and lastPosition and post decrement last position
		// we do this so we can implement heapsort
		swap(0, this.lastPosition--);
		trickleDown(0);

		return temp;
	}

	// swap if parent < children
	// if both children parent swap with the greater child
	private void trickleDown(int parent) {
		// get children indexs
		int left = 2 * parent + 1;
		int right = 2 * parent + 2;

		// first let is do the last level edge cases
		// parent has one child only and parent < left and we are at the last avaible
		// spot
		if (left == lastPosition && (((Comparable<T>) this.array[parent]).compareTo(array[left]) < 0)) {
			swap(parent, left);
			return;
		}

		// parent has two children and we are at the last level
		if (right == lastPosition) {
			// left > right and parent < left
			if ((((Comparable<T>) this.array[left]).compareTo(array[right]) > 0)
					&& (((Comparable<T>) this.array[parent]).compareTo(array[left]) < 0)) {
				swap(parent, left);
			}
			// right > left && parent < right
			else if (((Comparable<T>) this.array[parent]).compareTo(array[right]) < 0) {
				swap(parent, right);
			}

			return;
		}

		// left and/or right child DNE
		if (left >= lastPosition || right >= lastPosition) {
			return;
		}

		// now we do a actual trickledown we do this internally in a tree
		// we are guaranteed to have two children in our internal nodes/subtree
		// find if who is bigger left or right
		// left > right && parent < left
		if ((((Comparable<T>) this.array[left]).compareTo(array[right]) > 0)
				&& (((Comparable<T>) this.array[parent]).compareTo(array[left]) < 0)) {
			swap(parent, left);
			trickleDown(left);
		}
		// right > left && parent < right
		else if (((Comparable<T>) this.array[parent]).compareTo(array[right]) < 0) {
			swap(parent, right);
			trickleDown(right);
		}
	}

	// we sort our heap using heapSort
	public void heapSort() {
		while(this.lastPosition >= 0) {
			this.remove();
		}
	}

	// double array size using a shallow copy
	private void increaseArraySize() {
		T[] newArray = (T[]) new Object[capacity * 2];
		System.arraycopy(this.array, 0, newArray, 0, this.array.length); // shallow cope
		this.array = newArray;
		this.capacity = this.capacity * 2;

	}

	public static void main(String[] args) {
		//MaxHeap heap = new MaxHeap<>(Integer.class, 20);
		MaxHeap<Integer> heap = new MaxHeap<Integer>(3);
		heap.add(new Integer(12));
		heap.add(new Integer(11));
		heap.add(new Integer(10));
		heap.add(new Integer(27));
		heap.add(new Integer(19));
		heap.heapSort();
		

		Object[] arr = new Object[10];
//		arr[0] = new Integer(12);
//		arr[1] = new Double(12.5);
//		System.out.println(arr[0]);
//		System.out.println(arr[1]);


	}

}
