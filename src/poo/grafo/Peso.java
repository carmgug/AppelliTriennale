/*    */ package poo.grafo;
/*    */ 
/*    */ import poo.util.Mat;
/*    */ 
/*    */ public class Peso implements Comparable<Peso> {

			private static double InfinityD;//non so a cosa serva

/*    */   public static final double INFINITO = InfinityD;
/*    */   
/*    */   private double val;
/*    */   
/*    */   public Peso() {
/*  7 */     this(0.0D);
/*    */   }
/*    */   
/*    */   public Peso(double val) {
/*  8 */     this.val = val;
/*    */   }
/*    */   
/*    */   public Peso(Peso p) {
/*  9 */     this.val = p.val;
/*    */   }
/*    */   
/*    */   public double val() {
/* 10 */     return this.val;
/*    */   }
/*    */   
/*    */   public void setVal(double val) {
/* 11 */     this.val = val;
/*    */   }
/*    */   
/*    */   public Peso piu(Peso p) {
/* 13 */     Peso somma = new Peso();
/* 14 */     if (this.val == Double.POSITIVE_INFINITY || p.val() == Double.POSITIVE_INFINITY) {
/* 15 */       somma.setVal(Double.POSITIVE_INFINITY);
/*    */     } else {
/* 18 */       somma.setVal(this.val + p.val());
/*    */     } 
/* 20 */     return somma;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 23 */     String s = "";
/* 24 */     if (this.val == Double.POSITIVE_INFINITY) {
/* 24 */       s = String.valueOf(s) + "oo";
/*    */     } else {
/* 25 */       s = String.valueOf(s) + this.val;
/*    */     } 
/* 26 */     return s;
/*    */   }
/*    */   
/*    */   public boolean equals(Object o) {
/* 29 */     if (!(o instanceof Peso))
/* 29 */       return false; 
/* 30 */     if (o == this)
/* 30 */       return true; 
/* 31 */     Peso p = (Peso)o;
/* 32 */     return Mat.sufficientementeProssimi(this.val, p.val);
/*    */   }
/*    */   
/*    */   public int hashCode() {
/* 34 */     return (new Double(this.val)).hashCode();
/*    */   }
/*    */   
/*    */   public int compareTo(Peso p) {
/* 36 */     if (this.val == p.val())
/* 36 */       return 0; 
/* 37 */     if (this.val != Double.POSITIVE_INFINITY && p.val() == Double.POSITIVE_INFINITY)
/* 37 */       return -1; 
/* 38 */     if (this.val == Double.POSITIVE_INFINITY && p.val() != Double.POSITIVE_INFINITY)
/* 38 */       return 1; 
/* 39 */     if (Mat.sufficientementeProssimi(this.val, p.val))
/* 39 */       return 0; 
/* 40 */     if (this.val < p.val)
/* 40 */       return -1; 
/* 41 */     return 1;
/*    */   }
/*    */   
/*    */   public static void main(String[] args) {
/* 45 */     Peso p1 = new Peso();
/* 46 */     Peso p2 = new Peso(2.0D);
/* 47 */     Peso p3 = new Peso(Double.POSITIVE_INFINITY);
/* 48 */     System.out.println(p1.piu(p2));
/* 49 */     System.out.println(p1.piu(p2).compareTo(p3));
/*    */   }
/*    */ }


/* Location:              C:\Users\carme\Desktop\grafi (1).jar!\poo\grafo\Peso.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */