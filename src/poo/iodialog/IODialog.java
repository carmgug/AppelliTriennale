//IOGrafico.java
package poo.iodialog;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class IODialog{
	public static void main( String[] args ){
		//Dimostrazione di input/output tramite dialog box di swing
		//JOptionPane.showInputDialog( prompt ) - per acquisire un dato (come stringa)
		//JOptionPane.showMessageDialog( parent, stringa messaggio ) - per mostrare una stringa messaggio
		//JOptionPane.showConfirmDialog( parent, messaggio domanda ) - per richiedere una risposta
		//JFileChooser e jfc.showOpenDialog( parent ) - per scegliere un file dal file system
		//molto spesso il parent è non significativo e si specifica null
		
		int x;
		String nomeFile;
        for(;;){
        	try{
        		String input=JOptionPane.showInputDialog("Fornire il valore intero di x");
        		x=Integer.parseInt(input);
        		break;
        	}catch( Exception e ){
        		JOptionPane.showMessageDialog(null/*parent*/,"Nessun intero. Ripetere..."/*String*/);
        	}		
		}
		//visualizzazione tramite un ShowMessageDialog
		JOptionPane.showMessageDialog(null,"x="+x);
        int i=-1;
		do{
			//confirmation dialog
			i=JOptionPane.showConfirmDialog(null,"Sei disposto a selezionare un file?");
			//i=0 se SI, 1 per NO 2 per Annulla
			if( i==1 /*NO*/ ) System.exit(-1);
			if( i!=0 ) JOptionPane.showMessageDialog(null,"Devi rispondere SI o NO ...");
		}while(i!=0);

		//il nomeFile si potrebbe leggere tranquillamente con un input dialog box
		//ma qui si mostra l'uso di un JFileChooser
		JFileChooser jfc=new JFileChooser();
		int val = jfc.showOpenDialog(null);
   		if( val == JFileChooser.APPROVE_OPTION ) {
			nomeFile = jfc.getSelectedFile().getAbsolutePath();
			JOptionPane.showMessageDialog(null,"Hai scelto il file: "+nomeFile);
		}
		else if( val == JFileChooser.CANCEL_OPTION ){
			JOptionPane.showMessageDialog(null,"Hai annullato la scelta del file");
		}

		/* e' possibile impostare un filtro sui tipi dei file su cui scegliere, al
		   tempo di creazione del JFileChooser */
   		do{
   			i=JOptionPane.showConfirmDialog(null,"Vuoi scegliere un file di tipo pdf o txt?");
   			if( i==1 /*NO*/ ) System.exit(-1);
   			if( i!=0 ) JOptionPane.showMessageDialog(null,"Devi rispondere SI o NO ...");
   		}while(i!=0);
   		
		jfc=new JFileChooser("c:\\poo-file");    
		FileNameExtensionFilter filtro = new FileNameExtensionFilter("Documenti PDF o TXT", "pdf", "txt");
		jfc.setFileFilter(filtro);

		val = jfc.showOpenDialog(null);
   		if( val == JFileChooser.APPROVE_OPTION ) {
			nomeFile=jfc.getSelectedFile().getName(); //solo il nome, senza path
			JOptionPane.showMessageDialog(null,"Hai scelto il file: " + nomeFile);
		}
		else if( val== JFileChooser.CANCEL_OPTION ){
			JOptionPane.showMessageDialog(null,"Hai annullato la scelta del file");
		}

   		JOptionPane.showMessageDialog(null,"Bye.");
   		
	}//main
}//IODialog