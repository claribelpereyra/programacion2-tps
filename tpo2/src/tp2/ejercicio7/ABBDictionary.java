package tp2.ejercicio7;

import tp2.ejercicio7.abb.ArbolBB;

import java.util.*;

public class ABBDictionary<K, V extends Comparable<V>> implements Dictionary<K, V> {

	private final Entrada<K, ArbolBB<V>>[] tabla;
	private int size;
	private final Comparator<V> comp;

	@SuppressWarnings("unchecked")
	public ABBDictionary(Comparator<V> comp) {
		this.tabla = (Entrada<K, ArbolBB<V>>[]) new Entrada[100];
		this.size = 0;
		this.comp = comp;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public Iterable<V> get(K k) {
		for (int i = 0; i < size; i++) {
			if (tabla[i].getKey().equals(k)) {
				return tabla[i].getValue().listar();
			}
		}
		return Collections.emptyList();
	}

	@Override
	public void put(K k, V v) {
		for (int i = 0; i < size; i++) {
			if (tabla[i].getKey().equals(k)) {
				tabla[i].getValue().insertar(v);
				return;
			}
		}
		if (size < tabla.length) {
			ArbolBB<V> arbol = new ArbolBB<>(comp);
			arbol.insertar(v);
			tabla[size++] = new Entrada<>(k, arbol);
		} else {
			throw new RuntimeException("Diccionario lleno");
		}
	}

	public void putAll(K k, Iterable<V> valores) {
		for (V v : valores) {
			put(k, v);
		}
	}

	@Override
	public Iterable<V> remove(K k) {
		for (int i = 0; i < size; i++) {
			if (tabla[i].getKey().equals(k)) {
				Iterable<V> valores = tabla[i].getValue().listar();
				tabla[i] = tabla[size - 1];
				tabla[size - 1] = null;
				size--;
				return valores;
			}
		}
		return Collections.emptyList();
	}

	@Override
	public V remove(K k, V v) {
		for (int i = 0; i < size; i++) {
			if (tabla[i].getKey().equals(k)) {
				ArbolBB<V> arbol = tabla[i].getValue();
				if (arbol.pertenece(v)) {
					arbol.eliminar(v);
					if (!arbol.listar().iterator().hasNext()) {
						tabla[i] = tabla[size - 1];
						tabla[size - 1] = null;
						size--;
					}
					return v;
				}
			}
		}
		return null;
	}

	@Override
	public Iterable<K> keys() {
		K[] claves = (K[]) new Object[size];
		for (int i = 0; i < size; i++) {
			claves[i] = tabla[i].getKey();
		}
		return Arrays.asList(claves);
	}

	@Override
	public Iterable<Entrada<K, Iterable<V>>> entries() {
		Entrada<K, Iterable<V>>[] resultado = (Entrada<K, Iterable<V>>[]) new Entrada[size];
		for (int i = 0; i < size; i++) {
			resultado[i] = new Entrada<>(tabla[i].getKey(), tabla[i].getValue().listar());
		}
		return Arrays.asList(resultado);
	}
}
