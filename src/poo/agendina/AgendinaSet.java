package poo.agendina;
import java.util.*;
	
public class AgendinaSet extends AgendinaAstratta{
	private Set<Nominativo> tabella=new TreeSet<>();
	
	public int size() { return tabella.size(); }
	
	public void svuota() { tabella.clear(); }
	
	public Iterator<Nominativo> iterator(){ return tabella.iterator(); }
	
	public void aggiungi( Nominativo n ) {
		tabella.remove(n);
		tabella.add( n );
	}
}
