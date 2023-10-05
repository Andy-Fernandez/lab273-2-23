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

        // Pedir Dato
        DataInputStream entrada = new DataInputStream(sock.getInputStream());
        DataOutputStream salida = new DataOutputStream(sock.getOutputStream());

        //Ingrasar solicitud
        String solicitud = entrada.readUTF();  // Recibir solicitud del cliente
        //Covertir a Mayusculas
        solicitud = solicitud.toUpperCase();
        System.out.println("Solicitud recibida: " + solicitud);

        //Enviar respuesta
        salida.writeUTF(solicitud);  // Enviar respuesta al cliente

        // ahroa pare múltiples clientes usamos un while
        while (true) {
            Socket sock2 = servidor.accept();
            // esperando a que el cliente se conecte
            System.out.println("Cliente conectado");

            DataInputStream entrada2 = new DataInputStream(sock2.getInputStream());
            DataOutputStream salida2 = new DataOutputStream(sock2.getOutputStream());

            //Ingrasar solicitud
            String solicitud2 = entrada2.readUTF();  // Recibir solicitud del cliente
            //Covertir a Mayusculas
            solicitud2 = solicitud2.toUpperCase();
            System.out.println("Solicitud recibida: " + solicitud2);

            //Enviar respuesta
            salida2.writeUTF(solicitud2);  // Enviar respuesta al cliente
        }

        //las difercias entre el código una solicitud y varias es que en el caso de una solicitud
        //el servidor recibe la solicitud, la procesa y envía la respuesta, y luego se cierra la conexión


    }
}
