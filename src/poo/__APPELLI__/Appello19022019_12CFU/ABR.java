package poo.__APPELLI__.Appello19022019_12CFU;

import java.util.Iterator;


import java.util.Stack;

import poo.util.CollezioneOrdinata;
import poo.util.List;

public class ABR<T extends Comparable<? super T>> implements CollezioneOrdinata<T>{
	private static class Nodo<E>{
		E info;
		Nodo<E> fS, fD;
	}
	private Nodo<T> radice=null;
	
	public int size() {
		return size(radice);
	}//size
	private int size( Nodo<T> radice ) {
		if( radice==null ) return 0;
		return 1+size( radice.fS )+size( radice.fD );
	}//size
	
	public void clear() {
		radice=null;
	}//clear
	public boolean contains( T x ) {
		return contains(radice,x);
	}//contains
	private boolean contains( Nodo<T> radice, T e ) {
		if( radice==null ) return false;
		if( radice.info.equals(e) ) return true;
		if( radice.info.compareTo(e)>0 ) return contains( radice.fS, e );
		return contains( radice.fD, e );
	}//contains
	
	public void add( T x ) {
		radice=add(radice,x);
	}//add
	private Nodo<T> add( Nodo<T> radice, T x ){
		if( radice==null ) {
			Nodo<T> n=new Nodo<>();
			n.info=x; n.fS=null; n.fD=null;
			return n;
		}
		if( radice.info.compareTo(x)>0 ) {
			radice.fS=add( radice.fS, x );
			return radice;
		}
		radice.fD=add( radice.fD, x );
		return radice;
	}//add
	
	public void inOrder( List<T> lis ) {
		inOrder( radice, lis );
	}//inOrder
	private void inOrder( Nodo<T> radice, List<T> lis ) {
		if( radice!=null ) {
			inOrder( radice.fS, lis );
			lis.addLast( radice.info ); //visita la radice
			inOrder( radice.fD, lis );
		}
	}//inOrder
	
	public void preOrder( List<T> lis ) {//visita in ordine anticipato
		preOrder(radice,lis);
	}
	private void preOrder( Nodo<T> radice, List<T> lis ) {
		if( radice!=null ) {
			lis.addLast(radice.info); //prima la radice
			preOrder( radice.fS, lis );
			preOrder( radice.fD, lis );
		}
	}
	
	public void postOrder( List<T> lis ) {//visita in ordine posticipato
		postOrder( radice, lis );
	}
	private void postOrder( Nodo<T> radice, List<T> lis ) {
		if( radice!=null ) {
			postOrder( radice.fS, lis );
			postOrder( radice.fD, lis );
			lis.addLast( radice.info );
		}
	}
	
	public void remove( T x ) {
		radice=remove( radice, x );
	}//remove
	private Nodo<T> remove( Nodo<T> radice, T x ){
		if( radice==null ) return null;
		if( radice.info.compareTo(x)>0 ) {
			radice.fS=remove( radice.fS,x );
			return radice;
		}
		if( radice.info.compareTo(x)<0 ) {
			radice.fD=remove( radice.fD, x );
			return radice;
		}
		//l'elemento � trovato in radice.info
		if( radice.fS==null && radice.fD==null ) {//nodo foglia
			return null;
		}
		if( radice.fS==null ) {//nodo col solo figlio destro
			return radice.fD;
		}
		if( radice.fD==null ) { //nodo col solo figlio sinistro
			return radice.fS;
		}
		//nodo puntato da radice, ha entrambi i figli
		if( radice.fD.fS==null ) {//1 sotto caso: la radice destra e' il minimo
			//promozione
			radice.info=radice.fD.info;
			//rimozione "vittima"
			radice.fD=radice.fD.fD; //fatto il bypass
			return radice;
		}
		//2 sotto caso pi� generale
		Nodo<T> padre=radice.fD; //radice del sotto albero destro
		Nodo<T> figlio=padre.fS;
		while( figlio.fS!=null ) {
			padre=figlio; figlio=figlio.fS;
		}
		//figlio punta alla vittima
		//promozione
		radice.info=figlio.info;
		//eliminare il nodo figlio, che al pi� ha un solo figlio
		padre.fS=figlio.fD; //bypass
		return radice;
	}//remove
	
	public T get( T x ) {
		return get( radice,x );
	}//get
	private T get( Nodo<T> radice, T e ) {
		if( radice==null ) return null;
		if( radice.info.equals(e) ) return radice.info;
		if( radice.info.compareTo(e)>0 ) return get( radice.fS, e );
		return get( radice.fD, e );
	}//get
	
	public boolean isEmpty() { return radice==null; }
	public boolean isFull() { return false; }
	
	public Iterator<T> iterator(){ return new ABRIterator(); }
	
	private class ABRIterator implements Iterator<T>{
		private Stack<Nodo<T>> Stack=new Stack<>();
		private Nodo<T> nc=null;
		public ABRIterator() {
			BuildStack(radice);
		}
		
		
		private void BuildStack(Nodo<T> radice) {
			Stack.push(radice);
			BuildStack(radice.fS);
			
		}
		
		
		@Override
		public boolean hasNext() {
			return !Stack.isEmpty();
		}

		@Override
		public T next() {
			if(!hasNext()) throw new IllegalStateException();
			
			nc=Stack.pop();
			
			if(nc.fD!=null) {
				BuildStack(radice.fD);
			}
			
			return nc.info;
		}
		
