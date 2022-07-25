package RetiDiCalcolatori.ProvaScritta11_07_2019.Parte2;

import ProvaScritta11_07_2019.Parte2.Porto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ThreadHandlerRichiesta extends Thread{

    private final Porto porto;
    private final Socket nave;


    public ThreadHandlerRichiesta(Porto porto,Socket nave){
        this.porto=porto;
        this.nave=nave;

    }

    public void run(){
        try{

            BufferedReader bf=new BufferedReader(new InputStreamReader(nave.getInputStream()));
            String msg=bf.readLine();

            String[] split=msg.split("#");
            int id_nave=Integer.parseInt(split[0]);
            double lunghezza=Double.parseDouble(split[1]);
            int n_container=Integer.parseInt(split[2]);

            int aggiunta=porto.addNave(id_nave,lunghezza,n_container);

            if(aggiunta!=-1){
               new ThreadHandlerScarico(porto,aggiunta,id_nave).start();
            }



        }catch (IOException e){

        }
    }


}
