package sistema;

import Estructuras.ABB;

import Estructuras.Grafo;
import Estructuras.ListaImp;
import dominio.Ciudad;
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
    //lista de viajeros por tipo
    ABB<Viajero> viajerosPremium;
    ABB<Viajero> viajerosCasual;
    ABB<Viajero> viajerosEstandar;
    Grafo grafoCiudades;

    //Lista de ciudades
    //ListaImp<Ciudad> ciudades;

    @Override
    public Retorno inicializarSistema(int maxCiudades) {

        if (maxCiudades <= 5) {

            return Retorno.error1("");
        } else {
            //Inicializamos cantidad maxima de ciudades para el grafo
            this.cantidasMaxCiudades = maxCiudades;
            this.viajeros = new ABB<Viajero>();
            this.viajerosPremium  = new ABB<Viajero>();
            this.viajerosCasual  = new ABB<Viajero>();
            this.viajerosEstandar  = new ABB<Viajero>();
            //Inicializamos grafo con tope ciudades
            this.grafoCiudades=new Grafo(maxCiudades,true);

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
        if(tipo.getTexto().equals("PREMIUM")){
            viajerosPremium.insertar(viajero);
        }else if (tipo.getTexto().equals("CASUAL")){
            viajerosCasual.insertar(viajero);
        }else{
            viajerosEstandar.insertar(viajero);
        }
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
        if(!listaViajerosStr.isEmpty()){
            String prueba = listaViajerosStr.substring(0, listaViajerosStr.length()-1);
            return Retorno.ok(0,prueba);
        }else{
            return Retorno.ok(0,listaViajerosStr);
        }




    }

    @Override
    public Retorno listarViajerosDescendente() {
        ListaImp<Viajero>listaViajeros= new ListaImp<Viajero>();
        listaViajeros= viajeros.obtenerViajerosDsc();
        String listaViajerosStr = retorno(listaViajeros);
        if(!listaViajerosStr.isEmpty()){
            String prueba = listaViajerosStr.substring(0, listaViajerosStr.length()-1);
            return Retorno.ok(0,prueba);
        }else{
            return Retorno.ok(0,listaViajerosStr);
        }
    }

    @Override
    public Retorno listarViajerosPorTipo(TipoViajero tipo) {
        ListaImp<Viajero>listaViajeros= new ListaImp<Viajero>();
        if(tipo == null){
            return Retorno.error1("el tipo no puede ser null");
        }
        if(tipo.getTexto().equals("CASUAL")){
            listaViajeros=viajerosCasual.obtenerViajerosAsc();
            String ret = retorno(listaViajeros);
            if(!ret.isEmpty()){
                return Retorno.ok(ret.substring(0, ret.length()-1));
            }else{
                return Retorno.ok(ret);
            }
        }else if (tipo.getTexto().equals("PREMIUM")){
            listaViajeros=viajerosPremium.obtenerViajerosAsc();
            String ret = retorno(listaViajeros);
            if(!ret.isEmpty()){
                return Retorno.ok(ret.substring(0, ret.length()-1));
            }else{
                return Retorno.ok(ret);
            }
        }else{
            listaViajeros=viajerosEstandar.obtenerViajerosAsc();
            String ret = retorno(listaViajeros);

            if(!ret.isEmpty()){
                return Retorno.ok(ret.substring(0, ret.length()-1));
            }else{
                return Retorno.ok(ret);
            }
        }
    }



    @Override
    public Retorno registrarCiudad(String codigo, String nombre) {
        if(codigo==null || codigo.isEmpty() || nombre==null || nombre.isEmpty()){
            return Retorno.error2("Algun dato vacio o nulo");
        }
        if(grafoCiudades.esLleno()){
            return Retorno.error1("Grafo lleno");
        }
         Ciudad c=new Ciudad(codigo,nombre);
        if(!c.validarCodigo()){
            return Retorno.error3("Codigo no valido");
        }
        if(grafoCiudades.existeVertice(c)){
            return Retorno.error4("Ya existe esta ciudad");
        }
        grafoCiudades.agregarVertice(c);
        return Retorno.ok();

    }

    @Override
    public Retorno registrarConexion(String codigoCiudadOrigen, String codigoCiudadDestino, int identificadorConexion, double costo, double tiempo, TipoConexion tipo) {
       //esto esta aca por ahora para poder ver las ciudades en la lista
        //ciudades.mostrarLista();

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
