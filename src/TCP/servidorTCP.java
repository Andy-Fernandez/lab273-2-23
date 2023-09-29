package TCP;

import java.util.Scanner;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class servidorTCP {
    public static void main(String[] args) throws IOException {


        int puerto = 49152;
        String host = "localhost";

        ServerSocket servidor = new ServerSocket(puerto);

        Socket sock = servidor.accept();
        // esperando a que el cliente se conecte
        System.out.println("Cliente conectado");

        DataInputStream entrada = new DataInputStream(sock.getInputStream());
        DataOutputStream salida = new DataOutputStream(sock.getOutputStream());

        //Ingrasar solicitud
        String solicitud = entrada.readUTF();  // Recibir solicitud del cliente
        //Covertir a Mayusculas
        solicitud = solicitud.toUpperCase();
        System.out.println("Solicitud recibida: " + solicitud);

        //Enviar respuesta
        salida.writeUTF(solicitud);  // Enviar respuesta al cliente

    }
}
