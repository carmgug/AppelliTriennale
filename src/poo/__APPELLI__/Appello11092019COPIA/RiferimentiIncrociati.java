package poo.__APPELLI__.Appello11092019COPIA;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import poo.util.GestoreTesto;

public class RiferimentiIncrociati {
	public static void main(String[] args) throws IOException {
		
		System.out.println("RIFERIMENTI INCROCIATI");
		Scanner sc=new Scanner(System.in);
		
		File f=null;
		boolean ok=false;
		do {
			System.out.print("Inserisci NOMEFILE >> ");
			String NomeFile=sc.nextLine();
			f=new File(NomeFile);
			if(!f.exists()) {System.out.println("File inesistente!!");}
			else ok=true;
		}while(!ok);
		
		
		GestoreTesto g=new GestoreTesto(f,":");
		TreeMap<String,TreeSet<Integer>> Riferimenti=new TreeMap<>((s1,s2)->{
			if(s1.length()>s2.length()) return 1;
			if(s1.length()<s2.length())return -1;
			return s1.compareTo(s2);
		});
		
		
		RiempiMappa(g,Riferimenti);
		StringBuilder sb=StampaMappa(Riferimenti);
		System.out.println(sb.toString());
		
		loop1:for(;;) {
			System.out.println("Inserisci NomeFile di salvataggio>>");
			String NomeFile=sc.nextLine();
			try {
				SalvaSuFile(NomeFile,sb);
			}catch(IOException e) {
				System.out.println("Nome non valido");
				continue loop1;
			}
			break;
		}
		
		System.out.println("Bye...");
		sc.close();
		
		
	}
	
	
	private static void  RiempiMappa(GestoreTesto g,TreeMap<String,TreeSet<Integer>> mappa) throws IOException {
		
		while(g.prossimoSimbolo()!=GestoreTesto.Simbolo.EOF) {
			String parola=g.getString();
			if(g.prossimoSimbolo()==GestoreTesto.Simbolo.EOF) throw new RuntimeException("File Mlaformato");
			Integer numero=Integer.parseInt(g.getString());
			if(!mappa.containsKey(parola)) {
				TreeSet<Integer> Set=new TreeSet<>();
				mappa.put(parola, Set);
			}
			mappa.get(parola).add(numero);//non succede niente se l'elemento � gi� contenuto			
		}
		
	}
	
	private static StringBuilder StampaMappa(TreeMap<String,TreeSet<Integer>> mappa) {
		
		StringBuilder sb=new StringBuilder(20);
		Set<String> keys=mappa.keySet();
		for(String k:keys) {
			sb.append(k+"\n");
			Set<Integer> val=mappa.get(k);
			sb.append("---> ");
			for(Integer x:val) {
				sb.append(x+" ");
			}
			sb.append("\n");
		}
		return sb;
	}
	
	
	private static void SalvaSuFile(String NomeFile,StringBuilder sb) throws IOException {
		
		PrintWriter pw=new PrintWriter(new FileWriter(NomeFile));
		pw.println(sb.toString());
		pw.close();
	}
	
	
	
	
}
