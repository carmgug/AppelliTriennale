package poo.__APPELLI__.Appello10112017;

public abstract class MatriceSparsaAstratta implements MatriceSparsa{

	
	
	public boolean equals(Object o) {
		if(!(o instanceof MatriceSparsa)) return false;
		if(o==this) return true;
		MatriceSparsa m2=(MatriceSparsa) o;
		if(getN()!=m2.getN())return false;
		int n=getN();
		for(int i=0;i<n;++i) {
			for(int j=0;j<n;++j) {
				if(this.get(i, j)!=m2.get(i, j)) return false;
			}
		}
		return true;
	}
	
	public String toString() {
			StringBuilder sb=new StringBuilder();
			int n=getN();
			for(int i=0;i<n;++i) {
				sb.append("[");
				for(int j=0;j<n;++j) {
					if(j!=n-1) sb.append(this.get(i, j)+", ");
					else sb.append(this.get(i, j));
				}
				sb.append("]\n");
			}
			return sb.toString();
	}
	
	
	public int hashCode() {
		final int M=83;
		int h=0;
		int n=getN();
		for(int i=0;i<n;++i) {
			for(int j=0;j<n;++j) {
				h+=h*M+((Integer)get(i, j)).hashCode();
				
			}
		}
		return h;
	}
}
