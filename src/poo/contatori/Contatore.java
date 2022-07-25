package poo.contatori;

public class Contatore {
	protected int valore;
	public Contatore() { this(0); }
	public Contatore(  int valore ) {
		this.valore=valore;
	}
	public Contatore( Contatore c ) {
		this.valore=c.valore;
	}
	public int getValore() { return valore; }
	public void incr() { valore++; }
	public void decr() { valore--; }
	public String toString() {
		return "Contatore di valore="+valore;
	}//toString
	@Override
	public boolean equals( Object s ) {
		if( !(s instanceof Contatore) ) return false;
		if( s==this ) return true;
		Contatore c=(Contatore)s;
		return this.valore==c.valore;
	}//equals
	@Override
	public int hashCode() { return valore; }
}//Contatore
