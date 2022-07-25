package poo.util;

import java.util.Iterator;

public abstract class AbstractQueue<T> implements Queue<T>{
	//scrivere anche equals() e hashCode()
	public String toString() {
		StringBuilder sb=new StringBuilder();
		sb.append("[");
		Iterator<T> it=iterator();
		while( it.hasNext() ) {
			sb.append( it.next() );
			if( it.hasNext() ) sb.append(", ");
		}
		sb.append("]");
		return sb.toString();
	}//toString
}//AbstractQueue
