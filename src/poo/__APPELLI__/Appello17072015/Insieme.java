package poo.__APPELLI__.Appello17072015;

import java.util.Iterator;

public interface  Insieme<T> extends Iterable<T> {
	
	default public  int size() {
		int c=0;
		for(T x:this)c++;
		return c;
	}
	
	default void clear() {
		Iterator<T> it=iterator();
		while(it.hasNext()) {
			it.next();
			it.remove();
		}
	}

	default public boolean contains(T x) {
		Iterator<T> it=iterator();
		while(it.hasNext()) {
			T elem=it.next();
			if(elem.equals(x)) return true;
		}
		return false;
	}
	
	void add(T x);
	default void remove(T x) {
		Iterator<T> it=iterator();
		while(it.hasNext()) {
			T elem=it.next();
			if(elem.equals(x)) {
				it.remove();
				return;
			}
		}
	}
	
	
	//elementi condivisi aliasing tra contenuti attenzione
	default void addAll(Insieme<T> i) {
		for(T x:i) {
			this.add(x);
		}
	}
	
	default void removeAll(Insieme<T> i) {
		for(T x:i) {
			this.remove(x);
		}
	}
	
	default void retainAll(Insieme<T> i) {
		Iterator<T> it=iterator();
		while(it.hasNext()) {
			T elem=it.next();
			if(i.contains(elem)) continue;
			else {it.remove();}
		}
	}
}
