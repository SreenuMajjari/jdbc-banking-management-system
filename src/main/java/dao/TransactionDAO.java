package dao;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import util.DBConnection;
public class TransactionDAO {
    public void deposit(int accountId, double amount) {
        if (amount <= 0) {
            System.out.println("Amount must be greater than 0.");
            return;
        }

        String updateBalance =
                "UPDATE Account SET balance = balance + ? WHERE account_id = ?";

        String insertTransaction =
                "INSERT INTO Transactions(account_id, transaction_type, amount, transaction_date, description) " +
                        "VALUES (?, ?, ?, NOW(), ?)";

        Connection con = null;

        try {
            con = DBConnection.getConnection();
            con.setAutoCommit(false);

            PreparedStatement ps1 = con.prepareStatement(updateBalance);
            ps1.setDouble(1, amount);
            ps1.setInt(2, accountId);

            int rows = ps1.executeUpdate();

            if (rows == 0) {
                System.out.println("Account not found.");
                con.rollback();
                return;
            }

            PreparedStatement ps2 = con.prepareStatement(insertTransaction);

            ps2.setInt(1, accountId);
            ps2.setString(2, "DEPOSIT");
            ps2.setDouble(3, amount);
            ps2.setString(4, "Cash deposit");

            ps2.executeUpdate();

            con.commit();

            System.out.println("Deposit successful.");

            ps1.close();
            ps2.close();
            con.close();

        } catch (Exception e) {

            try {
                if (con != null) {
                    con.rollback();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            e.printStackTrace();
        }
    }
    public void withdraw(int accountId, double amount) {
        if (amount <= 0) {
            System.out.println("Amount must be greater than 0.");
            return;
        }

        String updateBalance =
                "UPDATE Account SET balance = balance - ? " +
                        "WHERE account_id = ? AND balance >= ?";

        String insertTransaction =
                "INSERT INTO Transactions(account_id, transaction_type, amount, transaction_date, description) " +
                        "VALUES (?, ?, ?, NOW(), ?)";

        Connection con = null;

        try {
            con = DBConnection.getConnection();
            con.setAutoCommit(false);

            PreparedStatement ps1 = con.prepareStatement(updateBalance);

            ps1.setDouble(1, amount);
            ps1.setInt(2, accountId);
            ps1.setDouble(3, amount);

            int rows = ps1.executeUpdate();

            if (rows == 0) {
                System.out.println("Insufficient balance or account not found.");
                con.rollback();
                return;
            }

            PreparedStatement ps2 = con.prepareStatement(insertTransaction);

            ps2.setInt(1, accountId);
            ps2.setString(2, "WITHDRAW");
            ps2.setDouble(3, amount);
            ps2.setString(4, "Cash withdrawal");

            ps2.executeUpdate();

            con.commit();

            System.out.println("Withdrawal successful.");

            ps1.close();
            ps2.close();
            con.close();

        } catch (Exception e) {

            try {
                if (con != null) {
                    con.rollback();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            e.printStackTrace();
        }
    }

    public void transferMoney(int fromAccount, int toAccount, double amount) {
        if (amount <= 0) {
            System.out.println("Amount must be greater than 0.");
            return;
        }

        String withdrawSql =
                "UPDATE Account SET balance = balance - ? " +
                        "WHERE account_id = ? AND balance >= ?";

        String depositSql =
                "UPDATE Account SET balance = balance + ? " +
                        "WHERE account_id = ?";

        Connection con = null;

        try {
            con = DBConnection.getConnection();
            con.setAutoCommit(false);

            PreparedStatement ps1 = con.prepareStatement(withdrawSql);
            ps1.setDouble(1, amount);
            ps1.setInt(2, fromAccount);
            ps1.setDouble(3, amount);

            int withdrawn = ps1.executeUpdate();

            if (withdrawn == 0) {
                System.out.println("Insufficient balance or sender account not found.");
                con.rollback();
                return;
            }

            PreparedStatement ps2 = con.prepareStatement(depositSql);
            ps2.setDouble(1, amount);
            ps2.setInt(2, toAccount);

            int deposited = ps2.executeUpdate();

            if (deposited == 0) {
                System.out.println("Receiver account not found.");
                con.rollback();
                return;
            }

            con.commit();

            System.out.println("Money transferred successfully.");

            ps1.close();
            ps2.close();
            con.close();

        } catch (Exception e) {

            try {
                if (con != null) {
                    con.rollback();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            e.printStackTrace();
        }
    }
    public void viewTransactionHistory(int accountId) {

        String sql = "SELECT * FROM Transactions WHERE account_id = ?";

        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, accountId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                System.out.println("Transaction ID: " + rs.getInt("transaction_id"));
                System.out.println("Account ID: " + rs.getInt("account_id"));
                System.out.println("Type: " + rs.getString("transaction_type"));
                System.out.println("Amount: " + rs.getDouble("amount"));
                System.out.println("Date: " + rs.getTimestamp("transaction_date"));
                System.out.println("Description: " + rs.getString("description"));
                System.out.println("-------------------------");
            }

            rs.close();
            ps.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
