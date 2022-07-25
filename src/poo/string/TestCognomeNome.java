package poo.string;
import java.util.Scanner;
public class TestCognomeNome {
	public static void main( String[] args ) {
		System.out.print("Fornisci Nome e Cognome di una persona: ");
		Scanner sc=new Scanner( System.in );
		String linea=sc.nextLine();
		linea=linea.trim();
		int i=linea.indexOf(' ');
		String nome=linea.substring(0,i).toUpperCase();
		int j=i;
		while( j<linea.length() && linea.charAt(j)==' ') ++j;
		String cognome=linea.substring(j);
		System.out.println(cognome+" "+nome.charAt(0)+".");
		sc.close();
	}
}
