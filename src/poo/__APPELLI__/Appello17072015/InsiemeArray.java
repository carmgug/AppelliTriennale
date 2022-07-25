package poo.__APPELLI__.Appello17072015;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class InsiemeArray<T> extends InsiemeAstratto<T> {

	
	T[] array;
	int size;
	
	public InsiemeArray(int c) {
		if(c<=0) throw new IllegalArgumentException();
		array=(T[]) new Object[c];
		size=0;
	}
	
	
	public InsiemeArray(Insieme<T> Set) {
		this(Set.size());
		Iterator<T> it=Set.iterator();
		int i=-1;
		while(it.hasNext()) {
			T elem=it.next();i++;
			array[i]=elem;size++;
		}
	}
	
	
	@Override
	public void add(T x) {
		if(this.contains(x)) return;
		if(size==array.length) {
			array=Arrays.copyOf(array, array.length*2);
		}
		array[size]=x;
		size++;
		
	}
	
	
	private class MyIterator implements Iterator<T>{
		private int cor=-1;
		private boolean flag=false;//rimuovibile
		@Override
		public boolean hasNext() {
			if(cor==-1) return size!=0;
			return cor<size-1;
		}

		@Override
		public T next() {
			if(!hasNext())throw new NoSuchElementException();
			cor++;
			flag=true;
			return array[cor];
		}
		
		public void remove() {
			if(!flag) throw new IllegalStateException();
			for(int i=cor+1;i<size;i++) {
				array[i-1]=array[i];
			}
			array[size]=null;
			size--;
			flag=false;
			
		}
		
	}

	@Override
	public Iterator<T> iterator() {
		return new MyIterator();
	}

}
