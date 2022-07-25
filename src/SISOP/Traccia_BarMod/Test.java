package SISOP.Traccia_BarMod;

public class Test {
    public static void main(String[] args){
        System.out.println("INIZIO GIORNATA DI LAVORO!!");
        BarSemafori locale=new BarSemafori();
        int num_persone=6;
        Cliente[] clienti=new Cliente[num_persone];
        for(int i=0;i<num_persone;i++){
            clienti[i]=new Cliente(Integer.toString(i), locale);
            clienti[i].start();
        }
        try {
            for (int i = 0; i < num_persone; i++) {
                clienti[i].join();
            }
        }catch(InterruptedException e){}

        System.out.println("FINE GIORNATA DI LAVORO!!");
    }
}
