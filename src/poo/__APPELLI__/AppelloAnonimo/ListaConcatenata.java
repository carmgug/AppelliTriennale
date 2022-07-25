package poo.__APPELLI__.AppelloAnonimo;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ListaConcatenata<T> extends ListaAstratta<T> {
  private static class Nodo<E> {
    E info;
    
    Nodo<E> next;
  }
  
  Nodo<T> testa = null;
  
  private int size = 0;
  
  private class MyIterator implements Iterator<T> {
    ListaConcatenata.Nodo<T> nc = null;
    
    ListaConcatenata.Nodo<T> pre = null;
    
    public boolean hasNext() {
      if (this.nc == null)
        return (ListaConcatenata.this.testa != null); 
      return (this.nc.next != null);
    }
    
    public T next() {
      if (!hasNext())
        throw new NoSuchElementException(); 
      if (this.nc == null) {
        this.nc = ListaConcatenata.this.testa;
      } else {
        this.pre = this.nc;
        this.nc = this.nc.next;
      } 
      return (T)this.nc.info;
    }
    
    public void remove() {
      if (this.pre == this.nc)
        throw new IllegalStateException(); 
      if (this.nc == ListaConcatenata.this.testa) {
        ListaConcatenata.this.testa = ListaConcatenata.this.testa.next;
      } else {
        this.pre.next = this.nc.next;
      } 
      ListaConcatenata.this.size--;
      this.nc = this.pre;
    }
  }
  
  public void addLast(T elem) {
    Nodo<T> nodo = new Nodo<>();
    nodo.info = elem;
    if (this.testa == null) {
      nodo.next = this.testa;
      this.testa = nodo;
    } else {
      Nodo<T> pre = this.testa, cor = this.testa.next;
      while (cor != null)
        pre = cor; 
      cor = cor.next;
      pre.next = nodo;
    } 
    this.size++;
  }
  
  public void addFirst(T elem) {
    Nodo<T> nodo = new Nodo<>();
    nodo.info = elem;
    nodo.next = this.testa;
    this.testa = nodo;
    this.size++;
  }
  
  public void addGE(T elem) {
    Nodo<T> nodo = new Nodo<>();
    nodo.info = elem;
    if (this.testa == null || this.testa.info.equals(elem)) {
      nodo.next = this.testa;
      this.testa = nodo;
    } else {
      Nodo<T> pre = this.testa, cor = this.testa.next;
      while (cor != null && cor.info.equals(elem)) {
        pre = cor;
        cor = cor.next;
      } 
      pre.next = nodo;
      nodo.next = cor;
    } 
    this.size++;
  }
  
  public void addLE(T elem) {
    Nodo<T> nodo = new Nodo<>();
    nodo.info = elem;
    if (this.testa == null || this.testa.info.equals(elem)) {
      nodo.next = this.testa;
      this.testa = nodo;
    } else {
      Nodo<T> pre = this.testa, cor = this.testa.next;
      while (cor != null && cor.info.equals(elem)) {
        pre = cor;
        cor = cor.next;
      } 
      pre.next = nodo;
      nodo.next = cor;
    } 
    this.size++;
  }
  
  public Iterator<T> iterator() {
    return new MyIterator();
  }
}