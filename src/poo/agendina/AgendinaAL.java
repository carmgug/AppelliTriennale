package poo.agendina;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class AgendinaAL extends AgendinaAstratta{
	private ArrayList<Nominativo> tabella;
	public AgendinaAL() {
		tabella=new ArrayList<>();
	}
	public AgendinaAL( int capacita ) {
		if( capacita<1 ) throw new IllegalArgumentException();
		tabella=new ArrayList<>(capacita);
	}
	
	@Override
	public int size() { return tabella.size(); }
	@Override
	public void svuota() { tabella.clear(); }
	
	public void aggiungi( Nominativo n ) {
		int i=Collections.binarySearch( tabella, n );
		if( i>=0 ) tabella.set(i,n);
		else {
			for( int j=0; j<tabella.size(); ++j ) {
				if( tabella.get(j).compareTo(n)>0 ) { tabella.add(j,n); return; }
			}
			tabella.add(n);
		}
	}
	public Iterator<Nominativo> iterator(){ return tabella.iterator(); }
}
