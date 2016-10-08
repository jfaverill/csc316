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

	// ChildNodeDetails class
	// captures the details for a particular child node of the root of a tree/subtree
	// to be used in the buildTree method
	private static class ChildNodeDetails {
		public char data;
		public int size;
		public int prestartPosition;
		public int poststartPosition;
	}

	// Queue class
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

	// Method to print the nodes in level order traversal of a tree
	public static void levelOrderTraversal(Node root) {
		// create a new queue
		Queue queue = new Queue();

		// initialize an empty string to hold the tree characters to print
		String treeNodes = "";
		// perform the level order traversal algorithm
		// add the first node to the queue
		queue.enqueue(root);
		// continue while the queue has data
		while (!queue.isEmpty()) {
			Node q = queue.dequeue();
			// here is our "visit" that is performed in the level order traversal
			treeNodes += q.data + ", ";
			// enqueue the children of the current node
			for (int i = 0; i < q.children.size(); i++) {
				Node r = q.children.get(i);
				queue.enqueue(r);
			}
		}
		// remove the last comma and space from the treeNodes string and add a period
		treeNodes = treeNodes.substring(0, treeNodes.length() - 2) + ".";
		// print the treeNodes string
		System.out.println(treeNodes);
	}

	// buildTree function to recursively build tree using
	// preorder and postorder traversals
	public static Node buildTree(int size, int prestart, int poststart) {
		// BASE CASE
		// return the node at current prestart position of pretrav array if the size is 1
		if (size == 1) {
			return new Node(pretrav[prestart]);
		} else {
			// create a new node at the prestart position
			Node root = new Node(pretrav[prestart]);

			// advance prestart by 1 in order to set the pretrav position of root's first child
			++prestart;

			// create an empty list of ChildNodeDetails that will store the details
			// of each child found under the root node
			// ChildNodeDetails has the following fields:
			// 		-- data (character value for the child node)
			//		-- size (the size of the nodes that make up the child's subtree)
			//		-- prestartPosition (the position within the pretrav array that the child's subtree starts)
			//		-- poststartPosition (the position within the postrav array that the child's subtree starts)
			LinkedList<ChildNodeDetails> rootChildrenDetails = new LinkedList<ChildNodeDetails>();
			
			// while the pretrav array still has elements found at the current prestart position...
			while (elementExists(pretrav, prestart)) {
				// reset size
				size = 0;

				// get the character value of the next child from the pretrav array at prestart position
				char nextChildChar = pretrav[prestart];

				// initialize current poststart value to variable that will scan the posttrav array
				int scanPostTrav = poststart;
				
				// As long as the posttrav array has elements within, scan posttrav 
				// until it finds the match of the character currently set in the 
				// nextChildChar variable
				// increase the size by 1 for each pass through the loop
				while (elementExists(posttrav, scanPostTrav) && nextChildChar != posttrav[scanPostTrav]) {
					++size;
					++scanPostTrav;
				}
				// increment size by 1 more, because size needs to include the matching character
				// of the child node in the that was found in the pretrav array
				++size;

				// create a ChildNodeDetails object and fill out the details of the child node
				// these will be used in the recursive calls to the buildTree method for 
				// each of root's child nodes
				ChildNodeDetails childNodeDetails = new ChildNodeDetails();
				childNodeDetails.data = nextChildChar;
				childNodeDetails.size = size;
				childNodeDetails.prestartPosition = prestart;
				childNodeDetails.poststartPosition = poststart;
				rootChildrenDetails.add(childNodeDetails);

				// advance prestart and poststart positions to skip past the nodes that are not
				// children of root
				prestart += size;
				poststart += size;
			}
			
			// for each of root's children recursively call buildTree using the details
			// of each corresponding child node
			// return the node to a nextChild variable and add that node to the children
			// of root
			for (int i = 0; i < rootChildrenDetails.size(); i++) {
				ChildNodeDetails details = rootChildrenDetails.get(i);
				Node nextChild = buildTree(details.size, details.prestartPosition, details.poststartPosition);
				root.children.add(nextChild);
			}
			
			// now that we have attached root's children, visit each child and set it's parent
			// node to root
			for (int i = 0; i < root.children.size(); i++) {
				Node child = root.children.get(i);
				child.parent = root;
			}
			// return the root node
			return root;
		}
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
				// Populate posttrav array
				} else if (readLine.substring(0,1).equals(">")) {
			 		posttrav = readLine.substring(1).toCharArray();
				} else if (readLine.substring(0,1).equals("?")) {
				// create each query and add to the list of queries
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
		Node treeRoot = buildTree(treeSize, prestart, poststart);

		// STILL TODO - JUST MADE SURE COULD FETCH QUERIES PROPERLY
		// Iterate through each query in the queries collection
		/*
		for (int i = 0; i < queries.size(); i++) {
			query = queries.get(i);
			for (int j = 0; j < query.length; j++) {
				System.out.println(query[j]);
			}
		}
		System.out.println();
		*/

		// Call levelOrderTraversal method starting with treeRoot
		// to print the levelOrderTraversal of the tree
		levelOrderTraversal(treeRoot);

		// Close input Scanner
		input.close();
		// Close output PrintStream
		output.close();
	}
}