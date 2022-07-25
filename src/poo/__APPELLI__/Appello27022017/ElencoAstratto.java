package poo.__APPELLI__.Appello27022017;

import java.util.Iterator;

public abstract class ElencoAstratto<T> implements Elenco<T>{

	
	public String toString() {
		StringBuilder sb=new StringBuilder();
		Iterator<T> it=this.iterator();
		sb.append("[");
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
		Iterator<T> it=this.iterator();
		while(it.hasNext()) {
			h+=h*M+it.next().hashCode();
		}
		return h;
	}

	public boolean equals(Object o) {
		if(!(o instanceof Elenco)) return false;
		if(o==this) return true;
		Elenco<T> x=(Elenco<T>) o;
		if(x.size()!=this.size()) return false;
		Iterator<T> it=this.iterator(),it2=x.iterator();
		while(it.hasNext()) {
			T elem1=it.next(),elem2=it2.next();
			if(!elem1.equals(elem2)) return false;
			
		}
		return true;
	}
	
}
