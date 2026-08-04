/* Problem Statement
Write a Java program to read 10 integers into an array and perform the following operations:
 Find Maximum Element
 Find Minimum Element
 Calculate Average
 Count Even Numbers
 Count Odd Numbers
 Search for a given number
 Display the array in reverse order
Concepts Covered
 Arrays
 Loops
 if statement
 Searching
 Variables */
import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      int[] arr = new int[10];
      System.out.println("enter 10 numbers");
      for(int i=0; i<10; i++) arr[i]= Integer.parseInt(br.readLine());
      int[] rev = new int[10];
      int low=0, high=0, even=0, odd=0, key, found=-1;
      double avr=0;
      System.out.println("enter key value to search:");
      key= Integer.parseInt(br.readLine());
      for(int i=0; i<10; i++){
        if(arr[i]%2==0) even++;
        else odd++;
        if(arr[i] > arr[high]) high = i;
        if(arr[i] < arr[low]) low = i;
        avr+=arr[i];
        rev[9-i]=arr[i];
        if(arr[i]==key) found=i;
      }
      avr/=10;
      System.out.println("highest number:" + arr[high]);
      System.out.println("lowest number:" + arr[low]);
      System.out.println("total even numbers:" + even);
      System.out.println("total odd numbers:" + odd);
      System.out.println("avreage:" + avr);
      System.out.println("found " + key + " at index "+ found);
      System.out.print("array in reverse order:");
      for(int i=0; i<10; i++) System.out.print( rev[i] +" ");
    }
}