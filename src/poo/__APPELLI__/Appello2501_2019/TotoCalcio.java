package poo.__APPELLI__.Appello2501_2019;

import java.util.LinkedList;

public class TotoCalcio {

	char[][] Sistema;
	char[] colonna;
	public TotoCalcio(char[][] Sistema) {
		if(Sistema.length!=13) throw new IllegalArgumentException();
		
		//controllo
		for(int i=0;i<13;i++) {
			if(Sistema[i].length>3) throw new IllegalArgumentException();
			for(int j=0;j<Sistema[i].length;++j) {
				if(!String.valueOf(Sistema[i][j]).matches("(1|2|X)"))
					throw new IllegalArgumentException();
			}
		}
		this.Sistema=new char[13][];
		for(int i=0;i<13;++i) {
			System.arraycopy(Sistema[i], 0,this.Sistema[i], 0, Sistema[i].length);
		}
		colonna=new char[13];
		
		
	}
	public void sviluppa() {
		sviluppa(0);
	}
	
	
	private LinkedList<Character> Scelte(char[] v){
		LinkedList<Character> scelte=new LinkedList<>();
		for(Character C:v) {
			scelte.add(C);
		}
		return scelte;
	}
	
	
	//ogni posizione del vettore colonna � un ps;
	private void sviluppa(int i) {
		LinkedList<Character> l=Scelte(Sistema[i]);
		for(Character scelta:l) {
			colonna[i]=scelta;
			if(i==13) ScriviSoluzione();
			else sviluppa(i+1);
			colonna[i]=' ';
		}
	}
	
	private void ScriviSoluzione() {
		System.out.println("--> ");
		for(Character Giocata:colonna) {
			System.out.print(Giocata+" ");
		}
		System.out.println();
	}
	
	
	public boolean equals(Object o) {
		if(! (o instanceof TotoCalcio)) return false;
		if(o==this) return true;
		TotoCalcio x=(TotoCalcio) o;
		char[][] Sistema=x.Sistema;
		for(int i=0;i<13;i++) {
			if(Sistema[i].length!=this.Sistema[i].length) return false;
			for(int j=0;j<Sistema[i].length;j++) {
				
				if(Sistema[i][j]!=this.Sistema[i][j]) return false;
			}
		}
		return true;
	}
	
	public String toString() {
		StringBuilder sb=new StringBuilder(50);
		for(int i=0;i<13;i++) {
			sb.append("[");
			for(int j=0;j<Sistema[i].length;j++) {
				sb.append(Sistema[i][j]);
			}
			sb.append("]");
			sb.append("\n");
		}
		return sb.toString();
	}
	
	public int hashCode() {
		final int M=43;
		int h=0;
		Character c=null;
		for(int i=0;i<13;i++) {
			for(int j=0;j<Sistema[i].length;j++) {
				c=Sistema[i][j];
				h+=h*M+c.hashCode();
			}
		}
		return h;
	}
}
