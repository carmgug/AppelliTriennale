/*     */ package poo.grafo;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedList;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ 
/*     */ public final class Grafi {
/*     */   public static <N> void visitaInAmpiezza(Grafo<N> g, N u, LinkedList<N> lista) {
/*   8 */     if (g == null || !g.esisteNodo(u))
/*   9 */       throw new IllegalArgumentException(); 
/*  10 */     Set<N> visitato = new HashSet<>();
/*  11 */     visitaInAmpiezza(g, u, visitato, lista);
/*     */   }
/*     */   
/*     */   private static <N> void visitaInAmpiezza(Grafo<N> g, N u, Set<N> visitato, LinkedList<N> lista) {
/*  16 */     LinkedList<N> coda = new LinkedList<>();
/*  17 */     coda.addLast(u);
/*  18 */     lista.addLast(u);
/*  19 */     visitato.add(u);
/*  20 */     while (!coda.isEmpty()) {
/*  21 */       N x = coda.removeFirst();
/*  22 */       Iterator<? extends Arco<N>> it = g.adiacenti(x);
/*  23 */       while (it.hasNext()) {
/*  24 */         N nodoAdiacente = ((Arco<N>)it.next()).getDestinazione();
/*  25 */         if (!visitato.contains(nodoAdiacente)) {
/*  26 */           coda.addLast(nodoAdiacente);
/*  27 */           lista.addLast(nodoAdiacente);
/*  28 */           visitato.add(nodoAdiacente);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public static <N> void visitaInProfondita(Grafo<N> g, N u, LinkedList<N> lista) {
/*  35 */     if (g == null || !g.esisteNodo(u))
/*  36 */       throw new IllegalArgumentException(); 
/*  37 */     Set<N> visitato = new HashSet<>();
/*  38 */     visitaInProfondita(g, u, visitato, lista);
/*     */   }
/*     */   
/*     */   private static <N> void visitaInProfondita(Grafo<N> g, N u, Set<N> visitato, LinkedList<N> lista) {
/*  43 */     lista.addLast(u);
/*  44 */     visitato.add(u);
/*  45 */     Iterator<? extends Arco<N>> it = g.adiacenti(u);
/*  46 */     while (it.hasNext()) {
/*  47 */       N nodoAdiacente = ((Arco<N>)it.next()).getDestinazione();
/*  48 */       if (!visitato.contains(nodoAdiacente))
/*  49 */         visitaInProfondita(g, nodoAdiacente, visitato, lista); 
/*     */     } 
/*     */   }
/*     */   
/*     */   public static <N> Grafo<N> raggiungibilita(Grafo<N> g) {
/*  54 */     Grafo<N> grafoR = g.copia();
/*  58 */     for (int k = 2; k < g.numNodi(); k++) {
/*  59 */       for (N i : g) {
/*  60 */         for (N j : g) {
/*  61 */           for (N m : g) {
/*  62 */             if (grafoR.esisteArco(i, m) && g.esisteArco(m, j))
/*  63 */               grafoR.insArco(i, j); 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*  65 */     return grafoR;
/*     */   }
/*     */   
/*     */   public static <N> int componentiConnesse(Grafo<N> g) {
/*  69 */     Set<N> visitato = new HashSet<>();
/*  70 */     LinkedList<N> fittizia = new LinkedList<>();
/*  71 */     int ncc = 0;
/*  72 */     for (N u : g) {
/*  73 */       if (!visitato.contains(u)) {
/*  74 */         ncc++;
/*  75 */         visitaInAmpiezza(g, u, visitato, fittizia);
/*     */       } 
/*     */     } 
/*  78 */     return ncc;
/*     */   }
/*     */   
/*     */   public static <N> boolean aciclico(GrafoOrientato<N> g) {
/*  91 */     Set<N> rimossi = new HashSet<>();
/*  92 */     Map<N, Integer> gradoEntrata = new HashMap<>();
/*  93 */     LinkedList<N> daRimuovere = new LinkedList<>();
/*  94 */     for (N u : g) {
/*  95 */       int gE = g.gradoEntrata(u);
/*  96 */       gradoEntrata.put(u, Integer.valueOf(gE));
/*  97 */       if (gE == 0)
/*  97 */         daRimuovere.addLast(u); 
/*     */     } 
/* 100 */     while (!daRimuovere.isEmpty()) {
/* 101 */       N nodo = daRimuovere.removeFirst();
/* 102 */       rimossi.add(nodo);
/* 103 */       Iterator<? extends Arco<N>> it = g.adiacenti(nodo);
/* 105 */       while (it.hasNext()) {
/* 106 */         N destinazione = ((Arco<N>)it.next()).getDestinazione();
/* 107 */         gradoEntrata.put(destinazione, Integer.valueOf(((Integer)gradoEntrata.get(destinazione)).intValue() - 1));
/* 108 */         if (((Integer)gradoEntrata.get(destinazione)).intValue() == 0)
/* 109 */           daRimuovere.addLast(destinazione); 
/*     */       } 
/*     */     } 
/* 113 */     for (N u : g) {
/* 114 */       if (!rimossi.contains(u))
/* 114 */         return false; 
/*     */     } 
/* 115 */     return true;
/*     */   }
/*     */   
/*     */   public static void main(String[] args) {
/* 119 */     GrafoOrientato<Integer> g = new GrafoOrientatoImpl<>();
/* 122 */     g.insNodo(Integer.valueOf(1));
/* 123 */     g.insNodo(Integer.valueOf(2));
/* 124 */     g.insNodo(Integer.valueOf(3));
/* 125 */     g.insNodo(Integer.valueOf(4));
/* 126 */     g.insNodo(Integer.valueOf(5));
/* 127 */     g.insNodo(Integer.valueOf(6));
/* 128 */     g.insNodo(Integer.valueOf(7));
/* 129 */     g.insArco(Integer.valueOf(1), Integer.valueOf(2));
/* 130 */     g.insArco(Integer.valueOf(1), Integer.valueOf(3));
/* 131 */     g.insArco(Integer.valueOf(2), Integer.valueOf(4));
/* 132 */     g.insArco(Integer.valueOf(3), Integer.valueOf(5));
/* 133 */     g.insArco(Integer.valueOf(4), Integer.valueOf(5));
/* 134 */     g.insArco(Integer.valueOf(5), Integer.valueOf(2));
/* 135 */     g.insArco(Integer.valueOf(5), Integer.valueOf(3));
/* 136 */     g.insArco(Integer.valueOf(4), Integer.valueOf(6));
/* 137 */     g.insArco(Integer.valueOf(7), Integer.valueOf(6));
/* 139 */     GrafoOrientato<Integer> ge = new GrafoOrientatoImpl<>();
/* 140 */     ge.insNodo(Integer.valueOf(5));
/* 141 */     ge.insNodo(Integer.valueOf(6));
/* 142 */     ge.insNodo(Integer.valueOf(7));
/* 143 */     ge.insNodo(Integer.valueOf(1));
/* 144 */     ge.insNodo(Integer.valueOf(2));
/* 145 */     ge.insNodo(Integer.valueOf(3));
/* 146 */     ge.insNodo(Integer.valueOf(4));
/* 147 */     ge.insArco(Integer.valueOf(1), Integer.valueOf(3));
/* 148 */     ge.insArco(Integer.valueOf(1), Integer.valueOf(2));
/* 149 */     ge.insArco(Integer.valueOf(4), Integer.valueOf(6));
/* 150 */     ge.insArco(Integer.valueOf(2), Integer.valueOf(4));
/* 151 */     ge.insArco(Integer.valueOf(3), Integer.valueOf(5));
/* 152 */     ge.insArco(Integer.valueOf(4), Integer.valueOf(5));
/* 153 */     ge.insArco(Integer.valueOf(5), Integer.valueOf(3));
/* 154 */     ge.insArco(Integer.valueOf(5), Integer.valueOf(2));
/* 155 */     ge.insArco(Integer.valueOf(7), Integer.valueOf(6));
/* 156 */     System.out.println("g==ge ? " + g.equals(ge));
/* 157 */     System.out.println("g.hashCode()=" + g.hashCode() + " ge.hashCode()=" + ge.hashCode());
/* 159 */     System.out.println(g);
/* 160 */     LinkedList<Integer> lis = new LinkedList<>();
/* 161 */     visitaInAmpiezza(g, Integer.valueOf(1), lis);
/* 162 */     System.out.println("Visita in ampiezza: " + lis);
/* 163 */     lis.clear();
/* 164 */     visitaInProfondita(g, Integer.valueOf(1), lis);
/* 165 */     System.out.println("Visita in profondita': " + lis);
/* 166 */     System.out.println();
/* 167 */     System.out.println("Grafo aciclico ? " + aciclico(g));
/* 168 */     Grafo<Integer> gR = raggiungibilita(g);
/* 169 */     System.out.println("Grafo di raggiungibilita'");
/* 170 */     System.out.println(gR);
/* 171 */     System.out.println("Rimuovo l'arco <2,4>");
/* 172 */     g.rimuoviArco(Integer.valueOf(2), Integer.valueOf(4));
/* 173 */     System.out.println(g);
/* 174 */     gR.clear();
/* 175 */     gR = raggiungibilita(g);
/* 176 */     System.out.println("Nuovo grafo di raggiungibilita");
/* 177 */     System.out.println(gR);
/* 178 */     System.out.println("Numero componenti connesse: " + componentiConnesse(g));
/* 180 */     System.out.println();
/* 181 */     g.insArco(Integer.valueOf(2), Integer.valueOf(4));
/* 182 */     System.out.println(g);
/* 183 */     System.out.println("Test rimuoviNodo 5");
/* 184 */     g.rimuoviNodo(Integer.valueOf(5));
/* 185 */     System.out.println(g);
/* 186 */     System.out.println("Grafo ricomposto");
/* 187 */     g.insNodo(Integer.valueOf(5));
/* 187 */     g.insArco(Integer.valueOf(5), Integer.valueOf(2));
/* 187 */     g.insArco(Integer.valueOf(5), Integer.valueOf(3));
/* 188 */     g.insArco(Integer.valueOf(4), Integer.valueOf(5));
/* 188 */     g.insArco(Integer.valueOf(3), Integer.valueOf(5));
/* 189 */     System.out.println(g);
/* 190 */     System.out.println("Test rimozione nodo 5 con iteratore");
/* 191 */     Iterator<Integer> it = g.iterator();
/* 192 */     while (it.hasNext()) {
/* 193 */       int x = ((Integer)it.next()).intValue();
/* 194 */       if (x == 5) {
/* 195 */         it.remove();
/*     */         break;
/*     */       } 
/*     */     } 
/* 199 */     System.out.println("Grafo dopo la rimozione del nodo 5");
/* 200 */     System.out.println(g);
/*     */   }
/*     */ }


/* Location:              C:\Users\carme\Desktop\grafi (1).jar!\poo\grafo\Grafi.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */