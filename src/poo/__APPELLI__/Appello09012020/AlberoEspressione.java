package poo.__APPELLI__.Appello09012020;

import java.util.StringTokenizer;

import poo.util.LinkedList;
import poo.util.List;
import poo.util.StackArray;


public class AlberoEspressione {
	private static class Nodo{
		Nodo fS, fD;
	}
	private static class NodoOperando extends Nodo{
		int val;
		public String toString() {
			return ""+val;
		}
	}
	private static class NodoOperatore extends Nodo{
		char op;
		public String toString() {
			return ""+op;
		}
	}
	private Nodo radice=null;
	
	public void build( String expr ) {
		//expr contiene una espressione eventualmente con ( ) senza priorit� degli operatori della matematica
		if( !expr.matches("(\\d+|[\\+\\-\\*/\\(\\)])+") ) throw new IllegalArgumentException(); //CN
		StringTokenizer st=new StringTokenizer(expr,"+-*/()",true);
		radice=buildEspressione(st);
	}
	
	private Nodo buildOperando( StringTokenizer st ) {
		String tk=st.nextToken();
		if( tk.equals("(") ) return buildEspressione(st);
		int val=Integer.parseInt(tk);
		NodoOperando opnd=new NodoOperando();
		opnd.val=val;
		return opnd;
	}
	private Nodo buildEspressione( StringTokenizer st ) {
		Nodo radice=buildOperando(st);
		while( st.hasMoreTokens() ) {
			char op=st.nextToken().charAt(0);
			if( op==')' ) return radice;
			NodoOperatore nop=new NodoOperatore();
			nop.op=op;
			nop.fS=radice;
			Nodo opnd=buildOperando(st);
			nop.fD=opnd;
			radice=nop;
		}
		return radice;
	}
	
	public void postOrder() {//genera la RPN dell'espressione
		postOrder( radice );
		System.out.println();
	}
	public void postOrder( Nodo radice ) {
		if( radice!=null ) {
			postOrder( radice.fS );
			postOrder( radice.fD );
			System.out.print(radice+" ");
		}
	}
	public void preOrder() {
		preOrder( radice );
		System.out.println();
	}
	private void preOrder( Nodo radice ) {
		if( radice!=null ) {
			System.out.print( radice+" " );
			preOrder( radice.fS );
			preOrder( radice.fD );
		}
	}
	
	public void inOrder() {
		inOrder( radice );
		System.out.println();
	}
	private void inOrder( Nodo radice ) {
		if( radice!=null ) {
			if( radice instanceof NodoOperatore ) System.out.print("(");
			inOrder( radice.fS );
			System.out.print( radice );
			inOrder( radice.fD );
			if( radice instanceof NodoOperatore ) System.out.print(")");
		}
	}
	
	public void inOrder( List<String> lista ) {
		inOrder( radice, lista );
	}//inOrder
	private void inOrder( Nodo radice, List<String> lista ) {
		if( radice!=null ) {
			if( radice instanceof NodoOperatore ) lista.addLast("(");
			inOrder( radice.fS, lista );
			lista.addLast( radice.toString() );
			inOrder( radice.fD, lista );
			if( radice instanceof NodoOperatore ) lista.addLast(")");
		}		
	}//inOrder
	
	public int valore() {
		if( radice==null ) throw new RuntimeException("Nessuna Espressione!");
		return valore( radice );
	}
	private int valore( Nodo radice ) {
		if( radice instanceof NodoOperando ) return ((NodoOperando)radice).val;
		int opnd1=valore(radice.fS);
		int opnd2=valore(radice.fD);
		char op=((NodoOperatore)radice).op;
		switch( op ) {
		case '+': return opnd1+opnd2;
		case '-': return opnd1-opnd2;
		case '*': return opnd1*opnd2;
		default : return opnd1/opnd2;
		}
	}
	
	public String toString() {
		//RIFARE usando l'iteratore di l, ed evitando di generare [ e ]
		List<String> l=new LinkedList<>();
		inOrder(l);
		return l.toString();
	}
	
	public boolean equals( Object x ) {
		return false; //TODO
	}
	
	public int hashCode() {
		String s=this.toString();
		return s.hashCode();
	}
	
	//---EserciziEsame---//
	public void buildEsame(String rpn) {
		if(!rpn.matches("([\\d]+|[\\+\\-\\*\\/\\s])+"))throw new IllegalArgumentException();
		StringTokenizer st=new StringTokenizer(rpn," ");
		buildEsame(st);
		
	}
	private void buildEsame(StringTokenizer st) {
		StackArray<Nodo> Stack=new StackArray<>();
		while(st.hasMoreTokens()) {
			String rpn=st.nextToken();
			if(rpn.matches("[\\d]+")) {
				NodoOperando Operando=new NodoOperando();
				Operando.val=Integer.parseInt(rpn);
				Stack.push(Operando);
			}
			if(rpn.matches("[\\+\\-\\*\\/]")) {
				NodoOperatore Operatore=new NodoOperatore();
				Operatore.op=rpn.charAt(0);
				Operatore.fD=Stack.pop();
				Operatore.fS=Stack.pop();
				Stack.push(Operatore);
			}
		}
		radice=Stack.pop();
		
		if(!Stack.isEmpty())throw new IllegalStateException();
		
	}
	public void inOrderIte(List<String> lv) {
		StackArray<Nodo> InOrder=new StackArray<>();
		Nodo nc=radice;
		while(nc!=null || !InOrder.isEmpty()) {
			while(nc!=null) {//raggiungiamo l'estremo sinistro
				
				InOrder.push(nc);
				nc=nc.fS;
			}
			//nc==null
			nc=InOrder.pop();
			lv.addLast(nc.toString());
			nc=nc.fD;
		}
	}
	
		
	
	
	
	
	public static void main( String[] args ) {
		AlberoEspressione ae=new AlberoEspressione();
		List<String> lv=new LinkedList<>();
		String expr="2 3 4 * + 17 2 / 5 - * ";
		ae.buildEsame(expr);
		System.out.println(ae);

		System.out.println("Espressione originale "+expr+" ");
		System.out.println("Espressione in versione preFix");
		ae.preOrder();
		System.out.println("Espressione in versione postFix (o RPN)");
		ae.postOrder();
		System.out.println(ae.radice);
		System.out.println(ae.radice.fS);
		
		System.out.println("Espressione in versione inFix");
		ae.inOrderIte(lv);
		System.out.println(lv);
		System.out.println("valore("+expr+")="+ae.valore());
	}
}//AlberoEspressione
