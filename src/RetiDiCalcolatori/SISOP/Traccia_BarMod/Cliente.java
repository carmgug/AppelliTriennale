package RetiDiCalcolatori.SISOP.Traccia_BarMod;

public class Cliente extends Thread{

    private Bar locale;
    private String id;

    public Cliente(String id,Bar locale){
        this.locale=locale;
        this.id=id;
    }


    public void run(){
        try{
            int i=locale.scegli();
            locale.inizia(i);
            System.out.print("Il cliente "+id+" ha finito di ");
            if(i==0) System.out.println("PAGARE");
            else System.out.println("BERE");
            locale.finisci(i);
            System.out.print("Il cliente "+id+" ha finito di ");
            if(i==0) System.out.print("BERE");
            else System.out.print("PAGARE");
            System.out.println(" ed esce dal bar");
        }catch(InterruptedException e){};





    }




}
