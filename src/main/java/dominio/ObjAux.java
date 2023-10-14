package dominio;

public class ObjAux<T extends Comparable<T>> {
    private T dato;
    private int cant;

    public ObjAux(T dato, int cant) {
        this.dato = dato;
        this.cant = cant;
    }

    public T getDato() {
        return dato;
    }

    public int getCant() {
        return cant;
    }
}
