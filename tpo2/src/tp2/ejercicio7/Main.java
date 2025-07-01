package tp2.ejercicio7;

public class Main {
	public static void main(String[] args) {
		ABBDictionary<String, String> dic = new ABBDictionary<>(String::compareTo);

		// Insertamos múltiples valores por clave
		dic.put("Argentina", "Buenos Aires");
		dic.put("Argentina", "Córdoba");
		dic.put("Argentina", "Rosario");

		dic.put("Brasil", "San Pablo");
		dic.put("Brasil", "Río de Janeiro");

		dic.put("Chile", "Santiago");

		System.out.println("Diccionario inicial:");
		System.out.println(dic);

		// Eliminamos una ciudad específica de Argentina
		System.out.println("\nEliminando 'Córdoba' de 'Argentina'...");
		dic.remove("Argentina", "Córdoba");

		System.out.println("Diccionario actualizado:");
		System.out.println(dic);

		// Eliminamos toda la clave Chile
		System.out.println("\nEliminando clave 'Chile' completa...");
		dic.remove("Chile");

		System.out.println("Diccionario final:");
		System.out.println(dic);

		// Verificamos pertenencia
		System.out.println("\n¿Contiene clave 'Brasil'? " + dic.get("Brasil"));
		System.out.println("¿Contiene clave 'Chile'? " + dic.get("Chile"));
	}
}
