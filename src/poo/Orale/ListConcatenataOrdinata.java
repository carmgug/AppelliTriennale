package poo.Orale;

import java.util.Iterator;
import java.util.NoSuchElementException;

import poo.util.CollezioneOrdinataAstratta;

public class ListConcatenataOrdinata<T extends Comparable<? super T>> extends CollezioneOrdinataAstratta<T>{

	private static class Nodo<E>{
		E info;
		Nodo<E> next;
	}
	
	
	Nodo<T> first=null;
	private int size=0;
	@Override
	public int size() {
		return size;
	}
	@Override
	public void clear() {
		first=null;
		size=0;
	}
	@Override
	public boolean contains(T x) {
		Nodo<T> corr=first;
		while(corr!=null) {
			if(corr.info.equals(x)) return true;
			if(corr.info.compareTo(x)>0) return false;
			corr=corr.next;
		}
		return false;
	}
	@Override
	public void add(T x) {
		Nodo<T> nuovo=new Nodo<T>();
		nuovo.info=x;
		if(first==null || first.info.compareTo(x)>0) {
			nuovo.next=first;
			first=nuovo;
		}
		else {
			Nodo<T> pre=first,cor=first.next;
			while(cor!=null && cor.info.compareTo(x)<0) {
				pre=cor;cor=cor.next;
			}
			pre.next=nuovo;
			nuovo.next=cor;
		}
		
	}
	@Override
	public void remove(T x) {
		Nodo<T> pre=null,cor=first;
		while(cor!=null && cor.info.compareTo(x)<0) {
			pre=cor;cor=cor.next;
		}
		if(cor!=null && cor.info.equals(x)) {
			if(cor==first) {
				first=first.next;
			}
			else {
				pre.next=cor.next;
			}
			size--;
		}
	}
	@Override
	public T get(T x) {
		Nodo<T> cor=first;
		while(cor!=null) {
			if(cor.info.equals(x)) return cor.info;
			if(cor.info.compareTo(x)>0) return null;
			cor=cor.next;
		}
		return null;
	}
	@Override
	public boolean isEmpty() {
		return first==null;
	}
	@Override
	public boolean isFull() {
		return false;
	}
	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return null;
	}
	
	private class MyIterator implements Iterator<T>{
		Nodo<T> pre=null,cor=null;
		@Override
		public boolean hasNext() {
			if(cor==null) return first!=null;
			return cor.next!=null;
			
		}

		@Override
		public T next() {
			if(!hasNext()) throw new NoSuchElementException();
			if(cor==null) cor=first;
			else {
				pre=cor;cor=cor.next;
			}
			return cor.info;
		}
		
		public void remove() {
			if(pre==cor) throw new IllegalStateException();
			if(cor==first) {
				first=first.next;
			}
			else {
				pre.next=cor.next;
			}
			size--;
			cor=pre;
			size--;
			
			
		}
		
		
	}
	
	
	
	
	
	
	
}
