package poo.eratostene;

public abstract class CrivelloAstratto implements Crivello{
	
	public String toString() {
		StringBuilder sb=new StringBuilder(500);
		int c=0;
		for( int x: this ) {
			sb.append( String.format("%10d", x) );
			c++;
			if( c%10==0 ) sb.append('\n');
		}
		sb.append('\n');
		return sb.toString();
	}
	
	//equals e hashCode da scrivere come esercizio
	
}//CrivelloAstratto
