package poo.recursion;

public class Permutazioni {
	private int[] a;
	public Permutazioni( int[] a ) {
		//PRE: gli elementi di a sono distinti
		this.a=java.util.Arrays.copyOf(a,a.length);
	}
	
	private void permuta( int i ) {
		if( i==a.length-1 ) System.out.println(java.util.Arrays.toString(a));
		else {
			for( int j=i; j<a.length; ++j ) {
				int tmp=a[i]; a[i]=a[j]; a[j]=tmp; //scambia a[i] con a[j]
				permuta(i+1);
				tmp=a[i]; a[i]=a[j]; a[j]=tmp; //rimettiamo a posto a[i] e a[j]
			}
		}
	}//permuta
	
	public void risolvi() {
		permuta(0);
	}//risolvi
	
	public static void main( String[] args ) {
		int[] v= {1,2,3,4};
		Permutazioni p=new Permutazioni(v);
		p.risolvi();
	}//main
	
}//Permutazioni
