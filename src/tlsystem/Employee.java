package tlsystem;
import java.util.Scanner;
public class Employee {
    
    public void viewTask() {
        String Query = "SELECT t_name, t_des FROM employee_task";
        String[] Headers = {"Task Name", "Description"};
        String[] Columns = {"t_name", "t_des"};

        config conf = new config();
        conf.viewRecords(Query, Headers, Columns);
}
    
    public void empTask() {
        Scanner sc = new Scanner(System.in);
        config conf = new config();
        int id, id2;
        double prog;

        System.out.print("\nEnter your Account ID: ");
            while (!sc.hasNextInt()) {
                System.out.println("Invalid input. Please enter a valid ID.");
                sc.next();
                System.out.print("Enter your Account ID: ");
            }
            id = sc.nextInt();

        while((conf.getSingleValue("SELECT acc_id FROM employee_acc WHERE acc_id = ?", id)) == 0){
            System.out.println("Selected Account ID doesn't exist!");
            System.out.print("Enter your Account ID again: ");
            while (!sc.hasNextInt()) {
                System.out.println("Invalid input. Please enter a valid ID.");
                sc.next();
                System.out.print("Enter your Account ID again: ");
            }
            id = sc.nextInt();
        }

        System.out.print("Enter your Assigned Task ID: ");
        while (!sc.hasNextInt()) {
            System.out.println("Invalid input. Please enter a valid ID.");
            sc.next();
            System.out.print("Enter your Assigned Task ID: ");
        }
        id2 = sc.nextInt();

        while((conf.getSingleValue("SELECT t_id FROM employee_task WHERE t_id = ?", id2)) == 0){
            System.out.println("Selected Task ID doesn't exist!");
            System.out.print("Enter Task ID again: ");
            while (!sc.hasNextInt()) {
                System.out.println("Invalid input. Please enter a valid ID.");
                sc.next();
                System.out.print("Enter Task ID again: ");
            }
            id2 = sc.nextInt();
        }

        System.out.print("Enter progress to your task: ");
        while (!sc.hasNextDouble()) {
            System.out.println("Invalid input. Please enter a valid number.");
            sc.next();
            System.out.print("Enter progress to your task: ");
        }
        prog = sc.nextDouble();

        if (prog <= -1) {
            System.out.println("Error adding, progress percentage should be a positive number");
            System.out.print("Enter your progress again: ");
            while (!sc.hasNextDouble()) {
                System.out.println("Invalid input. Please enter a valid number.");
                sc.next();
                System.out.print("Enter progress to your task: ");
            }
            prog = sc.nextDouble();
        }
        
        String status = "Started";
        
        String sql1 = "UPDATE task_report SET tr_progress = ? WHERE acc_id = ? AND t_id = ?";
        conf.updateRecord(sql1, prog, id, id2);

        String sql2 = "UPDATE employee_task SET t_status = ? WHERE t_id = ?";
        conf.updateRecord(sql2, status, id2);
    }

