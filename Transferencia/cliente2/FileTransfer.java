import java.rmi.Remote;
import java.rmi.RemoteException;

public interface FileTransfer extends Remote {
    public String transferFile(String fileName,String name, int id,int flag, int por) throws RemoteException;
    
}
