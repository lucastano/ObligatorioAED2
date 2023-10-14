package sistema;

import Estructuras.ABB;
import Estructuras.Lista;
import Estructuras.ListaImp;
import dominio.ObjAux;
import dominio.Viajero;
import interfaz.*;

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
        //hay que validar cedula con expreciones regulares
        // hay que buscar el viajero en el abb , si existe no podes registrarlo
        //verificar nullos
        //debe usar el validar cedula
        Viajero viajero = new Viajero(cedula, nombre, edad, tipo.getTexto());
        if (cedula == null || nombre == null || edad == 0 || tipo.getTexto() == null) {
            return Retorno.error1("algun parametro vacio");
        }
        if (!viajero.validarCedula()) {


            return Retorno.error2("cedula invalida");

        }
        if (viajeros.existe(viajero)) {
            String entro = "";
            return Retorno.error3("viajero ya registrado");
        }

        viajeros.insertar(viajero);
        return Retorno.ok();

        //return Retorno.noImplementada();
    }

    @Override
    public Retorno buscarViajero(String cedula) {
        //validar cedula fuera de viajero
        //como devolvemos la cantidad , si el obtenerPorCedula nos devuelve un objeto viajero
//        if(!validarCedula(cedula))
//        {
//            return Retorno.error1("Cedula no valida");
//        }
//        Viajero viajeroBuscado=new Viajero(cedula);
//        ObjAux<Viajero>aux = viajeros.obtenerPorCedula(viajeroBuscado);
//        Viajero viajero =aux.getDato();
//
//        if(viajero==null)
//        {
//            return Retorno.error2("No existe el viajero");
//        }
//        //Retorno ret= new Retorno(Retorno.Resultado.OK,aux.getCant(),viajero.toString());
//        return Retorno.ok(viajero.toString(),aux.getCant());
        return Retorno.noImplementada();
    }

    @Override
    public Retorno listarViajerosAscendente() {

//        ListaImp<Viajero>listaViajeros=new ListaImp<Viajero>();
//        listaViajeros=viajeros.obtenerViajerosDsec();
//
//       Retorno ret= new Retorno(Retorno.Resultado.OK,0,retorno(listaViajeros));
//       return Retorno.ok(listaViajeros).;
        return Retorno.noImplementada();
    }

    @Override
    public Retorno listarViajerosDescendente() {
        return Retorno.noImplementada();
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


    //por ahora siempre devuelve true
    public boolean validarCedula(String cedula) {
        return true;

    }

    public String retorno(ListaImp<Viajero> lista) {

        String retorno = "";
        for (Viajero v : lista) {
            retorno += v.toString() + "|";
        }
        return retorno;

    }
}
