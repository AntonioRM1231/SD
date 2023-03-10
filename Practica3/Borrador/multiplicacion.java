public class multiplicacion implements Runnable{
    int num1;
    int num2;
    

    public multiplicacion(int num1, int num2){
        this.num1 = num1;
        this.num2 = num2;
    }
    public void run(){
        System.out.println("El producto de "+String.valueOf(num1)+"x"+String.valueOf(num2)+" es:"+String.valueOf(num1*num2));
    }
}
