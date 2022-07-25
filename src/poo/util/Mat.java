package poo.util;
import java.util.Scanner;

public final class Mat{ //classe di utilità == tutti i metodi sono static

	private Mat(){}

	private static double EPSILON=1.0E-8; //tolleranza o approssimazione nei calcoli scientifici - 10^-10

	public static boolean sufficientementeProssimi( double x1, double x2 ){
		if( Math.abs( x1-x2 )<=EPSILON ) return true;
		return false;
	}//sufficientementeProssimi

	public static double getEpsilon(){ return EPSILON; }

	public static void setEpsilon( double EPSILON ){
		Mat.EPSILON=EPSILON;
	}//setEpsilon

	public static int mcd( int x, int y ){
		if( x<=0 || y<=0 ) throw new IllegalArgumentException();
		return mcd_euclide(x,y);
	}//mcd

	private static int mcd_euclide( int x, int y ){
		if( y==0 ) return x;
		return mcd_euclide( y, x%y ); //tail recursion o ricorsione in coda
	}//mcd_euclide

	public static int potenza( int a, int n ) {
		if( n<0 ) throw new IllegalArgumentException();
		return potLog(a,n);
	}//potenza
	
	private static int pot( int a, int n ) {
		if( n==0 ) return 1;
		return a*pot(a,n-1); //non tail recursion ossia e' una ricorsione normale
	}//pot
	
	private static int potLog( int a, int n ) {
		if( n==0 ) return 1;
		if( n==1 ) return a;
		int p=potLog(a,n/2);
		p=p*p;
		if( n%2==0 ) return p;
		return p*a;
	}//potLog
	
	private static int potIte( int a, int n ) {
		int p=1;
		for( int i=0; i<n; ++i ) p=p*a;
		return p;
	}//potIte

	
	public static int mcm( int x, int y ){
		if( x<=0 || y<=0 ) throw new IllegalArgumentException();
		return (x*y)/mcd_euclide(x,y);
	}//mcm
	
	public static void main( String[] args ){
/*
		Scanner sc=new Scanner( System.in );
		System.out.print("r1=");
		double r1=sc.nextDouble();
		System.out.print("r2=");
		double r2=sc.nextDouble();

		//if( r1==r2 )//test naif
		if( Mat.sufficientementeProssimi(r1,r2) ) //modo "corretto" di interrogare l'uguaglianza di double nei progammi
		    System.out.println("numeri quasi uguali");
		else
			System.out.println("numeri diversi");
		System.out.println("mcd("+100+","+"48)="+Mat.mcd(100,48)+
				           " mcm("+100+","+48+")="+mcm(100,48));
*/
		int p=potenza(2,3);
		System.out.println("2^3="+p);
	}//main

}//Mat
