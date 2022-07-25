package RetiDiCalcolatori.ProvaScritta2020Maggio;

import ProvaScritta29_03_2022.Parte2.ApplicazioneDiRete.ThreadTimeoutHandler;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.List;

public class ThreadHandlerTimeout extends Thread{


    private final static int uPort=4000;
    private DatagramSocket serverSocket;
    private Server server;
    private int codiceProdotto;

    public ThreadHandlerTimeout(Server server,int codiceProdotto){
        this.server=server;
        this.codiceProdotto=codiceProdotto;
    }

    public void run(){
        try{
        sleep(server.getTime(codiceProdotto));

        List<Offerta> offerte=server.chiudiAsta(codiceProdotto);
        Cliente vincitore=determinaVincitore(offerte);

            serverSocket=new DatagramSocket();
            byte[] buf=new byte[256];

            for(Offerta o: offerte) {
                InetAddress address= InetAddress.getByName(o.getCliente().getHostname());
                String msg=costruzioneMessaggio(o.getCliente(),vincitore);
                buf=msg.getBytes();
                //Invio ai client
                DatagramPacket packet = new DatagramPacket(buf, buf.length,address, uPort);
                serverSocket.send(packet);

                serverSocket.close();
            }
        }catch (IOException|InterruptedException e){
            e.printStackTrace();
        }

    }

    private Cliente determinaVincitore(List<Offerta> offerte){
        double max_value=-1;
        Cliente vincitore=null;
        for(Offerta o: offerte){
            if(o.getValue()>max_value){
                vincitore=o.getCliente();
                max_value=o.getValue();
            }
        }
        return vincitore;
    }

    private String costruzioneMessaggio(Cliente curr,Cliente vincitore){
        //due Clienti sono uguali se hanno il medesimo hostname;
        if(curr.getHostname()!=vincitore.getHostname())
            return "NON_VINCITORE_["+codiceProdotto+"]";
        else
            return "VINCITORE_["+codiceProdotto+"]";
    }




}
