package poo.__APPELLI__.Appello27022017;

public class Esercizio1 {

	
	public static int[] letturaEinversione(int[] v) {
		if (v.length!=10) throw new IllegalArgumentException();
		int[] tmp=new int[v.length];
		for(int i=0;i<v.length;i++) {
			tmp[i]=v[v.length-1-i];
		}
		return tmp;
	}
	
	
	public static void main(String[] args) {
		
		
		int[] v= {0,1,2,3,4,5,6,7,8,9};
		v=letturaEinversione(v);
		System.out.print("[");
		for(int i=0;i<v.length;i++) {
			System.out.print(v[i]);
			if(i<v.length-1) System.out.print(", ");
		}
		System.out.print("]");
	}

}
