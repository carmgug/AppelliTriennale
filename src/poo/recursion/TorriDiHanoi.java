package poo.recursion;

import poo.util.Stack;
import poo.util.StackConcatenato;

public class TorriDiHanoi {
	enum Pin{ SX, CL, DX }
	private int n;
	public TorriDiHanoi( int n ) {
		if( n<=1 ) throw new IllegalArgumentException();
		this.n=n;
	}
	
	private void sposta1disco( Pin da, Pin a ) {
		System.out.println("Sposta 1 disco da "+da+" a "+a);
	}//sposta1disco
	
	private void muovi( int n, Pin src, Pin aux, Pin dst ) {
		if( n==1 ) sposta1disco( src, dst );
		else {
			muovi( n-1, src,dst,aux );
			sposta1disco(src,dst);
			muovi( n-1, aux,src,dst );
		}
	}//muovi
	
	private void muoviIte( int n, Pin src, Pin aux, Pin dst ) {
		//l'idea e' di fare la mimica dello stack delle aree dati del metodo ricorsivo
		class AreaDati{
			int n;
			Pin src, aux, dst;
			public AreaDati( int n, Pin src, Pin aux, Pin dst ) {
				this.n=n; this.src=src; this.aux=aux; this.dst=dst;
			}
		}
		Stack<AreaDati> stack=new StackConcatenato<>();
		stack.push( new AreaDati(n,src,aux,dst) );
		while( !stack.isEmpty() ) {
			AreaDati ad=stack.pop();
			if( ad.n==1 ) sposta1disco( ad.src,ad.dst );
			else {
				stack.push( new AreaDati(ad.n-1,ad.aux,ad.src,ad.dst) );
				stack.push( new AreaDati(1,ad.src,ad.aux,ad.dst) );
				stack.push( new AreaDati( ad.n-1,ad.src,ad.dst,ad.aux) );
			}
		}
	}//muoviIte 
	
	public void risolvi() {
		muovi(n,Pin.SX,Pin.CL,Pin.DX);
	}//risolvi
	
	public static void main( String[] args ) {
		TorriDiHanoi th=new TorriDiHanoi(9);
		th.risolvi();
	}//main
}//TorriDiHanoi
