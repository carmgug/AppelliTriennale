package RetiDiCalcolatori.ProvaScritta20giugno2020.SecondaParte.ClientVari;

import ProvaScritta20giugno2020.SecondaParte.Dato;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

    private final static String ServerAddress="localhost";
    private final int tPort;
    private final int idCentralina;
    private final String grandezza;

    public Client(int tPort,int idCentralina,String grandezza){
        this.tPort=tPort;
        this.idCentralina=idCentralina;
        this.grandezza=grandezza;
        startRichiesta();
    }

    private void startRichiesta() {
        try{
            Socket socket=new Socket(ServerAddress,tPort);
            PrintWriter pw=new PrintWriter(socket.getOutputStream(),true);
            pw.write(idCentralina+"#"+grandezza);

            pw.close();

            ObjectInputStream ois=new ObjectInputStream(socket.getInputStream());
            Dato d=(Dato) ois.readObject();

            ois.close(); socket.close();


        }catch (IOException |ClassNotFoundException e){
            e.printStackTrace();
        }
    }






}
