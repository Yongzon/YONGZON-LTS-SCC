package tlsystem;

import java.util.Scanner;

public class Hr {
    public void addEmpAcc(){
    Scanner sc = new Scanner(System.in);
    config conf = new config();

    System.out.print("\nEmployee First Name: ");
    String fname = sc.nextLine();
    System.out.print("Employee Last Name: ");
    String lname = sc.nextLine();
    System.out.print("Employee Email: ");
    String email = sc.nextLine();
    System.out.print("Employee Department: ");
    String edpt = sc.nextLine();
    System.out.print("Employee Role: ");
    String erole = sc.nextLine();

    String sql = "INSERT INTO employee_acc (e_fname, e_lname, e_email, e_department, e_role) VALUES (?, ?, ?, ?, ?)";

    conf.addRecord(sql, fname, lname, email, edpt, erole);
}

    public void addTask() {
        Scanner sc = new Scanner(System.in);
        config conf = new config();

        System.out.print("\nTask Name: ");
        String tname = sc.nextLine();
        System.out.print("Description: ");
        String des = sc.nextLine();
        System.out.print("Priority Level: ");
        String pl = sc.nextLine();
        System.out.print("Due Date (mm/dd/yy): ");
        String dd = sc.nextLine();
        String status = "Pending...";

        String sqlTask = "INSERT INTO employee_task (t_name, t_des, t_pl, t_dd, t_status) VALUES (?, ?, ?, ?, ?)";
        conf.addRecord(sqlTask, tname, des, pl, dd, status);
}
    
    public void viewAcc() {
        String Query = "SELECT * FROM employee_acc";
        String[] Headers = {"Account ID", "First Name", "Last Name", "Email", "Department", "Role"};
        String[] Columns = {"acc_id", "e_fname", "e_lname", "e_email", "e_department", "e_role"};

        config conf = new config();
        conf.viewRecords(Query, Headers, Columns);
}
    
    public void viewTask() {
        String Query = "SELECT * FROM employee_task";
        String[] Headers = {"Task ID", "Task Name", "Priority Level", "Due Date", "Status"};
        String[] Columns = {"t_id", "t_name", "t_pl", "t_dd", "t_status"};

        config conf = new config();
        conf.viewRecords(Query, Headers, Columns);
}
    public void viewTask2() {
        String Query = "SELECT t_id, t_name, t_des FROM employee_task";
        String[] Headers = {"Task ID", "Task Name", "Description"};
        String[] Columns = {"t_id", "t_name", "t_des"};

        config conf = new config();
        conf.viewRecords(Query, Headers, Columns);
}

    public void taskReport(){
        String taskRquery = "SELECT employee_acc.acc_id, employee_acc.e_fname, employee_task.t_id, employee_task.t_name, employee_task.t_dd, employee_task.t_status, task_report.tr_progress "
                + "FROM task_report INNER JOIN employee_acc ON employee_acc.acc_id = task_report.acc_id "
                + "INNER JOIN employee_task ON employee_task.t_id = task_report.t_id";

        String[] Headers = {"Assigned Account", "Assigned Task", "Due Date", "Progress Percentage", "Status"};
        String[] Columns = {"e_fname", "t_name", "t_dd", "tr_progress", "t_status"};

        config conf = new config();
        conf.viewRecords(taskRquery, Headers, Columns);
}

    public void updateAcc(){
        Scanner sc = new Scanner(System.in);
        config conf = new config();
        int id;
               
        System.out.print("\nEnter Account ID to Update: ");
        while (!sc.hasNextInt()) {
            System.out.println("Invalid input. Please enter a valid ID.");
            sc.next();
            System.out.print("Enter Account ID to Update: ");
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
        
        sc.nextLine();
        System.out.print("Enter new Department: ");
        String edpt = sc.nextLine();
        System.out.print("Enter new Role: ");
        String erole = sc.nextLine();
         
        String sql = "UPDATE employee_acc SET e_department = ?, e_role = ? WHERE acc_id = ?";
        conf.updateRecord(sql, edpt, erole, id);
    }
     
    public void deleteAcc(){
        Scanner sc = new Scanner(System.in);
        config conf = new config();
        int id;
        
        System.out.print("\nEnter Account ID to Delete: ");
        while (!sc.hasNextInt()) {
            System.out.println("Invalid input. Please enter a valid ID.");
            sc.next();
            System.out.print("Enter Account ID to Delete: ");
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

        String sql = "DELETE FROM employee_acc WHERE acc_id = ?";
        conf.deleteRecord(sql, id);
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
        
public void Humanr(){
        Hr hr = new Hr();
        Scanner sc = new Scanner (System.in);
        int cho;
        boolean exit = true;
do{ 
        System.out.println("\n================== Welcome To Task Listing System ==================");
        System.out.println("| 1. Add Employee Account                                          |");
        System.out.println("| 2. Add Task                                                      |");
        System.out.println("| 3. View Employee Account, Employee Task and Task Report          |");
        System.out.println("| 4. Update Empployee Account                                      |");
        System.out.println("| 5. Delete Employee Account and Employee Task                     |");
        System.out.println("| 6. Exit                                                          |");
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
                    hr.addEmpAcc();
                break;

                case 2:
                    hr.addTask();
                    break;

                case 3:
                    System.out.println("\nList of Employee's Accounts");
                    hr.viewAcc();
                    System.out.println("\nList of Task");
                    hr.viewTask();
                    
                    do {
                        System.out.print("\nView Full Details of Employee Task? (yes/no): ");
                        String res = sc.next();

                        if (res.equalsIgnoreCase("yes")) {
                            hr.viewTask2(); 
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
                    System.out.println("\nList of Employee's Account");
                    hr.viewAcc();
                    hr.updateAcc();
                    System.out.println("\nList of Employee's Account");
                    hr.viewAcc();
                    break;

                case 5:
                    System.out.println("\nList of Employee's Accounts");
                    hr.viewAcc();

                    do {
                        System.out.print("\nDelete Employee's Account? (yes/no): ");
                        String dlEmpAcc = sc.next();

                        if (dlEmpAcc.equalsIgnoreCase("yes")) {
                            hr.deleteAcc();
                            hr.viewAcc();
                            System.out.println("\nTask Report");
                            hr.taskReport();
                            break; 
                        } else if (dlEmpAcc.equalsIgnoreCase("no")) {
                            break;
                        } else {
                            System.out.println("Invalid input. Please enter a valid 'yes' or 'no'.");
                        }
                    } while (true); 

                    System.out.println("\nList of Task");
                    hr.viewTask();
                    
                    do {
                        System.out.print("\nDelete Task (yes/no)? (yes/no): ");
                        String dlTask = sc.next();

                        if (dlTask.equalsIgnoreCase("yes")) {
                            hr.deleteTask();
                            hr.viewTask();
                            System.out.println("\nTask Report");
                            hr.taskReport();
                            break; 
                        } else if (dlTask.equalsIgnoreCase("no")) {
                            break;
                        } else {
                            System.out.println("Invalid input. Please enter a valid 'yes' or 'no'.");
                        }
                    } while (true); 
                    break;

                case 6:
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
                        System.out.println("Choice Error, There's no number "+cho+" in choices!");
            }
        } while(exit);    
            System.out.print("Returning to main menu....\n");
    }   
}
