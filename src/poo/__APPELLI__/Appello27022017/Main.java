package poo.__APPELLI__.Appello27022017;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {

	
	
	
	public static void main(String[] args) throws IOException {
		Scanner sc=new Scanner(System.in);
		System.out.print("Elenco :Premi Invio");
		sc.nextLine();
		
		System.out.print("Inserisci Nome File Rubrica:>> ");
		String NomeFile= sc.nextLine();
		Elenco<Stanza> Stanze=new ElencoLinkato<>(new Comparator<Stanza>() {

			@Override
			public int compare(Stanza o1, Stanza o2) {
				Integer n1=Integer.parseInt(o1.getNumero());
				Integer n2=Integer.parseInt(o2.getNumero());
				if(n1==n2) return 0;
				if(n1<n2) return -1;
				return 1;
			}
			
		});
		
		
		BufferedReader br=new BufferedReader(new FileReader(NomeFile));
		for(;;) {
			String linea=br.readLine();
			if(linea==null) { br.close();break;}
			StringTokenizer st=new StringTokenizer(linea," ");
			
			String Cognome=st.nextToken(),Nome=st.nextToken(),Numero=st.nextToken();
			Impiegato I =new Impiegato(Cognome,Nome,Integer.parseInt(Numero));
			Stanza s=new Stanza(Numero, new Comparator<Impiegato>() {

				@Override
				public int compare(Impiegato o1, Impiegato o2) {
					if(o1.getCognome().length()>o2.getCognome().length()) return 1;
					if(o1.getCognome().length()<o2.getCognome().length()) return -1;
					if(o1.getNome().length()>o2.getNome().length()) return 1;
					if(o1.getNome().length()<o2.getNome().length())return -1;
					return 0;
				}
				
			});
			if(Stanze.contains(s)) {
				s=Stanze.get(s);
				s.addImpiegato(I);
			}
			else {
				Stanze.add(s);
				s.addImpiegato(I);
			}
		}
		
		System.out.println();
		System.out.println(Stanze);
		
		
		
		
		
	}

}
