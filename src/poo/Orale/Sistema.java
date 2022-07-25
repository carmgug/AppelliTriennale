package poo.Orale;



public abstract class Sistema {
	
	private int n;
	
	public Sistema(int[][] a,int[] y) {
		if(a.length!=y.length) throw new IllegalArgumentException("Sistema Inconsistente");
		for(int i=0;i<a.length;i++) {
			if(a[i].length!=a.length) throw new IllegalArgumentException("Sistema Inconsistente");
		}
		this.n=a.length;
	}
	
	public int getN() {
		return n;
	}
	
	public abstract int[] risolvi();

}
