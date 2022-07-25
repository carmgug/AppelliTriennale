package RetiDiCalcolatori.ProvaScritta29_03_2022.Parte2.ApplicazioneDiRete;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class ThreadAcquistaBiglietto extends Thread{

    private final Registro registro;
    private ServerSocket socket;
    private final static int tPort= 3000;

    public ThreadAcquistaBiglietto(Registro registro){
        this.registro=registro;
    }

    public void run(){
        try{
            this.socket=new ServerSocket(tPort);
            while(true){
                Socket client= socket.accept();
                ObjectInputStream ois=new ObjectInputStream(client.getInputStream());
                Richiesta richiesta= (Richiesta) ois.readObject();
                System.out.println("Effettuata Richiesta: "+richiesta);
                List<Biglietto> bAcquistati=registro.getBiglietti(richiesta);


                //Rispondi al client
                ObjectOutputStream out=new ObjectOutputStream(client.getOutputStream());
                out.writeObject(bAcquistati);
                out.flush();


                //Chiudi Tutto
                client.close(); ois.close(); out.close();
            }
        }catch(IOException | ClassNotFoundException e){
            e.printStackTrace();
        }


    }




}
