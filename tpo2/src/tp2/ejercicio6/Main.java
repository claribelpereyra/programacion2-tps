package tp2.ejercicio6;

import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		AVL<Integer> arbol = new AVL<>(new DefaultComparator<Integer>());
		arbol.insert(20);
		arbol.insert(10);
		arbol.insert(30);
		arbol.insert(5);
		arbol.insert(15);
		arbol.insert(25);
		arbol.insert(35);

		System.out.println("AVL por niveles:");
		arbol.printPorNiveles();
		
		System.out.println("Altura: " + arbol.getHeight());
		
		// ejercicio 6
		AVL<Integer> arbolUsuario = new AVL<Integer>(new DefaultComparator<Integer>());
		
		boolean terminar = false;
		Scanner scanner = new Scanner(System.in);

		while (!terminar) {
			try {
				System.out.print("\nIngrese un número (o '.' para terminar): ");
				String input = scanner.nextLine();

				if (input == null || input.trim().isEmpty()) {
					throw new MyException("No se ingresó nada.");
				}

				input = input.trim();

				if (input.equals(".")) {
					terminar = true;
					break;
				}

				int num;
				try {
					num = Integer.parseInt(input);
				} catch (NumberFormatException e) {
					System.out.println("Entrada inválida. Por favor ingrese un número válido o '.' para terminar.");
					continue;
				}

				arbolUsuario.insert(num);

			} catch (MyException e) {
				System.out.println(e.getMessage());
			}
		}

		System.out.println("\nÁrbol AVL ingresado por usuario (por niveles):");
        arbolUsuario.printPorNiveles();

        // así hace un print de los elementos
        System.out.println("\nElementos ordenados de menor a mayor:");
        arbolUsuario.inOrderTraversal();
        
        // así muestra los elementos en un arreglo cumpliendo de forma más literal con el enunciado
        System.out.println("\nElementos ordenados de menor a mayor:");
        List<Integer> ordenados = arbolUsuario.inOrderList();
        System.out.println(ordenados);
	}

}
