package poo.file;
import java.io.*;
import java.util.*;

public class FileDemo{
	static void visualizza( String nome ) throws IOException {
        DataInputStream f=
        	new DataInputStream( new FileInputStream(nome) );
        int x=0;
        for(;;){
			try{
     			x=f.readInt();
		    }catch( IOException e ){
				//EOF
				break;
			}
			System.out.println(x);
		}
	}//visualizza
	static boolean esiste( String nome, int x ) throws IOException{
		//dimostrazione dell'uso di r.a.f. con ricerca binaria
		RandomAccessFile f=new RandomAccessFile( nome, "r");
		int inf=0, sup=(int)(f.length()/4)-1;
		for(;;){
			if( inf>sup ) return false;
			int med=(inf+sup)/2;
			f.seek( med*4 );
			int elem=f.readInt();
			if( elem==x ) return true;
			if( elem>x ) sup=med-1;
			else inf=med+1;
		}
	}//esiste
	public static void main( String []args ) throws IOException {
		Scanner sc=new Scanner( System.in );
		System.out.print("Nome file=");
		String nomeFile=sc.next();
		File f=new File( nomeFile );
		DataOutputStream dos=null;
		DataInputStream dis=null;
		if( !f.exists() ){
			//crea file
            dos=new DataOutputStream(
				         new FileOutputStream( nomeFile ));
     		System.out.println("Fornire una sequenza ordinata di interi sino al primo non intero");
			int x=0;
			for(;;){
				try{
				    x=Integer.parseInt(sc.next());
				}catch( Exception e ){
					System.out.println("Fine sequenza"); break;
				}
				dos.writeInt( x );
			}
			dos.close();
		}
		System.out.println("File originario");
		visualizza( nomeFile );
		dis=new DataInputStream( new FileInputStream(nomeFile));
		System.out.println("Fornisci ora un intero x da inserire in "+nomeFile);
		int x=sc.nextInt();
		dos=new DataOutputStream( new FileOutputStream("tmp") );
		//ricopia gli elementi di dis <= x
		int y=0;
		boolean flag=false;
		for(;;){
			try{
			   y=dis.readInt();
		    }catch(IOException e){
  		       //EOF detected
  		       break;
		    }
		    if( y>x ){ flag=true; break; }
		    dos.writeInt( y );
		}
		//scrivi ora x
		dos.writeInt(x);
		//scrivi ora porzione rimanente di dis
		int z=0;
		for(;;){
			if( flag ){ flag=false; /*residuo*/ dos.writeInt( y ); }
			try{
			   z=dis.readInt();
		    }catch(IOException e){
  		       //EOF detected
  		       break;
		    }
		    dos.writeInt(z);
		}
		dos.close(); dis.close();
		//ricopia tmp su nomeFile
		dos=new DataOutputStream( new FileOutputStream( nomeFile ));
		dis=new DataInputStream( new FileInputStream( "tmp") );
		for(;;){
		   try{
		      z=dis.readInt();
	       }catch( IOException e ){ //EOF
	          break;
	       }
	       dos.writeInt(z);
		}
		dos.close(); dis.close();
		System.out.println("File dopo aggiornamento selettivo");
		visualizza( nomeFile );
		dos=new DataOutputStream( new FileOutputStream("tmp") );
		dos.close();
		System.out.print("Valore da cercare x=");
		x=sc.nextInt();
		if( esiste( nomeFile, x ) ) System.out.println(x+" esiste");
		else System.out.println(x+" non esiste");
	}//main
}//DemoFile