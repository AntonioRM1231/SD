public class Operaciones{

    public static void main(String[] args){
        int num1=2;
        int num2=2;
        Runnable sumar = new suma(num1,num2);
        Runnable restar = new resta(num1,num2);
        Runnable multiplicar = new multiplicacion(num1,num2);
        Runnable dividir = new division(num1,num2);
        Thread hilo1 = new Thread(sumar);
        Thread hilo2 = new Thread(restar);
        Thread hilo3 = new Thread(multiplicar);
        Thread hilo4 = new Thread(dividir);
        hilo1.start();
        hilo2.start();
        hilo3.start();
        hilo4.start();
    }
}