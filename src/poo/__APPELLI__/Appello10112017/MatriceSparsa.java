package poo.__APPELLI__.Appello10112017;

public interface MatriceSparsa{
	int getN();
	void clear();
	default double grado() {
		int ContatoreNonNulli=0;
		int n=getN();
		for(int i=0;i<n;++i)
			for(int j=0;j<n;++j)
				if(get(i,j)!=0) ContatoreNonNulli++;
		return (ContatoreNonNulli/n)*100;
		
	}
	int get(int i,int j);
	void set(int i,int j,int v);
	MatriceSparsa crea();
	
	default MatriceSparsa add(MatriceSparsa m) {
		if(m.getN()!=getN()) throw new IllegalArgumentException();
		int n=getN();
		MatriceSparsa res=crea();
		for(int i=0;i<n;++i)
			for(int j=0;j<n;j++) {
				int v=get(i,j)+m.get(i, j);
				res.set(i, j, v);
			}	
		return res;
	}
	default MatriceSparsa mul(MatriceSparsa m) {
		if(m.getN()!=getN()) throw new IllegalArgumentException();
		int n=getN();
		MatriceSparsa res=crea();
		for(int i=0;i<n;++i)
			for(int j=0;j<n;++j)
				for(int k=0;k<n;++j) {
					int v=res.get(i, j)+get(i,k)+m.get(k, j);
					res.set(i, j, v);
				}
		
		
		return res;
	}
	default boolean simmetrica() {
		int n=getN();
		for(int i=0;i<n;++i) {
			for(int j=0;j<n;j++) {
				if(j==i) continue;
				else if(get(i,j)!=get(j,i)) return false;
			}
		}
		return true;
	}

}
