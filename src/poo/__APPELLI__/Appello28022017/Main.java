package poo.__APPELLI__.Appello28022017;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
	final static String Regex="(NICKNAME=[A-z][\\W]+ |[\\s]+| PHONE=[\\d]{1,9})";
	final static String RegexNominativo="([\\*][\\W]+ | [\\*][\\W]+[\\*] | [\\w]+|[\\w]+[\\*])";
	public static void Costruisci(PhoneBook rubrica,String NomeFile) throws IOException {
		BufferedReader br=new BufferedReader(new FileReader(NomeFile));
		
		for(;;) {
			String linea=br.readLine();
			if(linea==null) {break;}
			if(!linea.matches(Regex)) throw new RuntimeException("File-Malformato");
			StringTokenizer st=new StringTokenizer(linea,"=");
			Persona p=new Persona();
			while(st.hasMoreTokens()) {
				String variabile=st.nextToken();
				if(variabile.equals("NICKNAME")) p.nickname=st.nextToken().toUpperCase();
				else p.phone=st.nextToken().toUpperCase();
			}
			rubrica.add(p);
		}
		
		br.close();
		
	}
	public static void main(String[] args) throws IOException {
		
		
		Scanner sc=new Scanner(System.in);
		System.out.println("PhoneBook: PREMI INVIO");
		sc.nextLine();boolean ok=false;
		String NomeFile=null;
		do {
			System.out.print("Inserisci Nome File Rubrica: >>");
			NomeFile=sc.nextLine();
			File f=new File(NomeFile);
			if(f.exists()) {ok=true;break;}
			System.out.println("File inesistente");
		}while(!ok);
		
		PhoneBook rubrica=new LinkedPhoneBook();
		Costruisci(rubrica,NomeFile);
		System.out.println("Store Dati Effettuato con Successo");
		
	loop1:
		for(;;) {
			System.out.print("Cerca Nominativo >>>");
			String Nominativo=sc.nextLine();
			if(Nominativo.equals(".")) break;
			if(!Nominativo.matches(RegexNominativo)) {System.out.println("Nominativo Non Valido");continue loop1;}
			List<Persona> l=rubrica.locate(Nominativo);
			for(Persona x:l) {
				System.out.println(x);
			}
			System.out.println();
		}
		
	
		System.out.println("Bye..");
		sc.close();
		
	}

}
