package poo.__APPELLI__.Appello23072019_12CFU;

import java.io.IOException;
import java.io.RandomAccessFile;

public class SortFileOnCollection {
	
	
	
	
	public static ListaConcatenata<Integer> Ordina(String NomeFile) throws IOException {
		ListaConcatenata<Integer> l=new ListaConcatenata<Integer>();
		RandomAccessFile rf=new RandomAccessFile(NomeFile,"w");
		long posrf=0;
		while(posrf<rf.length()) {
			int x=rf.readInt();
			posrf=rf.getFilePointer();
			l.add(x);
		}
		l.BubbleSort((s1,s2)->{return s1.compareTo(s2);});
		return l;
	}
	
	
}
