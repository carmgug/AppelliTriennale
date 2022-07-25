package poo.__APPELLI__.Appello17072015;

import java.util.Iterator;
import java.util.NoSuchElementException;

public interface InsiemeOrdinato<T> extends Insieme<T> {
	
	default public T first() {
		if(size()==0) throw new NoSuchElementException();
		Iterator<T> it=iterator();
		return it.next();
		
	}
	
	default public T last() {
		if(size()==0) throw new NoSuchElementException();
		Iterator<T> it=iterator();
		T elem=null;
		while(it.hasNext()) {
			elem=it.next();
		}
		return elem;
		
	}
	public InsiemeOrdinato<T> headSet(T x);
	public InsiemeOrdinato<T> tailSet(T x);
	
	

}
