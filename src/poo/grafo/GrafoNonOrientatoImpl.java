/*     */ package poo.grafo;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedList;
/*     */ import java.util.Map;
/*     */ import java.util.NoSuchElementException;
/*     */ import java.util.Set;
/*     */ 
/*     */ public class GrafoNonOrientatoImpl<N> extends GrafoNonOrientatoAstratto<N> {
/*   8 */   private Map<N, LinkedList<Arco<N>>> grafo = new HashMap<>();
/*     */   
/*     */   private class IteratoreGrafo implements Iterator<N> {
/*     */     private Iterator<N> it;
/*     */     
/*     */     private N u;
/*     */     
/*     */     private IteratoreGrafo() {
/*  11 */       this.it = GrafoNonOrientatoImpl.this.grafo.keySet().iterator();
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
/*  19 */       Set<N> chiavi = GrafoNonOrientatoImpl.this.grafo.keySet();
/*  20 */       Iterator<N> it = chiavi.iterator();
/*  21 */       while (it.hasNext()) {
/*  22 */         N v = it.next();
/*  23 */         Iterator<? extends Arco<N>> adiacenti = (
/*  24 */           (LinkedList<? extends Arco<N>>)GrafoNonOrientatoImpl.this.grafo.get(v)).iterator();
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
/*     */   private class IteratoreAdiacenti implements Iterator<Arco<N>> {
/*     */     private Iterator<? extends Arco<N>> it;
/*     */     
/*  37 */     private Arco<N> a = null;
/*     */     
/*     */     public IteratoreAdiacenti(N u) {
/*  39 */       this.it = ((LinkedList<? extends Arco<N>>)GrafoNonOrientatoImpl.this.grafo.get(u)).iterator();
/*     */     }
/*     */     
/*     */     public boolean hasNext() {
/*  42 */       return this.it.hasNext();
/*     */     }
/*     */     
/*     */     public Arco<N> next() {
/*  45 */       if (!this.it.hasNext())
/*  46 */         throw new NoSuchElementException(); 
/*  47 */       this.a = this.it.next();
/*  48 */       return this.a;
/*     */     }
/*     */     
/*     */     public void remove() {
/*  51 */       this.it.remove();
/*  53 */       Arco<N> ai = new Arco<>(this.a.getDestinazione(), 
/*  54 */           this.a.getOrigine());
/*  55 */       LinkedList<Arco<N>> ad = (LinkedList<Arco<N>>)GrafoNonOrientatoImpl.this.grafo.get(ai.getOrigine());
/*  56 */       ad.remove(ai);
/*     */     }
/*     */   }
/*     */   
/*     */   public Iterator<N> iterator() {
/*  61 */     return new IteratoreGrafo();
/*     */   }
/*     */   
/*     */   public Iterator<? extends Arco<N>> adiacenti(N u) {
/*  64 */     if (!this.grafo.containsKey(u))
/*  65 */       throw new IllegalArgumentException(); 
/*  66 */     return new IteratoreAdiacenti(u);
/*     */   }
/*     */   
/*     */   public boolean esisteNodo(N u) {
/*  69 */     return this.grafo.containsKey(u);
/*     */   }
/*     */   
/*     */   public int numNodi() {
/*  72 */     return this.grafo.size();
/*     */   }
/*     */   
/*     */   public void insNodo(N u) {
/*  75 */     if (esisteNodo(u))
/*  76 */       throw new RuntimeException("Nodo gia' presente durante insNodo"); 
/*  77 */     this.grafo.put(u, new LinkedList<>());
/*     */   }
/*     */   
/*     */   public void insArco(Arco<N> a) {
/*  80 */     if (!this.grafo.containsKey(a.getOrigine()) || 
/*  81 */       !this.grafo.containsKey(a.getDestinazione()))
/*  82 */       throw new RuntimeException("Nodo(i) non esistente(i) durante insArco"); 
/*  85 */     LinkedList<Arco<N>> ad = this.grafo.get(a.getOrigine());
/*  86 */     ad.remove(a);
/*  87 */     ad.add(a);
/*  89 */     if (!this.grafo.containsKey(a.getDestinazione()))
/*  90 */       this.grafo.put(a.getDestinazione(), 
/*  91 */           new LinkedList<>()); 
/*  93 */     ad = this.grafo.get(a.getDestinazione());
/*  94 */     Arco<N> inverso = new Arco<>(a.getDestinazione(), 
/*  95 */         a.getOrigine());
/*  96 */     ad.remove(inverso);
/*  97 */     ad.add(inverso);
/*     */   }
/*     */   
/*     */   public void rimuoviNodo(N u) {
/* 100 */     this.grafo.remove(u);
/* 101 */     Set<N> chiavi = this.grafo.keySet();
/* 102 */     Iterator<N> it = chiavi.iterator();
/* 103 */     while (it.hasNext()) {
/* 104 */       N v = it.next();
/* 105 */       Iterator<? extends Arco<N>> adiacenti = (
/* 106 */         (LinkedList<? extends Arco<N>>)this.grafo.get(v)).iterator();
/* 107 */       while (adiacenti.hasNext()) {
/* 108 */         Arco<N> a = adiacenti.next();
/* 109 */         if (a.getDestinazione().equals(u)) {
/* 110 */           adiacenti.remove();
/*     */           break;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void rimuoviArco(Arco<N> a) {
/* 117 */     N u = a.getOrigine(), v = a.getDestinazione();
/* 118 */     if (this.grafo.containsKey(u)) {
/* 119 */       LinkedList<Arco<N>> ad = this.grafo.get(u);
/* 120 */       Iterator<? extends Arco<N>> adiacenti = 
/* 121 */         ad.iterator();
/* 122 */       while (adiacenti.hasNext()) {
/* 123 */         Arco<N> ar = adiacenti.next();
/* 124 */         if (ar.equals(a)) {
/* 125 */           adiacenti.remove();
/*     */           break;
/*     */         } 
/*     */       } 
/*     */     } 
/* 129 */     if (this.grafo.containsKey(v)) {
/* 130 */       Arco<N> duale = new Arco<>(a.getDestinazione(), 
/* 131 */           a.getOrigine());
/* 132 */       LinkedList<Arco<N>> ad = this.grafo.get(v);
/* 133 */       Iterator<? extends Arco<N>> adiacenti = 
/* 134 */         ad.iterator();
/* 135 */       while (adiacenti.hasNext()) {
/* 136 */         Arco<N> ar = adiacenti.next();
/* 137 */         if (ar.equals(duale)) {
/* 138 */           adiacenti.remove();
/*     */           break;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public Grafo<N> factory() {
/* 144 */     return new GrafoNonOrientatoImpl();
/*     */   }
/*     */   
/*     */   public void clear() {
/* 147 */     this.grafo.clear();
/*     */   }
/*     */ }


/* Location:              C:\Users\carme\Desktop\grafi (1).jar!\poo\grafo\GrafoNonOrientatoImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */