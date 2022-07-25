/*     */ package poo.grafo;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedList;
/*     */ import java.util.Map;
/*     */ import java.util.NoSuchElementException;
/*     */ import java.util.Set;
/*     */ 
/*     */ public class GrafoNonOrientatoPesatoImpl<N> extends GrafoNonOrientatoPesatoAstratto<N> {
/*   8 */   private Map<N, LinkedList<ArcoPesato<N>>> grafo = new HashMap<>();
/*     */   
/*     */   private class IteratoreGrafo implements Iterator<N> {
/*     */     private Iterator<N> it;
/*     */     
/*     */     private N u;
/*     */     
/*     */     private IteratoreGrafo() {
/*  11 */       this.it = GrafoNonOrientatoPesatoImpl.this.grafo.keySet().iterator();
/*  12 */       this.u = null;
/*     */     }
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
/*  19 */       Set<N> chiavi = GrafoNonOrientatoPesatoImpl.this.grafo.keySet();
/*  20 */       Iterator<N> it = chiavi.iterator();
/*  21 */       while (it.hasNext()) {
/*  22 */         N v = it.next();
/*  23 */         Iterator<? extends Arco<N>> adiacenti = (
/*  24 */           (LinkedList<? extends Arco<N>>)GrafoNonOrientatoPesatoImpl.this.grafo.get(v)).iterator();
/*  25 */         while (adiacenti.hasNext()) {
/*  26 */           Arco<N> a = adiacenti.next();
/*  27 */           if (a.getDestinazione().equals(this.u)) {
/*  28 */             adiacenti.remove();
/*     */             break;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   private class IteratoreAdiacenti implements Iterator<ArcoPesato<N>> {
/*     */     private Iterator<ArcoPesato<N>> it;
/*     */     
/*  38 */     private ArcoPesato<N> a = null;
/*     */     
/*     */     public IteratoreAdiacenti(N u) {
/*  40 */       this.it = ((LinkedList<ArcoPesato<N>>)GrafoNonOrientatoPesatoImpl.this.grafo.get(u)).iterator();
/*     */     }
/*     */     
/*     */     public boolean hasNext() {
/*  43 */       return this.it.hasNext();
/*     */     }
/*     */     
/*     */     public ArcoPesato<N> next() {
/*  46 */       if (!this.it.hasNext())
/*  47 */         throw new NoSuchElementException(); 
/*  48 */       this.a = this.it.next();
/*  49 */       return this.a;
/*     */     }
/*     */     
/*     */     public void remove() {
/*  52 */       this.it.remove();
/*  54 */       ArcoPesato<N> ai = 
/*  55 */         new ArcoPesato<>(this.a.getDestinazione(), 
/*  56 */           this.a.getOrigine(), this.a.getPeso());
/*  57 */       LinkedList<ArcoPesato<N>> ad = (LinkedList<ArcoPesato<N>>)GrafoNonOrientatoPesatoImpl.this.grafo.get(ai.getOrigine());
/*  58 */       ad.remove(ai);
/*     */     }
/*     */   }
/*     */   
/*     */   public Iterator<N> iterator() {
/*  63 */     return new IteratoreGrafo();
/*     */   }
/*     */   
/*     */   public Iterator<ArcoPesato<N>> adiacenti(N u) {
/*  67 */     if (!this.grafo.containsKey(u))
/*  68 */       throw new IllegalArgumentException(); 
/*  69 */     return new IteratoreAdiacenti(u);
/*     */   }
/*     */   
/*     */   public boolean esisteNodo(N u) {
/*  73 */     return this.grafo.containsKey(u);
/*     */   }
/*     */   
/*     */   public int numNodi() {
/*  77 */     return this.grafo.size();
/*     */   }
/*     */   
/*     */   public void insNodo(N u) {
/*  81 */     if (esisteNodo(u))
/*  82 */       throw new RuntimeException("Nodo gia' presente durante insNodo"); 
/*  83 */     this.grafo.put(u, new LinkedList<>());
/*     */   }
/*     */   
/*     */   public void insArco(ArcoPesato<N> ap) {
/*  87 */     if (!this.grafo.containsKey(ap.getOrigine()) || 
/*  88 */       !this.grafo.containsKey(ap.getDestinazione()))
/*  89 */       throw new RuntimeException("Nodo(i) non esistente(i) durante insArco"); 
/*  91 */     LinkedList<ArcoPesato<N>> ad = this.grafo.get(ap.getOrigine());
/*  92 */     ad.add(ap);
/*  94 */     ArcoPesato<N> ai = 
/*  95 */       new ArcoPesato<>(ap.getDestinazione(), ap.getOrigine(), ap.getPeso());
/*  96 */     if (!this.grafo.containsKey(ai.getOrigine()))
/*  97 */       this.grafo.put(ai.getOrigine(), 
/*  98 */           new LinkedList<>()); 
/* 100 */     ad = this.grafo.get(ai.getOrigine());
/* 101 */     ad.add(ai);
/*     */   }
/*     */   
/*     */   public void rimuoviNodo(N u) {
/* 105 */     this.grafo.remove(u);
/* 106 */     Set<N> chiavi = this.grafo.keySet();
/* 107 */     Iterator<N> it = chiavi.iterator();
/* 108 */     while (it.hasNext()) {
/* 109 */       N v = it.next();
/* 110 */       Iterator<ArcoPesato<N>> adiacenti = (
/* 111 */         (LinkedList<ArcoPesato<N>>)this.grafo.get(v)).iterator();
/* 112 */       while (adiacenti.hasNext()) {
/* 113 */         ArcoPesato<N> a = adiacenti.next();
/* 114 */         if (a.getDestinazione().equals(u)) {
/* 115 */           adiacenti.remove();
/*     */           break;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void rimuoviArco(Arco<N> a) {
/* 123 */     if (!(a instanceof ArcoPesato))
/* 124 */       throw new IllegalArgumentException(); 
/* 125 */     rimuoviArco((ArcoPesato<N>)a);
/*     */   }
/*     */   
/*     */   public void rimuoviArco(ArcoPesato<N> a) {
/* 129 */     N u = a.getOrigine();
/* 130 */     if (this.grafo.containsKey(u)) {
/* 131 */       LinkedList<ArcoPesato<N>> ad = this.grafo.get(u);
/* 132 */       Iterator<ArcoPesato<N>> adiacenti = 
/* 133 */         ad.iterator();
/* 134 */       while (adiacenti.hasNext()) {
/* 135 */         ArcoPesato<N> ar = adiacenti.next();
/* 136 */         if (ar.equals(a)) {
/* 137 */           adiacenti.remove();
/*     */           break;
/*     */         } 
/*     */       } 
/*     */     } 
/* 142 */     ArcoPesato<N> ai = 
/* 143 */       new ArcoPesato<>(a.getDestinazione(), a.getOrigine(), a.getPeso());
/* 144 */     u = ai.getOrigine();
/* 145 */     if (this.grafo.containsKey(u)) {
/* 146 */       LinkedList<ArcoPesato<N>> ad = this.grafo.get(u);
/* 147 */       Iterator<ArcoPesato<N>> adiacenti = ad.iterator();
/* 148 */       while (adiacenti.hasNext()) {
/* 149 */         ArcoPesato<N> ar = adiacenti.next();
/* 150 */         if (ar.equals(ai)) {
/* 151 */           adiacenti.remove();
/*     */           break;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public GrafoPesato<N> factory() {
/* 158 */     return new GrafoOrientatoPesatoImpl<>();
/*     */   }
/*     */   
/*     */   public void clear() {
/* 162 */     this.grafo.clear();
/*     */   }
/*     */ }


/* Location:              C:\Users\carme\Desktop\grafi (1).jar!\poo\grafo\GrafoNonOrientatoPesatoImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */