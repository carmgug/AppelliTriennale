package poo.util;

import java.util.Iterator;

public class ABR<T extends Comparable<? super T>> implements CollezioneOrdinata<T>{
	private static class Nodo<E>{
		E info;
		Nodo<E> fS, fD;
	}
	
	public enum OP{Visita,Leggi}
	
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
	
	
	public void InOrder() {
		class AreaDati{
			Nodo<T> radice;
			OP modo;
			public AreaDati(Nodo<T> radice,OP modo) {this.radice=radice;this.modo=modo;}
		}//AreaDati;
		
		Stack<AreaDati> stack=new StackArray<>();
		AreaDati a=new AreaDati(radice,OP.Visita);
		stack.push(a);
		while(!stack.isEmpty()) {
			AreaDati n=stack.pop();
			if(n.modo==OP.Leggi) {
				System.out.print(n.radice.info+" ");
			}
			else {
				if(n.radice!=null) {
					stack.push(new AreaDati(n.radice.fD,OP.Visita));
					stack.push(new AreaDati(n.radice,OP.Leggi));
					stack.push(new AreaDati(n.radice.fS,OP.Visita));
				}
			}
			
			
		}
	}
	
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
		//l'elemento è trovato in radice.info
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
		//2 sotto caso più generale
		Nodo<T> padre=radice.fD; //radice del sotto albero destro
		Nodo<T> figlio=padre.fS;
		while( figlio.fS!=null ) {
			padre=figlio; figlio=figlio.fS;
		}
		//figlio punta alla vittima
		//promozione
		radice.info=figlio.info;
		//eliminare il nodo figlio, che al più ha un solo figlio
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
		private LinkedList<T> l=new LinkedList<>();
		private Iterator<T> it=null;
		private T cor;
		public ABRIterator() { 
			inOrder(l); 
			it=l.iterator();
		}
		public boolean hasNext() { return it.hasNext(); }
		public T next() {
			cor=it.next(); //può sollevare NoSuchElementException
			return cor; 
		}
		public void remove() {
			it.remove(); //può sollevare IllegalStateException
			ABR.this.remove(cor); //la remove va fatta anche sull'albero...
			cor=null;
		}//remove
	}//ABRIterator
	
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
		return null; //TODO ma non usare l'aliasing!!!
	}//copy
	
	public void build( T[] a ) {
		clear();
		//crea l'albero a partire dal contenuto di a, supposto ordinato per valori crescenti
		//ci si aspetta che l'albero costruito sia bilanciato
	}//build
	
	public int altezza() {
		return altezza(radice);
	}//altezza
	
	private int altezza(Nodo<T> radice) {
		if(radice==null) return 0;
		int h1=0,h2=0;
		if(radice.fD!=null) h1=1+altezza(radice.fD);
		if(radice.fS!=null) h2=1+altezza(radice.fS);
		if(h1>h2) return h1;
		else return h2;
		
	}
	
	
	public boolean bilanciato() {
		return bilanciato( radice );
	}//bilanciato
	private boolean bilanciato( Nodo<T> radice ) {
		if( radice==null ) return true;
		int s1=size(radice.fS);
		int s2=size(radice.fD);
		if( Math.abs(s1-s2)>1 ) return false;
		return bilanciato(radice.fS) && bilanciato(radice.fD);
	}//bilanciato
	
	public void visitaPerLivelli( List<T> visitati ) {
		if( radice==null ) return;
		LinkedList<Nodo<T>> coda=new LinkedList<>();
		coda.addLast( radice );
		while( !coda.isEmpty() ) {
			Nodo<T> n=coda.removeFirst();
			//visita n
			visitati.addLast(n.info);
			if( n.fS!=null ) coda.addLast( n.fS );
			if( n.fD!=null ) coda.addLast( n.fD );
		}
		
	}//visitaPerLivelli
	
	public static void main( String[] args ) {
		ABR<Integer> abr=new ABR<>();
		abr.add(12); abr.add(34); abr.add(-2); abr.add(-4); abr.add(5); abr.add(1); abr.add(7);
		abr.add(38); abr.add(-6); abr.add(8);
		System.out.println(abr);
		List<Integer> lista=new LinkedList<>(); //lista vuota
		abr.inOrder(lista);
		System.out.println("inOrder="+lista);
		lista.clear();
		abr.InOrder();
		System.out.println();
		
		abr.preOrder(lista);
		System.out.println("preOrder="+lista);
		lista.clear();
		abr.postOrder(lista);
		System.out.println("postOrder="+lista);
		lista.clear();
		abr.visitaPerLivelli(lista);
		System.out.println("visita per livelli="+lista);
		System.out.println(abr.altezza());
	}
	
}//ABR
