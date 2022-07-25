package poo.esempi;

import java.util.Scanner;

public class TestIO {
	public static void main( String[] args ) {
		System.out.println("Demo Operazioni di Input/Output");
		Scanner sc=new Scanner( System.in );
		System.out.print("Fornisci un numero reale : ");
		double x=sc.nextDouble();
		System.out.printf(" x=%1.3f\n",x);
		System.out.printf("PI=%1.3f\n",Math.PI);
		System.out.print("Fornisci un intero : ");
		int h=sc.nextInt(); sc.nextLine();
		System.out.print("Fornisci un intero : ");
		int k=sc.nextInt(); sc.nextLine();
		System.out.printf("h=%-7dk=%1d\n", h,k);
		System.out.print("Fornisci una stringa : ");
		String st=sc.nextLine().toUpperCase();
		System.out.printf("st=%8s\n", st);
		sc.close();
	}
}
