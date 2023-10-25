package dominio;

import Estructuras.Arista;
import Estructuras.ListaImp;
import interfaz.TipoConexion;

public class conexionAux {

    private boolean existe;
    private Ciudad ciudadOrigen;
    private Ciudad ciudadDestino;
    private ListaImp<Arista> aristas = new ListaImp<Arista>();

    public conexionAux(Ciudad ciudadOrigen, Ciudad ciudadDestino, ListaImp<Arista> aristas, boolean existe) {
        this.ciudadOrigen = ciudadOrigen;
        this.ciudadDestino = ciudadDestino;
        this.aristas = aristas;
        this.existe = existe;
    }
    public conexionAux(){}

    public Ciudad getCiudadOrigen() {
        return ciudadOrigen;
    }

    public Ciudad getCiudadDestino() {
        return ciudadDestino;
    }

    public ListaImp<Arista> getAristas() {
        return aristas;
    }

    public boolean existeArista(int identifcador){
        boolean ret = false;
        for(Arista a: aristas){
            if(a.getIdentificadorConexion() == identifcador){
                ret = true;
            }
        }
        return ret;
   }

   public Arista getAristaDeConexion(int identificador){
       for(Arista a: aristas){
           if(a.getIdentificadorConexion() == identificador){
               return a;
           }
       }
        return null;
   }

   public void agregarArista(Ciudad origen, Ciudad destino, int identificadorConexion, double costoTiempo, double peso, TipoConexion tipo){
        Arista arista = new Arista();
        arista.setTipo(tipo);
        arista.setIdentificadorConexion(identificadorConexion);
        arista.setPeso(peso);
        arista.setCosto(costoTiempo);
        aristas.insertarDos(arista);


   }

    public boolean isExiste() {
        return existe;
    }

    public void setExiste(boolean existe) {
        this.existe = existe;
    }
}
