package tp2.ejercicio7.ejercicio7;

public class Entrada<K, V> implements Entry<K, V> {
	private K clave;
	private V valor;
	
	public Entrada(K k, V v) {
		clave = k;
		valor = v;
	}

	@Override
	public K getKey() {
		// TODO Auto-generated method stub
		return clave;
	}

	@Override
	public V getValue() {
		// TODO Auto-generated method stub
		return valor;
	}

	@Override
	public void setValue(V v) {
		// TODO Auto-generated method stub
		valor = v;
	}

	@Override
	public void setKey(K k) {
		// TODO Auto-generated method stub
		clave = k;
	}

}
