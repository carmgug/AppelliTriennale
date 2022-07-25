package poo.util;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedQueue<T> extends AbstractQueue<T> {
	private static class Nodo<E>{
		E info;
		Nodo<E> next;
	}
	private Nodo<T> first=null, last=null;
	private int size;
	public int size() { return size; }
	public void clear() { first=null; last=null; size=0; }
	
	public void add( T e ) {
		Nodo<T> n=new Nodo<>(); 
		n.info=e; n.next=null;
		if( first==null ) first=n;
		else last.next=n;
		last=n;
		size++;
	}//add
	
	public T remove() {
		if( first==null ) throw new NoSuchElementException();
		T x=first.info;
		first=first.next;
		if( first==null ) last=null;
		size--;
		return x;
	}//remove
	
	public T peek() {
		if( first==null ) throw new NoSuchElementException();
		return first.info;	
	}//peek
	
	public Iterator<T> iterator(){ return new QueueIterator(); }
	
	private class QueueIterator implements Iterator<T>{
		private Nodo<T> pre=null, cor=null;
		public boolean hasNext() {
			if( cor==null ) return first!=null;
			return cor.next!=null;
		}
		public T next() {
			if( !hasNext() ) throw new NoSuchElementException();
			if( cor==null ) cor=first;
			else { pre=cor; cor=cor.next; }
			return cor.info;
		}
		public void remove() {
			if( pre==cor ) throw new IllegalStateException();
			if( cor==first ) {
				first=first.next;
				if( first==null ) last=null;
			}
			else if( cor==last ) {
				last=pre; last.next=null;
			}
			else {
				pre.next=cor.next;
			}
			size--;
			cor=pre;
		}
	}//QueueIterator
	
}//LinkedQueue
