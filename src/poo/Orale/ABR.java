package poo.Orale;

import java.util.Iterator;

import poo.util.LinkedList;
import poo.util.List;

public class ABR<T extends Comparable<? super T>> {
	
	private static class Nodo<E>{
		E info;
		Nodo<E> fD,fS;
	}
	
	
	Nodo<T> radice=null;
	
	public int size() {
		return size(radice);
	}
	
	private int size(Nodo<T> radice) {
		if(radice==null) return 0;
		return 1+size(radice.fD)+size(radice.fS);
	}
	
	
	public void clear() {
		radice=null;
	}
	
	public void add(T x) {
		radice=add(radice,x);
	}
	
	private Nodo<T> add(Nodo<T> radice,T x){
		if(radice==null) {
			Nodo<T> nc=new Nodo<T>();nc.info=x;
			return nc;
		}
		if(radice.info.compareTo(x)>0) {
			radice.fS=add(radice.fS,x);
			return radice;
		}
		radice.fD=add(radice.fD,x);
		return radice;
	}
	
	public void remove(T x) {
		radice=remove(radice,x);
	}
	
	private Nodo<T> remove(Nodo<T> radice,T x){
		if(radice==null) return null;
		if(radice.info.compareTo(x)>0) {
			radice.fS=remove(radice.fS,x);
			return radice;
		}
		if(radice.info.compareTo(x)<0) {
			radice.fD=remove(radice.fD,x);
			return radice;
		}
		//info==x;
		if(radice.fD==null && radice.fS==null) return null;
		if(radice.fS==null) return radice.fD;
		if(radice.fD==null) return radice.fS;
		//nodo vittima ha due figli--> 
		if(radice.fD.fS==null) {
			radice.info=radice.fD.info;
			radice.fD=radice.fD.fD;
			return radice;
		}
		Nodo <T> padre=radice.fD;
		Nodo<T> figlio=radice.fD.fS;
		while(figlio.fS!=null) {
			padre=figlio;
			figlio=figlio.fS;
		}
		radice.info=figlio.info;
		padre.fS=figlio.fD;
		return radice;
	}
	
	
	//metodi di visita all'intenro dell'abr
	//PreOrder InOrder PostOrder
	//vista la riadice fs fd;
	//scorriamo a sinistra,vistiamo,scorrere a destra;
	//scorrere a sinistra,scorrere a destra e successivamente stampare il valore
	
	public void PreOrder(List<T> l) {
		PreOrder(l,radice);
	}
	
	private void PreOrder(List<T> l,Nodo<T> radice){
		if(radice==null) return;
		l.addLast(radice.info);
		PreOrder(l,radice.fS);
		PreOrder(l,radice.fD);
	}
	
	public void InOrder(List<T> l) {
		InOrder(l,radice);
	}
	
	private void InOrder(List<T> l,Nodo<T> radice){
		if(radice==null) return;
		InOrder(l,radice.fS);
		l.addLast(radice.info);
		InOrder(l,radice.fD);
	}
	public void PostOrder(List<T> l) {
		PostOrder(l,radice);
	}
	
	private void PostOrder(List<T> l,Nodo<T> radice) {
		if(radice==null) return;
		PostOrder(l,radice.fS);
		PostOrder(l,radice.fD);
		l.addLast(radice.info);
	}
	
	public void VisitaPerLivelli(List<T> l) {
		List<Nodo<T>> Queue=new LinkedList<>();
		Queue.addFirst(radice);
		while(!Queue.isEmpty()) {
			Nodo<T> nc=Queue.removeFirst();
			l.addLast(nc.info);
			if(nc.fS!=null) Queue.addLast(nc.fS);
			if(nc.fD!=null)Queue.addLast(nc.fD);
		}
	}
	
	public int altezza() {
		return altezza(radice);
	}
	
	private int altezza(Nodo<T> radice) {
		if(radice==null) return 0;
		int h1=0,h2=0;
		if(radice.fS!=null) h1=1+altezza(radice.fS);
		if(radice.fD!=null) h2=1+altezza(radice.fD);
		if(h1>h2) return h1;
		return h2;
	}
	
	
	private class MyIterator implements Iterator<T>{

		List<T> l;Iterator<T> it;
		T cor=null;
		public MyIterator() {
			l=new LinkedList<T>();
			InOrder(l);
			it=l.iterator();
		}
		
		@Override
		public boolean hasNext() {
			return it.hasNext();
		}

		@Override
		public T next() {
			cor= it.next();//NoSuchElementException()
			return cor;
		}
		public void remove() {
			it.remove();
			ABR.this.remove(cor);
			
		}
		
	}
	
	
	
	
	

}
