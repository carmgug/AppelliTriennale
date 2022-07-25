package poo.__APPELLI__.Appello27022017;

import java.util.Comparator;
import java.util.Iterator;

public interface Elenco<T> extends Iterable<T>{
	
	default public int size() {
		int c=0;
		for(T e:this) {
			c++;
		}
		return c;
	}
	default public void clear() {
		Iterator<T> it=this.iterator();
		while(it.hasNext()) {
			it.next();it.remove();
		}
		
		return;
	}
	public void add(T elem);
	default public void remove(T elem) {
		Iterator<T> it=this.iterator();
		while(it.hasNext()) {
			T x=it.next();
			if(x.equals(elem)) it.remove();
		}
	}
	default public boolean contains(T elem) {
		Iterator<T> it=this.iterator();
		while(it.hasNext()) {
			T x=it.next();
			if(x.equals(elem)) return true;
		}
		return false;
	}
	default public T get(T elem) {
		Iterator<T> it=this.iterator();
		T x=null;
		while(it.hasNext()) {
			T obj=it.next();
			if(obj.equals(elem)) x=obj;break;
		}
		return x;
	}
	
	
	public Comparator<T> getComparator();
	public void setComparatorAndSort(Comparator<T> c) ;

	
	
	

}
