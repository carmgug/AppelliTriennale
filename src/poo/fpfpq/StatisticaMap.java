package poo.fpfpq;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeMap;

public class StatisticaMap implements Statistica{

	private Map<String,Integer> fp=new TreeMap<>();	
	
	private Map<String,Map<String,Integer>> fpq=new TreeMap<>();

	public int numTotaleParole(){
		int ntp=0;
		for( String s: fp.keySet() ){
			ntp=ntp+fp.get(s);		
		}
		return ntp;
	}//numTotaleParole
	
	public void arrivoParola( String p ){
		if( !fp.containsKey(p) ){
			fp.put(p,0);
			fpq.put(p, new HashMap<>());
		}
		fp.put( p, fp.get(p)+1 );
	}//arrivoParola

	public void paroleConsecutive( String p, String q ){
		if( !fp.containsKey(p) || !fp.containsKey(q) )
			throw new RuntimeException("parole "+p+" e/o "+q+" assenti");
		Map<String,Integer> pad=fpq.get(p);
		if( !pad.containsKey(q) ) pad.put(q,0);	
		pad.put( q, pad.get(q)+1 );	
	}//paroleConsecutive

	public int frequenza( String p ){
		if( !fp.containsKey(p) ) return 0;
		return fp.get(p);
	}//frequenza

	public int frequenzaCoppia( String p, String q ){
		if( !fp.containsKey(p) || !fp.containsKey(q) ) return 0;
		Map<String,Integer> pad=fpq.get(p);
		if( !fpq.containsKey(q) ) return 0;
		return pad.get(q);
	}//frequenzaCoppia
	
	public String parolaCheSeguePiuFrequente( String target ){
		if( !fp.containsKey(target) ) throw new RuntimeException(target+" inesistente");
		Map<String,Integer> adiacenti=fpq.get(target);
		String ppf=null;
		int max=0;
		for( String p: adiacenti.keySet() )
			if( adiacenti.get(p)>max ){ ppf=p; max=adiacenti.get(p); }
		return ppf;
	}//parolaCheSeguePiuFrequente
	
	public String parolaCheSegueMenoFrequente( String target ){
		if( !fp.containsKey(target) ) throw new RuntimeException(target+" inesistente");
		Map<String,Integer> adiacenti=fpq.get(target);
		String pmf=null;
		int min=Integer.MAX_VALUE;
		for( String p: adiacenti.keySet() )
			if( adiacenti.get(p)<min ){ pmf=p; min=adiacenti.get(p); }
		return pmf;
	}//parolaCheSegueMenoFrequente

	public String toString(){
		StringBuilder sb=new StringBuilder(500);
		for( String p: fp.keySet() ){
			sb.append( "f("+p+")=" );
			sb.append( String.format("%.4f%n", ((double)frequenza(p))/numTotaleParole()) );
			Iterator<String> paroleAdiacenti=fpq.get(p).keySet().iterator();
			sb.append('\t');
			while( paroleAdiacenti.hasNext() ){
			    String q=paroleAdiacenti.next();
				sb.append("f("+p+","+q+")=");
				sb.append( String.format("%.4f", ((double)frequenzaCoppia(p,q))/fp.get(p)) );
				if( paroleAdiacenti.hasNext() ) sb.append(" ");
			}
			sb.append('\n');
		}
		return sb.toString();	
	}//toString
	
}//StatisticaMap