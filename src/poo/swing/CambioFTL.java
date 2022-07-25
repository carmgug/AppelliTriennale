//  Esempio di finestra creabile con Java AWT
//  che risponde all'evento di chiusura (si clicca
//  sul pulsante X o si digita ALT-F4 etc.) ed
//  ammette un pannello con due campi di testo
//  ed annesse etichette (label).
//  L'ascoltatore finestra reagisce agli
//  eventi azione che nascono quando si digita
//  invio in un campo di testo.
//*********************************************************

package poo.swing;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class FinestraCambioFTL extends JFrame{
   private JTextField euro, lire;
   private final float EURO_LIRE=1936.27f;
   private ActionListener al=
	( evt )->{
	      if( evt.getSource()==euro ){
	     	 String ae=euro.getText();
	     	 if( !ae.matches("[0-9]+(\\.[0-9]+)?") ){
	     		 euro.setText("WHAT?");
	     		 lire.setText("?");
	     		 return;    		 
	     	 }
	          double e=Double.parseDouble( ae );
	          euro.setText( String.format("%1.2f",e) );
	          double lit=Math.round(e*EURO_LIRE);
	          lire.setText( String.format("%1.0f",lit) );
	       }
	       else if( evt.getSource()==lire ){
	     	 String al=lire.getText();   	 
	     	 if( !al.matches("[0-9]+") ){
	     		 lire.setText("WHAT?");
	     		 euro.setText("?");
	     		 return;
	     	 }
	          double eur=Double.parseDouble( al )/EURO_LIRE;
	          euro.setText( String.format("%1.2f",eur) );
	       }
	 };
		   
   public FinestraCambioFTL(){
      setTitle("Cambio Euro-Lire");
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      JPanel p=new JPanel();
      p.add( new JLabel("Euro", JLabel.RIGHT) );
      p.add( euro=new JTextField("",12) );
      p.add( new JLabel("Lire", JLabel.RIGHT) );
      p.add( lire=new JTextField("",12) );
      add(p, BorderLayout.NORTH);
      JPanel q=new JPanel();
      q.add( new JLabel("1 Euro = 1936.27 Lire", JLabel.RIGHT) );
      add( q, BorderLayout.SOUTH );
      euro.addActionListener( al );
      lire.addActionListener( al );
      setLocation(400,400);
      setSize(450,100);
   }
}//FinestraCambioFTL

public class CambioFTL{
   public static void main(String []args){
      FinestraCambioFT fc=new FinestraCambioFT();
      fc.setVisible(true);
   }
}//CambioFTL
