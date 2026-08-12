package dao;

import util.DBConnection;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
public class CustomerDAO {
    public void insertCustomer(int id,String name,String email,String phone,String address){
        String sql = "INSERT INTO Customer(customer_id,name,email,phone,address) VALUES(?,?,?,?,?)";
        try{
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,id);
            ps.setString(2,name);
            ps.setString(3,email);
            ps.setString(4,phone);
            ps.setString(5,address);
            ps.executeUpdate();
            System.out.println("Inserted Successfully.");
            ps.close();
            con.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public void viewCustomers(){
        String sql = "SELECT * FROM Customer";
        try{
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                System.out.println("Customer_Id : "+rs.getInt("customer_id"));
                System.out.println("Name: "+rs.getString("name"));
                System.out.println("Address: "+rs.getString("address"));
                System.out.println("Phone Number: "+rs.getString("phone"));
                System.out.println("Email : "+rs.getString("email"));
            }
            rs.close();
            ps.close();
            con.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public void updateCustomer(int id, String name,String email,String phone,String address){
        String sql = "UPDATE Customer SET name=?, email=?, phone=?, address=? WHERE customer_id=?";

        try{
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,name);
            ps.setString(2,email);
            ps.setString(3,phone);
            ps.setString(4,address);
            ps.setInt(5,id);

            int rows = ps.executeUpdate();
            if(rows > 0){
                System.out.println("Updated Successfully.");

            }
            else{
                System.out.println("Updated Failed.");
            }
            ps.close();
            con.close();

        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public void deleteCustomer(int id){
        String sql ="DELETE FROM Customer WHERE customer_id = ?";
        try{
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,id);
            int rows = ps.executeUpdate();
            if(rows > 0){
                System.out.println("Deleted Successfully.");
            }
            else{
                System.out.println("Failed to delete the Customer.");
            }
            ps.close();
            con.close();

        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
