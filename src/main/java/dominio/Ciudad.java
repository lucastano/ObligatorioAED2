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
        String regex = "^[A-Z0-9]{5,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(this.codigo);
        return matcher.matches();
    }

    @Override
    public int compareTo(Ciudad o) {
        return 0;
    }
}
