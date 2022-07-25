package poo.Orale;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class Heap<T extends Comparable<? super T>> {
	
	
	private T[]	heap;
	private int size,n;
	
	
	public Heap(int c) {
		if(c<=0) throw new IllegalArgumentException();
		heap=(T[])new Object[c+1];
		size=0;n=c;
	}
	
	public Heap() {this(20);}
	
	
	public int size() {
		return size;
	}
	
	public void clear() {
		heap=(T[])new Object[heap.length];
		size=0;
	}
	
	public void add(T x) {
		if(size==n) {
			heap=Arrays.copyOf(heap, n*2);
			n=n*2;
		}
		size++;
		heap[size]=x;
		//nodo è minore di tutti i suoi discendenti NON MAGGIORE
		int i=size;
		while(i>1) {
			if(heap[i/2].compareTo(heap[i])>0) {
				T park=heap[i/2];
				heap[i/2]=heap[i];
				heap[i]=park;
				i=i/2;
			}
			break;
		}
		
	}
	
	
	public T remove() {
		if(size==0 ) throw new NoSuchElementException();
		T r=heap[1];
		heap[1]=heap[size];
		heap[size]=null;size--;
		int i=1;
		while(i<=size/2) {
			int j=i*2,k=i*2+1;
			int z=j;
			if(heap[k]!=null && heap[z].compareTo(heap[k])>0) z=k;
			if(heap[z].compareTo(heap[i])<0) {
				T park=heap[z];
				heap[z]=heap[i];
				heap[i]=heap[z];
				i=z;
			}
			break;
		}
		
		
		
		return r;
	}
	
	public void remove(T x) {
		if(size==0 ) throw new NoSuchElementException();
		int i=0;
		for(int j=1;j<=size;j++) {
			if(heap[j].compareTo(x)==0) {i=j; break;}
		}
		if(i==0) return;
		heap[i]=null;
		int limite=size;
		size=i-1;
		int j=i+1;
		for( ;j<=limite;j++) {
			T park=heap[j];heap[j]=null;
			add(park);
		}
	}
	

}
