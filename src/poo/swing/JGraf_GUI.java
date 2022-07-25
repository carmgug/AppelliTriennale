package poo.swing;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;
import java.io.*;

class Punto implements Serializable{
   /**
	 * Memorizza le informazioni di un punto sul pannello grafico
	 * utilizzato per tracciare un grafo.
	 * Il punto ha coordinate x e y e rappresenta il centro
	 * di un nodo (o vertice) del grafo. 
	 */
   private static final long serialVersionUID = 1L;
   private int x, y, peso;
   public Punto( int x, int y, int peso ){
	   this.x=x; this.y=y;
	   this.peso=peso;
   }
   public Punto( int x, int y ){
	   this(x,y,0);
   }
   public int getX(){ return x; }
   public int getY(){ return y; }
   public int getPeso(){ return peso; }
   public void setX( int x ){ this.x=x; }
   public void setY( int y ){ this.y=y; }
   public void setPeso( int peso ){ this.peso=peso; }
   public String toString(){
	   return "<"+x+","+y+","+peso+">";
   }
   public boolean equals( Object o ){
	   if( !(o instanceof Punto) ) return false;
	   Punto p=(Punto)o;
	   return this.x==p.x && this.y==p.y;
   }
}//Punto

class Arco implements Iterable<Punto>, Serializable{
	/**
	 * Memorizza le informazioni grafiche di un arco.
	 * Oltre al nodo sorgente (source) e al nodo destinazione (dest),
	 * mantiene il peso dell'arco, una lista di punti
	 * intermedi introdotti durante il tracciamento grafico
	 * (mediante click destri del mouse), e un oggetto
	 * freccia (Arrow) utile quando l'arco e' orientato.
	 */
	private static final long serialVersionUID = 1L;
	private Punto source, dest;
	private long peso;
	private java.util.LinkedList<Punto> punti=new java.util.LinkedList<Punto>();
	private boolean orientato=false, pesato=false;
	private Arrow arrow;
	public Punto getSource(){ return source; }
	public Punto getDest(){ return dest; }
	public long getPeso(){ return peso; }
	public boolean orientato(){ return orientato; }
	public boolean pesato(){ return pesato; }
	public void setSource( Punto source ){ this.source=source; }
	public void setDest( Punto dest ){ this.dest=dest; }
	public void setPeso( long peso ){ this.peso=peso; pesato=true; }
	public void setOrientato( boolean orientato ){ this.orientato=orientato; }
	public Arrow getArrow(){ return arrow; }
	public void setArrow( Arrow arrow ){ this.arrow=arrow; }
	public String toString(){ return "Arco da "+source+" a "+dest+" di peso "+peso;	}
	public void addPunto( Punto p ){ punti.add( p ); }
	public void removePunto( Punto p ){ punti.remove( p ); }
	public void clear(){ punti.clear(); }
	public java.util.Iterator<Punto> iterator(){ return punti.iterator(); }
}//Arco

class Arrow {	
	enum ArrowHead {
	   HEIGHT(5), WIDTH(5);
       int n;
	   ArrowHead(int n) {this.n = n;}
	   public int value() {return n;}
    }	
    private Point start;
    private Point end;
    private Polygon arrowHead;

    public Arrow( Point start, Point end ) {
        this.start = start;
        this.end = end;
        double direction = Math.atan2(end.y - start.y, end.x - start.x);
        arrowHead = new Polygon();
        arrowHead.addPoint(0, 0);
        Point p1 = rotate(ArrowHead.WIDTH.value()/2, 
        		          ArrowHead.HEIGHT.value(), direction);
        arrowHead.addPoint(p1.x, p1.y);
        Point p2 = rotate(-ArrowHead.WIDTH.value()/2, 
        		           ArrowHead.HEIGHT.value(), direction);
        arrowHead.addPoint(p2.x, p2.y);
        arrowHead.addPoint(0, 0);
        arrowHead.translate(end.x, end.y);
    }

    public Point rotate( int x, int y, double dir ){
        Point p = new Point();
        double r = Math.sqrt(x*x + y*y);
        double theta = Math.atan2(y, x);
        p.setLocation( Math.round(r*Math.cos(theta + dir + Math.PI/2)),
                       Math.round(r*Math.sin(theta + dir + Math.PI/2)) );
        return p;
    }//rotate

    public Point getStart(){ return start; }
    public Point getEnd(){ return end; }
    
    public void draw( Graphics2D g ) {
        g.drawLine(start.x, start.y, end.x, end.y);
        g.drawPolygon(arrowHead);
        g.fillPolygon(arrowHead);
    }
}//Arrow

class FinestraJGraf extends JFrame{
   private static final long serialVersionUID = 1L;
   private static final int SIZE=4; //diametro di un cerchietto-nodo
   private static int conta_nodo=0;

