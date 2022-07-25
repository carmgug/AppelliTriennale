package poo.string;

public class MiaClasse {
	private int [] v;
	public MiaClasse( int[] v ) {
		//evitiamo l'aliasing this.v=v; ...
		//this.v=new int[v.length];
		this.v=java.util.Arrays.copyOf(v,v.length); //v da solo è il parametro
	}
	//...altri metodi
	public String toString() {
/*
		String s="[";
		for( int i=0; i<v.length; ++i ) {
			s=s+v[i];
			if( i<v.length-1 ) s=s+", ";
		}
		s=s+"]";
		return s;
*/
		StringBuilder sb=new StringBuilder(100);
		sb.append("[");
		for( int i=0; i<v.length; ++i ) {
			sb.append(v[i]);
			if( i<v.length-1 ) sb.append(", ");
		}
		sb.append("]");
		return sb.toString();
	}
	
	public static void main( String[] args ) {
		int[] a= {9,2,5,3,-1,14,10}; //new implicito mediante aggregato
		MiaClasse o=new MiaClasse(a);
		System.out.println(o);
	}//main
	
}
