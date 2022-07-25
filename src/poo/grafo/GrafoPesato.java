package poo.grafo;

import java.util.Iterator;

public interface GrafoPesato<N> extends Grafo<N> {
  void insArco(ArcoPesato<N> paramArcoPesato);
  
  void insArco(Arco<N> paramArco, Peso paramPeso);
  
  void insArco(N paramN1, N paramN2, Peso paramPeso);
  
  void modArco(ArcoPesato<N> paramArcoPesato, Peso paramPeso);
  
  Iterator<ArcoPesato<N>> adiacenti(N paramN);
  
  Peso peso(N paramN1, N paramN2);
}


/* Location:              C:\Users\carme\Desktop\grafi (1).jar!\poo\grafo\GrafoPesato.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */