package poo.__APPELLI__.Appello11092019;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.Map.Entry;
public class RiferimentiIncorciati {
	
	private static void CostruisciIndice(TreeMap<String,LinkedList<Integer>> indice,String NomeFile) throws IOException {
		BufferedReader br=new BufferedReader(new FileReader(NomeFile));
		for(;;) {
			String linea=br.readLine();
			if(linea==null) break;
			StringTokenizer st=new StringTokenizer(linea,": ");
			String parola=st.nextToken();int numero=Integer.parseInt(st.nextToken());
			if(indice.containsKey(parola)) {
				LinkedList<Integer> l=indice.get(parola);
				if(l.contains(numero)) break;
				l.add(numero);
				l.sort(Comparator.naturalOrder());
			}
			else {
				LinkedList<Integer> l=new LinkedList<>();
				l.add(numero);
				indice.put(parola,l);
			}
		}
		br.close();
		
	}
	
	
	public static void main(String[] args) {
		TreeMap<String,LinkedList<Integer>> indice=new TreeMap<>(
				new Comparator<>() {

					@Override
					public int compare(String o1, String o2) {
						if(o1.length()>o2.length()) return 1;
						if(o1.length()<o2.length()) return -1;
						return o1.compareTo(o2);
					}
				});
		//16:32
		Scanner sc=new Scanner(System.in);
		System.out.println("Riferimenti Crociati: PREMI INVIO");
		sc.nextLine();
		
		
		for(;;) {
			System.out.println("Inserire nome File: ");
			String NomeFile=sc.nextLine();
			if(NomeFile.equals(".")) {sc.close();break;}
			try {
				CostruisciIndice(indice,NomeFile);break;
			}catch(IOException e ){
				System.out.println("Inserisci File esistente e valido");
			}
		}
		for(Entry<String, LinkedList<Integer>> entry: indice.entrySet()) {
			String Parola= entry.getKey();
			LinkedList<Integer> lista= entry.getValue();
			//Parola
			System.out.println(Parola);
			//Pagine
			StringBuilder sb=new StringBuilder(20);
			sb.append(" => ");
			for(Integer pagine:lista)
				sb.append(pagine+" ");
			System.out.print(sb);
			//PER parola successiva
			System.out.println();
		}
		
		
		
		
		System.exit(0);
	}

}
