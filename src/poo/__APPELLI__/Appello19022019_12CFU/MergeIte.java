package poo.__APPELLI__.Appello19022019_12CFU;

import poo.util.Stack;
import poo.util.StackConcatenato;

public class MergeIte {
	
	
	static enum OP {Merge_Sort,Merge}
	
	static class AreaDati<T extends Comparable<? super T>>{//inner class
		T[] array;
		int inf;int sup;
		OP chiamata;
		public AreaDati(T[] a,int inf,int sup,OP chiamata){
			array=a;this.inf=inf;this.sup=sup;
			this.chiamata=chiamata;
		}
		
		
	}
	
	
	public static <T extends Comparable<? super T>> void MergeSort(T[] array) {
		
		MergeSort(array,0,array.length-1,-1);
	}
	
	
	@SuppressWarnings("unchecked")
	private static <T extends Comparable<? super T>> void MergeSort(T[] a,int inf,int sup,int i) {
		Stack<AreaDati<T>> stack=new StackConcatenato<AreaDati<T>>();
		AreaDati<T> AD= new AreaDati(a,inf,sup,OP.Merge_Sort);//simulazione prima chiamata
		stack.push(AD);
		
		//INZIO 
		while(!stack.isEmpty()) {
			AreaDati<T> ad=stack.pop();
			if(ad.inf<ad.sup) {
				int med=(ad.sup+ad.inf)/2;
				if(ad.chiamata==OP.Merge) {Merge(ad.array,ad.inf,med,ad.sup);}
				else {
					stack.push(new AreaDati<T>(ad.array,ad.inf,ad.sup,OP.Merge));
					stack.push(new AreaDati<T>(ad.array,med+1,ad.sup,OP.Merge_Sort));
					stack.push(new AreaDati<T>(ad.array,ad.inf,med,OP.Merge_Sort));
				}
			}
		}
	}
	
	
	
	
	private static <T extends Comparable<? super T>> void MergeSort(T[] a,int inf,int sup) {
		if(inf<sup){//segmento non vuoto
			int med=(inf+sup)/2;
			MergeSort(a,inf,med);
			MergeSort(a,med+1,sup);
			merge(a,inf,med,sup);
		}
	}
	
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
	
	
	
	
	private static <T extends Comparable<? super T>> void Merge(T[] v,int inf,int med,int sup) {
		//vettore ausiliario
		@SuppressWarnings("unchecked")
		T[] aux=(T[]) new Comparable[sup-inf+1];
		int i=inf,j=med+1,k=0;
		while(i<=med && j<=sup) {
			if(v[i].compareTo(v[j])<0) {aux[k]=v[i];i++;k++;}
			else {aux[k]=v[j];j++;k++;}
		}
			//gestione residuo
			while(i<=med) {aux[k]=v[i];i++;k++;}
			while(j<=sup) {aux[k]=v[j];j++;k++;}
			//ricopia di aux su v tra inf e sup;
			for(k=0;k<aux.length;k++) v[inf+k]=aux[k];
		
	}
	
	public static void main(String[] arg) {
		
		
		
		Integer[] array= {7,32,745,1,4643,67,14,4436,7685,432,532};
		
		
		MergeSort(array);
	}
	
}
