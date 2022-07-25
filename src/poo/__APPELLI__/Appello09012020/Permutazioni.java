package poo.__APPELLI__.Appello09012020;

public class Permutazioni {

	int[] a;
	int[] b;
	int N_sol;
	public Permutazioni(int[] a) {
		int[] Copia=new int[a.length];
		System.arraycopy(a, 0, Copia, 0, a.length);
		this.a=Copia;
		b=new int[this.a.length];
		N_sol=MaxSoluzioni(a.length);
	}
	//ps--->indice di b
	//s--->indice elemnto di a
	private int MaxSoluzioni(int n) {
		if(n<=2)
			return n;
		return n*MaxSoluzioni(n-1);
		
	}
	
	private void permuta(int ps) {
		//if(N_sol==0) return;
		for(int i=0;i<a.length;i++) {
			if(assegnabile(i,ps)) {
				assegna(i,ps);
				if(ps==a.length-1) scriviSoluzione();
				else permuta(ps+1);
				deassegna(i,ps);
			}
		}
		
		
		
	}
	private boolean assegnabile(int s,int ps) {
		if(b[ps]!=0) return false;
		for(Integer x:b)
			if((int)x==a[s]) return false;
		return true;
	}
	private void assegna(int s,int ps) {
		b[ps]=a[s];
	}
	private void deassegna(int s,int ps) {
		b[ps]=0;
	}
	private void scriviSoluzione() {
		
		System.out.println();
		System.out.print("[");
		for(Integer x:b) {
			System.out.print(x+" ");
		}
		System.out.print("]");
		
	}
	public void risolvi() {
		permuta(0);
	}
	
	public static void main (String []args) {
		
		int[] a= {1,2,3};
		
		
		Permutazioni Per=new Permutazioni(a);
		Per.risolvi();
		
		
		
	}
}
