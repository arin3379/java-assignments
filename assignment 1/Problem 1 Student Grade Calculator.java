/* Problem Statement
Write a Java program to accept the marks of five subjects (out of 100 each) using BufferedReader.
Calculate:
 Total Marks
 Percentage
 Grade according to the following criteria:
Percentage Grade
≥ 90 A+
80–89 A
70–79 B
60–69 C
50–59 D
< 50 Fail
Also display whether the student has passed or failed.
Concepts Covered
 Variables
 Data Types
 Arithmetic Operators
 if-else ladder
 BufferedReader
 Type Casting */ 
import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      int[] marks = new int[5];
      System.out.print("enter marks for all 5 subjects:");
      for(int i=0; i<5; i++) marks[i] = Integer.parseInt(br.readLine());
      int total=0, per;
      for(int i=0; i<5; i++) total += marks[i];
      per= total/5;
      System.out.println("total marks:"+ total);
      System.out.println("Percentage:"+ per);
      if(per >= 90 ) System.out.println("Grade: A+");
      else if(per > 80 && per < 89) System.out.println("Grade: A");
      else if(per > 70 && per < 79) System.out.println("Grade: B");
      else if(per > 60 && per < 69) System.out.println("Grade: C");
      else if(per > 50 && per < 59) System.out.println("Grade: D");
      else if(per < 50) System.out.println("Grade: FAIL");
      }
    }