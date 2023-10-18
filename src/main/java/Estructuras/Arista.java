package Estructuras;

public class Arista {

    private boolean existe;
    private int peso;

    public Arista() {
        this.existe = false;
        this.peso = 0;
    }

    public Arista(int peso){
        this.existe=true;
        this.peso=peso;
    }

    public boolean isExiste() {
        return existe;
    }

    public void setExiste(boolean existe) {
        this.existe = existe;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }
}