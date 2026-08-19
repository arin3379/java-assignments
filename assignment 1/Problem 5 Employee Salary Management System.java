/* Problem Statement
Write a Java program that accepts details of N employees (using arrays):
 Employee ID
 Employee Name

 Basic Salary
Calculate:
 DA = 40% of Basic Salary
 HRA = 20% of Basic Salary
 PF = 12% of Basic Salary
 Gross Salary = Basic + DA + HRA
 Net Salary = Gross Salary − PF
Display all employee details in a tabular format and also display:
 Highest Paid Employee
 Lowest Paid Employee
 Average Salary of all employees
Concepts Covered
 Arrays
 Loops
 BufferedReader
 Arithmetic Operations
 String Handling
 Methods */
import java.io.*;
class Employee{
  int emp_id;
  String emp_name;
  int BS;
  double DA, HRA,PF, GS, net;
  BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  void calculate(){ 
    DA = 0.4*BS;
    HRA = 0.2*BS;
    PF = 0.12*BS;
    GS = BS + DA + HRA;
    net = GS - PF;
  }
  void get() throws IOException{
    System.out.println("enter the Employee id:");
    emp_id = Integer.parseInt(br.readLine());
    System.out.println("enter the Employee name:");
    emp_name = br.readLine();
    System.out.println("enter the Employee basic salary:");
    BS = Integer.parseInt(br.readLine());
    calculate();
  }
  // void set(int emp_id, String emp_name, int BS){
  //   this.emp_id = emp_id;
  //   this.emp_name= emp_name;
  //   this.BS = BS;
  //   calculate();
  // }
  void show(){
    System.out.println(emp_id + "|" + emp_name + "|" +  BS + "        |" + DA + "|" + HRA + " |" + PF + "|" + GS + "|" + net + "|");
  }
}
class Main extends Employee{
    public static void main(String[] args) throws IOException{
      BufferedReader b = new BufferedReader(new InputStreamReader(System.in));
      int n;
      System.out.println("how many Employee details u gone input:");
      n = Integer.parseInt(b.readLine());
      Employee[] emp = new Employee[n];
      for(int i=0; i<n; i++){
        System.out.println("enter the following details of " + (i+1) + " Employee:" );
        emp[i] = new Employee();
        emp[i].get();
      }
      System.out.println("ID|NAME|BASIC SALARY| DA | HRA | PF | GS | NET SALARY");
      System.out.println("-----------------------------------------------------");
      for(int i=0; i<n; i++) emp[i].show();
      System.out.println("-----------------------------------------------------");
      int high=0, low=0, avrage=0;
      for(int i=0; i<n; i++){
        if(emp[i].BS > emp[high].BS ) high=i;
        if(emp[i].BS < emp[low].BS ) low=i;
        avrage= avrage + emp[i].BS;
      }
      avrage/=n;
      System.out.println("highest paying Employee:" + emp[high].emp_name + "  " + emp[high].emp_id + " " + emp[high].BS );
      System.out.println("lowest paying Employee:" + emp[low].emp_name + "  " + emp[low].emp_id + " " + emp[low].BS );
      System.out.println("avrage salary:" + avrage);
    }
}