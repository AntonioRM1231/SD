import java.io.*;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class FileTransferClient{
    
    public static void main(String[] args) throws RemoteException, NotBoundException, IOException {
        /*String filename = "archivotransferir.txt";
        File file = new File(filename);
        long fileSize = file.length();
        long chunkSize = fileSize / 10;*/
        int idCli = (int)(Math.random()*10000);
        String nameCli = "client-"+idCli;
        String filename = "archivotransferir.txt";
        String filename2 = "archivotransferir2.txt";
        Registry registry = LocateRegistry.getRegistry("localhost", 1099);
        FileTransfer stub = (FileTransfer) registry.lookup("FileTransfer");
        ArrayList<Fragmento> ListFrag = new ArrayList<Fragmento>();
        ListFrag.add(0, new Fragmento(null, -1, 0));
        try (FileOutputStream fos = new FileOutputStream(filename2, true)) {
            int sizeS=0;
            int fid=-1;
            int contador=0;
            int f = 0;
            float p = 0;
            while(true) {
                String[] trama = stub.transferFile(filename,nameCli, fid,f,contador).split("-");
                int idG = Integer.parseInt(trama[0]);
                int sizeF=Integer.parseInt(trama[1]);
                byte[] bt=trama[2].getBytes();
                int flag=0;
                for (Fragmento fragmento : ListFrag) {
                    if(fragmento.getId()==idG){
                        flag=1;
                        f=0;
                        break;
                    }
                }
                if(flag==0){
                    contador++;
                    ListFrag.add(new Fragmento(bt, idG, sizeF));
                    fid=idG;
                    f=1;
                    if(ListFrag.size()==sizeF+1){
                        String car = stub.transferFile(filename, nameCli, idG, 2,contador);
                        break;
                    }
                }
                sizeS=sizeF;
                //fos.write(bt);
            }
            for(int i=0; i<sizeS; i++){
                for (Fragmento frac : ListFrag) {
                    if(i==frac.getId()){
                        //System.out.println(frac.getId()+"-"+new String(frac.getBuffer()));
                        fos.write(frac.getBuffer());
                        break;
                    }
                }
            } 
           
        } catch (IOException e) {
            System.err.println("Error writing file: " + e.getMessage());
        }
        /*try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file))) {
            byte[] buffer = new byte[(int) chunkSize];
            int bytesRead = 0;
            while ((bytesRead = bis.read(buffer)) != -1) {
                stub.transferFile(buffer, file.getName());
                System.out.println("Seha enviado un 10% del archivo\n");
            }
        }*/
    }
}
