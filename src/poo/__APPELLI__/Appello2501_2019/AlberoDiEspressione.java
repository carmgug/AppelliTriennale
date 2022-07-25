package poo.__APPELLI__.Appello2501_2019;

import java.util.Stack;
import java.util.StringTokenizer;

public class AlberoDiEspressione {
	
	class Nodo{
		String info;
		Nodo fD,fS; 
	}
	
	Nodo radice=null;
	int size=0;
	
	
	
	
	String regex="([\\d]+|[\\s]+[\\+\\-\\/\\*])+";
	
	//riceve un espresisone in post-fis
	public void build(String rpn_expr) {
		if(!rpn_expr.matches(regex)) throw new IllegalArgumentException("Espressione Malforamata");
		StringTokenizer st=new StringTokenizer(rpn_expr," ");
	}
	
	
	
	
	private void build(StringTokenizer st) {
		Stack<Nodo> Stack=new Stack<Nodo>();
		while(st.hasMoreTokens()) {
			
			String elem=st.nextToken();
			if(elem.matches("[\\d]+")) {
				Nodo Operando=new Nodo();
				Operando.info=elem;
				Stack.push(Operando);
			}
			if(elem.matches("[\\+\\-\\/\\*]")) {
				Nodo Operatore=new Nodo();
				//preleviamo op2
				Operatore.fD=Stack.pop();
				Operatore.fS=Stack.pop();
				Stack.push(Operatore);
			}
		}
		
		radice=Stack.pop();
		if(!Stack.isEmpty()) throw new RuntimeException("Espressione Malformata");
	}
	
	
}
