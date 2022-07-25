package poo.__APPELLI__.Appello27022017;

public class Impiegato {
	
	String cognome,nome;
	int stanza;
	
	public Impiegato(String cognome,String nome,int stanza) {
		this.cognome=cognome;
		this.nome=nome;
		this.stanza=stanza;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getStanza() {
		return stanza;
	}
	public void setStanza(int stanza) {
		this.stanza = stanza;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cognome == null) ? 0 : cognome.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + stanza;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Impiegato)) return false;
		if(obj==this) return true;
		Impiegato other = (Impiegato) obj;
		if(!cognome.equals(other.cognome)) return false;
		if(!nome.equals(other.nome)) return false;
		return true;
	}
	
	@Override
	public String toString() {
		return cognome +" "+nome;
	}
	
	

}
