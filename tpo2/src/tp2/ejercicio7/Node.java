package tp2.ejercicio7;

public class Node<E> {
	private E elem;
	private Node<E> next;
	private Node<E> previous;
	
	public Node(E elem, Node<E> next, Node<E> previous) {
		this.elem = elem;
		this.next = next;
		this.previous = previous;
	}

	public E getElem() {
		return elem;
	}

	public void setElem(E elem) {
		this.elem = elem;
	}

	public Node<E> getNext() {
		return next;
	}

	public void setNext(Node<E> next) {
		this.next = next;
	}

	public Node<E> getPrevious() {
		return previous;
	}

	public void setPrevious(Node<E> previous) {
		this.previous = previous;
	}
}
