package tp2.ejercicios2y3.ejercicios2y3;

import java.util.ArrayList;
import java.util.List;

public class GrafoEst<E> implements GrafoTDA<E> {
	private int[][] mAdy; //Matriz de adyacencia
	private E[] etiqs; //Vector para mapeo a índices
	private int cantNodos;
	
	@SuppressWarnings("unchecked")
	public void inicializarGrafo() {
		mAdy = new int[100][100];
		etiqs = (E[])new Object[100];
		cantNodos = 0;
	}
	
	public void agregarVertice(E v) {
		etiqs[cantNodos] = v;
		for (int i = 0; i <= cantNodos; i++) {
			mAdy[cantNodos][i] = 0; //Nueva fila en 0
			mAdy[i][cantNodos] = 0; //Nueva columna en 0
		}
		cantNodos++;
	}
	
	public void eliminarVertice(E v) {
		int ind = vert2Indice(v); //índice del vértice por eliminar
		for (int k = 0; k < cantNodos; k++)
			mAdy[k][ind] = mAdy[k][cantNodos-1]; //se “pisa” la fila...
		for (int k = 0; k < cantNodos; k++)
			mAdy[ind][k] = mAdy[cantNodos-1][k]; //... y la columna
		etiqs[ind] = etiqs[cantNodos-1];
		cantNodos--;
	}
	
	private int vert2Indice(E v) { //Mapeamos vértice a índice
		int i = cantNodos-1;
		while(i >= 0 && !etiqs[i].equals(v))
			i--;
		return i;
	}

	public List<E> vertices() {
		List<E> salida = new ArrayList<>();
		for (int i = 0; i < cantNodos; i++) {
			salida.add(etiqs[i]);
		}
		return salida;
	}

	
	public void agregarArista(E v1, E v2, int peso) {
		int o = vert2Indice(v1);
		int d = vert2Indice(v2);
		mAdy[o][d] = peso;
	}
	
	public void eliminarArista(E v1, E v2) {
		int o = vert2Indice(v1);
		int d = vert2Indice(v2);
		mAdy[o][d] = 0;
	}
	
	public boolean existeArista(E v1, E v2) {
		int o = vert2Indice(v1);
		int d = vert2Indice(v2);
		return mAdy[o][d] != 0;
	}
	
	public int pesoArista(E v1, E v2) {
		int o = vert2Indice(v1);
		int d = vert2Indice(v2);
		return mAdy[o][d];
	}

	public int mayorCostoSaliente(E v){
		int i = vert2Indice(v);
		if (i == -1) return -1;

		int max = Integer.MIN_VALUE;
		for (int j = 0; j < cantNodos; j++) {
			if (mAdy[i][j] != 0) {
				max = Math.max(max, mAdy[i][j]);
			}
		}
		return (max == Integer.MIN_VALUE) ? -1 : max;
	}

	public List<E> predecesores(E v) {
		int j = vert2Indice(v);
		List<E> salida = new ArrayList<>();

		for (int i = 0; i < cantNodos; i++) {
			if (mAdy[i][j] != 0) {
				salida.add(etiqs[i]);
			}
		}
		return salida;
	}

}
