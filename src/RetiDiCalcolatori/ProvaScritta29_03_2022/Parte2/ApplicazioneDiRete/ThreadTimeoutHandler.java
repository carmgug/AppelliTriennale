package RetiDiCalcolatori.ProvaScritta29_03_2022.Parte2.ApplicazioneDiRete;


import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;
import java.util.Random;

//Chiude le lotterie
public class ThreadTimeoutHandler extends Thread{
    private static final long TIMEOUT= 60000;
    private final static int bPort=3000;
    private final static String bAddress= "230.0.0.1";
    private final int idLotteria;
    private final String nomeLotteria;
    private final MulticastSocket mSocket;
    private final Registro registro;
    private Random GeneratoreNumeroVincente;

    public ThreadTimeoutHandler(String nomeLotteria,Registro registro) throws IOException {
        this.nomeLotteria = nomeLotteria;
        this.idLotteria = nomeLotteria.hashCode();
        this.registro = registro;
        this.mSocket = new MulticastSocket(bPort);

    }



    public void run(){
        try{
            //Sono stato avviato quindi è aperta la lotteria;
            sleep(TIMEOUT); //Questo perchè il server non si deve chiudere.
            registro.chiudiLotteria(idLotteria);
            System.out.println("Lotteria "+ nomeLotteria+ "chiusa!");
            //Estrai numero vincente
            estraiVincitore();
        }catch(InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void estraiVincitore() {
        try{
            int NumeroFortunato= GeneratoreNumeroVincente.nextInt(1,10001);


            System.out.format("Il NumeroFortunato della Lotteria %d è %d\n",nomeLotteria,NumeroFortunato);
            //InviailNumeroFortuanto
            String message="ESITO-"+this.nomeLotteria+"-"
                    +NumeroFortunato;
            byte[] buf=message.getBytes();
            InetAddress group= InetAddress.getByName(bAddress);
            DatagramPacket packet=new DatagramPacket(buf, buf.length, group,bPort);
            mSocket.send(packet);//Invio

        }catch(IOException e){
            e.printStackTrace();
        }

    }
}
