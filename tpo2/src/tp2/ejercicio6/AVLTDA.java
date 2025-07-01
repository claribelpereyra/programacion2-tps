package tp2.ejercicio6;

public interface AVLTDA <E extends Comparable<E>> {
	public boolean exists( E elemento );
	public void insert( E elemento );
	public void delete( E elemento );
	public String toString();
}

