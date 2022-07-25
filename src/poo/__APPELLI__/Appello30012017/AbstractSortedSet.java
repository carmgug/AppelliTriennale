package poo.__APPELLI__.Appello30012017;

import java.util.Iterator;

public abstract class AbstractSortedSet<T> implements SortedSet<T> {

	public String toString() {
		StringBuilder sb=new StringBuilder(20);
		sb.append("[");
		Iterator<T> it=iterator();
		while(it.hasNext()) {
			sb.append(it.next());
			if(it.hasNext())sb.append(", ");
		}
		sb.append("]");
		return sb.toString();
	}
	
	public int hashCode() {
		final int M=43;
		int h=0;
		for(T x:this) {
			h+=h*M+x.hashCode();
		}
		
		return h;
		
	}

	
	public boolean equals(Object o) {
		if(!(o instanceof SortedSet)) return false;
		if(o==this) return true;
		SortedSet Set=(SortedSet)o;
		if(size()!=Set.size()) return false;
		Iterator<T> it=iterator(),it2=Set.iterator();
		while(it.hasNext()) {
			T x1=it.next(),x2=it2.next();
			if(!x1.equals(x2)) return false;
		}
		return true;
	}
}
