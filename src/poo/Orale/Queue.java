package poo.Orale;

import java.util.Iterator;

public interface Queue<T> extends Iterable<T> {
	
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
	default public void offer(T x) {
		add(x);
	}
	default public T pull() {
		return remove();
	}
	public void add(T x);
	public T remove();
	public T peek();
	
	default public boolean contains(T x) {
		Iterator<T> it=iterator();
		while(it.hasNext()) {
			if(it.next().equals(x)) return true;
		}
		return false;
	}
}
