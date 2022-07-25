package poo.__APPELLI__.AppelloAnonimoCopia;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Stack;
import java.util.StringTokenizer;

public class EspressioneAritmetica {
	
	HashMap<String,Integer> Variabili;
	final static String Regex="([\\w]+=[\\d]+)";
	final static String Regex_Expr="([\\d]+|[\\w]+|[\\s]+|[\\+\\-\\*\\/])+";
	
	
	public EspressioneAritmetica(String NomeFile) throws IOException {
		Variabili=new HashMap<>();
		File f =new File(NomeFile);
		if(!f.exists()) throw new FileNotFoundException();
		InserisciVariabili(NomeFile);
	}
	
	private void  InserisciVariabili(String NomeFile) throws IOException {
		BufferedReader br=new BufferedReader(new FileReader(NomeFile));
		
		for(;;) {
			String linea=br.readLine();
			if(linea==null) break;
			if(!linea.matches(Regex)) throw new RuntimeException("File Irregolare");
			StringTokenizer st=new StringTokenizer(linea,"=");
			String Nome=st.nextToken().toUpperCase();
			Integer Var=Integer.parseInt(st.nextToken());
			//Attenzione se l'utente ridefinisce una variabili,viene inserita l'ultima ridefinizione
			Variabili.put(Nome, Var);
		}
		br.close();
	}
	
	public int risolvi(String Expr) {
		if(!Expr.matches(Regex_Expr)) throw new IllegalArgumentException();
		return ValutaEspressione(Expr);
		
	}
	
	private int ValutaEspressione(String Expr) {
		StringTokenizer st=new StringTokenizer(Expr," ");
		Stack<Integer> pila=new Stack<>();
		while(st.hasMoreTokens()) {
			String elem=st.nextToken();
			if(elem.matches("\\d+")) {pila.push(Integer.parseInt(elem));}
			else if(elem.matches("\\w+")) {
				Integer valore=Variabili.get(elem.toUpperCase());
				pila.push(valore);
			}
			else if(elem.matches("[\\+\\-\\*\\/]")) {
				int op2=pila.pop();
				int op1=pila.pop();
				pila.push(SvolgiOperazione(op1,op2,elem));
			}
			
		}
		int ris=pila.pop();
		if(!pila.isEmpty()) throw new RuntimeException("Espressione MalFormata");
		return ris;
		
	}
	
	private int SvolgiOperazione(int op1,int op2,String opc) {
		switch(opc) {
			case "+":return op1+op2;
			case "-":return op1-op2;
			case "/":return op1/op2;
			default : return op1*op2;
		}
	}
	

}
