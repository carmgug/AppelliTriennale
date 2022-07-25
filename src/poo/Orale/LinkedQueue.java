package poo.Orale;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedQueue<T> extends AbstractQueue<T> {

	private static class Nodo<E>{
		E info;
		Nodo<E> next;
	}
	
	private Nodo<T> first=null,last=null;
	private int size=0;
	
	
	
	@Override
	public void add(T x) {
		Nodo<T> nc=new Nodo<>();
		nc.info=x;
		if(last==null) {first=nc;}
		else last.next=nc;
		last=nc;
		size++;
	}

	@Override
	public T remove() {
		if(size==0) throw new NoSuchElementException();
		T elem=first.info;
		first=first.next;
		if(first==null) last=null;
		size--;
		return elem;
	}

	@Override
	public T peek() {
		if(size==0) throw new NoSuchElementException();
		return first.info;

	}

	@Override
	public Iterator<T> iterator() {
		return new MyIterator();
	}

	
	private class MyIterator implements Iterator<T>{
		Nodo<T> pre=null,cor=null;

		@Override
		public boolean hasNext() {
			if(cor==null) return size>0;
			 return cor.next!=null;
		}

		@Override
		public T next() {
			if(!hasNext()) throw new NoSuchElementException();
			if(cor==null) cor=first;
			else {
				pre=cor;cor=cor.next;
			}
			return cor.info;
		}
		public void remove() {
			if(pre==cor) throw new IllegalStateException();
			if(cor==first) {
				first=first.next;
			}
			else {
				pre.next=cor.next;//in mezzo alla coda o siamo alla fine della cosa
			}
			cor=pre;
			size--;
		}
		
		
		
	}
	
}
