package poo.__APPELLI__.Appello11092019;

import java.util.Collection;
import java.util.LinkedList;

public class Sudoku {
	
	int[][] Sudoku;
	int N_Sol;
	
	
	
	
	public Sudoku() {
		int[][] Sudoku=new int[9][9];
		N_Sol=0;
	}
	
	public Sudoku(int[][][] imp) {
		this();
		for(int i=0;i<imp.length;++i)
			for(int j=0;j<imp[0].length;j++) {
				int[] temp=imp[i][j];
				
				int[] ps=new int[2];
				ps[0]=temp[0];ps[1]=temp[1];
				int s=temp[2];
				colloca(ps,s);
			}
		
	}
	
	public void colloca(int[] ps,int s) {
		if(s<1 || s>9) throw new IllegalArgumentException();
		if(assegnabile(ps,s))
			assegna(ps,s);
		else
			throw new IllegalStateException();
		
	}
	
	private boolean assegnabile(int[] ps,int s) {
		if(Sudoku[ps[0]][ps[1]]==0) return false;
		
		boolean Check_1=riga(ps[0],s);
		boolean Check_2=colonna(ps[1],s);
		boolean Check_3=SottoMatrice(ps[0],ps[1],s);
		
		
		return Check_1 && Check_2 && Check_3;
	}
	
	
	private boolean riga(int r,int s) {
		
		for(int j=0;j<Sudoku[0].length;++j)
			if(Sudoku[r][j]==s) return false;
		
		return true;
	}
	
	private boolean colonna(int c,int s) {
		
		for(int i=0;i<Sudoku.length;++i)
			if(Sudoku[i][c]==s) return false;
		return true;
	}
	
	private boolean SottoMatrice(int r,int c,int s) {
		
		for(int i=r-(r%3);i<(r%3)+3;++i)
			for(int j=c-(c%3);j<(c%3)+3;++j)
				if(Sudoku[i][j]==s) return false;
		
		return true;
	}
	
	private void assegna(int[] ps,int s) {
		Sudoku[ps[0]][ps[1]]=s;
	}
	
	private void deassegna(int[] ps,int s) {
		Sudoku[ps[0]][ps[1]]=0;
	}
	
	private Collection<int[]> PuntiScelte(){
		LinkedList<int[]> l=new LinkedList<>();
		for(int i=0;i<Sudoku.length;++i)
			for(int j=0;j<Sudoku[0].length;j++)
				if(Sudoku[i][j]==0) {
					int[] punto= {i,j};
					l.add(punto);
				}
		return l;
	}
	
	private Collection<Integer> SceltePossibili(int[] ps){
		LinkedList<Integer> l=new LinkedList<Integer>();
		
		for(int i=1;i<=9;++i) {
			if(assegnabile(ps,i))
				l.add(i);
		}
		
		return l;
		
	}
	
	public void risolvi() {
		LinkedList<int[]> ListaPS= (LinkedList<int[]>) PuntiScelte();
		
		tentativo(ListaPS,ListaPS.get(0),0);
	}
	
	private void tentativo(LinkedList<int[]> ListaPS,int[] ps,int index) {
		LinkedList<Integer> Scelte=(LinkedList<Integer>) SceltePossibili(ps);
		for(Integer s:Scelte)
			if(assegnabile(ps,s)) {
				assegna(ps,s);
				if(index==ListaPS.size()-1) {ScriviSoluzione();}
				else tentativo(ListaPS,ListaPS.get(index+1),index+1);
				deassegna(ps,s);
			}
	}
	
	private void ScriviSoluzione() {
		N_Sol++;
		
		StringBuilder sb=new StringBuilder(83);
		
		for(int i=0;i<9;++i) {
			sb.append("[ ");
			for(int j=0;j<9;j++)
				sb.append(Sudoku[i][j]+" ");
			sb.append("]");
		}
		System.out.print(sb.toString());
	}
}
