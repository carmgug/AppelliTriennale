package poo.__APPELLI__.AppelloAnonimo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.StringTokenizer;
import poo.util.StackArray;

public class ValutazioneEspressioneRPN {
  static final String Regex_Variabili = "[\\w]+[=][\\d]";
  
  static final String Regex_Expr = "(\\d+|[A-z]+|\\+|\\-|\\*|\\/|\\s)+";
  
  static HashMap<String, Integer> Mappa = new HashMap<>();
  
  private static boolean vericaFile(String nomeFile) throws IOException {
    BufferedReader br = new BufferedReader(new FileReader(nomeFile));
    for(;;){
      String Variabile = br.readLine();
      if (Variabile==null) {br.close();break;}
      if (!Variabile.matches(Regex_Variabili)) { br.close();return false;}
      StringTokenizer st = new StringTokenizer(Variabile,"=");
      Variabile = st.nextToken();
      String Numero = st.nextToken();
      Mappa.put(Variabile.toUpperCase(), Integer.parseInt(Numero));
    }
    return true;
  }
  
  private static int risolvi(String Expr) {
    if (!Expr.matches("(\\d+|[A-z]+|\\+|\\-|\\*|\\/|\\s)+"))
      throw new IllegalArgumentException(); 
    StringTokenizer st = new StringTokenizer(Expr, " ");
    return ValutaEspressione(st);
  }
  
  private static int ValutaEspressione(StringTokenizer st) {
    StackArray<Integer> StackOperandi = new StackArray();
    while (st.hasMoreTokens()) {
      String Token = st.nextToken();
      if (Token.matches("[\\d]+")) StackOperandi.push(Integer.parseInt(Token)); 
      if (Token.matches("[A-z]+")) {
        Integer valore = Mappa.get(Token.toUpperCase());
        if (valore == null) throw new IllegalStateException(); 
        StackOperandi.push(valore);
      } 
      if (Token.matches("[\\+\\*\\/\\-]")) StackOperandi.push(EseguiOperazione(Token, StackOperandi)); 
    }
    
    int Risultato = StackOperandi.pop();
    if (!StackOperandi.isEmpty())
      throw new IllegalStateException(); 
    return Risultato;
  }
  
  private static int EseguiOperazione(String Operazione, StackArray<Integer> StackOperandi) {
    int op2 = StackOperandi.pop();
    int op1 = StackOperandi.pop();
    switch (Operazione) {
      case "*": return op1 * op2;
      case "+": return op1 + op2;
      case "/": return op1 / op2;
      default : return op1 - op2;
    }
  }
  
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.println("ValutaEspressione: Premi Invio");
    sc.nextLine();
    while (true) {
      System.out.println("Inserire Nome File: ");
      String Nome_File = sc.nextLine();
      try {
        if (!vericaFile(Nome_File))
          throw new IllegalArgumentException(); 
        break;
      } catch (Exception e) {
        System.out.println("Inserire Nome di un file esistente e valido.");
      } 
    } 
    System.out.println("File Letto Correttamente : Premi Invio");
    sc.nextLine();
    while (true) {
      System.out.println("Inserire Espressione: ");
      System.out.print(">>");
      String Expr = sc.nextLine();
      if (Expr.equals("."))
        break; 
      try {
        System.out.println(risolvi(Expr));
      } catch (Exception exp) {
        System.out.println("Espressione Malformata");
      } 
    } 
    sc.close();
    System.exit(0);
  }
}