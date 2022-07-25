/*     */ package poo.grafo;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedList;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ 
/*     */ public class GrafoOrientatoPesatoImpl<N> extends GrafoOrientatoPesatoAstratto<N> {
/*   8 */   private Map<N, LinkedList<ArcoPesato<N>>> grafo = new HashMap<>();
/*     */   
/*     */   private class IteratoreGrafo implements Iterator<N> {
/*  11 */     private Iterator<N> it = GrafoOrientatoPesatoImpl.this.grafo.keySet().iterator();
/*     */     
/*  12 */     private N u = null;
/*     */     
/*     */     public boolean hasNext() {
/*  13 */       return this.it.hasNext();
/*     */     }
/*     */     
/*     */     public N next() {
/*  14 */       return this.u = this.it.next();
/*     */     }
/*     */     
/*     */     public void remove() {
/*  16 */       this.it.remove();
/*  19 */       Set<N> chiavi = GrafoOrientatoPesatoImpl.this.grafo.keySet();
/*  20 */       Iterator<N> it = chiavi.iterator();
/*  21 */       while (it.hasNext()) {
/*  22 */         N v = it.next();
/*  23 */         Iterator<? extends Arco<N>> adiacenti = (
/*  24 */           (LinkedList<? extends Arco<N>>)GrafoOrientatoPesatoImpl.this.grafo.get(v)).iterator();
/*  25 */         while (adiacenti.hasNext()) {
/*  26 */           Arco<N> a = adiacenti.next();
/*  27 */           if (a.getDestinazione().equals(this.u)) {
/*  28 */             adiacenti.remove();
/*     */             break;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     }
/*     */     
/*     */     private IteratoreGrafo() {}
/*     */   }
/*     */   
/*     */   public Iterator<N> iterator() {
/*  36 */     return new IteratoreGrafo();
/*     */   }
/*     */   
/*     */   public Iterator<ArcoPesato<N>> adiacenti(N u) {
/*  39 */     if (!this.grafo.containsKey(u))
/*  40 */       throw new IllegalArgumentException(); 
/*  41 */     return ((LinkedList<ArcoPesato<N>>)this.grafo.get(u)).iterator();
/*     */   }
/*     */   
/*     */   public boolean esisteNodo(N u) {
/*  44 */     return this.grafo.containsKey(u);
/*     */   }
/*     */   
/*     */   public int numNodi() {
/*  47 */     return this.grafo.size();
/*     */   }
/*     */   
/*     */   public void insNodo(N u) {
/*  50 */     if (esisteNodo(u))
/*  51 */       throw new RuntimeException("Nodo gia' presente durante insNodo"); 
/*  52 */     this.grafo.put(u, new LinkedList<>());
/*     */   }
/*     */   
/*     */   public void insArco(Arco<N> a) {
/*  55 */     insArco(new ArcoPesato<>(a.getOrigine(), 
/*  56 */           a.getDestinazione()));
/*     */   }
/*     */   
/*     */   public void insArco(Arco<N> a, Peso peso) {
/*  59 */     insArco(new ArcoPesato<>(a.getOrigine(), 
/*  60 */           a.getDestinazione(), 
/*  61 */           peso));
/*     */   }
/*     */   
/*     */   public void insArco(ArcoPesato<N> ap) {
/*  64 */     if (!this.grafo.containsKey(ap.getOrigine()) || 
/*  65 */       !this.grafo.containsKey(ap.getDestinazione()))
/*  66 */       throw new RuntimeException("Nodo(i) non esistente(i) durante insArco"); 
/*  68 */     LinkedList<ArcoPesato<N>> ad = this.grafo.get(ap.getOrigine());
/*  69 */     ad.add(ap);
/*     */   }
/*     */   
/*     */   public void rimuoviNodo(N u) {
/*  72 */     this.grafo.remove(u);
/*  73 */     Set<N> chiavi = this.grafo.keySet();
/*  74 */     Iterator<N> it = chiavi.iterator();
/*  75 */     while (it.hasNext()) {
/*  76 */       N v = it.next();
/*  77 */       Iterator<ArcoPesato<N>> adiacenti = (
/*  78 */         (LinkedList<ArcoPesato<N>>)this.grafo.get(v)).iterator();
/*  79 */       while (adiacenti.hasNext()) {
/*  80 */         ArcoPesato<N> a = adiacenti.next();
/*  81 */         if (a.getDestinazione().equals(u)) {
/*  82 */           adiacenti.remove();
/*     */           break;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void rimuoviArco(Arco<N> a) {
/*  89 */     if (!(a instanceof ArcoPesato))
/*  90 */       throw new IllegalArgumentException(); 
/*  91 */     rimuoviArco((ArcoPesato<N>)a);
/*     */   }
/*     */   
/*     */   public void rimuoviArco(ArcoPesato<N> a) {
/*  94 */     N u = a.getOrigine();
/*  95 */     if (this.grafo.containsKey(u)) {
/*  96 */       LinkedList<ArcoPesato<N>> ad = this.grafo.get(u);
/*  97 */       Iterator<ArcoPesato<N>> adiacenti = 
/*  98 */         ad.iterator();
/*  99 */       while (adiacenti.hasNext()) {
/* 100 */         ArcoPesato<N> ar = adiacenti.next();
/* 101 */         if (ar.equals(a)) {
/* 102 */           adiacenti.remove();
/*     */           break;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public GrafoPesato<N> factory() {
/* 108 */     return new GrafoOrientatoPesatoImpl();
/*     */   }
/*     */   
/*     */   public void clear() {
/* 111 */     this.grafo.clear();
/*     */   }
/*     */ }


/* Location:              C:\Users\carme\Desktop\grafi (1).jar!\poo\grafo\GrafoOrientatoPesatoImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */