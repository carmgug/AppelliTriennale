package poo.geometria;

public class Disco extends Punto implements FiguraPiana{
	
	private double raggio;
	public Disco( double raggio ){//centro nell’origine
		super(); //evoca il costruttore di default di Punto. Si può anche omettere
		this.raggio=raggio;
	}//Disco

	public Disco( double x, double y, double raggio ){
		super(x,y);
		this.raggio=raggio;
	
	}
	public Disco( Punto p, double raggio ){ 
		super(p.getX(),p.getY()); 
		this.raggio=raggio; 
	}
	public Disco( Disco d ){ 
		super(d.getX(),d.getY()); 
		this.raggio=d.raggio; 
	}
	public double getRaggio( ){ return raggio; }
	public double perimetro(){ return 2*Math.PI*raggio; }
	public double area(){ return raggio*raggio*Math.PI; }
	public String toString(){ 
		return "Disco di raggio "+raggio+" e centro "+super.toString(); 
	}//toString
	public static void main( String[] args ) {//test
		Punto p=new Punto(3,5);
		Disco d=new Disco(2,4,7);
		System.out.println("x del centro del disco: "+d.getX());
		double dis=p.distanza(d);
		System.out.println("distanza punto disco="+dis);
		p=d;
		System.out.println(p);
		
	}//main
}//Disco

