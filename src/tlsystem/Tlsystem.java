package tlsystem;
import java.util.Scanner;
public class Tlsystem {
     
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int cho;
        boolean exit = true;
        
        do {
            System.out.println("\n======= Who's Using the System? ========");
            System.out.println("| 1. HR                                |");
            System.out.println("| 2. MANAGER                           |");
            System.out.println("| 3. EMPLOYEE                          |");
            System.out.println("| 4. EXIT                              |");
            System.out.println("========================================");
            System.out.print("Enter Choice: "); 
            while (!sc.hasNextInt()) {
                System.out.println("Invalid input. Please enter a valid number.");
                sc.next();
                System.out.print("Enter Choice: ");
            }
            cho = sc.nextInt();
            
            switch(cho) {
                case 1:
                    Hr hr = new Hr();
                    hr.Humanr();
                    break;
                
                case 2:
                    Manager mg = new Manager();
                    mg.Manager();
                    break;

                case 3:
                    Employee ep = new Employee();
                    ep.selection();
                    break;
                
                case 4:
                    System.out.print("Do you want to exit? (yes/no): ");
                    String resp = sc.next();
                    if(resp.equalsIgnoreCase("yes")) {
                        exit = false;
                    }
                    break;
                
                default:
                    System.out.println("Choice Error, There's no such number.");
            }  
        } while(exit);
        
        System.out.println("Thank you for using the TLS System :)");
    }
}