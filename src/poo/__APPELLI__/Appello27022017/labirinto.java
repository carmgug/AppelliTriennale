package poo.__APPELLI__.Appello27022017;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


public class labirinto {
	
	HashMap<Integer,LinkedList<Integer>> labirinto; 
	private int N;
	boolean Risolto;
	LinkedList<Integer> Soluzione;
	
	public labirinto(String NomeFile) throws IOException {
		labirinto=new HashMap<>();
		File f1=new File(NomeFile);
		CostruisciLabirinto(f1);
		Risolto=false;
		Soluzione=new LinkedList<>();
	}

	private void CostruisciLabirinto(File f1) throws IOException {
		BufferedReader br=new BufferedReader(new FileReader(f1));
		//prima linea
		N=(Integer.parseInt(br.readLine()));
		if(N>=9999) throw new RuntimeException();
		for(;;) {
			String linea=br.readLine();
			if(linea==null) {br.close();break;}
			StringTokenizer st=new StringTokenizer(linea," -$");
			int s=Integer.parseInt(st.nextToken());
			if(s<1 || s>=9999) throw new RuntimeException("File-Malformato");
			int i1=Integer.parseInt(st.nextToken());
			int i2=Integer.parseInt(st.nextToken());
			int i3=Integer.parseInt(st.nextToken());
			int i4=Integer.parseInt(st.nextToken());
			if(i1<0 || i2<0 || i3<0 || i4<0) throw new RuntimeException();
			if(!labirinto.containsKey(s)) {
				LinkedList<Integer> l=new LinkedList<>();
				labirinto.put(s, l);
			}
			List<Integer> l=labirinto.get(s);
			l.add(i1);l.add(i2);l.add(i3);l.add(i4);
		}
		if(labirinto.size()!=N) throw new RuntimeException();
	}
	
	public void risolvi(int n) {
		tentativo(n);
		if(!Risolto) {
			System.out.println("Nessuna via d'uscita");
		}
	}
	
	
	private void tentativo(int porta) {
		List<Integer> ps=labirinto.get(porta);
		if(ps==null) throw new RuntimeException("Porta Inesistente");
		if(ps.contains(0)) return; //murata;
		for(Integer scelta:ps) {
			if(!Risolto) {
				Soluzione.add(scelta);
				if(scelta==9999) {ScriviSoluzione();Risolto=true;}
				else tentativo(scelta);
				Soluzione.remove(scelta);
			}
				
		}
	}
	
	private void ScriviSoluzione() {
		StringBuilder sb=new StringBuilder();
		Iterator<Integer> it=Soluzione.iterator();
		sb.append("[");
		while(it.hasNext()) {
			sb.append(it.next());
			if(it.hasNext()) sb.append(", ");
		}
		sb.append("]");
		System.out.println(sb.toString());
	}
	
	
	
	
	public static void main (String[] args) {
		
		Scanner sc=new Scanner(System.in);
		
		System.out.println("Inserisci NomeFile labirinto :");
		String NomeFile=sc.nextLine();
		labirinto lab = null;
		try {
			lab=new labirinto(NomeFile);
		}catch(Exception e){
			e.printStackTrace();
			System.exit(-1);sc.close();
		}
		System.out.println("Inserisci Porta >>");
		int porta=sc.nextInt();
		
		System.out.println();
		lab.risolvi(porta);
		sc.close();
		
		
		
	}
	
	

}
