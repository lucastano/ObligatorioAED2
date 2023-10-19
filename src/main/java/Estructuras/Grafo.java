package Estructuras;

import dominio.Ciudad;
import interfaz.TipoConexion;

public class Grafo {
    private int cantidad;
    private final int tope;

    //vertices son ciudades
    private Ciudad []vertices;
    private Arista [][]matAdy;

    public Grafo(int tope, boolean esDirigido){
        this.cantidad=0;
        this.tope=tope;
        this.vertices=new Ciudad [tope];//por defecto null
        this.matAdy=new Arista[tope][tope];//por defecto en null
        if(esDirigido) {
            for (int i = 0; i < tope; i++) {
                for (int j = 0; j < tope; j++) {
                    matAdy[i][j] = new Arista();

                }

            }
        }
        else
        {
            for (int i = 0; i < tope; i++) {
                for (int j = i; j < tope; j++) {
                    matAdy[i][j] = new Arista();
                    matAdy[j][i]=matAdy[i][j];
                    //recorre la triangular superior

                }

            }

        }

    }

    public boolean existeArista(Ciudad origen, Ciudad destino, int identificadorConexion){
        int posOrigen = obtenerPos(origen);
        int posDestino = obtenerPos(destino);
        return matAdy[posOrigen][posDestino].isExiste() && matAdy[posOrigen][posDestino].getIdentificadorConexion() == identificadorConexion;
    }

    public boolean esLleno(){
        return cantidad==tope;
    }

    public boolean esVacio(){
        return cantidad==0;
    }

    //obtener posicion, si el vertice en posicion i ==0 esta libre
    //devuelve la primera posicion libre
    //si no encuentra ninguno devuelve -1;
    private int obtenerPosLibre(){
        for(int i=0;i<tope;i++){
            if(vertices[i]==null){
                return i;
            }
        }
        return -1;
    }

    public Arista getArista(Ciudad origen, Ciudad destino){
        int posOrigen = obtenerPos(origen);
        int posDestino = obtenerPos(destino);
        return matAdy[posOrigen][posDestino];
    }
    public void agregarArista(Ciudad origen, Ciudad destino, int identificadorConexion, double costo, double peso, TipoConexion tipo) {
        int posOrigen = obtenerPos(origen);
        int posDestino = obtenerPos(destino);
        matAdy[posOrigen][posDestino].setExiste(true);
        matAdy[posOrigen][posDestino].setPeso(peso);
        matAdy[posOrigen][posDestino].setCosto(costo);
        matAdy[posOrigen][posDestino].setIdentificadorConexion(identificadorConexion);
        matAdy[posOrigen][posDestino].setTipo(tipo);

    }

    //busca la posicion de la ciudad que pasamos por parametro
    private int obtenerPos(Ciudad c){
        for(int i=0;i<tope;i++){
            if(c.equals(vertices[i])){
                return i;
            }
        }
        return -1;
    }
    // PRE: !esLleno && !existeVertice
    public void agregarVertice(Ciudad vert) {
        //Implementar...
        int posLibre =obtenerPosLibre();
        vertices[posLibre]=vert;
        cantidad++;
    }
    public void borrarVertice(Ciudad vert) {
        //Implementar...
        int pos =obtenerPos(vert);
        vertices[pos]=null;
        for(int i=0;i< matAdy.length;i++)
        {
            matAdy[pos][i].setExiste(false);
            matAdy[i][pos].setExiste(false);


        }
        cantidad--;
    }
    public boolean existeVertice(Ciudad vert) {

        return obtenerPos(vert) != -1;
    }

}
