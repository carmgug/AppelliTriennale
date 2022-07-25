package poo.util;

import java.util.Arrays;

public final class Matrix {
	private Matrix() {}
	public static double[][] add( double[][] a, double[][] b ){
		//PRE: a, b matrici rettangolari compatibili
		if( a.length!=b.length || a[0].length!=b[0].length )
			throw new IllegalArgumentException("Matrici incompatibili per l'addizione");
		double[][] somma=new double[a.length][a[0].length];
		for( int i=0; i<a.length; ++i )
			for( int j=0; j<a[0].length; ++j )
				somma[i][j]=a[i][j]+b[i][j];
		return null;
	}//add
	public static int[][] add( int[][] a, int[][] b ){
		//PRE: a, b matrici rettangolari compatibili
		if( a.length!=b.length || a[0].length!=b[0].length )
			throw new IllegalArgumentException("Matrici incompatibili per l'addizione");
		int[][] somma=new int[a.length][a[0].length];
		for( int i=0; i<a.length; ++i )
			for( int j=0; j<a[0].length; ++j )
				somma[i][j]=a[i][j]+b[i][j];
		return null;
	}//add	
	//sviluppare per esercizio altri metodi di utilità sull'aritmetica di matrici
	public static double[][] trasposta( double[][] a ){
		//PRE: a è quadrata ma non lo deve essere per forza
		for(int i=0;i<a.length;i++)
			if(a.length!=a[i].length) throw new IllegalArgumentException();
		double[][] matrix=new double[a.length][a.length];
		for(int i=0;i<a.length;++i)
			for(int j=0;j<a[0].length;++j)
				matrix[j][i]=a[i][j];
		return matrix;
	}//trasposta
	public static double[][] mul( double[][] a, double[][] b  ){
		//PRE: a e b quadrate e della stessa dimensione
		for(int i=0;i<a.length;i++)
			if(a.length!=a[i].length) throw new IllegalArgumentException();
		double[][] mul=new double[a.length][a.length];
		for(int i=0;i<a.length;++i)
			for(int j=0;j<b[0].length;++j)
				for(int k=0;k<a.length;k++)
					mul[i][j]+=a[i][k]*b[k][j];
		return mul;
	}//mul
	//altri metodi
	public static double determinante( double[][] m ) {//m è attesa matrice quadrata
		for( int i=0; i<m.length; ++i )
			if( m.length!=m[i].length )
				throw new IllegalArgumentException("Matrice non quadrata!");
		double[][] a=new double[m.length][m.length];
		//copia di m su a
		for( int i=0; i<a.length; ++i )
			for( int j=0; j<a.length; ++j )
				a[i][j]=m[i][j];
		int n=a.length, scambi=0; //attenzione alle inizializzazioni
		for( int j=0; j<n; ++j ) {
			if( Mat.sufficientementeProssimi(a[j][j], 0.0D) ) {//fai pivoting
				int p=j+1;
				for( ; p<n; ++p )
					if( !Mat.sufficientementeProssimi(a[p][j], 0.0D) ) break;
				if( p==n ) return 0.0D; //determinante zero
				double[] tmp=a[j]; a[j]=a[p]; a[p]=tmp;
				scambi++;
			}
			for( int i=j+1; i<n; ++i ) {//ciclo di azzeramento sotto a[j][j]
				if( !Mat.sufficientementeProssimi(a[i][j], 0.0D) ) {
					double cm=a[i][j]/a[j][j];
					for( int k=j; k<n; ++k ) {
						a[i][k]=a[i][k]-a[j][k]*cm;
					}
				}
			}
		}
		double d=1.0D;
		for( int i=0; i<n; ++i )
			d=d*a[i][i];
		if( scambi%2!=0 ) d=d*(-1);
		return d;
	}//determinante
		
	public static void main( String[] args ) {//demo
		double[][] m= {
			{1,4,3},
			{3,5,2},
			{-1,7,4}
		};
		System.out.println("det="+determinante(m));
		m=trasposta(m);
		for(int i=0;i<m.length;i++)
			System.out.println(Arrays.toString(m[i]));
	}//main
	
}//Matrix