    public void empTaskUpt() {
        Scanner sc = new Scanner(System.in);
        config conf = new config();
        int id, id2;
        double prog;

        System.out.print("\nEnter your Account ID: ");
        while (!sc.hasNextInt()) {
            System.out.println("Invalid input. Please enter a valid ID.");
            sc.next();
            System.out.print("Enter your Account ID: ");
        }
        id = sc.nextInt();

        while((conf.getSingleValue("SELECT acc_id FROM employee_acc WHERE acc_id = ?", id)) == 0){
            System.out.println("Selected Account ID doesn't exist!");
            System.out.print("Enter your Account ID again: ");
            while (!sc.hasNextInt()) {
                System.out.println("Invalid input. Please enter a valid ID.");
                sc.next();
                System.out.print("Enter your Accoubt ID again: ");
            }
            id = sc.nextInt();
        }

        System.out.print("Enter your Task ID: ");
        while (!sc.hasNextInt()) {
            System.out.println("Invalid input. Please enter a valid ID.");
            sc.next();
            System.out.print("Enter your Task ID: ");
        }
        id2 = sc.nextInt();

        while((conf.getSingleValue("SELECT t_id FROM employee_task WHERE t_id = ?", id2)) == 0){
            System.out.println("Selected Task ID doesn't exist!");
            System.out.print("Enter your Task ID again: ");
            while (!sc.hasNextInt()) {
                System.out.println("Invalid input. Please enter a valid ID.");
                sc.next();
                System.out.print("Enter your Task ID again: ");
            }
            id = sc.nextInt();
        }

        System.out.print("Enter Update progress of the Selected task: ");
        while (!sc.hasNextDouble()) {
            System.out.println("Invalid input. Please enter a valid number.");
            sc.next();
            System.out.print("Enter Update progress to the Selected task: ");
        }
        prog = sc.nextDouble();

        if (prog <= -1) {
            System.out.println("Error adding, progress percentage should be a positive number");
            System.out.print("Enter Update progress again: ");
            while (!sc.hasNextDouble()) {
                System.out.println("Invalid input. Please enter a valid number.");
                sc.next();
                System.out.print("Enter Update progress again: ");
            }
            prog = sc.nextDouble();
        }

        String sql = "UPDATE task_report SET tr_progress = ? WHERE acc_id = ? AND t_id = ?";
        conf.updateRecord(sql, prog, id, id2);
}
    
    public void selection(){
        Scanner sc = new Scanner(System.in);
        Employee emp = new Employee();
        Manager mgr = new Manager();
        Hr hr = new Hr();
        int opt;
        boolean exit = true;
        
    do{
        System.out.println("\n====== Select Option ======");
        System.out.println("| 1. Input Progress       |");
        System.out.println("| 2. Update Progress      |");
        System.out.println("| 3. View Task and Report |");
        System.out.println("| 4. Exit                 |");
        System.out.println("===========================");
        System.out.print("Enter Option: ");
            while (!sc.hasNextInt()) {
                System.out.println("Invalid input. Please enter a valid number.");
                sc.next();
                System.out.print("Enter Option: ");
            }
            opt = sc.nextInt();
        
        switch(opt){
            case 1:
                System.out.println("\nList of Employee's Account");
                hr.viewAcc();
                System.out.println("\nList of Task");
                mgr.viewTask();
                System.out.println("\nTask Report");
                hr.taskReport();
                emp.empTask();
                break;
            
            case 2:
                System.out.println("\nList of Employee's Account");
                hr.viewAcc();
                System.out.println("\nList of Task");
                mgr.viewTask();
                System.out.println("\nTask Report");
                hr.taskReport();
                emp.empTaskUpt();
                break;
                
            case 3:
                System.out.println("\nList of Task");
                hr.viewTask();

                do {
                    System.out.print("\nView Full Details of Employee Task? (yes/no): ");
                    String res = sc.next();

                    if (res.equalsIgnoreCase("yes")) {
                        emp.viewTask(); 
                        break; 
                    } else if (res.equalsIgnoreCase("no")) {
                        break;
                    } else {
                        System.out.println("Invalid input. Please enter a valid 'yes' or 'no'.");
                    }
                } while (true); 
                
                System.out.println("\nTask Report");
                hr.taskReport();
                break;
            
            case 4:
                do {
                    System.out.print("\nExit Selected.... type yes to continue: ");
                    String res = sc.next();

                    if (res.equalsIgnoreCase("yes")) {
                        exit = false;
                        break; 
                    } else if (res.equalsIgnoreCase("no")) {
                        break;
                    } else {
                        System.out.println("Invalid input. Please enter a valid 'yes' or 'no'.");
                    }
                } while (true); 
                break;
                
            default: 
                System.out.println("Invalid Option, There's no number "+opt+" in option!");
          }
        }while(exit);
            System.out.print("Returning to main menu....\n");
    }
}
