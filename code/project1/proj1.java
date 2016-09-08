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

		public void addFirst(E toAdd) {
			head = new Node<E>(toAdd, head);
			size++;
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
			System.out.println(list.lookUp(i));
		}

		System.out.println(list.positionInList("cat"));
		System.out.println(list.positionInList("needed"));
		System.out.println(list.positionInList("lazy"));
		System.out.println(list.positionInList("brown"));

		System.out.println(list.size());
		input.close();
	}
}

