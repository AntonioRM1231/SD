import java.io.*;
import java.net.*;
public class Cliente {
    public static void main(String[] args) {
        try {
            BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
            String tecladoS;
            while((tecladoS=teclado.readLine())!= null){
                if(Integer.parseInt(tecladoS)==0){
                    System.exit(1);
                }
                // Conectar al servidor en la dirección IP y el puerto correspondiente
                Socket socket = new Socket("localhost", 8888);
                // Enviar un mensaje al servidor
                OutputStream output = socket.getOutputStream();
                PrintWriter writer = new PrintWriter(output, true);
                writer.println(tecladoS);
                // Recibir la respuesta del servidor
                InputStream input = socket.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                System.out.println(reader.readLine());
            }
            // Cerrar el socket
            //socket.close();
        } catch (UnknownHostException e) {
            System.err.println("No conozco al host ");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("no se pudo obtener E/S para la conexion ");
            System.exit(1);
        } 
    }
}
