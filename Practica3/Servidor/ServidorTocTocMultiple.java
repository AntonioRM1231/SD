import java.net.*;
import java.io.*;

public class ServidorTocTocMultiple {
    public static void main(String[] args) throws IOException {
        //declaramos socket que recibira peticiones
        ServerSocket serverSocket1 = null;
        ServerSocket serverSocket2 = null;
        //variable para usarse en un ciclo infinito
        boolean escuchando = true;
        try {
            serverSocket1 = new ServerSocket(4444);
            serverSocket2 = new ServerSocket(5000);
        } catch (IOException e) {
            System.err.println("No puedo escuchar en el puerto: 4444.");
            System.err.println("No puedo escuchar en el puerto: 5000.");
            System.exit(-1);
        }
        int i=1;
        while (escuchando){
            // se crea un objeto del servidor multiple de hilos , 
            //pero su ejecucion NO se hace en el hilo main de este programa
            //se hace en una copia identidca de este codigo en ejecucion (un hilo nuevo)
	        new ServidorTocTocMultipleHilos(serverSocket1.accept(),serverSocket2.accept(),i).start(); //Este se lleva toda la chamba
            i++;
        }
            
        }
}
