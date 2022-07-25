package poo.esempi;

import java.util.Scanner;

import poo.sistema.Cramer;
import poo.sistema.Gauss;
import poo.sistema.GaussDiagonale;
import poo.sistema.Sistema;

public class SEL {
	public static void main( String[] args ) {
		System.out.println("Risoluzione di un sistema di equazioni lineari con Gauss");
		Scanner sc=new Scanner( System.in );
		try {
			System.out.print("Fornisci la dimensione del sistema n=");
			int n=sc.nextInt(); sc.nextLine();
			double[][] a=new double[n][n];
			double[] y=new double[n];
			double[] x=new double[n];
			//leggi a
			for( int i=0; i<n; ++i )
				for( int j=0; j<n; ++j ) {
					System.out.print("Fornisci a["+i+"]["+j+"]=");
					a[i][j]=sc.nextDouble(); sc.nextLine();
				}
			//leggi y
			for( int i=0; i<n; ++i ) {
				System.out.print("Fornisci y["+i+"]=");
				y[i]=sc.nextDouble(); sc.nextLine();
			}
			Sistema s=new Cramer(a,y);
			System.out.println("Sistema originale");
			System.out.println(s);
			try {
				x=s.risolvi();
			}catch( RuntimeException e ) {
				System.out.println("Sistema Singolare!");
				System.exit(-1);
			}
			System.out.println("Sistema finale");
			System.out.println(s);
			System.out.println();
			System.out.println("Vettore delle incognite");
			for( int i=0; i<n; ++i )
				System.out.printf("x=%1.2f%n", x[i]);
		}finally { 
			sc.close(); 
		}
	}//main
}//SEL
