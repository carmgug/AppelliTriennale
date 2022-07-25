package poo.__APPELLI__.Appello28022017COPIA;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedPhoneBook extends AbstractPhoneBook{
	
	private static class Nodo<E>{
		E info;
		Nodo<E> next;
	}
	
	private Nodo<Persona> first=null;
	private int size=0;
	
	public int size() {
		return size;
	}
	public void clear() {
		first=null;
		size=0;
	}
	
	@Override
	public void add(Persona p) {
		Nodo<Persona> nc=new Nodo<>();
		nc.info=p;
		if(first==null || first.info.compareTo(p)>0) {
			nc.next=first;
			first=nc;
		}
		else {
			Nodo<Persona> pre=null,cor=first;
			while(cor!=null && cor.info.compareTo(p)<0) {
				pre=cor;cor=cor.next;
			}
			//non ha senso mantenere pi� nominativi uguali tra di loro in una rubrica;
			if(cor.info.compareTo(p)==0) cor.info.setPhone(p.getPhone());
			else{pre.next=nc; nc.next=cor;}
		}
		size++;
		
	}
	@Override
	public Iterator<Persona> iterator() {
		return new  MyIterator();
	}
	
	private class MyIterator implements Iterator<Persona>{
		Nodo<Persona> pre=null,cor=null;
		@Override
		public boolean hasNext() {
			if(cor==null) return first!=null;
			return cor.next!=null;
		}

		@Override
		public Persona next() {
			if(!hasNext())throw new NoSuchElementException();
			if(cor==null) cor=first;
			else {
				pre=cor;cor=cor.next;
			}
			return cor.info;
		}
		
		public void remove() {
			if(pre==cor) throw new IllegalStateException();
			//il nodo da rimuovre � cor
			if(cor==first) {
				first=first.next;
			}
			else {
				pre.next=cor.next;//al massimo pre � pari alla coda che ha come nuovo successivo il null;
			}
			size--;
			cor=pre;
			
		}
		
	}
	
	
	
}
