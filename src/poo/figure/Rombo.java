package poo.figure;

public class Rombo extends Figura{
	private double diagonaleMaggiore;
	public Rombo( double dm, double dM ) {
		super(dm);
		diagonaleMaggiore=dM;
	}
	public Rombo( Rombo r ) {
		super( r.getDimensione() );
		diagonaleMaggiore=r.diagonaleMaggiore;
	}
	public double getDiagonaleMinore() { return getDimensione(); }
	public double getDiagonaleMaggiore() { return diagonaleMaggiore; }
	public double getLato() {
		double dm=getDimensione();
		double dM=diagonaleMaggiore;
		return Math.sqrt( (dm/2)*(dm/2)+(dM/2)*(dM/2)); //teorema di pitagora;
	}
	public double perimetro() {
		double l=getLato();
		return 4*l;
	}
	public double area() {
		return (getDimensione()*diagonaleMaggiore)/2;
	}
	public String toString() {
		return "Rombo avente diagonaleMinore="+getDimensione()+
				" e diagonaleMaggiore="+diagonaleMaggiore;
	}//toString
}//Rombo
