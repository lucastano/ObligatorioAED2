package Estructuras;

import dominio.ObjAux;
import dominio.Viajero;

public class ABB<T extends Comparable<T>> {

    private Nodo<T> raiz;

    public void insertar(T dato) {
        if (this.raiz == null) {
            this.raiz = new Nodo<T>(dato);
        } else {
            insertarRec(raiz, dato);
        }
    }

    //pre nodo != null
    private void insertarRec(Nodo<T> nodo, T dato) {
        if (dato.compareTo(nodo.getDato()) > 0) {
            if (nodo.getDer() == null) {
                nodo.setDer(new Nodo<T>(dato));
            } else {
                insertarRec(nodo.getDer(), dato);
            }
        } else {
            if (nodo.getIzq() == null) {
                nodo.setIzq(new Nodo<T>(dato));
            } else {
                insertarRec(nodo.getIzq(), dato);
            }
        }
    }

    public boolean existe(T dato) {
        return existeRec(raiz, dato);
    }

    private boolean existeRec(Nodo<T> nodo, T dato) {
        if (nodo == null) {
            return false;
        } else if (nodo.getDato().equals(dato)) {
            return true;
        } else {
            if (dato.compareTo(nodo.getDato()) > 0) {
                return existeRec(nodo.getDer(), dato);
            } else {
                return existeRec(nodo.getIzq(), dato);
            }
        }
    }


    //se debe llamar diferente ya que el abb no necesariamente es de viajeros
    public ListaImp<T> obtenerViajerosAsc() {
        ListaImp<T> retorno = new ListaImp<T>();
        obtenerViajerosAsc(raiz, retorno);
        return retorno;
    }

    public void obtenerViajerosAsc(Nodo<T> nodo, ListaImp<T> retorno) {
        if (nodo != null) {
            obtenerViajerosAsc(nodo.getDer(), retorno);
            retorno.insertarDos(nodo.getDato());
            obtenerViajerosAsc(nodo.getIzq(), retorno);
        }


    }

    public ListaImp<T> obtenerViajerosDsc() {
        ListaImp<T> retorno = new ListaImp<T>();
        obtenerViajerosDsc(raiz, retorno);
        return retorno;
    }

    public void obtenerViajerosDsc(Nodo<T> nodo, ListaImp<T> retorno) {
        if (nodo != null) {
            obtenerViajerosDsc(nodo.getIzq(), retorno);
            retorno.insertarDos(nodo.getDato());
            obtenerViajerosDsc(nodo.getDer(), retorno);
        }
    }




    public T obtener(T dato) {
        return obtener(raiz, dato);

    }

    private T obtener(Nodo<T> nodo, T dato) {
        if (nodo == null) {
            return null;
        } else if (nodo.getDato().equals(dato)) {
            return nodo.getDato();
        } else {
            if (dato.compareTo(nodo.getDato()) > 0) {
                return obtener(nodo.getDer(), dato);
            } else {
                return obtener(nodo.getIzq(), dato);
            }
        }
    }

    //se debe llamar diferente ya que el abb no necesariamente es de viajeros
    public ObjAux<T> obtenerPorCedula(T viajero) {
        return obtenerPorCedula(raiz, viajero, 0);
    }

    private ObjAux<T> obtenerPorCedula(Nodo<T> nodo, T viajero, int num) {

        if (nodo == null) {
            return null;
        } else if (nodo.getDato().compareTo(viajero) == 0) {
            ObjAux<T> aux = new ObjAux<>(nodo.getDato(), num);
            return aux;
        } else {
            if (viajero.compareTo(nodo.getDato()) > 0) {

                return obtenerPorCedula(nodo.getDer(), viajero, num + 1);
            } else {

                return obtenerPorCedula(nodo.getIzq(), viajero, num + 1);
            }
        }

    }

    public int cantidadElementosRecorridos(T viajero) {
        return cantidadElementosRecorridos(raiz, viajero);
    }

    public int cantidadElementosRecorridos(Nodo<T> nodo, T viajero) {
        if (nodo == null) {
            return 0;
        }
        int cantidad = 0;
        if (nodo.getDato().compareTo(viajero) == 0) {
            return cantidad;
        }

        int izq = cantidadElementosRecorridos(nodo.getIzq(), viajero);
        int der = cantidadElementosRecorridos(nodo.getDer(), viajero);
        return cantidad + izq + der;
    }


    public void listarAsc() {
        listarAsc(raiz);
    }

    private void listarAsc(Nodo<T> nodo) {
        if (nodo != null) {
            listarAsc(nodo.getIzq());
            System.out.println(nodo.getDato());
            listarAsc(nodo.getDer());
        }
    }

    public void listarDes() {
        listarDes(raiz);
    }

    private void listarDes(Nodo<T> nodo) {
        if (nodo != null) {
            listarDes(nodo.getDer());
            System.out.println(nodo.getDato());
            listarDes(nodo.getIzq());
        }
    }

    private class Nodo<T> {
        private T dato;
        private Nodo<T> der;
        private Nodo<T> izq;

        public Nodo(T dato) {
            this.dato = dato;
        }

        public Nodo(T dato, Nodo<T> der, Nodo<T> izq) {
            this.dato = dato;
            this.der = der;
            this.izq = izq;
        }

        public T getDato() {
            return dato;
        }

        public void setDato(T dato) {
            this.dato = dato;
        }

        public Nodo<T> getDer() {
            return der;
        }

        public void setDer(Nodo<T> der) {
            this.der = der;
        }

        public Nodo<T> getIzq() {
            return izq;
        }

        public void setIzq(Nodo<T> izq) {
            this.izq = izq;
        }
    }

}
