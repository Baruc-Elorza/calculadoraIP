/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datos;

/**
 *
 * @author Nimsi Ortega
 */

public class Respuesta {

    private final int id;
    private final String estado;
    private final String mensaje;
    private final String mascara;
    private final String direccionRed;
    private final String broadcast;
    private final String primeraIP;
    private final String ultimaIP;
    private final String totalDirecciones;
    private final String hostsUtilizables;

    private Respuesta(int id, String estado, String mensaje, String mascara,
                      String direccionRed, String broadcast, String primeraIP,
                      String ultimaIP, String totalDirecciones, String hostsUtilizables) {
        this.id = id;
        this.estado = estado;
        this.mensaje = mensaje;
        this.mascara = mascara;
        this.direccionRed = direccionRed;
        this.broadcast = broadcast;
        this.primeraIP = primeraIP;
        this.ultimaIP = ultimaIP;
        this.totalDirecciones = totalDirecciones;
        this.hostsUtilizables = hostsUtilizables;
    }

    public static Respuesta correcta(int id, CSubred c) {
        return new Respuesta(id, "Correcto", "-",
                c.obtenerMascara(), c.obtenerDireccionRed(), c.obtenerBroadcast(),
                c.obtenerPrimeraIP(), c.obtenerUltimaIP(),
                String.valueOf(c.obtenerTotalDirecciones()),
                String.valueOf(c.obtenerHostsUtilizables()));
    }

    public static Respuesta error(int id, String mensaje) {
        return new Respuesta(id, "Error", mensaje, "-", "-", "-", "-", "-", "-", "-");
    }

    public int getId() { return id; }
    public String getEstado() { return estado; }
    public String getMensaje() { return mensaje; }
    public String getMascara() { return mascara; }
    public String getDireccionRed() { return direccionRed; }
    public String getBroadcast() { return broadcast; }
    public String getPrimeraIP() { return primeraIP; }
    public String getUltimaIP() { return ultimaIP; }
    public String getTotalDirecciones() { return totalDirecciones; }
    public String getHostsUtilizables() { return hostsUtilizables; }
}