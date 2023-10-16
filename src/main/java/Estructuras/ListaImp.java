package Estructuras;

import java.util.Iterator;

public class ListaImp<T extends Comparable<T>> implements Lista<T> {

    protected NodoLista<T> inicio;
    protected int largo;

    public ListaImp() {
        this.inicio = null;
        this.largo = 0;
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
            anterior.setSig(nuevoNodo);
        }
        largo++;
    }



    @Override
    public int largo() {
        return largo;
    }

    @Override
    public boolean esVacia() {
        return largo == 0;
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
