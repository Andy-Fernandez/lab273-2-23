package TCP;

import java.io.*;
import java.net.*;
import java.util.*;
public class ServidorHora {
    public static void main(String[] args) throws Exception {
        ServerSocket servidor = new ServerSocket(5201);
        System.out.println("Esperando la conexion de un cliente ....");

        Socket solicitud = servidor.accept();
        OutputStream salida = solicitud.getOutputStream();
        PrintWriter out = new PrintWriter(salida, true); // Agregamos true para habilitar el autoflush

        // Obtenemos la hora actual
        Date fecha = new Date();
        String hora = fecha.toString();

        out.println(hora); // Enviamos la hora al cliente

        out.close();
        solicitud.close();
    }
}
