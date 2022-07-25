package poo.__APPELLI__.Appello28022017;

public class Persona {
	String nickname;
	String phone;
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
		if(nickname.equals(p.nickname)) return true;
		return false;
	}
	
	public int compareTo(Persona p) {
		return nickname.compareTo(p.nickname);
	}
	
	public String toSstring() {
		return nickname+", "+phone;
	}
}
