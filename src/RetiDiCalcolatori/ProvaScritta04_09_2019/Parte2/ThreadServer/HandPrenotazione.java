package RetiDiCalcolatori.ProvaScritta04_09_2019.Parte2.ThreadServer;

import ProvaScritta04_09_2019.Parte2.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;

public class HandPrenotazione extends Thread{

    private final Server server;
    private final Socket paziente;
    private final static long start=8;
    private final static long end=12;

    public HandPrenotazione(Server server, Socket paziente){
        this.server=server;
        this.paziente=paziente;

    }

    public void run(){
        try{
            BufferedReader bf=new BufferedReader(new InputStreamReader(paziente.getInputStream()));
            String msg= bf.readLine();
            bf.close();
            //Ricevuto messaggio da paziente
            if(isActive()){
                String prenotazione=server.addPrenotazione(msg,paziente);

                if(!prenotazione.equals("non effettuata")){
                    PrintWriter pw=new PrintWriter(paziente.getOutputStream(),true);
                    pw.write(prenotazione);
                    pw.close();
                    paziente.close();
                }//prenotazione ok
                //gestire queue
                else{

                }
            }
            else{
                msg="service not available";
                PrintWriter pw=new PrintWriter(paziente.getOutputStream(),true);
                pw.write(msg);
                pw.close();
                paziente.close();
            }//servzio chiuso

        }catch (IOException e){
            e.printStackTrace();
        }

    }

    private boolean isActive(){
        Date date=new Date(System.currentTimeMillis());
        if(date.getTime()>=start && date.getTime()<=end){
            return true;
        }
        return false;
    }







}
