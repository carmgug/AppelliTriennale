package poo.__APPELLI__.Appello2501_2019;

import java.util.Iterator;

public abstract class AbstractList<T> implements List<T> {
	
	public String toString() {
		StringBuilder sb=new StringBuilder(20);
		Iterator<T> it=iterator();
		
		sb.append("[");
		while(it.hasNext()) {
			T elem=it.next();
			if(it.hasNext()) sb.append(elem+", ");
			else sb.append(elem);
		}
		sb.append("]");
		return sb.toString();
	}
	
	
	public boolean equals(Object o) {
		if(!(o instanceof List)) return false;
		if(o==this) return true;
		List<T> l=(List<T>) o;
		if(l.size()!=this.size()) return false;
		Iterator<T> it=iterator();
		Iterator<T> it2=l.iterator();
		while(it.hasNext()) {
			T elem1= it.next();
			T elem2=it2.next();
			if(!elem1.equals(elem2)) {
				return false;
			}
			
		}
		return true;
	}
	
	
	public int hashCode() {
		final int M=7;
		int h=0;
		
		for(T elem:this) {
			h+=h*M+elem.hashCode();
		}
		return h;
	}
	//1:18m

}


