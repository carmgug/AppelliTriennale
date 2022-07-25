package poo.__APPELLI__.Appello2501_2019;

import java.util.Iterator;
import java.util.ListIterator;

public interface List<T> extends Iterable<T> {
	default int size() {
		int c=0;
		for(T x:this) {
			c++;
		}
		return c;
	}
	default boolean contains(T x) {
		for(T elem:this) {
			if(elem.equals(x)) return true;
		}
		return false;
	}
	default void clear() {
		Iterator<T> it=this.iterator();
		
		while(it.hasNext()) {
			it.next();
			it.remove();
		}
	}
	
	void addFirst(T x) ;
	void addLast(T x);
	T getFirst();
	T getLast();
	default T removeFirst() {
		if(size()==0) return null;
		Iterator<T> it=iterator();
		T elem=it.next();
		it.remove();
		return elem;
	}
	default T removeLast() {
		
		int size=size()-1;
		if(size==-1) return null;
		Iterator<T> it=iterator();
		T elem=null;
		while(it.hasNext() && size==0) {
			elem= it.next();
			size--;
		}
		it.remove();
		return elem;
	}
	
	default T remove(T x) {
		
		if(size()==0) return null;
		
		Iterator<T> it=iterator();
		while(it.hasNext()) {
			T elem=it.next();
			if(elem.equals(x)) {it.remove();return elem;}
		}
		return null;
	}
	
	default boolean isEmpty() {return size()==0;}
	default boolean isFull() {return false;}//non � mai piena.
	ListIterator<T> listIterator();
	ListIterator<T> listIterator(int from);
	
	
	
	
	
	
	
}//LIst
