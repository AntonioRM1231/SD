import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.SourceDataLine;

public class TrackerConnection extends UnicastRemoteObject implements TorrentTrack {
    public ArrayList<TorrentPeer> peers;
    public List<String> kys;

    public TrackerConnection() throws RemoteException{
        peers = new ArrayList<TorrentPeer>();
        kys = new ArrayList<String>();
    }
    @Override
    public synchronized void addPeer(TorrentPeer peer, String ky) throws RemoteException{
        this.peers.add(peer);
        this.kys.add(ky);
        System.out.println("Se ha anadido");
    }
    @Override
    public synchronized void saludo(String mensaje)throws RemoteException{
        int i =0;
        while(i<peers.size()){
            peers.get(i++).saludo(mensaje);
        }
    }
    @Override
    public List<String> viewPeers() throws RemoteException{
        return kys;
    }
}
