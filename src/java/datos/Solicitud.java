/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datos;

/**
 *
 * @author Nimsi Ortega
 */

public class Solicitud {

    private final int id;
    private final String fecha;
    private final String ip;
    private final String prefijo;

    public Solicitud(int id, String fecha, String ip, String prefijo) {
        this.id = id;
        this.fecha = fecha;
        this.ip = ip;
        this.prefijo = prefijo;
    }

    public int getId() { return id; }
    public String getFecha() { return fecha; }
    public String getIp() { return ip; }
    public String getPrefijo() { return prefijo; }
}
