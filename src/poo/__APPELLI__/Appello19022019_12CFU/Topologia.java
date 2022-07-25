package poo.__APPELLI__.Appello19022019_12CFU;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Topologia {
	
	final static String regex=("(x=[\\d]+[\\s]+yi=[\\d]+[\\s]+yf=[\\d]+)");
	
	ArrayList<Segmento> l=new ArrayList<>();
	ArrayList<Segmento> Soluzione=new ArrayList<>();
	class Segmento {
		int x; //ascissa
		int yi,yf;//ordinata
		
		public Segmento(int x,int yi,int yf) {
			this.x=x;
			this.yi=yi;
			this.yf=yf;
		}
		
		public boolean isConsecutivi(Segmento s) {
			if(Math.abs(s.x-x)>5) return false;
			if(s.yi<yf && yf<s.yf || yi<s.yf && s.yf<yf) return true;
			
			return false;
		}
		
		public String toString() {
			StringBuilder sb=new StringBuilder(10);
			sb.append("Segmento : x= "+x+" ;"+" yi= "+yi+" ; "+"yf= "+yf);
			return sb.toString();
		}
		
		public int hashCode() {
			final int M=73;
			Integer x=this.x;
			Integer yi=this.yi,yf=this.yf;
			int h=x.hashCode()+yi.hashCode()+yf.hashCode();
			h+=h*M;
			return h;
		}
		
		public boolean equals(Object o) {
			if(!(o instanceof Segmento)) return false;
			if(o==this) return true;
			Segmento seg=(Segmento) o;
			
			if(x!=seg.x || yi!=seg.yi || yf!=seg.yf) return false;
			
			return true;
			
			
		}
		
		
	}

	public Topologia(String NomeFile) throws IOException {
		
		BufferedReader br=new BufferedReader(new FileReader(NomeFile));
		
		for(;;) {
			String s=br.readLine();
			if(s==null) break;
			if(!s.matches(regex)) throw new RuntimeException();
			Inserisci(s);
		}
		
		
		
		
	}
	
	private void Inserisci(String s) {
		StringTokenizer st=new StringTokenizer(s," ");
		
		Integer x=null,yi=null,yf=null;
		while(st.hasMoreTokens()) {
			//elempreso
			String variabile=st.nextToken();
			if(variabile.contains("x=")) {
				StringTokenizer st2=new StringTokenizer(variabile,"x=");
				variabile=st2.nextToken();
				x=Integer.parseInt(variabile);
				
			}
			else if(variabile.contains("yi=")) {
				StringTokenizer st2=new StringTokenizer(variabile,"yi=");
				variabile=st2.nextToken();
				yi=Integer.parseInt(variabile);
			}
			else if(variabile.contains("yf=")) {
				StringTokenizer st2=new StringTokenizer(variabile,"yf=");
				variabile=st2.nextToken();
				yf=Integer.parseInt(variabile);
			}
			
		}
		
		Segmento segmento=new Segmento(x,yi,yf);
		l.add(segmento);
		
		
	}
	
	public void risolvi() {
		tentativo(0);
	}
	
	private ArrayList<Segmento> PossibiliScelte(int ps){
		ArrayList<Segmento> PossibiliScelte=new ArrayList<Segmento>();
		Segmento x=l.get(ps);
		for(Segmento seg:l) {
			if (seg.equals(x)) continue;
			if (seg.isConsecutivi(x)) PossibiliScelte.add(seg);			
		}
		return PossibiliScelte;
	}
	
	
	private void tentativo(int ps) {
		ArrayList<Segmento> scelte=PossibiliScelte(ps);
		for(Segmento s:scelte) {
			if(!Soluzione.contains(s) && l.get(ps).isConsecutivi(s)) {//assegnabile
				Soluzione.add(s);//assegna;
				if(ps==l.size()-1) ScriviSoluzione();
				else tentativo(ps+1);
				Soluzione.remove(s);
			}
		}
		if(Soluzione.size()!=0)ScriviSoluzione(); //se non sono diponibili altre scelte ma ci sono elementi � essa stessa un percorso
	}
	
	private void ScriviSoluzione() {
		StringBuilder sb=new StringBuilder(100);
		sb.append("[");
		for(Segmento s: Soluzione) {
			sb.append(s.toString());
		}
		sb.append("]");
		System.out.println(sb);
		
		
	}
	
	

}
