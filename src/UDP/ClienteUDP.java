package UDP;

import java.net.*;
import java.io.*;

public class ClienteUDP {
    public static void main(String[] args) throws IOException {
        String servidorIP = "localhost"; // Cambia esto a la dirección IP o nombre de host del servidor si es necesario
        int puerto = 9876;

        DatagramSocket socket = new DatagramSocket();

        InetAddress servidorDireccion = InetAddress.getByName(servidorIP);

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            // Leer mensaje del usuario
            System.out.print("Cliente: ");
            String mensaje = br.readLine();

            // Enviar mensaje al servidor
            byte[] mensajeBytes = mensaje.getBytes();
            DatagramPacket paquete = new DatagramPacket(mensajeBytes, mensajeBytes.length, servidorDireccion, puerto);
            socket.send(paquete);

            // Verificar si el cliente desea finalizar la sesión
            if (mensaje.equals("ADIOS")) {
                System.out.println("Sesión de chat finalizada.");
                break;
            }

            // Recibir respuesta del servidor
            byte[] buffer = new byte[1024];
            DatagramPacket respuestaPaquete = new DatagramPacket(buffer, buffer.length);
            socket.receive(respuestaPaquete);

            String respuesta = new String(respuestaPaquete.getData(), 0, respuestaPaquete.getLength());
            System.out.println("Servidor: " + respuesta);
        }

        socket.close();
    }
}
