package RetiDiCalcolatori.SISOP.Traccia_Palazzo;

import java.awt.desktop.SystemEventListener;
import java.util.concurrent.TimeUnit;

public class Operai extends Thread{

    private String ID;
    //tipo 0 1 2
    //0 costruzione
    //1 pulizie
    //2 controllo e verifica
    private int tipo;
    private Palazzo palazzo;

    private static int t_costruzione=20;//h
    private static int t_ripulire=10;
    private static int t_verifica=5;
    private int[] tempi=new int[3];

    public Operai(String ID,int tipo,Palazzo palazzo){
        this.ID=ID;
        this.tipo=tipo;
        this.palazzo=palazzo;
        tempi[0]=t_costruzione;
        tempi[1]=t_ripulire;
        tempi[2]=t_verifica;
    }



    private void lavora(int tempo) throws InterruptedException{
        TimeUnit.SECONDS.sleep(1);//ho messo 1 per test
    }



    public void run(){
        try{
            while(!palazzo.LavoroFinito()){
                palazzo.start(tipo);
                System.out.format("La squadra[%s] ha iniziato a lavorare op:%s%n", ID,tipo);
                lavora(tempi[tipo]);
                System.out.format("La squadra[%s] ha finito di lavorare op:%s%n", ID,tipo);
                palazzo.end(tipo);
            }
        }catch(InterruptedException e){

        }
    }



}
