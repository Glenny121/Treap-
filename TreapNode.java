import java.util.Random;

public class TreapNode {
	int data;
	int priority;
	TreapNode left, right;
	TreapNode (int data){
		this.data = data;
		this.priority = new Random().nextInt(100);
		this.left = this.right = null;
	}
	public static TreapNode  rotateLeft(TreapNode  root){
		TreapNode  R = root.right;
		TreapNode  X = root.right.left;

		R.left = root;
		root.right = X;

		return R;
	}

	public static TreapNode  rotateRight(TreapNode root){
		TreapNode  L = root.left;
		TreapNode  Y = root.left.right;

		L.right = root;
		root.left = Y;

		return L;
	}

	public static TreapNode  insertNode(TreapNode  root, int data){

		if (root == null) {
			return new TreapNode (data);
		}
		if (data < root.data){
			root.left = insertNode(root.left, data);

			if (root.left != null && root.left.priority > root.priority) {
				root = rotateRight(root);
			}
		}
		else {
			root.right = insertNode(root.right, data);

			if (root.right != null && root.right.priority > root.priority) {
				root = rotateLeft(root);
			}
		}

		return root;
	}

	public static boolean searchNode(TreapNode  root, int key){
		if (root == null) {
			return false;
		}
		if (root.data == key) {
			return true;
		}

		if (key < root.data) {
			return searchNode(root.left, key);
		}
		return searchNode(root.right, key);
	}
	
	public static TreapNode  deleteNode(TreapNode  root, int key){
		if (root == null) {
			return null;
		}

		if (key < root.data) {
			root.left = deleteNode(root.left, key);
		}
		else if (key > root.data) {
			root.right = deleteNode(root.right, key);
		}

		else {
			if (root.left == null && root.right == null){
				root = null;
			}

			else if (root.left != null && root.right != null)
			{
				if (root.left.priority < root.right.priority){
					root = rotateLeft(root);
					root.left = deleteNode(root.left, key);
				}
				else {
					root = rotateRight(root);
					root.right = deleteNode(root.right, key);
				}
			}

			else {
				TreapNode  child = (root.left != null)? root.left: root.right;
				root = child;
			}
		}
		return root;
	}

	public static void printTreap(TreapNode  root, int space)
	{
		final int height = 10;
		if (root == null) {
			return;
		}
		space += height;

		printTreap(root.right, space);
		System.lineSeparator();

		for (int i = height; i < space; i++) {
			System.out.print(' ');
		}

		System.out.println(root.data + "(" + root.priority + ")");
		System.lineSeparator();
		printTreap(root.left, space);
	}
	public static void main(String[] args){
	
		int[] keys = { 5, 2, 3, 4, 9, 8, 10 };
		TreapNode  root = null;
		for (int key: keys) {
			root = insertNode(root, key);
		}

		System.out.println("Imprimir Treap:\n\n");
		printTreap(root, 0);

		System.out.println("\nEliminar nodo 3:\n\n");
		root = deleteNode(root, 3);
		printTreap(root, 0);

	}
}

