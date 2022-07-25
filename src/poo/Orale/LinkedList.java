package poo.Orale;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class LinkedList<T> extends AbstractList<T> {

	private static class Nodo<E>{
		E info;
		Nodo<E> prior,next;
	}
	
	public enum Move{FORWARD,BACKWARD,UNKNOWN}
	
	
	private Nodo<T> first=null,last=null;
	private int size=0;
	
	
	public int size() { return size;}
	public void clear() {
		first=null;last=null;
		size=0;
	}
	
	
	public T getFirst() {
		if(size==0) throw new NoSuchElementException();
		T elem=first.info;
		return elem;
	}
	
	public T getLast() {
		if(size==0) throw new NoSuchElementException();
		T elem=last.info;
		return elem;
	}
	
	public void addFirst(T x) {
		Nodo<T> nc=new Nodo<>();
		nc.info=x;
		nc.next=first;
		if(first!=null) first.prior=nc;
		else last=nc;
		first=nc;
		size++;
	}
	
	public void addLast(T x) {
		Nodo<T> nc=new Nodo<>();
		nc.info=x;
		nc.prior=last; //null;
		if(last!=null) last.next=nc;
		else first=nc;
		last=nc;
		size++;
	}
	
	public T removeFirst() {
		if(size==0) throw new NoSuchElementException();
		T elem=first.info;
		first=first.next;
		if(first!=null) first.prior=null;
		else last=null;
		return elem;
	}
	
	public T removeLast() {
		if(size==0) throw new NoSuchElementException();
		T elem=last.info;
		last=last.prior;
		if(last!=null) last.next=null;
		else first=null;
		return elem;
	}
	
	
	private class MyListIterator implements ListIterator<T> {
		
		Nodo<T> pre=null,cor=null;
		//pre consuma l'emento presente in cor che viene restituito con una next
		//cor consuma l'elemento presente in pre che viene restituito con una previous
		
		Move lastMove=Move.UNKNOWN;
		
		public MyListIterator() {
			pre=null;cor=first;
		}
		
		public MyListIterator(int pos) {
			if(pos<0 || pos>size) throw new IllegalArgumentException();
			pre=null;cor=first;
			for(int i=0;i<pos;i++) {
				pre=cor;cor=cor.next;
			}
		}
		
		
		@Override
		public boolean hasNext() {
			return cor!=null;
		}

		@Override
		public T next() {
			if(!hasNext()) throw new NoSuchElementException();
			pre=cor;cor=cor.next;
			lastMove=Move.FORWARD;
			return pre.info;
		}

		@Override
		public boolean hasPrevious() {
			return pre!=null;
		}

		@Override
		public T previous() {
			if(!hasPrevious()) throw new NoSuchElementException();
			cor=pre;pre=pre.prior;
			return cor.info;
		}

		@Override
		public int nextIndex() {
			throw new RuntimeException("Operazione Non supportata");
		}

		@Override
		public int previousIndex() {
			throw new RuntimeException("Operazione Non supportata");
		}

		@Override
		public void remove() {
			if(lastMove==Move.UNKNOWN)throw new IllegalStateException();
			Nodo<T> r=null;
			if(lastMove==Move.FORWARD) r=pre;
			else r=cor;
			
			if(r==first) {
				first=first.next;
				if(first==null) last=null;
				else first.prior=null;
			}
			else if(r==last) {
				last=last.prior;
				last.next=null;
			}
			else {
				r.prior.next=r.next;
				r.next.prior=r.prior;
			}
			
			if(lastMove==Move.FORWARD) pre=r.prior;
			if(lastMove==Move.BACKWARD) cor=r.next;
			lastMove=Move.UNKNOWN;
			size--;
			
			
			
			
		}

		@Override
		public void set(T e) {
			if(lastMove==Move.UNKNOWN) throw new IllegalStateException();
			if(lastMove==Move.FORWARD) pre.info=e;
			else cor.info=e;
			
		}

		@Override
		public void add(T e) {
			Nodo<T> nc=new Nodo<>();
			nc.info=e;
			nc.prior=pre;nc.next=cor;
			if(cor!=null) cor.prior=nc;
			if(pre!=null) pre.next=nc;
			pre=nc;
			if(nc.next==first) first=nc;
			if(nc.prior==last) last=nc;
			lastMove=Move.UNKNOWN;
			size++;
		}
		
		
		
		
	}
	
	@Override
	public ListIterator<T> Listiterator() {
		return new MyListIterator();
	}

	@Override
	public ListIterator<T> Listiterator(int pos) {
		return new MyListIterator(pos);
	}

	@Override
	public Iterator<T> iterator() {
		return new MyListIterator();
	}

}
