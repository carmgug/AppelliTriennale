package RetiDiCalcolatori.ProvaScritta2020Maggio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Socket;
import java.util.Random;

public class Cliente extends Thread {

    private String hostname;
    private final static String hostnameServer="aste.unical.it";
    private final static int uPort=4000;
    private final static int tPort=3000;
    private DatagramSocket datagramSocket;
    private Socket socket;
    private Offerta offerta;
    private Random random=new Random();

    public Cliente(String hostname,Prodotto p){
        this.hostname=hostname;
        this.offerta=new Offerta(p,this, random.nextDouble(0,150));
    }


    public String getHostname() {
        return hostname;
    }



    public void run(){
        try{
            socket=new Socket(hostnameServer,tPort);
            ObjectOutputStream oos=new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(offerta);

            oos.close();

            BufferedReader bf=new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String msg=bf.readLine();
            bf.close();

            if(tuttoOk(msg)){
                datagramSocket=new DatagramSocket(uPort);
                byte[] buf=new byte[256];

                DatagramPacket datagram=new DatagramPacket(buf,buf.length);
                datagramSocket.receive(datagram);

                msg=new String(datagram.getData());

                if(sonoVincitore(msg)){
                    System.out.println("HO VINTO SONO"+hostname);
                }



            }

        }catch (IOException e){
            e.printStackTrace();
        }

    }

    private boolean tuttoOk(String msg){
        if(msg.equals("OK")) return true;
        return false;
    }
    private boolean sonoVincitore(String msg){
        if(msg.equals("VINCITORE_["+offerta.getProdotto().getCodice()+"]")) return true;
        return false;
    }








}
