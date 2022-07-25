package poo.recursion;

import java.util.Iterator;

import poo.util.CollezioneOrdinata;

public class ListaRec<T extends Comparable<? super T>> implements CollezioneOrdinata<T> {
	private static class Nodo<E>{
		E info;
		Nodo<E> next;
	}
	private Nodo<T> lista=null;
	
	public int size() {
		return size( lista );
	}
	
	private int size( Nodo<T> lista ) {
		if( lista==null ) return 0;
		return 1+size( lista.next );
	}
	
	public boolean contains( T e ) {
		return contains( lista, e );
	}
	
	private boolean contains( Nodo<T> lista, T e ) {
		if( lista==null ) return false;
		if( lista.info.equals(e) ) return true;
		if( lista.info.compareTo(e)>0 ) return false; //"ottimizzazione" ricerca lineare
		return contains( lista.next, e );
	}
	
	public boolean isEmpty() { return lista==null; }
	public boolean isFull() { return false; }
	public void clear() { lista=null; }
	
	public T get( T e ) {
		return get( lista, e );
	}
	
	private T get( Nodo<T> lista, T e ) {
		if( lista==null ) return null;
		if( lista.info.equals(e) ) return lista.info;
		if( lista.info.compareTo(e)>0 ) return null;
		return get( lista.next, e );
	}
	
	public void add( T e ) {
		lista=add( lista, e ); //certamente la lista cambia a seguito dell'inserimento di e
	}
	
	private Nodo<T> add( Nodo<T> lista, T e ){
		if( lista==null || lista.info.compareTo(e)>=0 ) {
			Nodo<T> n=new Nodo<>();
			n.info=e; n.next=lista;
			return n;
		}
		//la lista non e' vuota ed il suo primo elemento e' minore di e
		lista.next=add( lista.next, e );
		return lista;
	}
	
	public void remove( T e ) {
		lista=remove( lista, e );
	}
	
	private Nodo<T> remove( Nodo<T> lista, T e ){
		if( lista==null ) return null;
		if( lista.info.equals(e) ) {
			return lista.next; //eliminato il nodo capolista
		}
		if( lista.info.compareTo(e)>0 ) return lista;
		lista.next=remove( lista.next, e ); //lista residua potenzialmente cambia
		return lista;
	}
	
	public Iterator<T> iterator(){ return new ListaRecIterator(); }
	
	private class ListaRecIterator implements Iterator<T>{ //TODO - lasciato come esercizio
		//sollevare anche la ConcurrentModificationException, quando serve
		Nodo<T> pre=null, cor=null;
		public boolean hasNext() {
			return false; //TODO
		}
		public T next() {
			return null; //TODO
		}
		public void remove() {
			
		}
	}//ListaRecIterator
	
	public String toString() {
		StringBuilder sb=new StringBuilder(100);
		sb.append("[");
		toString( lista, sb );
		sb.append("]");
		return sb.toString();
	}
	
	private void toString( Nodo<T> lista, StringBuilder sb ) {
		if( lista==null ) return;
		sb.append( lista.info );
		if( lista.next!=null ) sb.append(", ");
		toString( lista.next, sb );
	}
	
	@SuppressWarnings("unchecked")
	public boolean equals( Object x ) {
		if( !(x instanceof ListaRec) ) return false;
		if( x==this ) return true;
		ListaRec<T> l=(ListaRec<T>)x;
		return equals( lista, l.lista );
	}
	
	private boolean equals( Nodo<T> lista1, Nodo<T> lista2 ) {
		if( lista1==null && lista2==null ) return true;
		if( lista1==null || lista2==null ) return false;
		if( !lista1.info.equals(lista2.info) ) return false;
		return equals( lista1.next, lista2.next );
	}
	
	private final int MUL=83;
	
	public int hashCode() {
		return hashCode( lista ); 
	}
	
	private int hashCode( Nodo<T> lista ) { //lasciato come esercizio
		return 0; //TODO
	}
	
	public static void main( String[] args ) {
		ListaRec<String> ls=new ListaRec<>();
		ls.add("tana"); ls.add("dado"); ls.add("zinco"); ls.add("casa"); ls.add("abaco");
		System.out.println(ls+" size="+ls.size());
		ls.remove("dado");
		System.out.println(ls+" size="+ls.size());
		ls.add("lupo");
		System.out.println(ls+" size="+ls.size());
		System.out.println("Esiste lupo? "+ls.contains("lupo"));
		ListaRec<String> l2=new ListaRec<>();
		l2.add("lupo"); l2.add("tana"); l2.add("zinco");
		System.out.println("l2="+l2);
		System.out.println("Liste uguali? "+ls.equals(l2));
	}
	
}//ListaRec
