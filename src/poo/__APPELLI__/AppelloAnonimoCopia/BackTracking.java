package poo.__APPELLI__.AppelloAnonimoCopia;

import java.util.Arrays;

public class BackTracking {
	private int[] V;
	private int S;
	private Integer[] Soluzione;
	
	//senza duplicati significa che l'array non debba avere
	//elementi uguali tra di loro;
	
	public BackTracking(int[] V,int S) {
		this.V=new int[V.length];
		int k=0;
		//per formare sottoinsiemi distinti abbiamo bisogno di elementi non doppi nell'array;
		for(int i=0;i<V.length;i++) {
			boolean ok=true;//supponiamo l'elemento i non si duplicato se si solo l'ultima occorrenza verr� aggiunta;
			for(int j=i+1;j<V.length;j++) {
				if(V[j]==V[i]) {ok=false; break;}
			}
			if(ok) {this.V[k]=V[i];k++;}
		}
		//array di elementi distinti
		this.V=Arrays.copyOf(this.V, k+1);
		
		
		Soluzione=new Integer[this.V.length];
		this.S=S;
	}
	
	
	
	public void risolvi() {
		System.out.println("Inziato");
		tentativo(0,0);
	}

	private boolean assegnabile(int ps) {
		int c=0;
		for(Integer s:Soluzione) {
			if(s==null) break;
			c+=s;
		}
		
		if( (c+V[ps] )>S) return false;
		return true;
	}
	
	
	private void tentativo(int ps,int k) {
		
		for(int i=ps;i<V.length;i++) {
			if(assegnabile(i)) {
				Soluzione[k]=V[i];
				if(EsisteSoluzione()) ScriviSoluzione();
				else tentativo(i+1,k+1);
				Soluzione[k]=null;
			}
		}
	}
	
	private void ScriviSoluzione() {
		StringBuilder sb=new StringBuilder(20);
		sb.append("[");
		for(Integer x:Soluzione) {
			if(x==null) break;
			sb.append(x+", ");
		}
		sb.setLength(sb.length()-2);
		sb.append("]");
		System.out.println(sb.toString());
	}
	
	private boolean EsisteSoluzione() {
		int c=0;
		for(Integer x:Soluzione) {
			if(x==null) break;
			c+=x;
		}
		if(c==S) return true;
		return false;
	}
	
	public static void main(String[] args) {
		int[] V= {1,2,3,3,3,6,7,8,9,5,4,32,34,52,4};
		int S=3;
		BackTracking bt=new BackTracking(V,S);
		bt.risolvi();
		
	}
	
}
