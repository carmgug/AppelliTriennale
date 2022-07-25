package poo.Orale;

import java.util.StringTokenizer;

public class AlberoEspressione {
	
	private static class Nodo{
		Nodo fd,fs;
	}
	
	private static class NodoOperando extends Nodo{
		int val;
		public String toString() {
			return " "+val;
		}
		
	}
	
	private static class NodoOperatore extends Nodo{
		char op;
		public String toString() {
			return " "+op;
		}
	}
	
	
	Nodo radice;
	private final String regex="(\\d+|[\\+\\-\\*\\/]|[\\(\\)])+";
	
	
	public void build(String expr) {
		if(!expr.matches(regex)) throw new IllegalArgumentException();
		StringTokenizer st=new StringTokenizer(expr,"+*/-",true);
		radice=buildEspressione(st);
	}
	
	
	private Nodo buildOperando(StringTokenizer st) {
		String x=st.nextToken();
		if(x.equals("(")) return buildEspressione(st);
		Integer val=Integer.parseInt(x);
		NodoOperando op=new NodoOperando();
		op.val=val;
		return op;
	}
	
	private Nodo buildEspressione(StringTokenizer st) {
		radice=buildOperando(st);
		while(st.hasMoreTokens()) {
			char x=st.nextToken().charAt(0);
			if(x==')') return radice;
			NodoOperatore opc=new NodoOperatore();
			opc.op=x;
			opc.fs=radice;
			opc.fd=buildOperando(st);
			radice=opc;
		}
		
		return radice;
	}
	
	public int valore() {
		if(radice==null) throw new RuntimeException("Nessuna Espressione");
		return valore(radice);
	}
	
	
	public void InOrder() {
		InOrder(radice);
		System.out.println();
	}
	private void InOrder(Nodo radice) {
		if(radice==null) return;
		if(radice instanceof NodoOperatore) System.out.print("(");
		InOrder(radice.fs);
		System.out.print(radice);
		InOrder(radice.fd);
		if(radice instanceof NodoOperatore) System.out.print(")");
		
	}
	
	
	private int valore(Nodo radice) {
		if(radice instanceof NodoOperando) return ((NodoOperando)radice).val;
		int op1=valore(radice.fs);
		int op2=valore(radice.fd);
		char op=((NodoOperatore)radice).op;
		switch(op) {
			case '+': return op1+op2;
			case'-': return op1-op2;
			case'*': return op1*op2;
			default: return op1/op2;
		}
	}
	
	
	
}
