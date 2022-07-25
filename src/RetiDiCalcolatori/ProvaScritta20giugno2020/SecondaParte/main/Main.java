package RetiDiCalcolatori.ProvaScritta20giugno2020.SecondaParte.main;

import ProvaScritta20giugno2020.SecondaParte.ClientVari.Centralina;
import ProvaScritta20giugno2020.SecondaParte.ClientVari.Client;
import ProvaScritta20giugno2020.SecondaParte.ClientVari.Client2;
import ProvaScritta20giugno2020.SecondaParte.Server;

public class Main {
    public static void main(String[] var){
        Server server=new Server();
        Centralina centralina=new Centralina(0,5000,6000);
        Client client=new Client(3000,0,"temperatura");
        Client2 client2=new Client2(4000,0,90,100);
    }
}
