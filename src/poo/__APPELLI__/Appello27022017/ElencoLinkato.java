package poo.__APPELLI__.Appello27022017;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ElencoLinkato<T> extends ElencoAstratto<T> {

	private static class Nodo<T>{
		T info;
		Nodo<T> next;
	}
	
	Nodo<T> first;
	int size;
	Comparator<T> c;
	
	public ElencoLinkato(Comparator<T> c) {
		first=null;
		size=0;
		this.c=c;
	}
	
	
	public int size() {
		return size;
	}
	
	@Override
	public void add(T elem) {
		Nodo<T> nuovo=new Nodo<T>();nuovo.info=elem;
		nuovo.next=first;
		first=nuovo;
		size++;
		setComparatorAndSort(c);
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
		int limite=size-1;int ius=0;
		while(scambi) {
			Nodo<T> x=first,y=null;
			int pos=1;scambi=false;
			while(pos<limite) {
				y=x.next;
				if(c.compare(x.info, y.info)>0) {
					T park=x.info;
					x.info=y.info;
					y.info=park;
					scambi=false;ius=pos;
				}
				else x=y;
				pos++;
			}
			limite=ius;
		}

	}

	@Override
	public Iterator iterator() {
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
			else{ pre=cor; cor=cor.next;}
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
