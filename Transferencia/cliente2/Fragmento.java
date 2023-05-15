import java.io.*;
public class Fragmento {
    public byte[] buffer;
    public int id;
    public int size;

    public Fragmento(){

    }
    public Fragmento(byte[] buffer, int id, int size){
        this.buffer=buffer;
        this.id=id;
        this.size=size;
    }
    public void setBuffer(byte[] buffer){
        this.buffer=buffer;
    }
    public void setId(int id){
        this.id=id;
    }
    public void setSize(int size){
        this.size=size;
    }
    public byte[] getBuffer(){
        return this.buffer;
    }
    public int getId(){
        return this.id;
    }
    public int getSize(){
        return this.size;
    }
}
