package RetiDiCalcolatori.ProvaScritta20_01_2021.SecondaParte.Thread;

import ProvaScritta20_01_2021.SecondaParte.Misura;
import ProvaScritta20_01_2021.SecondaParte.Server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ThreadHandleMisura extends Thread{


    private final static int uPort=4000;
    private final Server server;
    private DatagramSocket socket;


    public ThreadHandleMisura(Server server){
        this.server=server;

    }


    public void run(){
        try{
            socket=new DatagramSocket(uPort);
            byte[] buf=new byte[256];

            while(true){
                DatagramPacket p=new DatagramPacket(buf,buf.length);
                socket.receive(p);

                String msg=new String(p.getData());
                String[] split=msg.split("#");
                Misura misura=new Misura(Integer.parseInt(split[0]),Double.parseDouble(split[1]),Long.parseLong(split[2]));

                //aggiungi misura
                server.addMisura(Integer.parseInt(split[0]),misura);


            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }


}
