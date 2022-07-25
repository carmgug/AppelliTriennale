package poo.__APPELLI__.Appello17072015;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class InsiemeOrdinatoArray<T extends Comparable<? super T>> extends InsiemeOrdinatoAstratto<T>{

	private T[] array;
	int size;
	private Comparator<T> c= (s1,s2)->{ return s1.compareTo(s2);};
	
	public InsiemeOrdinatoArray(int size,Comparator<T> c) {
		if(size<=0) throw new IllegalArgumentException();
		array=(T[])new Object[size];
		size=0;
		this.c=c;
	}
	public InsiemeOrdinatoArray(int size) {
		if(size<=0) throw new IllegalArgumentException();
		array=(T[])new Object[size];
		size=0;
	}
	//insieme si � un insieme ordinato ma l'utente portrebbe inviare un comparatore diverso da quello utilizzato
	//per oridnare Set 
	public InsiemeOrdinatoArray(InsiemeOrdinato<T> Set,Comparator<T> c) {
		this.c=c;
		for(T x:Set) {
			this.add(x);
		}
	}
	
	public int size() {
		return size;
	}
	
	@Override
	public InsiemeOrdinato<T> headSet(T x) {
		InsiemeOrdinato<T> SottoInsieme=new InsiemeOrdinatoArray<T>(10,this.c);
		for(int i=0;i<size;i++) {
			if(c.compare(array[i], x)>0)SottoInsieme.add(x);
		}
		return SottoInsieme;
		
	}

	@Override
	public InsiemeOrdinato<T> tailSet(T x) {
		InsiemeOrdinato<T> SottoInsieme=new InsiemeOrdinatoArray<T>(10,this.c);
		for(int i=0;i<size;i++) {
			if(c.compare(array[i],x)>0)SottoInsieme.add(x);
		}
		return SottoInsieme;
	}

	@Override
	public void add(T x) {
		if(size==array.length) {
			array=Arrays.copyOf(array, array.length*2);
		}
		boolean flag=false; //not aggiunto
		for(int i=0;i<size && !flag;i++) {
			if(c.compare(array[i], x)>0) {
				//SHIFT DESTRO
				for(int j=size-1;j>=i;j--) {
					array[j+1]=array[j];
				}
				array[i]=x; flag=true;
			}
		}
	}

	@Override
	public Iterator<T> iterator() {
		return new MyIterator();
	}
	
	private class MyIterator implements Iterator<T>{
		private int cor=-1;
		boolean flag=false;
		@Override
		public boolean hasNext() {
			if(cor==-1) return size!=0;
			return cor<size;
		}
		@Override
		public T next() {
			if(!hasNext()) throw new NoSuchElementException();
			cor++;
			flag=true;
			return array[cor];
		}
		
		public void remove() {
			if(!flag) throw new IllegalStateException();
			
			for(int i=cor+1;i<size;i++) {
				array[i-1]=array[i];
			}
			flag=false;
			array[size]=null;
			size--;
		}
		
		
	}

}
