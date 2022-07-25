package poo.__APPELLI__.Appello17072015;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class InsiemeConcatenato<T> extends InsiemeAstratto<T>{

	private static class Nodo<T>{
		T info;
		Nodo<T> next;
	}
	
	
	
	public InsiemeConcatenato() {
		first=null;
		size=0;
	}
	
	public InsiemeConcatenato(Insieme<T> Set) {
		this();
		for(T x:Set) {
			this.add(x);
		}
	}
	
	private Nodo<T> first;
	private int size;
	
	@Override
	public void add(T x) {
		if(this.contains(x)) return;
		Nodo<T> nc=new Nodo<T>();nc.info=x;
		if(first==null) {first=nc;}
		else {//aggiungiamo in coda;
			Nodo corr=first;
			while(corr.next!=null) {
				corr=corr.next;
			}
			corr.next=nc;
		}
		size++;
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
			if(cor==null) {cor=first;}
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
				pre.next=cor.next;
			}
			size--;
			cor=pre;
			
		}
		
		
		
	}
	

}
