package poo.file;
import java.util.*;
import java.io.*;
public class ObjectFileSelectionSort {
	static enum Direzione{ DA_F1_A_TEMP2, DA_TEMP2_A_F1 };
	
	static boolean cercaMin( ObjectFile<Integer> f1, ObjectFile<Integer> temp1, ObjectFile<Integer> temp2 ) throws IOException{
		if( f1.eof() ) return false;
		int min=f1.peek(); f1.get();
		while( !f1.eof() ){
			int x=f1.peek(); f1.get();
			if( x<min ){ temp2.put(min); min=x; }
			else temp2.put(x);
		}
		temp1.put(min);
		return true;
	}//cercaMin
	public static void main( String[] args ) throws IOException{
		System.out.println("Ordinamento esterno di un file di oggetti interi con selection sort");
		Scanner sc=new Scanner( System.in );
		String nomeFile=null;
		boolean ok=false;
		do{
			System.out.print("Nome file : ");
			nomeFile=sc.nextLine();
			File f=new File(nomeFile);
			ok=f.exists();
			if( !ok ) 
				System.out.println(nomeFile+" non esiste. Ridare il nome del file.");
		}while( !ok );
		ObjectFile<Integer> f1=new ObjectFile<Integer>( nomeFile, ObjectFile.Modo.LETTURA );
		ObjectFile<Integer> temp1=new ObjectFile<Integer>( "c:\\poo-file\\temp1", ObjectFile.Modo.SCRITTURA );
		ObjectFile<Integer> temp2=new ObjectFile<Integer>( "c:\\poo-file\\temp2", ObjectFile.Modo.SCRITTURA );
		Direzione d=Direzione.DA_F1_A_TEMP2;
		boolean continua=cercaMin( f1, temp1, temp2 );;
		while( continua ){
			switch( d ){
				case DA_F1_A_TEMP2:
					d=Direzione.DA_TEMP2_A_F1;
					f1.open( ObjectFile.Modo.SCRITTURA );
					temp2.open( ObjectFile.Modo.LETTURA ); 
					continua=cercaMin( temp2, temp1, f1 );
					break;
				default:
					d=Direzione.DA_F1_A_TEMP2;
					f1.open( ObjectFile.Modo.LETTURA );
					temp2.open( ObjectFile.Modo.SCRITTURA ); 
					continua=cercaMin( f1, temp1, temp2 );
			}
		}
		f1.close(); temp1.close(); temp2.close();
		
		File w=new File( "c:\\poo-file\\temp2" );
		w.delete(); //temp2 e' rimosso
		//ridenomina temp1 come f1
		File y=new File( nomeFile );
		y.delete();
		File z=new File("c:\\poo-file\\temp1");
		z.renameTo(y);
		
		System.out.println("Contenuto file dopo ordinamento esterno");
		System.out.println(f1);
		sc.close();
		
	}//main
}//ObjectFileSelectionSort
