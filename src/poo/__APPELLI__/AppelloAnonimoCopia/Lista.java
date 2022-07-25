package poo.__APPELLI__.AppelloAnonimoCopia;

import java.util.Iterator;
import java.util.NoSuchElementException;

public interface Lista<T extends Comparable<? super T>> extends Iterable<T> {

	default public int size() {
		int c=0;
		for(T x:this) {
			c++;
		}
		return c;
	}
	default public void clear() {
		Iterator<T> it=iterator();
		while(it.hasNext()) {
			it.next();
			it.remove();
		}
	}
	default public boolean contains(T elem) {
		Iterator<T> it=iterator();
		while(it.hasNext()) {
			T elem2=it.next();
			if(elem.equals(elem2)) return true;
		}
		return false;
	}
	void addLast(T elem);
	void addFirst(T elem);
	void addGE(T elem);
	void addLE(T elem);
	void remove(T elem);
	
	
	default T removeLast() {
		if(size()==0) throw new NoSuchElementException();
		Iterator<T> it=iterator();
	
		while(it.hasNext()) {
			T elem=it.next();
			if(!it.hasNext()) {it.remove(); return elem;}
		}
		return null;//mai eseguito
	}
	default public T removeFirst() {
		Iterator<T> it=iterator();
		T elem=it.next();//gestisce NoSuchElementException();
		it.remove(); 
		return elem;
		
	}
	

}
