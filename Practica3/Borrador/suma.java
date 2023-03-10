public class suma implements Runnable{
    int num1;
    int num2;
    

    public suma(int num1, int num2){
        this.num1 = num1;
        this.num2 = num2;
    }
    public void run(){
        System.out.println("La suma de "+String.valueOf(num1)+"+"+String.valueOf(num2)+" es:"+String.valueOf(num1+num2));
    }
}
