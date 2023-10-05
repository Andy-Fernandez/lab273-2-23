package Automovil;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.DayOfWeek;

public class servidorTCP {
    public static void main(String[] args) {
        int puerto = 49152;
        String host = "localhost";

        try (ServerSocket servidor = new ServerSocket(puerto)) {
            System.out.println("Esperando cliente...");
            Socket sock = servidor.accept();
            System.out.println("Cliente conectado");

            while (true){
            DataInputStream entrada = new DataInputStream(sock.getInputStream());
            DataOutputStream salida = new DataOutputStream(sock.getOutputStream());

            String fechaCliente = entrada.readUTF();

            if (fechaCliente.equals("salir")) {
                System.out.println("Cerrando conexión");
                break;
            }

            String placa = entrada.readUTF();
            //Solo usarmos los primero 4 caracteres de la placa, por ejemmo si es 1010ASD, solo usamos 1010
            placa = placa.substring(0,4);

            String solicitud;

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate fecha = LocalDate.parse(fechaCliente, formatter);

            DayOfWeek diaSemana = fecha.getDayOfWeek();

            if ((diaSemana == DayOfWeek.MONDAY && (placa.endsWith("1") || placa.endsWith("2"))) ||
                    (diaSemana == DayOfWeek.TUESDAY && (placa.endsWith("3") || placa.endsWith("4"))) ||
                    (diaSemana == DayOfWeek.WEDNESDAY && (placa.endsWith("5") || placa.endsWith("6"))) ||
                    (diaSemana == DayOfWeek.THURSDAY && (placa.endsWith("7") || placa.endsWith("8"))) ||
                    (diaSemana == DayOfWeek.FRIDAY && (placa.endsWith("9") || placa.endsWith("0")))) {
                solicitud = "No puede circular";
            } else {
                solicitud = "Puede circular";
            }

            System.out.println(solicitud);
            salida.writeUTF(solicitud);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
