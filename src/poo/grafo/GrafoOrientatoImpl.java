/*    */ package poo.grafo;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ import java.util.Iterator;
/*    */ import java.util.LinkedList;
/*    */ import java.util.Map;
/*    */ import java.util.Set;
/*    */ 
/*    */ public class GrafoOrientatoImpl<N> extends GrafoOrientatoAstratto<N> {
/*  8 */   private Map<N, LinkedList<Arco<N>>> grafo = new HashMap<>();
/*    */   
/*    */   private class IteratoreGrafo implements Iterator<N> {
/* 11 */     private Iterator<N> it = GrafoOrientatoImpl.this.grafo.keySet().iterator();
/*    */     
/* 12 */     private N u = null;
/*    */     
/*    */     public boolean hasNext() {
/* 13 */       return this.it.hasNext();
/*    */     }
/*    */     
/*    */     public N next() {
/* 14 */       return this.u = this.it.next();
/*    */     }
/*    */     
/*    */     public void remove() {
/* 16 */       this.it.remove();
/* 19 */       Set<N> chiavi = GrafoOrientatoImpl.this.grafo.keySet();
/* 20 */       Iterator<N> it = chiavi.iterator();
/* 21 */       while (it.hasNext()) {
/* 22 */         N v = it.next();
/* 23 */         Iterator<? extends Arco<N>> adiacenti = (
/* 24 */           (LinkedList<? extends Arco<N>>)GrafoOrientatoImpl.this.grafo.get(v)).iterator();
/* 25 */         while (adiacenti.hasNext()) {
/* 26 */           Arco<N> a = adiacenti.next();
/* 27 */           if (a.getDestinazione().equals(this.u)) {
/* 28 */             adiacenti.remove();
/*    */             break;
/*    */           } 
/*    */         } 
/*    */       } 
/*    */     }
/*    */     
/*    */     private IteratoreGrafo() {}
/*    */   }
/*    */   
/*    */   public Iterator<N> iterator() {
/* 36 */     return new IteratoreGrafo();
/*    */   }
/*    */   
/*    */   public Iterator<? extends Arco<N>> adiacenti(N u) {
/* 39 */     if (!this.grafo.containsKey(u))
/* 40 */       throw new IllegalArgumentException(); 
/* 41 */     return ((LinkedList<? extends Arco<N>>)this.grafo.get(u)).iterator();
/*    */   }
/*    */   
/*    */   public boolean esisteNodo(N u) {
/* 44 */     return this.grafo.containsKey(u);
/*    */   }
/*    */   
/*    */   public int numNodi() {
/* 47 */     return this.grafo.size();
/*    */   }
/*    */   
/*    */   public void insNodo(N u) {
/* 50 */     if (esisteNodo(u))
/* 51 */       throw new RuntimeException("Nodo gia' presente durante insNodo"); 
/* 52 */     this.grafo.put(u, new LinkedList<>());
/*    */   }
/*    */   
/*    */   public void insArco(Arco<N> a) {
/* 55 */     if (!this.grafo.containsKey(a.getOrigine()) || 
/* 56 */       !this.grafo.containsKey(a.getDestinazione()))
/* 57 */       throw new RuntimeException("Nodo(i) non esistente(i) durante insArco"); 
/* 59 */     LinkedList<Arco<N>> ad = this.grafo.get(a.getOrigine());
/* 60 */     ad.remove(a);
/* 61 */     ad.add(a);
/*    */   }
/*    */   
/*    */   public void rimuoviNodo(N u) {
/* 64 */     this.grafo.remove(u);
/* 65 */     Set<N> chiavi = this.grafo.keySet();
/* 66 */     Iterator<N> it = chiavi.iterator();
/* 67 */     while (it.hasNext()) {
/* 68 */       N v = it.next();
/* 69 */       Iterator<? extends Arco<N>> adiacenti = (
/* 70 */         (LinkedList<? extends Arco<N>>)this.grafo.get(v)).iterator();
/* 71 */       while (adiacenti.hasNext()) {
/* 72 */         Arco<N> a = adiacenti.next();
/* 73 */         if (a.getDestinazione().equals(u)) {
/* 74 */           adiacenti.remove();
/*    */           break;
/*    */         } 
/*    */       } 
/*    */     } 
/*    */   }
/*    */   
/*    */   public void rimuoviArco(Arco<N> a) {
/* 80 */     N u = a.getOrigine();
/* 81 */     if (this.grafo.containsKey(u)) {
/* 82 */       LinkedList<Arco<N>> ad = this.grafo.get(u);
/* 83 */       Iterator<? extends Arco<N>> adiacenti = 
/* 84 */         ad.iterator();
/* 85 */       while (adiacenti.hasNext()) {
/* 86 */         Arco<N> ar = adiacenti.next();
/* 87 */         if (ar.equals(a)) {
/* 88 */           adiacenti.remove();
/*    */           break;
/*    */         } 
/*    */       } 
/*    */     } 
/*    */   }
/*    */   
/*    */   public Grafo<N> factory() {
/* 94 */     return new GrafoOrientatoImpl();
/*    */   }
/*    */   
/*    */   public void clear() {
/* 97 */     this.grafo.clear();
/*    */   }
/*    */ }


/* Location:              C:\Users\carme\Desktop\grafi (1).jar!\poo\grafo\GrafoOrientatoImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */