package poo.geometria;

public class Triangolo implements FiguraPiana{
	private Punto p1, p2, p3;
	private double a, b, c;
	public Triangolo( Punto p1, Punto p2, Punto p3 ){
		//verifica triangolo
	   	if( !esisteTriangolo(p1,p2,p3) ){
	        System.out.println("Triangolo inesistente");
	        System.exit(-1);
		}
	    this.p1=new Punto( p1 );
	    this.p2=new Punto( p2 );
	    this.p3=new Punto( p3 );
		a=p1.distanza(p2);
		b=p2.distanza(p3);
		c=p3.distanza(p1);
	}
	public Triangolo( Triangolo t ){
		p1=new Punto( t.p1 );
		p2=new Punto( t.p2 );
		p3=new Punto( t.p3 );
		this.a=t.a;
		this.b=t.b;
		this.c=t.c;
	}
	public static boolean esisteTriangolo( Punto p1, Punto p2, Punto p3 ){
		//attenzione: qui non si usa this: i tre lati vanno calcolati
		double a=p1.distanza(p2), b=p2.distanza(p3), c=p3.distanza(p1);
		if( a>b+c || b>a+c || c>a+b ) return false;
		return true;
	}//esisteTriangolo
	public double getA(){ return a; }
	public double getB(){ return b; }
	public double getC(){ return c; }
	public double perimetro(){
	   return a+b+c;
	}//perimetro
	public double area(){
	   double s=this.perimetro()/2;
	   return Math.sqrt(s*(s-a)*(s-b)*(s-c));
	}//area
	public String tipo(){//confronti "naif" per ora
	   if( a==b && b==c && a==c ) return "Equilatero";
	   if( a==b || a==c || b==c ) return "Isoscele";
	   return "Scaleno";
	}//tipo
	public String toString(){
	   return "Triangolo con vertici: "+p1+" "+p2+" "+p3+ " tipo "+tipo();
    }//toString
}//Triangolo