package RetiDiCalcolatori.ProvaScritta20giugno2020.SecondaParte;



import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.Buffer;
import java.util.Random;

public class ThreadHandlerCentralina extends Thread {

    private int tport=5000;
    private ServerSocket server;
    private Server gestore;

    private Random random=new Random();

public ThreadHandlerCentralina(Server gestore){
    this.gestore=gestore;

}

    public void run(){
        try{
            server=new ServerSocket(tport);
            while(true){
                Socket centralina=server.accept();
                BufferedReader br=new BufferedReader(new InputStreamReader(centralina.getInputStream()));

                //ho identificato la centralina
                String line=br.readLine();
                br.close();
                int idCentralina=Integer.parseInt(line);

                long timestamp=-1; //valore errato allora la centralina è già presente.
                //aggiungiamo la centralina
                if(gestore.addCentralina(idCentralina)){
                    timestamp=random.nextInt(0,61);
                }

                String msg=""+timestamp;
                PrintWriter pw=new PrintWriter(centralina.getOutputStream(),true);
                pw.write(msg);

                pw.close();
                centralina.close();

            }

        }catch(IOException e){
            e.printStackTrace();
        }
    }













}
