package poo.__APPELLI__.AppelloAnonimo;


import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

public class ListaConcatenataJ<T> extends ListaAstratta<T> {
  LinkedList<T> lista = new LinkedList<>();
  
  public void addLast(T elem) {
    this.lista.addLast(elem);
  }
  
  public void addFirst(T elem) {
    this.lista.addLast(elem);
  }
  
  public void addGE(T elem) {
    ListIterator<T> it = this.lista.listIterator();
    boolean trovato = false;
    while (it.hasNext()) {
      T elemcurr = it.next();
      if (elemcurr.equals(elem)) {
        trovato = true;
        break;
      } 
    } 
    if (trovato) {
      it.previous();
      it.add(elem);
    } else {
      it.add(elem);
    } 
  }
  
  public void addLE(T elem) {
    ListIterator<T> it = this.lista.listIterator();
    boolean trovato = false;
    while (it.hasNext()) {
      T elemcurr = it.next();
      if (elemcurr.equals(elem)) {
        trovato = true;
        break;
      } 
    } 
    if (trovato) {
      it.previous();
      it.add(elem);
    } else {
      it.add(elem);
    } 
  }
  
  public Iterator<T> iterator() {
    return this.lista.iterator();
  }
}