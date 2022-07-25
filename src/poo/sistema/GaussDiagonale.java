package poo.sistema;

import poo.util.Mat;

public class GaussDiagonale extends Gauss{

	public GaussDiagonale( double[][] a, double[] y ) {
		super(a,y);
	}

	protected void triangolarizza() {
		super.triangolarizza();
		int n=getN();
		for( int j=0; j<n; ++j ) {
			//rendi unitario il coeff diagonale a[j][j]
			double c=a[j][j];
		    for( int k=j; k<=n; ++k )
		    	a[j][k]=a[j][k]/c;
		    //azzera coeff parte superiore colonna j, sulle righe dalla j-1-esima alla 0
			for( int i=j-1; i>=0; --i ) {
				if( !Mat.sufficientementeProssimi(a[i][j], 0.0D) ) {
					double cm=a[i][j]/a[j][j];
					for( int k=j; k<=n; ++k )
						a[i][k]=a[i][k]-a[j][k]*cm;
				}
			}
		}
	}//triangolarizza

	protected double[] calcolaSoluzione() {
		int n=getN();
		double[] x=new double[n];
		for( int i=0; i<n; ++i ) x[i]=a[i][n];
		return x;
	}//calcolaSoluzione

}//GaussDiagonale
