/** muy importante recuerden que aquino hay main, porque este es un hilo que alguien mas debe mandar ejecutar 
*/
public class division extends Thread {
    int x;
    int y;
    
    public division( int x,int y)
    {
        this.x = x;
        this.y = y;
        }
    
    
    @Override
    public void run()
        {
            
            System.out.println("El resultado de la division es: "+ (x*1.0/y));
        
           
    this.yield();
     }
}

