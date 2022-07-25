package poo.__APPELLI__.Appello28022017COPIA;

import java.util.Iterator;
import java.util.StringTokenizer;

import poo.util.LinkedList;
import poo.util.List;

public interface PhoneBook extends Iterable<Persona>{
	void add(Persona p);
	default public List<Persona> remove(String s){
		String linea=s;
		if(s.contains("*")) {
			StringTokenizer st=new StringTokenizer(s,"*");
			linea=st.nextToken();//prendo la sottostringa
		}
		List<Persona> l=new LinkedList<>();
		Iterator<Persona> it=iterator();
		while(it.hasNext()) {
			Persona p=it.next();
			if(p.getNickname().equals(linea)) {l.addLast(p);it.remove();}
		}
		return l;
	}
	default public Persona remove(Persona p) {
		Iterator<Persona> it=iterator();
		
		while(it.hasNext()) {
			Persona x=it.next();
			if(x.equals(p))it.remove();return x;
		}
		return null;
	}
	default List<Persona> locate(String s){
		String linea=s;
		if(s.contains("*")) {
			StringTokenizer st=new StringTokenizer(s,"*");
			linea=st.nextToken();//prendo la sottostringa dopo o prima di "*" 
		}
		List<Persona> l=new LinkedList<>();
		Iterator<Persona> it=iterator();
		while(it.hasNext()) {
			Persona p=it.next();
			if(p.getNickname().equals(linea)) {l.addLast(p);}
		}
		return l;
	}
	default public int size() {
		int c=0;
		for(Persona p:this)
			c++;
		return c;
	}
	default public void clear() {
		Iterator<Persona> it=iterator();
		while(it.hasNext()) {
			it.next();it.remove();
		}
		
	}

}
