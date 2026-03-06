
import java.util.*;
public class InputFloat {
    public static void main(String[] args) {
        double a,b,c;
        System.out.println("从键盘输入一个浮点数");
        a=0;
        Scanner reader = new Scanner(System.in) ;
        a =reader.nextDouble();
       
        /*try{
            a=(double)System.in.read();
        }catch(IOException e){
        }*/
        b = a*a;
        c = a*a*a;
        System.out.printf("这个数的平方是%.4f\n",b);
        System.out.printf("这个数的立方是%.4f\n",c);
    }
}
