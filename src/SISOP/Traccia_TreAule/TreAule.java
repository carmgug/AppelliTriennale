package SISOP.Traccia_TreAule;

public abstract class TreAule {

    //vi sono tre aule
    protected int[] Cap_Max=new int[3];

    public TreAule(){
        Cap_Max[0]=80;
        Cap_Max[1]=60;
        Cap_Max[2]=40;
    }

    protected abstract int entra() throws InterruptedException;
    protected abstract void lascia(int c) throws InterruptedException;
    protected abstract void faiEntrare() throws InterruptedException;




}
