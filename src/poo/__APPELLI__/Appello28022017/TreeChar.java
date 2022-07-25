package poo.__APPELLI__.Appello28022017;

import java.util.Scanner;

import poo.util.LinkedList;
import poo.util.List;

public class TreeChar {
	
	private class Nodo<Character>{
		Character info;
		Nodo<Character> fD,fS;
	}
	
	Nodo<Character> radice=null;
	
	public TreeChar() {
		radice=new Nodo<Character>();
		radice.info='.';
	}
	
	
	public void build(String clp) {
		clp=clp.toUpperCase();
		List<Character> Queue=new LinkedList<>();
		for(int i=0;i<clp.length();i++) Queue.addLast(clp.charAt(i));
		radice=build(Queue,radice);
		
	}
	
	private Nodo<Character> build(List<Character> Queue,Nodo<Character> radice){
		if(Queue.isEmpty()) {
			Nodo<Character> n=new Nodo<Character>();
			n.info='.';
			return n;
		}
		Nodo<Character> n=new Nodo<Character>();
		n.info=Queue.removeFirst();
		if(n.info=='.') return n;
		n.fD=new Nodo<Character>();n.fS=new Nodo<Character>();
		n.fS=build(Queue,n.fS);
		n.fD=build(Queue,n.fD);
		return n;
	}
	
	public void inOrder(List<Character> l) {
		if(radice.info=='.') {l.addFirst('.'); return;}
		inOrder(radice,l);
	}
	
	private void inOrder(Nodo<Character> radice,List<Character> l) {
		if(radice.info=='.') { return;}
		inOrder(radice.fS,l);
		l.addLast(radice.info);
		inOrder(radice.fD,l);
		
		
	}

	public void PostOrder(List<Character> l) {
		if(radice.info=='.') {l.addFirst('.'); return;}
		PostOrder(radice,l);
	}
	
	private void PostOrder(Nodo<Character> radice,List<Character> l) {
		if(radice.info=='.') { return;}
		PostOrder(radice.fS,l);
		PostOrder(radice.fD,l);
		l.addLast(radice.info);
	}
	

	public void PreOrder(List<Character> l) {
		if(radice.info=='.') {l.addFirst('.'); return;}
		PreOrder(radice,l);
	}
	
	private void PreOrder(Nodo<Character> radice,List<Character> l) {
		if(radice.info=='.') {return;}
		l.addLast(radice.info);
		PreOrder(radice.fS,l);
		PreOrder(radice.fD,l);
	}
	
	
	public String toString() {
		List<Character> l=new LinkedList<Character>();
		inOrder(l);
		return l.toString();
	}
	
	public boolean equals(Object o) {
		if(!(o instanceof TreeChar )) return false;
		if(o==this) return false;
		TreeChar Tree=(TreeChar)o;
		//if(Tree.size()!=this.size()) return false;
		return controlla(Tree.radice,radice);
		
	}
	private boolean controlla(Nodo<Character> r1,Nodo<Character>r2) {
		if(r1.info.equals(r2) && r1.info.equals('.')) return true;
		
		if(!r1.info.equals(r2)) return false;
		return controlla(r1.fD,r2.fD) && controlla(r1.fS,r1.fS);
	}
	
	
	
	public static void main(String [] args) {
		
		final String Regex="([A-z]|\\.)+";
		
		Scanner sc=new Scanner(System.in);
		String clp=null;boolean ok=false;
		do {
			System.out.print("Inserisci Pre-Order TreeChar >>>");
			clp=sc.nextLine();
			
			if(!clp.matches(Regex)) {System.out.println("TreeChar Non Corretto");ok=false; }
			else ok=true;
		}while(!ok);
		
		TreeChar Albero=new TreeChar();
		
		Albero.build(clp);
		
		LinkedList<Character> l=new LinkedList<Character>();
		Albero.inOrder(l);
		System.out.println(l);
		l.clear();
		Albero.PostOrder(l);
		System.out.println(l);
		l.clear();
		Albero.PreOrder(l);
		System.out.println(l);
		
		
		System.out.println(Math.round(1)*(3-1)+1);
		
	}
	
	
	
	
	

}
