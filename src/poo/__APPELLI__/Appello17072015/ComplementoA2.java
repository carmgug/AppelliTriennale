package poo.__APPELLI__.Appello17072015;

import java.util.Scanner;

public class ComplementoA2 {
	
	
	
	
	
	
	public static void Complemento(int[] v) {
		
		for(int i=0;i<v.length;i++) {
			if(v[i]==0) v[i]=1;
			else v[i]=0;
		}
		int riporto=1;
		for(int i=v.length-1;i>=0;i--) {
			int ris=v[i]+riporto;
			if(ris>1) {
				v[i]=0;riporto=1;
			}
			else {
				v[i]=ris;riporto=0;
			}
		}
		if(riporto==1) throw new RuntimeException("Superamento Di Capacit�");
		
	}
	
	
	
	public static void main(String[] arg) {
		
		
		int[] v=new int[10];
		Scanner sc=new Scanner(System.in);
		for(int i=0;i<10;i++) {
			System.out.print("Inserisci bit 0/1 >>");
			boolean flag=false;int x=0;
			do {
				x=sc.nextInt();
				if(x!=1 && x!=0) System.out.println("Valore Sbagliato!!");
				else flag=true;
			}while(!flag);
			v[i]=x;
			sc.nextLine();//Pulizia buffer
		}
		
		Complemento(v);
		for(Integer x:v) {
			System.out.print(x+" ");
		}
		
	}

}