   private java.util.List<Punto> nodi=new java.util.ArrayList<Punto>();
   private java.util.List<Arco> archi=new java.util.ArrayList<Arco>();
 
   private int current;
   private boolean arco_pending=false;
   private Punto source, dest;
   private Arco currentArc;
   private boolean grafo_orientato=true;//true se il grafo e' orientato
   private boolean grafo_pesato=true;//true se il grafo e' pesato

   private Pannello panel; //ricopre la finestra per la visualizzazione

   public FinestraJGraf(){
      setTitle("JGraf GUI");
      add( panel=new Pannello(), "Center" );
      MouseHandler m;
      panel.addMouseListener( m=new MouseHandler() );
      panel.addMouseMotionListener( m );
      addWindowListener( new WindowAdapter() {
         public void windowClosing(WindowEvent e){
            System.exit(0);
         }
      } );
      pack();
      setSize(300,300);
   }//FinestraJGraf

   class Pannello extends JPanel{//inner class
	   private static final long serialVersionUID = 1L;
	   BasicStroke stroke=new BasicStroke(1);
	   public void paintComponent(Graphics g){
		   super.paintComponent(g);
		   Graphics2D g2=(Graphics2D)g;

		   //contesto prima della visualizzazione
		   Stroke oldStroke = g2.getStroke();
           Color oldColor = g2.getColor();
           g2.setStroke(stroke);
           g2.setColor(Color.black);
         
           //traccia nodi
           for( int i=0; i<nodi.size(); i++ ){
              Punto p=nodi.get(i);
              Ellipse2D e=new Ellipse2D.Double( p.getX()-SIZE/2, 
            		                            p.getY()-SIZE/2, SIZE, SIZE );
              g2.draw( e );
              g2.drawString( ""+p.getPeso(), p.getX()-10, p.getY()+4 );
           }
         
           //traccia archi
           for( int i=0; i<archi.size(); i++ ){
              Arco a=archi.get(i);
              //arco segmentato
              java.util.Iterator<Punto> it=a.iterator();
              if( !it.hasNext() )
            	 g2.drawLine(a.getSource().getX(), a.getSource().getY(), 
            			     a.getDest().getX(), a.getDest().getY());
              else{
            	 Punto pre=a.getSource();
            	 while( it.hasNext() ){
            		 Punto cor=it.next();
            		 g2.drawLine(pre.getX(), pre.getY(), cor.getX(), cor.getY());
            		 pre=cor;
            	 }
            	 g2.drawLine( pre.getX(), pre.getY(), 
            			      a.getDest().getX(), a.getDest().getY() );
              }
              if( grafo_orientato ){
            	 Punto primo=a.getSource();
        		 Punto ultimo=a.getDest();
           		 java.util.Iterator<Punto> ite=a.iterator();
        		 if( ite.hasNext() ){
        			ultimo=ite.next();
        			if( ite.hasNext() ){
        			    primo=ultimo;
        				ultimo=ite.next();
        			}
        		 }
            	 if( a.pesato() ){   		 
             	    //calcola punto medio e mostra peso
            		int mx=(primo.getX()+ultimo.getX())/2;
            		int my=(primo.getY()+ultimo.getY())/2;
            		g2.drawString(""+a.getPeso(), mx, my);
            	 }//if a.pesato
            	 
            	 //mostra freccia
            	 a.getArrow().draw(g2);
            	 
             }//if grafo_orientato
             
         }//for su archi
         //ripristina contesto precedente a repaint
         g2.setStroke(oldStroke);
         g2.setColor(oldColor);
    }//paintComponent
   }//Pannello

   class MouseHandler extends MouseAdapter implements MouseMotionListener{
      
