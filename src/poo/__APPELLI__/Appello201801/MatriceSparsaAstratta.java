package poo.__APPELLI__.Appello201801;

import java.util.Iterator;

public abstract class MatriceSparsaAstratta implements MatriceSparsa {

	public String toString() {
		StringBuilder sb=new StringBuilder(1000);
		for(int i=0;i<getN();++i) {
			sb.append("[ ");
			Iterator<Elemento> it=riga(i);
			while(it.hasNext()) {
				Elemento e=it.next();
				if(it.hasNext()) sb.append(e.v+", ");
				else sb.append(e.v+" ]\n");
			}
		}
		
		return sb.toString();
	}
	
	
	public int hashCode() {
		final int M=43;
		int h=0;
		for(int i=0;i<getN();++i) {
			Iterator<Elemento> it=riga(i);
			while(it.hasNext()) {
				Elemento e=it.next();
				h+=h*M+e.hashCode();
			}
		}
		return h;
	}
	
	
	public boolean equals(Object o) {
		if(!(o instanceof MatriceSparsa)) return false;
		if(o==this) return false;
		MatriceSparsa M=(MatriceSparsa) o;
		if(M.getN()!=this.getN()) return false;
		for(int i=0;i<getN();i++) {
			Iterator<Elemento> it=riga(i),it2=M.riga(i);
			while(it.hasNext()) {
				Elemento e1=it.next(),e2=it2.next();
				if(!e1.equals(e2)) return false;
			}
		}
		
		return true;
	}
	
	
}
