package Automovil;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.DayOfWeek;

public class clienteTCP {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int puerto = 49152;
        String host = "localhost";

        Socket sock = new Socket(host, puerto);

        DataInputStream entrada = new DataInputStream(sock.getInputStream());
        DataOutputStream salida = new DataOutputStream(sock.getOutputStream());

        //Ingrasar solicitud
        System.out.println("Ingrese fecha: ");
        String fechaCliente = sc.nextLine();
        salida.writeUTF(fechaCliente);  // Enviar la fecha

        System.out.println("Ingrese número de placa: ");
        String placa = sc.nextLine();
        salida.writeUTF(placa);  // Enviar la placa

        //Recibir respuesta
        String respuesta = entrada.readUTF();  // Recibir respuesta del servidor
        System.out.println("Respuesta recibida: " + respuesta);

    }
}
