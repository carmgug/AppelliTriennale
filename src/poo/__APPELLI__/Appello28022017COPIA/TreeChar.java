package poo.__APPELLI__.Appello28022017COPIA;

import java.util.Scanner;


import poo.util.LinkedList;
import poo.util.List;

public class TreeChar {
	
	private static class Nodo{
		char info;
		Nodo fD,fS;
	}
	private Nodo radice=null;
	private final String Regex="([A-z]+|.)+";//chsahcysg.djsoiahdsa
	
	
	public void build(String clp) {
		if(!clp.matches(Regex)) throw new RuntimeException("Contenuto Stringa non valido!!");
		List<Character> Queue=new LinkedList<>();
		for(int i=0;i<clp.length();i++) {
			Queue.addLast(clp.charAt(i));
		}
		radice=build(Queue,radice);
	}
	
	private Nodo build(List<Character> Queue,Nodo radice) {
		if(Queue.isEmpty()) return null;
		Character e=Queue.removeFirst();
		System.out.println(e);
		if(e.equals('.')) {System.out.println(true);return null;}
		Nodo nc=new Nodo();
		nc.info=e;
		
		nc.fS=build(Queue,nc.fS);
		nc.fD=build(Queue,nc.fD);
		System.out.println(e+"ciao");
		return nc;
	}

	
	public void PreOrder(List<Character> l) {
		PreOrder(l,radice);
	}
	private void PreOrder(List<Character> l,Nodo radice){
		if(radice==null) {l.addLast('.'); return;}
		l.addLast(radice.info);
		PreOrder(l,radice.fS);
		PreOrder(l,radice.fD);
	}
	
	public void InOrder(List<Character> l) {
		InOrder(l,radice);
	}
	private void InOrder(List<Character> l,Nodo radice){
		if(radice==null) {l.addLast('.'); return;}
		InOrder(l,radice.fS);
		l.addLast(radice.info);
		InOrder(l,radice.fD);
	}
	
	public void PostOrder(List<Character> l) {
		PostOrder(l,radice);
	}
	private void PostOrder(List<Character> l,Nodo radice){
		if(radice==null) {l.addLast('.'); return;}
		PostOrder(l,radice.fS);
		PostOrder(l,radice.fD);
		l.addLast(radice.info);
	}
	
	public String toString() {
		List<Character> l=new LinkedList<>();
		PostOrder(l);
		StringBuilder sb=new StringBuilder(20);
		sb.append("[");
		for(Character x:l) {
			sb.append(x+", ");
		}
		sb.setLength(sb.length()-2);//", " dell'ultimo elemento
		sb.append("]");
		return sb.toString();
	}
	
	public boolean equals(Object o) {
		if(!(o instanceof TreeChar)) return false;
		if(o==this) return true;
		TreeChar tree=(TreeChar)o;
		
		return equals(radice,tree.radice);
	}
	private boolean equals(Nodo radice1,Nodo radice2) {
		if(radice1==null && radice2==null) return true;
		if(radice1==null) return false;
		if(radice2==null) return false;
		if(radice1.info != radice2.info)return false;
		return equals(radice1.fS,radice2.fS) && equals(radice1.fD,radice2.fD);
	}
	
	public int hashCode() {
		final int M=43;
		int h=0;
		List<Character> l=new LinkedList<>();
		PostOrder(l);
		for(Character c:l) h=h*M+c.hashCode();
		return h;
	}
	public static void main(String[] arg) {
		Scanner sc=new Scanner(System.in);
		System.out.print("Insersici Stringa>> ");
		String clp=sc.nextLine();
		TreeChar tree=new TreeChar();
		tree.build(clp);
		System.out.println(tree.radice.info);
		List<Character> l=new LinkedList<>();
		tree.InOrder(l);System.out.println(l);
		l.clear();
		tree.PreOrder(l);System.out.println(l);
		l.clear();
		tree.PostOrder(l);System.out.println(l);
	}
	
}
