package poo.Orale;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class StackConcatenato<T> extends AbstractStack<T>{

	private static class Nodo<E>{
		E info;
		Nodo<E> next;
	}
	
	
	
	private Nodo<T> testa=null;
	private int size=0;
	
	public int size() {
		return size;
		
	}
	public void clear() {
		testa=null;
		size=0;
	}
	
	
	
	
	
	@Override
	public void push(T x) {
		Nodo<T> nc=new Nodo<>();
		nc.info=x;
		nc.next=testa;
		testa=nc;
		size++;
	}

	public T pop() {
		if(size==0) throw new NoSuchElementException();
		T elem=testa.info;
		testa=testa.next;
		size--;
		return elem;
	}
	
	public T peek() {
		if(size==0) throw new NoSuchElementException();
		return testa.info;
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
			if(!hasNext())throw new NoSuchElementException();
			if(cor==null) cor=testa;
			else {
				pre=cor;cor=cor.next;
			}
			return cor.info;
		}
		
		public void remove() {
			if(pre==cor) throw new IllegalStateException();
			if(cor==testa) {
				testa=cor.next;
			}
			else {
				pre.next=cor.next;
			}
			
			cor=pre;
			
			size--;
			
		}
		
		
		
	}
	

}
