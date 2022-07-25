package poo.util;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ListaOrdinataConcatenata<T extends Comparable<? super T>> extends CollezioneOrdinataAstratta<T> {
	private static class Nodo<E>{
		E info;
		Nodo<E> next;
	}
	private Nodo<T> testa=null;
	private int size=0;

	public int size() { return size; }
	public void clear() { testa=null; size=0; }
	public boolean contains( T x ) {
		Nodo<T> cor=testa;
		while( cor!=null ) {
			if( cor.info.equals(x) ) return true;
			if( cor.info.compareTo(x)>0 ) return false;
			cor=cor.next;
		}
		return false;
	}//contains
	
	public void add( T x ) {
		Nodo<T> nuovo=new Nodo<>();
		nuovo.info=x;
		if( testa==null || testa.info.compareTo(x)>=0 ) {
			nuovo.next=testa;
			testa=nuovo;
		}
		else {
			Nodo<T> pre=testa, cor=testa.next;
			while( cor!=null && cor.info.compareTo(x)<0 ) {
				pre=cor;
				cor=cor.next;
			}
			pre.next=nuovo;
			nuovo.next=cor;
		}
		size++;
	}//add
	
	public void remove( T x ) {
		Nodo<T> pre=null,  cor=testa;
		while( cor!=null && cor.info.compareTo(x)<0 ) {
			pre=cor;
			cor=cor.next;
		}
		if( cor!=null && cor.info.equals(x) ) {
			if( cor==testa )
				testa=testa.next;
			else
				pre.next=cor.next; 
			size--;
		}
	}//remove
	
	public T get( T x ) {
		Nodo<T> cor=testa;
		while( cor!=null ) {
			if( cor.info.equals(x) ) return cor.info;
			if( cor.info.compareTo(x)>0 ) return null;
			cor=cor.next;
		}
		return null;		
	}//get
	public boolean isEmpty() { return size==0; } //o testa==null
	public boolean isFull() { return false; }
	
	public Iterator<T> iterator(){ return new MyIterator(); }
	
	private class MyIterator implements Iterator<T>{
		Nodo<T> pre=null, cor=null;
		public boolean hasNext() {
			if( cor==null ) return testa!=null;
			return cor.next!=null;
		}//hasNext
		public T next() {
			if( !hasNext() ) throw new NoSuchElementException();
			if( cor==null ) cor=testa;
			else { pre=cor; cor=cor.next; }
			return cor.info;
		}//next
		public void remove() {
			if( pre==cor ) throw new IllegalStateException();
			if( cor==testa ) testa=testa.next;
			else {
				pre.next=cor.next;
			}
			size--;
			cor=pre;
		}//remove
	}//MyIterator
	
	public static void main( String[] args ) {
		CollezioneOrdinata<String> cs=new ListaOrdinataConcatenata<>();
		cs.add("como"); cs.add("aratro"); cs.add("zaino"); cs.add("lupo"); cs.add("tana");
		System.out.println( cs );
		cs.remove("lupo");
		System.out.println("Lista senza lupo: "+cs+" size="+cs.size());
	}//main
	
}//ListaOrdinataConcatenata
