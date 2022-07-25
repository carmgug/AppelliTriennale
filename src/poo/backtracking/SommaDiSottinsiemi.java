package poo.backtracking;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SommaDiSottinsiemi extends Backtracking<Integer,Integer>{
	private int []a, b;
	private int S;
	public SommaDiSottinsiemi( int[]a, final int S ) {
		this.a=java.util.Arrays.copyOf(a, a.length);
		this.b=new int[a.length];
		this.S=S;
	}
	protected List<Integer> puntiDiScelta() {
		List<Integer> li=new ArrayList<>();
		for( int i=0; i<b.length; ++i ) li.add(i);
		return li;
	}//puntiDiScelta
	protected Collection<Integer> scelte( Integer p ){
		List<Integer> li=new ArrayList<>();
		for( int i=0; i<a.length; ++i ) li.add(i);
		return li;
	}//scelte
	public void risolvi() { tentativo(puntiDiScelta(),0); }

	protected boolean esisteSoluzione( Integer p) {
		int sum=0;
		for( int i=0; i<=p; ++i ) sum=sum+b[i];
		return sum==S;
	}//esisteSoluzione

	protected boolean assegnabile( Integer p, Integer s ) {
		int som=0;
		for( int i=0; i<p; ++i ) {
			if( b[i]==a[s] ) return false;
			som=som+b[i];
		}
		if( som+a[s]>S ) return false;
		return true;
	}//assegnabile

	protected void assegna( Integer p, Integer s ) {
		b[p]=a[s];
	}//assegna

	protected void deassegna( Integer p, Integer s ) {}//deassegna

	protected void scriviSoluzione( Integer p ) {
		System.out.print('[');
		for( int i=0; i<=p; ++i ) {
			System.out.print(b[i]);
			if( i<p ) System.out.print(',');
		}
		System.out.println(']');
	}//scriviSoluzione

	public static void main( String...args ) {
		int a[]= {1,2,3,4,5};
		new SommaDiSottinsiemi(a,6).risolvi();
	}
}//SommaDiSottinsiemi
