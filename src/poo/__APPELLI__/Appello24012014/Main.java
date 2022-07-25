package poo.__APPELLI__.Appello24012014;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.StringTokenizer;

import poo.util.LinkedList;
import poo.util.List;

public class Main{

	private static class Nodo{
		String Origine,Destinazione;
		int l;
		
		
	}
	
	
	
	public static void CostruisciDaFile(String NomeFile,HashMap<String,LinkedList<Nodo>> mappa) throws IOException {
		BufferedReader br=new BufferedReader(new FileReader(NomeFile));
		
		for(;;) {
			String linea=br.readLine();
			if(linea==null) break;
			StringTokenizer st=new StringTokenizer(linea," ");
			if(st.countTokens()!=4) throw new RuntimeException("File Malformato");
			String Origine=st.nextToken(),Destinazione=st.nextToken(),Percorso=st.nextToken();
			int l=Integer.parseInt(st.nextToken());
			Nodo nc=new Nodo();nc.Origine=Origine;nc.Destinazione=Destinazione;nc.l=l;
			if(mappa.get(Percorso)==null) {
				LinkedList<Nodo> lista=new LinkedList<>();
				lista.addLast(nc);
				mappa.put(Percorso, lista);
			}
			else {
				LinkedList<Nodo> lista=mappa.get(Percorso);
				if(Verificato(lista,nc)) throw new RuntimeException("File Malformato");
				lista.addLast(nc);
			}
		}
		
		
		br.close();
		
		
	}
	
	
	private static boolean Verificato(List<Nodo> l,Nodo corr) {
		
		for(Nodo n:l) {
			if(n.Destinazione.equals(corr.Origine))
				if(n.Origine.equals(corr.Destinazione))
					return true;			
			//se la connessione � gi� presente eliminamola
			if(n.Origine.equals(corr.Origine))
				if(n.Destinazione.equals(corr.Destinazione))
					return true;
		}
		return false;
	}
	
	
	private static void VerificaPercorso(List<Nodo> Scelte,String partenza,String destinazione,List<Nodo> Soluzione) {
		
		for(Nodo ps:Scelte) {
			if(ps.Origine.equals(partenza)) {
				Soluzione.addLast(ps);
				if(ps.Destinazione.equals(destinazione)) ScriviSoluzione(Soluzione);
				else VerificaPercorso(Scelte,ps.Origine,destinazione,Soluzione);
				Soluzione.removeLast();
			}
		}
	}
	
	private static void ScriviSoluzione(List<Nodo> Soluzione) {
		int p=0;
		Iterator<Nodo> it=Soluzione.iterator();
		while(it.hasNext()) {
			Nodo corr=it.next();p+=corr.l;
			System.out.print(corr.Origine+","+corr.Destinazione+" , l:"+corr.l);
			if(it.hasNext()) System.out.print("; ");
		}
		System.out.println();
		System.out.println("Lunghezza Complessiva"+p);
		
	}
	
	
	public static void main(String[] args) throws IOException {
		
		
		
		HashMap<String,LinkedList<Nodo>> Connessioni=new HashMap<>();
		
		
		Scanner sc=new Scanner(System.in);
		System.out.print("Inserisci NomeFile >>");
		String NomeFile=sc.nextLine();
		CostruisciDaFile(NomeFile,Connessioni);
		System.out.print("Inserisci NomeNodo1: >>");
		String Nodo1=sc.nextLine();
		System.out.print("Inserisci NomeNodo2: >>");
		String Nodo2=sc.nextLine();
		System.out.print("Inserisci Percorso: >>");
		String Percorso=sc.nextLine();
		
		
		
		
		
		
		
		
	}
}
