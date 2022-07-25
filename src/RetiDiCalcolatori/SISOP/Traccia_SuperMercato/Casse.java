package RetiDiCalcolatori.SISOP.Traccia_SuperMercato;

public abstract class Casse {

    protected int N_Casse;

    public Casse(int N_Casse){
        this.N_Casse=N_Casse;
    }

    protected abstract int getIdCassa() throws InterruptedException;
    protected abstract void consegnaProdotti(int id,int p) throws InterruptedException;
    protected abstract int segnalaCassaLibera(int id) throws InterruptedException;
    protected abstract void congedaCliente(int id);






}
