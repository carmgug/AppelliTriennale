package poo.__APPELLI__.Appello28022017;

public class Fibonacci {

	public static long fibo(int n) {
		if(n<1) throw new IllegalArgumentException();
		if(n==1 || n==2) return 1;
		
		int[][] A= {{1,1},{1,0}};
		
		A=MatrixPow(A,n-1);
		return A[0][0];
	}
	
	private static int[][] MatrixPow(int[][] A,int n){
		if(n==1) return A;
		if(n==0) return unitaria(2);
		//copia Matrice
		int[][] B=new int[A.length][A[0].length];
		for(int i=0;i<A.length;i++) {
			System.arraycopy(A[i], 0, B[i], 0, A[0].length);
		}
		if(n%2==0) return MatrixMul(MatrixPow(B,n/2),MatrixPow(B,n/2));
		else return MatrixMul(B , MatrixMul(MatrixPow(B,n/2),MatrixPow(B,n/2)) );
	}

	private static int[][] MatrixMul(int[][] A, int[][] B) {
		int[][] res=new int[A.length][B[0].length];
		for(int i=0;i<A.length;i++)
			for(int j=0;j<B[0].length;j++)
				for(int k=0;k<A.length;i++) {
					res[i][j]+=A[i][k]*B[k][j];
				}
		
		return res;
	}

	private static int[][] unitaria(int n){
		int[][] A= new int[n][n];
		for(int i=0;i<n;i++) {
			A[i][i]=1;
		}
		return A;
	}
	
	
	
	
	
}
