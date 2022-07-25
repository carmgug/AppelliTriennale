package poo.Orale;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class SelectionSortEsterno {
	public enum Direzione{DA_F1_A_TMP,DA_TMP_A_F1}
	
	static boolean CercaMinimo(RandomAccessFile f1,DataOutputStream tmp,DataOutputStream F_Ordinato) throws IOException {
		int min=0;
		try {
			min=f1.readInt();
		}catch(IOException e) {
			return false;
		}
		
		for(;;) {
			
			int x=0;
			try {
				x=f1.readInt();
			}catch(IOException e) {
				break;
			}
			if(x<min) {tmp.writeInt(min); min=x;}
			tmp.writeInt(x);
		}
		F_Ordinato.writeInt(min);
		return true;
	}
	
	
	
	
	public static void main(String[] arg) throws IOException {
		
		Scanner sc=new Scanner(System.in);
		System.out.print("SelectionSort File: PREMI INVIO");
		sc.nextLine();
		boolean flag=false;
		String NomeFile=null;
		do {
			System.out.print("Inserisi NomeFile >> " );
			NomeFile=sc.nextLine();
			File f=new File(NomeFile);
			if(!f.exists()) {System.out.println("File non esistente!!"); continue;}
			flag=true;
		}while(!flag);
		
		
		RandomAccessFile f1=new RandomAccessFile(NomeFile,"r");
		DataOutputStream tmp=new DataOutputStream(new FileOutputStream("tmp"));
		DataOutputStream F_Ordinato=new DataOutputStream(new FileOutputStream("FileOrdinatoTMP"));
		Direzione d=Direzione.DA_F1_A_TMP;
		boolean E_min=CercaMinimo(f1,tmp,F_Ordinato);
		while(E_min) {
			switch(d) {
				case DA_F1_A_TMP:
					f1.close();tmp.close();
					f1=new RandomAccessFile("tmp","r");
					tmp=new DataOutputStream(new FileOutputStream(NomeFile));
					d=Direzione.DA_TMP_A_F1;
				case DA_TMP_A_F1:
					f1.close();tmp.close();
					f1=new RandomAccessFile(NomeFile,"r");
					tmp=new DataOutputStream(new FileOutputStream("tmp"));
					d=Direzione.DA_F1_A_TMP;
			}
			E_min=CercaMinimo(f1,tmp,F_Ordinato);
		}
		
		f1.close();tmp.close();F_Ordinato.close();
		
		
		File y=new File(NomeFile);
		y.delete();
		File y2=new File("FileOrdinatoTMP");
		y2.renameTo(y);
		sc.close();
		System.out.println("File Ordinato...Bye...");
	}
}
