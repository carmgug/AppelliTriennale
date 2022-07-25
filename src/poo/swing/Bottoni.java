package poo.swing;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

class FinestraBottoni extends JFrame{
	private JButton bianco, rosso, verde;
	private Color c;
	private Pannello pannello;
	private Ascoltatore ascoltatore;
	
	class Ascoltatore implements ActionListener{
		public void actionPerformed( ActionEvent e ){
			if( e.getSource()==bianco ){				
				c=new Color(255,255,255); //RGB
				pannello.repaint();
			}
			else if( e.getSource()==rosso ){					
				c=new Color(255,0,0);
				pannello.repaint();
			}
			else if( e.getSource()==verde ){				
				c=new Color(0,255,0);
				pannello.repaint();
			}
		}
	}//Ascoltatore
	
	class Pannello extends JPanel{
		public void paintComponent( Graphics g ){
			super.paintComponent(g);		
			setBackground( c );
		}
	}//Pannello
	
	public FinestraBottoni(){
		setTitle("Finestra bottoni");
		setSize(300,300);
		setLocation(250,250);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //default
		pannello=new Pannello();
		ascoltatore=new Ascoltatore();
		bianco=new JButton("Bianco");
		rosso=new JButton("Rosso");
		verde=new JButton("Verde");
		bianco.addActionListener(ascoltatore);
		rosso.addActionListener(ascoltatore);
		verde.addActionListener(ascoltatore);
		pannello.add(bianco); pannello.add(rosso); pannello.add(verde);
		add(pannello, BorderLayout.CENTER);
	}
	
}//FinestraBottoni

public class Bottoni {
	public static void main( String []args ){
		JFrame f=new FinestraBottoni();
		f.setVisible(true);
	}
}//Bottoni
