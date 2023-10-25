package Estructuras;

import interfaz.TipoConexion;

public class Arista implements Comparable<Arista> {

    private boolean existe;
    private double peso;
    private int identificadorConexion;

    //lo cambie a int por problemas en el dijkstra
    private double costo;

    private TipoConexion tipo;

    public Arista() {
        this.existe = false;
        this.peso = 0;
        this.costo = 0;
        this.identificadorConexion = 0;
        this.tipo = null;


    }

    public int getIdentificadorConexion() {
        return identificadorConexion;
    }

    public void setIdentificadorConexion(int identificadorConexion) {
        this.identificadorConexion = identificadorConexion;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public TipoConexion getTipo() {
        return tipo;
    }

    public void setTipo(TipoConexion tipo) {
        this.tipo = tipo;
    }

    public boolean isExiste() {
        return existe;
    }

    public void setExiste(boolean existe) {
        this.existe = existe;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    @Override
    public int compareTo(Arista o) {
        return 0;
    }
}
