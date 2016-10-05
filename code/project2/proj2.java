import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

public class proj2 {

	// Node class
	private static class Node {
		// char variable for Node data
		public char data;
		// Node variable for the parent node
		public Node parent = null;
		// Collection of Nodes for any children of the node
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

	// global array of characters for pretraversal order
	public static char[] pretrav = new char[256];
	// global array of characters for posttraversal order
	public static char[] posttrav = new char[256];

	// buildTree function to recursively build tree using
	// preorder and postorder traversals
	public static Node buildTree(int size, int prestart, int poststart) {
		// base case
		if (size == 1) {
			Node leaf = new Node(pretrav[0]);
			return leaf;
		}
		return null;
	}

	// main program
	public static void main(String[] args) throws FileNotFoundException {

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
				// Generate pretrav array
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
				}
			}
		}

		// Close input Scanner
		input.close();
		// Close output PrintStream
		output.close();
	}
}