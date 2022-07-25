/*    */ package poo.grafo;
/*    */ 
/*    */ public class Arco<N> {
/*    */   private N origine;
/*    */   
/*    */   private N destinazione;
/*    */   
/*    */   public Arco(N origine, N destinazione) {
/*  6 */     this.origine = origine;
/*  7 */     this.destinazione = destinazione;
/*    */   }
/*    */   
/*    */   public N getOrigine() {
/* 10 */     return this.origine;
/*    */   }
/*    */   
/*    */   public N getDestinazione() {
/* 13 */     return this.destinazione;
/*    */   }
/*    */   
/*    */   public boolean equals(Object o) {
/* 17 */     if (!(o instanceof Arco))
/* 17 */       return false; 
/* 18 */     if (o == this)
/* 18 */       return true; 
/* 19 */     Arco<N> a = (Arco<N>)o;
/* 20 */     return (this.origine.equals(a.getOrigine()) && 
/* 21 */       this.destinazione.equals(a.getDestinazione()));
/*    */   }
/*    */   
/*    */   public int hashCode() {
/* 24 */     int numero_primo = 811;
/* 25 */     return this.origine.hashCode() * 811 + this.destinazione.hashCode();
/*    */   }
/*    */   
/*    */   public String toString() {
/* 28 */     return "<" + this.origine + "," + this.destinazione + ">";
/*    */   }
/*    */ }


/* Location:              C:\Users\carme\Desktop\grafi (1).jar!\poo\grafo\Arco.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */