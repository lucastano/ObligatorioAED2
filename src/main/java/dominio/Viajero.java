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
        if(this.cedula==null || this.cedula.isEmpty() || this.cedula.length() > 11  ){
            return false;
        }
        char primerDigito = this.cedula.charAt(0);
        int primerDigitoN = Character.getNumericValue(primerDigito);

        if (primerDigitoN == 0){
            return false;
        }

        String cedula=this.cedula;
        String regex = "^(\\d{1,3}\\.)?\\d{1,3}\\.\\d{1,3}-$";
            // valida N.NNN.NNN- y NNN.NNN-
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(cedula.substring(0, cedula.length() -1));
        // quitamos los dos ultimos caracteres para que no tome el identificador.
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
        String cedula = this.cedula.replace(".", "").replace("-", "");
        int cedulaInt=Integer.parseInt(cedula);
        String cedula2 = o.getCedula().replace(".", "").replace("-", "");
        int cedulaInt2=Integer.parseInt(cedula2);
        return Integer.compare(cedulaInt,cedulaInt2);
    }

    @Override
    public String toString() {
        return cedula + ";" + nombre + ";" + edad + ";" + tipo;
    }
}
