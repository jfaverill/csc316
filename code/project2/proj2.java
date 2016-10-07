import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.LinkedList;

public class proj2 {

	// Node class
	private static class Node {
		// char variable for Node data
		public char data;
		// Node variable for the parent node
		public Node parent = null;
		// Collection of Nodes for any children of a node
		public List<Node> children = null;
		// binary variable to mark the Node in determining relationships 
		public int mark = 0;

		// Node constructor
		public Node(char data) {
			this.data = data;
			this.parent = null;
			this.children = new ArrayList<Node>();
			this.mark = 0;
		}
	}

	private static class Queue {
		private LinkedList<Node> q;

		public Queue() {
			q = new LinkedList<Node>();
		}

		public void enqueue(Node n) {
			q.add(n);
		}

		public Node dequeue() {
			Node n = q.get(0);
			q.remove(0);
			return n;
		}

		public boolean isEmpty() {
			return q.size() == 0;
		}
	}

	// global array of characters for pretraversal order
	public static char[] pretrav = new char[256];
	// global array of characters for posttraversal order
	public static char[] posttrav = new char[256];

	// Method to check to see whether or not array element exists
	public static boolean elementExists(char[] arr, int index) {
		try {
			char c = arr[index];
			return true;
		} catch (ArrayIndexOutOfBoundsException e) {
			return false;
		}
	}

	public static void levelOrderTraversal(Node root) {
		Queue queue = new Queue();
		queue.enqueue(root);
		while (!queue.isEmpty()) {
			Node q = queue.dequeue();
			System.out.print(q.data);
			for (int i = 0; i < q.children.size(); i++) {
				Node r = q.children.get(i);
				queue.enqueue(r);
			}
		}
	}

	// buildTree function to recursively build tree using
	// preorder and postorder traversals
	public static Node buildTree(int size, int prestart, int poststart) {
		// initialize root node, this will be returned once set
		Node root = null;
		// BASE CASE
		// return the node at position 1 if the size is 1
		if (size == 1) {
			root = new Node(pretrav[prestart]);
		} else {
			// create a new node at the prestart position
			root = new Node(pretrav[prestart]);
			// advance prestart by 1
			++prestart;
			
			while (elementExists(pretrav, prestart)) {
				size = 0;
				char nextChildChar = pretrav[prestart];
				// System.out.print(nextChildChar);
				int scanPostTrav = poststart;
				
				while (elementExists(posttrav, scanPostTrav) && nextChildChar != posttrav[scanPostTrav]) {
					++size;
					++scanPostTrav;
				}
				
				++size;
				Node nextChild = buildTree(size, prestart, poststart);
				root.children.add(nextChild);
				prestart += size;
				poststart += size;

				/*
				System.out.print(size);
				System.out.print(prestart);
				System.out.print(poststart);
				*/
			}
			// System.out.println(root.children.size());
			
			for (int i = 0; i < root.children.size(); i++) {
				Node child = root.children.get(i);
				child.parent = root;
			}

			// System.out.println();
			/**************************
			move pretrav up 1
			scan posttrav until i find corresponding value (get that position + 1)
			that gives me size of next chunk to process
			create the subtree for that child using size, pretrav and posttrav
			move pretrav and postrav up by size
			*determine where the subtree starts, and how many nodes the subtree has
			*27:25 has skip information
			****************************/
			// reset size to 0
			/*
			size = 0;
			// check next position after prestart to get position of leftmost child
			// and assign to leftChildPosition
			int leftChildPosition = prestart + 1;
			// initialize number of children for the leftmost child to 0
			int leftChildNumChildren = 0;
			// get leftmost child character of root from pretrav array at position leftChildPosition
			char leftChild = pretrav[leftChildPosition];
			// use scanPostTrav to find the equivalent character of leftChild in the posttrav array
			int scanPostTrav = poststart;
			while (leftChild != posttrav[scanPostTrav]) {
				++leftChildNumChildren;
				++scanPostTrav;
			}
			// now that we have the number of children of the leftmost child, we need
			// to add 1 to the number of its children to get the size of the tree to create 
			size = leftChildNumChildren + 1;
			// do while prestart position is less than the pretrav array length
			// DON'T THINK I NEED while (prestart < pretrav.length) {
				// create a new Node at the preorder traversal start position
				// root = new Node(pretrav[prestart]);
			//}
			*/
		}
		// return the root node
		return root;
	}

	// main program
	public static void main(String[] args) throws FileNotFoundException {
		// ArrayList of char arrays to hold queries
		List<char[]> queries = new ArrayList<char[]>();
		// Array to store each query
		char[] query = null;

		// Create console Scanner to read input and output file names
		Scanner console = new Scanner(System.in);

		// Prompt user for input file and output file
		// Save filenames to variables
		System.out.print("Enter name of input file: ");
		String inFileName = console.next();
		System.out.print("Enter name of output file: ");
		String outFileName = console.next();

		// Close console Scanner
		console.close();

		// Create Scanner to read input file line by line
		// Scanner input = new Scanner(System.in);
		Scanner input = new Scanner(new File(inFileName));	

		// Create PrintStream to write to output file
		PrintStream output = new PrintStream(new File(outFileName));

		// Read each input line and handle it
		while (input.hasNextLine()) {
			String readLine = input.nextLine();
			// Remove any periods, spaces or commas
			readLine = readLine.replace(",", "").replace(".", "").replace(" ", "");
			// Only do if input line has length of at least 1 char
			if (readLine.length() >= 1) {
				// Populate pretrav array
				if (readLine.substring(0,1).equals("<")) {
			 		pretrav = readLine.substring(1).toCharArray();
					// System.out.println(pretrav.length);
					for (int i = 0; i < pretrav.length; i++) {
						System.out.println(pretrav[i]);
					}
					System.out.println();
				// Populate posttrav array
				} else if (readLine.substring(0,1).equals(">")) {
			 		posttrav = readLine.substring(1).toCharArray();
					// System.out.println(posttrav.length);
					for (int i = 0; i < posttrav.length; i++) {
						System.out.println(posttrav[i]);
					}
					System.out.println();
				} else if (readLine.substring(0,1).equals("?")) {
					query = new char[2];
					query = readLine.substring(1).toCharArray();
					queries.add(query);
				}
			}
		}

		// End fetching in lines of the file. Ready to build tree

		// Get the initial size of the tree and set the initial 
		// prestart and poststart values
		int treeSize = pretrav.length;
		int prestart = 0;
		int poststart = 0;

		// Call buildTree and get the root of the entire tree
		// Node treeRoot = buildTree(treeSize, prestart, poststart);

		// Iterate through each query in the queries collection
		for (int i = 0; i < queries.size(); i++) {
			query = queries.get(i);
			for (int j = 0; j < query.length; j++) {
				System.out.println(query[j]);
			}
		}
		System.out.println();

		Node test = buildTree(treeSize, prestart, poststart);
		levelOrderTraversal(test);
		// Close input Scanner
		input.close();
		// Close output PrintStream
		output.close();
	}
}