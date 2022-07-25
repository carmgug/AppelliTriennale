package poo.concorrenza;

public interface Risorsa {
	void richiesta( Processo.Tipo id );
	void rilascio( Processo.Tipo id );
}
