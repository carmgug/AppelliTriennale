package poo.string;

import java.util.Scanner;
import java.util.StringTokenizer;

public class ValutatoreEspressione {
	//si ammettono anche sotto espressioni in parentesi ( e )
	private String expr;
	public ValutatoreEspressione( String expr ) {
		//per ora non facciamo controlli
		this.expr=expr;
	}
	
	public int valuta() {
		StringTokenizer st=new StringTokenizer( expr, "+-*/()", true );
		return valutaEspressione( st );
	}//valuta
	
	private int valutaOperando( StringTokenizer st ) {
		String token=st.nextToken();
		if( token.charAt(0)=='(' ) return valutaEspressione(st);
		return Integer.parseInt(token);
	}//valutaOperando
	
	private int valutaEspressione( StringTokenizer st ) {
		int ris=valutaOperando( st );
		while( st.hasMoreTokens() ) {
			char op=st.nextToken().charAt(0);
			if( op==')' ) return ris;
			int opnd=valutaOperando(st);
			switch( op ){
			case '+': ris=ris+opnd; break;
			case '-': ris=ris-opnd; break;
			case '*': ris=ris*opnd; break;
			case '/': ris=ris/opnd; break;
			default : throw new RuntimeException(op+" operatore inatteso-Espressione malformata!");
			}			
		}
		return ris;
	}//valutaEspressione
	
	public String toString() { return expr; }
	
	public static void main( String[] args ) {
		String expr=null;	
		if( args.length==0 || args.length>1 ) {
			Scanner sc=new Scanner( System.in );
			System.out.print("Fornisci una espressione aritmetica intera con potenziali parentesi ( e ) > ");
			expr=sc.nextLine();
			sc.close();
		}
		else {
			expr=args[0];
		}
		ValutatoreEspressione v=new ValutatoreEspressione(expr);
		int ris=v.valuta();
		System.out.println(expr+"="+ris);
	}//main
	
}//ValutatoreEspressione
