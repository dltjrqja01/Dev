import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner1 = new Scanner(System.in);
        while (true) {
            System.out.println("1.Input Infix 2.Exit");
            int res = scanner1.nextInt();
            switch(res){
                case 1:
                    System.out.print("Input Infix: ");
                    Scanner scanner2 = new Scanner(System.in);
                    String str = scanner2.nextLine();
                    char[] infix = str.toCharArray();

                    Calculate calculate = new Calculate();

                    calculate.change(infix);
                    System.out.print("Invert: ");
                    calculate.invertPrint();

                    System.out.print("cal: ");
                    calculate.cal();
                    calculate.print();
                    break;

                default:
                    return;
            }

        }
    }
}