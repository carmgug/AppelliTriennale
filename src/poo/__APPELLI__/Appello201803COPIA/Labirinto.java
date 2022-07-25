package poo.__APPELLI__.Appello201803COPIA;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

import poo.backtracking.Backtracking;
import poo.util.GestoreTesto;


public class Labirinto extends Backtracking<Integer,Integer> {
	//ps Integer (Key) S(Integer)Porta alla key
	HashMap<Integer,Set<Integer>> labirinto; //� INUTILE AVERE due porte uguali ECCO IL SET;
	LinkedList<Integer> Soluzione;
	boolean flag=false;
	
	@SuppressWarnings("unchecked")
	public Labirinto(HashMap<Integer,Set<Integer>> labirinto) {
		//Shallow-Copy
		this.labirinto=(HashMap<Integer, Set<Integer>>) labirinto.clone();
		
		Soluzione=new LinkedList<>();
	}
	
	@Override
	protected boolean assegnabile(Integer p, Integer s) {
		if(Soluzione.contains(s)) return false;
		if(labirinto.get(s).contains(0)) return false;
		return true;
	}

	@Override
	protected void assegna(Integer ps, Integer s) {
		Soluzione.add(s);
		
	}

	@Override
	protected void deassegna(Integer ps, Integer s) {
		Soluzione.remove(s);
		
	}
	
	protected boolean esisteSoluzione(Integer p) {
		if(Soluzione.getLast().equals(9999)) {flag=true;return true;}
		return false;
		
	}
	
	protected boolean ultimaSoluzione( Integer p ) {
		if(flag==true) return true;
		return false;
	}//ultimaSoluzione

	@Override
	protected void scriviSoluzione(Integer p) {
		StringBuilder sb=new StringBuilder(20);
		sb.append("[");
		Iterator<Integer> it=Soluzione.iterator();
		while(it.hasNext()) {
			sb.append(it.next());
			if(it.hasNext()) sb.append("-");
		}
		sb.append("]");
		System.out.println(sb.toString());
	}

	@Override
	protected List<Integer> puntiDiScelta() {
		throw new RuntimeException("Operazione Non Possibile,La porta deve essere specifica;");
		}

	@Override
	protected Collection<Integer> scelte(Integer p) {
		Collection<Integer> scelte=new LinkedList<Integer>();
		for(Integer adiacenti:labirinto.get(p)) {
			scelte.add(adiacenti);
		}
		return scelte;
	}

	@Override
	protected void risolvi() {}
	
	protected void risolvi(int n) {
		if(labirinto.containsKey(n)) System.out.println("Nessuna Soluzione,Porta non contenuta all'interno dell'labirinto");
		List<Integer> ps=(List<Integer>) scelte(n);
		tentativo(ps,ps.get(0));
		if(!flag) System.out.println("Nessuna Soluzione");
	}
	public static void main(String[] args) throws IOException {
		
		HashMap<Integer,Set<Integer>> labirinto=new HashMap<>();//� INUTILE AVERE due porte uguali ECCO IL SET;
		Scanner sc=new Scanner(System.in);
		File f=null;
		
		boolean ok=false;
		do {
			String NomeFile=sc.nextLine();
			f=new File(NomeFile);
			if(!f.exists())System.out.println("File Inesistente");
			else{
				GestoreTesto g=new GestoreTesto(f," ");
				try {
					CreaMappa(g,labirinto);
				}catch(IOException e) {
					System.out.println("File Malformato");
				}
				ok=true;
			}
		}while(!ok);
		
		Labirinto lab=new Labirinto(labirinto);
		System.out.print("Inserisci Porta >>> ");
		int n=sc.nextInt();
		lab.risolvi(n);
		sc.close();
		
	}
	
	static void CreaMappa(GestoreTesto g,HashMap<Integer,Set<Integer>> labirinto) throws IOException {
		while(g.prossimoSimbolo()!=GestoreTesto.Simbolo.EOF) {
			String Porta=g.getString();
			//per ogni porta ci sono 4 porte
			g.prossimoSimbolo();
			Integer A1=Integer.parseInt(g.getString());
			g.prossimoSimbolo();
			Integer A2=Integer.parseInt(g.getString());
			g.prossimoSimbolo();
			Integer A3=Integer.parseInt(g.getString());
			g.prossimoSimbolo();
			Integer A4=Integer.parseInt(g.getString());
			Set<Integer> s=new TreeSet<Integer>();
			s.add(A4);s.add(A3);s.add(A2);s.add(A1);
			labirinto.put(Integer.parseInt(Porta), s);
		}
	}

}
