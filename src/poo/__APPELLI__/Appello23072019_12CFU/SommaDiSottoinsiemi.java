package poo.__APPELLI__.Appello23072019_12CFU;

import poo.util.ArrayVector;

public class SommaDiSottoinsiemi {
	
	int[] a;
	int m;
	ArrayVector<Integer> b;
	
	
	public SommaDiSottoinsiemi(int[] a,int m) {
		for(int i=0;i<a.length;i++)
			for(int j=i+1;j<a.length;j++)
				if(a[i]==a[j]) throw new IllegalArgumentException();
		
		int[] temp=new int[a.length];
		System.arraycopy(a, 0, temp, 0, a.length);
		this.a=temp;
		this.m=m;
		b=new ArrayVector<Integer>(a.length);
		
	}
	
	
	public void risolvi() {
		tentativo(0);
	}
	
	public void tentativo(int ps) {

		for(int i=ps;i<a.length;++i) {
			int scelta=a[i]; 
			if(assegnabile(scelta)) {
				assegna(scelta);
				if(esisteSoluzione()) ScriviSoluzione();
				else if(i<a.length) tentativo(i+1);
				deassegna(scelta);
			}
		}
		
	}
	
	private void ScriviSoluzione() {
		
		System.out.println(b);
	}
	private boolean esisteSoluzione() {
		int somma=0;
		for(Integer x:b)
			somma+=x;
		if(somma==m) {
			
			return true;
			
		}
		
		return false;
	}
	
	private boolean assegnabile(int s) {
		
		if(b.contains(s)) return false;
		
		int somma=0;
		for(Integer x:b)
			somma+=x;
		if(somma+s<=m)
			return true;
		
		return false;
	}
	
	private void assegna(int s) {
		b.add(s);
	}
	private void deassegna(int s) {
		b.remove((Integer) s);
		
	}
	
	public static void main(String []args) {
		
		int[] a= {1,2,3,4,5,6,7,8,10,12,13};
		int m=26;
		SommaDiSottoinsiemi S=new SommaDiSottoinsiemi(a,m);
		S.risolvi();
		
	}
}
