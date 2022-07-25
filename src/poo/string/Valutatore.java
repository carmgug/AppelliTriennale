package poo.string;

import java.util.Scanner;
import java.util.StringTokenizer;

public class Valutatore {
	//valutatore di espressioni aritmetiche intere con i soli operatori +-*/
	//Es. 13+4*2-45, 0-13+4*20+458/4-6, 154
	public static void main( String[] args ) {
		Scanner sc=new Scanner( System.in );
		System.out.print("Fornisci una espressione aritmetica intera > ");
		String expr=sc.nextLine();
		StringTokenizer st=new StringTokenizer( expr, "+-*/", true );
		int ris=Integer.parseInt( st.nextToken() ); //il primo token è sempre un operando
		while( st.hasMoreTokens() ) {
			//certamente il prossimo token è operatore
			char op=st.nextToken().charAt(0);
			int opnd=Integer.parseInt( st.nextToken() );
			switch( op ){
			case '+': ris=ris+opnd; break;
			case '-': ris=ris-opnd; break;
			case '*': ris=ris*opnd; break;
			case '/': ris=ris/opnd; break;
			default : throw new RuntimeException(op+" operatore inatteso!");
			}
		}
		System.out.println(expr+"="+ris);
		sc.close();
	}
}
