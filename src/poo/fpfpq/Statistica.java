package poo.fpfpq;

public interface Statistica{
	void arrivoParola( String p );
	void paroleConsecutive( String p, String d );
	int numTotaleParole();
	int frequenza( String p );
	int frequenzaCoppia( String p, String q );
	String parolaCheSeguePiuFrequente( String target );
	String parolaCheSegueMenoFrequente( String target );
}//Statistica