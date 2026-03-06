import  java.util.Scanner;  
public class Number {  
    public static void main(String args[]) {  
        int a, b;  
        System.out.println("请输入两个数：");
        Scanner reader = new Scanner(System.in);  
        a = reader.nextInt();  
        b = reader.nextInt();  
   
        if (b <= 0 || (b & (b - 1)) != 0) {  

            System.out.println("Error: b must be a power of 2 and greater than 0");  
            return;
        }  
  
        int c = (int)(Math.log(b) / Math.log(2));    
        System.out.printf("a/b=%d", a >> c);  
    }

    @Override
    public String toString() {
        return "Application []";
    }  
}