package poo.__APPELLI__.Appello17072015;

import java.util.Scanner;
import java.util.Stack;
import java.util.StringTokenizer;

public class AlberoEspressione {

	
	
	final static String Operatore="[\\+\\-\\*\\/]";
	final static String Regex="("+Operatore+"|[\\s]+|[\\d]+)";
	
	public static int valutaEspressione(String rpn) {
		if(!rpn.matches(Regex)) throw new RuntimeException("Espressione Malformata");
		StringTokenizer st=new StringTokenizer(rpn," ");
		return valutaEspressione(st);
	}
	
	private static int valutaEspressione(StringTokenizer st) {
		Stack<String> stack=new Stack<>();
		while(st.hasMoreTokens()) {
			String x=st.nextToken();
			if(x.matches("[\\d]+")) {
				String Operando=x;
				stack.push(Operando);
			}
			else if(x.matches(Operatore)) {
				int op1=Integer.parseInt(stack.pop());
				int op2=Integer.parseInt(stack.pop());
				int ris=Operazione(op1,op2,x);
				String op=String.valueOf(ris);
				stack.push(op);
			}
		}
		int RisExpr=Integer.parseInt(stack.pop());
		if(!stack.isEmpty())throw new RuntimeException();
		return RisExpr;
	}
	
	
	private static int Operazione(int op1,int op2,String opc) {
		switch(opc) {
			case "+": return op1+op2;
			case "-": return op1-op2;
			case "*": return op1*op2;
			default: return op1/op2;
		}
		
		
		
	}
	
	
	public static void main(String [] arg) {
		
		
		Scanner sc=new Scanner(System.in);
		
		loop1:for(;;) {
			System.out.print("Inserisci Espressione RPN >>");
			String Expr=sc.nextLine();
			if(Expr.equals(".")) break;
			int ris=0;
			try {
				ris=valutaEspressione(Expr);
			}catch(Exception e) {
				System.out.println("Espressione Malformata");
				continue loop1;
			}
			System.out.println("Risultato-->"+ris);
		}
		sc.close();
		System.out.println("Bye...");
		
		
		
		
		
		
		
		
	}
	
	
}
