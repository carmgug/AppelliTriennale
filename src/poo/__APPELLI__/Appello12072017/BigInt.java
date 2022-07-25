package poo.__APPELLI__.Appello12072017;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;

public class BigInt {
	
	static int ZERO=0,UNO=1,DUE=2;
	final static String Regex="[0-9]+";	
	ArrayList<Integer> Numero;
	public BigInt(String s) {
		if(!s.matches(Regex)) throw new IllegalArgumentException();
		Numero=new ArrayList<>();
		StringTokenizer st=new StringTokenizer(s,"\\d",true);
		while(st.hasMoreTokens()) {
			String Cifra=st.nextToken();
			Numero.add(Integer.parseInt(Cifra));
		}
	}
	
	public BigInt(BigInt b) {
		Numero=new ArrayList<>();
		for(Integer x:b.Numero) {
			Numero.add(x);
		}
	}
	
	private BigInt() {
		Numero=new ArrayList<>();
	}
	
	
	private BigInt(int zero_uno_due) {
		Numero=new ArrayList<>();
		Numero.add(zero_uno_due);
		
	}
	
	public BigInt add(BigInt b) {
		BigInt res=new BigInt();
		int i=Numero.size()-1,j=b.Numero.size()-1;
		int riporto=0;
		while(i>=0 && j>=0) {
			int cifra1=Numero.get(i),cifra2=b.Numero.get(j);
			int x=cifra1+cifra2+riporto;
			if(x>=10) {riporto=1;x=x%10;}
			else riporto=0;
			res.Numero.add(0, x);
			i--;j--;
		}
		//gestione residui
		while(i>=0) {
			int cifra=Numero.get(i);
			int x=cifra+riporto;
			if(x>=10) {riporto=1;x=x%10;}
			else riporto=0;
			res.Numero.add(0, x);
			i--;
		}
		while(j>=0) {
			int cifra=b.Numero.get(j);
			int x=cifra+riporto;
			if(x>=10) {riporto=1;x=x%10;}
			else riporto=0;
			res.Numero.add(0,x);
			j--;
		}
		if(riporto!=0) res.Numero.add(0,riporto);
		
		
		return res;
	}
	
	
	public BigInt sub(BigInt b) {
		//this.compareTo(b)<0 thorw new IllegalArgumentException
		//this.compareTo(b)==0 return 0;
		BigInt res=new BigInt();
		int i=Numero.size()-1,j=b.Numero.size()-1;
		int resto=0;
		while(i>=0 && j>=0) {
			int cifra1=Numero.get(i),cifra2=b.Numero.get(j);
			int x=cifra1-cifra2-resto;
			if(x<0) {resto=1;x=x+10;}
			else resto=0;
			res.Numero.add(0,x);
			i--;j--;
		}
		//gestione residui
		while(i>=0) {
			int cifra=Numero.get(i);
			int x=cifra-resto;
			if(x<0) {resto=1;x=x+10;}
			else resto=0;
			res.Numero.add(0,x);
			i--;
		}
		while(j>=0) {
			int cifra=b.Numero.get(j);
			int x=cifra-resto;
			if(x<0) {resto=1;x=x+10;}
			else resto=0;
			res.Numero.add(0,x);
			j--;
		}
		return res;
	}
	
	public BigInt mul(BigInt b) {
		BigInt ris=new BigInt(ZERO);
		BigInt uno=new BigInt(UNO);BigInt zero=new BigInt(ZERO);
		while(!b.equals(zero)) {
			ris=ris.add(this);
			b=b.sub(uno); //non modifico b ma il riferimento
		}
		return ris;
	}
	
	public BigInt div(BigInt b) {
		BigInt a=new BigInt(this);
		BigInt Q=new BigInt(ZERO);
		BigInt uno=new BigInt(UNO),zero=new BigInt(ZERO);
		while(!a.equals(zero)) {
			a=a.sub(b);
			Q=Q.add(uno);
		}
		return Q;
	}
	public String toString() {
		StringBuilder sb=new StringBuilder();
		sb.append("Valore BigInt = ");
		Iterator<Integer> it=Numero.iterator();
		while(it.hasNext()) {
			sb.append(it.next());
		}
		
		return sb.toString();
	}
	public int hashCode() {
		final int M=43;
		int h=0;
		for(Integer x:Numero) {
			h+=h*M+x.hashCode();
		}
		return h;
	}
	
	public boolean equals(Object o) {
		if(!(o instanceof BigInt)) return false;
		if(o==this) return true;
		BigInt Numero2=(BigInt) o;
		if(Numero.size()!=Numero2.Numero.size()) return false;
		Iterator<Integer> it=Numero.iterator(),it2=Numero2.Numero.iterator();
		while(it.hasNext()) {
			Integer cifra1=it.next();
			Integer cifra2=it2.next();
			if(cifra1!=cifra2) return false;
		}
		return true;
	}
	
	public int compareTo(BigInt b) {
		if(b.Numero.size()>Numero.size()) return -1;
		if(b.Numero.size()==Numero.size()) {//probabilmente uguali
			Iterator<Integer> it=Numero.iterator(),it2=b.Numero.iterator();
			while(it.hasNext()) {
				Integer cifra1=it.next(),cifra2=it.next();
				if(cifra1>cifra2) return 1;
				else if(cifra1<cifra2) return -1;
			}
			return 0;//non sono usciti durante il while allora sono uguali
		}
		return 1; //se non � pi� piccolo o uguale allora � pi� grande;
	}
	
	public static void main(String[] args) {
		
		
		
		int exp=80;
		BigInt Numero=new BigInt("2");
		BigInt Res=new BigInt("1");
		while(exp>0) {
			Res=Res.mul(Numero);
			exp--;
		}
		System.out.println(Res);
		BigInteger Res2=new BigInteger("2");
		System.out.println("Valore BigInt = "+Res2.pow(80));
		
	}
	

}
