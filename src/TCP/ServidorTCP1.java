package TCP;

import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ServidorTCP1 {
    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.out.println("Uso: java ServidorTCP <puerto>");
            return;
        }


        int puerto = Integer.parseInt(args[0]);
        //int puerto = 49152;
        ServerSocket servidor = new ServerSocket(puerto);
        System.out.println("Servidor TCP en ejecución en el puerto " + puerto);

        while (true) {
            Socket cliente = servidor.accept();
            System.out.println("Cliente conectado desde " + cliente.getInetAddress());

            // Obtener la fecha y hora actual
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String fechaHora = dateFormat.format(new Date());

            // Enviar la fecha y hora al cliente
            PrintWriter out = new PrintWriter(cliente.getOutputStream(), true);
            out.println("Fecha y hora actual: " + fechaHora);

            // Cerrar la conexión con el cliente
            cliente.close();
        }
    }
}
