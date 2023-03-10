public class division implements Runnable{
    int num1;
    int num2;
    

    public division(int num1, int num2){
        this.num1 = num1;
        this.num2 = num2;
    }
    public void run(){
        System.out.println("El cociente de "+String.valueOf(num1)+"/"+String.valueOf(num2)+" es:"+Float.toString(num1/num2));
    }
}
