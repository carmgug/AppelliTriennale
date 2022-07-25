package poo.contatori;

public class ContatoreModulare extends Contatore{
	private int modulo;
	public ContatoreModulare( int modulo ) {
		super();
		if( modulo<=1 ) throw new IllegalArgumentException();
		this.modulo=modulo;
	}
	public ContatoreModulare( int valore, int modulo ) {
		super(valore);
		if( valore<0 || valore>=modulo || modulo<=1 )
			throw new IllegalArgumentException();
		this.modulo=modulo;
	}
	public ContatoreModulare( ContatoreModulare cm ) {
		super(cm.getValore());
		this.modulo=cm.modulo;
	}
	public int getModulo() { return modulo; }
	@Override
	public void incr() {
		valore=(valore+1)%modulo;
	}//incr
	@Override 
	public void decr() {
		valore=(valore-1+modulo)%modulo;
	}//decr
	public String toString() {
		return "Contatore modulo="+modulo+" "+super.toString();
	}//
	//aggiungere equals e hashCode
	public static void main( String...args ) {
		
		Contatore c=new Contatore(10);
		//varie incr/decr
		
		System.out.println(c);
		
		ContatoreModulare cm=new ContatoreModulare(8);
		
		for( int i=0; i<10; ++i ) cm.incr();
		
		System.out.println( cm.getValore() );
		
		c=cm;
		
		int m=((ContatoreModulare)c).getModulo();
		
		for( int i=0; i<10; ++i ) c.decr();
		
		System.out.println(c.getValore());
		System.out.println(c);
		
	}
}//ContatoreModulare
