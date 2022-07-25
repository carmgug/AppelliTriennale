package poo.__APPELLI__.Appello17072015;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class InsiemeMap<T> extends InsiemeAstratto<T>{

	
	HashMap<Integer,T> mappa;
	
	public InsiemeMap() {
		mappa=new HashMap<>();
		
	}
	
	
	public int size() {
		return mappa.size();
	}
	//shallow-copy
	public InsiemeMap(Insieme<T> Set) {
		int i=-1;
		Iterator<T> it=Set.iterator();
		while(it.hasNext()) {
			T elem=it.next();i++;
			mappa.put(i, elem);
		}
		
		
	}
	
	
	//immaginiamo che la mappa abbiama una chiave integer che va da 0-size-1;
	//quindi la nuova chiave(che non esiste sicuramente) sara proprio l'intero size
	@Override
	public void add(T x) {
		int key=mappa.size();
		mappa.put(key, x);
		
	}
	
	public void remove(T x) {
		Set<Integer> set=mappa.keySet();
		for(Integer i:set) {
			if(mappa.get(i).equals(x)) {
				mappa.remove(i);
				return;
			}
		}
		
		
		
	}
	
	public Iterator<Integer> iteratorOnKey(){
		return mappa.keySet().iterator();
	}

	@Override
	public Iterator<T> iterator() {
		throw new RuntimeException("Operazione Non Supportata");
	}
	

}
