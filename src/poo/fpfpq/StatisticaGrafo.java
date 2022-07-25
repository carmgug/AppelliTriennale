package poo.fpfpq;
import java.util.*;
import poo.grafo.*;

public class StatisticaGrafo implements Statistica{
	
	private class Parola implements Comparable<Parola>{
		private String parola;
		private int frequenza;
		public Parola( String parola ){
			this.parola=parola;
			frequenza=1;
		}
		public String getParola(){ return parola; }
		public int getFrequenza(){ return frequenza; }
		public void setFrequenza( int frequenza ){
			this.frequenza=frequenza;
		}
		public String toString(){
			return parola;
		}//toString
		public boolean equals( Object o ){
			if( !(o instanceof Parola) ) return false;
			if( o==this ) return true;
			Parola p=(Parola)o;
			return this.parola.equals(p.parola);
		}//equals
		public int hashCode(){
			return parola.hashCode();
		}//hashCode
		public int compareTo( Parola p ){
			return this.parola.compareTo(p.parola);
		}//cpmpareTo
	}//Parola
	
	private GrafoOrientatoPesato<Parola> gp=new GrafoOrientatoPesatoImpl<Parola>();
	
	public int numTotaleParole(){
		int ntp=0;
		for( Parola p: gp ){
			ntp=ntp+p.getFrequenza();
		}
		return ntp;
	}//numTotaleParole
	
	public void arrivoParola( String p ){
		Parola par=new Parola(p);
		if( !gp.esisteNodo(par) ) gp.insNodo(par);
		else{
			for( Parola t: gp )
				if( t.equals(par) ){
					t.setFrequenza( t.getFrequenza()+1 );
					break;
				}
		}
	}//arrivoParola

	public void paroleConsecutive( String p, String q ){
		Parola par=new Parola(p), pad=new Parola(q);
		if( !gp.esisteNodo(par) || !gp.esisteNodo(pad) )
			throw new RuntimeException("parole "+p+" e/o "+q+" assenti");
		if( !gp.esisteArco(par,pad) ) gp.insArco( new ArcoPesato<Parola>(par,pad,new Peso(1)));
		else{ 
			Iterator<? extends Arco<Parola>> adiacenti=gp.adiacenti(par);
			while( adiacenti.hasNext() ){
				ArcoPesato<Parola> ap=(ArcoPesato<Parola>)adiacenti.next();
				if( ap.equals(new Arco<Parola>(par,pad)) ){
					Peso peso=ap.getPeso();
					peso.setVal( peso.val()+1 );
					break;
				}
			}
		}
	}//paroleConsecutive

	public int frequenza( String p ){
		Parola par=new Parola(p);
		for( Parola q: gp )
			if( q.equals(par) ) return q.getFrequenza();
		return 0;
	}//frequenza

	public int frequenzaCoppia( String p, String q ){
		Parola par=new Parola(p), pad=new Parola(q);
		if( !gp.esisteArco(par,pad) ) return 0;
		int fpq=0;
		Iterator<? extends Arco<Parola>> adiacenti=gp.adiacenti(par);
		while( adiacenti.hasNext() ){
			ArcoPesato<Parola> ap=(ArcoPesato<Parola>)adiacenti.next();
			if( ap.equals(new Arco<Parola>(par,pad)) ){
				fpq=fpq+(int)ap.getPeso().val();
				break;
			}
		}
		return fpq;
	}//frequenzaCoppia
	
	public String parolaCheSeguePiuFrequente( String target ){
		Parola t=new Parola(target);
		if( !gp.esisteNodo(t) ) throw new RuntimeException(target+" inesistente");
		Iterator<? extends Arco<Parola>> ad=gp.adiacenti(t);
		Parola pf=null;
		int max=0;
		while( ad.hasNext() ){
			ArcoPesato<Parola> ap=(ArcoPesato<Parola>)ad.next();
			if( ap.getPeso().val()>max ){ pf=ap.getDestinazione(); max=(int)ap.getPeso().val(); }
		}
		return pf.getParola();
	}//parolaCheSeguePiuFrequente
	
	public String parolaCheSegueMenoFrequente( String target ){
		Parola t=new Parola(target);
		if( !gp.esisteNodo(t) ) throw new RuntimeException(target+" inesistente");
		Iterator<? extends Arco<Parola>> ad=gp.adiacenti(t);
		Parola mf=null;
		int min=Integer.MAX_VALUE;
		while( ad.hasNext() ){
			ArcoPesato<Parola> ap=(ArcoPesato<Parola>)ad.next();
			if( ap.getPeso().val()<min ){ mf=ap.getDestinazione(); min=(int)ap.getPeso().val(); }
		}
		return mf.getParola();
	}//parolaCheSegueMenoFrequente

	public String toString(){
		StringBuilder sb=new StringBuilder(500);
		int ntp=numTotaleParole();
		for( Parola p: gp ){
			sb.append( "f("+p.getParola()+")=" );
			sb.append( String.format("%.4f%n", ((double)p.getFrequenza())/ntp) );
			Iterator<? extends Arco<Parola>> adiacenti=gp.adiacenti(p);
			
			sb.append('\t');
			while( adiacenti.hasNext() ){
			    ArcoPesato<Parola> ap=(ArcoPesato<Parola>)adiacenti.next();
				sb.append("f("+p.getParola()+","+ap.getDestinazione().getParola()+")=");
				sb.append( String.format("%.4f", ap.getPeso().val()/p.getFrequenza()) );
				if( adiacenti.hasNext() ) sb.append(" ");
			}
			sb.append('\n');
		}
		return sb.toString();	
	}//toString	
	
}//StatisticaGrafo
