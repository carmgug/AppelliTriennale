package poo.geometria;

public class Punto{
   private double x, y;
   public Punto(){//costruttore di default
      this(0,0);
   }
   public Punto( double x, double y ){//costruttore normale
      this.x=x; this.y=y;
   }
   public Punto( Punto p ){//costruttore di copia
      x=p.x; y=p.y;
   }
   public double getX(){ return x; }
   public double getY(){ return y; }
   public void sposta( double nuovaX, double nuovaY ){
      x=nuovaX; y=nuovaY;
   }//sposta
   public double distanza( Punto p ){
      return Math.sqrt((p.x-x)*(p.x-x)+(p.y-y)*(p.y-y));
   }//distanza
   
   public String toString(){
      return "Punto("+String.format("%1.2f",x)+","+String.format("%1.2f",y)+")";
   }//toString

   public static void main( String[] args ){
	   Punto p=new Punto(5.7354,7.21987654);
	   System.out.println(p);
	   p.sposta(-3,4);
	   System.out.println(p);
	   Punto q=new Punto(0,6);
	   double d=p.distanza(q);
	   System.out.printf("Distanza tra "+p+" e "+q+" = %1.2f%n",d);
   }//main
   
}//Punto