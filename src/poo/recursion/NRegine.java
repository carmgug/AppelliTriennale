package poo.recursion;

public class NRegine {
	//punti di scelta: tutte le righe da 0 a n-1
	//scelte: tutte le colonne da 0 ad n-1
	private boolean[][] board;
	private int n, nrSol=0;
	
	public NRegine( int n ) {
		if( n<3 ) throw new IllegalArgumentException();
		board=new boolean[n][n]; //inizializzata a tutti false
		this.n=n;
	}
	
	private boolean assegnabile( int r, int c ) {
		//è assegnabile una regina sulla colonna c della riga r?
		//verificare i vincoli di essere sotto attacco
		//verifica attacco a NORD
		for( int i=r-1; i>=0; --i )
			if( board[i][c] ) return false;
		//verifica attacco a NORD-OVEST
		for( int i=r-1,j=c-1; i>=0 && j>=0; --i,--j )
			if( board[i][j] ) return false;
		//verifica attacco a NORD-EST
		for( int i=r-1,j=c+1; i>=0 && j<=n-1; --i,++j )
			if( board[i][j] ) return false;
		return true;
	}//assegnabile
	
	private void assegna( int r, int c ) {
		board[r][c]=true;
	}//assegna
	
	private void deassegna( int r, int c ) {
		board[r][c]=false;
	}//deassegna
	
	private void scriviSoluzione() {
		nrSol++;
		System.out.print(nrSol+": ");
		for( int i=0; i<n; ++i ) {
			for( int j=0; j<n; ++j )
				if( board[i][j] ) {
					System.out.print("<"+i+","+j+">");
					break; //interrompe il ciclo di for interno
				}		
		}
		System.out.println();
	}//scriviSoluzione
	
	private void collocaRegina( int row ) {
		for( int col=0; col<n; ++col ) {
			if( assegnabile(row,col) ) {
				assegna(row,col);
				if( row==n-1 ) scriviSoluzione();
				else collocaRegina( row+1 );
				//in questo punto ritorna la ricorsione e si applica il backtracking
				deassegna(row,col);
			}
		}
	}//collocaRegina
	
	public void risolvi() {
		collocaRegina(0);
	}//risolvi
	
	public static void main( String[] args ) {
		NRegine nr=new NRegine(8);
		nr.risolvi();
	}//main
	
}//NRegine
