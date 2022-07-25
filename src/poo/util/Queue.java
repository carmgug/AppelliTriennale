package poo.util;

public interface Queue<T> extends Iterable<T> {
	//implementare quanti più metodi è possibile mediante l'iterator
	//ipotesi: iteratore restituisce gli elementi dal piu' vecchio al piu' giovane
	int size();
	void clear();
	default void offer( T x ) { //add x in coda
		add(x);
	}
	void add( T x ); //add x in coda
	T remove(); //rimuove e ritorna l'elemento più vecchio
	default T poll(){ return remove(); } //rimuove e ritorna l'elemento più vecchio
	T peek(); //ritorna l'elemento più vecchio
	default boolean isEmpty() { return !iterator().hasNext(); }
	default boolean isFull() { return false; }
}//Queue
