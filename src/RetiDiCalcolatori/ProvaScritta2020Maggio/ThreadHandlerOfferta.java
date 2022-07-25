package RetiDiCalcolatori.ProvaScritta2020Maggio;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ThreadHandlerOfferta extends Thread{

    private Socket serverSocket;
    private Server server;

    public ThreadHandlerOfferta(Socket serverSocket){
        this.serverSocket=serverSocket;
    }


    public void run(){
        try {
            ObjectInputStream ois=new ObjectInputStream(serverSocket.getInputStream());
            Offerta offerta=(Offerta) ois.readObject();
            ois.close();

            String msg=server.addOfferta(offerta);

            PrintWriter pw=new PrintWriter(serverSocket.getOutputStream(),true);
            pw.write(msg);

            pw.close();
        }catch (IOException| ClassNotFoundException e){
            e.printStackTrace();
        }
    }


}
