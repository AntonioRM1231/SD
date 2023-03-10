

/**
 *
*/
public class AppHilos {

    /**
     * @los parametros de args recuerden que los recibimos de lo que escribimso en consola 
     */
    public static void main(String[] args) {
        
        
        suma operacionSuma= new suma(5,3);
        resta operacionResta= new resta(5,3);
        multiplicacion operacionMultiplicacion= new multiplicacion(5,3);
        division operacionDivision= new division(5,3);
   
    operacionSuma.start();
    operacionResta.start();
    operacionMultiplicacion.start();
    operacionDivision.start();
    
    //System.out.println("La carrera ha terminado");
    
    
    }
}


