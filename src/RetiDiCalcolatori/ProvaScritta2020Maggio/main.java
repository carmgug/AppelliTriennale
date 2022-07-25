package RetiDiCalcolatori.ProvaScritta2020Maggio;

public class main {
    public static void main(String[] args){
        Server server=new Server();
        Prodotto p1=new Prodotto(0,30,60000);
        Prodotto p2=new Prodotto(1,30,60000);
        Prodotto p3=new Prodotto(1,30,60000);

        server.addProdotto(p1);
        server.addProdotto(p2);
        server.addProdotto(p3);

        Cliente c1=new Cliente("Utente 1",p1);
        Cliente c2=new Cliente("Utente 2",p2);
        Cliente c3=new Cliente("Utente 3",p3);


    }
}
