package poo.__APPELLI__.Appello30012017;

import java.util.Comparator;
import java.util.Iterator;

public interface SortedSet<T> extends Iterable<T>{

	Comparator<T> getComparator();
	void setComparatorAndSort(Comparator<T> c);
	default int size() {
		int c=0;
		for(T x:this) c++;
		return c;
	}
	boolean add(T e);
	default boolean addAll(SortedSet<T> i) {
		if(i.size()==0) return false;
		boolean flag=false;
		for(T x:i) {
			if(!flag) flag=this.add(x); //basta che per solo un elemento sia true allora � modificata
			else this.add(x);
		}
		return flag;
	}
	default boolean remove(T e) {
		Iterator<T> it=iterator();
		boolean flag=false;
		while(it.hasNext()) {
			T x=it.next();
			if(x.equals(e)) {it.remove();flag=true;break;}
		}
		return flag;
	}
	default boolean removeAll(SortedSet<T> i) {
		boolean flag=false;
		for(T x:i) {
			if(!flag) flag=this.remove(x);
			else this.remove(x);
		}
		return flag;
	}
	default void clear() {
		Iterator<T> it=iterator();
		while(it.hasNext()) {
			it.next();it.remove();
		}
	}
	default boolean contains(T e) {
		for(T x:this) {
			if(x.equals(e)) return true;
		}
		return false;
	}
	default boolean retainAll(SortedSet<T> i) {
		Iterator<T> it=iterator();
		boolean flag=false;
		while(it.hasNext()) {
			T x=it.next();
			if(!i.contains(x)) {flag=true;it.remove();}
		}
		return flag;
	}
	
	

}//SortedSet
