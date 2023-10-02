package TCP;

import java.util.Scanner;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.DataInputStream;
import java.io.DataOutputStream;

public class clienteTCP {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int puerto = 7777;
        String host = "192.168.16.5";

        Socket sock = new Socket(host, puerto);

        DataInputStream entrada = new DataInputStream(sock.getInputStream());
        DataOutputStream salida = new DataOutputStream(sock.getOutputStream());

        //Ingrasar solicitud
        System.out.println("Ingrese solicitud: ");
        String cadena = sc.nextLine();
        salida.writeUTF(cadena);  // Enviar solicitud al servidor

        //Recibir respuesta
        String respuesta = entrada.readUTF();  // Recibir respuesta del servidor
        System.out.println("Respuesta recibida: " + respuesta);

    }
}
//adios