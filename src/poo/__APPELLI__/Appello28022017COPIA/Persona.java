package poo.__APPELLI__.Appello28022017COPIA;

public class Persona implements Comparable<Persona>{
	private String nickname;
	private String phone;
	
	public Persona(String nickname,String phone) {
		//non si mette limite a nickname e phone ma si mantengono generali,ovviamente
		//per il nostro programma sviluppato in Main.java terremo conto delle restrizioni richieste
		//[A-z]stringaalfanumerica e Numero da 1 a 9 cifre
		this.nickname=nickname;
		this.phone=phone;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public boolean equals(Object o) {
		if(!(o instanceof Persona)) return false;
		if(o==this) return true;
		Persona p=(Persona) o;
		if(p.nickname.equals(this.nickname)) return true;
		return false;
	}
	
	public String toString() {
		return nickname.toUpperCase()+" PHONE= "+phone;
	}
	@Override
	public int compareTo(Persona p) {
		return nickname.compareTo(p.nickname);
	}

}
