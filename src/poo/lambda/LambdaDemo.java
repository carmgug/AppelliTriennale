package poo.lambda;
import java.util.Collections;
import java.util.List;

public class LambdaDemo {
	public static void main( String... args) {
		List<String> ls=java.util.Arrays.asList(
			"casa","zaino","duca","cuore","albero","oro","dado");
		System.out.println( ls );
		Collections.sort( ls, 
			(s1,s2)->Integer.compare(s1.length(),s2.length()) ); //lambda expr
		System.out.println( ls ); 
		Collections.shuffle( ls );
		Collections.sort( ls, Auxiliary::method1 ); //static method reference
		System.out.println( ls );
		Collections.shuffle(ls); 
		Auxiliary a=new Auxiliary();
		//non static method reference, on an explicit object
		Collections.sort( ls, a::method2 ); 
		System.out.println( ls );
		Collections.shuffle(ls); 
		//non static method reference, on an anonymous object
		Collections.sort( ls, new Auxiliary()::method2 );
		System.out.println( ls );
	}//main
}//LambdaDemo
