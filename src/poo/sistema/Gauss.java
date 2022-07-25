package poo.sistema;

import poo.util.Mat;

public class Gauss extends Sistema{
	protected double[][] a;
	
	public Gauss( double[][] a, double[] y ) {
		super(a,y);
		this.a=new double[a.length][a.length+1];
		for( int i=0; i<a.length; ++i ) {
			System.arraycopy(a[i], 0, this.a[i], 0, a.length);
			this.a[i][a.length]=y[i];
		}
	}
	
	protected void triangolarizza() {
		int n=getN();
		for( int j=0; j<n; ++j ) {//a[j][j] è l'elemento diagonale o di pivot
			//ipotesi: a[j][j] è diverso da 0
			if( Mat.sufficientementeProssimi(a[j][j], 0.0D) ) { //occorre colmare la lacuna
				//fai pivoting
				int p=j+1;
				for( ; p<n; ++p )
					if( !Mat.sufficientementeProssimi(a[p][j], 0.0D) )//trovato nuovo pivot
						break;
				if( p==n ) throw new RuntimeException("Sistema Singolare!");
				//scambia riga j-esima con la riga p-esima
				double[] park=a[j];
				a[j]=a[p]; a[p]=park;
			}
			//azzera tutti i coefficienti sulla colonna j-esima, sulle righe dalla j+1 fino a n-1
			for( int i=j+1; i<n; ++i ) {
				if( !Mat.sufficientementeProssimi(a[i][j],0.0D) ){
					//azzera il coeff a[i][j] che non è già zero
					double cm=a[i][j]/a[j][j]; //coeff moltiplicativo
					//dalla riga i-esima, sottrai la riga j-esima moltiplicata per cm
					//fai combinazione lineare, tenendo presente che a sinistra di j, gli elementi sono già nulli
					for( int k=j; k<=n; ++k ) {
						a[i][k]=a[i][k]-a[j][k]*cm;
					}	
				}
			}
		}
	}//triangolarizza
	
	protected double[] calcolaSoluzione() {
		int n=getN();
		double[] x=new double[n];
		for( int i=n-1; i>=0; --i ) {//per tutte le equazioni dall'ultima verso la prima
			double sm=a[i][n];
			//portiamo a 2 membro tutti i termini relativi ad incognite già calcolate
			for( int j=i+1; j<n; ++j ) //ciclo saltato quando i=n-1
				sm=sm-a[i][j]*x[j];
			x[i]=sm/a[i][i];
		}
		return x;
	}//calcolaSoluzione
	
	public double[] risolvi() {
		triangolarizza(); //la matrice dei coeff + termini noti
		return calcolaSoluzione();
	}//risolvi
	
	public String toString() {
		StringBuilder sb=new StringBuilder(200);
		int n=getN();
		for( int i=0; i<n; ++i ) {
			for( int j=0; j<=n; ++j ) {
				sb.append(String.format("%7.2f", a[i][j])); //esempio
			}
			sb.append("\n");
		}
		return sb.toString();
	}//toString
	
}//Gauss
