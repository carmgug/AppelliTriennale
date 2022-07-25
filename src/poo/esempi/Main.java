package poo.esempi;

import poo.geometria.*;

import java.util.Scanner;

import poo.figure.*;

public class Main{
	public static void main( String...args ) {
		/*
		FiguraPiana []f=new FiguraPiana[4]; //super tipo più in alto
		f[0]=new Disco(3.4);
		f[1]=new Cerchio(5);
		f[2]=new Triangolo( new Punto(), new Punto(4,7), new Punto(0,-4));
		f[3]=new Rombo(14,24);
		
		double am=0.0D;
		FiguraPiana fam=null;
		for( int i=0; i<f.length; ++i ) {
			double a=f[i].area();
			if( a>am ) { am=a; fam=f[i]; }
		}
		System.out.println("La figura "+fam+" ha area massima="+String.format("%1.2f",am));
		*/
		
		String regex="yi=[\\d]+";
		Scanner sc=new Scanner(System.in);
		for(;;) {
			String linea=sc.nextLine();
			if(linea.equals(".")) break;
			System.out.println(linea+" "+linea.matches(regex));
		}

	}
}
