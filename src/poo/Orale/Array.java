package poo.Orale;

public final class Array {
	
	
	
	
	public static <T extends Comparable<? super T>> void  MergeSort(T[] array) {
		MergeSort(array,0,array.length-1);
	}
	
	
	private static <T extends Comparable<? super T>> void MergeSort(T[] array,int inf,int sup) {
		int med;
		if(inf<sup) {
			med=(inf+sup)/2;
			MergeSort(array,0,med-1);
			MergeSort(array,med,sup);
			Merge(array,inf,med,sup);
		}
	}
	
	
	private static <T extends Comparable<? super T>> void Merge(T[] array,int inf,int med,int sup){
		T[] aux=(T[]) new Object[sup-inf+1];
		int i=inf,j=med+1,k=0;
		while(i<=med && j<=sup) {
			if(array[i].compareTo(array[j])>0) {
				aux[k]=array[j];++j;++k;
			}
			else {
				aux[k]=array[i];++i;++k;
			}
		}
		//gestione residui
		while(i<=med) {
			aux[k]=array[i];++i;++k;
		}
		while(j<=sup) {
			aux[k]=array[j];++j;++k;
		}
		for(k=0;k<aux.length;++k) {
			array[inf+k]=aux[k];
		}
	}
	
	public static <T extends Comparable<? super T>> void HeapSort(T[] array) {
		Heap<T> h=new Heap<>(array.length);
		for(int i=0;i<array.length;i++) {
			h.add(array[i]);
		}
		for(int i=0;i<array.length;i++) {
			array[i]=h.remove();
		}
		//abbiamo oridnato l'array
	}
	
	public static <T extends Comparable<? super T>> void SelectionSort(T[] array) {
		for(int j=array.length-1;j>1;--j) {
			int iMax=0;
			for(int i=1;i<=j;i++) {
				if(array[i].compareTo(array[iMax])>0) iMax=i;
			}
			T park=array[j];
			array[j]=array[iMax];
			array[iMax]=park;
		}
	}
	
	public static <T extends Comparable<? super T>> void InsertionSort(T[] array) {
		for(int i=1;i<array.length;i++) {
			T x=array[i];
			int j=i;
			while(j>0 && array[j-1].compareTo(x)>0) {
				array[j]=array[j-1];j--;
			}
			array[j]=x;
		}
	}
	
	public static <T extends Comparable<? super T>> void BubbleSort(T[] array) {
		int ius=0;
		for(int j=array.length-1;j>0;j=ius) {
			int scambi=0;
			for(int i=0;i<j;i++) {
				if(array[i].compareTo(array[i+1])>0) {
					T park=array[i];
					array[i]=array[i+1];
					array[i+1]=park;
					scambi++; ius=i;
				}
			}
			if(scambi==0) break;
		}
	}
}
