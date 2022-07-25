package RetiDiCalcolatori.ProvaScritta15_01_2020.Parte2.ThreadServer;

import ProvaScritta15_01_2020.Parte2.Gestore;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class ThreadTakeOfferta extends Thread{

    private final Gestore gestore;
    private ServerSocket server;
    private final static int tPort=3333;

    public ThreadTakeOfferta(Gestore s){
        this.gestore=s;


    }

    public void run() {
        try {
            server = new ServerSocket(tPort);
            while (true) {
                Socket cb = server.accept(); //centro benessere
                new GestoreOfferte(gestore,cb).start();
            }

        }catch (IOException e) {
            e.printStackTrace();
        }
    }


    class GestoreOfferte extends Thread {

        private final Gestore gestore;
        private final Socket cb;

        public GestoreOfferte(Gestore gestore,Socket cb) {
            this.gestore = gestore;
            this.cb=cb;
        }

        public void run() {
            try {
                BufferedReader bf = new BufferedReader(new InputStreamReader(cb.getInputStream()));
                String msg = bf.readLine();
                //arrivo <NumeroRichiesta>#<Offerta>
                String[] split = msg.split("#");
                int richiesta = Integer.parseInt(split[0]);
                String offerta = split[1];

                gestore.addOfferta(richiesta, offerta);

                bf.close();
                cb.close();
            }catch (IOException e) {
                e.printStackTrace();
            }

        }
    }



}
