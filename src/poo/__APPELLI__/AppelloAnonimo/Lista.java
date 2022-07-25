package poo.__APPELLI__.AppelloAnonimo;

import java.util.Iterator;

public interface Lista<T> extends Iterable<T> {
  default int size() {
    int c = 0;
    for (T x : this)
      c++; 
    return c;
  }
  
  default void clear() {
    Iterator<T> it = iterator();
    while (it.hasNext()) {
      it.next();
      it.remove();
    } 
  }
  
  default boolean contains(T elem) {
    Iterator<T> it = iterator();
    while (it.hasNext()) {
      if (elem.equals(it.next()))
        return true; 
    } 
    return false;
  }
  
  void addLast(T paramT);
  
  void addFirst(T paramT);
  
  void addGE(T paramT);
  
  void addLE(T paramT);
  
  default void remove(T elem) {
    Iterator<T> it = iterator();
    while (it.hasNext()) {
      if (elem.equals(it.next()))
        it.remove(); 
    } 
  }
  
  default T removeLast() {
    Iterator<T> it = iterator();
    T elementorimosso = null;
    while (it.hasNext())
      elementorimosso = it.next(); 
    if (elementorimosso != null)
      it.remove(); 
    return elementorimosso;
  }
  
  default T removeFirst() {
    Iterator<T> it = iterator();
    T elementorimosso = null;
    if (it.hasNext())
      elementorimosso = it.next(); 
    if (elementorimosso != null)
      it.remove(); 
    return elementorimosso;
  }
}