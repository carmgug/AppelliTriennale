package poo.__APPELLI__.AppelloAnonimoCopia;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		
		Scanner sc=new Scanner(System.in);
		System.out.println("Espressione Aritmetica: Premi Invio");
		sc.nextLine();
		boolean ok=true;
		EspressioneAritmetica Expr=null;
		loop1: for(;;) {
			System.out.print("Inserisci NomeFile: >>");
			String NomeFile=sc.nextLine();
			try {
				Expr=new EspressioneAritmetica(NomeFile);
			}catch(Exception e){
				System.out.println("File Inesistente/Malformato");
				continue loop1;
			}
			break;
		}
		
		System.out.println("File letto correttamente");

		
		loop2: for(;;) {
			System.out.print(">>");
			String RPN=sc.nextLine();
			if(RPN.equals(".")) break;
			try {
				System.out.println(Expr.risolvi(RPN));
			}catch(Exception e){
				System.out.println("Espressione MalFormata");
				continue loop2;
			}
			
		}
		
		sc.close();
		System.out.println("Bye");
		System.exit(0);
	}
}
