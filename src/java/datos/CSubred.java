/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datos;

/**
 *
 * @author Jobae
 */

public class CSubred {

    private String ip;
    private int prefijo;

    private long direccionIP;
    private long mascara;
    private long direccionRed;
    private long broadcast;

    public CSubred(String ip, int prefijo) {
        if (prefijo < 0 || prefijo > 32) {
            throw new IllegalArgumentException(
                "El prefijo debe estar entre 0 y 32."
            );
        }

        this.ip = ip;
        this.prefijo = prefijo;

        direccionIP = convertirIP(ip);

        if (prefijo == 0) {
            mascara = 0;
        } else {
            mascara = (0xFFFFFFFFL << (32 - prefijo))
                      & 0xFFFFFFFFL;
        }

        direccionRed = direccionIP & mascara;
        broadcast = direccionRed | (~mascara & 0xFFFFFFFFL);
    }

    private long convertirIP(String ip) {
        String[] partes = ip.split("\\.");

        if (partes.length != 4) {
            throw new IllegalArgumentException(
                "La dirección debe tener cuatro octetos."
            );
        }

        long resultado = 0;

        for (String parte : partes) {
            int octeto;

            try {
                if (parte.isEmpty() || !parte.matches("[0-9]+")) {
                    throw new NumberFormatException();
                }

                octeto = Integer.parseInt(parte);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException(
                    "La dirección IP contiene un octeto inválido."
                );
            }

            if (octeto < 0 || octeto > 255) {
                throw new IllegalArgumentException(
                    "Cada octeto debe estar entre 0 y 255."
                );
            }

            resultado = (resultado << 8) | octeto;
        }

        return resultado;
    }

    private String convertirAIP(long valor) {
        return ((valor >> 24) & 255) + "."
             + ((valor >> 16) & 255) + "."
             + ((valor >> 8) & 255) + "."
             + (valor & 255);
    }

    public String obtenerMascara() {
        return convertirAIP(mascara);
    }

    public String obtenerDireccionRed() {
        return convertirAIP(direccionRed);
    }

    public String obtenerBroadcast() {
        return convertirAIP(broadcast);
    }

    public String obtenerPrimeraIP() {
        if (prefijo >= 31) {
            return prefijo == 31
                ? convertirAIP(direccionRed)
                : convertirAIP(direccionIP);
        }

        return convertirAIP(direccionRed + 1);
    }

    public String obtenerUltimaIP() {
        if (prefijo >= 31) {
            return prefijo == 31
                ? convertirAIP(broadcast)
                : convertirAIP(direccionIP);
        }

        return convertirAIP(broadcast - 1);
    }

    public long obtenerTotalDirecciones() {
        return 1L << (32 - prefijo);
    }

    public long obtenerHostsUtilizables() {
        if (prefijo <= 30) {
            return obtenerTotalDirecciones() - 2;
        }

        if (prefijo == 31) {
            return 2;
        }

        return 1;
    }

    public int obtenerPrefijo() {
        return prefijo;
    }
}

