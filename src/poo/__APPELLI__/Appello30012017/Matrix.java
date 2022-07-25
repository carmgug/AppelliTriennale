package poo.__APPELLI__.Appello30012017;

public class Matrix {

	
	
	public static void sviluppa(char[][] sistema,char[] colonna) {
		if(sistema.length!=13 || colonna.length!=sistema.length) throw new IllegalArgumentException();
		for(int i=0;i<sistema.length;i++) {
			if(sistema[i].length>3) throw new IllegalArgumentException();
		}
		sviluppa(sistema,0,colonna);
	}
	
	private static void sviluppa(char[][] sistema,int i,char[] colonna) {
		
		for(int j=0;j<sistema[i].length;j++) {
			colonna[i]=sistema[i][j];
			if(i==sistema.length-1) {ScriviSoluzione(colonna);}
			else sviluppa(sistema,i+1,colonna);
			//non vi � bisogno del diassegnamento.
		}
		
	}
	
	private static void ScriviSoluzione(char[] colonna) {
		StringBuilder sb=new StringBuilder(13);
		for(int i=0;i<colonna.length;i++) {
			sb.append(colonna[i]);
		}
		System.out.println(sb.toString());
	}
	
	
	public static void main(String[] args) {
		
		
		char[][] sistema= {{'1'},{'2','1'},{'X'},{'1','2','X'},{'X','2'},
				{'2'},{'1'},{'1','2','X'},{'X'},{'2'},{'1','X'},{'2'},{'1'}};
		char[] colonna=new char[13];
		
		sviluppa(sistema,colonna);
		
		
		
		
		
		
		
		
	}
}
