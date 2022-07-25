package poo.__APPELLI__.Appello17072015;

import java.util.Iterator;

public abstract class InsiemeOrdinatoAstratto<T extends Comparable<? super T>> implements InsiemeOrdinato<T>{

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
			h+=h*M+x.hashCode();
		}
		return h;
	}
	
	public boolean equal(Object o) {
		if(!(o instanceof Insieme)) return false;
		if(o==this) return true;
		Insieme<T> ins=(Insieme<T>) o;
		if(ins.size()!=this.size()) return false;
		Iterator<T> it=iterator(),it2=ins.iterator();
		while(it.hasNext()){
			T elem=it.next(),elem2=it2.next();
			if(!elem.equals(elem2)) return false;
		}
		return true;
	}

}
