package SISOP.Traccia_Dischi;

public abstract class Controllore {

    protected int n_sharedDisk;
    protected int[] Max_Memory;

    public Controllore(int n_sharedDisk,int max_M){
        this.n_sharedDisk=n_sharedDisk;
        Max_Memory=new int[n_sharedDisk];
        for(int i=0;i<n_sharedDisk;i++){
            Max_Memory[i]=max_M;
        }
    }

    protected abstract void allocaDischi(int a,int b) throws InterruptedException;
    protected abstract void rilasciaDischi(int a,int b);



}
