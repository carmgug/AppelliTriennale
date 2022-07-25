package poo.__APPELLI__.Appello17072015;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
	
	
	
	public static void Collezziona(String NomeFile,Insieme<String> parole,Insieme<Integer> Numero) throws IOException {
		BufferedReader br=new BufferedReader(new FileReader(NomeFile));
		for(;;) {
			String linea=br.readLine();
			if(linea==null) break;
			StringTokenizer st=new StringTokenizer(linea," ");
			while(st.hasMoreTokens()) {
				String x=st.nextToken();
				if(x.matches("[\\d]+")) {
					Numero.add(Integer.parseInt(x));
				}
				else if(x.matches("[\\W]+")) {
					parole.add(x);
				}
				
			}
		}
		br.close();
	}
	
	public static void main(String[] arg) throws IOException {
		
		
		Scanner sc=new Scanner(System.in);
		System.out.print("Inserisci NomeFile >>> ");
		String NomeFile=sc.nextLine();
		Insieme<String> parole=new InsiemeConcatenato<>();
		Insieme<Integer> numeri=new InsiemeConcatenato<>();
		Collezziona(NomeFile,parole,numeri);
		InsiemeOrdinato<String> ParoleOrdinate=new InsiemeOrdinatoAlbero<>(parole,(s1,s2)->{
			if(s1.length()>s2.length()) return 1;
			if(s1.length()<s2.length()) return -1;
			return s1.compareTo(s2);
		});

		InsiemeOrdinato<Integer> NumeroOrdinati=new InsiemeOrdinatoAlbero<>(numeri,(s1,s2)->{
			if(s1>s2) return -1;
			if(s1<s2) return 1;
			return 0;
		});

		System.out.println(ParoleOrdinate);
		System.out.println(NumeroOrdinati);
		
		
		
	}

}
