package poo.Orale;

import poo.util.Mat;

public class Gauss extends Sistema {

	
	int[][] a;
	
	public Gauss(int[][] a,int[] y) {
		super(a,y);
		int[][] matrix=new int[a.length][a.length+1];
		for(int i=0;i<a.length;i++) {
			System.arraycopy(a[i], 0, matrix[i], 0, a.length);
			matrix[i][getN()]=y[i];
		}
		a=matrix;
	}
	
	
	protected void triangolarizza() {
		int n=getN();
		for(int j=0;j<n;j++) {
			if(Mat.sufficientementeProssimi(a[j][j], 0.0D)) {
				int p=j+1;
				for(;p<n;p++) {
					if(!Mat.sufficientementeProssimi(a[p][j], 0.0D)) break;
				}
				if(p==n) throw new RuntimeException("Sistema Singolare");
				int[] park=a[j];
				a[j]=a[p];
				a[p]=park;
			}
			
			for(int i=j+1;i<n;i++) {
				if(!Mat.sufficientementeProssimi(a[i][j], 0.0D)) {
					int cm=a[i][j]/a[j][j];
					for(int k=j;k<=n;++k) {
						a[i][k]=a[i][k]-a[j][k]*cm;
					}
				}
			}
		}
	}
	
	
	protected int[] VettoreSoluzione() {
		int n=getN();
		int[] x=new int[n];
		for(int i=n-1;i>=0;--i) {
			int tn=a[i][n];
			for(int j=i+1;j<n;j++) {
				tn=tn-a[i][j];
			}
			x[i]=tn/a[i][i];
		}
		return x;
	}
	
	
	
	
	@Override
	public int[] risolvi() {
		triangolarizza();
		return VettoreSoluzione();
	}

}
