package sistema;

import Estructuras.ABB;

import Estructuras.ListaImp;
import interfaz.Retorno;
import dominio.ObjAux;
import dominio.Viajero;
import interfaz.*;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ImplementacionSistema implements Sistema {
    // aca van a ir las estructuras
    // las ciudades van a ser la cantidad de vertices del grafo
    int cantidasMaxCiudades;
    ABB<Viajero> viajeros;

    @Override
    public Retorno inicializarSistema(int maxCiudades) {

        if (maxCiudades <= 5) {

            return Retorno.error1("");
        } else {
            //Inicializamos cantidad maxima de ciudades para el grafo
            this.cantidasMaxCiudades = maxCiudades;
            //inicializamos abb viajero
            this.viajeros = new ABB<Viajero>();
            //aca inicializamos grafo y abb
            return Retorno.ok();

        }


    }

    @Override
    public Retorno registrarViajero(String cedula, String nombre, int edad, TipoViajero tipo) {


        if (cedula == null || cedula.isEmpty() || nombre == null || nombre.isEmpty() ||  edad == 0 || tipo == null) {
            return Retorno.error1("algun parametro vacio");
        }
        Viajero viajero = new Viajero(cedula, nombre, edad, tipo.getTexto());
        if (!viajero.validarCedula()) {


            return Retorno.error2("cedula invalida");

        }
        if (viajeros.existe(viajero)) {
            String entro = "";
            return Retorno.error3("viajero ya registrado");
        }

        viajeros.insertar(viajero);
        return Retorno.ok();

    }

    @Override
    public Retorno buscarViajero(String cedula) {
        Viajero viajeroBuscado= new Viajero(cedula);
        if(!viajeroBuscado.validarCedula()){
            return Retorno.error1("cedula no valida");
        }
        ObjAux<Viajero> aux=viajeros.obtenerPorCedula(viajeroBuscado);
        if(aux==null){
            return Retorno.error2("no existe viajero registrado");
        }

        System.out.println("retorno");
        System.out.println(aux.getCant());
        System.out.println(aux.getDato().toString());

        return Retorno.ok(aux.getCant(),aux.getDato().toString());
    }

    @Override
    public Retorno listarViajerosAscendente() {

        ListaImp<Viajero>listaViajeros= new ListaImp<Viajero>();
        listaViajeros=viajeros.obtenerViajerosAsc();
        String listaViajerosStr = retorno(listaViajeros);
        String prueba = listaViajerosStr.substring(0, listaViajerosStr.length()-1);
        return Retorno.ok(0,prueba);

    }

    @Override
    public Retorno listarViajerosDescendente() {
        ListaImp<Viajero>listaViajeros= new ListaImp<Viajero>();
        listaViajeros= viajeros.obtenerViajerosDsc();
        String listaViajerosStr = retorno(listaViajeros);
        String prueba = listaViajerosStr.substring(0, listaViajerosStr.length()-1);
        return Retorno.ok(0,prueba);
    }

    @Override
    public Retorno listarViajerosPorTipo(TipoViajero tipo) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno registrarCiudad(String codigo, String nombre) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno registrarConexion(String codigoCiudadOrigen, String codigoCiudadDestino, int identificadorConexion, double costo, double tiempo, TipoConexion tipo) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno actualizarConexion(String codigoCiudadOrigen, String codigoCiudadDestino, int identificadorConexion, double costo, double tiempo, TipoConexion tipo) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno listadoCiudadesCantTrasbordos(String codigo, int cantidad) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno viajeCostoMinimo(String codigoCiudadOrigen, String codigoCiudadDestino) {
        return Retorno.noImplementada();
    }




    public String retorno(ListaImp<Viajero> lista) {

        String retorno = "";
        for (Viajero v : lista) {
            retorno += v.toString() + "|";
        }

        return retorno;//.substring(0, retorno.length()-1)

    }
}
