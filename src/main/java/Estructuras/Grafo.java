package Estructuras;

import dominio.Ciudad;
import interfaz.TipoConexion;

import java.sql.Array;

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
    public boolean existeArista2(Ciudad origen, Ciudad destino){
        int posOrigen = obtenerPos(origen);
        int posDestino = obtenerPos(destino);
        if(matAdy[posOrigen][posDestino].isExiste() || matAdy[posDestino][posOrigen].isExiste()){
            return true;

        }
        return false;
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
    public void agregarArista(Ciudad origen, Ciudad destino, int identificadorConexion, int costo, double peso, TipoConexion tipo) {
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

    public Ciudad getVertice(Ciudad vert){
        int pos = obtenerPos(vert);
        return vertices[pos];
    }

    //Pre existeVertice(vert)
    public ListaImp<Ciudad> dfs(Ciudad vert, int cantidad){
        int posInicial = obtenerPos(vert);
        boolean[] visitados = new boolean[tope]; //por defecto todo en false
        ListaImp<Ciudad> ciudades = new ListaImp<>();
        if(cantidad > tope){
            cantidad = tope;
        }
        dfsRec(posInicial, visitados, ciudades, cantidad );
        return ciudades;
    }

    private void dfsRec(int pos, boolean[] visitados, ListaImp<Ciudad> ciudades, int cantidad ){
        if(cantidad == 0){
            Ciudad c = vertices[pos];
            if(ciudades.existe(c)){
                return;
            }
            ciudades.insertar(c);
            return;
        }
        visitados[pos] = true;
        for (int j = 0; j < tope; j++) {
            if( matAdy[pos][j].isExiste() && !visitados[j]){
                Ciudad c = vertices[j];
                ciudades.insertar(c);
                dfsRec(j, visitados, ciudades, cantidad-1);
            }
        }
        dfsRec(pos, visitados, ciudades, cantidad-1);
    }

    //esta funcion recorre el grafo con dfs desde ciudad de origen y verifica si la ciudad
    // de destino esta en el array de visitados, si esta significa que hay conexion entre las 2 ciudades
    public boolean dfs2(Ciudad origen, Ciudad destino){
        int posInicial = obtenerPos(origen);
        int posDestino= obtenerPos(destino);
        boolean [] visitados= new boolean[tope];
        dfs2(posInicial,visitados);
        //si la posicion del destino final no fue visitado, no hay conexion
        return visitados[posDestino];
    }


    private void dfs2(int pos, boolean[] visitados) {
       visitados[pos]=true;
        for (int j = 0; j < tope; j++) {
            if(matAdy[pos][j].isExiste() && !visitados[j]){
                dfs2(j,visitados);
            }
        }
    }


    public int dijkstra(Ciudad origen, Ciudad destino){

        int posOrigen=obtenerPos(origen);
        int posDestino=obtenerPos(destino);

        //creamos e inicializamos estructuras
        boolean[]visitados=new boolean[tope];
        int[]costos=new int[tope];
        Ciudad []anterior=new Ciudad[tope];

        for (int i = 0; i < tope; i++) {
            costos[i]=Integer.MAX_VALUE;

        }
        //marcar origen con distancia 0
        // la primera posicion tiene costo 0
        costos[posOrigen]=0;
        //loop por cantidad de vertices
        for (int i = 0; i < cantidad ; i++) {

            // 1) obtener vertice de menor costo no visitado
            int pos=obtenerSiguienteVerticeNoVisitadoDeMenorCosto(costos,visitados);
            if(pos!=-1){
                //2) Visitarlo
                visitados[pos]=true;

                //3) evaluar si tengo que actualizar el costo de los no visitados
                for (int j = 0; j <tope ; j++) {
                    //verifico los adyasentes
                    if(matAdy[pos][j].isExiste() && !visitados[j]){
                        int costoNuevo=costos[pos]+matAdy[pos][j].getCosto();
                        if(costoNuevo<costos[j]){
                            costos[j]=costoNuevo;
                        }
                    }
                    
                }
            }



        }


        return costos[posDestino];



    }

    private int obtenerSiguienteVerticeNoVisitadoDeMenorCosto(int []costos, boolean []visitados){
        //posicion del minimo inicializada en -1 , si nos devuelve esa posicion es que no encontro nada
        int posMin=-1;
        //minimo en max value
        int minimo = Integer.MAX_VALUE;
        //puedo recorrer cualquier de los array ya que tienen la misma cantidad de elementos
        for (int i = 0; i < costos.length; i++) {
            //si el costo es mayor al minimo y no fue visitado
            if(!visitados[i] && costos[i]<minimo  ){
                minimo=costos[i];
                posMin=i;
            }

        }
        return posMin;
    }










}
