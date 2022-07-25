package poo.__APPELLI__.Appello30012017;
import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;
public class SortedSetFrameWorkJava<T> extends AbstractSortedSet<T> {

	
	
	TreeSet<T> SortedSet;
	
	public SortedSetFrameWorkJava(Comparator<T> c) {
		SortedSet=new TreeSet<>(c);
	}
	
	
	public int size() {
		return SortedSet.size();
	}
	
	@Override
	public Comparator<T> getComparator() {
		@SuppressWarnings("unchecked")
		Comparator<T> comparator = (Comparator<T>) SortedSet.comparator();
		return comparator;
	}

	@Override
	public void setComparatorAndSort(Comparator<T> c) {
		TreeSet<T> tmp=new TreeSet<>(c);
		for(T x:SortedSet) {
			tmp.add(x);
		}
		SortedSet=tmp;
		
	}

	@Override
	public boolean add(T e) {
		return SortedSet.add(e);
	}

	@Override
	public Iterator<T> iterator() {
		return SortedSet.iterator();
	}

}
