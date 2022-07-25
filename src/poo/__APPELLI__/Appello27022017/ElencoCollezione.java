package poo.__APPELLI__.Appello27022017;

import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;

public class ElencoCollezione<T> extends ElencoAstratto<T> {

	
	
	
	private TreeSet<T> elenco;
	Comparator<T> c;
	
	
	public ElencoCollezione(Comparator<T> c) {
		this.c=c;
		elenco=new TreeSet<>(c);
	}
	
	
	public int size() {
		return elenco.size();
	}
	
	
	@Override
	public void add(T elem) {
		elenco.add(elem);
	}

	@Override
	public Comparator<T> getComparator() {
		return c;
	}

	@Override
	public void setComparatorAndSort(Comparator<T> c) {
		this.c=c;
	}

	@Override
	public Iterator<T> iterator() {
		return elenco.iterator();
	}

	
	
	
	
	
	
}
