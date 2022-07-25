package poo.Orale;

import java.io.IOException;
import java.util.Scanner;

import poo.file.ObjectFile;
import poo.file.ObjectFile.Modo;

public class MergeFile {

	
	public static void main(String[] arg) throws IOException {
		
		
		
		
		Scanner sc=new Scanner(System.in);
		System.out.print("Inserisci NomeFile1>> ");
		String NomeFile1=sc.nextLine();
		System.out.print("Inserisci NomeFile2>> ");
		String NomeFile2=sc.nextLine();
		
		ObjectFile<Integer> f3=new ObjectFile<>("FileOrdinato",Modo.SCRITTURA);
		
		ObjectFile<Integer> f1=new ObjectFile<>(NomeFile1,Modo.LETTURA);
		ObjectFile<Integer> f2=new ObjectFile<>(NomeFile2,Modo.LETTURA);

		
		f1.get();f2.get();
		while(!f1.eof() && !f2.eof()) {
			int x=f1.peek();int y=f2.peek();
			if(x>y) {
				f3.put(y);f2.get();
			}
			else {
				f3.put(x);f1.get();
			}
		}
		
		while(!f1.eof()) {
			f3.put(f1.peek());
			f1.get();
		}
		
		while(!f2.eof()) {
			f3.put(f2.peek());
			f2.get();
		}
		
		f1.close();f2.close();sc.close();
		f3.open(Modo.LETTURA);
		f3.get();
		System.out.print("[");
		while(!f3.eof()) {
			System.out.print(f3.peek());
			f3.get();
		}
		System.out.print("]");
		
		
		
		
		
	}
}
