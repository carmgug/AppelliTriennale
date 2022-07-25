package poo.util;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class BoundedBuffer<T> extends AbstractQueue<T> {
	private T[] buffer;
	private int in, out, size;
	
	@SuppressWarnings("unchecked")
	public BoundedBuffer( int n ) {
		if( n<1 ) throw new IllegalArgumentException();
		buffer=(T[])new Object[n];
		in=0; out=0; size=0;
	}
	
	public int size() { return size; }
	
	public boolean isFull() { return size==buffer.length; }
	
	public void clear() {
		for( int i=out; i!=in; i=(i+1)%buffer.length )
			buffer[i]=null;
		in=0; out=0; size=0;
	}
	
	public void add( T e ) {
		if( isFull() ) throw new RuntimeException("Queue is Full!!!");
		buffer[in]=e;
		in=(in+1)%buffer.length;
		size++;
	}
	public T remove() {
		if( size==0 ) throw new RuntimeException("Queue is Empty!!!");
		T x=buffer[out];
		out=(out+1)%buffer.length;
		size--;
		return x;
	}
	public T peek() {
		if( size==0 ) throw new RuntimeException("Queue is Empty!!!");
		return buffer[out];		
	}
	public Iterator<T> iterator(){ return new BoundedBufferIterator(); }
	
	public class BoundedBufferIterator implements Iterator<T>{
		//si ignora la ConcurrentModificationException
		// i=(i+1)%n; i=(i-1+n)%n;
		private int cor=-1;
		private boolean rimuovibile=false;
		public BoundedBufferIterator() {
			//prendi contatore dall'oggetto outer e copialo qui
		}
		public boolean hasNext() {
			if( cor==-1 ) return size>0;
			return (cor+1)%buffer.length!=in;
		}//hasNext
		public T next() {
			if( !hasNext() ) throw new NoSuchElementException();
			if( cor==-1 ) cor=out;
			else cor=(cor+1)%buffer.length;
			rimuovibile=true;
			return buffer[cor];
		}//next
		public void remove() { 
			if( !rimuovibile ) throw new IllegalStateException();
			rimuovibile=false;
			int j=(cor+1)%buffer.length;
			while( j!=in ) { //left shift
				buffer[(j-1+buffer.length)%buffer.length]=buffer[j];
				j=(j+1)%buffer.length;
			}
			size--;
			cor=(cor-1+buffer.length)%buffer.length; //arretra cor
			in=(in-1+buffer.length)%buffer.length; //arretra in
			buffer[in]=null;
		}//remove
	}//BoundedBufferIterator
	
}//BoundedBuffer
