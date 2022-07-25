package poo.__APPELLI__.Appello28022017;

import java.util.Iterator;
import java.util.LinkedList;

import java.util.List;
import java.util.StringTokenizer;

public interface PhoneBook extends Iterable<Persona> {
	public void add(Persona p);
	default public List<Persona> remove(String s){
		List<Persona> l=new LinkedList<Persona>();
		Iterator<Persona> it=iterator();
		if(s.contains("*")) {
			StringTokenizer st=new StringTokenizer(s,"*");
			s=st.nextToken(); //presa sottostringa
			while(it.hasNext()) {
				Persona p=it.next();
				if(p.getNickname().contains(s)) {l.add(p);it.remove();}
			}
		}
		else {
			while(it.hasNext()) {
				Persona p=it.next();
				if(p.getNickname().equals(s)) {l.add(p);it.remove();}
			}
		}
		return l;
	}
	
	
	default public Persona remove(Persona p) {
		Iterator<Persona> it=iterator();
		Persona x=null;
		while(it.hasNext()) {
			x=it.next();
			if(x.equals(p)) {it.remove();}
		}
		return x;
		
	}
	default public List<Persona> locate(String s){
		List<Persona> l=new LinkedList<Persona>();
		Iterator<Persona> it=iterator();
		if(s.contains("*")) {
			StringTokenizer st=new StringTokenizer(s,"*");
			s=st.nextToken(); //presa sottostringa
			while(it.hasNext()) {
				Persona p=it.next();
				if(p.getNickname().contains(s)) {l.add(p);}
			}
		}
		else {
			while(it.hasNext()) {
				Persona p=it.next();
				if(p.getNickname().equals(s)) {l.add(p);}
			}
		}
		return l;
	}
	default public int size() {
		int c=0;
		for(Persona p:this) {
			c++;
		}
		return c;
	}
	default public void clear() {
		Iterator<Persona> it=iterator();
		while(it.hasNext()) {
			it.next();it.remove();
		}
	}

}
