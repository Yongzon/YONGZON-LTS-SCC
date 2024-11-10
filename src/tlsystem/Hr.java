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
        System.out.print("Status: ");
        String status = sc.nextLine();

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
        String Query = "SELECT * FROM employee_task";
        String[] Headers = {"Task ID", "Task Name", "Description"};
        String[] Columns = {"t_id", "t_name", "t_des"};

        config conf = new config();
        conf.viewRecords(Query, Headers, Columns);
}

    public void taskReport(){
        String taskRquery = "SELECT employee_acc.acc_id, employee_task.t_id, employee_task.t_dd, employee_task.t_status, task_report.tr_progress, task_report.t_com "
                + "FROM task_report INNER JOIN employee_acc ON employee_acc.acc_id = task_report.acc_id "
                + "INNER JOIN employee_task ON employee_task.t_id = task_report.t_id";

        String[] Headers = {"Assigned Account ID", "Assigned Task ID", "Due Date", "Progress Percentage", "Status", "Comment Of Manager"};
        String[] Columns = {"acc_id", "t_id", "t_dd", "tr_progress", "t_status", "t_com"};

        config conf = new config();
        conf.viewRecords(taskRquery, Headers, Columns);
}

    public void updateAcc(){
        Scanner sc = new Scanner(System.in);
        config conf = new config();
               
        System.out.print("\nEnter Account ID to Update: ");
        int id = sc.nextInt();
        
        while((conf.getSingleValue("SELECT acc_id FROM employee_acc WHERE acc_id = ?", id)) == 0){
        System.out.println("Selected Account ID doesn't exist!");
        System.out.print("Enter Account ID again: ");
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
        
        System.out.print("\nEnter Account ID to Delete: ");
        int id = sc.nextInt();

        while((conf.getSingleValue("SELECT acc_id FROM employee_acc WHERE acc_id = ?", id)) == 0){
        System.out.println("Selected Account ID doesn't exist!");
        System.out.print("Enter Account ID again: ");
        id = sc.nextInt();
    }

        String sql = "DELETE FROM employee_acc WHERE acc_id = ?";
        conf.deleteRecord(sql, id);
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
       conf.deleteRecord(sql, id);
    }
        
public void Humanr(){
        Hr hr = new Hr();
        Scanner sc = new Scanner (System.in);
        int chose, act;
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
        System.out.print("Enter Action: ");
       
        if(sc.hasNextInt()){
            act = sc.nextInt();
            
            switch(act){
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
                    
                    System.out.print("\nView Full Details of Employee Task? (yes/no): ");
                    String res = sc.next();
                    while(res.equals("yes")){
                        hr.viewTask2();
                        break;
                    }
                    
                    System.out.println("\nTask Report");
                    hr.taskReport();
                    break;

                case 4:
                    System.out.println("\nList of Employee's Account");
                    hr.viewAcc();
                    System.out.println("\nList of Employee Task");
                    hr.viewTask();
                    hr.updateAcc();
                    hr.viewAcc();
                    break;

                case 5:
                    System.out.println("\nList of Employee's Accounts");
                    hr.viewAcc();

                    System.out.print("\nDelete Employee's Account (yes/no)?: ");
                    String dlEmpAcc = sc.next();
                    while(dlEmpAcc.equals("yes")){
                        hr.deleteAcc();
                        hr.viewAcc();
                        System.out.println("\nTask Report");
                        hr.taskReport();
                        break;
                    }

                    System.out.println("\nList of Task");
                    hr.viewTask();

                    System.out.print("\nDelete Task (yes/no)?: ");
                    String dlTask = sc.next();
                    while(dlTask.equals("yes")){
                        hr.deleteTask();
                        hr.viewTask();
                        System.out.println("\nTask Report");
                        hr.taskReport();
                        break;
                    }
                    break;

                case 6:
                    System.out.print("Exit selected...type yes to continue: ");
                        String resp = sc.next();
                        if(resp.equalsIgnoreCase("yes")){
                        exit = false;
                        }
                        System.out.print("Returning to main menu....\n");
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
