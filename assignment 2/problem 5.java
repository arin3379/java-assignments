import java.io.*;
import java.util.*;
interface Payment{
    void makePayment(int amount);
    void paymentDetails();
}
class CreditCardPayment implements Payment{
    int amount;
    String cardnumber;
    String name;
    CreditCardPayment(String n,String cn, int am){
        name=n;
        cardnumber = cn;
        amount = am;
    }
    public void makePayment(int amount){
        System.out.println("Amount: " + amount + " is credited in your account using credit card.");
    }
    public void paymentDetails(){
        System.out.println("Name:" + name);
        System.out.println("Card Number:" + cardnumber);
        System.out.println("Amount:" + amount);
    }
}
class UPIpayment implements Payment{
    String id;
    int amount;
    String name;
    UPIpayment(String n,String id1, int am){
        name = n;
        id = id1;
        amount = am;
    }
    public void makePayment(int amount){
        System.out.println("Amount: " + amount + " is credited in your account using UPI.");
    }
    public void paymentDetails(){
        System.out.println("Name:" + name);
        System.out.println("UPI ID:" + id);
        System.out.println("Amount:" + amount);
    }
}
class CashPayment implements Payment{
    String name;
    int amount;
    CashPayment(String n, int am){
        name = n;
        amount = am;
    }
    public void makePayment(int amount){
        System.out.println("Amount: " + amount + " is credited in your pocket using Cash.");
    }
    public void paymentDetails(){
        System.out.println("Name:" + name);
        System.out.println("Amount:" + amount);
    }
}
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        CreditCardPayment p;
        UPIpayment p1;
        CashPayment p2;
        System.out.println("----- PAYMENT SYSTEM -----");
        System.out.println("1. Credit Card Payment");
        System.out.println("2. UPI Payment");
        System.out.println("3. Cash Payment");
        System.out.print("Enter your choice:");
        int ch = Integer.parseInt(br.readLine());
        switch(ch){
            case 1:
            System.out.print("Enter the name:");
            String name = br.readLine();
            System.out.print("Enter the card number:");
            String cardnumber = br.readLine();
            System.out.print("Enter the amount:");
            int amount = Integer.parseInt(br.readLine());
            p = new CreditCardPayment(name,cardnumber,amount);
            p.makePayment(amount);
            p.paymentDetails();
            break;
            case 2:
            System.out.print("Enter the name:");
            String name1 = br.readLine();
            System.out.print("Enter the UPI ID:");
            String id1 = br.readLine();
            System.out.print("Enter the amount:");
            int amount1 = Integer.parseInt(br.readLine());
            p1 = new UPIpayment(name1,id1,amount1);
            p1.makePayment(amount1);
            p1.paymentDetails();
            break;
            case 3:
            System.out.print("Enter the name:");
            String name2 = br.readLine();
            System.out.print("Enter the amount:");
            int amount2 = Integer.parseInt(br.readLine());
            p2 = new CashPayment(name2,amount2);
            p2.makePayment(amount2);
            p2.paymentDetails();
            break;
            default:
            System.out.println("Invalid choice");
        }
    }
}