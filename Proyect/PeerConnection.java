import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;
import java.util.List;

public class PeerConnection extends UnicastRemoteObject implements TorrentPeer,Runnable{
    public String clientID;
    public String hostIP;
    public int port;
    public TorrentTrack server;


    @Override
    public String toString() {
        return "PeerConnection [clientID=" + clientID + ", hostIP=" + hostIP + ", port=" + port + "]";
    }


    public PeerConnection(String clientID, String hostIP, int port, TorrentTrack server)throws RemoteException{
        this.clientID=clientID;
        this.hostIP=hostIP;
        this.port=port;
        this.server=server;
        server.addPeer(this,this.clientID+":"+this.hostIP+":"+port);
    }

    public String getClientID() {
        return clientID;
    }

    public String getHostIP() {
        return hostIP;
    }

    public int getPort() {
        return port;
    }

    public void setClientID(String clientID) {
        this.clientID = clientID;
    }

    public void setHostIP(String hostIP) {
        this.hostIP = hostIP;
    }

    public void setPort(int port) {
        this.port = port;
    }
    public void saludo(String mensaje) throws RemoteException{
        System.out.println(mensaje);
    }
    public void run(){
        try{
            //ENTRADA DE TECLADO
            Scanner teclado = new Scanner(System.in);
            //MENU
            int answ=0;
            while(true){
                System.out.println("1. Ver");
                System.out.println("2. Mensaje");
                System.out.println("3. Salir");
                System.out.println("Su opcion:");
                answ=Integer.parseInt(teclado.nextLine());
                if(answ==1){
                    System.out.println("Aqui se veran lo otros clientes");
                    //server.viewPeers();
                    List<String> peerInf = server.viewPeers();
                    for (String pi : peerInf) {
                        System.out.println(pi);
                    }
                    /* String usId;
                    System.out.println("ID del usuario a enviar");
                    usId=teclado.nextLine();
                    if(usId.equals("A")){
                        Registry registryA = LocateRegistry.getRegistry("127.0.0.1",5000);
                        TorrentPeer stubA = (TorrentPeer) registry.lookup("TorrentPeer");
                        stubA.saludo("B", "127.0.0.1", 5050);
                    }else if(usId.equals("B")){
                        Registry registryB = LocateRegistry.getRegistry("127.0.0.1",5050);
                        TorrentPeer stubB = (TorrentPeer) registry.lookup("TorrentPeer");
                        stubB.saludo("A", "127.0.0.1", 5000);
                    } */
                }else if(answ==2){
                    //REGISTRO DE SERVER
                    /* String mensaje; 
                    Scanner t = new Scanner(System.in);
                    mensaje = t.nextLine();
                    server.saludo(clientID+":"+mensaje); */  
                    Scanner t1 = new Scanner(System.in);
                    Scanner t2 = new Scanner(System.in);
                    System.out.println("Direccion IP:");
                    String ip = t1.nextLine();
                    System.out.println("Puerto:");
                    int puerto = Integer.parseInt(t2.nextLine());
                    Registry registry = LocateRegistry.getRegistry(ip, puerto);
                    TorrentPeer serverP = (TorrentPeer) registry.lookup("TorrentPeer");
                    serverP.saludo(clientID+":"+hostIP+":"+port);
                }else if(answ==3){
                    System.exit(0);
                }
            }

        }catch(Exception e){
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
        
    }

    
    
}
