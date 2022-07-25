package poo.Orale;

import poo.util.Mat;

public class GaussDiagonale extends Gauss{

	public GaussDiagonale(int[][] a, int[] y) {
		super(a, y);
	}
	
	private void triangolarizzaSup() {
		this.triangolarizza();
		int n=getN();
		for(int j=0;j<n;j++) {
			int pivot=a[j][j];
			
			for(int k=j;k<=n;k++) {
				a[j][k]=a[j][k]/a[j][j];
			}
			for(int i=j-1;i>=0;i--) {
				if(!Mat.sufficientementeProssimi(a[i][j], 0.0D)) {
					int cm=a[i][j]/pivot;
					for(int k=j;k<=n;k++) {
						a[i][k]=a[i][k]-a[j][k]*cm;
					}
				}
			}
		}
	}
	
	
	protected  int[] VettoreSoluzione() {
		int n=getN();
		int[] x=new int[n];
		for(int i=0;i<x.length;i++) {
			x[i]=a[i][n];
		}
		return x;
		
		
	}
	
	
	public int[] risolvi() {
		triangolarizzaSup();
		return VettoreSoluzione();
		
	}

}
