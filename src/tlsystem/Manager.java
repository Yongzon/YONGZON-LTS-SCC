package tlsystem;

import java.util.Scanner;

public class Manager {
    
    public void viewTask() {
        String Query = "SELECT * FROM employee_task WHERE t_status != 'Started'";
        String[] Headers = {"Task ID", "Task Name", "Priority Level", "Due Date", "Status"};
        String[] Columns = {"t_id", "t_name", "t_pl", "t_dd", "t_status"};

        config conf = new config();
        conf.viewRecords(Query, Headers, Columns);
}
    
    public void viewTask2() {
        String Query = "SELECT * FROM employee_task WHERE t_status != 'Started'";
        String[] Headers = {"Task Name", "Description"};
        String[] Columns = {"t_name", "t_des"};

        config conf = new config();
        conf.viewRecords(Query, Headers, Columns);
}

    public void assTask() {
       Scanner sc = new Scanner(System.in);
       config conf = new config();
       int id, id2;
       
       System.out.print("\nEnter Account ID to Assign Task: ");
        while (!sc.hasNextInt()) {
            System.out.println("Invalid input. Please enter a valid ID.");
            sc.next();
            System.out.print("Enter Account ID to Assign Task: ");
        }
        id = sc.nextInt();
       
       while((conf.getSingleValue("SELECT acc_id FROM employee_acc WHERE acc_id = ?", id)) == 0){
            System.out.println("Selected Account ID doesn't exist!");
            System.out.print("Enter Account ID again: ");
            while (!sc.hasNextInt()) {
                System.out.println("Invalid input. Please enter a valid ID.");
                sc.next();
                System.out.print("Enter Account ID again: ");
            }
            id = sc.nextInt();
       }
       
       System.out.print("Enter Task ID to Assign in the Account: ");
        while (!sc.hasNextInt()) {
            System.out.println("Invalid input. Please enter a valid ID.");
            sc.next();
            System.out.print("Enter Task ID to Assign in the Account: ");
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
       
       double prog = 0;
       
       String sql = "INSERT INTO task_report (acc_id, t_id, tr_progress) VALUES (?, ?, ?)";
       conf.addRecord(sql, id, id2, prog);
}
    
    public void deleteTask(){
        Scanner sc = new Scanner(System.in);
        config conf = new config();
        int id;
        
        System.out.print("\nEnter Task ID to Delete: ");
        while (!sc.hasNextInt()) {
            System.out.println("Invalid input. Please enter a valid ID.");
            sc.next();
            System.out.print("Enter Task ID to Delete: ");
        }
        id = sc.nextInt();

        while((conf.getSingleValue("SELECT t_id FROM employee_task WHERE t_id = ?", id)) == 0){
            System.out.println("Selected Task ID doesn't exist!");
            System.out.print("Enter Task ID again: ");
            while (!sc.hasNextInt()) {
                System.out.println("Invalid input. Please enter a valid ID.");
                sc.next();
                System.out.print("Enter Task ID again: ");
            }
            id = sc.nextInt();
        }
        
        String sql = "DELETE FROM employee_task WHERE t_id = ?";
        conf.deleteRecord(sql, id);
    }
    
    public void Manager(){
        Manager mgr = new Manager();
        Hr hr = new Hr();
        Scanner sc = new Scanner (System.in);
        int cho;
        boolean exit = true;
do{ 
        System.out.println("\n================== Welcome To Task Listing System ==================");
        System.out.println("| 1. Task Assigning                                                |");
        System.out.println("| 2. View Employee Task and Task Report                            |");
        System.out.println("| 3. Delete Employee Task                                          |");
        System.out.println("| 4. Exit                                                          |");
        System.out.println("====================================================================");
        System.out.print("Enter Choice: ");
            while (!sc.hasNextInt()) {
                System.out.println("Invalid input. Please enter a valid number.");
                sc.next();
                System.out.print("Enter Choice: ");
            }
            cho = sc.nextInt();
            
            switch(cho){
                    
                case 1:
                    System.out.println("\nList of Employee's Account");
                    hr.viewAcc();
                    System.out.println("\nList of Task");
                    mgr.viewTask();
                    mgr.assTask();
                    System.out.println("\nTask Report");
                    hr.taskReport();
                    break;

                case 2:
                    System.out.println("\nList of Task");
                    mgr.viewTask();
                    
                    System.out.print("View Full Details of Employee Task? (yes/no): ");
                    String res = sc.next();
                    while(res.equals("yes")){
                        mgr.viewTask2();
                        break;
                    }
                    
                    System.out.println("\nTask Report");
                    hr.taskReport();
                    break;

                case 3:
                    System.out.println("\nList of Task");
                    mgr.viewTask();
                    System.out.print("Delete Task? (yes/no): ");
                    String res1 = sc.next();
                    while(res1.equals("yes")){
                        mgr.deleteTask();
                        mgr.viewTask();
                        break;
                    }
                    break;

                case 4:
                    System.out.print("Exit selected...type yes to continue: ");
                        String resp = sc.next();
                        if(resp.equalsIgnoreCase("yes")){
                        exit = false;
                        }
                    break;

                    default:
                        System.out.println("Choice Error, There's no such number");
            }
        }while(exit);
            System.out.print("Returning to main menu....\n");
    }
}
