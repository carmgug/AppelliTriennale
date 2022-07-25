package poo.Orale;

import java.util.Iterator;

import poo.util.CollezioneOrdinataAstratta;

public class ListRic<T extends Comparable<? super T>> extends CollezioneOrdinataAstratta<T> {

	private class Nodo<E> {
		E info;
		Nodo<E> next;
	}
	
	private Nodo<T> first=null;
	
	
	@Override
	public int size() {
		return size(first);
	}
	private int size(Nodo<T> corr) {
		if(corr==null) return 0;
		return 1+size(corr);
	}

	@Override
	public void clear() {
		first=null;
	}

	@Override
	public boolean contains(T x) {
		// TODO Auto-generated method stub
		return false;
	}
	private boolean contains(Nodo<T> corr,T x) {
		if(corr==null) return false;
		if(corr.info.compareTo(x)==0) return true;
		if(corr.info.compareTo(x)>0) return false;
		return contains(corr.next,x);
	}
	
	@Override
	public void add(T x) {
		first=add(first,x);
	}
	
	
	private Nodo<T> add(Nodo<T> corr,T x){
		if(corr==null || corr.info.compareTo(x)>0) {
			Nodo<T> nc=new Nodo<T>(); nc.info=x;
			nc.next=corr;
			return nc;
		}
		corr.next=add(corr.next,x);
		return corr;
	}

	@Override
	public void remove(T x) {
		// TODO Auto-generated method stub
		
	}
	
	public Nodo<T> remove(Nodo<T> corr,T x){
		if(corr==null) return null;
		if(corr.info.compareTo(x)==0) {
			return corr.next;
		}
		if(corr.info.compareTo(x)>0) return corr;
		corr.next=remove(corr.next,x);
		return corr;
	}

	@Override
	public T get(T x) {
		return get(first,x);
	}

	private T get(Nodo<T> corr,T x) {
		if(corr==null) return null;
		if(corr.info.equals(x)) return corr.info;
		if(corr.info.compareTo(x)>0) return null;
		return get(corr.next,x);
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
	
	
}
