package poo.__APPELLI__.Appello10112017;

import java.util.LinkedList;

public class MatriceSparsaLinked extends MatriceSparsaAstratta {

	private class ElementoRiga{
		
		final int i;
		LinkedList<ElementoColonna> riga;
		
		private ElementoRiga(int i){
			if(i<0) throw new IllegalArgumentException();
			this.i=i;
			riga=new LinkedList<>();
		}
		private void aggiungiElemento(ElementoColonna e) {
			if(riga.contains(e)) riga.remove(e);
			riga.add(e);
			
		}
		
		private int getIndice(){
			return i;
		}
		private LinkedList<ElementoColonna> getRiga() {
			LinkedList<ElementoColonna> copia=new LinkedList<>();
			for(ElementoColonna colonna:riga) {
				ElementoColonna nuovo=new ElementoColonna(colonna);
				copia.add(nuovo);
			}
			return copia;
		}
	}
	
	 private class ElementoColonna{
		private int j;
		private int v;
		
		private ElementoColonna(int j,int v){
			if(v==0) throw new IllegalArgumentException();
			this.j=j;
			this.v=v;
		}
		
		private ElementoColonna(ElementoColonna e) {
			this.j=e.j;
			this.v=e.v;
		}
		
		
		private int getIndice(){
			return j;
		}
		
		private int getValore() {
			return v;
		}
		
		private void setValore(int v) {
			if(v==0) throw new IllegalArgumentException();
		}
		
		 public boolean equals (Object o) {
			if(!(o instanceof ElementoColonna)) return false;
			if(o==this) return true;
			ElementoColonna e=(ElementoColonna) o;
			if(e.j==this.j) return true; //cos� da avere facilitazione per il contains
			return false;
		}
		
	}
	
	
	private LinkedList<ElementoRiga> listaF;
	private int N;
	
	
	public MatriceSparsaLinked(int n) {
		if(n<=0) throw new IllegalArgumentException();
		LinkedList<ElementoRiga> listaF=new LinkedList<>();
		for(int i=0;i<n;i++) {
			ElementoRiga k=new ElementoRiga(i);
			listaF.add(k);
		}
		
		
		
		
	}
	
	
	
	@Override
	public int getN() {
		return N;
	}

	@Override
	public void clear() {
		LinkedList<ElementoRiga> listaF=new LinkedList<>();
	}

	@Override
	public int get(int i, int j) {
		if(i<0 || i>=N || j<0 || j>=N ) throw new IllegalArgumentException();
		ElementoRiga r=listaF.get(i);
		if(r.riga.size()==0) return 0;
		
		for(ElementoColonna c:r.riga) {
			if(c.j==j) return c.v;
		}
		
		return 0;
		
	}

	@Override
	public void set(int i, int j, int v) {
		if(i<0 || i>=N || j<0 || j>=N ) throw new IllegalArgumentException();
		ElementoRiga r=listaF.get(i);
		ElementoColonna c=new ElementoColonna(j,v);
		r.aggiungiElemento(c);
	}

	@Override
	public MatriceSparsa crea() {
		MatriceSparsa nuova=new MatriceSparsaLinked(N);
		return nuova;
	}

	
}
