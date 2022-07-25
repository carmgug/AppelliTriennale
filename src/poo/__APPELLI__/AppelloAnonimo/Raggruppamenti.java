package poo.__APPELLI__.AppelloAnonimo;
import java.util.Collection;
import java.util.LinkedList;

public class Raggruppamenti {
  int[] V;
  
  int S;
  
  LinkedList<int[]> Soluzioni;
  
  public Raggruppamenti(int[] V, int S) {
    this.S = S;
    this.V = new int[V.length];
    System.arraycopy(V, 0, this.V, 0, V.length);
    Soluzioni = new LinkedList<>();
  }
  
  private boolean assegnabile(int[] coppia) {
    if (this.Soluzioni.size() == 0)
      return true; 
    int x1 = coppia[0], x2 = coppia[1];
    for (int[] sol :Soluzioni) {
      if ((x1 == sol[0] && x2 == sol[1]) || (x2 == sol[0] && x1 == sol[1]))
        return false; 
    } 
    return true;
  }
  
  private void assegna(int[] coppia) {
    this.Soluzioni.add(coppia);
  }
  
  private void deassegna(int[] coppia) {
    this.Soluzioni.remove(coppia);
  }
  
  private Collection<int[]> ScelteAmmesse(int i) {
    LinkedList<int[]> scelteAmmesse = (LinkedList)new LinkedList<>();
    for (int j=i+1;j<V.length;j++) {
      if (V[i] + V[j] == S) {
        int[] coppia = { V[i], V[j] };
        scelteAmmesse.add(coppia);
      } 
    } 
    return (Collection<int[]>)scelteAmmesse;
  }
  
  private void tentativo(int i) {
    Collection<int[]> scelteAmmesse = ScelteAmmesse(i);
    for (int[] coppia : scelteAmmesse) {
      if (assegnabile(coppia)) {
        assegna(coppia);
        if (i == (this.V.length - 1) / 2 - 1) {ScriviSoluzione();} 
        else tentativo(i + 1);
        deassegna(coppia);
      } 
    } 
    if (i==(V.length-1)/2-1)ScriviSoluzione();
    else tentativo(i + 1);
    
  }
  
  private void ScriviSoluzione() {
    System.out.println();
    for (int[] coppia : this.Soluzioni)
      System.out.print("<" + coppia[0] + "," + coppia[1] + ">"); 
  }
  
  public void risolvi() {
    tentativo(0);
  }
  
  public static void main(String[] args) {
    int[] V = {-1,0,5,8,-4,4,2,9,2,-5};
    int S = 4;
    
    Raggruppamenti R = new Raggruppamenti(V, S);
    R.risolvi();
   
  }
}
