package poo.concorrenza;

public class Manager implements Risorsa{ //classe thread-safe
	private int cont=0; //conta gli accessi di A
	private boolean risorsaOccupata=false;
	//cont e risorsaOccupata sono variabili condivise tra i due thread
	
	public synchronized void richiesta( Processo.Tipo id ) { //sezione critica
		if( id==Processo.Tipo.A ) {
			while( cont==2 || risorsaOccupata  ) {
				try {
					wait();
				}catch( InterruptedException e ) {}
			}
			cont++;
			risorsaOccupata=true;
			System.out.println("Processo A puo' accedere.");
		}
		else { //B fa richiesta
			while( risorsaOccupata ) {
				try {
					wait();
				}catch( InterruptedException e ) {}
			}
			risorsaOccupata=true;
			System.out.println("Processo B puo' accedere.");
		}
	}//richiesta
	public synchronized void rilascio( Processo.Tipo id ) {
		if( id==Processo.Tipo.B ) cont=0;
		risorsaOccupata=false;
		notifyAll();
	}//rilascio
	
}
