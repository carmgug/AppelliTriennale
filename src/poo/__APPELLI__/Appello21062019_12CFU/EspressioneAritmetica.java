package poo.__APPELLI__.Appello21062019_12CFU;

import java.util.Comparator;
import java.util.Scanner;
import java.util.Stack;
import java.util.StringTokenizer;

public class EspressioneAritmetica  {
	final static String Segno="[\\+\\-\\*\\%\\^]";
	final static String Regex=("([\\d]+|"+Segno+"|[\\(\\)])+");
	
	public static int  Risolvi(String Espressione) {
		if(!Espressione.matches(Regex)) throw new IllegalArgumentException();
		StringTokenizer st=new StringTokenizer(Espressione,"+-%*^()",true);
		return ValutaEspressione(st);
	}
	
	private static int ValutaEspressione(StringTokenizer st) {
		Stack<Integer> Operandi=new Stack<Integer>();
		Stack<Character> Operatori=new Stack<Character>();
		
		
		while(st.hasMoreTokens()) {
			String elemento=st.nextToken();
			if(elemento.matches("[\\d]+")) {Operandi.push(Integer.parseInt(elemento));}
			if(elemento.matches(Segno)) { ValutaOperatore(elemento,Operatori,Operandi);}
			if(elemento.matches("[\\(]")) {Operandi.push(ValutaEspressione(st));}
			if(elemento.matches("[\\)]")) break;
		}
		while(!Operatori.isEmpty()) {
			int o2=Operandi.pop();
			int o1=Operandi.pop();
			Character op=Operatori.pop();
			switch(op) {
				case '+': Operandi.push(o1+o2); break;
				case '-': Operandi.push(o1-o2); break;
				case '/': Operandi.push(o1/o2); break;
				case '%': Operandi.push(o1%o2); break;
				case '*': Operandi.push(o1*o2);break;
				default : Operandi.push((int) Math.pow(o1, o2)); 
			}
			
		}
		
		if(Operandi.isEmpty() || Operandi.size()>1) throw new RuntimeException();
		
		return Operandi.pop();
		
		
		
	}
	
	private static void ValutaOperatore(String elemento,Stack<Character> Operatori,Stack<Integer> Operandi) {
		Character Opc=elemento.charAt(0);
		if(Operatori.isEmpty()) {Operatori.push(Opc); return;}
		Comparator<Character> c= 
				(Character op1,Character op2)-> {
				if(op1.equals(op2)) return 0;
				if(op1.equals('+') || op1.equals('-') && !op2.equals('-')) return -1;  
				if(op1.equals('/') || op1.equals('%') && !op2.equals('+') || !op2.equals('-') || !op2.equals('%')) return -1;
				if(op1.equals('*') && !op2.equals('/') || !op2.equals('%') || !op2.equals('+') || !op2.equals('-') || !op2.equals('%')) return -1;
				return 1;
			};
		
		Character op=Operatori.peek();
		if(c.compare(Opc,op)>0) {Operatori.push(Opc);return;}
		
		
		while(c.compare(Opc,op)<=0 ) {
			int o2=Operandi.pop();
			int o1=Operandi.pop();
			op=Operatori.pop();
			switch(op) {
				case '+': Operandi.push(o1+o2); break;
				case '-': Operandi.push(o1-o2); break;
				case '/': Operandi.push(o1/o2); break;
				case '%': Operandi.push(o1%o2); break;
				case '*': Operandi.push(o1*o2);break;
				default : Operandi.push((int) Math.pow(o1, o2)); 
			}
			//per il prossimo controllo
			if(Operatori.isEmpty()) break;
			op=Operatori.peek();
		}
		Operatori.push(Opc);
	}
	
	public static void main(String[] args){
		Scanner sc=new Scanner(System.in);
		System.out.println("Calcola Espressione: Premi Invio");
		sc.nextLine();
		for(;;) {
			System.out.print("Inserisci Espressione:\n"
							+ ">>");
			String Expr=sc.nextLine();
			if(Expr.equals(".")) {sc.close();break;}
			try {
				System.out.println(" = "+Risolvi(Expr));
			}catch(Exception e) {
				System.out.println (" --> Espressione Malformata");
			}
		}
		
		
		
		
	}
	
}
