package RetiDiCalcolatori.ProvaScritta11_07_2019.Parte2.LatoClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Nave extends Thread{
    private final int id_Nave;
    private final int n_container;
    private final double lunghezza;
    private final static int tPort=3000;
    private final static int tPortS=4000;
    private final static String hostname="porto.unical.it";

    private Socket porto;
    private ServerSocket server;

    public Nave(int id_Nave,double lunghezza,int n_container){
        this.id_Nave=id_Nave;
        this.n_container=n_container;
        this.lunghezza=lunghezza;

    }

    public void run(){
        try{
            porto=new Socket(hostname,tPort);
            PrintWriter pw=new PrintWriter(porto.getOutputStream(),true);
            String msg=""+id_Nave+"#"+lunghezza+"#"+n_container;
            pw.write(msg);
            pw.close();
            porto.close();
            //Creo il thread di tempo media attesa
            Thread t1=new ThreadHandlerTempoAttesa(id_Nave);
            t1.start();
            //mi metto in attesa di una risposta
            server=new ServerSocket(tPortS);
            Socket connected=server.accept();
            BufferedReader bf=new BufferedReader(new InputStreamReader(connected.getInputStream()));
            msg=bf.readLine();
            //ho ricevuto la banchina chiudo il thread
            t1.interrupt();
            bf.close();
            server.close();

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public int getId_Nave() {
        return id_Nave;
    }

    public int getN_container() {
        return n_container;
    }

    public double getLunghezza() {
        return lunghezza;
    }

    public Socket getPorto() {
        return porto;
    }

    public ServerSocket getServer() {
        return server;
    }
}
