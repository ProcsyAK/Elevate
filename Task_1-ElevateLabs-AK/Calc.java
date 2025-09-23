
import static java.lang.System.exit;
import java.util.Scanner;

public class Calc {
     
    static Scanner scanner= new Scanner(System.in);

    public static void main(String[] args) {
        
        
        
        while (true) { 
            System.out.println(
                "\nOperations \n\n 1 - Add \n 2 - Subtract \n 3 - Multiply \n 4 - Divide \n 5 - Modulo \n 6 - Exit \nselect operation - " 
            );
            int operation = scanner.nextInt();

            switch (operation) {
                case 1:
                    add();
                    Continue();
                    break;
                case 2:
                    subtract();
                    Continue();
                    break;
                case 3:
                    multiply();
                    Continue();
                    break;
                case 4:
                    divide();
                    Continue();
                    break;
                case 5:
                    mod();
                    Continue();
                    break;
                case 6:
                    System.exit(0);
                default:
                    System.out.println("Invalid Operation Selection");
            }

        }
    }
    public static void add(){
        
	System.out.println("Enter Two values \t");
        System.out.println("a: ");
        long a =scanner.nextLong();
        System.out.println("b: ");
        long b =scanner.nextLong();
        System.out.println(" = "+(a + b));
	    
    }

    private static void subtract() {
	
	    System.out.println("Enter Two values \t");
        System.out.println("a: ");
        long a =scanner.nextLong();
        System.out.println("b: ");
        long b =scanner.nextLong();
        System.out.println(" = "+(a-b));    
	    
    }

    private static void multiply() {
	
	System.out.println("Enter Two values \t");
        System.out.println("a: ");
        long a =scanner.nextLong();
        System.out.println("b: ");
        long b =scanner.nextLong();
        System.out.println(" = "+(a*b));    
	    
    }

    private static void divide() {
	
	    System.out.println("Enter Two values \t");
        System.out.println("a: ");
        long a =scanner.nextLong();
        System.out.println("b: ");
        long b =scanner.nextLong();
	    if(b>0)
        System.out.println(" = "+(a/b)); 
	    else{
	    System.out.println(" 😒Divisor cannot be zero \n"); }
	    
    }

    private static void mod() {
	
	System.out.println("Enter Two values \n");
        System.out.println("a: ");
        long a =scanner.nextLong();
        System.out.println("b: ");
        long b =scanner.nextLong();
        System.out.println(" = "+(a%b));   
	     
    }
    private static void Continue(){
        
        while (true) { 
            int i=0;
            System.out.println("\nwant to continue (1)\nexit (0) \n");
            int c = scanner.nextInt();
            switch (c) {
                case 1 :
                    break;
                case 0:
                    exit(0);
                default:
                    System.out.println("invalid input");
                    i=1;
            }
            if(i == 0)
                break;

        }
        

    }
}
