package poo.sistema;

public abstract class Sistema {
	private int n; 
	public Sistema( double[][]a, double[] y ) {
		if( a.length<1 )
			throw new RuntimeException("Sistema inconsistente!");
		if( a.length!=y.length ) 
			throw new RuntimeException("Sistema inconsistente!");
		for( int i=0; i<a.length; ++i ) {
			if( a[i].length!=a.length )
				throw new RuntimeException("Sistema inconsistente!");
		}
		this.n=a.length;
	}
	public int getN() { return n; }
	public abstract double[] risolvi();
}//Sistema
