package poo.string;

public class TestArgomenti {
	public static void main( String...args ) {
		//input di un array di interi a riga di comando
		if( args.length==0 ) throw new RuntimeException("Argomenti assenti!");
		int []v=new int[ args.length ];
		for( int i=0; i<v.length; ++i )
			v[i]=Integer.parseInt( args[i] );
		System.out.println("Vettore costruito");
		System.out.println( java.util.Arrays.toString(v) );
	}
}
