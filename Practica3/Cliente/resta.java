
/** muy importante recuerden que aquino hay main, porque este es un hilo que alguien mas debe mandar ejecutar 
*/
public class resta extends Thread {
    int x;
    int y;
    
    public resta( int x,int y)
    {
        this.x = x;
        this.y = y;
        }
    
    
    @Override
    public void run()
        {
            
            System.out.println("El resultado de la resta es: "+ (x-y));
        
           
    this.yield();
     }
}

