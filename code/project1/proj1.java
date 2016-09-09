import java.util.Scanner;
import java.io.IOException;

public class proj1 {

	private static class LinkedList<E> {

		private static class Node<E> {
			private E element;
			private Node<E> next;

			public Node (E element, Node<E> next) {
				this.element = element;
				this.next = next;
			}

			public E getElement() {
				return this.element;
			}

			public Node<E> getNext() {
				return this.next;
			}

			public void setNext(Node<E> next) {
				this.next = next;
			}
		}

		private Node<E> head = null;
		private int size = 0;

		public int size() {
			return this.size;
		}

		public E lookUp(int position) {
			Node<E> p = head;
			int k = 0;
			while (p != null && k < position) {
				p = p.getNext();
				k++;
			}
			if (p == null) {
				return null;
			}
			return p.getElement();
		} 

		public void addFirst(E toAdd) {
			head = new Node<E>(toAdd, head);
			size++;
		}

		public int positionInList(E element) {
			int positionToReturn = -1;
			Node<E> p = head;
			int k = 0;
			while (p != null && !(p.getElement().equals(element))) {
				p = p.getNext();
				k++;
			}
			if (p != null && p.getElement().equals(element)) {
				positionToReturn = k;
			}
			return positionToReturn;
		}

		public boolean isEmpty() {
			return size == 0;
		}

		public E removeFirst() {
			E removedElement = null;
			if (head == null) {
				removedElement = null;
			} else {
				removedElement = head.getElement();
				head = head.getNext();
				size--;
			}
			return removedElement;
		}

		public E removeAtPosition(int position) {
			// if list is empty then return null
			if (isEmpty()) {
				return null;
			}

			// if position requested is outside of list range then return null
			if (position < 0 || position >= size) {
				return null;
			}

			E removedElement = null;
			// if requested element is first element, then remove and
			// return the head element
			// and advance the head to the next element
			if (position == 0) {
				removedElement = head.getElement();
				head = head.getNext();
				size--;
			} else {
				Node<E> current = head;
				Node<E> previous = null;
				int k = 0;
				
				while (current.getNext() != null && k < position) {
					previous = current;
					current = current.getNext();
					k++;
				}

				removedElement = current.getElement();
				previous.setNext(current.getNext());
				size--;
			}

			return removedElement;
		}
	}

	public static void main(String[] args) {
		int firstChar = -1;
		boolean needsUncompression = false;

		try {
			firstChar = System.in.read();
		} catch (IOException e) {
			System.out.println("There was an exception reading the first character; " + e.getMessage());
		}
		
		if (firstChar == '0') { //48) {
			needsUncompression = true;
		} 

		Scanner input = new Scanner(System.in);
		LinkedList<String> list = new LinkedList<String>();
		int uncompressedCharacters = 0;
		int compressedCharacters = 0;
		int lineNumInFile = 0;

		while (input.hasNextLine()) {
			lineNumInFile++;
			String inputLine = "";
			if (lineNumInFile == 1) {
				// inputLine = String.valueOf(Character.toChars(firstChar)) + input.nextLine();
				inputLine = ((char) firstChar) + input.nextLine();
			} else {
				inputLine = input.nextLine();
			}

			if (needsUncompression) {
				// code to uncompress
				System.out.println("Here is where we uncompress");
			} else {
				if (lineNumInFile == 1) {
					System.out.print("0_");
				}
				
				// code to compress
				Scanner inputLineScanner = new Scanner(inputLine).useDelimiter("[^a-zA-Z]");
				String words = "";
				
				while (inputLineScanner.hasNext()) {
					words += inputLineScanner.next() + " ";
				}
				System.out.print(words.trim());
				inputLineScanner.close();
			}
			// list.addFirst(input.next());
		}

		input.close();
	}
}

