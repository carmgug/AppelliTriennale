package poo.__APPELLI__.Appello2501_2019;

import java.util.Arrays;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ArrayList<T> extends AbstractList<T> {
	T[] array;
	int size;
	
	private enum MOVE {UNKNOWN, FORWARD, BACKWARD}
	
	
	
	public ArrayList(int capacit�) {
		if(capacit�<0) throw new IllegalArgumentException();
		array=(T[] )new Object[capacit�];
		size=0;
	}
	
	public ArrayList() {
		this(30);
		size=0;
	}
	@Override
	public void clear() {
		Iterator<T> it=iterator();
		while(it.hasNext()) {
			it.next();it.remove();
		}
		size=0;
	}
	@Override
	public void addFirst(T x) {
		if(size==array.length)
			array=Arrays.copyOf(array, size*2);
		for(int i=size-1;i>0;i--) {
			array[i+1]=array[i];
		}
		array[0]=x;
		size++;
	}

	@Override
	public void addLast(T x) {
		if(size==array.length)
			array=Arrays.copyOf(array, size*2);
		array[size]=x;
		size++;
	}

	@Override
	public T getFirst() {
		if(!(size>0)) throw new IllegalStateException();
		return array[0];
	}

	@Override
	public T getLast() {
		if(!(size>0)) throw new IllegalStateException();
		return array[size-1];
	}

	@Override
	public ListIterator<T> listIterator() {
		return new ArrayListIterator();
	}

	@Override
	public ListIterator<T> listIterator(int from) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<T> iterator() {
		return  listIterator();
	}
	
	private class ArrayListIterator implements ListIterator<T>{
		int cor=0;
		int pre=-1;
		private MOVE lastMove=MOVE.UNKNOWN;
		
		
		@Override
		public boolean hasNext() {
			if(pre==-1) return size>0;
			else return cor<size;
		}
		

		@Override
		public T next() {
			if(!hasNext()) throw new NoSuchElementException();
			lastMove=MOVE.FORWARD;
			pre++;cor++;
			return array[pre];
		}

		@Override
		public boolean hasPrevious() {
			return pre!=-1;
		}

		@Override
		public T previous() {
			if(!hasPrevious()) throw new NoSuchElementException();
			lastMove=MOVE.BACKWARD;
			pre--;cor--;
			return array[cor];
		}

		@Override
		public int nextIndex() {
			throw new UnsupportedOperationException();
		}

		@Override
		public int previousIndex() {
			throw new UnsupportedOperationException();
		}

		@Override
		public void remove() {
			if(lastMove==MOVE.UNKNOWN) throw new IllegalStateException();
			if(lastMove==MOVE.FORWARD) {
				for(int i=pre;i<size-1;i++)
					array[i]=array[i+1];
			}
			else 
				for(int i=cor;i<size-1;i++)
					array[i]=array[i+1]; 
			if(lastMove==MOVE.FORWARD) {
				pre--;
			}
			else cor++;
			array[size-1]=null;
			size--;
			lastMove=MOVE.UNKNOWN;
			
		}

		@Override
		public void set(T e) {
			if(lastMove==MOVE.UNKNOWN)
				throw new IllegalStateException();
			if(lastMove==MOVE.FORWARD)
				array[pre]=e;
			else
				array[cor]=e;
			
		}

		@Override
		public void add(T e) {
			if(size==array.length) {array=Arrays.copyOf(array,size*2);}
			for(int i=size-1;i>=cor;--i) {
				array[i+1]=array[i];
			}
			array[cor]=e;
			size++;
			lastMove=MOVE.UNKNOWN;
		}
		
		
		
	}
	
	public static void filtra(int N,ArrayList<Integer> l) {
		
		
		for(int x=2;x<=Math.round(Math.sqrt(N));x=(x==2)?x+1:x+2) {
			if(l.contains(x)) {
				int m=x+x;
				while(m<=N) {
					l.remove(m);
					m=m+x;
				}
			}
		}
		
		
	}
	
	public static void main(String[] args) {
		ArrayList<Integer> l1=new ArrayList<Integer>();
		Scanner sc=new Scanner(System.in);
		System.out.println("Benvenuto: Premi Invio ");
		sc.nextLine();
		loop1: for(;;) {
			
			int linea=sc.nextInt();
			if(linea==-1) break;
			try {
				int n=linea;
				for(int i=2;i<=n;i++) l1.addLast(i);
				filtra(n,l1);
			}catch(NumberFormatException e) {
				System.out.println("inserisci numero valido");
				continue loop1;
			}
			System.out.println(l1);
			l1.clear();
			sc.nextLine();
		}
		ListIterator<Integer> it=l1.listIterator();
		it.add(5);
		it.add(7);
		it.add(9);
		System.out.println(l1);
	}
	

}
