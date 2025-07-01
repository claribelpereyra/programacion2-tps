package tp2.ejercicios2y3;

import java.util.List;

public class Main {
	public static void main(String[] args) {
		System.out.println("\n Ejercicio 2: \n");
		ejericio2();
		System.out.println("\n Ejercicio 3: \n");
		ejercicio3();
	}

	private static void ejericio2(){
		GrafoEst<String> grafo = new GrafoEst<>();
		grafo.inicializarGrafo();

		// Agregamos vértices
		grafo.agregarVertice("A");
		grafo.agregarVertice("B");
		grafo.agregarVertice("C");
		grafo.agregarVertice("D");
		grafo.agregarVertice("E");

		// Agregamos aristas con diferentes pesos
		grafo.agregarArista("A", "B", 5);
		grafo.agregarArista("A", "C", 10);
		grafo.agregarArista("A", "D", 3);
		grafo.agregarArista("B", "D", 7);
		grafo.agregarArista("C", "D", 2);
		grafo.agregarArista("E", "A", 9);

		// Probar mayorCostoSaliente
		System.out.println("Mayor costo saliente desde A: " + grafo.mayorCostoSaliente("A")); // Esperado: 10
		System.out.println("Mayor costo saliente desde B: " + grafo.mayorCostoSaliente("B")); // Esperado: 7
		System.out.println("Mayor costo saliente desde D: " + grafo.mayorCostoSaliente("D")); // Esperado: -1 (sin salientes)

		// Probar predecesores
		imprimirPredecesores(grafo, "D"); // Esperado: A, B, C
		imprimirPredecesores(grafo, "A"); // Esperado: E
		imprimirPredecesores(grafo, "E"); // Esperado: []

		// Verificación rápida de existencia de aristas
		System.out.println("¿Existe arista A → C?: " + grafo.existeArista("A", "C")); // true
		System.out.println("¿Existe arista C → B?: " + grafo.existeArista("C", "B")); // false

		// Eliminar vértice C y volver a probar
		grafo.eliminarVertice("C");
		System.out.println("Vértices tras eliminar C: " + grafo.vertices()); // Esperado: A, B, D, E

		// Probar nuevamente mayorCostoSaliente y predecesores
		System.out.println("Mayor costo saliente desde A luego de eliminar C: " + grafo.mayorCostoSaliente("A")); // Esperado: 5 (B), 3 (D) => 5

		imprimirPredecesores(grafo, "D"); // Esperado: A, B (C fue eliminado)

	}

	private static void imprimirPredecesores(GrafoEst<String> grafo, String v) {
		List<String> lista = grafo.predecesores(v); // Usa esta si cambiaste el método a List<E>
		System.out.print("Predecesores de " + v + ": ");
		if (lista.isEmpty()) {
			System.out.println("(ninguno)");
		} else {
			System.out.println(lista);
		}
	}

	private static void ejercicio3(){
		GrafoDin<String> grafo = new GrafoDin<>();

		// Agregar vértices
		grafo.agregarVertice("A");
		grafo.agregarVertice("B");
		grafo.agregarVertice("C");
		grafo.agregarVertice("D");
		grafo.agregarVertice("E");
		grafo.agregarVertice("F"); // Vértice sin conexiones
		grafo.agregarVertice("G"); // Vértice sin conexiones

		// Agregar aristas
		grafo.agregarArista("A", "B", 1);
		grafo.agregarArista("B", "C", 1);
		grafo.agregarArista("A", "D", 1);
		grafo.agregarArista("D", "C", 1);
		grafo.agregarArista("C", "E", 1);
		grafo.agregarArista("B", "E", 1);

		// Mostrar vértices aislados
		List<String> aislados = grafo.verticesAislados();
		System.out.println("Vértices aislados: " + aislados); // Esperado: F, G

		// Probar vértices puente entre A y C
		List<String> puentesAC = grafo.verticesPuente("A", "C");
		System.out.println("Vértices puente entre A y C: " + puentesAC); // Esperado: B, D

		// Puentes entre A y E
		List<String> puentesAE = grafo.verticesPuente("A", "E");
		System.out.println("Vértices puente entre A y E: " + puentesAE); // Esperado: B, C

		// Puentes entre F y E (sin conexión)
		List<String> puentesFE = grafo.verticesPuente("F", "E");
		System.out.println("Vértices puente entre F y E: " + puentesFE); // Esperado: []

		// Eliminar arista A → B
		grafo.eliminarArista("A", "B");
		List<String> puentesAC2 = grafo.verticesPuente("A", "C");
		System.out.println("Puentes A → C tras eliminar A → B: " + puentesAC2); // Esperado: D

		// Eliminar vértice D
		grafo.eliminarVertice("D");
		List<String> puentesAC3 = grafo.verticesPuente("A", "C");
		System.out.println("Puentes A → C tras eliminar D: " + puentesAC3); // Esperado: []

		// Verificar si F sigue aislado
		System.out.println("Vértices aislados tras cambios: " + grafo.verticesAislados()); // Esperado: F, G

	}
}
