package TCP;

import java.io.*;
import java.net.*;
import java.util.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
public class App {
    public static void main(String[] args) throws Exception {
        int puerto = 9091;
        ServerSocket mySocket = new ServerSocket(puerto);
        System.out.println("El socket servidor fue creado correctamente en el puerto: " + puerto);

        while (true) {
            Socket enlace = mySocket.accept();
            System.out.println("El usuario tiene la siguiente dirección IP: " + enlace.getInetAddress());

            BufferedReader entrada = new BufferedReader(new InputStreamReader(enlace.getInputStream()));
            PrintWriter salida = new PrintWriter(enlace.getOutputStream(), true);

            salida.println("Introduzca la palabra \"fecha\" : ");

            String clave;
            while ((clave = entrada.readLine()) != null) {
                if (clave.equals("fecha")) {
                    Calendar calendario = Calendar.getInstance();
                    Date fecha = calendario.getTime();
                    SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String fechaFormateada = formatoFecha.format(fecha);
                    salida.println("Fecha y hora actual: " + fechaFormateada);
                } else {
                    salida.println("No ingreso la palabra clave");
                }
            }

            enlace.close();
        }
    }
}
