package poo.Orale;

import poo.util.Mat;

public class Cramer extends Sistema{

	
	int[][] a;
	int[] y;
	
	
	public Cramer(int[][] a, int[] y) {
		super(a, y);
		this.a=new int [a.length][a.length];
		for(int i=0;i<a.length;i++ ) {
			System.arraycopy(a[i], 0, this.a[i], 0, a.length);
		}
		this.y=new int[y.length];
		System.arraycopy(y, 0, this.y, 0, y.length);
	}
	
	
	private int determinante() {
		int n=getN();
		int[][] b=new int[n][n];
		for(int i=0;i<n;i++) {
			System.arraycopy(a[i], 0, b[i], 0, n);
		}
		int scambi=0;
		for(int j=0;j<n;j++) {
			if(Mat.sufficientementeProssimi(b[j][j], 0.0D)) {
				int p=j+1;
				for(;p<n;p++) {
					if(!Mat.sufficientementeProssimi(b[p][j], 0.0D)) break;
				}
				if(p==n) return 0;
				int[] park=b[j];
				b[j]=b[p];b[p]=park;
				scambi++;
			}
			for(int i=j+1;i<n;j++) {
				if(!Mat.sufficientementeProssimi(b[i][j], 0)) {
					int cm=b[i][j]/b[j][j];
					for(int k=j;k<n;k++) {
						b[i][k]=b[i][k]-b[i][j]*cm;
					}
				}
			}
		}
		
		
		int det=1;
		for(int j=0;j<n;j++) {
			det=det*b[j][j];
		}
		if(scambi%2!=0)det=det*-1; 
		return det;
		
	}
	
	private int[] VettoreSoluzione(){
		int n=getN();
		int[] x=new int[n];
		int dA=determinante();
		if(dA==0) throw new RuntimeException("Sistema Singolare!!");
		for(int j=0;j<n;j++) {
			
			for(int i=0;i<n;i++) {
				int park=a[i][j];
				a[i][j]=y[i]; y[i]=park;
			}
			
			x[j]=determinante()/dA;
			for(int i=0;i<n;i++) {
				int park=a[i][j];
				a[i][j]=y[i];
				y[i]=park;
			}	
		}
		
		return x;
		
	}
	
	@Override
	public int[] risolvi() {
		return VettoreSoluzione();
	}

}
