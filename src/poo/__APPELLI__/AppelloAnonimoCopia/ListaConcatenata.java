package poo.__APPELLI__.AppelloAnonimoCopia;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ListaConcatenata<T extends Comparable<? super T>> extends ListaAstratta<T> {

	private static class Nodo<E>{
		E info;
		Nodo<E> next;
	}
	
	
	
	
	
	private Nodo<T> first=null;
	private int size=0;
	
	public int size() {
		return size;
	}
	
	@Override
	public void addLast(T elem) {
		Nodo<T> nc=new Nodo<>();
		nc.info=elem;
		if(first==null) {
			first=nc;
		}
		else {
			Nodo<T> pre=null;Nodo<T> cor=first;
			while(cor!=null) {
				pre=cor;cor=cor.next;
			}
			pre.next=nc;
		}
		
		size++;
		
	}
	@Override
	public void addFirst(T elem) {
		Nodo<T> nc=new Nodo<T>();
		nc.info=elem;
		nc.next=first;
		first=nc;
		size++;
	}
	@Override
	public void addGE(T elem) {
		Nodo<T> nc=new Nodo<>();
		nc.info=elem;
		if(first==null) {
			first=nc;
		}
		else {
			Nodo<T> pre=null,cor=first;
			while(cor!=null && cor.info.compareTo(elem)<0) {
				pre=cor;cor=cor.next;
			}
			pre.next=nc;
			nc.next=cor;
		}
		size++;
	}
	@Override
	public void addLE(T elem) {
		Nodo<T> nc=new Nodo<>();
		nc.info=elem;
		if(first==null) {
			first=nc;
		}
		else {
			Nodo<T> pre=null,cor=first;
			while(cor!=null && cor.info.compareTo(elem)>0) {
				pre=cor;cor=cor.next;
			}
			pre.next=nc;
			nc.next=cor;
		}
		size++;
		
	}
	@Override
	public void remove(T elem) {
		if(size==0) throw new NoSuchElementException();
		Nodo<T> pre=null,cor=first;
		while(cor!=null && !cor.info.equals(elem)) {
			pre=cor;cor=cor.next;
		}
		if(cor==null) return;
		pre.next=cor.next;
		size--;
		
	}
	@Override
	public Iterator<T> iterator() {
		return new MyIterator();
	}
	
	
	private class MyIterator implements Iterator<T>{
		Nodo<T> pre=null,cor=null;

		@Override
		public boolean hasNext() {
			if(cor==null) return first!=null;
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
			if(pre==cor)throw new IllegalStateException();
			if(cor==first) {
				first=first.next;
			}
			else {
				pre.next=cor.next;
			}
			size--;
			cor=pre;
		}
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