		public void remove() {
			if (nc==null) throw new NullPointerException();
			
			T rimuovere=nc.info;
			ABR.this.remove(rimuovere);
			Stack.clear();
			BuildStack(radice,rimuovere);
			nc=null;
		}
		
		private void BuildStack(Nodo<T> radice, T rimuovere) {
			if(radice.info.compareTo(rimuovere)<0) {
				BuildStack(radice.fD,rimuovere);
			}
			else {
				BuildStack(radice); //devo trovare la radice non ancora visitata quindi che contine un info maggiore o uguale all'info rimossa
				//non sono accettati duplicati;
			}
		}
		
		
		
	}//ABRIterator
	
	
	public void remove2(T info) {
		radice=remove2(radice,info);
	}
	
	private Nodo<T> remove2(Nodo<T> radice,T info) {
		if(radice==null) return null;
		if(radice.info.compareTo(info)>0) {
			radice.fS=remove2(radice.fS,info);
			return radice;
		}
		if(radice.info.compareTo(info)<0) {
			radice.fD=remove2(radice.fD,info);
		}
		//ho trovato al radice
		if(radice.fD==null && radice.fS==null) {
			return null;
		}
		if(radice.fD!=null) return radice.fS;
		if(radice.fS!=null) return radice.fD;
		
		//il nodo trovato ha due figli 2 casi
		if(radice.fD.fS==null) { //il figlio destro non ha figlio sinsitro
			radice.info=radice.fD.info;
			radice.fD=radice.fD.fD;
			return radice;
		}
		//2 caso dobbiamo trovare il minimo
		Nodo<T> padre=radice.fD;
		Nodo<T> figlio=padre.fS;
		while(figlio.fS!=null) {
			padre=figlio;
			figlio=padre.fS;
		}
		radice.info=figlio.info;
		padre.fS=figlio.fD;//al p� il figlio ha solo il figlio destro;
		
		
		return radice;
	}
	
	
	
	public String toString() {
		StringBuilder sb=new StringBuilder(100);
		sb.append('[');
		Iterator<T> it=this.iterator();
		while( it.hasNext() ) {
			sb.append( it.next() );
			if( it.hasNext() ) sb.append(", ");
		}
		sb.append(']');
		return sb.toString();
	}//toString
	
	public boolean equals( Object x ) {
		if( !(x instanceof ABR) ) return false;
		if( x==this ) return true;
		ABR<T> abr=(ABR<T>)x;
		return equals( this.radice, abr.radice );
	}//equals
	
	private boolean equals( Nodo<T> r1, Nodo<T> r2 ) {
		if( r1==null && r2==null ) return true;
		if( r1==null || r2==null ) return false;
		if( !r1.info.equals(r2.info) ) return false;
		return equals( r1.fS, r2.fS ) && equals( r1.fD, r2.fD );
	}//equals
	
	public int hashCode() {
		return toString().hashCode();
	}//hashCode
	
	public ABR<T> copy(){
		ABR<T> copia=new ABR<T>();
		copia.radice=copy(this.radice, copia.radice);
		
		return copia;
	}//copy
	
	private Nodo<T> copy(Nodo<T> radice,Nodo<T> copia){
		if(radice==null ) return null;
		if(copia==null) copia=new Nodo<T>();
		copia.info=radice.info;
		//costruiamo il sotto-albeor detsro;
		copia.fD=copy(radice.fD,copia.fD);
		//Costruiamo il sotto-albero sinistro;
		copia.fS=copy(radice.fS,copia.fS);
		
		return copia;
		
	}
	
	
	public void build( T[] a ) {
		clear();
		this.radice=new Nodo<T>();
		this.radice=build(a,0,a.length-1,this.radice);
		
		
	}//build
	
	private Nodo<T> build(T[] a, int inizio,int fine,Nodo<T> nc) {
		
		int mid;
		
		if(inizio>fine)
			return null;
		else {
			
			mid=(inizio+fine)/2;
			nc.info=a[mid];
			
			nc.fD=new Nodo<T>();
			nc.fD=build(a,inizio,mid-1,nc.fD);
			
			nc.fS=new Nodo<T>();
			nc.fS=build(a,mid+1,fine,nc.fS);
			
			return radice;
		}
	}
	
	public boolean bilanciato() {
		return bilanciato(radice);
		
	}
	
	private  boolean bilanciato(Nodo<T> radice) {
		if(radice==null) return true; //fd=0 //fs==0 |fs-fd|=0
		int cfd=size(radice.fD);
		int cfs=size(radice.fS);
		if(Math.abs(cfd-cfs)>1) return false;
		return bilanciato(radice.fD) && bilanciato(radice.fS);
	}
	public int altezza() {
		return 0; //TODO
	}//altezza
	
	public boolean equals1(Object x) {
		if(!(x instanceof ABR)) return false;
		if(x==this) return true;
		@SuppressWarnings("unchecked")
		ABR<T> albero=(ABR<T>) x;
		return equals1(this.radice,albero.radice);
		
		
	}
	
	public boolean equals1(Nodo<T> Radice1,Nodo<T> Radice2) {
		if(Radice1==null && Radice2==null) return true;
		if(Radice1==null || Radice2==null) return false;
		
		if(!Radice1.info.equals(Radice2.info)) return false; 
		
		
		return equals1(Radice1.fD,Radice2.fD) && equals1(Radice1.fS,Radice2.fS);
	}
	
	
	
	
}//ABR

