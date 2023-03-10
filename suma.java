/** muy importante recuerden que aquino hay main, porque este es un hilo que alguien mas debe mandar ejecutar 
*/
public class suma  extends Thread {
    int x;
    int y;
    
    public suma (int x, int y)
    {
        this.x = x;
        this.y = y;
        }
    
    
    @Override
    public void run()
        {
       
            
            System.out.println("El resultado de la suma es: "+(x+y));
            
        
    this.yield();
     }
}

