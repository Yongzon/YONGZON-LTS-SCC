package tlsystem;

import java.util.Scanner;

public class Manager {
    public void assTask() {
       Scanner sc = new Scanner(System.in);
       config conf = new config();
       
       System.out.print("\nEnter Account ID to Assign Task: ");
       int id = sc.nextInt();
       
       while((conf.getSingleValue("SELECT acc_id FROM employee_acc WHERE acc_id = ?", id)) == 0){
        System.out.println("Selected Account ID doesn't exist!");
        System.out.print("Enter Account ID again: ");
        id = sc.nextInt();
    }
       System.out.print("Enter Task ID to Assign in the Account: ");
       int id2 = sc.nextInt();

       while((conf.getSingleValue("SELECT t_id FROM employee_task WHERE t_id = ?", id2)) == 0){
        System.out.println("Selected Task ID doesn't exist!");
        System.out.print("Enter Task ID again: ");
        id2 = sc.nextInt();
    }
       
       double prog = 0;
       String  com = "to be inputted";
       
       String sql = "INSERT INTO task_report (acc_id, t_id, tr_progress, t_com) VALUES (?, ?, ?, ?)";
       conf.addRecord(sql, id, id2, prog, com);
}
    
    public void updateTask(){
        Scanner sc = new Scanner(System.in);
        config conf = new config();
        
        System.out.print("\nEnter Task ID to Update: ");
        int id = sc.nextInt();
        
        while((conf.getSingleValue("SELECT t_id FROM employee_task WHERE t_id = ?", id)) == 0){
        System.out.println("Selected Task ID doesn't exist!");
        System.out.print("Enter Task ID again: ");
        id = sc.nextInt();
}
        
        sc.nextLine();
        System.out.print("Enter new Status: ");
        String status = sc.nextLine();

        String sql = "UPDATE employee_task SET t_status = ? WHERE t_id = ?";
        conf.updateRecord(sql, status, id);
    }
    
    public void deleteTask(){
        Scanner sc = new Scanner(System.in);
        config conf = new config();
        
        System.out.print("\nEnter Task ID to Delete: ");
        int id = sc.nextInt();

        while((conf.getSingleValue("SELECT t_id FROM employee_task WHERE t_id = ?", id)) == 0){
        System.out.println("Selected Task ID doesn't exist!");
        System.out.print("Enter Task ID again: ");
        id = sc.nextInt();
}
        String sql = "DELETE FROM employee_task WHERE t_id = ?";
       conf.updateRecord(sql, id);
    }
    
        public void taskCom() {
        Scanner sc = new Scanner(System.in);
        config conf = new config();

        System.out.print("\nEnter Employee Account ID: ");
        int id = sc.nextInt();

        while (conf.getSingleValue("SELECT acc_id FROM employee_acc WHERE acc_id = ?", id) == 0) {
            System.out.println("Selected Account ID doesn't exist!");
            System.out.print("Enter Account ID again: ");
            id = sc.nextInt();
        }

        System.out.print("Enter the Task ID of the Employee: ");
        int id2 = sc.nextInt();

        while (conf.getSingleValue("SELECT t_id FROM employee_task WHERE t_id = ?", id2) == 0) {
            System.out.println("Selected Task ID doesn't exist!");
            System.out.print("Enter Task ID again: ");
            id2 = sc.nextInt();
        }
        
        sc.nextLine();
        System.out.print("Enter Comment to the Task of Employee: ");
        String com = sc.nextLine();
        
        String sql = "UPDATE task_report SET t_com = ? WHERE acc_id = ? AND t_id = ?";
        conf.updateRecord(sql, com, id, id2);
    }

    public void taskComUpt() {
        Scanner sc = new Scanner(System.in);
        config conf = new config();;

        System.out.print("\nEnter Account ID: ");
        int id = sc.nextInt();

        while (conf.getSingleValue("SELECT acc_id FROM employee_acc WHERE acc_id = ?", id) == 0) {
            System.out.println("Selected Account ID doesn't exist!");
            System.out.print("Enter Account ID again: ");
            id = sc.nextInt();
        }

        System.out.print("Enter Task ID: ");
        int id2 = sc.nextInt();

        while (conf.getSingleValue("SELECT t_id FROM employee_task WHERE t_id = ?", id2) == 0) {
            System.out.println("Selected Task ID doesn't exist!");
            System.out.print("Enter Task ID again: ");
            id2 = sc.nextInt();
        }
        
        sc.nextLine();
        System.out.print("Enter Update Comment to the Performance Task of Employee: ");
        String com = sc.nextLine();
        
        String sql = "UPDATE task_report SET t_com = ? WHERE acc_id = ? AND t_id = ?";
        conf.updateRecord(sql, com, id, id2);
    }
    
    public void Manager(){
        Manager mgr = new Manager();
        Hr hr = new Hr();
        Scanner sc = new Scanner (System.in);
        int chose, act;
        boolean exit = true;
do{ 
        System.out.println("\n================== Welcome To Task Listing System ==================");
        System.out.println("| 1. Add Comment to the Task of Employee                           |");
        System.out.println("| 2. Task Assigning                                                |");
        System.out.println("| 3. View Employee Account, Employee Task and Task Report          |");
        System.out.println("| 4. Update Employee Task and Comment                              |");
        System.out.println("| 5. Delete Employee Task                                          |");
        System.out.println("| 6. Exit                                                          |");
        System.out.println("====================================================================");
        System.out.print("Enter Action: ");
       
        if(sc.hasNextInt()){
            act = sc.nextInt();
            
            switch(act){
                case 1:
                    System.out.println("\nTask Report");
                    hr.taskReport();
                    mgr.taskCom();
                    System.out.println("\nTask Report");
                    hr.taskReport();
                    break;
                case 2:
                    System.out.println("\nList of Employee's Accounts");
                    hr.viewAcc();
                    System.out.println("\nList of Task");
                    hr.viewTask();
                    mgr.assTask();
                    System.out.println("\nTask Report");
                    hr.taskReport();
                    break;

                case 3:
                    System.out.println("\nList of Employee's Accounts");
                    hr.viewAcc();
                    System.out.println("\nList of Task");
                    hr.viewTask();
                    System.out.println("\nTask Report");
                    hr.taskReport();
                    break;

                case 4:
                    System.out.println("\nList of Employee's Account");
                    hr.viewAcc();
                    System.out.println("\nList of Employee Task");
                    hr.viewTask();
                    
                    System.out.print("Update Employee Task? (yes/no): ");
                    String utsk = sc.next();
                    while(utsk.equals("yes")){
                        mgr.updateTask();
                        hr.viewTask();
                        break;
                    } 
                    System.out.print("Update Comment? (yes/no): ");
                    String upt = sc.next();
                    while(upt.equals("yes")){
                        mgr.taskComUpt();
                        hr.viewTask();
                        break;
                    }
                    break;

                case 5:
                    System.out.println("\nList of Employee's Accounts");
                    hr.viewAcc();
                    System.out.println("\nList of Task");
                    hr.viewTask();
                    mgr.deleteTask();
                    hr.viewTask();
                    break;

                case 6:
                    System.out.print("Exit selected...type yes to continue: ");
                        String resp = sc.next();
                        if(resp.equalsIgnoreCase("yes")){
                        exit = false;
                        }
                    break;

                    default:
                        System.out.println("Action Error, There's no such number");
            }
          }else{
            System.out.println("Invalid input. Please enter a valid number.");
            }
        } while(exit);
    }
}
