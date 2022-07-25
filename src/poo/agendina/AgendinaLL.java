package poo.agendina;
import java.util.*;

public class AgendinaLL extends AgendinaAstratta{
	private LinkedList<Nominativo> tabella=new LinkedList<>();
	
	public int size() { return tabella.size(); }
	public void svuota() { tabella.clear(); } 
		
	public Iterator<Nominativo> iterator(){ return tabella.iterator(); }
	
	public void aggiungi( Nominativo n ) {
		ListIterator<Nominativo> lit=tabella.listIterator();
		boolean flag=false;
		while( lit.hasNext() ) {
			Nominativo q=lit.next();
			if( q.equals(n) ) { lit.set(n); flag=true; }
			else if( q.compareTo(n)>0 ) {
				lit.previous();
				lit.add(n);
				flag=true;
			}
		}
		if( !flag ) lit.add(n);
	}
	
}
