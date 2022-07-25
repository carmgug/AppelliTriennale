package RetiDiCalcolatori.ProvaScritta20giugno2020.SecondaParte.ClientVari;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Centralina {

    private final int idCentralina;
    private final int tPort;
    private final int uPort;

    public Centralina(int idCentralina,int tPort,int uPort){
        this.idCentralina=idCentralina;
        this.tPort=tPort;
        this.uPort=uPort;
        startCentralina();
    }

    public void startCentralina(){
        try{
            Socket socket=new Socket("locahost",tPort);
            PrintWriter pw=new PrintWriter(socket.getOutputStream(),true);
            pw.write(((Integer)idCentralina).toString());

            pw.close();

            BufferedReader bf=new BufferedReader(new InputStreamReader(socket.getInputStream()));

            long time=Long.parseLong(bf.readLine());
            bf.close();
            new ThreadGestoreCentralina(idCentralina,time,uPort);
            socket.close();

        }catch(IOException e){
            e.printStackTrace();
        }


    }



}
