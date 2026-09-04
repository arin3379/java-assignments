import java.util.*;
import java.io.*;
import java.time.*;
import java.time.format.*;


class Bankaccount {
    int accno;
    String name;
    String phoneno;
    int bal;
    String pin;
    static int n = 100;
    int totalattempt = 4;
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


    Bankaccount() {
        accno = 0;
        name = "No Name";
        phoneno = "No Phone Number";
        bal = 0;
        pin = "1234";
    }


    void attemptreduce() {
        totalattempt--;
    }


    static int genrateaccountno() {
        n++;
        return n;
    }


    void makepin() throws IOException {
        System.out.print("Enter your new 4-digit PIN: ");
        pin = br.readLine();
        while (pin.length() != 4 || !CheckPinInput(pin)) {
            if (pin.length() != 4 && !CheckPinInput(pin))
                System.out.print("Invalid PIN length! Please enter a 4-digit PIN: ");
            else if (!CheckPinInput(pin))
                System.out.print("Invalid PIN! Please enter digits only: ");
            else if (pin.length() != 4)
                System.out.print("Invalid! Please enter a 4-digit numeric PIN: ");
            pin = br.readLine();
        }
    }


    boolean CheckPinInput(String pin) {
        boolean flag = true;
        for (int i = 0; i < 4; i++) {
            if (!(pin.charAt(i) <= '9' && pin.charAt(i) >= '0')) {
                flag = false;
                break;
            }
        }
        return flag;
    }


    boolean CheckphonenoInput(String phoneno) {
        boolean flag = true;
        for (int i = 0; i < 10; i++) {
            if (!(phoneno.charAt(i) <= '9' && phoneno.charAt(i) >= '0')) {
                flag = false;
                break;
            }
        }
        return flag;
    }


    boolean checkpin(String inputpin) {
        if (totalattempt != 0) {
            if (pin.equals(inputpin))
                return true;
            else {
                attemptreduce();
                System.out.println("Invalid PIN! Only " + totalattempt + " attempt(s) remaining.");
                return false;
            }
        } else {
            System.out.println("No attempts remaining. Account locked.");
            return false;
        }
    }


    Bankaccount(String name, String phoneno, int bal, String pin) {
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
        if (name.equals(" ") || name.equals("")) {
            System.out.println("Invalid name entered. Setting name to 'Customer'.");
            name = "Customer";
        }
        System.out.print("Enter the 10-digit phone number: ");
        phoneno = br.readLine();
        while (phoneno.length() != 10 || !CheckphonenoInput(phoneno)) {
            if (phoneno.length() != 10)
                System.out.print("Invalid phone number! Please enter a 10-digit phone number: ");
            else if (!CheckphonenoInput(phoneno))
                System.out.print("Invalid phone number! Please enter digits only: ");
            else if (phoneno.length() != 10 && !CheckphonenoInput(phoneno))
                System.out.print("Invalid! Please enter a 10-digit numeric phone number: ");
            phoneno = br.readLine();
        }
        makepin();
    }


    void ChangePin() throws IOException {
        System.out.print("Enter your current PIN: ");
        String inputpin = br.readLine();
        if (checkpin(inputpin)) {
            makepin();
        }
    }


