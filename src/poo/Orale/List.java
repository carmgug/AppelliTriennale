package poo.Orale;

import java.util.Comparator;
import java.util.Iterator;
import java.util.ListIterator;

public interface List<T> extends Iterable<T>{
	
	default public int size() {
		int c=0;
		for(T x:this)c++;
		return c;
	}
	default public void clear() {
		Iterator<T> it=iterator();
		while(it.hasNext()) {
			it.next();it.remove();
		}
	}
	default public boolean contains(T x) {
		Iterator<T> it=iterator();
		while(it.hasNext()) {
			if(it.next().equals(x))return true;
		}
		return false;
	}
	default public T getFirst() {
		Iterator<T> it=iterator();
		return it.next();
	}
	default public T getLast() {
		ListIterator<T> lit=Listiterator(size());
		return lit.previous();
	}
	default public void addFirst(T x) {
		ListIterator<T> lit=Listiterator();
		lit.add(x);
	}
	default public void addLast(T x) {
		ListIterator<T> lit=Listiterator(size());
		lit.add(x);
	}
	default public T removeFirst() {
		ListIterator<T> lit=Listiterator();
		T elem=lit.next();
		lit.remove();
		return elem;
	}
	default public T removeLast() {
		ListIterator<T> lit=Listiterator(size());
		T elem=lit.previous();
		lit.remove();
		return elem;
	}
	
	default boolean isFull() {return false;}
	default boolean isEmpty() {return size()==0;}
	
	
	public static <T> void Sort(List<T> l,Comparator<T> c) {
		if(l.size()<=1) return;
		boolean scambi=true;
		int limite=l.size(),ius=0;
		while(scambi) {
			ListIterator<T> lit=l.Listiterator();
			T x=lit.next(),y=null;
			int pos=1;scambi=false;
			while(pos<limite) {
				y=lit.next();
				if(c.compare(x, y)>0) {
					lit.set(x);
					lit.previous();lit.previous();
					lit.set(y);
					lit.next();lit.next();
					scambi=true;ius=pos;
				}
				else {x=y;}
				pos++;
			}
			limite=ius;
		}
	}
	
	
	public ListIterator<T> Listiterator();
	public ListIterator<T> Listiterator(int pos);
	
	

}
