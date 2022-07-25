package poo.__APPELLI__.AppelloAnonimo;



import java.util.Iterator;

public abstract class ListaAstratta<T> implements Lista<T> {
  public String toString() {
    StringBuilder sb = new StringBuilder(100);
    sb.append("[");
    Iterator<T> it = iterator();
    while (it.hasNext()) {
      sb.append(it.next());
      if (it.hasNext())
        sb.append(", "); 
    } 
    sb.append("]");
    return sb.toString();
  }
  
  public boolean equals(Object o) {
    if (!(o instanceof Lista))
      return false; 
    if (o == this)
      return true; 
    Lista<T> l = (Lista<T>)o;
    if (size() != l.size())
      return false; 
    Iterator<T> it = iterator();
    Iterator<T> it2 = l.iterator();
    while (it.hasNext()) {
      if (!it.next().equals(it2.next()))
        return false; 
    } 
    return true;
  }
  
  public int hashCode() {
    int M = 43;
    int h = 0;
    for (T x : this)
      h = h * 43 + x.hashCode(); 
    return h;
  }
}