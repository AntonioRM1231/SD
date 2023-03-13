import java.io.*;
import java.net.*;

public class ClienteTocToc {
    public static void main(String[] args) throws IOException {
        //Declaramos el socket, escritor y lector
        Socket TocTocSocket = null; // declaramos un socket para recibir y enviar datos
        PrintWriter EscritorEnSocket = null;  //declaramos un escritor para escribir los datos que se enviaran en el socket
        BufferedReader LectorDeSocket = null;  /* declaramos un lector de flujo de texto (el flujo es una secuencia de entrada de caracteres, 
            //almacenando caracteres en un buffer para proporcionar una lectura eficiente 
            //de caracteres, líneas y matrices.*/
        

        try {
            //abrimos el socket TocTocSocket para hacer peticiones al servidor //podremos abrir tres sockets para comunicarnos con los tres servidores 
            TocTocSocket = new Socket("127.0.0.1", 4444); //Datos socket cliente
            System.out.println("Estamos listos en el puerto " + 4444);
            ////asignamos a lector y escritor el flujo de datos del socket
            EscritorEnSocket = new PrintWriter(TocTocSocket.getOutputStream(), true);
            LectorDeSocket = new BufferedReader(new InputStreamReader(TocTocSocket.getInputStream()));
        } catch (UnknownHostException e) {
            System.err.println("No se conoce al anfitrión: Juanito.");//No se encontro la operación
            System.exit(1);
        } catch (IOException e) {
            System.err.println("No se pudo obtener E / S para la conexión a: Juanito.");
            System.exit(1);
        }
// abrimos un lector , para leer el texto desde consola 
        BufferedReader EntradaTeclado = new BufferedReader(new InputStreamReader(System.in)); //aqui leemos la operación y sus valores
        //declaramos cadenas para almacenar el texto recibido del servidor 
        //y otra para el texto recibido desde consola por el usuario
        String DelServidor; //Pasar a int
        String DelUsuario;                                                                                          //Todo esto no va chanse si va pero se comenta el menje
// leemos lo que dice el servidor , y si hay algo escrito procedemos a ingresar al ciclo while                      //Todo esto no va
        while ((DelServidor = LectorDeSocket.readLine()) != null) { //recibimos lo que dice uno de los servidores   //Todo esto no va
            System.out.println("El Servidor dice: " + DelServidor);
            DelUsuario = EntradaTeclado.readLine();
            if (DelUsuario != null) {
                System.out.println("El Cliente dice: " + DelUsuario);
                EscritorEnSocket.println(DelUsuario);
                 //if(DelServidor==null){

                 //   } else { System.out.println("El Servidor dice: " + DelServidor); }
                                                          //Todo esto no va
           // si el usuario escribe Adios, significa que hay que cerrar la transmision de datos                     //Todo esto no va
          // if (DelServidor.equals("Adios."))                                                                       //Todo esto no va
               // break;                                                                                              //Todo esto no va
          // leeemos desde la consola del usuario
           
            // si si leimos algo ( quiere decir que el usuario escribio algo) 
	   
	    }
        }

        EscritorEnSocket.close();
        LectorDeSocket.close();
        EntradaTeclado.close();
        TocTocSocket.close();
    }
}
//vamos a configurar el servidormultiple para que reciba una cadena de caracteres 
//Vamos a configrar el clientetoctoc

