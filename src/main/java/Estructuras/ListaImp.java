package Estructuras;

import java.sql.SQLOutput;
import java.util.Iterator;

public class ListaImp<T extends Comparable<T>> implements Lista<T> {

    protected NodoLista<T> inicio;
    protected NodoLista<T>fin;
    protected int largo=0;

    public ListaImp() {
        this.inicio = null;

    }

    public NodoLista<T> getInicio() {
        return inicio;
    }

    public NodoLista<T> getFin() {
        return fin;
    }

    public int getLargo() {
        return largo;
    }

    public void setInicio(NodoLista<T> inicio) {
        this.inicio = inicio;
    }

    public void setFin(NodoLista<T> fin) {
        this.fin = fin;
    }

    public void setLargo(int largo) {
        this.largo = largo;
    }

    public void insertarOrd(T dato){
        NodoLista<T>nodo=new NodoLista<>(dato);
        if(this.esVacia()){
            this.insertarInicio(dato);
        }
        else{
            NodoLista<T>nodoActual=this.inicio;
            NodoLista<T>anterior=null;
            while(nodoActual!=null && nodoActual.getDato().compareTo(nodo.getDato())<0){
                anterior=nodoActual;
                nodoActual=nodoActual.getSig();
            }

            if(anterior==null){
                this.insertarInicio(dato);

            }
            else{
                nodo.setSig(nodoActual);
                anterior.setSig(nodo);
                largo++;
            }
        }

    }
    public void insertarInicio(T dato) {

        NodoLista<T> nodo= new NodoLista<T>(dato);
        if(this.esVacia())
        {

            this.setInicio(nodo);
            this.setFin(nodo);
            largo++;

        }
        else
        {

            nodo.setSig(inicio);
            this.setInicio(nodo);
            largo++;

        }

    }

    public void insertarDos(T dato) {
        inicio = new NodoLista<T>(dato, inicio);
        largo++;
    }
    public void insertar(T dato) {
        NodoLista<T> nuevoNodo = new NodoLista<T>(dato);

        if (inicio == null) {
            inicio = new NodoLista<T>(dato, inicio);
            largo++;
            return;
        }

        if (dato.compareTo(inicio.getDato()) < 0) {
            NodoLista<T> aux = inicio;
            inicio = new NodoLista<>(dato, inicio);
            inicio.setSig(aux);
            largo++;
            return;
        }

        NodoLista<T> actual = inicio;
        NodoLista<T> anterior = null;

        while (actual != null && dato.compareTo(actual.getDato()) > 0) {
            anterior = actual;
            actual = actual.getSig();
        }

        if (actual == null) {
            anterior.setSig(nuevoNodo);
        } else {
            nuevoNodo.setSig(actual);
            if (anterior != null) {
                anterior.setSig(nuevoNodo);
            } else {
                inicio = nuevoNodo;
            }
        }
        largo++;
    }

    //verificar si esta bien este existe
    public boolean existe(T elemento){
        return existe(this.inicio,elemento);
    }
    private boolean existe(NodoLista<T> nodo,T elemento){
        if(nodo==null){
            return false;
        }
        if(nodo.getDato().compareTo(elemento)==0){
            return true;
        }
        return existe(nodo.getSig(),elemento);
    }



    @Override
    public int largo() {
        return largo;
    }

    @Override
    public boolean esVacia() {
        return largo == 0;
    }

    public void mostrarLista(){
        NodoLista<T> nodo=this.inicio;
        while(nodo!=null){
            System.out.println(nodo.getDato().toString());
            nodo=nodo.getSig();

        }
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            private NodoLista<T> aux = inicio;

            @Override
            public boolean hasNext() {
                return aux != null;
            }

            @Override
            public T next() {
                T dato = aux.getDato();
                aux = aux.getSig();
                return dato;
            }

            @Override
            public void remove() {
            }

        };
    }

    private class NodoLista<T> {

        private T dato;
        private NodoLista<T> sig;

        public NodoLista(T dato) {
            this.dato = dato;
            this.sig = null;
        }

        public NodoLista(T dato, NodoLista<T> sig) {
            this.dato = dato;
            this.sig = sig;
        }

        public T getDato() {
            return dato;
        }

        public void setDato(T dato) {
            this.dato = dato;
        }

        public NodoLista<T> getSig() {
            return sig;
        }

        public void setSig(NodoLista<T> sig) {
            this.sig = sig;
        }

        @Override
        public String toString() {
            return dato.toString();
        }

    }


}
