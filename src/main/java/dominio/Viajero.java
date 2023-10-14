package dominio;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Viajero implements Comparable<Viajero> {

    //hay que validar formato cedula con expreciones regulares
    private final String cedula;
    private String nombre;
    private int edad;//ver por que no podemos acceder con el .

    public int aux;
    private String tipo;

    public Viajero(String cedula, String nombre, int edad, String tipo) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.edad = edad;
        this.tipo = tipo;
    }

    public Viajero(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCedula() {
        return cedula;
    }

    public int getEdad() {
        return edad;
    }

    public String getTipo() {
        return tipo;
    }

    public boolean validarCedula() {
        String patron = "^(\\d{1,3}(\\.\\d{3})*-\\d{1}|\\d{1,3}-\\d{1})$";
        Pattern pattern = Pattern.compile(patron);
        Matcher matcher = pattern.matcher(this.cedula);

        return matcher.matches();

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Viajero viajero = (Viajero) o;
        return edad == viajero.edad && Objects.equals(nombre, viajero.nombre) && Objects.equals(cedula, viajero.cedula);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, cedula, edad, tipo);
    }

    @Override
    public int compareTo(Viajero o) {
        int indiceGuionCedulaO=o.getCedula().lastIndexOf("-");
        int indiceGuionCedulaThis=this.cedula.lastIndexOf("-");
        String cedulaSinGuionO="";
        String cedulaSinGuionThis="";
        if(indiceGuionCedulaO!=-1){
             cedulaSinGuionO=o.getCedula().substring(0,indiceGuionCedulaO);
             cedulaSinGuionThis=this.cedula.substring(0,indiceGuionCedulaThis);

        }
        cedulaSinGuionThis=cedulaSinGuionThis.replace(".","");
        cedulaSinGuionO=cedulaSinGuionO.replace(".","");

        int cedula = Integer.parseInt(cedulaSinGuionThis);
        int cedula2 = Integer.parseInt(cedulaSinGuionO);
        return Integer.compare(cedula, cedula2);
    }

    @Override
    public String toString() {
        return cedula + ";" + nombre + ";" + edad + ";" + tipo;
    }
}
