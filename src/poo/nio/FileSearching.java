package poo.nio;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Random;

public class FileSearching{
	static final int n=1000000;
	public static void main( String[] args ) throws IOException{
		String nome="C:\\Users\\carme\\Desktop\\adabra1";
		File f=new File(nome);
		if( !f.exists() ) {
			creaFile( nome );
		}
		int x=new Random().nextInt(n);
		int pos=0;
		
		long start=System.currentTimeMillis();
		pos=linearSearchRandomAccessFile(nome,x);
		long end=System.currentTimeMillis();
		System.out.println("linearSearchRAF: pos="+pos+" of x="+x+" elapsed time="+((end-start))+"msec");

		start=System.currentTimeMillis();
		pos=linearSearchDIS(nome,x);
		end=System.currentTimeMillis();
		System.out.println("linearSearchDIS: pos="+pos+" of x="+x+" elapsed time="+((end-start))+"msec");		

		start=System.currentTimeMillis();
		pos=linearSearchBDIS(nome,x);
		end=System.currentTimeMillis();
		System.out.println("linearSearchBDIS: pos="+pos+" of x="+x+" elapsed time="+((end-start))+"msec");		
		
		start=System.currentTimeMillis();
		pos=linearSearchMemoryMappedFile(nome,x);
		end=System.currentTimeMillis();
		System.out.println("linearSearchMMF: pos="+pos+" of x="+x+" elapsed time="+((end-start))+"msec");			
		
	}//main
	
	static int linearSearchRandomAccessFile( String nome, int x ) throws IOException{
	    RandomAccessFile f=new RandomAccessFile( nome, "r" );
	    int result=-1, elem=0;
	    for( int i=0; i<n; i++ ){
	    	f.seek( i*4 );
	    	elem=f.readInt();
	    	if( elem==x ){ result=i; break; }
	    }
	    f.close();
	    return result;
	}//linearSearchRandomAccessFile
	
	static int linearSearchDIS( String nome, int x ) throws IOException{
	    DataInputStream f=new DataInputStream( new FileInputStream(nome) );
	    int result=-1, elem=0, i=0;
	    for(;;){
	    	try {
	    		elem=f.readInt();
	    		if( elem==x ){ result=i; break; }
	    	}catch( EOFException e ) { break; }
	    	i++;
	    }
	    f.close();
	    return result;		
	}//linearSearchDIS

	static int linearSearchBDIS( String nome, int x ) throws IOException{
	    DataInputStream f=new DataInputStream( 
	    		new BufferedInputStream(new FileInputStream(nome) ) );
	    
	    int result=-1, elem=0, i=0;
	    for(;;){
	    	try {
	    		elem=f.readInt();
	    		if( elem==x ){ result=i; break; }
	    	}catch( EOFException e ) { break; }
	    	i++;
	    }
	    f.close();
	    return result;		
	}//linearSearchBDIS	
	
	static int linearSearchMemoryMappedFile( String nome, int x ) throws IOException{
		RandomAccessFile f=new RandomAccessFile( nome, "r" );
		FileChannel channel=f.getChannel();
		int length=(int)channel.size();
		MappedByteBuffer buffer=channel.map(FileChannel.MapMode.READ_ONLY, 0, length);
	    int result=-1, elem=0;
	    for( int i=0; i<n; i++ ){
	    	elem=buffer.getInt(i*4);
	    	if( elem==x ){ result=i; break; }
	    }
	    f.close();
	    return result;
	}//linearSearchMemoryMappedFile	
	
	static void creaFile( String nome ) throws IOException{
		DataOutputStream dos=new DataOutputStream( new FileOutputStream(nome) );
		boolean[] a=new boolean[n];
		int c=0, pos=0;
		Random r=new Random();
		while( c<n ){
			while(a[pos=r.nextInt(n)]) {
				dos.writeInt(pos);
				c++; a[pos]=true;
			}
		}
		dos.close();
	}//creaFile
	
}//FileSearching
