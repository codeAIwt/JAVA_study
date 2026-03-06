/*public class MainClass {
    public static void main(String args[]) {
		char a='A';
		int m=(int)a;
		boolean b=false;
		int i=(int)b;
		float f=1000.34;
		int n=(int)f;
		long l=100;
		int p=(int)l;
		double d=1000.34;
		int q=(int)d;
		int t=97;
		char c=(char)t;
        System.out.printf("%c\n",c);
		
    }
}*/
import java.util.Scanner;  
  
public class Application {  
    public static void main(String args[]) {  
        int a, b;  
        Scanner reader = new Scanner(System.in);  
        a = reader.nextInt();  
        b = reader.nextInt();  
  
        // 检查 b 是否是 2 的幂且不为 0  
        if (b <= 0 || (b & (b - 1)) != 0) {  
            System.out.println(0); // 如果不是 2 的幂或 b 为 0，则输出 0  
        } else {  
            int c = Integer.numberOfTrailingZeros(b); // 获取 b 是 2 的几次幂  
            System.out.printf("a/b=%d", a >> c); // 对 a 进行右移 c 位操作并输出结果  
        }  
    }  
}
