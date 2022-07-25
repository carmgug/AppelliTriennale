/*    */ package poo.grafo;
/*    */ 
/*    */ import java.util.Iterator;
/*    */ 
/*    */ public abstract class GrafoNonOrientatoAstratto<N> extends GrafoAstratto<N> implements GrafoNonOrientato<N> {
/*    */   public int grado(N u) {
/*  8 */     int g = 0;
/*  9 */     if (esisteNodo(u)) {
/* 10 */       Iterator<? extends Arco<N>> it = adiacenti(u);
/* 11 */       while (it.hasNext()) {
/* 12 */         it.next();
/* 13 */         g++;
/*    */       } 
/*    */     } 
/* 16 */     return g;
/*    */   }
/*    */ }


/* Location:              C:\Users\carme\Desktop\grafi (1).jar!\poo\grafo\GrafoNonOrientatoAstratto.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */