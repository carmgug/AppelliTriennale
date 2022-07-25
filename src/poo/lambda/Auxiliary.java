package poo.lambda;

public class Auxiliary {
	public static int method1( String s1, String s2 ) {
		if( s1.length()<s2.length() ||
		    s1.length()==s2.length() && s1.compareTo(s2)<0 ) return -1;
		if( s1.equals(s2) ) return 0;
		return 1;
	}//method1
	public int method2( String s1, String s2 ) {
		if( s1.length()>s2.length() ||
			s1.length()==s2.length() && s1.compareTo(s2)<0 ) return -1;
		if( s1.equals(s2) ) return 0;
		return 1;
	}//method2
}//Auxiliary
