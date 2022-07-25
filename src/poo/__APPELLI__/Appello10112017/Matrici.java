package poo.__APPELLI__.Appello10112017;

import java.util.Scanner;

import poo.util.Mat;

public final class Matrici {

	
	public static double[][] matriceInversa(double[][] a){
		for(int i=0;i<a.length;i++) {
			if(a[i].length!=a.length) throw new IllegalArgumentException("Matrice non Quadrata");
		}
		int n=a.length;
		double[][] b=new double[n][n*2];
		for(int i=0;i<n;i++) {
			System.arraycopy(a[i], 0, b[i], 0, n);
		}
		//matrice unitaria
		for(int i=n;i<n*2;i++) {
			b[i][i]=1;
		}
		
		traingolarizzaINF(b);
		triangolarizzaSUP(b);
		
		b=Inversa(b);
		return b;
	}
	
	
	private static  void traingolarizzaINF(double[][] b) {
		int n=b.length; // sottomatrice
		int n2=b[0].length;
		for(int j=0;j<n;++j) {
			if(Mat.sufficientementeProssimi(b[j][j], 0.0D)) {
				int p=j+1;
				for(;p<n;++p) {
					if(!Mat.sufficientementeProssimi(b[p][j], 0.0D)) break;
				}
				if(p==n) throw new RuntimeException();
				double[] park=b[p];
				b[p]=b[j];
				b[j]=park; //scambio effettuato;
			}
			
			double pivot=b[j][j];
			for(int i=j+1;i<n;++i) {
				if(Mat.sufficientementeProssimi(b[i][j], 0.0D)) {
					double cm=b[i][j]/b[j][j];
					for(int k=0;k<n2;++k) {
						b[i][k]=b[i][k]-b[j][j]*cm;
					}
				}
			}
		}
	}
	
	private static void triangolarizzaSUP(double[][] b) {
		int n=b.length;//matrice
		int n2=b[0].length;
		
		for(int j=0;j<n;++j) {
			
			double pivot=b[j][j];
			
			//rendiamo pivot unitario
			for(int k=0;k<n2;k++) {
				b[j][k]=b[j][k]/b[j][j];
			}
			
			
			
			//rendiamo nulli gli elementi sopra
			for(int i=j-1;i>=0;i++) {
				if(!Mat.sufficientementeProssimi(b[i][j], 0.0D)) {
					double cm=b[i][j]/b[j][j];
					for(int k=0;k<n2;k++) {
						b[i][k]=b[i][k]-b[j][j]*cm;
					}
					
				}
			}
		}
		
	}
	
	private static double[][] Inversa(double[][] b){
		double[][] Inversa=new double[b.length][b.length];
		for(int i=0;i<b.length;++i) {
			System.arraycopy(b[i], i+b.length, Inversa[i], 0, b.length);
		}
		return Inversa;
	}
	
	
	private static double[][] minore(double[][] a,int n,int p,int q){
		
		  double cofac[][] = new double [n-1][n-1];
	        int x = 0, y = 0;
	        for (int i = 0; i < n; ++i)
	        {
	            for (int j = 0; j < n; ++j)
	                if (i != p && j != q)
	                {
	                    cofac[x][y] = a[i][j];
	                    y++;
	                }
	            if (y > n-2)  { ++x; y = 0; }
	        }
	        return cofac;
	    
	}
	
	public static double determinante(double[][] a) {
		return 0+determinante(a,0);
	}
	private static double determinante(double[][] a,int i) {
		if(i==a.length) return 0.0D;
		if(a.length==1) return a[0][0];
		if(a.length==2) return (a[0][0]*a[1][1])-(a[0][1]*a[1][0]);
		double res=a[i][0]*Math.pow(-1, i)*determinante(minore(a,a.length,i,0),i);
		return res+determinante(a,i+1);
	}
	
	
	
	public static void main(String[] args) {
		
		Scanner readIn = new Scanner(System.in);
		readIn.nextLine();
        int n = readIn.nextInt();
        double a[][] = new double[n][n];
        for (int i = 0; i < n; ++i)
            for (int j = 0; j < n; ++j)
                a[i][j] = readIn.nextDouble();

	    //x.print_mat();
	    System.out.println(determinante(a));
		
		
		
		
		
		
		
		
	}
	
	

}