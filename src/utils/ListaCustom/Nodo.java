package Collezioni;

class Nodo<E>{
    //CAMPI
    private E dato;
    private Nodo<E> next;

    //COSTRUTTORI
    public Nodo(E dato, Nodo<E> next){
        this.next = next;
        this.dato = dato;
    }
    
    //METODI
    public void setNext(Nodo<E> next){this.next = next;}
    public void setDato(E dato){this.dato = dato;}
    public Nodo<E> getNext(){return next;}
    public E getDato(){return dato;}
}
