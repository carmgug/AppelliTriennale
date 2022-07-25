package poo.Orale;

import java.util.Iterator;

public interface Vector<T> extends Iterable<T> {
	
	default public int size() {
		int c=0;
		for(T x:this) c++;
		return c;
	}
	default public void clear() {
		Iterator<T> it=iterator();
		while(it.hasNext()) {
			it.next();it.remove();
		}
	}
	default public void add(T x) {
		add(size(),x);
	}
	public void add(int indice,T x);
	default public void remove(T x) {
		Iterator<T> it=iterator();
		while(it.hasNext()) {
			if(it.next().equals(x)) {it.remove();return;}
		}
	}
	default public T remove(int indice) {
		if(indice<0 || indice>=size()) throw new IndexOutOfBoundsException();
		Iterator<T> it=iterator();
		int i=-1;
		while(it.hasNext()) {
			T elem=it.next();i++;
			if(i==indice) {it.remove();return elem;}
		}
		return null;
		
	}
	default public T get(int indice) {
		if(indice<0 || indice>=size()) throw new IndexOutOfBoundsException();
		Iterator<T> it=iterator();
		int i=-1;
		while(it.hasNext()) {
			T elem=it.next();i++;
			if(i==indice) {return elem;}
		}
		return null;
	}
	public void set(int indice,T x);
	default public boolean contains(T x) {
		Iterator<T> it=iterator();
		while(it.hasNext()) {
			if(it.next().equals(x))return true;
		}
		return false;
	}
	
	

}