	   public void mousePressed(MouseEvent e){
         int x=e.getX();
         int y=e.getY();
		 current=find(x,y);
		   
		 if( e.getButton()==MouseEvent.BUTTON1 ){//bottone sinistro
			 if( current<0 ){//fuori da qualsiasi nodo
				 add(x,y,conta_nodo);
				 conta_nodo++;
			 }
			 arco_pending=false;
			 currentArc=null;
		 }
         else if( e.getButton()==MouseEvent.BUTTON3 ){//bottone destro
        	 //segna inizio, o punto intermedio o concludi/modifica un arco
        	 if( arco_pending ){ //concludi arco o segna punto intermedio
        		 if( current!=-1 ){//cursore su nodo finale
        			 currentArc.setDest( nodi.get(current) );      		 
        			 if( grafo_pesato ){
        				 String option=JOptionPane.showInputDialog("peso arco (default 0)");
        				 if( option==null || option.length()==0 ) currentArc.setPeso( 0 );
        				 else currentArc.setPeso( Long.parseLong( option ) );
        			 }
        			 if( grafo_orientato ){
        	        	 Punto primo=currentArc.getSource();
                		 Punto ultimo=currentArc.getDest();
                   		 java.util.Iterator<Punto> ite=currentArc.iterator();
                		 while( ite.hasNext() ){
               				 primo=ite.next();
                 		 }
        				 currentArc.setArrow( 
        					new Arrow( new Point(primo.getX(),primo.getY()), 
        							   new Point(ultimo.getX(),ultimo.getY())) );
        			 }
        			 arco_pending=false;
        			 boolean duplicato=false;
        			 for( Arco a: archi ){//verifica arco duplicato
        				 if( a.getSource().equals(currentArc.getSource()) &&
        					 a.getDest().equals(currentArc.getDest()) ){
        					 a.setPeso( currentArc.getPeso() );
        					 duplicato=true; break;  
        				 }
        				 if( !grafo_orientato && 
        					 a.getSource().equals(currentArc.getDest()) &&
        					 a.getDest().equals(currentArc.getSource()) ){
        					 a.setPeso( currentArc.getPeso() );
        					 duplicato=true; break;      					 
        				 }
        			 }//for
        			 if( !duplicato ) archi.add(currentArc);
        			 currentArc=null; //reset currentArc
        			 repaint();
        		 }
        		 else{//punto intermedio
        			 currentArc.addPunto( new Punto(x,y,0) );
        		 }
        	 }
        	 else{ //segna possibile inizio arco
        		 if( current!=-1 ){//mouse su un nodo
        			 arco_pending=true;
        			 currentArc=new Arco();
        			 currentArc.setOrientato( grafo_orientato );
        			 currentArc.setSource( nodi.get(current) );
        		 }
         	 }
         }
      }//mousePressed
      
      public void mouseClicked(MouseEvent e){
         if( e.getClickCount()>=2 ) delete( current );
      }//mouseClicked

      public void mouseMoved(MouseEvent e){
         int x=e.getX();
         int y=e.getY();
         if( find(x,y)>=0 )
            setCursor( Cursor.getPredefinedCursor
                     ( Cursor.CROSSHAIR_CURSOR ) );
         else
            setCursor( Cursor.getDefaultCursor() );
      }//mouseMoved
      
      public void mouseDragged(MouseEvent e){
    		 int x=e.getX();
    		 int y=e.getY();
    		 current=find(x,y);
    		 if( current>=0 ){
    			 Punto p=nodi.get(current); 
    			 Punto nuovo=new Punto(x,y,p.getPeso());
    			 nodi.remove(current);
    			 nodi.add( nuovo );
    			 //aggiorna source/dest archi dipendenti
    			 for( Arco a: archi ){
      				 if( grafo_orientato && 
    				     a.getArrow().getEnd().equals(new Point(p.getX(),p.getY())) ){
    					 //aggiorna freccia
    					 a.setArrow( new Arrow( a.getArrow().getStart(), 
    							                new Point(nuovo.getX(),nuovo.getY())));
    				 }
     				 if( grafo_orientato &&
     					 a.getArrow().getStart().equals(new Point(p.getX(),p.getY())) ){
    					 //aggiorna freccia
    					 a.setArrow( new Arrow( new Point(nuovo.getX(),nuovo.getY()), 
    							                a.getArrow().getEnd()));
    				 }
       				 if( a.getSource().equals(p) ){
    					 a.setSource( nuovo );
    				 }
       				 if( a.getDest().equals(p) ){
    					 a.setDest( nuovo );
    				 }
    			 }
    			 repaint();
    		 }
      }//mouseDragged
      
   }//MouseHandler

   int find( int x, int y ){
      for( int i=0; i<nodi.size(); i++ ){
         Punto p=nodi.get(i);
         if( x>=p.getX()-SIZE/2 && x<=p.getX()+SIZE/2 &&
             y>=p.getY()-SIZE/2 && y<=p.getY()+SIZE/2 ) return i;
      }
      return -1;
   }//find
   
   void add( int x, int y, int w ){
      nodi.add( new Punto(x,y,w) );
      panel.repaint();
   }//add

   void delete( int n ){
      if( n>=0 && n<nodi.size() ){
    	 //rimuovi archi che hanno questo nodo come source o dest
         java.util.Iterator<Arco> it=archi.iterator();
         while( it.hasNext() ){
        	 Arco a=it.next();
        	 if( a.getSource().equals( nodi.get(n) ) || 
        		 a.getDest().equals( nodi.get(n) ) ){
        		 it.remove();
        	 }
         }
         //togli il nodo
         nodi.remove(n);
         panel.repaint();
      }
   }//delete
   
}//FinestraJGraf

public class JGraf_GUI{
   public static void main( String []args ){
      FinestraJGraf f=new FinestraJGraf();
      f.setVisible(true);
   }//main
}//JGraf_GUI
