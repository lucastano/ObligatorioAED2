package dominio;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Ciudad  implements Comparable<Ciudad>{

    private String codigo;
    private String nombre;



    public Ciudad(String codigo, String nombre){
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public boolean validarCodigo(){
        //codigo alfanumerico  al menos de largo 5
        //letras mayuscula
        String regex = "^[A-Z0-9]{5,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(this.codigo);
        return matcher.matches();
    }

    @Override
    public int compareTo(Ciudad o) {

        return this.codigo.compareTo(o.codigo);

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ciudad ciudad = (Ciudad) o;
        return Objects.equals(codigo, ciudad.codigo) ;
    }
    @Override
    public String toString() {
        return codigo +";"+ nombre;
    }
}


