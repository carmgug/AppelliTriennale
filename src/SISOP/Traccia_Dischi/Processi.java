package SISOP.Traccia_Dischi;

import java.util.concurrent.TimeUnit;

public class Processi extends Thread{
    private String ID;
    private Controllore controllore;
    private int n_SharedDisk;
    private final static int t_Max=1000,t_Min=100;


    private void SvolgiAzione() throws InterruptedException {
        int tempo=(int)(Math.random()*(t_Max-t_Min+1))+t_Min;
        TimeUnit.MILLISECONDS.sleep(tempo);
    }

    private int[] ScegliDisco(){
        int Disco_1=(int) Math.random()*(n_SharedDisk-0)+0;
        boolean trovato=false;
        int Disco_2=-1;
        while(!trovato){
            Disco_2=(int) Math.random()*(n_SharedDisk-0)+0;
            if(!(Disco_2==Disco_1))trovato=true;
        }
        int[] res=new int[2];
        res[0]=Disco_1;res[1]=Disco_2;
        return res;
    }


    public void run(){
        try{
            while (true){
                int[] disk=ScegliDisco();
                controllore.allocaDischi(disk[0],disk[1]);
                SvolgiAzione();
                controllore.rilasciaDischi(disk[0],disk[1]);
            }

        }catch(InterruptedException e){}


    }





}
