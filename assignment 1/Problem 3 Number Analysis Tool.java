/* Problem Statement
Write a menu-driven Java program that accepts an integer and performs the following operations:
1. Check whether the number is Prime
2. Check whether it is Palindrome
3. Check whether it is Armstrong
4. Find the Sum of Digits
5. Reverse the Number
6. Exit
The program should continue until the user selects Exit.
Concepts Covered
 switch-case
 while loop
 do-while loop
 Methods
 Modulus Operator */
import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      int num, c=1;
      System.out.print("enter the number:");
      num = Integer.parseInt(br.readLine());
      while(c!=6){
      System.out.println("enter the number to do following actions:");
      System.out.println("1. Check whether the number is Prime");
      System.out.println("2. Check whether it is Palindrome");
      System.out.println("3. Check whether it is Armstrong");
      System.out.println("4. Find the Sum of Digits");
      System.out.println("5. Reverse the Number");
      System.out.println("6. Exit");      
      c = Integer.parseInt(br.readLine());
      switch(c) {
        case 1: 
        int prime = 1;
        for(int i=2; i<num; i++){
           if(num%i==0){
            System.out.println("its not a prime");
            prime = -1; 
            break;
           }
        }
        if(prime==1) System.out.println("it is a prime");
        break;
        case 2:
        int copy=num;
        int rev=0;
        while(copy!=0){
          rev= (rev*10) + (copy%10);
          copy/=10;
        } 
        if(num==rev) System.out.println("given number is a Palindrome");
        else System.out.println("given number is not a Palindrome");
        break;
        case 3:
        int copy1=num;
        int n=0;
        while(copy1!=0){
          n++;
          copy1/=10;
        }
        copy1=num;
        double arms=0;
        while(copy1!=0){
          int tamp= copy1%10;
          arms = arms + Math.pow(tamp, n);
          copy1/=10;
        }
        if(num==arms) System.out.println("given number is a Armstrong");
        else System.out.println("given number is not a Armstrong");
        break;
        case 4:
        int copy2=num;
        int sum=0;
        while(copy2!=0){
          int tamp = copy2%10;
          sum = sum + tamp;
          copy2/=10;
        }
        System.out.println("the sum of Digits is:" + sum);
        break;
        case 5:
         int copy3=num;
        int rev1=0;
        while(copy3!=0){
          rev1 = (rev1*10) + (copy3%10);
          copy3/=10;
        }
        System.out.println("the Reverse of the number is:" + rev1);
        break;
        case 6:
        System.out.println("exitting......");
        break;
        default:
        System.out.println("invaild input");
      }
      }
    
    }
}