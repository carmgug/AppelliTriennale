package poo.__APPELLI__.Appello24012014;

import java.util.Scanner;

public class Esercizio3 {

	
	
	private static int RicercaBinaria(int[] v,int x,int inf,int sup) {
		int med;
		if(inf>sup)
			return -1;
		else {
			med=sup+inf;
			if(v[med]==x) return med;
			if(v[med]>x) {
				return RicercaBinaria(v,x,0,med-1);
			}
			else {
				return RicercaBinaria(v,x,med+1,sup);
			}
		}
	}
	
	public static void main(String[] args) {
		
		
		Scanner sc=new Scanner(System.in);
		int[] v=new int[10];
		boolean ok=true;//ottimismo
		for(int i=0;i<10;i++) {
			System.out.print("Inserisci Numero in poizione "+i+" : >>");
			v[i]=sc.nextInt();
			if(i>0 && v[i-1]>v[i]) {ok=false;break;}
		}
		if(!ok) {System.out.println("-2");sc.close();System.exit(-1);}
		sc.nextLine();//pulizia buffer
		System.out.print("Inserisci Numero x: >>>");
		int x=sc.nextInt();
		System.out.println(RicercaBinaria(v,x,0,v.length-1));
	}
}
