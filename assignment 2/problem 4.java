import java.io.*;
import java.util.*;
abstract class Employee{
    int empid;
    String name;
    int salary;
    int HRA;
    int DA;
    int PF;
    int gross;
    int net;
    Employee(int x, String y, int z){
        empid = x;
        name = y;
        salary = z;
    }
    void calculate(){
        HRA = (int)(salary*0.2);
        DA = (int)(salary*0.4);
        PF = (int)(salary*0.12);
        net = gross - PF;
    }
    void display(){
        System.out.println("Employee ID:" + empid);
        System.out.println("Employee Name:" + name);
        System.out.println("Employee Salary:" + salary);
        System.out.println("HRA:" + HRA);
        System.out.println("DA:" + DA);
        System.out.println("PF:" + PF);
        System.out.println("Gross Salary:" + gross);
        System.out.println("Net Salary:" + net);
    }
    abstract void calculateSalary();
}
class PermanentEmployee extends Employee{
    PermanentEmployee(int x, String y, int z){
        super(x,y,z);
    }
    void calculateSalary(){
        super.calculate();
        gross = salary + HRA + DA;
    }
    void display(){
        super.display();
    }
}
class ContractEmployee extends Employee{
    ContractEmployee(int x, String y, int z){
        super(x,y,z);
    }
    void calculateSalary(){
        super.calculate();
        gross = (int)(salary*1.1);
    }
    void display(){
        super.display();
    }
}
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Employee e;
        System.out.println("1. Create Permanent Employee");
        System.out.println("2. Create Contract Employee");
        System.out.print("Enter your choice:");
        int ch = Integer.parseInt(br.readLine());
        switch(ch){
            case 1:
            System.out.print("Enter the employee ID:");
            int empid = Integer.parseInt(br.readLine());
            System.out.print("Enter the employee name:");
            String name = br.readLine();
            System.out.print("Enter the employee salary:");
            int salary = Integer.parseInt(br.readLine());
            e = new PermanentEmployee(empid,name,salary);
            e.calculateSalary();
            e.display();
            break;
            case 2:
            System.out.print("Enter the employee ID:");
            int empid1 = Integer.parseInt(br.readLine());
            System.out.print("Enter the employee name:");
            String name1 = br.readLine();
            System.out.print("Enter the employee salary:");
            int salary1 = Integer.parseInt(br.readLine());
            e = new ContractEmployee(empid1,name1,salary1);
            e.calculateSalary();
            e.display();
            break;
            default:
            System.out.println("Invalid choice");
        }
    }
}