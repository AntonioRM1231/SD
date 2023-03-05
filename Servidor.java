import java.net.*;
import java.io.*;

public class Servidor {
    public static void main(String[] args) throws IOException {
        // Crea el socket del servidor y especifica el número de puerto
        ServerSocket serverSocket = new ServerSocket(1234);
        
        // Espera a que un cliente se conecte
        System.out.println("Esperando conexión del cliente...");
        Socket clientSocket = serverSocket.accept();
        System.out.println("Cliente conectado!");

        // Crea los streams de entrada y salida para el socket
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        // Lee mensajes del cliente y responde con un mensaje de confirmación
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            System.out.println("Mensaje recibido del cliente: " + inputLine);
            out.println("Mensaje recibido: " + inputLine);
        }

        // Cierra los streams y el socket
        out.close();
        in.close();
        clientSocket.close();
        serverSocket.close();
    }
}
