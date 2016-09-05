import java.util.Scanner;

public class proj1 {

	private static class LinkList {
		public void printMessage() {
			System.out.println("In the list");
		}
	}

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		while (input.hasNextLine()) {
			System.out.println(input.next());
		}
		System.out.println("Hello World!");
		// LinkList.printMessage();
		LinkList l = new LinkList();
		l.printMessage();
	}
}

