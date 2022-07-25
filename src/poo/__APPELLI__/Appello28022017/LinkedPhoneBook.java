package poo.__APPELLI__.Appello28022017;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedPhoneBook extends AbstractPhoneBook{

	private static class Nodo<Persona>{
		Persona info;
		Nodo<Persona> next;
	}
	
	Nodo<Persona> first=null;
	int size=0;
	
	@Override
	public void add(Persona p) {
		Nodo<Persona> n=new Nodo<Persona>();n.info=p;
		if(first==null) first=n;
		else if(first.info.compareTo(p)>0) {n.next=first;first=n;}
		else {
			Nodo<Persona> pre=first,cor=first.next;
			while(cor!=null && cor.info.compareTo(p)<0) {
				pre=cor;cor=cor.next;
			}
			pre.next=n;n=cor.next;
		}
		size++;
		
	}
	
	@Override
	public Iterator<Persona> iterator() {
		return new MyIterator();
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
			if(!hasNext())	throw new NoSuchElementException();
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
			cor=pre;
		}
		
	}

}
