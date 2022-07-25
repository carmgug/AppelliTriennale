package poo.Orale;

public class TorriDiHinaoi {
	public enum Pin{SX,CL,DX}
	
	int n;
	public TorriDiHinaoi(int n) {
		if(n<=0) throw new IllegalArgumentException();
		this.n=n;
		
	}
	
	
	public void Sposta1Disco(Pin srg,Pin dst) {
		System.out.println("Sposta 1 disco da "+srg+" a "+dst);
	}
	
	
	public void muovi(int n,Pin srg,Pin aux,Pin dst) {
		if(n==1) Sposta1Disco(srg,dst);
		else {
			muovi(n-1,srg,dst,aux);
			Sposta1Disco(srg,dst);
			muovi(n-1,aux,srg,dst);
		}
	}
	
	
	public void risolvi() {
		muovi(n,Pin.SX,Pin.CL,Pin.DX);
	}
	
	
	
	public static void main(String[] arg) {
		TorriDiHinaoi x=new TorriDiHinaoi(3);
		x.risolvi();
	}
	
	
	
	
}
