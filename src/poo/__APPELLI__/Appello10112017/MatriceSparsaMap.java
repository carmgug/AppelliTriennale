package poo.__APPELLI__.Appello10112017;

import java.util.HashMap;

public class MatriceSparsaMap extends MatriceSparsaAstratta {

	
	private HashMap<Integer,Integer> matrice;
	private int n;
	
	private MatriceSparsaMap(int n) {
		if(n<=0) throw new IllegalArgumentException();
		this.n=n;
		matrice=new HashMap<>();
	}
	
	
	
	@Override
	public int getN() {
		return n;
	}

	@Override
	public void clear() {
		matrice=new HashMap<>();
		
	}

	@Override
	public int get(int i, int j) {
		if(i<0 || i>(n-1)*n+(n-1) || j<0 || j>(n-1)*n+(n-1) ) throw new IllegalArgumentException();
		Integer valore=matrice.get(i*n+j);
		if(valore==null) return 0;
		else return valore;
	}

	@Override
	public void set(int i, int j, int v) {
		if(i<0 || i>(n-1)*n+(n-1) || j<0 || j>(n-1)*n+(n-1) || v==0 ) throw new IllegalArgumentException();
		if(matrice.containsKey(i*n+j)) {
			matrice.remove(i*n+j);
			matrice.put(i*n+j, v);
		}
		
		
		
	}

	@Override
	public MatriceSparsa crea() {
		return new MatriceSparsaMap(n);
	}
	
	
	
	
	
	

}
