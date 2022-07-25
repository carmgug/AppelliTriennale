package poo.__APPELLI__.Appello23072019_12CFU;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class Main {
	public static void Main(String[] args) throws IOException {
		
		Scanner sc=new Scanner(System.in);
		System.out.print("Ordinamento File: Inserisci NomeFile >>");
		String NomeFile=sc.nextLine();
		boolean ok=false;
		boolean PrimaSoluzione=false,SecondaSoluzione=false;
		do {
			System.out.println("A -->Ordinamento File tramite Collection");
			System.out.println("B -->Ordinamento File su Disco");
			String Scelta=sc.nextLine().toUpperCase();
			switch(Scelta) {
				case "A":
					PrimaSoluzione=true;ok=true;break;
				case "B":
					SecondaSoluzione=true;ok=true;break;
				default : System.out.println("Comando Non Valido");
			}
		}while(!ok);
		
		
		ScriviFile(NomeFile);
		
		if(PrimaSoluzione) {
			ListaConcatenata<Integer> Sort=SortFileOnCollection.Ordina(NomeFile);			
			StringBuilder sb=new StringBuilder(20);
			sb.append("[");
			for(Integer x:Sort) {
				sb.append(x);
				sb.append(", ");
			}
			sb.setLength(sb.length()-2);
			System.out.println(sb.toString());
		}
		else {
			SortFile.Ordina(NomeFile);
			ScriviFile(NomeFile);
		}
		
		
		
		
		
		
		
		
	}//main
	
	
	private static void ScriviFile(String NomeFile) throws IOException {
		DataInputStream dis=new DataInputStream(new FileInputStream(NomeFile));
		StringBuilder sb=new StringBuilder(20);
		sb.append("[");
		for(;;) {
			int x=0;
			try {
				x=dis.readInt();
			}catch(IOException e) {
				break;
			}
			sb.append(x);
			sb.append(", ");
		}
		sb.setLength(sb.length()-2);
		sb.append("]");
		System.out.println(sb.toString());
		
		
		dis.close();
	}

}
