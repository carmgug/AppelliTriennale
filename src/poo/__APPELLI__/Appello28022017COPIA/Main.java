package poo.__APPELLI__.Appello28022017COPIA;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;

import poo.util.List;

public class Main {
	final static String Regex="(NICKNAME="+"[A-z][\\w]*|[\\s]+|PHONE="+"[\\d]{1,9})+";
	public static void main(String[] args) throws IOException {
		Scanner sc=new Scanner(System.in);
		System.out.println("RUBRICA: PREMI INVIO");
		sc.nextLine();
		System.out.print("Inserisci Path File >> ");
		String NomeFile=sc.nextLine();
		PhoneBook rubrica=new LinkedPhoneBook();
		CostruisciRubrica(NomeFile,rubrica);
		loop1:for(;;) {
			System.out.print(">>");
			String s=sc.nextLine();
			if(s.equals(".")) break;
			List<Persona> l=rubrica.locate(s);
			if(l.size()==0) {System.out.println("Nessun Nominativo Trovato"); continue loop1;}
			for(Persona p:l) {
				System.out.print(p+"\n");
			}
			
		}
		sc.close();
		

	}
	
	static void CostruisciRubrica(String NomeFile,PhoneBook rubrica) throws IOException {
		BufferedReader br=new BufferedReader(new FileReader(NomeFile));
		for(;;) {
			String linea=br.readLine();
			if(linea==null) break;
			if(!linea.matches(Regex))throw new RuntimeException("File Malformato");
			StringTokenizer st=new StringTokenizer(linea," =");
			String nickname=null,Phone=null;
			while(st.hasMoreTokens()) {
				String s=st.nextToken();
				if(s.equals("NICKNAME")) nickname=st.nextToken();
				else Phone=st.nextToken();
			}
			if(nickname==null || Phone==null) throw new RuntimeException("File Malformato");
			//caso Phone=321345433 Phone=333333333
			Persona p=new Persona(nickname.toUpperCase(),Phone);
			rubrica.add(p);
			
			
			
		}
		br.close();
		
		
	}

}
