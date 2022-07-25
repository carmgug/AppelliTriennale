package poo.__APPELLI__.Appello28022017COPIA;

public class Fibonacci {
	static long fibo(int n) {
		if(n<1) throw new IllegalArgumentException();
		
		int[][] A= {{1,1},{1,0}}; 
		A=pow(A,n-1);
		return A[0][0];
	}
	
	
	private static int[][] pow(int[][] A,int n){
		if(n==0) return identit�(A.length);
		if(n==1) return A;
		if(n%2==0) {
			return mul(pow(A,n/2),pow(A,n/2));
		}
		else {
			int[][] B=mul(pow(A,n/2),pow(A,n/2));
			return mul(B,A);
		}
		
		
	}
	
	
	private static int[][] mul(int[][] A,int[][] B){
		int[][] mul=new int[A.length][B.length];
		for(int i=0;i<A.length;++i)
			for(int j=0;j<B[0].length;++j)
				for(int k=0;k<A.length;k++)
					mul[i][j]+=A[i][k]*B[k][j];
		return mul;
		
	}
	
	
	private static int[][] identit�(int grado){
		int[][] matrix=new int[grado][grado];
		for(int j=0;j<grado;++j) {
			matrix[j][j]=1;
		}
		return matrix;
		
	}
	public static void main(String[] arg) {
		System.out.print(fibo(8));
		
	}
}
