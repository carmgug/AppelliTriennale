package poo.Orale;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class StackArray<T> extends AbstractStack<T> {

	private T[] stack;
	private int size;
	
	public StackArray(int c) {
		if(c<=0) throw new IllegalArgumentException();
		stack=(T[])new Object[c];
		size=0;
	}
	
	
	
	public int size() {
		return size;
	}
	
	
	
	
	
	
	
	@Override
	public void push(T x) {
		if(size==stack.length) {
			stack=Arrays.copyOf(stack, stack.length*2);
		}
		stack[size]=x;
		size++;	
	}
	
	public T pop() {
		if(size==0) throw new NoSuchElementException();
		T elem=stack[size-1];
		stack[size-1]=null;
		size--;
		return elem;
		
	}
	public T peek() {
		if(size==0) throw new NoSuchElementException();
		T elem=stack[size-1];
		return elem;
	}
	
	
	
	private class MyIterator implements Iterator<T>{
		private int cor=size;
		private boolean flag=false;
		
		
		@Override
		public boolean hasNext() {
			if(cor==size) return size>0;
			return cor>0;
			
		}

		@Override
		public T next() {
			if(!hasNext()) throw new NoSuchElementException();
			cor--;flag=true;
			return stack[cor];
		}
		
		public void remove() {
			if(!flag) throw new IllegalStateException();
			stack[cor]=null;
			for(int i=cor+1;cor<size;++i) {
				stack[i-1]=stack[i];
			}
			flag=false;
			//size-1 ==size-2;
			size--;
			stack[size]=null;
		}
		
	}

	@Override
	public Iterator iterator() {
		return new MyIterator();
	}

}
