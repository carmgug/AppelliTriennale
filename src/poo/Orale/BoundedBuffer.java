package poo.Orale;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class BoundedBuffer<T> extends AbstractQueue<T> {

	private T[] array;
	private int in,out,size;
	//contatore modulare
	
	@SuppressWarnings("unchecked")
	public BoundedBuffer(int c) {
		if(c<=0) throw new IllegalArgumentException();
		array=(T[])new Object[c];
		in=out=size=0;
		
		
	}
	
	
	
	@Override
	public void add(T x) {
		if(size==array.length) throw new RuntimeException("Queue is Full!!");
		array[in]=x;
		in=(in+1)%array.length;
		size++;
	}

	@Override
	public T remove() {
		if(size==0) throw new NoSuchElementException();
		T elem=array[out];
		out=(out+1)%array.length;
		size--;
		return elem;
	}

	@Override
	public T peek() {
		if(size==0) throw new NoSuchElementException();
		return array[out];
	}

	@Override
	public Iterator<T> iterator() {
		return new MyIterator();
	}
	

	private class MyIterator implements Iterator<T>{
		private int cor=-1;
		private boolean flag=false;
		@Override
		public boolean hasNext() {
			if(cor==-1) return size>0;
			return (cor+1)%array.length!=in;
		}

		@Override
		public T next() {
			if(!hasNext())throw new NoSuchElementException();
			if(cor==-1) cor=out;
			else cor=(cor+1)%array.length;
			flag=true;
			return array[cor];
		}
		
		public void remove() {
			if(!flag) throw new IllegalStateException();
			int j=(cor+1)%array.length;
			while(j!=in) {
				array[(j-1-array.length)%array.length]=array[j];
				j=(1+j)%array.length;
			}
			array[j]=null;
			cor=(cor-1-array.length)%array.length;
			flag=false;
			size--;
			
		}
		
	}
	
	
}
