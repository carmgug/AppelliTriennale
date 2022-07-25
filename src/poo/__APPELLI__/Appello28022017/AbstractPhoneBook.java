package poo.__APPELLI__.Appello28022017;

import java.util.Iterator;

public abstract class AbstractPhoneBook implements PhoneBook{

	
	public boolean equals(Object o) {
		if(!(o instanceof PhoneBook)) return false;
		if(o==this) return true;
		PhoneBook Lista=(PhoneBook)o;
		if(size()!=Lista.size()) return false;
		Iterator<Persona> it=iterator(),it2=Lista.iterator();
		while(it.hasNext()) {
			Persona p1=it.next(),p2=it2.next();
			if(!p1.equals(p2)) return false;
		}
		
		return false;
	}
	public String toString() {
		StringBuilder sb=new StringBuilder();
		sb.append("[");
		Iterator<Persona> it=iterator();
		while(it.hasNext()) {
			sb.append(it.next());
			if(it.hasNext()) sb.append("; ");
		}
		sb.append("]");
		
		
		return sb.toString();
	}
	
	public int hashCode() {
		final int M=43;
		int h=0;
		Iterator<Persona> it=iterator();
		while(it.hasNext()) {
			Persona p=it.next();
			h=h*M+(p.getNickname().hashCode()+p.getPhone().hashCode());
		}
		
		return h;
	}
	
	
}
