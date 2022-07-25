package poo.__APPELLI__.Appello28022017COPIA;

import java.util.Iterator;
import java.util.TreeSet;

public class PhoneBookCollection extends AbstractPhoneBook {
	TreeSet<Persona> rubrica;
	
	
	public PhoneBookCollection() {
		rubrica=new TreeSet<Persona>();
	}

	
	public void clear() {
		rubrica.clear();
	}
	
	public int size() {
		return rubrica.size();
	}

	@Override
	public void add(Persona p) {
		rubrica.add(p);//persona viene sostituita da persona p non possono esistere omonimi di nickname
		
	}


	@Override
	public Iterator<Persona> iterator() {
		return rubrica.iterator();
	}
}
