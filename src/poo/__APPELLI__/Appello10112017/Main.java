package poo.__APPELLI__.Appello10112017;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {

	
	
	
	
	
	
	private static void CreaMatrice(RandomAccessFile f,MatriceSparsa m) throws IOException {
		
		for(;;) {
			String linea=f.readLine();
			if(linea==null) break; //f.readLine() restitisce null se il file � finito
			StringTokenizer st=new StringTokenizer(linea," ");
			if(!linea.matches("(i=[\\d]+||j=[\\d]+|v=[\\d]+|\\s+)")) throw new RuntimeException("File MalFormato");
			Integer i=-1,j=-1,v=0;
			while(st.hasMoreTokens()) {
				String valore=st.nextToken();
				if(valore.contains("i="))
					i=Integer.parseInt(valore.substring(3));
				else if(valore.contains("j="))
					i=Integer.parseInt(valore.substring(3));
				else if(valore.contains("v="))
						j=Integer.parseInt(valore.substring(3));
			}
			m.set(i, j, v);
		}
		
		
		
		
		
		
	}
	
	public static void main(String[] args) throws IOException {
		
		
		
		Scanner sc=new Scanner(System.in);
		RandomAccessFile fa;
		RandomAccessFile fb;
		
		loop1: for(;;) {
			System.out.print("Inserisci NomeFile-1: >>");
			String File1=sc.nextLine();
			System.out.println("Inserisci NomeFile-2: >>");
			String File2=sc.nextLine();
			try {
				fa=new RandomAccessFile(File1,"r");
				fb=new RandomAccessFile(File2,"r");
			}catch(IOException e) {
				System.out.println("Errore File");
				continue loop1;
			}
			sc.close();
			break;
		}
		MatriceSparsa m=new MatriceSparsaLinked(1000);
		MatriceSparsa m2=new MatriceSparsaLinked(1000);
		
		CreaMatrice(fa,m);
		CreaMatrice(fb,m2);
		
		MatriceSparsa mul=m.mul(m2);
		MatriceSparsa add=m.add(m2);
		System.out.println("Matrice Somma M1+M2: ");
		System.out.println(add);
		System.out.println("Matrice Prodotto M1*M2: ");
		System.out.println(mul);
		System.out.println("La matrice M1 mul M2 � simmetrica?: "+mul.simmetrica());
		
		
		
		
		
		
	}
}
