import java.io.*;
import java.util.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class FileTransferServer extends UnicastRemoteObject implements FileTransfer {
    public FileTransferServer() throws RemoteException {
        super();
    }

    @Override
    // public void transferFile(byte[] data, String filename) throws RemoteException {
    //     try (FileOutputStream fos = new FileOutputStream(filename, true)) {
    //         fos.write(data);
    //     } catch (IOException e) {
    //         System.err.println("Error writing file: " + e.getMessage());
    //     }
    // }
    public  String transferFile(String filename,String nameCli, int id, int f, int cont) throws RemoteException{
        File file = new File(filename);
        long fileSize = file.length();
        long chunkSize = fileSize / 10;
        //System.out.println("filesize:"+fileSize);
        //System.out.println("chunkSize (fileSize/10):"+chunkSize);
        int idRan = (int)(Math.random()*(fileSize/chunkSize));
        //System.out.println(idRan);
        if(f==1){
            System.out.println("Soy el cliente "+nameCli+" y recibi el fragmento "+id+";"+cont+" de "+ (fileSize/chunkSize));
        }else if(f==2){
            System.out.println("Soy el cliente "+nameCli+" y recibi el fragmento "+id+" se cargo todo el archivo");
        }
        
        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file))) {
            byte[] buffer = new byte[(int) chunkSize];
            int bytesRead = 0;
            int c = 0;
            while ((bytesRead = bis.read(buffer)) != -1) {
                if(c==idRan){
                    break;
                }else{
                    c++;
                }
                
            }
            //String bt=new String(buffer);
            String trama = c+"-"+(fileSize/chunkSize)+"-"+new String(buffer);
            return trama;
        }catch (IOException e) {
            System.err.println("Error writing file: " + e.getMessage());
            return null;
        }
    }
    public static void main(String[] args) throws RemoteException {
        try {
            FileTransferServer server = new FileTransferServer();
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.bind("FileTransfer", server);
            System.out.println("Server ready");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
