package poo.__APPELLI__.Appello201801;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class MatriceSparsaLinked extends MatriceSparsaAstratta {

	HashMap<Integer,LinkedList<Elemento>> righe;
	HashMap<Integer,LinkedList<Elemento>> colonne;
	
	
	public MatriceSparsaLinked(int n) {
		if(n<=0) throw new IllegalArgumentException();
		righe=new HashMap<>();
		colonne=new HashMap<>();
		
		for(int i=0;i<n;i++) {
			LinkedList<Elemento> ElementiRiga=new LinkedList<>();
			
			for(int k=0;k<n;k++) {
				Elemento tmp=new Elemento();
				tmp.i=i;tmp.j=k;tmp.v=0;
				ElementiRiga.add(tmp);
				if(colonne.containsKey(k)) {
					colonne.get(k).add(tmp);
				}
				else {
					LinkedList<Elemento> ElementiColonna=new LinkedList<>();
					ElementiColonna.add(tmp);
					colonne.put(k,ElementiColonna);
				}
			}
			righe.put(i, ElementiRiga);
		}
	}

	
	@Override
	public int getN() {
		return righe.size();
	}

	@Override
	public Iterator<Elemento> riga(int i) {
		if(i<0 || i>=getN()) throw new IllegalArgumentException();
		return righe.get(i).iterator();
	}

	@Override
	public Iterator<Elemento> colonna(int j) {
		return colonne.get(j).iterator();
	}

	@Override
	public MatriceSparsa crea() {
		MatriceSparsa m=new MatriceSparsaLinked(getN());
		return m;
	}
	
	
	
	public static void CostruisciDaFile(RandomAccessFile f,MatriceSparsa M,String regex) throws IOException {
		
		for(;;) {
			//sono dello stesso ordine,hanno elemeti uguali ma numero di linea uguali
			String linea1=f.readLine();
			if(linea1==null) {f.close();break;}
			if(!linea1.matches(regex)) throw new IllegalArgumentException();
			StringTokenizer st=new StringTokenizer(linea1," =");
			Elemento e=new Elemento();
			while(st.hasMoreTokens()) {
				String variabile=st.nextToken();
				if(variabile.contains("i")) e.i=Integer.parseInt(st.nextToken());
				else if(variabile.contains("j")) e.j=Integer.parseInt(st.nextToken());
				else if(variabile.contains("v"))e.v=Integer.parseInt(st.nextToken());
			}
			M.set(e);
		}
		
	}
	

	public static void main(String[] args) throws IOException {
		String regex="(i=[\\d]+ | j=[\\d]+ | v=[\\d]+)";
		Scanner sc=new Scanner(System.in);
		System.out.println("Manipolazione MatriciSparse: Premi Invio");
		sc.nextLine();
		RandomAccessFile fa,fb;
		loop1:for(;;) {
			System.out.print("Inserisci NomeFile: >>");
			String file1=sc.nextLine();
			System.out.print("Inserisci NomeFile2: >>");
			String file2=sc.nextLine();
			try {
				fa=new RandomAccessFile(file1,"w");
				fb=new RandomAccessFile(file2,"w");
			}catch(IOException e) {
				System.out.println("File Illeggibili o Inesistenti");
				continue loop1;
			}
			
			sc.close();break;
		}
		
		MatriceSparsa M1=new MatriceSparsaLinked(100);
		MatriceSparsa M2=new MatriceSparsaLinked(100);
		
		CostruisciDaFile(fa,M1,regex);
		CostruisciDaFile(fb,M2,regex);
		
		MatriceSparsa add=M1.add(M2);
		MatriceSparsa mul=M1.mul(M2);
		System.out.println("ADD:");
		System.out.println(add);
		System.out.println("MUL:");
		System.out.println(mul);
		
		
		
		
		
	}
}
