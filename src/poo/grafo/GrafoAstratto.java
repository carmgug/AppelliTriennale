/*     */ package poo.grafo;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ 
/*     */ public abstract class GrafoAstratto<N> implements Grafo<N> {
/*     */   public boolean esisteNodo(N u) {
/*   7 */     for (N v : this) {
/*   8 */       if (v.equals(u))
/*   8 */         return true; 
/*     */     } 
/*   9 */     return false;
/*     */   }
/*     */   
/*     */   public boolean esisteArco(Arco<N> a) {
/*  13 */     N u = a.getOrigine();
/*  14 */     if (esisteNodo(u)) {
/*  15 */       Iterator<? extends Arco<N>> it = adiacenti(u);
/*  16 */       while (it.hasNext()) {
/*  17 */         Arco<N> ar = it.next();
/*  18 */         if (ar.equals(a))
/*  19 */           return true; 
/*     */       } 
/*     */     } 
/*  23 */     return false;
/*     */   }
/*     */   
/*     */   public boolean esisteArco(N u, N v) {
/*  27 */     return esisteArco(new Arco<>(u, v));
/*     */   }
/*     */   
/*     */   public int numNodi() {
/*  31 */     int n = 0;
/*  32 */     for (N u : this)
/*  32 */       n++; 
/*  33 */     return n;
/*     */   }
/*     */   
/*     */   public int numArchi() {
/*  37 */     int na = 0;
/*  38 */     for (N u : this) {
/*  39 */       Iterator<? extends Arco<N>> it = adiacenti(u);
/*  40 */       while (it.hasNext()) {
/*  41 */         it.next();
/*  42 */         na++;
/*     */       } 
/*     */     } 
/*  45 */     return na;
/*     */   }
/*     */   
/*     */   public void insArco(N u, N v) {
/*  49 */     insArco(new Arco<>(u, v));
/*     */   }
/*     */   
/*     */   public void rimuoviNodo(N u) {
/*  53 */     Iterator<N> it = iterator();
/*  54 */     while (it.hasNext()) {
/*  55 */       N v = it.next();
/*  56 */       if (v.equals(u)) {
/*  57 */         it.remove();
/*     */         break;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void rimuoviArco(Arco<N> a) {
/*  64 */     N u = a.getOrigine();
/*  65 */     if (esisteNodo(u)) {
/*  66 */       Iterator<? extends Arco<N>> it = adiacenti(u);
/*  67 */       while (it.hasNext()) {
/*  68 */         Arco<N> ar = it.next();
/*  69 */         if (ar.equals(a)) {
/*  70 */           it.remove();
/*     */           break;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void rimuoviArco(N u, N v) {
/*  78 */     rimuoviArco(new Arco<>(u, v));
/*     */   }
/*     */   
/*     */   public abstract Grafo<N> factory();
/*     */   
/*     */   public void clear() {
/*  84 */     Iterator<N> it = iterator();
/*  85 */     while (it.hasNext()) {
/*  86 */       it.next();
/*  87 */       it.remove();
/*     */     } 
/*     */   }
/*     */   
/*     */   public Grafo<N> copia() {
/*  92 */     Grafo<N> copia = factory();
/*  93 */     for (N u : this)
/*  94 */       copia.insNodo(u); 
/*  96 */     for (N u : this) {
/*  97 */       Iterator<? extends Arco<N>> it = adiacenti(u);
/*  98 */       while (it.hasNext()) {
/*  99 */         Arco<N> ac = it.next();
/* 100 */         copia.insArco(new Arco<>(u, ac.getDestinazione()));
/*     */       } 
/*     */     } 
/* 103 */     return copia;
/*     */   }
/*     */   
/*     */   private boolean equals(GrafoAstratto<N> g1, GrafoAstratto<N> g2, N u) {
/* 107 */     for (N v : g1) {
/* 108 */       Arco<N> a = new Arco<>(u, v);
/* 109 */       if (g1.esisteArco(a) && !g2.esisteArco(a))
/* 109 */         return false; 
/* 110 */       if (g2.esisteArco(a) && !g1.esisteArco(a))
/* 110 */         return false; 
/*     */     } 
/* 112 */     return true;
/*     */   }
/*     */   
/*     */   public boolean equals(Object o) {
/* 118 */     if (!(o instanceof GrafoAstratto))
/* 118 */       return false; 
/* 119 */     if (o == this)
/* 119 */       return true; 
/* 120 */     GrafoAstratto<N> g = (GrafoAstratto<N>)o;
/* 121 */     if (numNodi() != g.numNodi() || 
/* 122 */       numArchi() != g.numArchi())
/* 122 */       return false; 
/* 123 */     for (N u : this) {
/* 124 */       if (!g.esisteNodo(u))
/* 124 */         return false; 
/* 125 */       if (!equals(this, g, u))
/* 125 */         return false; 
/*     */     } 
/* 127 */     return true;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 131 */     int p = 41, h = 0;
/* 132 */     for (N u : this) {
/* 133 */       h = h * p + u.hashCode();
/* 134 */       for (N v : this) {
/* 135 */         Arco<N> a = new Arco<>(u, v);
/* 136 */         if (esisteArco(a))
/* 137 */           h = h * p + a.hashCode(); 
/*     */       } 
/*     */     } 
/* 141 */     return h;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 145 */     StringBuilder sb = new StringBuilder(500);
/* 146 */     for (N u : this) {
/* 147 */       sb.append(u);
/* 147 */       sb.append(':');
/* 147 */       sb.append(' ');
/* 148 */       Iterator<? extends Arco<N>> it = adiacenti(u);
/* 149 */       while (it.hasNext())
/* 150 */         sb.append(it.next()); 
/* 152 */       sb.append('\n');
/*     */     } 
/* 154 */     return sb.toString();
/*     */   }
/*     */ }


/* Location:              C:\Users\carme\Desktop\grafi (1).jar!\poo\grafo\GrafoAstratto.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */