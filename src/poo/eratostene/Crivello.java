package poo.eratostene;

public interface Crivello extends Iterable<Integer> {
	default int size() {
		int c=0;
		for( int x: this ) c++;
		return c;
	}
	void filtra();
}//Crivello
