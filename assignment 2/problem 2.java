import java.util.*;
import java.io.*;

class Bankaccount{
  int accno;
  String name;
  String phoneno;
  int bal;
  String pin;
  static int n=100;
  int totalattempt = 4;
  BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  Bankaccount(){
    accno = 0;
    name = "No Name";
    phoneno = "No Phone Number";
    bal = 0;
    pin = "1234";
  }
  void attemptreduce(){
    totalattempt--;
  }
  static int genrateaccountno(){
    n++;
    return n;
  }
  void makepin()throws IOException {
    System.out.print("enter your new 4-digit PIN: ");
    pin = br.readLine();
    while(pin.length()!=4){
      System.out.print("Invalid PIN! Please enter a 4-digit PIN: ");
      pin = br.readLine();
    }
  }
  boolean checkpin(String inputpin){
    if(pin.equals(inputpin)) return true;
    else{
      if(totalattempt==0){
        System.out.println("No attempts remaining. Account locked.");
        return false;
      }
      else{
        attemptreduce();
        System.out.println("Invalid PIN! Only " + totalattempt + " attempt(s) remaining.");
        return false;
      }
    }
  }
  Bankaccount(String name, String phoneno, int bal, String pin){
    accno = genrateaccountno();
    this.name = name;
    this.phoneno = phoneno;
    this.bal = bal;
    this.pin = pin;
  }
  void makeaccount() throws IOException {
    accno = genrateaccountno();
    System.out.print("Enter the full name: ");
    name = br.readLine();
    if(name.equals(" ") || name.equals("")){
      System.out.println("Invalid name entered. Setting name to 'Customer'.");
      name = "Customer";
    }
    System.out.print("Enter the 10-digit phone number: ");
    phoneno = br.readLine();
    while(phoneno.length()!=10){
      System.out.print("Invalid phone number! Please enter a 10-digit phone number: ");
      phoneno = br.readLine();
    }
    makepin();
  }
  int deposit() throws IOException {
    System.out.print("enter the amount you want to deposit: ");
    int amount = Integer.parseInt(br.readLine());
    if(amount>0){
      System.out.println(amount + " deposited successfully.");
      bal += amount;
    }
    else if(amount==0) {
      System.out.println("Deposit amount must be greater than zero.");
    }
    else {
      System.out.println("Invalid deposit amount. Negative values are not allowed.");
    }
    return bal;
  }
  boolean doWeHaveMoney(int money){
    if(bal - money < 0 ){
      System.out.println("Insufficient balance.");
      return false;
    }
    else return true;
  }
  int withdraw() throws IOException {
    System.out.print("enter the amount you want to withdraw: ");
    int tamp = Integer.parseInt(br.readLine());
    if(tamp> 0){
      System.out.print("Enter your PIN: ");
      String inputpin = br.readLine();
      if(checkpin(inputpin) && doWeHaveMoney(tamp)){
        System.out.println("Successfully withdrew " + tamp + " from " + name + "'s account.");
        bal-=tamp;
      }
    }
    else if(tamp==0) {
      System.out.println("Withdrawal amount must be greater than zero.");
    }
    else {
      System.out.println("Invalid withdrawal amount. Negative values are not allowed.");
    }
    return bal;
  }
  void showAllDetails() {
    System.out.println();
    System.out.println("Account Number : " + accno);
    System.out.println("Full Name      : " + name);
    System.out.println("Phone Number   : " + phoneno);
    System.out.println();
  }
  void showbal() throws IOException{
    System.out.print("enter your PIN: ");
    String inputpin = br.readLine();
    if(checkpin(inputpin)) System.out.println("balance = " + bal);
  }
  void transfer(Bankaccount receiver) throws IOException{
    System.out.print("enter the amount you want to transfer: ");
    int tamp = Integer.parseInt(br.readLine());
    System.out.print("Enter your PIN: ");
    String inputpin = br.readLine();
    if(tamp>0){
      if(checkpin(inputpin) && doWeHaveMoney(tamp)){
        System.out.println("Successfully transferred " + tamp + " from " + name + "'s account to " + receiver.name + "'s account.");
        bal-=tamp;
        receiver.bal +=tamp;
      }
    }
    else if(tamp==0) {
      System.out.println("Transfer amount must be greater than zero.");
    }
    else {
      System.out.println("Invalid transfer amount. Negative values are not allowed.");
    }
  }
  String getname(){
    return name;
  }
  int getaccno(){
    return accno;
  }
  int showattempt(){
    return totalattempt;
  }
}
class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    Bankaccount[] account = new Bankaccount[50];
    account[0] = new Bankaccount("arin", "1234567890", 3000, "1234");
    account[1] = new Bankaccount("rahul", "2345678901", 334, "1234");
    account[2] = new Bankaccount("kahul", "3456789012", 4533, "1234");
    int total=3;
    int i=0; 
    int ch=-1;
    System.out.println("Welcome to the Bank Management System");
    while(ch!=3){
      System.out.println("1. Login");
      System.out.println("2. Create New Account");
      System.out.println("3. Exit");
      System.out.print("Enter your choice: ");
      ch = Integer.parseInt(br.readLine());
      switch(ch) {
        case 1:{
          System.out.print("enter the bank account number: ");
          int banker = Integer.parseInt(br.readLine());
          boolean found= false;
          while(!found){
            for(int j=0; j< total; j++){
              if(account[j].getaccno()==banker){
                i=j;
                found = true;
                break;
              }
            }
            if(!found){
              System.out.print("Invalid account number. Please try again: ");
              banker = Integer.parseInt(br.readLine());
            }
          }
          if(account[i].showattempt()==0){
            System.out.println("Your account is locked due to too many incorrect PIN attempts. Please contact customer support.");
            break;
          }
          System.out.println("Hello " + account[i].getname());
          System.out.print("enter your PIN to get started: ");
          String inputpin = br.readLine();
          while(!account[i].checkpin(inputpin) && account[i].showattempt()>0){
            System.out.print("Please try to re-enter your PIN: ");
            inputpin = br.readLine();
          }
          if(account[i].showattempt()==0){
            System.out.println("Your account has been locked.");
            break;
          }
          System.out.println("       WELCOME, " + account[i].getname());
          int ch2 = -1;
          while(ch2 != 6){
            System.out.println();
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Transfer Money");
            System.out.println("5. Account Details");
            System.out.println("6. Logout");
            System.out.print("Enter your choice: ");
        
            ch2 = Integer.parseInt(br.readLine());

            switch(ch2){
              case 1:
                account[i].showbal();
                break;

              case 2:
                account[i].deposit();
                break;

              case 3:
                account[i].withdraw();
                break;

              case 4:{
                System.out.print("enter the bank account number: ");
                int receiver = Integer.parseInt(br.readLine());
                int k = -1;
                boolean found1= false;
                while(!found1){
                  for(int j=0; j< total; j++){
                    if(account[j].getaccno()==receiver){
                      k=j;
                      found1 = true;
                      break;
                    }
                  }
                  if(!found1){
                    System.out.print("Invalid account number. Please try again: ");
                    receiver = Integer.parseInt(br.readLine());
                  }
                }
                account[i].transfer(account[k]);
                break;
              }

              case 5:
                account[i].showAllDetails();
                break;

              case 6:{
                System.out.println("Logging out...");
                break;
              }

              default:
                System.out.println("Invalid choice. Please enter a valid option.");
            }
          }
          break;
        }
        case 2:{
          account[total] = new Bankaccount();
          account[total].makeaccount();
          System.out.println("Your bank account number is: " + account[total].getaccno() + ". Please login again to access your account." );
          total++;
          break;
        }
        case 3:{
          System.out.println("Exiting the system...");
          break;
        }
        default: 
          System.out.println("Invalid choice. Please enter a valid option.");
      }
    }
    System.out.println("Thank you for using our service.");
  }
}
