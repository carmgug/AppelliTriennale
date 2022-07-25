package RetiDiCalcolatori.ProvaScritta15_01_2020.Parte2.Main;

import ProvaScritta15_01_2020.Parte2.LatoClient.ThreadClient;

public class main2 {
    public static void main(String[] agrs){
        String richiesta="150600,67";
        ThreadClient client=new ThreadClient(richiesta);
    }
}
