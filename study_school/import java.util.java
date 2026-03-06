import java.util.Scanner;

public class ChangeTheBigAndSmallOfWord {
	public static void main(String[] args) {
		
        System.out.print("请输入一段字符串: \n");
		Scanner scanner = new Scanner(System.in);
		String input = scanner.nextLine();
		String lower = input.toLowerCase();
		System.out.println(""+lower);
	}
}
