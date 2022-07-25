/*     */ package poo.grafo;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ 
/*     */ public abstract class GrafoOrientatoPesatoAstratto<N> extends GrafoOrientatoAstratto<N> implements GrafoOrientatoPesato<N> {
/*     */   public void insArco(Arco<N> a, Peso peso) {
/*  10 */     insArco(new ArcoPesato<>(
/*  11 */           a.getOrigine(), 
/*  12 */           a.getDestinazione(), 
/*  13 */           peso));
/*     */   }
/*     */   
/*     */   public void insArco(N u, N v, Peso peso) {
/*  17 */     insArco(new ArcoPesato<>(u, v, peso));
/*     */   }
/*     */   
/*     */   public void insArco(Arco<N> a) {
/*  20 */     insArco(new ArcoPesato<>(a.getOrigine(), a.getDestinazione()));
/*     */   }
/*     */   
/*     */   public void modArco(ArcoPesato<N> a, Peso peso) {
/*  23 */     if (!esisteNodo(a.getOrigine()) || 
/*  24 */       !esisteNodo(a.getDestinazione()))
/*     */       return; 
/*  25 */     Iterator<ArcoPesato<N>> it = adiacenti(a.getOrigine());
/*  26 */     while (it.hasNext()) {
/*  27 */       ArcoPesato<N> ap = it.next();
/*  28 */       if (ap.equals(a)) {
/*  29 */         ap.setPeso(peso);
/*     */         return;
/*     */       } 
/*     */     } 
/*  32 */     insArco(new ArcoPesato<>(a.getOrigine(), 
/*  33 */           a.getDestinazione(), peso));
/*     */   }
/*     */   
/*     */   public Peso peso(N u, N v) {
/*  37 */     Iterator<ArcoPesato<N>> iap = adiacenti(u);
/*  38 */     while (iap.hasNext()) {
/*  39 */       ArcoPesato<N> ap = iap.next();
/*  40 */       if (ap.getOrigine().equals(u) && 
/*  41 */         ap.getDestinazione().equals(v))
/*  42 */         return ap.getPeso(); 
/*     */     } 
/*  44 */     return null;
/*     */   }
/*     */   
/*     */   public Grafo<N> copia() {
/*  50 */     GrafoPesato<N> copia = factory();
/*  51 */     for (N u : this)
/*  52 */       copia.insNodo(u); 
/*  54 */     for (N u : this) {
/*  55 */       Iterator<ArcoPesato<N>> it = adiacenti(u);
/*  56 */       while (it.hasNext()) {
/*  57 */         ArcoPesato<N> ac = it.next();
/*  58 */         copia.insArco(new ArcoPesato<>(u, ac.getDestinazione(), new Peso(ac.getPeso())));
/*     */       } 
/*     */     } 
/*  61 */     return copia;
/*     */   }
/*     */   
/*     */   private boolean equals(GrafoOrientatoPesatoAstratto<N> g1, GrafoOrientatoPesatoAstratto<N> g2, N u) {
/*  66 */     Iterator<ArcoPesato<N>> it1 = g1.adiacenti(u);
/*  67 */     while (it1.hasNext()) {
/*  68 */       ArcoPesato<N> a1 = it1.next();
/*  69 */       if (!g2.esisteArco(a1))
/*  69 */         return false; 
/*  70 */       Iterator<ArcoPesato<N>> iterator = g2.adiacenti(u);
/*  71 */       while (iterator.hasNext()) {
/*  72 */         ArcoPesato<N> a2 = iterator.next();
/*  73 */         if (a1.equals(a2) && !a1.getPeso().equals(a2.getPeso()))
/*  74 */           return false; 
/*     */       } 
/*     */     } 
/*  77 */     Iterator<ArcoPesato<N>> it2 = g2.adiacenti(u);
/*  78 */     while (it2.hasNext()) {
/*  79 */       ArcoPesato<N> a2 = it2.next();
/*  80 */       if (!g1.esisteArco(a2))
/*  80 */         return false; 
/*  81 */       it1 = g1.adiacenti(u);
/*  82 */       while (it1.hasNext()) {
/*  83 */         ArcoPesato<N> a1 = it1.next();
/*  84 */         if (a1.equals(a2) && !a1.getPeso().equals(a2.getPeso()))
/*  85 */           return false; 
/*     */       } 
/*     */     } 
/*  88 */     return true;
/*     */   }
/*     */   
/*     */   public boolean equals(Object o) {
/*  94 */     if (!(o instanceof GrafoOrientatoPesatoAstratto))
/*  94 */       return false; 
/*  95 */     if (o == this)
/*  95 */       return true; 
/*  96 */     GrafoOrientatoPesatoAstratto<N> g = (GrafoOrientatoPesatoAstratto<N>)o;
/*  97 */     if (numNodi() != g.numNodi() || 
/*  98 */       numArchi() != g.numArchi())
/*  98 */       return false; 
/*  99 */     for (N u : this) {
/* 100 */       if (!g.esisteNodo(u))
/* 100 */         return false; 
/* 101 */       if (!equals(this, g, u))
/* 101 */         return false; 
/*     */     } 
/* 103 */     return true;
/*     */   }
/*     */   
/*     */   public abstract void insArco(ArcoPesato<N> paramArcoPesato);
/*     */   
/*     */   public abstract Iterator<ArcoPesato<N>> adiacenti(N paramN);
/*     */   
/*     */   public abstract GrafoPesato<N> factory();
/*     */ }


/* Location:              C:\Users\carme\Desktop\grafi (1).jar!\poo\grafo\GrafoOrientatoPesatoAstratto.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */