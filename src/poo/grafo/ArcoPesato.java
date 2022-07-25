/*    */ package poo.grafo;
/*    */ 
/*    */ public class ArcoPesato<N> extends Arco<N> {
/*    */   private Peso peso;
/*    */   
/*    */   public ArcoPesato(N origine, N destinazione, Peso peso) {
/*  6 */     super(origine, destinazione);
/*  7 */     this.peso = peso;
/*    */   }
/*    */   
/*    */   public ArcoPesato(N origine, N destinazione) {
/* 10 */     super(origine, destinazione);
/*    */   }
/*    */   
/*    */   public Peso getPeso() {
/* 13 */     return this.peso;
/*    */   }
/*    */   
/*    */   public void setPeso(Peso peso) {
/* 16 */     this.peso = peso;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 19 */     return "<" + super.toString() + "," + this.peso + ">";
/*    */   }
/*    */   
/*    */   public static void main(String[] args) {
/* 22 */     ArcoPesato<Integer> ap = 
/* 23 */       new ArcoPesato<>(Integer.valueOf(0), Integer.valueOf(4), new Peso(Double.POSITIVE_INFINITY));
/* 24 */     System.out.println(ap);
/*    */   }
/*    */ }


/* Location:              C:\Users\carme\Desktop\grafi (1).jar!\poo\grafo\ArcoPesato.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */