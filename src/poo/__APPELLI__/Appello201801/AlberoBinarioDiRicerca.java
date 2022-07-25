package poo.__APPELLI__.Appello201801;

public class AlberoBinarioDiRicerca<T extends Comparable<? super T>> {
	
	private static class Nodo<T>{
		T info;
		Nodo<T> fd,fs;
		
	
	}
	
	private Nodo<T> radice=null;
	private int size=0;
	
	
	public int altezza() {
		if(radice==null) return 0;
		int cfs=altezza(radice.fs,0,0);
		int cfd=altezza(radice.fd,0,0);
		if(cfs>cfd) return cfs;
		else return cfd;
	}
	
	private int altezza(Nodo<T> nodo,int cfs,int cfd) {
		if(nodo==null) return 0; //nessun futuro arco
		cfs+=1+altezza(nodo.fs,cfs,cfd); //arco che oltrepasso+futuri archi
		cfd+=1+altezza(nodo.fd,cfs,cfd);
		if(cfs>cfd) return cfs;
		else return cfd;
	}
	
	public  void frontiera(java.util.List<T> l) {
		frontiera(l,radice);
		
	}

	private void frontiera(java.util.List<T> l,Nodo<T> nodo) {
		if(nodo.fd==null && nodo.fs==null) {
			l.add(nodo.info);
			return;
		}
		frontiera(l,nodo.fs);
		frontiera(l,nodo.fd);
		
		
	}
}
