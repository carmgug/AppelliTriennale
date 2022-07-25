package poo.__APPELLI__.AppelloAnonimoCopia;

import java.util.Iterator;

public abstract class ListaAstratta<T extends Comparable<? super T>> implements Lista<T> {

	
	
	public String toString() {
		StringBuilder sb=new StringBuilder(20);
		sb.append("[");
		Iterator<T> it=iterator();
		while(it.hasNext()) {
			sb.append(it.next());
			if(it.hasNext()) sb.append(", ");
		}
		sb.append("]");
		return sb.toString();
	}
	
	public int hashCode() {
		final int M=43;
		int h=0;
		for(T x:this) {
			h+=h+M*x.hashCode();
		}
		return h;
	}
	
	public boolean equals(Object o) {
		if(!(o instanceof Lista)) return false;
		if(o==this) return true;
		Lista<T> l=(Lista<T>)o;
		if(l.size()!=size()) return false;
		Iterator<T> it=iterator(),it2=l.iterator();
		while(it.hasNext()) {
			T elem1=it.next(),elem2=it.next();
			if(!elem1.equals(elem2)) return false;
		}
		return true;
	}
	
	
}
