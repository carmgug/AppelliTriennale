package poo.__APPELLI__.Appello12072017;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Matrici {
	
	final static String Regex="(i=[\\d]+|j=[\\d]+|v=[\\d]+|[\\s]+)+";
	
	
	
	
	public static double Determinante(double[][] a){
		
		int scambi=0;
		int n=a.length;
		for(int j=0;j<n;j++) {
			if(poo.util.Mat.sufficientementeProssimi(a[j][j], 0.0D)) {
				int p=j+1;
				for(;p<n;p++) {
					if(!poo.util.Mat.sufficientementeProssimi(a[p][j],0.0D)) break;
					
				}
				if(p==n) return 0.0D;
				double[] park=a[j];
				a[j]=a[p];a[p]=park;
				scambi++;
			}
			for(int i=j+1;i<n;j++) {
				if(!poo.util.Mat.sufficientementeProssimi(a[i][j], 0.0D)) {
					double cm=a[i][j]/a[j][j];
					for(int k=j;k<n;k++) {
						a[i][k]=a[i][k]-a[j][k]*cm;
					}
				}
			}
		}
		double D=1.0D;
		for(int j=0;j<n;j++) {
			D=D*a[j][j];
		}
		if(scambi%2!=0) D=D*-1;
		return D;
	}
	
	
	
	
	public static double[][] CostruisciMatrice(File f1) throws IOException {
		BufferedReader br=new BufferedReader(new FileReader(f1));
		LinkedList<double[]> l=new LinkedList<>();
		TreeMap<Integer,TreeMap<Integer,Double>> M=new TreeMap<>();
		for(;;) {
			String linea=br.readLine();
			if(linea==null) {br.close();break;}
			if(!linea.matches(Regex))throw new RuntimeException("File-Malformato");
			StringTokenizer st=new StringTokenizer(linea," ");
			Integer R=null,C=null;Double V=null;
			while(st.hasMoreTokens()) {
				String variabile=st.nextToken();
				if(variabile.contains("v=")) {V=Double.parseDouble(variabile.substring(2));}
				else if(variabile.contains("j=")) {C=Integer.parseInt(variabile.substring(2));}
				else if(variabile.contains("i=")) {R=Integer.parseInt(variabile.substring(2));}
			}
			if(R==null || C==null || V==null ) throw new RuntimeException("File-Malformato");
			if(!M.containsKey(R)) {
				M.put(R, new TreeMap<>());
			}
			TreeMap<Integer,Double> colonna=M.get(R);
			if(colonna.containsKey(C)) throw new RuntimeException("File-Malformato");//� stata redfinita una colonna per la stessa riga
			colonna.put(C, V);
		}
		int righe=M.size();//N righe definite N righe nella matrice
		double[][] matrice=new double[righe][righe];
		Set<Integer> SetR=M.keySet();
		for(Integer r:SetR) {
			int i=r;
			if(M.get(r).size()!=righe) throw new RuntimeException("File-Malformato");//Matrice Rettangolare;
			Set<Integer> SetC=M.get(r).keySet();
			for(Integer c:SetC) {
				int j=c;
				double v=M.get(r).get(c);
				matrice[i][j]=v;
			}
			
		}
		return matrice;
	}
	
	
	
	
	public static void main(String[] args) {
		
		Scanner sc=new Scanner(System.in);
		System.out.println("Calcola Determinante Matrice da file: Premi Invo");
		sc.nextLine();
		double[][] matrice=null;
		loop1: for(;;) {
			String linea=sc.nextLine();
			File f1=new File(linea);
			if(!f1.exists()) {System.out.println("Inserisci Path File Esistente");continue loop1;}
			try{
				matrice=CostruisciMatrice(f1);
			}catch(Exception e) {
				System.out.println("Matrice Non Quadrata");
			}
			break;
		}
		
		
		StringBuilder sb=new StringBuilder(20);
		for(int i=0;i<matrice.length;i++) {
			sb.append("[");
			for(int j=0;j<matrice.length;j++) {
				sb.append(matrice[i][j]);
				if(j<matrice.length-1) sb.append(", ");
			}
			sb.append("]\n");
		}
		System.out.println(sb.toString());
		System.out.println("Determinante: "+Determinante(matrice));
		
		
		
		
		
	}
	
	
	
	
	
	

}
