package poo.fpfpq;
import java.util.*;
import java.io.*;
import poo.util.GestoreTesto;

public class FpFpq{
	public static void main( String[] args ) throws IOException{

		Scanner sc=new Scanner(System.in);
		
		
		
		
		String nomeFile=null;
		File f=null;
		do{
			System.out.print("Nome file testo: ");
			nomeFile=sc.nextLine();
			f=new File( nomeFile );
			if( !f.exists() ) System.out.println("File "+nomeFile+" inesistente! Ridarlo");
		}while( !f.exists() );

		GestoreTesto gt=new GestoreTesto( f, "[\\W]+" );
		
		Statistica stat=new StatisticaMap();
		
		GestoreTesto.Simbolo sim=gt.prossimoSimbolo();
		
		String ppre=null; //parola precedente
		while( sim!=GestoreTesto.Simbolo.EOF ){
			String pcor=gt.getString().toUpperCase(); //parola corrente
			System.out.println("Token:   "+pcor);
			stat.arrivoParola( pcor );
			if( ppre!=null ){
				stat.paroleConsecutive( ppre, pcor );
			}
			ppre=pcor;
			sim=gt.prossimoSimbolo();

		}//while

		System.out.print("Parola target=");
		String target=sc.nextLine().toUpperCase();
		sc.close();

		System.out.println("Statistica d'uso delle parole:");
		System.out.println(stat);
		
		PrintWriter pw=new PrintWriter( new FileWriter("c:\\poo-file\\statistica.txt"));
        pw.println( stat );
		pw.close();

		
		System.out.println("Parola che più verosimilmente segue "+target+"="+
		                   stat.parolaCheSeguePiuFrequente(target));
		System.out.println("Parola che meno verosimilmente segue "+target+"="+
		                   stat.parolaCheSegueMenoFrequente(target));

	}//main
}//FpFpq