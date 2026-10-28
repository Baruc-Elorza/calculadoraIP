 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datos;

/**
 *
 * @author Nimsi Ortega
 */

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Named("datos")
@ApplicationScoped
public class Datos {

   
    private List<Solicitud> solicitudes;
    private List<Respuesta> respuestas;

    private int[] resumen;

    private int contador;

    private static final DateTimeFormatter FORMATO =
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    public Datos() {
        solicitudes = new ArrayList<>();
        respuestas = new ArrayList<>();
        resumen = new int[2];
        contador = 0;
    }

    public synchronized void registrarCorrecta(String ip, Integer prefijo, CSubred calculo) {
        int id = ++contador;
        solicitudes.add(crearSolicitud(id, ip, prefijo));
        respuestas.add(Respuesta.correcta(id, calculo));
        resumen[0]++;
    }

    public synchronized void registrarError(String ip, Integer prefijo, String mensaje) {
        int id = ++contador;
        solicitudes.add(crearSolicitud(id, ip, prefijo));
        respuestas.add(Respuesta.error(id, mensaje));
        resumen[1]++;
    }

    private Solicitud crearSolicitud(int id, String ip, Integer prefijo) {
        return new Solicitud(
                id,
                LocalDateTime.now().format(FORMATO),
                ip == null ? "" : ip.trim(),
                prefijo == null ? "" : String.valueOf(prefijo));
    }

    
    public synchronized Solicitud[] getSolicitudes() {
        return solicitudes.toArray(new Solicitud[0]);
    }

    
    public synchronized List<Respuesta> getRespuestas() {
        return new ArrayList<>(respuestas);
    }

    public synchronized int[] getResumen() {
        return resumen.clone();
    }

    public synchronized int getTotal() {
        return solicitudes.size();
    }

    public synchronized void limpiar() {
        solicitudes.clear();
        respuestas.clear();
        resumen[0] = 0;
        resumen[1] = 0;
        contador = 0;
    }
}
