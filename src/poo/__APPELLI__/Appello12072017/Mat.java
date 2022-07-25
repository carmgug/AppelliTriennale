package poo.__APPELLI__.Appello12072017;

public class Mat {
	
	
	
	
	
	
	public static  int rN(int x) {
		return rn(x);
	}
	
	private static int rn(int x) {
		int s=calcolocifre(x);
		
		if(s<9) return s;
		if(s==9) return 0;
		else return rn(s);
	}
	private static int calcolocifre(int number) {
		if(number<10) return number;
		int tmp=number,c=0;
		while(tmp>=10) {
			tmp=tmp/10;
			c++;
		}
		
		return tmp+calcolocifre(number-(int)(tmp*Math.pow(10, c)));
	}

	public static void main(String[] args) {
		System.out.println(rN(1987));
	}
	
	
}
