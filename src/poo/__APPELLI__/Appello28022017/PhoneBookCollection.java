package poo.__APPELLI__.Appello28022017;

import java.util.Iterator;
import java.util.TreeSet;

public class PhoneBookCollection extends AbstractPhoneBook {
	
	TreeSet<Persona> PhoneBook;

	
	public PhoneBookCollection() {
		PhoneBook=new TreeSet<>();
	}


	@Override
	public void add(Persona p) {
		if(PhoneBook.contains(p)) PhoneBook.remove(p);
		PhoneBook.add(p);
	}


	@Override
	public Iterator<Persona> iterator() {
		return PhoneBook.iterator();
	}
	
	
}
