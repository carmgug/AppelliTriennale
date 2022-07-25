package poo.concorrenza;
public class Processo extends Thread {
	public enum Tipo {A,B}
	private Risorsa m;
	private Tipo id;
	private final int MAX=5000, MIN=1000; //millisecondi
	public Processo( Tipo id, Risorsa m ) {
		this.id=id;
		this.m=m;
	}
	private void pausa() {
		try {
			Thread.sleep( (int)(Math.random()*(MAX-MIN)+MIN) );
		}catch( InterruptedException e ) {}
	}
	public void run() {
		while( true ) {
			pausa(); //il processo fa altre cose
			System.out.println("Processo "+id+" fa richiesta.");
			m.richiesta(id);
			System.out.println("Processo "+id+" usa la risorsa.");
			pausa(); //il processo usa la risorsa
			m.rilascio(id);
			System.out.println("Processo "+id+" rilascia la risorsa.");
		}
	}
}
