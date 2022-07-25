package poo.__APPELLI__.Appello27022017;

import java.util.Comparator;
import java.util.Iterator;

public class Stanza {
	private String numero;
	private Elenco<Impiegato> elencoimpiegati;
	
	public Stanza(String numero,Comparator<Impiegato> c) {
		this.numero=numero;
		elencoimpiegati=new ElencoLinkato<Impiegato>(c);
	}
	
	public void addImpiegato(Impiegato x) {
		elencoimpiegati.add(x);
		elencoimpiegati.setComparatorAndSort(elencoimpiegati.getComparator());
	}
	public void removeImpiegato(Impiegato x) {
		elencoimpiegati.remove(x);
	}
	
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((elencoimpiegati == null) ? 0 : elencoimpiegati.hashCode());
		result = prime * result + ((numero == null) ? 0 : numero.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object o) {
		if(!(o instanceof Stanza)) return false;
		Stanza other = (Stanza) o;
		if(!this.numero.equals(other.numero)) return false;
		return true;
	}
	
	public String toString() {
		StringBuilder sb=new StringBuilder();
		sb.append("N.S: "+numero);
		sb.append("; Impiegati -> ");
		Iterator<Impiegato> it=elencoimpiegati.iterator();
		while(it.hasNext()) {
			sb.append(it.next());
			if(it.hasNext()) sb.append(", ");
		}
		sb.append(";\n");
		return sb.toString();
		
		
		
	}
	
	
	
}
