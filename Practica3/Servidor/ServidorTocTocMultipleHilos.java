import java.net.*;
import java.io.*;

public class ServidorTocTocMultipleHilos extends Thread {
    private Socket socket1 = null;
	private Socket socket2 = null;
	public int id;

    public ServidorTocTocMultipleHilos(Socket socketenviada,Socket socketenviada2, int id) {
	//ejecuta el contructor de la clase Thread ( con esto hace propio
	 //todo el codigo de la clase Thread)
	super("ServidorTocTocMultipleHilos");
	//ahora le decimos al objeto socket de esta clase ServidorTocTocMultiplesHilos
	//que va a tener todos los valores recibidos en la variable socketenviada 
	// que es la que envio el ServidorTocTocMultiple 
	this.socket1 = socketenviada;
	this.socket2 = socketenviada2;
	this.id = id;
    }

// este metodo es el equivalente al main, ( estamos en un hilo por eso no hay main)
    //es un metodo que cada hilo lo ejecutara de forma independiente
    // puede haber muchos hilos , por supuesto
    public void run() {

	try {
		PrintWriter out1=null;
		BufferedReader in1=null;
		PrintWriter out2=null;
		BufferedReader in2=null;
		if(id==1){
			//abrimos el escritor del Socket 
			out1 = new PrintWriter(socket1.getOutputStream(), true);
			//abirmos el lector del socket
			in1 = new BufferedReader(new InputStreamReader(socket1.getInputStream()));
			//declaramos variables de paso para almacenar temporalmente, cada linea de
			// lo que se escribe y lee desde el socket
			String LineaDeEntrada, LineaDeSalida;
			//creamos el objeto del protocolo para poder procesar el texto  de mensajes enviados
			ProtocoloTocToc ptt = new ProtocoloTocToc();
			// procesamos
			LineaDeSalida = ptt.processInput(null);
			out1.println(id);
			out2= new PrintWriter(socket2.getOutputStream(), true);
			while ((LineaDeEntrada = in1.readLine()) != null) {
				LineaDeSalida = ptt.processInput(LineaDeEntrada);
				out2.println(LineaDeSalida);
				out1.println("Se escribio en cliente remoto\n");
				//if (LineaDeSalida.equals("Adios"))
				 //   break;
			}
		}else if (id == 2){
			//abrimos el escritor del Socket 
			out2= new PrintWriter(socket2.getOutputStream(), true);
			//abirmos el lector del socket
			in2 = new BufferedReader(new InputStreamReader(socket2.getInputStream()));
			//declaramos variables de paso para almacenar temporalmente, cada linea de
			// lo que se escribe y lee desde el socket
			String LineaDeEntrada, LineaDeSalida;
			//creamos el objeto del protocolo para poder procesar el texto  de mensajes enviados
			ProtocoloTocToc ptt = new ProtocoloTocToc();
			// procesamos
			LineaDeSalida = ptt.processInput(null);
			out2.println(id);
			out1 = new PrintWriter(socket1.getOutputStream(), true);
			while ((LineaDeEntrada = in2.readLine()) != null) {
				LineaDeSalida = ptt.processInput(LineaDeEntrada);
				out1.println(LineaDeSalida);
				out2.println("Se escribio en cliente remoto\n");
				//if (LineaDeSalida.equals("Adios"))
				 //   break;
			}
		}
		
		// if(id==1){
		// 	out1.println(id);
		// 	while ((LineaDeEntrada = in1.readLine()) != null) {
		// 		LineaDeSalida = ptt.processInput(LineaDeEntrada);
		// 		out2.println(LineaDeSalida);
		// 		//if (LineaDeSalida.equals("Adios"))
		// 		 //   break;
		// 	}
		// 	//out.close();
		// 	//in.close();
			
		// }else if(id==2){
		// 	out2.println(id);
		// 	while ((LineaDeEntrada = in2.readLine()) != null) {
		// 		LineaDeSalida = ptt.processInput(LineaDeEntrada);
		// 		out1.println(LineaDeSalida);
		// 		//if (LineaDeSalida.equals("Adios"))
		// 		 //   break;
		// 	}
		// 	//out.close();
		// 	//in.close();
		// }
	    //socket1.close();
		//socket2.close();

	} catch (IOException e) {
	    e.printStackTrace();
	}
    }
}
