package ejercicio7.ejercicio7.abb;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ArbolBB<E extends Comparable<E>> implements ABBTDA<E> {
	protected NodoABB<E> raiz;
	protected int size;
	Comparator<E> comp;

	public ArbolBB(Comparator<E> comp) {
		raiz = new NodoABB<E>(null, null);
		size = 0;
		this.comp = comp;
	}

	public boolean pertenece(E elemento) {
		return buscar(elemento).getElemento() != null;
	}

	private NodoABB<E> buscar(E elemento) {
		return buscarAux(elemento, raiz);
	}

	private NodoABB<E> buscarAux(E elemento, NodoABB<E> nodov) {
		if (nodov.getElemento() == null)
			return nodov;
		else {
			int c = comp.compare(elemento, nodov.getElemento());
			if (c == 0)
				return nodov;
			else if (c < 0)
				return buscarAux(elemento, nodov.getIzq());
			else
				return buscarAux(elemento, nodov.getDer());
		}
	}

	public void insertar(E elemento) {
		NodoABB<E> nodov = buscar(elemento);
		if (nodov.getElemento() == null) {
			nodov.setElemento(elemento);
			nodov.setIzq(new NodoABB<E>(null, nodov));
			nodov.setDer(new NodoABB<E>(null, nodov));
			size++;
		} else {
			throw new IllegalArgumentException("Elemento duplicado: " + elemento);
		}
	}

	public void insertar2(E elemento) {
		insertarAux(elemento, raiz);
	}

	private void insertarAux(E elemento, NodoABB<E> nodov) {
		if (nodov.getElemento() == null) {
			nodov.setElemento(elemento);
			size++;
			nodov.setIzq(new NodoABB<E>(null, nodov));
			nodov.setDer(new NodoABB<E>(null, nodov));
		} else {
			int c = comp.compare(elemento, nodov.getElemento());
			if (c == 0) {
				throw new IllegalArgumentException("Elemento duplicado: " + elemento);
			} else if (c < 0) {
				insertarAux(elemento, nodov.getIzq());
			} else {
				insertarAux(elemento, nodov.getDer());
			}
		}
	}

	public E eliminar(E elemento) {
		NodoABB<E> p = buscar(elemento);
		if (p.getElemento() != null) {
			E eliminado = p.getElemento();
			eliminarAux(p);
			size--;
			return eliminado;
		} else {
			return null;
		}
	}

	private boolean isExternal(NodoABB<E> p) {
		return p.getIzq().getElemento() == null && p.getDer().getElemento() == null;
	}

	private boolean soloTieneHijoIzquierdo(NodoABB<E> p) {
		return p.getIzq().getElemento() != null && p.getDer().getElemento() == null;
	}

	private boolean soloTieneHijoDerecho(NodoABB<E> p) {
		return p.getDer().getElemento() != null && p.getIzq().getElemento() == null;
	}

	private void eliminarAux(NodoABB<E> p) {
		if (isExternal(p)) {
			p.setElemento(null);
			p.setIzq(null);
			p.setDer(null);
		} else {
			if (soloTieneHijoIzquierdo(p)) {
				if (p.getPadre().getIzq() == p)
					p.getPadre().setIzq(p.getIzq());
				else
					p.getPadre().setDer(p.getIzq());
				p.getIzq().setPadre(p.getPadre());
			} else if (soloTieneHijoDerecho(p)) {
				if (p.getPadre().getIzq() == p)
					p.getPadre().setIzq(p.getDer());
				else
					p.getPadre().setDer(p.getDer());
				p.getDer().setPadre(p.getPadre());
			} else {
				p.setElemento(eliminarMinimo(p.getDer()));
			}
		}
	}

	private E eliminarMinimo(NodoABB<E> p) {
		if (p.getIzq().getElemento() == null) {
			E aRetornar = p.getElemento();
			if (p.getDer().getElemento() == null) {
				p.setElemento(null);
				p.setIzq(null);
				p.setDer(null);
			} else {
				p.getPadre().setIzq(p.getDer());
				p.getDer().setPadre(p.getPadre());
			}
			return aRetornar;
		} else {
			return eliminarMinimo(p.getIzq());
		}
	}

	public String toString() {
		return preorder(raiz);
	}

	private String inorder(NodoABB<E> nodov) {
		if (nodov.getElemento() != null) {
			return "(" + inorder(nodov.getIzq()) + nodov.getElemento() + inorder(nodov.getDer()) + ")";
		} else return "";
	}

	private String preorder(NodoABB<E> nodov) {
		if (nodov.getElemento() != null) {
			return "(" + nodov.getElemento() + preorder(nodov.getIzq()) + preorder(nodov.getDer()) + ")";
		} else return "";
	}

	private String postorder(NodoABB<E> nodov) {
		if (nodov.getElemento() != null) {
			return "(" + postorder(nodov.getIzq()) + postorder(nodov.getDer()) + nodov.getElemento() + ")";
		} else return "";
	}

	public Iterable<E> listar() {
		List<E> lista = new ArrayList<>();
		inOrden(raiz, lista);
		return lista;
	}

	private void inOrden(NodoABB<E> nodo, List<E> lista) {
		if (nodo != null && nodo.getElemento() != null) {
			inOrden(nodo.getIzq(), lista);
			lista.add(nodo.getElemento());
			inOrden(nodo.getDer(), lista);
		}
	}
}