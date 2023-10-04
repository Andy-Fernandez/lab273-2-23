package UDP;

import java.net.*;
import java.io.*;

public class ServidorUDP {
    public static void main(String[] args) throws IOException {
        int puerto = 9876;
        DatagramSocket socket = new DatagramSocket(puerto);

        System.out.println("Servidor UDP en ejecución en el puerto " + puerto);

        while (true) {
            byte[] buffer = new byte[1024];
            DatagramPacket paquete = new DatagramPacket(buffer, buffer.length);

            // Recibir mensaje del cliente
            socket.receive(paquete);
            String mensaje = new String(paquete.getData(), 0, paquete.getLength());

            System.out.println("Cliente: " + mensaje);

            // Verificar si el cliente desea finalizar la sesión
            if (mensaje.equals("ADIOS")) {
                System.out.println("Sesión de chat finalizada.");
                break;
            }

            // Leer mensaje del servidor
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Servidor: ");
            String respuesta = br.readLine();

            // Enviar respuesta al cliente
            byte[] respuestaBytes = respuesta.getBytes();
            DatagramPacket respuestaPaquete = new DatagramPacket(respuestaBytes, respuestaBytes.length, paquete.getAddress(), paquete.getPort());
            socket.send(respuestaPaquete);
        }

        socket.close();
    }
}
