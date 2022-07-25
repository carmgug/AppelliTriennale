package poo.esempi;

public class CiProvo{
	private int[] a;
	public CiProvo( int[] a ){
		this.a=new int[a.length];
		System.arraycopy(a, 0, this.a, 0, a.length );
	}
	public void ciProvo( int[] b ){
		if( a.length!=b.length )
			throw new IllegalArgumentException();
		for( int i=0; i<a.length; i++ ) a[i] =(a[i]+b[i])%2;
		for(int i=0; i<a.length; i++ ) b[i] = (a[i]==1 && b[i]==1)?1:0;
	}//ciProvo
	public String toString(){
		String s="";
		for( int i=0;i<a.length; i++ ) s+=a[i];
		s+='\n';
		return s;
	}//toString
	public static void main( String[] args ){
		int []a={1,0,1,1};
		int []b={0,1,1,0};
		CiProvo mo=new CiProvo ( a );
		mo.ciProvo(b);
		System.out.println(mo);
		System.out.println(java.util.Arrays.toString( a ) );
		System.out.println(java.util.Arrays.toString( b ) );
	}//main
}//CiProvo