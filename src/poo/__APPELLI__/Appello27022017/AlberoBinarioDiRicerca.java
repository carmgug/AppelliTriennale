package poo.__APPELLI__.Appello27022017;

import java.util.LinkedList;

import poo.util.List;

public class AlberoBinarioDiRicerca<T extends Comparable<? super T>> {
	
	private static class Nodo<T>{
		T info;
		Nodo<T> fD,fS;
	}
	
	Nodo<T> radice=null;
	private int size=0;
	
	
	
	public int altezza() {
		return altezza(radice);
	}
	
	private int altezza(Nodo<T> radice) {
		if(radice==null) return 0;
		int h1=0,h2=0;
		if(radice.fD!=null) h1=1+altezza(radice.fD);
		if(radice.fS!=null) h2=1+altezza(radice.fS);
		if(h1>h2) return h1;
		else return h2;
		
	}
	
	
	public boolean bilanciato() {
		return bilanciato(radice);
	}
	
	private boolean bilanciato(Nodo<T> radice) {
		if(radice==null) return true;
		int cfd=size(radice.fD);
		int cfs=size(radice.fS);
		if(Math.abs(cfs-cfd)>1) return false;
		return bilanciato(radice.fD) && bilanciato(radice.fS);
	}

	
	public void visitaPerLivelli(List<T> ls) {
		if(radice==null) return;
		LinkedList<Nodo<T>> Queue=new LinkedList<>();
		Queue.add(radice);
		while(!Queue.isEmpty()) {
			Nodo<T> visitato=Queue.removeFirst();
			ls.addLast(visitato.info);
			if(visitato.fD!=null)Queue.addLast(visitato.fS);
			if(visitato.fS!=null)Queue.addLast(visitato.fD);
		}
	}
	
	public void visitaPerLivelliFrontiera(List<T> ls) {
		if(radice==null) return;
		LinkedList<Nodo<T>> Queue=new LinkedList<>();
		Queue.add(radice);
		while(!Queue.isEmpty()) {
			Nodo<T> visitato=Queue.removeFirst();
			if(visitato.fD==null && visitato.fS==null) ls.addLast(visitato.info);
			
			if(visitato.fS!=null)Queue.addLast(visitato.fD);
			if(visitato.fD!=null)Queue.addLast(visitato.fS);
		}
	}
	
	
	private void vistaPerLivelli(List<T> ls,Nodo<T> radice) {
		if(radice==null) return;
		vistaPerLivelli(ls,radice.fS);
		vistaPerLivelli(ls,radice.fD);
		ls.addFirst(radice.info);
	}
	
	//metodi 
	private int size(Nodo<T> fD) {
		// TODO Auto-generated method stub
		return 0;
	}
	

}
