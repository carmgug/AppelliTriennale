package poo.__APPELLI__.Appello28062018;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class labrinito {
	
	
	HashMap<Integer,TreeSet<Integer>> labirinto;
	Integer stanza;
	LinkedList<Integer> Soluzione;
	boolean flag;
					//1     2     3     4     5
	static String Regex ="([\\d]+|[\\s]+)+";
	
	public labrinito(HashMap<Integer,TreeSet<Integer>> labirinto) {
		if(labirinto.size()==0 && labirinto.size()-1<9999) throw new IllegalArgumentException();
		this.labirinto=labirinto;
		HashMap.Entry<Integer,TreeSet<Integer>> entry=labirinto.entrySet().iterator().next();
		stanza=entry.getKey(); //prendo la prima stanza
		Soluzione=new LinkedList<Integer>();
		flag=false;
		
	}
	private static void costruisci(String nomeFile,HashMap<Integer,TreeSet<Integer>> labirinto) throws IOException {
		
		BufferedReader br=new BufferedReader(new FileReader(nomeFile));
		
		for(;;) {
			String linea=br.readLine();
			if(linea==null) break;
			StringTokenizer st=new StringTokenizer(linea," ");
			//cond
			if(st.countTokens()!=5 && !linea.matches(Regex)) {throw new RuntimeException();}
			Integer stanza=Integer.parseInt(st.nextToken());
			//cond 
			if(stanza>=9999 || stanza<=0 || labirinto.containsKey(stanza)) {throw new RuntimeException();}
			
			TreeSet<Integer> stanze_comunicanti=new TreeSet<>();
			while(st.hasMoreTokens()) {
				Integer porta=Integer.parseInt(st.nextToken());
				if(porta>9999 || porta<=0) throw new RuntimeException();
				
				stanze_comunicanti.add(porta);
			}
			labirinto.put(stanza, stanze_comunicanti);
		}
		br.close();
		
	}
	
	public void  risolvi() {
		Soluzione.add(stanza);//stanza di partenza � la prima
		tentativo(stanza);
		if(flag==false) System.out.println("Nessuna Soluzione Trovata"); 
	}
	
	private void tentativo(int porta) {
		
		TreeSet<Integer> SceltePossibili=labirinto.get(porta);
		for(Integer scelta:SceltePossibili) {
			if(!Soluzione.contains(scelta) && flag!=true) {
				Soluzione.add(scelta);
				if(scelta==9999) {ScriviSoluzione();flag=true;}
				else tentativo(scelta);
				Soluzione.remove(scelta);
			}
		}
	}
	
	private void ScriviSoluzione() {
		StringBuilder sb=new StringBuilder();
		sb.append("[");
		Iterator<Integer> it=Soluzione.iterator();
		while (it.hasNext()) {
			Integer curr=it.next();
			if(it.hasNext()) sb.append(curr+"-");
			else sb.append(curr);
		}
		sb.append("]");
		System.out.println(sb.toString());
	}
	
	public static void main(String[] args) {
		
		
		HashMap<Integer,TreeSet<Integer>> labirinto=new HashMap<>();
		
		Scanner sc=new Scanner(System.in);
		System.out.println("Premi Invio:");
		sc.nextLine();
		loop1: for(;;) {
			System.out.print("Inserisci Nome File>> ");
			String NomeFile=sc.nextLine();
			try {
				costruisci(NomeFile,labirinto);
				
			}catch(Exception e) {
				System.out.print("File-Malformato/Non trovato");
				continue loop1;
			}
			break;
		}
		labrinito l=new labrinito(labirinto);
		System.out.println();
		l.risolvi();
		
		
		
		
		
		
	}
	
}
