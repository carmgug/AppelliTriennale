package poo.__APPELLI__.AppelloAnonimo28062018;

public class Fibonacci {
	
	
	public static long fibo(int n) {
		
		if (n<1) throw new IllegalArgumentException();
		long[][] matrice= {{1,1},{1,0}};
		matrice=pow(matrice,n-1);
		return matrice[0][0];
	}
	private static long[][] pow(long[][]a,int n){
		if(n==0) return unitary();
		if(n==1) return a;
		long[][] copy=a;
		
		
		if(n%2==0) {
			return mul(pow(copy,n/2),pow(copy,n/2));
		}
		else {			//pirmo membro 					//secondo membro
			return mul( mul(pow(copy,n/2), pow(copy,n/2)) , copy);
		}
	}
	
	private static long[][] mul(long[][] a,long[][] b){
		
		long[][] res=new long[a.length][b[0].length];
		for(int i=0;i<a.length;++i) 
			for(int j=0;j<b[0].length;++j) 
				for(int k=0;k<b.length;k++) 
					res[i][j]+=a[i][k]*b[k][j];
			
		return res;
	}
	
	private static long[][] unitary(){
		
		long[][] uni=new long[2][2];
		for(int i=0;i<uni.length;i++) {
			uni[i][i]=1;
		}
		
		return uni;
	}
	public static void main(String[] args) {
		int n=90;
		System.out.println(fibo(50));
		
		
	}
}
