package poo.__APPELLI__.Appello2020_05;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Testo {
	
	TreeMap<String,Integer> Mappa;
	
	public Testo(String NomeFile) {
		Mappa=new TreeMap<>();
		try {
			InserisciInMappa(NomeFile);
		}catch(Exception e) {
			System.out.println(e);
			System.out.println(NomeFile+"File illegibile/Inesistente");
		}
	}
	public Testo() {
		Mappa=new TreeMap<>();
	}
	
	private void InserisciInMappa(String NomeFile) throws IOException {
		BufferedReader br=new BufferedReader(new FileReader(NomeFile));
		
		for(;;) {
			String linea=br.readLine();
			if(linea==null) {br.close();break;}
			StringTokenizer st=new StringTokenizer(linea," ");
			while(st.hasMoreTokens()) {
				String Parola=st.nextToken();
				if(Mappa.containsKey(Parola)) {
					Integer occ=Mappa.get(Parola);
					Mappa.put(Parola, occ+1);
				}
				else {
					Mappa.put(Parola, 1);
				}
				
			}
			
			
		}
	
	}
	
	public Testo Intersezione(Testo T) {
		Testo tmp=new Testo();
		Set<String> Primo=this.Mappa.keySet();
		Set<String> Secondo=T.Mappa.keySet();
		Iterator<String> it=Primo.iterator();
		while(it.hasNext()) {
			String parola=it.next();
			if(!Secondo.contains(parola)) continue;
			Integer occ=this.Mappa.get(parola);
			tmp.Mappa.put(parola,occ);
		}
		return tmp;
		
	}
	
	
	public String toString() {
		StringBuilder sb=new StringBuilder();
		Set<Entry<String,Integer>> Mappa2=Mappa.entrySet();
		for(Entry<String,Integer> elemento: Mappa2) {
			sb.append(elemento.getKey()+","+elemento.getValue()+"\n");
		}
		
		return sb.toString();
	}
	
	public int hashCode() {
		final int M=83;
		int h=0;
		Set<Entry<String,Integer>> Mappa2=Mappa.entrySet();
		for(Entry<String,Integer> elemento: Mappa2) {
			h+=h*M+elemento.getValue().hashCode()+elemento.getKey().hashCode();
		}
		return h;
	}
	
	public boolean equals(Object o) {
		if(!(o instanceof Testo)) return false;
		if(o==this) return true;
		Testo T=(Testo)o;
		if(T.Mappa.size()!=this.Mappa.size()) return false;
		Set<Entry<String,Integer>> Primo=Mappa.entrySet();
		Set<Entry<String,Integer>> Secondo=T.Mappa.entrySet();
		Iterator<Entry<String,Integer>> it=Primo.iterator();
		Iterator<Entry<String,Integer>> it2=Secondo.iterator();
		while(it.hasNext()) {
			Entry<String,Integer> elemento1=it.next(),elemento2=it2.next();
			if(!elemento1.getKey().equals(elemento2.getKey())) return false;
			if(!elemento1.getValue().equals(elemento2.getValue()))return false;
		}
		return true;
		
	}
	
	
	public double simCoseno(Testo T) {
		
		Testo Testo1=this.Intersezione(T);
		Testo Testo2=T.Intersezione(this);
		
		TreeMap<String,Integer> vettore1=Testo1.Mappa;
		TreeMap<String,Integer> vettore2=Testo2.Mappa;
		
		double p=DeterminaP(vettore1,vettore2);
		double modulo1=DeterminaM(vettore1);
		double modulo2=DeterminaM(vettore2);
		
		
		
		
		return p/(modulo1*modulo2); 
	}
	
	private double DeterminaP(TreeMap<String,Integer> vettore1,TreeMap<String,Integer> vettore2) {
		Set<String> Testo1=vettore1.keySet();
		Set<String> Testo2=vettore2.keySet();
		Iterator<String> it=Testo1.iterator(),it2=Testo2.iterator();
		//size uguale stese componenti per come creati;
		double p=0;
		while(it.hasNext()) {
			String Componente1=it.next(),Componente2=it2.next();
			p+=vettore1.get(Componente1)*vettore2.get(Componente2);
		}
		return p;
		
	}
	private double DeterminaM(TreeMap<String,Integer> vettore1) {
		
		Set<String> Testo=vettore1.keySet();
		Iterator<String> it=Testo.iterator();
		double M=0;
		while(it.hasNext()) {
			String Componente=it.next();
			M+=vettore1.get(Componente)*vettore1.get(Componente);
		}
		
		return Math.sqrt(M);
	}
	
	
	

}
