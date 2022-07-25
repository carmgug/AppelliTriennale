package poo.file;
//copia di file

import java.io.*;

public class Copia{
	public static void main( String []args ) throws IOException {
		FileInputStream source=new FileInputStream("c:\\poo-file\\f1.dat");
		FileOutputStream dest=new FileOutputStream("c:\\poo-file\\f2.dat");
		int dato;
		for(;;){
			if( source.available()==0 ) break;
			dato=source.read();
			//if( dato==-1 ) break;
			dest.write( dato );
		}
		source.close();
		dest.close();
	}//main
}//Copia