package application;



import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;





public class Jdbc{
	
	 private static final String INSERT_QUERY = "INSERT INTO user_info (Email, Full_name, Pass , Age, Number) VALUES (?, ?, ?, ?, ?)";
	 private static final String SEARCH_Q = "SELECT * FROM user_info WHERE Email = ? and Pass = ?";
	 private static final String SELECT_QUERY = "SELECT ID, Food_name, Ftype , Food_weight, Center ,Ex_Date , Date FROM donation WHERE ID=?";
	 private static final String INSERT_D = "INSERT INTO donation (ID, Food_name, Ftype , Food_weight, Center ,Ex_Date , Date) VALUES (?, ?, ?, ?, ?, ? ,? )";
	 private static final String SEARCH_user = "SELECT * FROM user_info WHERE Email=?";
	 private static final String UP_QUERY = "UPDATE user_info set Email = ?, Full_name=?, Pass = ?, Age = ?, Number= ? WHERE Email = ?";
	 
	 
	 
	 
	 
	 public void insertRecord(String emailId, String fullName, String password , String Age , String Number) throws SQLException {

	        // Step 1: Establishing a Connection and 
	        // try-with-resource statement will auto close the connection.
	        try (
	                
	            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ood?allowPublicKeyRetrieval=true&useSSL=false","root","root");
	            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY) ){
	            preparedStatement.setString(1, emailId);
	            preparedStatement.setString(2, fullName);
	            preparedStatement.setString(3, password);
	            preparedStatement.setString(4, Age);
	            preparedStatement.setString(5, Number);
	            System.out.println(preparedStatement);
	            // Step 3: Execute the query or update query
	            preparedStatement.executeUpdate();
	        } catch (SQLException e) {
	            // print SQL exception information
	            printSQLException(e);
	        }
	    }
	 
	 public boolean searchRecord(String emailId, String password) throws SQLException
	    {
	        
	        try(
	            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ood","root","root");
	            PreparedStatement preparedStatement = connection.prepareStatement(SEARCH_Q);) 
	        {
	            preparedStatement.setString(1,emailId);
	            preparedStatement.setString(2,password);
	            System.out.println(preparedStatement);
	            ResultSet rs = preparedStatement.executeQuery();
	            if(rs.next())
	            {
	                return true;
	            }     
	        }catch(SQLException e){
	        
	            printSQLException(e);     
	        }
	        return false;
	    }
	 
	 public void insertDon(String Id, String foodName, String Food_type, String Food_weight , String Center, String Ex_date , String Date) throws SQLException {

	        // Step 1: Establishing a Connection and 
	        // try-with-resource statement will auto close the connection.
	        try (
	                
	            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ood?allowPublicKeyRetrieval=true&useSSL=false","root","root");
	            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_D) ){
	            preparedStatement.setString(1, Id);
	            preparedStatement.setString(2, foodName);
	            preparedStatement.setString(3, Food_type);
	            preparedStatement.setString(4, Food_weight);
	            preparedStatement.setString(5, Center);
	            preparedStatement.setString(6, Ex_date);
	            preparedStatement.setString(7, Date);
	            System.out.println(preparedStatement);
	            // Step 3: Execute the query or update query
	            preparedStatement.executeUpdate();
	        } catch (SQLException e) {
	            // print SQL exception information
	            printSQLException(e);
	        }
	    }
	 
	 public  ObservableList<donation> getRecord(String Id) throws SQLException
	    {
		 ObservableList<donation> data = FXCollections.observableArrayList();
	        try(
	            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ood","root","root");
	            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_QUERY);) 
	        {
	            preparedStatement.setString(1, Id);
	            
	            System.out.println(preparedStatement);
	            ResultSet rs = preparedStatement.executeQuery();
	            donation don ;
	            while(rs.next())
	            {
	            	
	            	don= new donation(rs.getString("ID"),
	            			rs.getString("Food_name"),
	            			rs.getString("Ftype"),
	            			rs.getString("Food_weight"),
	            			rs.getString("Center"),
	            			rs.getString("Ex_Date"),
	            			rs.getString("Date")) ;
	                data.add(don);
	               
	            /*   don.setId(rs.getString("ID"));
	               System.out.println(don.getId());
	               don.setFoodname(rs.getString("Food_name"));
	               System.out.println(don.getFoodname());
	               don.setFoodtype(rs.getString("Ftype"));
	               System.out.println(don.getFoodtype());
	               don.setFoodweight(rs.getString("Food_weight"));
	               System.out.println(don.getFoodweight());
	               don.setCenter(rs.getString("Center"));
	               System.out.println(don.getCenter());
	               don.setEXDate(rs.getString("Ex_Date"));
	               System.out.println(don.getEXDate());
	               don.setDate(rs.getString("Date"));
	               data.add(don);*/
	            	
	              // data.add(new donation(rs.getString("ID"),rs.getString("Food_name"),rs.getString("Ftype"),rs.getString("Food_weight"),rs.getString("Center"),rs.getString("Ex_Date"),rs.getString("Date")));
	               //System.out.println(data);
	            }
	           
	        }catch(SQLException e){
	        
	            printSQLException(e);     
	        }
	        return data;
	    }
	     
	 
	 public  ObservableList<users> getUserRecord(String Id) throws SQLException
	    {
		 ObservableList<users> udata = FXCollections.observableArrayList();
	        try(
	            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ood","root","root");
	            PreparedStatement preparedStatement = connection.prepareStatement(SEARCH_user);) 
	        {
	            preparedStatement.setString(1, Id);
	            
	            System.out.println(preparedStatement);
	            ResultSet rs = preparedStatement.executeQuery();
	            users data ;
	            while(rs.next())
	            {
	            	
	            	data= new users(rs.getString("Email"),
	            			rs.getString("Full_name"),
	            			rs.getString("Pass"),
	            			rs.getString("Age"),
	            			rs.getString("Number"));
	            			
	                udata.add(data);
	               
	           
	            }
	           
	        }catch(SQLException e){
	        
	            printSQLException(e);     
	        }
	        return udata;
	    }
	 
	      public void UpdateUser(String email, String fname, String pass, String age , String num , String Id) throws SQLException {

	        // Step 1: Establishing a Connection and 
	        // try-with-resource statement will auto close the connection.
	        try (
	                
	            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ood","root","root");
	            PreparedStatement preparedStatement = connection.prepareStatement(UP_QUERY) ){
	            preparedStatement.setString(1, email);
	            preparedStatement.setString(2, fname);
	            preparedStatement.setString(3, pass);
	            preparedStatement.setString(4, age);
	            preparedStatement.setString(5, num);
	            preparedStatement.setString(6, Id);
	           
	            System.out.println(preparedStatement);
	            // Step 3: Execute the query or update query
	            preparedStatement.executeUpdate();
	        } catch (SQLException e) {
	            // print SQL exception information
	            printSQLException(e);
	        }
	    }
	 
	 
	     /*  @FXML 
	      public List<users> getUser(String emailid) throws SQLException, IOException {
		 
	        
	        List<users> user = new ArrayList<>();
	        Jdbc jdbc = new Jdbc();
	      
	        user = jdbc.getRecord(emailid);
	        return user;
	    }*/
	 
	 
	 
	 
	 
	 public static void printSQLException(SQLException ex) {
	        for (Throwable e: ex) {
	            if (e instanceof SQLException) {
	                e.printStackTrace(System.err);
	                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
	                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
	                System.err.println("Message: " + e.getMessage());
	                Throwable t = ex.getCause();
	                while (t != null) {
	                    System.out.println("Cause: " + t);
	                    t = t.getCause();
	                }
	            }
	        }
	    }
}