    int deposit() throws IOException {
        System.out.print("Enter the amount you want to deposit: ");
        int amount;
        try {
            amount = Integer.parseInt(br.readLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input! Please enter a valid integer.");
            amount = 0;
            return bal;
        }
        if (amount > 0) {
            System.out.println(amount + " deposited successfully.");
            bal += amount;
            Main.transaction[Main.transactioncount] = new Transactions();
            Main.transaction[Main.transactioncount].MakeTransactions(amount, "Deposit", accno);
        } else if (amount == 0) {
            System.out.println("Deposit amount must be greater than zero.");
        } else {
            System.out.println("Invalid deposit amount. Negative values are not allowed.");
        }
        return bal;
    }


    boolean doWeHaveMoney(int money) {
        if (bal - money < 0) {
            System.out.println("Insufficient balance.");
            return false;
        } else
            return true;
    }


    int withdraw() throws IOException {
        System.out.print("Enter the amount you want to withdraw: ");
        int tamp;
        try {
            tamp = Integer.parseInt(br.readLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input! Please enter a valid integer.");
            tamp = 0;
            return bal;
        }
        if (tamp > 0) {
            System.out.print("Enter your PIN: ");
            String inputpin = br.readLine();
            if (checkpin(inputpin) && doWeHaveMoney(tamp)) {
                System.out.println("Successfully withdrew " + tamp + " from " + name + "'s account.");
                bal -= tamp;
                Main.transaction[Main.transactioncount] = new Transactions();
                Main.transaction[Main.transactioncount].MakeTransactions(-1 * tamp, "Withdraw", accno);
            }
        } else if (tamp == 0) {
            System.out.println("Withdrawal amount must be greater than zero.");
        } else {
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


    void showbal() throws IOException {
        System.out.print("Enter your PIN: ");
        String inputpin = br.readLine();
        if (checkpin(inputpin))
            System.out.println("Balance = " + bal);
    }


    void transfer(Bankaccount receiver) throws IOException {
        if (accno == receiver.accno) {
            System.out.println("You cannot transfer money to your own account.");
            return;
        }
        System.out.print("Enter the amount you want to transfer: ");
        int tamp;
        try {
            tamp = Integer.parseInt(br.readLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input! Please enter a valid integer.");
            tamp = 0;
            return;
        }
        System.out.print("Enter your PIN: ");
        String inputpin = br.readLine();
        if (tamp > 0) {
            if (checkpin(inputpin) && doWeHaveMoney(tamp)) {
                System.out.println("Successfully transferred " + tamp + " from " + name + "'s account to " + receiver.name + "'s account.");
                bal -= tamp;
                receiver.bal += tamp;
                Main.transaction[Main.transactioncount] = new Transactions();
                Main.transaction[Main.transactioncount].MakeTransactions(tamp, "Transfer", accno, receiver.accno);
            }
        } else if (tamp == 0) {
            System.out.println("Transfer amount must be greater than zero.");
        } else {
            System.out.println("Invalid transfer amount. Negative values are not allowed.");
        }
    }


    String getname() {
        return name;
    }


    int getaccno() {
        return accno;
    }


    int showattempt() {
        return totalattempt;
    }
}


class Transactions {
    int transaction_id;
    String Timestamp;
    int amount;
    String type;
    int account_from;
    int account_to;
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


    String generateTimestamp() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy / HH:mm");
        return now.format(formatter);
    }


    Transactions() {
        transaction_id = -1;
        Timestamp = "NULL";
        amount = -1;
        type = "NULL";
        account_from = -1;
        account_to = -1;
    }


    static int nextTransactionID = 100;


    int genrateTransationID() {
        return nextTransactionID++;
    }


    void MakeTransactions(int amount, String type, int account_from, int account_to) throws IOException {
        this.transaction_id = genrateTransationID();
        this.Timestamp = generateTimestamp();
        this.amount = amount;
        this.type = type;
        this.account_from = account_from;
        this.account_to = account_to;
        Main.transactioncount++;
        askUserIftheywantstoPrintOrNot();
    }


    void MakeTransactions(int amount, String type, int account_from) throws IOException {
        this.transaction_id = genrateTransationID();
        this.Timestamp = generateTimestamp();
        this.amount = amount;
        this.type = type;
        this.account_from = account_from;
        Main.transactioncount++;
        askUserIftheywantstoPrintOrNot();
    }


    void askUserIftheywantstoPrintOrNot() throws IOException {
        System.out.println("Transaction has been completed. The Transaction ID is: " + transaction_id);
        System.out.print("Do you want to print the transaction slip? (y/n): ");
        String choice;
        choice = br.readLine();
        if (choice.equals("y")) {
            showTransaction();
        }
    }


    void showTransaction() {
        System.out.println("------------------------------------------------------------------------------------------------");
        System.out.println("Transaction ID:   " + transaction_id);
        System.out.println("Timestamp:        " + Timestamp);
        System.out.println("Amount:           " + amount);
        System.out.println("Type:             " + type);
        if (type.equals("Deposit")) {
            System.out.println("Account Number:   " + account_from);
            System.out.println("₹" + amount + " deposited to account number " + account_from);
        } else if (type.equals("Withdraw")) {
            System.out.println("Account Number:   " + account_from);
            System.out.println("₹" + amount + " withdrawn from account number " + account_from);
        } else if (type.equals("Transfer")) {
            System.out.println("Account Number:   " + account_from);
            System.out.println("Account Number:   " + account_to);
            System.out.println("₹" + amount + " transferred from account number " + account_from + " to account number " + account_to);
        }
        System.out.println("------------------------------------------------------------------------------------------------");
    }


    void show(int account) {
        if (account_from == account && type.equals("Withdraw")) {
            System.out.println(transaction_id + "            " + "Withdrawal " + amount + "    " + Timestamp);
        } else if (account_from == account && type.equals("Deposit")) {
            System.out.println(transaction_id + "              " + "Deposit    +" + amount + "    " + Timestamp);
        } else if (account_from == account && type.equals("Transfer")) {
            System.out.println(transaction_id + "              " + "Transfer   -" + amount + "    " + Timestamp + " to account number " + account_to);
        } else if (account_to == account && type.equals("Transfer")) {
            System.out.println(transaction_id + "              " + "Received   +" + amount + "    " + Timestamp + " from account number " + account_from);
        }
    }


    int getTransactionID() {
        return transaction_id;
    }


    String getType() {
        return type;
    }


    int getamount() {
        return amount;
    }


    int getaccount_from() {
        return account_from;
    }


    int getaccount_to() {
        return account_to;
    }


    String getTimestamp() {
        return Timestamp;
    }
}


class Main {
    static Transactions[] transaction = new Transactions[100];
    static int transactioncount = 0;


    static int findaccount(int banker, Bankaccount[] account, int last) {
        for (int i = 0; i < last; i++) {
            if (account[i].getaccno() == banker) {
                return i;
            }
        }
        return -1;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Bankaccount[] account = new Bankaccount[50];
        account[0] = new Bankaccount("arin", "1234567890", 3000, "1234");
        account[1] = new Bankaccount("rahul", "2345678901", 334, "1234");
        account[2] = new Bankaccount("kahul", "3456789012", 4533, "1234");
        int total = 3;
        int i = -1;
        int ch = -1;
        boolean newUser = false;
        System.out.println("Welcome to the Bank Management System");
        while (ch != 3) {
            System.out.println("1. Login");
            System.out.println("2. Create New Account");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            try {
                ch = Integer.parseInt(br.readLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid integer.");
                ch = 0;
            }
            switch (ch) {
                case 1: {
                    System.out.print("Enter the bank account number: ");
                    int banker;
                    try {
                        banker = Integer.parseInt(br.readLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input! Please enter a valid integer.");
                        banker = 0;
                    }
                    while (findaccount(banker, account, total) == -1) {
                        System.out.print("Invalid account number. Please try again: ");
                        try {
                            banker = Integer.parseInt(br.readLine());
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input! Please enter a valid integer.");
                            banker = 0;
                        }
                    }
                    i = findaccount(banker, account, total);
                    if (account[i].showattempt() == 0) {
                        System.out.println("Your account is locked due to too many incorrect PIN attempts. Please contact customer support.");
                        break;
                    }
                    System.out.println("Hello " + account[i].getname());
                    System.out.print("Enter your PIN to get started: ");
                    String inputpin = br.readLine();
                    while (!account[i].checkpin(inputpin) && account[i].showattempt() > 0) {
                        System.out.print("Please re-enter your PIN: ");
                        inputpin = br.readLine();
                    }
                    if (account[i].showattempt() == 0) {
                        System.out.println("Your account has been locked.");
                        break;
                    }
                    System.out.println("       WELCOME, " + account[i].getname());
                    int ch2 = -1;
                    while (ch2 != 8) {
                        System.out.println();
                        System.out.println("1. Check Balance");
                        System.out.println("2. Deposit Money");
                        System.out.println("3. Withdraw Money");
                        System.out.println("4. Transfer Money");
                        System.out.println("5. Account Details");
                        System.out.println("6. Transactions ");
                        System.out.println("7. Change PIN");
                        System.out.println("8. Logout");
                        System.out.print("Enter your choice: ");


                        try {
                            ch2 = Integer.parseInt(br.readLine());
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input! Please enter a valid integer.");
                            ch2 = 0;
                        }


                        switch (ch2) {
                            case 1:
                                account[i].showbal();
                                break;


                            case 2:
                                account[i].deposit();
                                break;


                            case 3:
                                account[i].withdraw();
                                break;


                            case 4: {
                                System.out.print("Enter the bank account number you want to transfer money to: ");
                                int receiver;
                                try {
                                    receiver = Integer.parseInt(br.readLine());
                                } catch (NumberFormatException e) {
                                    System.out.println("Invalid input! Please enter a valid integer.");
                                    receiver = 0;
                                }
                                while (findaccount(receiver, account, total) == -1) {
                                    System.out.print("Invalid account number. Please try again: ");
                                    try {
                                        receiver = Integer.parseInt(br.readLine());
                                    } catch (NumberFormatException e) {
                                        System.out.println("Invalid input! Please enter a valid integer.");
                                        receiver = 0;
                                    }
                                }
                                int r = findaccount(receiver, account, total);
                                account[i].transfer(account[r]);
                                break;
                            }


                            case 5:
                                account[i].showAllDetails();
                                break;
                            case 6: {
                                int ch3 = 0;
                                while (ch3 != 3) {


                                    System.out.println("1. Show all transactions");
                                    System.out.println("2. search transaction by transaction id");
                                    System.out.println("3. Back");
                                    System.out.print("Enter your choice: ");
                                    try {
                                        ch3 = Integer.parseInt(br.readLine());
                                    } catch (NumberFormatException e) {
                                        System.out.println("Invalid input! Please enter a valid integer.");
                                        ch3 = 0;
                                    }
                                    switch (ch3) {
                                        case 1: {
                                             System.out.println("Transaction ID  " + "Type      " + "Amount" + "  Timestamp");
                                for (int j = 0; j < transactioncount; j++) {
                                    if (transaction[j].account_from == account[i].accno || transaction[j].account_to == account[i].accno) {
                                        transaction[j].show(account[i].getaccno());
                                    }
                                }
                                break;
                                        }
                                        case 2: {
                                System.out.print("Enter the transaction ID to search for: ");
                                int searchId;
                                try {
                                    searchId = Integer.parseInt(br.readLine());
                                } catch (NumberFormatException e) {
                                    System.out.println("Invalid input! Please enter a valid integer.");
                                    searchId = 0;
                                }
                                boolean found = false;
                                for (int j = 0; j < transactioncount; j++) {
                                    if (transaction[j].getTransactionID() == searchId) {
                                        transaction[j].showTransaction();
                                        found = true;
                                        break;
                                    }
                                }
                                if (!found) {
                                    System.out.println("Transaction with ID " + searchId + " not found.");
                                }
                                break;
                            }
                            case 3: {
                                System.out.println("Back");
                                break;
                            }
                            default: {
                                System.out.println("Invalid choice. Please enter a valid option.");
                                break;
                            }
                            }
                                }
                                break;
                            }
                            case 7: {
                                account[i].ChangePin();
                                break;
                            }
                            case 8: {
                                System.out.println("Logging out...");
                                break;
                            }


                            default:
                                System.out.println("Invalid choice. Please enter a valid option.");
                        }
                    }
                    break;
                }
                case 2: {
                    account[total] = new Bankaccount();
                    account[total].makeaccount();
                    System.out.println("Your bank account number is: " + account[total].getaccno() + ". Please login again to access your account.");
                    total++;
                    newUser = true;
                    break;
                }
                case 3: {
                    System.out.println("Exiting the system...");
                    break;
                }
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
        System.out.println("Thank you for using our service.");
        if (!newUser) {
            if (i != -1)
                System.out.println("See you soon " + account[i].getname() + " :)");
            else
                System.out.println("Are we that bad? You didn't even log in :(");
        } else {
            if (i != -1) {
                System.out.println("Welcome to the family " + account[i].getname() + "! Account created successfully. See you soon :)");
            } else {
                System.out.println("Account created successfully! But you didn't even log in :(");
            }
        }
    }
}



   