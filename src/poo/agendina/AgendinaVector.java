package poo.agendina;
import poo.util.*;

public class AgendinaVector extends AgendinaAstratta{
	private Vector<Nominativo> tabella;
	public AgendinaVector(){
		this(100);
	}
	public AgendinaVector( int n ){
		if( n<=0 ) throw new IllegalArgumentException();
		tabella=new ArrayVector<Nominativo>(n);
	}
	
	@Override
	public int size(){ return tabella.size(); }
	
	@Override
	public void svuota(){ tabella.clear(); }
	
	@Override
    public void aggiungi( Nominativo n ){
		int i=Array.ricercaBinaria( tabella, n );
		if( i>=0 ){ tabella.set(i,n); return; }
		i=0;
		while( i<tabella.size() ){
			Nominativo x=(Nominativo)tabella.get(i);
			if( x.compareTo(n)>0 ) break;
			i++;
		}
		tabella.add(i,n); //inserisce n in posizione i
	}//aggiungi
	
	@Override
	public void rimuovi( Nominativo n ){
		int i=Array.ricercaBinaria( tabella, n ); 
		if( i<0 ) return;
		tabella.remove(i);
	}//rimuovi
	
	@Override
	public Nominativo cerca( Nominativo n ){
		int i=Array.ricercaBinaria( tabella, n ); 
		if( i<0 ) return null;
		return tabella.get(i);
	}//cerca
	
	@Override
	public java.util.Iterator<Nominativo> iterator(){
		return tabella.iterator();
	}//iterator

}//AgendinaVector

	//AgendinaVector extends AgendinaAstratta
/*	Vector<Nominativo> tabella;
 * 
 * 	public AgendinaVector(){
 *
 * 	tabella=ArrayVector<>(50); 
 * }
 * 	
 * public AgendinaVector(int n){
 * 	if(n<=0) throw new IllegalArgumnetException();
 * 	tabella=new ArrayVector<>(n);
 * 
 * 
 * }
 * 
 * public int size(){
 * 		tabella.size();
 * 	}
 * 
 * public void aggiungi (Nominativo n){
 * 		int i=ricercaBinaria(n,0,tabbella.size()-1)
 * 		if(i>-1) tabella.set(i,n) return;
 * 		Iterator<Nominativo> it=new iterator();
 * 		int i=-1;
 * 		while(it.hasNext())
 * 			Nominativo n2=it.next();i++;
 * 			if(n2.comparTo(n)>0) break;
 * 		tabella.aggiungi(i,n)
 * 
 * 
 * 
 * }
 * 
 * private int ricercaBinaria(Nominativo n,int inf,itn sup){
 * 	int med;
 * 	if(inf>sup)
 * 		return -1;
 * 	else
 * 		med=(sup+inf)/2;
 * 		if(tabella.array[med].equals(n) return i;
 * 		if(tabella.array[med].compareTo(n)<0
 * 					ricercaBinaria(n,med+1,sup);
 * 		else
 * 			ricercaBinaria(n,inf,med-1);
 * }
 * 
 * 	
 * 
 * 	
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
*/

