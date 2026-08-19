/* Problem Statement
Write a Java program that calculates the electricity bill based on the following slab rates:
Units Rate (₹/Unit)
First 100 units ₹3
Next 100 units ₹5
Next 300 units ₹7
Above 500 units ₹10
Accept:
 Consumer Name
 Consumer Number
 Units Consumed
Display:
 Consumer Details
 Total Bill Amount
If the bill exceeds ₹5000, apply a 5% surcharge.

Concepts Covered
 Nested if-else
 Arithmetic Operators
 BufferedReader
 Variables */
import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      int units;
      String name, id;
      System.out.print("enter the Consumer name:");
      name = br.readLine();
      System.out.print("enter the Consumer number/ID:");
      id = br.readLine();
      System.out.print("enter the units used:");
      units = Integer.parseInt(br.readLine());
      double bill=0;
      int copy=units;
      if(units<=100) bill = units*3;
      else{
        bill = 300;
        units-=100;
        if(units<=100) bill = bill+ (units*5);
      else{
        bill = 800;
        units-=100;
        if(units<=300) bill = bill+ (units*7);
      else{
        units-=300;
        bill = 800 + (7*300) + (units*10);
      }
      }
      }
      if(bill>5000) bill= 1.05*bill; 
      System.out.println("Consumer Details:");
      System.out.println("consumer name:" + name);
      System.out.println("Consumer number/ID:" + id);
      System.out.println("total unit used:" + copy);
      System.out.println("total bill:₹" + bill);

      }
    }