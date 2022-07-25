package poo.regex;
import java.util.Scanner;

public class RiconoscitoreEspressione {
	public static void main( String[] args ) {
		String EXPR="(\\d+|[\\+\\-\\*/\\(\\)])+"; //solo condizione necessaria
		Scanner sc=new Scanner( System.in );
		for(;;) {
			System.out.print("EXPR o solo invio per terminare: ");
			String linea=sc.nextLine();
			if( linea.length()==0 ) break;
			if( linea.matches(EXPR) )
				System.out.println(EXPR+" potenzialmente ben formata");
			else
				System.out.println(EXPR+" non riconosciuta");
		}
		System.out.println("Bye.");
		sc.close();
	}
}
