package poo.__APPELLI__.Appello23072019_12CFU;

public class SommaDiSottoInsiemiv2 {
	
	int[] a;
	int m;
	Integer[] b;
	public SommaDiSottoInsiemiv2(int[] a,int m){
		for(int i=0;i<a.length;i++)
			for(int j=i+1;j<a.length;j++)
				if(a[i]==a[j]) throw new IllegalArgumentException();
		this.a=a;
		this.m=m;
		b=new Integer[a.length];
		for(int i=0;i<a.length;i++) {
			b[i]=null;
		}
	}
	
	
	
	
	
	
	
	
	
	
	private void assegna(int ps,int i) {
		b[i]=a[ps];
	}
	
	private void deassegna(int ps,int i) {
		b[i]=null;
	}
	
	private boolean assegnabile(int ps) {
		int c=0;
		for(Integer x:b) {
			if(x==null) break;//nessun valore
			c+=x;
		}
		if(c+a[ps]>m) return false;
		return true;
	}
	
	private boolean esisteSoluzione() {
		int c=0;
		for(Integer x:b) {
			if(x==null) break;
			c+=x;
		}
		if(c==m) return true;
		return false;
	}
	
	public void risolvi() {
		tentativo(0,0);
	}
	
	private void tentativo(int ps,int k) {
		if(ps==a.length) return;
		for(int i=ps;i<a.length;i++) {
			if(assegnabile(i)) {
				assegna(i,k);
				if(esisteSoluzione()) ScriviSoluzione();
				else tentativo(i+1,k+1);
				deassegna(i,k);
			}
		}
	}
	
	private void ScriviSoluzione() {
		StringBuilder sb=new StringBuilder(200);
		sb.append("[");
		for(Integer x:b) {
			if(x==null )break;
			sb.append(x+", ");
		}
		sb.setLength(sb.length()-2);//", "
		sb.append("]");
		System.out.println(sb.toString());
	}
	public static void main(String [] args) {
		int[] a= {1,2,3,4,5,6,7,8,10,12,13};
		int m=23;
		SommaDiSottoInsiemiv2 S=new SommaDiSottoInsiemiv2(a,m);
		S.risolvi();
	}
	
	
	

}
