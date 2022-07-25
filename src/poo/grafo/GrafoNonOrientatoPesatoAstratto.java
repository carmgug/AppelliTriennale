/*     */ package poo.grafo;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ 
/*     */ public abstract class GrafoNonOrientatoPesatoAstratto<N> extends GrafoNonOrientatoAstratto<N> implements GrafoPesato<N> {
/*     */   public void insArco(Arco<N> a, Peso peso) {
/*  11 */     insArco(new ArcoPesato<>(
/*  12 */           a.getOrigine(), 
/*  13 */           a.getDestinazione(), 
/*  14 */           peso));
/*     */   }
/*     */   
/*     */   public void insArco(N u, N v, Peso peso) {
/*  19 */     insArco(new ArcoPesato<>(u, v, peso));
/*     */   }
/*     */   
/*     */   public void insArco(Arco<N> a) {
/*  23 */     insArco(new ArcoPesato<>(a.getOrigine(), a.getDestinazione()));
/*     */   }
/*     */   
/*     */   public void modArco(ArcoPesato<N> a, Peso peso) {
/*  27 */     if (!esisteNodo(a.getOrigine()) || 
/*  28 */       !esisteNodo(a.getDestinazione()))
/*     */       return; 
/*  29 */     Iterator<ArcoPesato<N>> it = adiacenti(a.getOrigine());
/*  30 */     while (it.hasNext()) {
/*  31 */       ArcoPesato<N> ap = it.next();
/*  32 */       if (ap.equals(a)) {
/*  33 */         ap.setPeso(peso);
/*  35 */         ArcoPesato<N> ai = 
/*  36 */           new ArcoPesato<>(a.getDestinazione(), 
/*  37 */             a.getOrigine(), a.getPeso());
/*  38 */         it = adiacenti(ai.getOrigine());
/*  39 */         while (it.hasNext()) {
/*  40 */           ap = it.next();
/*  41 */           if (ap.equals(ai)) {
/*  42 */             ap.setPeso(peso);
/*     */             return;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*  48 */     insArco(new ArcoPesato<>(a.getOrigine(), 
/*  49 */           a.getDestinazione(), peso));
/*     */   }
/*     */   
/*     */   public Peso peso(N u, N v) {
/*  55 */     Iterator<ArcoPesato<N>> iap = adiacenti(u);
/*  56 */     while (iap.hasNext()) {
/*  57 */       ArcoPesato<N> ap = iap.next();
/*  58 */       if (ap.getOrigine().equals(u) && 
/*  59 */         ap.getDestinazione().equals(v))
/*  60 */         return ap.getPeso(); 
/*     */     } 
/*  62 */     return null;
/*     */   }
/*     */   
/*     */   public Grafo<N> copia() {
/*  68 */     GrafoPesato<N> copia = factory();
/*  69 */     for (N u : this)
/*  70 */       copia.insNodo(u); 
/*  72 */     for (N u : this) {
/*  73 */       Iterator<ArcoPesato<N>> it = adiacenti(u);
/*  74 */       while (it.hasNext()) {
/*  75 */         ArcoPesato<N> ac = it.next();
/*  76 */         copia.insArco(new ArcoPesato<>(u, ac.getDestinazione(), new Peso(ac.getPeso())));
/*     */       } 
/*     */     } 
/*  79 */     return copia;
/*     */   }
/*     */   
/*     */   private boolean equals(GrafoNonOrientatoPesatoAstratto<N> g1, GrafoNonOrientatoPesatoAstratto<N> g2, N u) {
/*  84 */     Iterator<ArcoPesato<N>> it1 = g1.adiacenti(u);
/*  85 */     while (it1.hasNext()) {
/*  86 */       ArcoPesato<N> a1 = it1.next();
/*  87 */       if (!g2.esisteArco(a1))
/*  87 */         return false; 
/*  88 */       Iterator<ArcoPesato<N>> iterator = g2.adiacenti(u);
/*  89 */       while (iterator.hasNext()) {
/*  90 */         ArcoPesato<N> a2 = iterator.next();
/*  91 */         if (a1.equals(a2) && !a1.getPeso().equals(a2.getPeso()))
/*  92 */           return false; 
/*     */       } 
/*     */     } 
/*  95 */     Iterator<ArcoPesato<N>> it2 = g2.adiacenti(u);
/*  96 */     while (it2.hasNext()) {
/*  97 */       ArcoPesato<N> a2 = it2.next();
/*  98 */       if (!g1.esisteArco(a2))
/*  98 */         return false; 
/*  99 */       it1 = g1.adiacenti(u);
/* 100 */       while (it1.hasNext()) {
/* 101 */         ArcoPesato<N> a1 = it1.next();
/* 102 */         if (a1.equals(a2) && !a1.getPeso().equals(a2.getPeso()))
/* 103 */           return false; 
/*     */       } 
/*     */     } 
/* 106 */     return true;
/*     */   }
/*     */   
/*     */   public boolean equals(Object o) {
/* 111 */     if (!(o instanceof GrafoNonOrientatoPesatoAstratto))
/* 111 */       return false; 
/* 112 */     if (o == this)
/* 112 */       return true; 
/* 113 */     GrafoNonOrientatoPesatoAstratto<N> g = (GrafoNonOrientatoPesatoAstratto<N>)o;
/* 114 */     if (numNodi() != g.numNodi() || 
/* 115 */       numArchi() != g.numArchi())
/* 115 */       return false; 
/* 116 */     for (N u : this) {
/* 117 */       if (!g.esisteNodo(u))
/* 117 */         return false; 
/* 118 */       if (!equals(this, g, u))
/* 118 */         return false; 
/*     */     } 
/* 120 */     return true;
/*     */   }
/*     */   
/*     */   public abstract void insArco(ArcoPesato<N> paramArcoPesato);
/*     */   
/*     */   public abstract Iterator<ArcoPesato<N>> adiacenti(N paramN);
/*     */   
/*     */   public abstract GrafoPesato<N> factory();
/*     */ }


/* Location:              C:\Users\carme\Desktop\grafi (1).jar!\poo\grafo\GrafoNonOrientatoPesatoAstratto.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */