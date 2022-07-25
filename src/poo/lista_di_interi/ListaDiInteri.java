package poo.lista_di_interi;

public class ListaDiInteri {
	//lista ordinata per valori crescenti
	
	private static class Nodo{//inner class
		int info;
		Nodo next;
	}
	
	private Nodo inizio=null; //lista vuota
	private int size=0;
	
	public void add( int x ) {
		//creare un nodo, porre la sua info a x e inserire il nodo in ordine
		Nodo nuovo=new Nodo();
		nuovo.info=x; nuovo.next=null;
		if( inizio==null || inizio.info>=x ) {
			//lista vuota o il primo elemento è >=x - inserimento in testa
			nuovo.next=inizio;
			inizio=nuovo;
		}
		else {
			//lista non è vuota e nuovo va inserito dopo il primo elemento
			Nodo pre=null, cor=inizio;
			while( cor!=null && cor.info<x ) {
				pre=cor;
				cor=cor.next;
			}
			//certezza: nuovo va inserito tra pre e cor
			nuovo.next=cor; //nuovo va prima di cor
			pre.next=nuovo; //nuovo va dopo pre
		}
		size++;
	}//add
	
	public void remove( int x ) {
		//rimozione della prima occorrenza, se esiste, di x
		Nodo pre=null, cor=inizio;
		//ricerca di x
		while( cor!=null && cor.info<x ) {
			pre=cor;
			cor=cor.next;
		}
		if( cor!=null && cor.info==x ) {
			//x esiste e va rimosso
			if( cor==inizio ) {
				//bisogna rimuovere la testa assegnando a inizio il puntatore al 2 elemento
				inizio=cor.next; //bypass del primo nodo
			}
			else {
				//x si trova su un nodo da 2 all'ultimo
				pre.next=cor.next; //bypass del nodo cor
			}
			size--;
		}
	}//remove
	
	public void removeAll( int x ) {
		Nodo pre=null, cor=inizio;
		while( cor!=null ) {
			if( cor.info==x ) {
				if( cor==inizio ) {
					inizio=inizio.next;
				}
				else {
					pre.next=cor.next;
				}
				cor=cor.next;
				size--;
			}
			else if( cor.info>x ) break;
			else{ pre=cor; cor=cor.next; }
		}
	}//removeAll
	
	public int duplicati( int x ) {
		return 0; //TODO
	}//duplicati
	
	public int size() {//consulta la lista - supposta creata
/*
		int s=0;
		Nodo cor=inizio;
		while( cor!=null ) {
			s++;
			cor=cor.next; //avanza cor
		}
		return s;
*/
		return size;
	}//size
	
	public boolean contains( int x ) {
		Nodo cor=inizio;
		while( cor!=null ) {
			if( cor.info==x ) return true;
			if( cor.info>x ) return false;
			cor=cor.next; //avanza
		}
		return false;
	}
	
	public String toString() {
		StringBuilder sb=new StringBuilder(100);
		Nodo cor=inizio;
		sb.append("[");
		while( cor!=null ) {
			sb.append(cor.info);
			if( cor.next!=null ) sb.append(", ");
			cor=cor.next;
		}
		sb.append("]");
		return sb.toString();
	}
	
	public static void main( String[] args ) {
		ListaDiInteri l=new ListaDiInteri();
		l.add(5); l.add(18); l.add(-4); l.add(3); l.add(5); l.add(18); l.add(5); l.add(-4); l.add(-4);
		System.out.println("Contenuto della lista");
		System.out.println(l);
		System.out.println("size="+l.size());
		System.out.println("esiste il 5? "+l.contains(5));
		l.remove(-4);
		System.out.println("Dopo rimozione di -4: "+l+ " size="+l.size());
		l.removeAll(5);
		System.out.println("Dopo removeAll(5) : "+l+ " size="+l.size());
		l.removeAll(-4);
		System.out.println("Dopo removeAll(-4) : "+l+ " size="+l.size());
		l.removeAll(18);
		System.out.println("Dopo removeAll(18) : "+l+ " size="+l.size());
	}//main
	
}
