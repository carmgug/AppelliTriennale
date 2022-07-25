/*    */ package poo.grafo;
/*    */ 
/*    */ import java.util.Iterator;
/*    */ 
/*    */ public abstract class GrafoOrientatoAstratto<N> extends GrafoAstratto<N> implements GrafoOrientato<N> {
/*    */   public int gradoEntrata(N u) {
/*  8 */     int gE = 0;
/*  9 */     if (esisteNodo(u))
/* 10 */       for (N v : this) {
/* 11 */         Iterator<? extends Arco<N>> it = adiacenti(v);
/* 12 */         while (it.hasNext()) {
/* 13 */           Arco<N> a = it.next();
/* 14 */           if (a.getDestinazione().equals(u)) {
/* 15 */             gE++;
/*    */             break;
/*    */           } 
/*    */         } 
/*    */       }  
/* 20 */     return gE;
/*    */   }
/*    */   
/*    */   public int gradoUscita(N u) {
/* 23 */     int gU = 0;
/* 24 */     if (esisteNodo(u)) {
/* 25 */       Iterator<? extends Arco<N>> it = adiacenti(u);
/* 26 */       while (it.hasNext()) {
/* 27 */         it.next();
/* 28 */         gU++;
/*    */       } 
/*    */     } 
/* 31 */     return gU;
/*    */   }
/*    */ }


/* Location:              C:\Users\carme\Desktop\grafi (1).jar!\poo\grafo\GrafoOrientatoAstratto.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */