import java.util.Scanner;

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
		Scanner input = new Scanner(System.in);
		LinkedList<String> list = new LinkedList<String>();

		System.out.println(list.size());
		while (input.hasNextLine()) {
			list.addFirst(input.next());
		}

		for (int i = 0; i < list.size(); i++) {
			System.out.print(list.lookUp(i) + " ");
		}

		System.out.println();

		System.out.println(list.positionInList("cat"));
		System.out.println(list.positionInList("needed"));
		System.out.println(list.positionInList("lazy"));
		System.out.println(list.positionInList("brown"));

		System.out.println(list.size());

		int positionToRemove = list.positionInList("fox");
		System.out.println(positionToRemove);
		String removed = list.removeAtPosition(positionToRemove);
		System.out.println(removed);
		removed = list.removeAtPosition(list.size() - 1);
		System.out.println(removed);
		removed = list.removeAtPosition(list.size() - 1);
		System.out.println(removed);
		removed = list.removeAtPosition(0);
		System.out.println(removed);
		positionToRemove = list.positionInList("fox");
		System.out.println(positionToRemove);
		removed = list.removeAtPosition(positionToRemove);
		System.out.println(removed);
		positionToRemove = list.positionInList("dog.");
		System.out.println(positionToRemove);
		removed = list.removeAtPosition(positionToRemove);
		System.out.println(removed);
		positionToRemove = list.positionInList("lazy");
		System.out.println(positionToRemove);
		removed = list.removeAtPosition(positionToRemove);
		System.out.println(removed);

		for (int i = 0; i < list.size(); i++) {
			System.out.print(list.lookUp(i) + " ");
		}

		System.out.println();

		System.out.println(list.size());
		input.close();
	}
}

