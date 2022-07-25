package poo.__APPELLI__.Appello09012020;

import java.util.List;

public interface Cruciverba {
	
	int getNumeroRighe();
	int getNumeroColonne();
	
	
	default boolean contains(String parola) {
		List<String> pO=paroleOrizzontali();
		List<String> pV=paroleVerticali();
		for(String x:pO) {
			if(x.equals(parola)) return true;
		}
		for(String x:pV) {
			if(x.equals(parola)) return true;
		}
		return false;
	}
	List<String> paroleOrizzontali();
	List<String> paroleVerticali();

}
