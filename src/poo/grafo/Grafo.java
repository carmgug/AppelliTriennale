package poo.grafo;

import java.util.Iterator;

public interface Grafo<N> extends Iterable<N> {
  int numNodi();
  
  int numArchi();
  
  boolean esisteNodo(N paramN);
  
  boolean esisteArco(Arco<N> paramArco);
  
  boolean esisteArco(N paramN1, N paramN2);
  
  void insNodo(N paramN);
  
  void insArco(Arco<N> paramArco);
  
  void insArco(N paramN1, N paramN2);
  
  void rimuoviNodo(N paramN);
  
  void rimuoviArco(Arco<N> paramArco);
  
  void rimuoviArco(N paramN1, N paramN2);
  
  Iterator<? extends Arco<N>> adiacenti(N paramN);
  
  void clear();
  
  Grafo<N> copia();
}


/* Location:              C:\Users\carme\Desktop\grafi (1).jar!\poo\grafo\Grafo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */