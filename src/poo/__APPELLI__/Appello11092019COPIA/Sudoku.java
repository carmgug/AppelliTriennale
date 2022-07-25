package poo.__APPELLI__.Appello11092019COPIA;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import poo.backtracking.Backtracking;


public class Sudoku extends Backtracking< Integer[] , Integer> {

	
	int[][] sudoku;
	
	
	
	
	@Override
	protected boolean assegnabile(Integer[] p, Integer s) {
		int i=p[0],j=p[1];
		boolean Check_1=CheckRiga(i,s);
		boolean Check_2=CheckColonna(j,s);
		boolean Check_3=CheckSottoMatrice(i,j,s);
		return Check_1 && Check_2 && Check_3;
	}
	

	private boolean CheckRiga(int i,Integer s) {
		
		for(int j=0;j<9;j++) {
			if(sudoku[i][j]==s) return false;
		}
		return true;
	}
	
	private boolean CheckColonna(int j,Integer s) {
		
		for(int i=0;i<9;i++) {
			if(sudoku[i][j]==s) return false;
		}
		return true;
	}
	
	private boolean CheckSottoMatrice(int r,int c,Integer s) {
		int sup_r=(r-(r%3))+3;
		int sup_c=(c-(c%3))+3;
		for(int i=r-(r%3);i<sup_r;i++) {
			for(int j=c-(c%3);j<sup_c;j++){
				if(sudoku[i][j]==s) return false;
			}
		}
		return true;
		
	}
	
	
	@Override
	protected void assegna(Integer[] ps, Integer s) {
		sudoku[ps[0]][ps[1]]=s;
		
	}

	@Override
	protected void deassegna(Integer[] ps, Integer s) {
		sudoku[ps[0]][ps[1]]=0;

		
	}

	@Override
	protected void scriviSoluzione(Integer[] p) {
		StringBuilder sb=new StringBuilder(20);
		for(int i=0;i<9;i++) {
			sb.append("[");
			for(int j=0;j<9;j++) {
				sb.append(sudoku[i][j]);
				if(j<8) sb.append(", "); 
			}
			sb.append("]\n");
		}
		System.out.println(sb.toString());
		
	}

	@Override
	protected List<Integer[]> puntiDiScelta() {
		List<Integer[]> l=new LinkedList<Integer[]>();
		for(int i=0;i<9;i++) {
			for(int j=0;j<9;j++) {
				if(sudoku[i][j]==0) {
					Integer[] ps= {i,j};
					l.add(ps);
				}
			}
		}
		return l;
	}

	@Override
	protected Collection<Integer> scelte(Integer[] p) {
		List<Integer> scelte=new LinkedList<Integer>();
		
		for(int i=1;i<=9;i++) {
			if(assegnabile(p,i)) {
				scelte.add(i);
			}
		}
		return scelte;
	}

	@Override
	protected void risolvi() {
		List<Integer[]> l=puntiDiScelta();
		tentativo(l,l.get(0));
		
	}
	
	
	

}
