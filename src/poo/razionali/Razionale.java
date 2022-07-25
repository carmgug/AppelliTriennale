package poo.razionali;
import poo.util.Mat;
public class Razionale implements Comparable{

   private final int NUM, DEN; //variabili di istanza o campi dell'oggetto

   private static int contaRazionali=0; //variabili della classe - campo static - condiviso da tutti i razionali

   public Razionale( int num, int den ){//costruttore normale
      if( den==0 )
      	  throw new RuntimeException("Denominatore nullo!");

      if( num!=0 ){
    	  int n=Math.abs( num ), d=Math.abs( den );
    	  int cd=Mat.mcd( n, d );
    	  num=num/cd; den=den/cd;
      }

      if( den<0 ){
         num *= -1;
         den *= -1;
      }

      this.NUM=num;
      this.DEN=den;

      contaRazionali++;

   }

   public Razionale( Razionale r ){//costruttore di copia
      this.NUM=r.NUM;
      this.DEN=r.DEN;

      contaRazionali++;
   }

   public static int razionaliCreati(){ return contaRazionali; }

   public int getNum(){ return NUM; }//getter
   public int getDen(){ return DEN; }

   public Razionale add( Razionale r ){
      int mc=Mat.mcm(r.DEN,DEN);
      int num=(mc/DEN)*NUM + (mc/r.DEN)*r.NUM;
      int den=mc;
      return new Razionale( num, den );
   }//add

   public Razionale sub( Razionale r ){//this - r
	      int mc=Mat.mcm(r.DEN,DEN);
	      int num=(mc/DEN)*NUM - (mc/r.DEN)*r.NUM;
	      int den=mc;
	      return new Razionale( num, den );
   }//sub

   public Razionale mul( Razionale r ){
      return new Razionale( NUM*r.NUM, DEN*r.DEN );
   }//mul


   public Razionale mul( int s ){//this * s
      return new Razionale( NUM*s, DEN );
   }//mul


   public Razionale div( Razionale r ){//this div r ossia NUM/DEN * r.DEN/r.NUM
      return new Razionale( NUM*r.DEN, DEN*r.NUM );
   }//div

   @Override
   public String toString(){
      if( DEN==1 ) return ""+NUM;
      if( NUM==0 ) return "0";
      return ""+NUM+"/"+DEN;
   }//toString

   @Override 
   public boolean equals( Object o ) {
	   if( !(o instanceof Razionale) ) return false;
	   if( o==this ) return true;
	   Razionale r=(Razionale)o;
	   return this.NUM==r.NUM && this.DEN==r.DEN;
   }//equals
   
   @Override
   public int hashCode() {
	   //se due oggetti sono equals, devono avere lo stesso hashCode
	   //tuttavia, se due oggetti hanno lo stesso hashCode, non è detto che siano equals
	   final int M=83;
	   //shuffling dei campi NUM e DEN secondo M
	   int h=0;
	   h=h*M+NUM;
	   h=h*M+DEN;
	   return h;
   }//hashCode
   
   @Override
   public int compareTo( Object x ) {
	   //lasciato come esercizio
	   //ma quando ritorna 0, deve essere coerente con equals
	   return 0; //TODO
   }
   
   protected void finalize(){
	   contaRazionali--;
   }

   public static void main( String[] args ){
	   //calcolo espressione (4/8+14/24)*6/9=(1/2+7/12)*2/3
	   Razionale r1=new Razionale(4,8);
	   Razionale r2=new Razionale(14,24);
	   Razionale r3=new Razionale(6,9);

	   Razionale r=r1.add(r2).mul(r3); //mul si applica al razionale restituito da add - concatenazione dei metodi

	   System.out.println("("+r1+"+"+r2+")*"+r3+"="+r);

	   System.out.println("Razionali creati="+Razionale.razionaliCreati());

   }//main

}//Razionale