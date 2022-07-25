package poo.__APPELLI__.Appello17072015;

import java.util.Comparator;
import java.util.Iterator;

import poo.util.LinkedList;
import poo.util.List;

public class InsiemeOrdinatoAlbero<T extends Comparable<? super T>> extends InsiemeOrdinatoAstratto<T> {

	
	private static class Nodo<T>{
		T info;
		Nodo<T> fd,fs;
	}
	
	
	private Nodo<T> radice;
	private Comparator<T> c;

	public InsiemeOrdinatoAlbero() {
		radice=null;
		c=(s1,s2)->{return s1.compareTo(s2);};
		
		
	}
	
	public InsiemeOrdinatoAlbero(Comparator<T> c) {
		this.c=c;
	}
	public InsiemeOrdinatoAlbero(Insieme<T> Set,Comparator<T> c) {
		this();
		this.c=c;
		for(T x:Set) {
			this.add(x);
		}
	}
	
	public InsiemeOrdinatoAlbero(Insieme<T> Set) {
		this();
		for(T x:Set) {
			this.add(x);
		}
	}
	
	@Override
	public InsiemeOrdinato<T> headSet(T x) {
		InsiemeOrdinato<T> ABR=new InsiemeOrdinatoAlbero<>();
		Iterator<T> it=iterator();
		while(it.hasNext()) {
			T elem=it.next();
			if(c.compare(elem, x)<0) {
				ABR.add(elem);
			}
		}
		return ABR;
	}


	@Override
	public InsiemeOrdinato<T> tailSet(T x) {
		InsiemeOrdinato<T> ABR=new InsiemeOrdinatoAlbero<>();
		Iterator<T> it=iterator();
		while(it.hasNext()) {
			T elem=it.next();
			if(c.compare(elem, x)>0) {
				ABR.add(elem);
			}
		}
		return ABR;
	}

	@Override
	public void add(T x) {
		radice=add(x,radice);
	}
	
	
	public void remove(T x) {
		radice=remove(radice,x);
	}
	
	private Nodo<T> remove(Nodo<T> radice,T x){
		if(radice==null) return null;
		if(c.compare(radice.info, x)>0) {
			radice.fs=remove(radice.fs,x);
			return radice;
		}
		if(c.compare(radice.info, x)<0) {
			radice.fd=remove(radice.fd,x);
			return radice;
			
		}
		//nodo trovato
		if(radice.fd==null && radice.fs==null) return null;
		if(radice.fd==null) return radice.fs;
		if(radice.fs==null) return radice.fd;
		//tutti e due i figli
		if(radice.fd.fs==null) {
			radice.info=radice.fd.info;
			radice.fd=radice.fd.fd;
			return radice;
		}
		Nodo<T> padre=radice.fd;
		Nodo<T> figlio= radice.fs;
		while(figlio.fs!=null) {
			padre=figlio;
			figlio=figlio.fs;
		}
		radice.info=figlio.info;
		padre.fs=figlio.fd;//al pi� un figlio a destra
		return radice;
		
		
	}
	
	
	
	private Nodo<T> add(T x,Nodo<T> radice) {
		if(radice==null) {
			Nodo<T> nc=new Nodo<>();
			nc.info=x;
			return nc;
		}
		if(c.compare(radice.info, x)>0) {
			radice.fs=add(x,radice.fs);
			return radice;
		}
		if(c.compare(radice.info, x)<0) {
			radice.fd=add(x,radice.fd);
		}
		return radice;//elemento uguale non deve essere aggiunto "Set";
		
	}

	
	public void InOrder(List<T> l) {
		InOrder(radice,l);
	}
	private void InOrder(Nodo<T> radice,List<T> l) {
		if(radice==null) return;
		InOrder(radice.fs,l);
		l.addLast(radice.info);
		InOrder(radice.fd,l);
	}

	@Override
	public Iterator<T> iterator() {
		return new MyIterator();
	}
	
	private class MyIterator implements Iterator<T>{
		
		List<T> lista;
		Iterator<T> it;
		T cor;
		public MyIterator() {
			lista=new LinkedList<T>();
			InOrder(lista);
			it=lista.iterator();
		}
		@Override
		public boolean hasNext() {
			return it.hasNext();
		}
		@Override
		public T next() {
			T cor=it.next();
			return cor;
		}
		
		public void remove() {
			it.remove();
			InsiemeOrdinatoAlbero.this.remove(cor);
		}
		
	
		
		
		
	}
	
	
	
	
	
	
	
	
	
}
