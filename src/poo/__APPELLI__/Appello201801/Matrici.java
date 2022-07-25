package poo.__APPELLI__.Appello201801;

import poo.util.Mat;

public final class Matrici {

	
	
	public static double[][] matriceInversa(double[][] a){
		
		for(int i=0;i<a.length;++i) {
			if(a[i].length!=a.length) throw new IllegalArgumentException();
		}
		double[][] b=new double[a.length][a.length*2];
		for(int i=0;i<a.length;i++) {
			System.arraycopy(a[i], 0, b[i], 0, a.length);
		}
		//matrice unitaria
		for(int i=a.length;i<a.length*2;++i) {
			b[i][i]=1.0D;
		}
		triangolarizza(b);
		triangolarizzaSUP(b);
		
		b=Estrai(b);
		return b;
	}
	
	private static void triangolarizza(double[][] b) {
		int n=b[0].length/2;
		for(int j=0;j<n;j++) {
			//scelta pivot
			if(Mat.sufficientementeProssimi(b[j][j], 0.0D)) {
				int p=j+1;
				for(;p<n;p++) {
					if(!Mat.sufficientementeProssimi(b[p][j], 0.0D)) break;
				}
				if(p==n) throw new RuntimeException("Non esiste l'inversa");
				double[] park=b[p];
				b[p]=b[j];b[j]=park;
			}
			for(int i=j+1;i<n;i++) {
				if(!Mat.sufficientementeProssimi(b[i][j], 0.0D)) {
					double cm=b[i][j]/b[j][j];
					for(int k=0;k<b[0].length;k++) {
						b[i][k]=b[i][k]-b[j][k]*cm;
					}	
				}
			}
		}
	}
	
	private static void triangolarizzaSUP(double[][] b) {
		int n=b[0].length/2;
		for(int j=0;j<n;j++) {
			double pivot=b[j][j];
			//rendiamo unitario il coefficiente
			for(int k=j;k<b[0].length;k++) {
				b[j][k]=b[j][k]/pivot;
			}
			for(int i=j-1;i>=0;i--) {
				if(!Mat.sufficientementeProssimi(b[i][j], 0.0D)) {
					double cm=b[i][j]/b[j][j];
					for(int k=0;k<b[0].length;k++) {
						b[i][k]=b[i][k]-b[j][k]*cm;
					}
				}
			}
		}
	}
	
	
	
	
	private static double[][] Estrai(double[][] m){
		int n=m.length/2;
		double[][] res=new double[n][n];
		for(int i=n;i<n*2;i++) {
			for(int j=n;j<n*2;j++) {
				res[i][j]=m[i][j];
			}
		}
		return m;
	}
}
