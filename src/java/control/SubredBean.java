package control;

import datos.CSubred;
import datos.Datos;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Named("subredBean")
@RequestScoped
public class SubredBean {

    @Inject
    private Datos datos;

    private String ip;
    private Integer prefijo;
    private CSubred calculo;
    private String error;

    // Checkboxes: datos que el usuario quiere ver (empiezan todos marcados)
    private List<String> campos = new ArrayList<>(Arrays.asList(
            "mascara", "red", "broadcast", "primera", "ultima", "total", "hosts"));

    public String calcular() {
        calculo = null;
        error = null;

        if (!validarIP(ip)) {
            error = "La dirección IP no es válida.";
            datos.registrarError(ip, prefijo, error);
            return "/ip-invalida.xhtml";
        }

        try {
            if (prefijo == null) {
                error = "Escribe un prefijo CIDR entre 0 y 32.";
                datos.registrarError(ip, prefijo, error);
                return null;
            }
            calculo = new CSubred(ip.trim(), prefijo);
            datos.registrarCorrecta(ip, prefijo, calculo);
            return "/resultado.xhtml";
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
            datos.registrarError(ip, prefijo, error);
            return null;
        }
    }

    private boolean validarIP(String direccion) {
        if (direccion == null || direccion.trim().isEmpty()) {
            return false;
        }
        try {
            new CSubred(direccion.trim(), 0);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    // Opciones del combobox: prefijos de 0 a 32
    public List<Integer> getPrefijos() {
        List<Integer> lista = new ArrayList<>();
        for (int i = 0; i <= 32; i++) {
            lista.add(i);
        }
        return lista;
    }

    public String getIp() { return ip; }
    public void setIp(String ip) { this.ip = ip; }

    public Integer getPrefijo() { return prefijo; }
    public void setPrefijo(Integer prefijo) { this.prefijo = prefijo; }

    public CSubred getCalculo() { return calculo; }
    public String getError() { return error; }

    public List<String> getCampos() { return campos; }
    public void setCampos(List<String> campos) { this.campos = campos; }
}