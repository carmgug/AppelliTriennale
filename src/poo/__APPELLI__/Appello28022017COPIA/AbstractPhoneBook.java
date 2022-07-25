package poo.__APPELLI__.Appello28022017COPIA;

import java.util.Iterator;

public abstract class AbstractPhoneBook implements PhoneBook{

	public String toString() {
		StringBuilder sb=new StringBuilder(20);
		Iterator<Persona> it=iterator();
		//nickname PHONE=39837927
		//nickname2 PHONE=3182314
		while(it.hasNext()) {
			sb.append(it.next()+"\n");
		}
		return sb.toString();
	}
	
	public int hashCode() {
		final int M=43;
		int h=0;
		for(Persona p:this)
			h+=h*M+p.hashCode();
		
		return h;
	}
	
	public boolean equals(Object o) {
		if(!(o instanceof PhoneBook)) return false;
		if(o==this)return true;
		PhoneBook rubrica=(PhoneBook) o;
		if(rubrica.size()!=this.size()) return false;
		Iterator<Persona> it=iterator(),it2=rubrica.iterator();
		while(it.hasNext()) {
			Persona p=it.next(),p2=it2.next();
			if(!p.equals(p2)) return false;
		}
		return true;
	}
}
