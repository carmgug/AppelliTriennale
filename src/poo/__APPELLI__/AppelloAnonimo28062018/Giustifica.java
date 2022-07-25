package poo.__APPELLI__.AppelloAnonimo28062018;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Giustifica {

	public static int contaLineaMassima(String nomeFile, LinkedList<String> l) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(nomeFile));
		int max = 0;
		for (;;) {

			String linea = br.readLine();
			if (linea == null)
				break;
			l.add(linea);
			if (max < linea.length())
				max = linea.length();

		}
		br.close();
		return max;
	}

	public static void giustifica(String nomeFile, LinkedList<String> l, int max) throws IOException {

		PrintWriter pr = new PrintWriter(new FileWriter(nomeFile));
		Iterator<String> it = l.iterator();

		while (it.hasNext()) {
			String lineacurr = it.next();
			System.out.println(lineacurr);
			/*
			 * if(lineacurr.length()<max && !lineacurr.contains(".")) { //il punto se c'� �
			 * sempre a fine linea StringBuilder sb=new StringBuilder(max); String[]
			 * array=lineacurr.split(" "); int spazi=max/array.length;
			 * if(max%array.length!=0) { } }
			 */
			if (lineacurr.length() < max && !lineacurr.contains("."))
				pr.println(allarga(lineacurr, max));
			else
				pr.println(lineacurr);
		}
		pr.close();

	}

	public static String allarga(String linea, int max) {
		StringBuilder giustificata = new StringBuilder(max);
		StringTokenizer st = new StringTokenizer(linea, " ");
		int buchi=st.countTokens()-1;
		int spazi=(max-linea.length())/buchi;
		System.out.println(spazi+"CAZZO");
		int aggiunti=0;
		while(st.hasMoreTokens() && st.countTokens()!=1) {
			String parola=st.nextToken();
			parola+=" ";
			 //spazio che � stato eliminato dall'st
			for(int i=1;i<=spazi;++i) {
				parola+=" ";
				aggiunti++;
			}
			
			giustificata.append(parola);
		}
		if(aggiunti<max-linea.length()) // gestione resto
			for(int i=0;i<max-linea.length()-aggiunti;++i)
				giustificata.append(" ");
		giustificata.append(st.nextToken());	
		

		return giustificata.toString();

	}

	public static void main(String[] args) throws IOException {

		String f1;
		Integer max = null;
		LinkedList<String> Lista = new LinkedList<>();
		Scanner sc = new Scanner(System.in);
		System.out.println("Giustica: Premi Invio");
		sc.nextLine();

		loop1: for (;;) {
			System.out.println("Inserisci Nome File: ");
			f1 = sc.nextLine();
			if (f1.equals(".")) {
				sc.close();
				break;
			}
			try {
				max = contaLineaMassima(f1, Lista);
				break;
			} catch (IOException e) {
				System.out.println("Nome File o contenuto non valido");
				continue loop1;
			}

		}

		System.out.println(Lista);
		giustifica("c:\\Users\\carme\\Desktop\\Tmp.txt", Lista, max);

		File y = new File(f1);
		y.delete();
		File y2 = new File("c:\\Users\\carme\\Desktop\\Tmp.txt");
		System.out.println(y2.renameTo(y));

		System.out.println(y.getAbsolutePath() + "   " + y2.getAbsolutePath());

		System.out.println(allarga("Uno due, tre", 25));
		sc.close();
		System.exit(0);

	}
}
