package poo.__APPELLI__.Appello24012014;

public class Esercizio6<T> {
	
	
	private static class Lista<E>{
		E info;
		Lista<E> next;
	}
	
	private Lista<T> lista=null;
	
	
	
	
	public void remove(T e) {
		lista=remove(lista,e);
	}
	
	private Lista<T> remove(Lista<T> n,T e) {
		if(n==null) return null;
		if(n.info.equals(e)) {
			n.next=remove (n.next,e);
			return n.next;
		}
		n.next=remove(n.next,e);//cerco nei nodi successivi;
		return n;
	}
	
	public Esercizio6<T> copy(T e) {
		Esercizio6<T> copy=new Esercizio6<>();
		copy.lista=copy(lista);
		return copy;
	}
	
	private Lista<T> copy(Lista<T> n) {
		if(n==null) return null;
		Lista<T> copia=new Lista<T>();
		copia.info=n.info;
		copia.next=copy(n.next);
		return copia;
	}
	
}
