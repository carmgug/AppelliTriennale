package poo.giochi;

import java.util.*;
import poo.geometria.*;

public class MonteCarloPiGreco{
	
	private int n,contaEventi;
	
	public MonteCarloPiGreco(int n) {
		this.n=n;
		contaEventi=0;
		
	}
	
	public double pi() {
		
		Punto origine=new Punto();
		
		for(int e=0;e<=n;e++) {
			
			//math.random()*(Max-MIn)+MIN 1 UpperBound 0 LowerBound
			double x=Math.random()*2-1;
			double y=Math.random()*2-1;
			Punto p=new Punto(x,y);
			//se il punto Generato appartiene al cerchio contaEventi++
			double d=origine.distanza(p);
			if(d<=1) contaEventi++;
		}
		double pi=4.0D*(double)(contaEventi)/(n);
		
		return pi;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static void main( String[] args ){
		System.out.println("Approssimazione Monte Carlo del numero pi greco");
		Scanner sc=new Scanner( System.in );
		System.out.print("Fornisci il numero totale di esperimenti N=");
		int N=sc.nextInt();
		int contaEventi=0;
		Punto origine=new Punto();
		for( int e=0; e<N; ++e ){
			double x=Math.random()*2-1;
			double y=Math.random()*2-1;
			Punto p=new Punto(x,y);
			if( origine.distanza(p)<=1 )
				contaEventi=contaEventi+1;
		}
		double pi=4*((double)contaEventi/N);
		System.out.println("pi="+pi);
	}//main
}//MonteCarloPiGreco