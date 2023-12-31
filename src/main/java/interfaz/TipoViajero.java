package interfaz;

import java.util.Arrays;
import java.util.Objects;

public enum TipoViajero {
    PREMIUM(0, "PREMIUM"),
    ESTANDAR(1, "ESTANDAR"),
    CASUAL(2, "CASUAL");

    private final int indice;
    private final String texto;

    TipoViajero(int indice, String texto) {
        this.indice = indice;
        this.texto = texto;
    }

    public int getIndice() {
        return indice;
    }

    public String getTexto() {
        return texto;
    }

    public static TipoViajero fromTexto(String texto) {
        return Arrays.stream(TipoViajero.values())
                .filter(a -> Objects.equals(a.texto, texto))
                .findFirst()
                .orElse(null);
    }

}
