import dao.CustomerDAO;
import dao.AccountDAO;
import dao.TransactionDAO;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        CustomerDAO customerDAO = new CustomerDAO();
        AccountDAO accountDAO = new AccountDAO();
        TransactionDAO transactionDAO = new TransactionDAO();

        while (true) {

            System.out.println("\n===== BANKING MANAGEMENT SYSTEM =====");
            System.out.println("1. Add Customer");
            System.out.println("2. View Customers");
            System.out.println("3. Update Customer");
            System.out.println("4. Delete Customer");
            System.out.println("5. Create Account");
            System.out.println("6. View Accounts");
            System.out.println("7. Update Account");
            System.out.println("8. Delete Account");
            System.out.println("9. Deposit");
            System.out.println("10. Withdraw");
            System.out.println("11. Transfer Money");
            System.out.println("12. Transaction History");
            System.out.println("0. Exit");

            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            double amount;

            switch (choice) {

                case 1:
                    System.out.print("Customer ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Name: ");
                    String name = sc.nextLine();

                    System.out.print("Email: ");
                    String email = sc.nextLine();

                    System.out.print("Phone: ");
                    String phone = sc.nextLine();

                    System.out.print("Address: ");
                    String address = sc.nextLine();

                    customerDAO.insertCustomer(id, name, email, phone, address);
                    break;

                case 2:
                    customerDAO.viewCustomers();
                    break;

                case 3:
                    System.out.print("Customer ID: ");
                    id = sc.nextInt();
                    sc.nextLine();

                    System.out.print("New Name: ");
                    name = sc.nextLine();

                    System.out.print("New Email: ");
                    email = sc.nextLine();

                    System.out.print("New Phone: ");
                    phone = sc.nextLine();

                    System.out.print("New Address: ");
                    address = sc.nextLine();

                    customerDAO.updateCustomer(id, name, email, phone, address);
                    break;

                case 4:
                    System.out.print("Customer ID: ");
                    id = sc.nextInt();

                    customerDAO.deleteCustomer(id);
                    break;

                case 5:
                    System.out.print("Account ID: ");
                    int accountId = sc.nextInt();

                    System.out.print("Customer ID: ");
                    int customerId = sc.nextInt();

                    System.out.print("Account Number: ");
                    long accountNumber = sc.nextLong();
                    sc.nextLine();

                    System.out.print("Account Type: ");
                    String accountType = sc.nextLine();

                    System.out.print("Initial Balance: ");
                    double balance = sc.nextDouble();
                    sc.nextLine();

                    System.out.print("Status: ");
                    String status = sc.nextLine();

                    accountDAO.createAccount(
                            accountId,
                            customerId,
                            accountNumber,
                            accountType,
                            balance,
                            status
                    );
                    break;

                case 6:
                    accountDAO.viewAccounts();
                    break;

                case 7:
                    System.out.print("Account ID: ");
                    accountId = sc.nextInt();
                    sc.nextLine();

                    System.out.print("New Account Type: ");
                    accountType = sc.nextLine();

                    System.out.print("New Status: ");
                    status = sc.nextLine();

                    accountDAO.updateAccount(
                            accountId,
                            accountType,
                            status
                    );
                    break;

                case 8:
                    System.out.print("Account ID: ");
                    accountId = sc.nextInt();

                    accountDAO.deleteAccount(accountId);
                    break;

                case 9:
                    System.out.print("Account ID: ");
                    accountId = sc.nextInt();

                    System.out.print("Deposit Amount: ");
                    amount = sc.nextDouble();

                    transactionDAO.deposit(accountId, amount);
                    break;

                case 10:
                    System.out.print("Account ID: ");
                    accountId = sc.nextInt();

                    System.out.print("Withdrawal Amount: ");
                    amount = sc.nextDouble();

                    transactionDAO.withdraw(accountId, amount);
                    break;

                case 11:
                    System.out.print("Sender Account ID: ");
                    int fromAccount = sc.nextInt();

                    System.out.print("Receiver Account ID: ");
                    int toAccount = sc.nextInt();

                    System.out.print("Transfer Amount: ");
                    double transferAmount = sc.nextDouble();

                    transactionDAO.transferMoney(
                            fromAccount,
                            toAccount,
                            transferAmount
                    );
                    break;

                case 12:
                    System.out.print("Account ID: ");
                    accountId = sc.nextInt();

                    transactionDAO.viewTransactionHistory(accountId);
                    break;

                case 0:
                    System.out.println("Thank you!");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}