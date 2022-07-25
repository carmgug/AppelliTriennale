package poo.agendina;
import java.util.*;
public class AgendinaMap extends AgendinaAstratta{
	Map<Nominativo,Nominativo> tabella=new TreeMap<>();
	
	public int size() { return tabella.size(); }
	
	public void aggiungi( Nominativo n ) {
		tabella.put(n,n); //eventualmente realizza un aggiornamento del valore
	}//aggiungi
	
	public Iterator<Nominativo> iterator(){
		return tabella.values().iterator();
	}//iterator
	
	public void remove( Nominativo n ) {
		tabella.remove(n);
	}//remove
	
	public Nominativo cerca( Nominativo n ) {
		return tabella.get(n);
	}//cerca
	
}//AgendinaMap
