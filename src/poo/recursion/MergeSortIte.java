package poo.recursion;

import poo.util.Stack;
import poo.util.StackConcatenato;

public final class MergeSortIte {
	private MergeSortIte() {}
	
	enum OP{ SORT, MERGE }
	
	public static <T extends Comparable<? super T>> void mergeSort( T[] v ) {
		mergeSortIte( v, 0, v.length-1 );
	}//mergeSort
	
	private static <T extends Comparable<? super T>> void mergeSort( T[] v, int inf, int sup ) {
		if( inf<sup ) {
			int med=(inf+sup)/2;
			mergeSort( v, inf, med ); //primo segmento
			mergeSort( v, med+1, sup ); //secondo segmento
			merge( v, inf, med, sup );
		}
	}//mergeSort 
	
	@SuppressWarnings("unchecked")
	private static <T extends Comparable<? super T>> void merge( T[] v, int inf, int med, int sup ) {
		T[] aux=(T[])new Comparable[sup-inf+1];
		int i=inf, j=med+1, k=0;
		while( i<=med && j<=sup ) {
			if( v[i].compareTo(v[j])<0 ) {
				aux[k]=v[i]; i++; k++;
			}
			else {
				aux[k]=v[j]; j++; k++;
			}
		}
		//gestione residuo
		while( i<=med ) {
			aux[k]=v[i]; i++; k++;
		}
		while( j<=sup ) {
			aux[k]=v[j]; j++; k++;
		}
		for( k=0; k<aux.length; ++k )
			v[inf+k]=aux[k];
	}//merge	
	
	private static <T extends Comparable<? super T>> void mergeSortIte( T[] v, int inf, int sup ){
		class AD{
			int inf, med, sup;
			OP op;
			public AD( OP op, int inf, int med, int sup ) {
				this.op=op; this.inf=inf; this.med=med; this.sup=sup;
			}
		}//AD
		Stack<AD> stack=new StackConcatenato<>();
		stack.push( new AD(OP.SORT,inf,0,sup) );
		while( !stack.isEmpty() ) {
			AD ad=stack.pop();
			if( ad.op==OP.SORT ) {
				if( ad.inf<ad.sup ) {
					ad.med=(ad.inf+ad.sup)/2;
					stack.push( new AD( OP.MERGE,ad.inf,ad.med,ad.sup ) );
					stack.push( new AD( OP.SORT,ad.med+1,ad.med,ad.sup ) );
					stack.push( new AD( OP.SORT, ad.inf, ad.med, ad.med ) );
				}
			}
			else merge(v,ad.inf,ad.med,ad.sup);
		}
	}//mergeSortIte
	
	public static void main( String[] args ) {
		Integer[] a= {12,56,-3,-2,10,4,2,7,5};
		System.out.println("Before: "+java.util.Arrays.toString(a));
		mergeSort( a );
		System.out.println("After: "+java.util.Arrays.toString(a));
	}//main
}//MergeSortIte
