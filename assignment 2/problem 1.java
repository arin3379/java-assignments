import java.io.*;
import java.util.*;

class Student{
BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
int rollno;
String name;
int marks1;
int marks2;
int marks3;
Student(){
  rollno = 0;
  name = "no name";
  marks1 = 0;
  marks2 = 0;
  marks3 = 0;
}
void get() throws IOException {
  System.out.print("Enter The Roll No:");
  rollno = Integer.parseInt(br.readLine());
  System.out.print("Enter The name:");
  name = br.readLine();
  System.out.print("Enter The marks of 1st subject(in the range of 0-100):");
  marks1 = Integer.parseInt(br.readLine());
  if (marks1 < 0 || marks1 > 100) {
    System.out.println("Fail! Invalid marks. Setting marks to 0.");
    marks1 = 0;
}
  System.out.print("Enter The marks of 2nd subject(in the range of 0-100):");
  marks2 = Integer.parseInt(br.readLine());
  if (marks2 < 0 || marks2 > 100) {
    System.out.println("Fail! Invalid marks. Setting marks to 0.");
    marks2 = 0;
}
  System.out.print("Enter The marks of 3rd subject(in the range of 0-100):");
  marks3 = Integer.parseInt(br.readLine());
  if (marks3 < 0 || marks3 > 100) {
    System.out.println("Fail! Invalid marks. Setting marks to 0.");
    marks3 = 0;
}
}
void show() {
  System.out.println("Name:" + name);
  System.out.println("Roll No:" + rollno);
  int total = marks1 + marks2 + marks3;
  System.out.println("Total marks:" + total );
  System.out.println("percentage:" + (total/3) + "%");
  if(total/3 >= 90 ) System.out.println("Grade: A+");
  else if(total/3 >= 80) System.out.println("Grade: A");
  else if(total/3 >= 70) System.out.println("Grade: B");
  else if(total/3 >= 60) System.out.println("Grade: C");
  else if(total/3 >= 50) System.out.println("Grade: D");
  else System.out.println("Grade: F (FAIL)");
}
}

public class Main {
    public static void main(String[] args) throws IOException {
      Student s1 = new Student();
      Student s2 = new Student();
      s1.show();
      s1.get();
      s2.get();
      s1.show();
      s2.show();
    }
}