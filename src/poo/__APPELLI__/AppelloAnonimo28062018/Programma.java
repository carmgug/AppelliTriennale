package poo.__APPELLI__.AppelloAnonimo28062018;


import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import poo.polinomi.*;

public class Programma {
	
	static String Numero="[0-9]+";
	static String Grado="x(\\^[\\d]+)?";
	static String Monomio="([\\-]?"+Numero+"("+Grado+")?|[\\-]?"+Grado+")";
	
	static String Regex=Monomio+"([\\+\\-]"+Numero+"|"+"[\\-\\+]("+Numero+")?("+Grado+")?)*";
	
	public static Polinomio Converti(String p,Matcher M) {
		Polinomio P=new PolinomioLL();
		while(M.find()) {
			
			String MonomioTrovato=M.group();
			
			Integer Coeff=null,Grado=0;
			StringTokenizer st=new StringTokenizer(MonomioTrovato,"x",true);
			while(st.hasMoreTokens()) {
				String Token=st.nextToken();
				
				if(Coeff==null && !Token.matches("x")) {
					if(Token.equals("+")) {Coeff=1;continue;} //se contiene solo un segno allora � uguale a +1/-1;
					if(Token.equals("-")) {Coeff=-1;continue;}
					Coeff=Integer.parseInt(Token);
					continue;
				}
				if(Token.matches("x")) {
					if(Coeff==null) Coeff=1;
					Grado=1; continue;
				}
				if(Token.contains("^")){
					Grado=Integer.parseInt(Token.substring(1, Token.length())); //inzio incluso fine escluso
					continue;
				}
			}
			System.out.println(Coeff+" "+Grado+"QUI STO PRINTANDO");
			P.add(new Monomio(Coeff,Grado));
		}
		
		
		
		return P;
	}
	public static void main(String[] args) {
		
		Scanner sc=new Scanner(System.in);
		System.out.println("Benvenuto in Inserisci Polinomi:Premi Invio");
		System.out.print("Inserisci Polinomio>> ");
		String Poly1=sc.nextLine();
		if(!Poly1.matches(Regex)) {throw new IllegalArgumentException();}
		System.out.print("Inserisci Secondo Polinomio>> ");
		String Poly2=sc.nextLine();
		if(!Poly2.matches(Regex)) throw new IllegalArgumentException();
		Pattern pattern=Pattern.compile(Monomio);
		Matcher m=pattern.matcher(Poly1);
		Polinomio p=Converti(Poly1,m);
		
		Matcher m2=pattern.matcher(Poly2);
		Polinomio p2=Converti(Poly2,m2);
		
		System.out.println(p+"      "+p2);
		sc.close();
		
		
	}
}
