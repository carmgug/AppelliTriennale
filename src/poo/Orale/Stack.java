package poo.Orale;

import java.util.Iterator;

public interface Stack<T> extends Iterable<T> {
	
	default public int size() {
		int c=0;
		for(T elem:this)c++;
		return c;
	}
	default public void clear() {
		Iterator<T> it=iterator();
		while(it.hasNext()) {
			it.next();it.remove();
		}
	}
	public void push(T x);
	default public T pop(T x) {
		Iterator<T> it=iterator();
		T elem=it.next();
		it.remove();
		return elem;
	}
	
	default public T peek() {
		Iterator<T> it=iterator();
		return it.next();
	}
	default public boolean contains(T x) {
		Iterator<T> it=iterator();
		while(it.hasNext()) {
			T elem=it.next();
			if(elem.equals(x)) return true;
		}
		return false;
	}
	
	
	

}
