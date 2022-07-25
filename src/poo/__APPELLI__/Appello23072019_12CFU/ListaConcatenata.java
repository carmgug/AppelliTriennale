package poo.__APPELLI__.Appello23072019_12CFU;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ListaConcatenata<T> implements Iterable<T>{
	private static class Nodo<E>{
		E info;
		Nodo<E> next;
	}
	
	
	
	
	private Nodo<T> first=null;
	private int size=0;
	
	
	
	public int size() {
		return size;
	}
	
	
	public void add(T x) {
		Nodo<T> nc=new Nodo<T>();
		nc.info=x;
		nc.next=first;
		first=nc;
		size++;
	}
	
	
	public void remove(T x) {
		if(first==null) return;
		Nodo<T> pre=null,cor=first;
		while(cor!=null && !cor.info.equals(x)) {
			pre=cor;cor=cor.next;
		}
		if(cor==null) return;
		//se cor � null non c'� nella lista in caso c'�
		pre.next=cor.next;
		//bypass
		size--;
	}
	
	public void BubbleSort(Comparator<T> c) {
		if(size<=1) return;
		boolean scambi=true;
		int limite=size-1,ius=-1;
		while(scambi) {
			Nodo<T> x=first,y=null;
			scambi=false;
			int pos=1;
			while(pos<limite) {
				y=x.next;
				if(c.compare(x.info, y.info)>0) {
					T park=x.info;
					x.info=y.info;
					y.info=park;
					scambi=true;ius=pos;
					x=y;
				}
				else {x=y;}
				pos++;
			}
			limite=ius;
		}	
	}


	@Override
	public Iterator<T> iterator() {
		return new MyIterator();
	}
	
	private class MyIterator implements Iterator<T>{
		Nodo<T> cor=null;
		//il precedente non serve non devono essere effettuate rimozioni
		@Override
		public boolean hasNext() {
			if(cor==null) return first!=null;
			else return cor.next!=null;
		}

		@Override
		public T next() {
			if(!hasNext()) throw new NoSuchElementException();
			if(cor==null) cor=first;
			else {
				cor=cor.next;
			}
			return cor.info;
		}
		
		
		
		
		
	}
	
}
