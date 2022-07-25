package poo.__APPELLI__.Appello30012017;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
	
	
	
	
	public static void CostruisciFile(String NomeFile,SortedSet<String> Set) throws IOException {
		
		BufferedReader br=new BufferedReader(new FileReader(NomeFile));
		
		for(;;) {
			String linea=br.readLine();
			if(linea==null) break;
			StringTokenizer st=new StringTokenizer(linea,",.:; ");
			while(st.hasMoreTokens()) {
				String Parola=st.nextToken();
				String Carattere=String.valueOf(Parola.charAt(0));
				if(!Carattere.matches("[A-z]")) throw new RuntimeException();
				Set.add(Parola);
			}
		}
		
		
		
		
	}
	
	public static void main(String[] args) throws IOException {
		
		
		Scanner sc=new Scanner(System.in); boolean ok=false;
		String NomeFile1,NomeFile2;
		
		do {
			System.out.print("Inserisci NomeFile1: >>");
			NomeFile1=sc.nextLine();
			System.out.print("Inserisic NomeFile2: >>");
			NomeFile2=sc.nextLine();
			File f1=new File(NomeFile1);
			File f2=new File(NomeFile2);
			if(!f1.exists() || !f2.exists()) System.out.println("File Inesistenti");
			else ok=true;
		}while(!ok);
		
		SortedSet<String> Set1=new LinkedSortedSet<String>((s1,s2)->{return s1.compareTo(s2);});
		SortedSet<String> Set2=new LinkedSortedSet<String>((s1,s2)->{return s1.compareTo(s2);});
		
		CostruisciFile(NomeFile1,Set1);
		CostruisciFile(NomeFile2,Set2);
		
		SortedSet<String> output=new LinkedSortedSet<String>((s1,s2)->{
			if(s1.length()>s2.length()) return 1;
			if(s1.length()<s2.length()) return -1;
			return s1.compareTo(s2);
		});
		
		for(String x:Set1) {
			if(Set2.contains(x)) continue;
			output.add(x);
		}
		System.out.println(output);
		
	
	}
	
	
	
}
