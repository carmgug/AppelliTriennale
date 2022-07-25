package poo.sistema;
import static poo.util.Matrix.*;

import poo.util.Mat;
public class Cramer extends Sistema{
	private double[][]a;
	private double[]y;
	public Cramer( double[][] a, double[] y ) {
		super(a,y);
		int n=getN();
		this.a=new double[n][n];
		this.y=new double[n];
		for( int i=0; i<n; ++i ) {
			System.arraycopy(a[i],0,this.a[i],0,n);
			this.y[i]=y[i];
		}
		
		
	}
	
	
	
	
	
	public double[] risolvi() {
		double dA=determinante(a);
		if( Mat.sufficientementeProssimi(dA, 0.0D) )
			throw new RuntimeException("Sistema Singolare!");
		int n=getN();
		double[] x=new double[n];
		for( int j=0; j<n; ++j ) {
			//scambia a[.][j] con y[.]
			for( int i=0; i<n; ++i ) {
				double tmp=a[i][j]; a[i][j]=y[i]; y[i]=tmp;
			}
			x[j]=determinante(a)/dA;
			//scambia a[.][j] con y[.]
			for( int i=0; i<n; ++i ) {
				double tmp=a[i][j]; a[i][j]=y[i]; y[i]=tmp;
			}			
		}
		return x;
	}//risolvi
	
	public String toString() {
		StringBuilder sb=new StringBuilder(200);
		int n=getN();
		for( int i=0; i<n; ++i ) {
			for( int j=0; j<n; ++j ) {
				sb.append(String.format("%7.2f", a[i][j])); //esempio
			}
			sb.append(String.format("%7.2f%n", y[i]));
		}
		return sb.toString();		
	}//toString
	
}//Cramer
