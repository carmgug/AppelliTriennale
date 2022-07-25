package poo.util;

import java.util.Comparator;

public final class Array{
	//classe di utilità sugli array numerici monodimensionali
	private Array(){}
	
	public static int ricercaLineare( int[] a, int x ){
		//ritorna l'indice di x in a, o -1 se la ricerca fallisce
		for( int i=0; i<a.length; ++i )
			if( a[i]==x ) return i;
		return -1;
	}//ricercaLineare
	
	public static void selectionSort( int[] a ){
		for( int j=a.length-1; j>1; --j ){
			int iMax=0;
			for( int i=1; i<=j; ++i )
				if( a[i]>a[iMax] ) iMax=i;
			int park=a[iMax]; a[iMax]=a[j]; a[j]=park;
		}
	}//selectionSort
	
	//inserire per esercizio altri metodi di servizio ricorrenti e overloaded, es:
	//ricerca binaria su array di int e di double
	//altri metodi di ordinamento, su array di int o di double
	//etc.
	
	public static void selectionSort( Comparable[] a ){
		for( int j=a.length-1; j>1; --j ){
			int iMax=0;
			for( int i=1; i<=j; ++i )
				if( a[i].compareTo(a[iMax])>0 ) iMax=i;
			Comparable park=a[iMax]; a[iMax]=a[j]; a[j]=park;
		}
	}//selection

	public static void bubbleSort( Comparable []v ){
		int ius=0;//inizializzazione fittizia
		for( int j=v.length-1; j>0; j=ius ){
			int scambi=0;
			for( int i=0; i<j; i++ )
			     if( v[i].compareTo(v[i+1])>0 ){
				//scambia v[i] con v[i+1]
				Comparable park=v[i];
				v[i]=v[i+1]; v[i+1]=park;
				scambi++; ius=i;//indice ultimo scambio
			     }
			if( scambi==0 ) break;
		}//for esterno
	}//bubbleSort
	
	public static <T extends Comparable<? super T>> int ricercaBinaria( Vector<T> v, T x ) {
		//PRE: v è ordinato per valori crescenti
		int inf=0, sup=v.size()-1;
		while( inf<=sup ) {
			int med=(inf+sup)/2;
			if( v.get(med).equals(x) ) return med;
			if( v.get(med).compareTo(x)>0 ) sup=med-1;
			else inf=med+1;
		}
		return -1;
	}//ricercaBinaria
	
	public static <T extends Comparable<? super T>> void insertionSort( T[] v ) {
		for( int i=1; i<v.length; ++i ) {
			T x=v[i];
			int j=i;
			while( j>0 && v[j-1].compareTo(x)>0 ) {
				v[j]=v[j-1]; j--;
			}
			v[j]=x;
		}
	}//insertionSort
	
	public static <T> void insertionSort( T[] a, Comparator<? super T> c ) {
		for( int i=1; i<a.length; ++i ) {
			T x=a[i];
			int j=i;
			while( j>0 && c.compare(a[j-1],x)>0 ) {
				a[j]=a[j-1]; j--;
			}
			a[j]=x;
		}		
	}//insertionSort

	/*
	1.Scrivere sotto forma di metodi generici i due precedenti metodi selectionSort e
	bubbleSort che ricevono un array di T col confronto, o un array di T ed un
	comparatore di elementi di tipo T.
	2. Quindi scrivere l'algoritmo di ricerca binaria su un array di tipo T dotato di
	confronto naturale, ed un elemento x da cercare, oppure passando un comparatore
	di elementi di tipo T non necessariamente dotato del confronto.
	*/
	
	public static <T extends Comparable<? super T>> void mergeSort( T[] v ) {
		System.out.println("Merge Sort Iterativo");
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
	
	private static enum Op{ MERGE_SORT, MERGE };
	
	private static <T extends Comparable<? super T>> void mergeSortIte( T[] v, int inf, int sup ){
		class AD{
			int inf, med, sup;
			Op op;
			public AD( int inf, int med, int sup, Op op ) {
				this.inf=inf; this.med=med; this.sup=sup; this.op=op;
			}
		}//AD
		Stack<AD> stack=new StackConcatenato<>();
		//schedulazione della chiamata iniziale di mergeSort
		stack.push(new AD(inf,0,sup,Op.MERGE_SORT)); //med e' fittizio qui, posto a 0 come es.
		while( !stack.isEmpty() ) {
			AD ad=stack.pop(); //area dati corrente su cui eseguire le operazioni
			if( ad.op==Op.MERGE_SORT ) {
				if( ad.inf<ad.sup ) {
					ad.med=(ad.inf+ad.sup)/2;
					stack.push( new AD(ad.inf,ad.med,ad.sup,Op.MERGE) ); //schedulazione MERGE
					stack.push( new AD(ad.med+1,0,ad.sup,Op.MERGE_SORT) ); //schedulazione 2 chiamata di mergeSort
					stack.push( new AD(ad.inf,0,ad.med,Op.MERGE_SORT) ); //schedulazione 1 chiamata di mergeSort
				}
			}
			else //ad.op==Op.MERGE
				merge( v, ad.inf, ad.med, ad.sup );
		}
	}//mergeSortIte
	
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
	
	//uso notevole dell'heap per costruire un algoritmo di ordinamento
	public static <T extends Comparable<? super T>> void heapSort( T[]v ) {
		Heap<T> heap=new Heap<>(v.length);
		//prima fase: caricamento heap
		for( int i=0; i<v.length; ++i ) heap.add( v[i] );
		//seconda fase: svuotamento heap
		int i=0; //posizione prossimo elemento vuoto di v
		while( heap.size()>0 ) {
			v[i]=heap.remove();
			i++;
		}
	}//heapSort
	
	public static void main( String[] args ){
		Integer[] v={8,2,7,-1,5,12,10};
/*
		System.out.println("Array iniziale: "+java.util.Arrays.toString(v));
		int i5=ricercaLineare(v,5);
		System.out.println("5 si trova in posizione "+i5);
		selectionSort(v);
		System.out.println("Array ordinato: "+java.util.Arrays.toString(v));
		String[] a={"zorro","caso","ebano","birillo","tana","lupo","fuoco"};
		System.out.println(java.util.Arrays.toString(a));
		Array.bubbleSort(a);
		System.out.println(java.util.Arrays.toString(a));	

		System.out.println("Array iniziale: "+java.util.Arrays.toString(v));
		insertionSort(v, (i1,i2)->{ //il compilatore inferisce che il tipo è Integer
			if( i1>i2 ) return -1;
			if( i1.equals(i2) ) return 0;
			return 1;
		});
		System.out.println("Array finale: "+java.util.Arrays.toString(v));
*/
		System.out.println("Array iniziale: "+java.util.Arrays.toString(v));
		heapSort(v);
		System.out.println("Array finale: "+java.util.Arrays.toString(v));
		
	}//main
}//Array