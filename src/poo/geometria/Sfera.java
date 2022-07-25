package poo.geometria;

public class Sfera extends Disco implements FiguraSolida{
	public Sfera( double raggio ) {
		super(raggio);
	}
	public Sfera( double x, double y, double raggio ) {
		super(x,y,raggio);
	}
	public Sfera( Sfera s ) {
		super( s.getX(),s.getY(),s.getRaggio() );
	}
	public double perimetro() { throw new UnsupportedOperationException(); }
	public double area() { return 4*getRaggio()*Math.PI; }
	public double areaLaterale() { return area(); }
	public String toString() {
		return "Sfera avente ="+super.toString();
	}
	public double volume() { 
		double r=getRaggio();
		return (4*Math.PI*r*r*r)/3;
	}
}
