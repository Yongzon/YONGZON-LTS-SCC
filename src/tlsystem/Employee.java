package tlsystem;
import java.util.Scanner;
public class Employee {
    public void empTask() {
        Scanner sc = new Scanner(System.in);
        config conf = new config();

        System.out.print("\nEnter your Account ID: ");
        int id = sc.nextInt();

        while((conf.getSingleValue("SELECT acc_id FROM employee_acc WHERE acc_id = ?", id)) == 0){
            System.out.println("Selected Account ID doesn't exist!");
            System.out.print("Enter your Account ID again: ");
            id = sc.nextInt();
        }

        System.out.print("Enter your Assigned Task ID: ");
        int id2 = sc.nextInt();

        while((conf.getSingleValue("SELECT t_id FROM employee_task WHERE t_id = ?", id2)) == 0){
            System.out.println("Selected Task ID doesn't exist!");
            System.out.print("Enter Task ID again: ");
            id2 = sc.nextInt();
        }

        System.out.print("Enter progress to your task: ");
        double prog = sc.nextDouble();

        if (prog <= -1) {
            System.out.println("Error adding, progress percentage should be a positive number");
            System.out.print("Enter your progress again: ");
            prog = sc.nextDouble();
        }
        
        String sql = "UPDATE task_report SET tr_progress = ? WHERE acc_id = ? AND t_id = ?";
        conf.updateRecord(sql, prog, id, id2);
    }

    public void empTaskUpt() {
        Scanner sc = new Scanner(System.in);
        config conf = new config();

        System.out.print("\nEnter your Account ID: ");
        int id = sc.nextInt();

        while((conf.getSingleValue("SELECT acc_id FROM employee_acc WHERE acc_id = ?", id)) == 0){
            System.out.println("Selected Account ID doesn't exist!");
            System.out.print("Enter your Account ID again: ");
            id = sc.nextInt();
        }

        System.out.print("Enter your Task ID: ");
        int id2 = sc.nextInt();

        while((conf.getSingleValue("SELECT t_id FROM employee_task WHERE t_id = ?", id2)) == 0){
            System.out.println("Selected Task ID doesn't exist!");
            System.out.print("Enter your Task ID again: ");
            id2 = sc.nextInt();
        }

        System.out.print("Enter Update progress of the Selected task: ");
        double prog = sc.nextDouble();
        
        if (prog <= -1) {
            System.out.println("Error updating, progress percentage should be a positive number");
            System.out.print("Enter your updated progress again: ");
            prog = sc.nextDouble();
        }

        String sql = "UPDATE task_report SET tr_progress = ? WHERE acc_id = ? AND t_id = ?";
        conf.updateRecord(sql, prog, id, id2);
}
    
    public void selection(){
        Scanner sc = new Scanner(System.in);
        Employee emp = new Employee();
        Hr hr = new Hr();
        int act;
        boolean exit = true;
        
    do{
        System.out.println("\n==== Select Option ====");
        System.out.println("| 1. Input Progress   |");
        System.out.println("| 2. Update Progress  |");
        System.out.println("| 3. View Task Report |");
        System.out.println("| 4. Exit             |");
        System.out.println("=======================");
        System.out.print("Enter Option: ");
        act = sc.nextInt();
        
        switch(act){
            case 1: 
                System.out.println("\nList of Employee's Account");
                hr.viewAcc();
                System.out.println("\nList of Task");
                hr.viewTask();
                System.out.println("\nTask Report");
                hr.taskReport();
                emp.empTask();
                break;
            
            case 2:
                System.out.println("\nList of Employee's Account");
                hr.viewAcc();
                System.out.println("\nList of Task");
                hr.viewTask();
                System.out.println("\nTask Report");
                hr.taskReport();
                emp.empTaskUpt();
                break;
                
            case 3:
                System.out.println("\nTask Report");
                hr.taskReport();
                break;
            
            case 4:
                System.out.print("Exit Selected.... type yes to continue: ");
                    String resp = sc.next();
                    if(resp.equalsIgnoreCase("yes")){
                    exit = false;
                    }
                break;
                
            default: 
                System.out.println("Invalid Option");
          }
        }while(exit);
    }
}
