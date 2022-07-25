package poo.__APPELLI__.Appello09012020;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class Schema implements Cruciverba{

	char[][] schema;
	
	
	public Schema(char[][] Schema) {
		char[][] copia=new char[Schema.length][Schema[0].length];
		for(int i=0;i<Schema.length;i++) {
			System.arraycopy(Schema[i], 0, copia[i], 0, Schema[0].length);
		}
		
	}
	
	
	@Override
	public int getNumeroRighe() {
		return schema.length;
	}

	@Override
	public int getNumeroColonne() {
		return schema[0].length;
	}

	@Override
	public List<String> paroleOrizzontali() {
		LinkedList<String> l=new LinkedList<>();
		StringBuilder s=new StringBuilder(20);
		for(int i=0;i<schema.length;++i) {
			for(int j=0;j<schema[0].length;++j) {
				if(String.valueOf(schema[i][j]).matches("[A-z]"))
					s.append(schema[i][j]);
				else if(schema[i][j]==' ') { //aggiungiamo la parola e formiamo la prossima
					l.add(s.toString());
					s=new StringBuilder(20);
				}
			}
		}
		
		l.sort(new Comparator<String>() {
			
			public int compare(String s1,String s2) {
				if(s1.length()>s2.length())
					return 1;
				if(s1.length()<s2.length())
					return -1;
				return s1.compareTo(s2);
			}
		});
		//Domanda: l'ordine avviene in ordine crescente o decrescente?
		//Risposta: l'ordine � "Ascending" Ascendente dal pi� piccolo al pi� grande come richiesto.
		return l;
	}

	@Override
	public List<String> paroleVerticali() {
		LinkedList<String> l=new LinkedList<>();
		StringBuilder s=new StringBuilder(20);
		for(int j=0;j<schema[0].length;++j) {
			for(int i=0;i<schema.length;++i) {
				if(String.valueOf(schema[i][j]).matches("[A-z]"))
					s.append(schema[i][j]);
				else if(schema[i][j]==' ') { //aggiungiamo la parola e formiamo la prossima
					l.add(s.toString());
					s=new StringBuilder(20);
				}
			}
		}
		
		l.sort(new Comparator<String>() {
			
			public int compare(String s1,String s2) {
				if(s1.length()>s2.length())
					return 1;
				if(s1.length()<s2.length())
					return -1;
				return s1.compareTo(s2);
			}
		});
		return l;
	}

}
