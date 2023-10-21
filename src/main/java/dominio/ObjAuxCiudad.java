package dominio;

import java.util.Arrays;

public class ObjAuxCiudad {
    private double costo;
    private String[]ciudadesVisitadas;

    public ObjAuxCiudad(double costo, String[] ciudadesVisitadas) {
        this.costo = costo;
        this.ciudadesVisitadas = ciudadesVisitadas;
    }

    public int getCosto() {
        return (int) costo;
    }

    public void setCosto(int costo) {
        this.costo = costo;
    }

    public String[] getCiudadesVisitadas() {
        return ciudadesVisitadas;
    }

    public void setCiudadesVisitadas(String[] ciudadesVisitadas) {
        this.ciudadesVisitadas = ciudadesVisitadas;
    }


}
