package poo.__APPELLI__.Appello30012017;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedSortedSet<T> extends AbstractSortedSet<T> {

	
	private static class Nodo<T>{
		T info;
		Nodo<T> next;
		public void info(T e) {
			// TODO Auto-generated method stub
			
		}
	}
	
	
	Nodo<T> first;
	int size;
	Comparator<T> c;
	
	public LinkedSortedSet(Comparator<T> c) {
		this.c=c;
		first=null;
		size=0;
	}
	
	
	@Override
	public Comparator<T> getComparator() {
		return c;
	}

	@Override
	public void setComparatorAndSort(Comparator<T> c) {
		
		this.c=c;
		if(size<=1) return;
		boolean scambi=true;
		int limite=size,ius=0;
		while(scambi) {
			Nodo<T> x=first,y=null;
			scambi=false;int i=1;
			while(i<size) {
				y=x.next;
				if(c.compare(x.info, y.info)>0) {
					T park=x.info;x.info=y.info;
					y.info=park;
					ius=i;scambi=true;
				}
				else {x=y;}
				i++;
			}
			limite=ius;
		}
	}

	@Override
	public boolean add(T e) {
		if(this.contains(e)) return false;
		Nodo<T> nc=new Nodo<T>();nc.info(e);
		
		if(first==null) {first=nc;}
		else {
			Nodo<T> pre=null,cor=first;
			while(cor!=null && c.compare(cor.info, e)<0) {
				pre=cor;cor=cor.next;
			}
			pre.next=nc;nc.next=cor;
		}
		size++;
		return true;
	}

	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return null;
	}
	
	private class MyIterator implements Iterator<T> {
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
			else {pre=cor;cor=cor.next;}
			size++;
			return cor.info;
		}
		
		public void remove() {
			if(pre==cor) throw new IllegalStateException();
			if(cor==first) {
				first=cor.next;
			}
			else {
				pre.next=cor.next;
			}
			cor=pre;
			size--;
		}
		
	}

}
