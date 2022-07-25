package poo.__APPELLI__.Appello23072019_12CFU;

import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Scanner;

public class LetturaFile {
	
	
	public static void CreaLista(RandomAccessFile f1,LinkedList<Integer> l) {
		for(;;) {
			try {
				l.add(f1.readInt());
			}catch(IOException e) {
				return;
			}
		}
	}
	
	
	//BubbleSort
	/*
	private static class Nodo{
		Integer info;
		Nodo next;
	}
	
	/*
	public static void BubbleSort(LinkedList<Integer> l) {
		public void sort() {
        if (size > 1) {
            boolean wasChanged;

            do {
                Node current = head;
                Node previous = null;
                Node next = head.nextNode;
                wasChanged = false;

                while ( next != null ) {
                    if (current.data > next.data) {
                        
                        // This is just a literal translation
                        // of bubble sort in an array
                        Node temp = currentNode;
                        currentNode = next;
                        next = temp;
                        wasChanged = true;

                        if ( previous != null ) {
                            Node sig = next.nextNode;

                            previous.nextNode = next;
                            next.nextNode = current;
                            current.nextNode = sig;
                        } else {
                            Node sig = next.nextNode;

                            head = next;
                            next.nextNode = current;
                            current.nextNode = sig;
                        }

                        previous = next;
                        next = current.nextNode;
                    } else { 
                        previous = current;
                        current = next;
                        next = next.nextNode;
                    }
                } 
            } while( wasChanged );
        }
    }
	*/
	static enum Direzione{ DA_F1_A_TEMP3, DA_TEMP3_A_F1 };
	
	public static boolean cercaMIN(RandomAccessFile f1,RandomAccessFile f2,RandomAccessFile f3) throws IOException {
		int min=0;
		//leggiamo su f1 intero
		try {
			min=f1.readInt();
		}catch(EOFException e) {
			return false;
		}
		
		for(;;) {
			int x=0;
			try {
				x=f1.readInt();
			}catch(EOFException e) {break;}
			if(x<min) {f3.writeInt(min);min=x;}
			else f3.writeInt(x);
		}
		
		f2.writeInt(min);
		return true;
	}
	
	public static void main(String[] args) throws IOException {
		
		RandomAccessFile f1=null;
		String NomeFile;
		ciclo1: for(;;) {
			Scanner sc=new Scanner(System.in);
			System.out.print("Nome File: ");
			NomeFile=sc.nextLine();
			try {
				f1=new RandomAccessFile(NomeFile,"r");
			}catch(IOException e){
				System.out.print("Inserisci File Valido");
				continue ciclo1;
			}
			break;
		}
		//Prima Soluzione
		LinkedList<Integer> l=new LinkedList<Integer>();
		CreaLista(f1,l);
		System.out.println("Lista Di Partenza: "+l);
		l.sort(Comparator.naturalOrder());
		
		//Seconda Soluzione--->Seconda Classe
		RandomAccessFile temp2=new RandomAccessFile(NomeFile+"tmp2","rw");
		RandomAccessFile temp3=new RandomAccessFile(NomeFile+"tmp3","rw");
		boolean continua=cercaMIN(f1,temp2, temp3 );
		Direzione d=Direzione.DA_F1_A_TEMP3;
		while(continua) {
			switch(d) {
			
			case DA_F1_A_TEMP3:
				f1.close();temp3.close();
				f1=new RandomAccessFile(NomeFile+"tmp3","r");
				temp3=new RandomAccessFile(NomeFile,"rw");
				d=Direzione.DA_TEMP3_A_F1;
				break;
			default:
				f1.close();temp3.close();
				f1=new RandomAccessFile(NomeFile,"r");
				temp3=new RandomAccessFile(NomeFile+"tmp3","r");
				d=Direzione.DA_F1_A_TEMP3;
			}
			continua=cercaMIN(f1,temp2,temp3);
		}//WHILE
		
		f1.close();temp2.close();temp3.close();
		
		//manutenzione File
		File superfluo= new File(NomeFile+"tmp3");
		superfluo.delete();
		
		File y=new File(NomeFile);
		y.delete();
		
		//dove ci sono tutti i minimi
		File DaConservare= new File(NomeFile+"tmp2");
		DaConservare.renameTo(y);
		
		
		System.out.println("ContenutoOrdinato: ");
		f1=new RandomAccessFile(NomeFile,"r");
		System.out.print("[");
		for(;;){
			int x=0;
			try {
				x=f1.readInt();
			}catch(EOFException e) {break;}
			System.out.print(x+" ");
		}
		System.out.print("]");
		System.out.println();
		f1.close();
	}//main
	
}
