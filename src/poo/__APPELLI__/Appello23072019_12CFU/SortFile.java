package poo.__APPELLI__.Appello23072019_12CFU;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;

public class SortFile {
	public enum Direzione{F1_TMP,TMP_F2}
	
	
	private static boolean CercaMin(RandomAccessFile rf,DataOutputStream tmp,DataOutputStream dos) throws IOException {
		int min=0;
		try {
			min=rf.readInt();
		}catch(IOException e) {
			return false;
		}
		
		for(;;) {
			int x=0;
			try {
				x=rf.readInt();
			}catch(IOException e) {
				break;
			}
			if(x<min) {tmp.writeInt(min); min=x;}
			else tmp.writeInt(x);
		}
		dos.writeInt(min);
		return true;
	}
	
	
	
	public static void Ordina(String NomeFile) throws IOException {
		
		RandomAccessFile rf=new RandomAccessFile(NomeFile,"w");
		DataOutputStream tmp=new DataOutputStream(new FileOutputStream("tmp"));	
		DataOutputStream dos=new DataOutputStream(new FileOutputStream("FileOrdinato"));	
		Direzione d=Direzione.F1_TMP;
		boolean EsisteMinimo=CercaMin(rf,tmp,dos);
		while(EsisteMinimo) {
			switch(d) {
				case F1_TMP:
					d=Direzione.TMP_F2;
					rf.close();tmp.close();
					rf=new RandomAccessFile("tmp","w");
					tmp=new DataOutputStream(new FileOutputStream(NomeFile));break;
				default:
					d=Direzione.F1_TMP;
					rf.close();tmp.close();
					rf=new RandomAccessFile(NomeFile,"w");
					tmp=new DataOutputStream(new FileOutputStream("tmp"));	
			}
			EsisteMinimo=CercaMin(rf,tmp,dos);
		}
		rf.close();tmp.close();dos.close();
		File f1=new File(NomeFile);
		File f2=new File("FileOrdinato");
		f1.delete();
		f2.renameTo(f1);
	
		
		
	}
	
	
	
	

}
