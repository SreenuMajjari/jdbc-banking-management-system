package dao;
import util.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
public class AccountDAO {
    public void createAccount(int account_id,int customerId,long accountNumber,String accountType,double balance,String status){
        String sql = "INSERT INTO Account(account_id,customer_id,account_number,account_type,balance,status,created_at) VALUES(?, ?, ?, ?, ?, ?, NOW())";
        try{
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,account_id);
            ps.setInt(2,customerId);
            ps.setLong(3,accountNumber);
            ps.setString(4,accountType);
            ps.setDouble(5,balance);
            ps.setString(6,status);
            int rows = ps.executeUpdate();
            if(rows > 0){
                System.out.println("Account is Successfully created.");
            }
            else {
                System.out.println("Failed to create a Account.");
            }
            ps.close();
            con.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public void viewAccount(int id){
        String sql = "SELECT * FROM Account WHERE Customer_id = ?";
        try{
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                System.out.println("Account_Id : "+rs.getInt("account_id"));
                System.out.println("Customer_Id : "+rs.getInt("customer_id"));
                System.out.println("Account_Number : "+rs.getLong("account_number"));
                System.out.println("Account_Type : "+rs.getString("account_type"));
                System.out.println("Balance : "+rs.getDouble("balance"));
                System.out.println("Status : "+rs.getString("status"));
                System.out.println("Created_Date :"+rs.getDate("created_at"));
            }
            rs.close();
            ps.close();
            con.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }

    }
    public void updateAccount(int accountId,String accountType,String status){
        String sql = "UPDATE Account SET account_type = ? , status = ? WHERE account_id = ?";
        try{
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,accountType);
            ps.setString(2,status);
            ps.setInt(3,accountId);
            int rows = ps.executeUpdate();
            if(rows > 0){
                System.out.println("Successfully Updated.");
            }
            else{
                System.out.println("Not updated Successfully.");
            }
            ps.close();
            con.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public void viewAccounts(){
        String sql = "SELECT * FROM Account ";
        try{
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                System.out.println("Account_Id : "+rs.getInt("account_id"));
                System.out.println("Customer_Id : "+rs.getInt("customer_id"));
                System.out.println("Account_Number : "+rs.getLong("account_number"));
                System.out.println("Account_Type : "+rs.getString("account_type"));
                System.out.println("Balance : "+rs.getDouble("balance"));
                System.out.println("Status : "+rs.getString("status"));
                System.out.println("Created_Date :"+rs.getDate("created_at"));
            }
            rs.close();
            ps.close();
            con.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }

    }
    public void deleteAccount(int account_id){
        String sql = "DELETE FROM Account where account_id = ?";
        try{
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,account_id);
            int rows = ps.executeUpdate();
            if(rows > 0){
                System.out.println("Deleted Successfully.");
            }
            else{
                System.out.println("Failed to delete the acount");
            }
            ps.close();
            con.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
