package poo.__APPELLI__.Appello201801;

import java.util.Iterator;

public interface MatriceSparsa {
	
	int getN();
	default void clear() {
	}
	default double grado() {
		int n=getN();
		int numerinulli=0;
		for(int i=0;i<n;i++) {
			Iterator<Elemento> it=riga(i);
			while(it.hasNext()) {
				if(it.next().v==0) numerinulli++;
			}
			Iterator<Elemento> it2=colonna(i);
			while(it2.hasNext()) {
				if(it2.next().v==0) numerinulli++;
			}
		}
		int valori=n-numerinulli;
		
		return (valori/n)*100.0D;
	}
	default int get(int i,int j) {
		if(i<0 || j<0 || i>=getN() || j>=getN()) throw new IndexOutOfBoundsException();
		Iterator<Elemento> it=riga(i);
		int k=-1;
		Elemento elemento=null;
		while(it.hasNext() && k!=j) {
			k++;elemento=it.next(); 
		}
		return elemento.v;
	}
	default void set(int i,int j,int v) {
		if(i<0 || j<0 || i>=getN() || j>=getN()) throw new IndexOutOfBoundsException();
		Iterator<Elemento> it=riga(i);
		int k=-1;
		Elemento elemento=null;
		while(it.hasNext() && k!=j) {
			k++;elemento=it.next(); 
		}
		elemento.v=v;
		
	}
	default void set(Elemento e) {
		if(e.i<0 || e.j<0 || e.i>=getN() || e.j>=getN()) throw new IndexOutOfBoundsException();

		Iterator<Elemento> it=riga(e.i);
		int k=-1;
		Elemento elemento=null;
		while(it.hasNext() && k!=e.j) {
			k++;elemento=it.next(); 
		}
		elemento=e;
	}
	
	default boolean rigaVuota(int i) {
		if(i<0 || i>=getN()) throw new IndexOutOfBoundsException();

		Iterator<Elemento> it=riga(i);
		while(it.hasNext()) {
			Elemento e=it.next();
			if(e.v!=0) return false;
		}
		return true;		
	}
	default boolean colonnaVuota(int j){
		if(j<0 || j>=getN()) throw new IndexOutOfBoundsException();

		Iterator<Elemento> it=colonna(j);
		while(it.hasNext()) {
			Elemento e=it.next();
			if(e.v!=0) return false;
		}
		return true;
	}
	default int sizeRiga(int i) {
		if(i<0 || i>=getN()) throw new IndexOutOfBoundsException();
		int k=0;
		Iterator<Elemento> it=riga(i);
		while(it.hasNext()) {
			it.next();k++;
		}
		return k;
	}
	default int sizeColonna(int j) {
		if(j<0 || j>=getN()) throw new IndexOutOfBoundsException();
		int k=0;
		Iterator<Elemento> it=colonna(j);
		while(it.hasNext()) {
			it.next();k++;
		}
		return k;
	}
	Iterator<Elemento> riga(int i);
	Iterator<Elemento> colonna(int j);
	MatriceSparsa crea();
	default MatriceSparsa add(MatriceSparsa m) {
		if(m.getN()!=this.getN()) throw new IllegalArgumentException();
		MatriceSparsa res=crea();
		for(int i=0;i<getN();++i) {
			Iterator<Elemento> it=riga(i);
			Iterator<Elemento> it2=m.riga(i);
			while(it.hasNext()) {
				Elemento e=it.next();
				Elemento e2=it2.next();
				int v=e.v+e2.v;
				res.set(e.i,e.j,v);
			}
		}
		return res;
		
		
	}
	default MatriceSparsa mul(MatriceSparsa m) {
		if(getN()!=m.getN())throw new IllegalArgumentException();
		MatriceSparsa res=crea();
		for(int i=0;i<getN();++i) {
			for(int j=0;j<getN();++j) {
				for(int k=0;k<getN();++j) {
					int v=this.get(i, k)+m.get(k, j);
					v+=res.get(i, j);res.set(i,j,v);
				}
			}
		}
		
		
		return res;
		
	}
	default boolean simmetrica() {
		boolean simmetrica=true;
		loop1: for(int i=0;i<getN();++i) {
			Iterator<Elemento> it=riga(i);
			while(it.hasNext()) {
				Elemento e=it.next();
				if(e.i==e.j) continue;
				if(get(e.i,e.j)!=get(e.j,e.i)) {simmetrica=false;break loop1;}
			}
		}
		return simmetrica;
	}
	
}
class Elemento{
	
	
	int i;
	int j;
	int v;
	
}