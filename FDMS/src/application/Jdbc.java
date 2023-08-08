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
	 private static final String SEARCH_A = "SELECT * FROM admin WHERE ID = ? and Pass = ?";
	 private static final String SEARCH_U = "SELECT * FROM user_info";
	 private static final String DELETE_U = "DELETE FROM user_info WHERE Email =?";
	 private static final String SELECT_Bar = "SELECT Date, sum(Food_weight) AS sum FROM ood.donation Group BY Date order by Timestamp(Date) asc LIMIT 8";
	 private static final String SELECT_pie = "SELECT Center, sum(Food_weight) AS sum FROM ood.donation Group BY Center ";
	 private static final String SEARCH_RQ = "SELECT * FROM food_request";
	 private static final String SELECT_QUERY = "SELECT ID, Food_name, Ftype , Food_weight, Center ,Ex_Date , Date FROM donation WHERE ID=?";
	 private static final String INSERT_D = "INSERT INTO donation (ID, Food_name, Ftype , Food_weight, Center ,Ex_Date , Date) VALUES (?, ?, ?, ?, ?, ? ,? )";
	 private static final String SEARCH_user = "SELECT * FROM user_info WHERE Email=?";
	 private static final String UP_QUERY = "UPDATE user_info set Email = ?, Full_name=?, Pass = ?, Age = ?, Number= ? WHERE Email = ?";
	 private static final String UP_REQ = "UPDATE food_request set Status=? WHERE Req_num = ?";
	 private static final String INSERT_R = "INSERT INTO food_request (Req_id, Name, Food_name , Food_weight, Center ,Del_date , Status) VALUES (?, ?, ?, ?, ?, ? ,?)";
	 private static final String SELECT_R = "SELECT Req_id, Name, Food_name , Food_weight, Center ,Del_date , Status ,Req_num FROM food_request WHERE Req_id=?";
	 
	 
	 
	 
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
	      
	      
	      public void RequestFood(String Id, String Name, String Food_Name, String Food_weight , String Center, String Date , String Status) throws SQLException {

		        // Step 1: Establishing a Connection and 
		        // try-with-resource statement will auto close the connection.
		        try (
		                
		            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ood?allowPublicKeyRetrieval=true&useSSL=false","root","root");
		            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_R) ){
		            preparedStatement.setString(1, Id);
		            preparedStatement.setString(2, Name);
		            preparedStatement.setString(3, Food_Name);
		            preparedStatement.setString(4, Food_weight);
		            preparedStatement.setString(5, Center);
		            preparedStatement.setString(6, Date);
		            preparedStatement.setString(7, Status);
		            System.out.println(preparedStatement);
		            // Step 3: Execute the query or update query
		            preparedStatement.executeUpdate();
		        } catch (SQLException e) {
		            // print SQL exception information
		            printSQLException(e);
		        }
		    }
	      
	      public void UpdateStat(String status, int num) throws SQLException {

		        // Step 1: Establishing a Connection and 
		        // try-with-resource statement will auto close the connection.
		        try (
		                
		            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ood","root","root");
		            PreparedStatement preparedStatement = connection.prepareStatement(UP_REQ) ){
		            preparedStatement.setString(1, status);
		            preparedStatement.setInt(2, num);
		           
		            System.out.println(preparedStatement);
		            // Step 3: Execute the query or update query
		            preparedStatement.executeUpdate();
		        } catch (SQLException e) {
		            // print SQL exception information
		            printSQLException(e);
		        }
		    }
	      
	      public void DeleteUser(String Email) throws SQLException {

		        // Step 1: Establishing a Connection and 
		        // try-with-resource statement will auto close the connection.
		        try (
		                
		            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ood","root","root");
		            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_U) ){
		            preparedStatement.setString(1, Email);
		            
		           
		            System.out.println(preparedStatement);
		            // Step 3: Execute the query or update query
		            preparedStatement.execute();
		        } catch (SQLException e) {
		            // print SQL exception information
		            printSQLException(e);
		        }
		    }  
	      
	      
	 	 public  ObservableList<foodreq> getReq(String Id) throws SQLException
	 	    {
	 		 ObservableList<foodreq> req = FXCollections.observableArrayList();
	 	        try(
	 	            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ood","root","root");
	 	            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_R);) 
	 	        {
	 	            preparedStatement.setString(1, Id);
	 	            
	 	            System.out.println(preparedStatement);
	 	            ResultSet rs = preparedStatement.executeQuery();
	 	            foodreq fr ;
	 	            while(rs.next())
	 	            {
	 	            	
	 	            	fr= new foodreq(
	 	            			rs.getString("Req_id"),
	 	            			rs.getString("Name"),
	 	            			rs.getString("Food_name"),
	 	            			rs.getString("Food_weight"),
	 	            			rs.getString("Center"),
	 	            			rs.getString("Del_date"),
	 	            			rs.getString("Status"),
	 	            			rs.getInt("Req_num"));
	 	            	
	 	            	
	 	            	
	 	            	
	 	            	
	 	                req.add(fr);
	 	               
	 	           
	 	            }
	 	           
	 	        }catch(SQLException e){
	 	        
	 	            printSQLException(e);     
	 	        }
	 	        return req;
	 	    }
	 
	 	 public  ObservableList<Bardata> getData() throws SQLException
	 	    {
	 		 ObservableList<Bardata> data = FXCollections.observableArrayList();
	 	        try(
	 	            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ood","root","root");
	 	            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_Bar);) 
	 	        {
	 	           // preparedStatement.setString(1, Id);
	 	            
	 	            System.out.println(preparedStatement);
	 	            ResultSet rs = preparedStatement.executeQuery();
	 	            Bardata bd ;
	 	            while(rs.next())
	 	            {
	 	            	
	 	            	bd= new Bardata(
	 	            			rs.getString("Date"),
	 	            			rs.getString("sum")
	 	            			
	 	            			);
	 	            	
	 	            	
	 	            	
	 	            	
	 	            	
	 	            	
	 	                data.add(bd);
	 	               
	 	           
	 	            }
	 	           
	 	        }catch(SQLException e){
	 	        
	 	            printSQLException(e);     
	 	        }
	 	        return data;
	 	    }
	 	 
	 	 
	 	public  ObservableList<Pie> getPData() throws SQLException
 	    {
 		 ObservableList<Pie> data = FXCollections.observableArrayList();
 	        try(
 	            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ood","root","root");
 	            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_pie);) 
 	        {
 	           // preparedStatement.setString(1, Id);
 	            
 	            System.out.println(preparedStatement);
 	            ResultSet rs = preparedStatement.executeQuery();
 	            Pie p ;
 	            while(rs.next())
 	            {
 	            	
 	            	p= new Pie(
 	            			rs.getString("Center"),
 	            			rs.getString("sum")
 	            			
 	            			);
 	            	
 	            	
 	            	
 	            	
 	            	
 	            	
 	                data.add(p);
 	               
 	           
 	            }
 	           
 	        }catch(SQLException e){
 	        
 	            printSQLException(e);     
 	        }
 	        return data;
 	    }
	 	 
	 	 public  ObservableList<users> getAllUserRecord() throws SQLException
		    {
			 ObservableList<users> udata = FXCollections.observableArrayList();
		        try(
		            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ood","root","root");
		            PreparedStatement preparedStatement = connection.prepareStatement(SEARCH_U);) 
		        {
		            //preparedStatement.setString(1, Id);
		            
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
	 	 
	 	 
	 	public boolean searchAdmin(String emailId, String password) throws SQLException
	    {
	        
	        try(
	            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ood","root","root");
	            PreparedStatement preparedStatement = connection.prepareStatement(SEARCH_A);) 
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
	 	
	 	
	 	 public  ObservableList<foodreq> getAllReq() throws SQLException
	 	    {
	 		 ObservableList<foodreq> req = FXCollections.observableArrayList();
	 	        try(
	 	            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ood","root","root");
	 	            PreparedStatement preparedStatement = connection.prepareStatement(SEARCH_RQ);) 
	 	        {
	 	            //preparedStatement.setString(1, Id);
	 	            
	 	            System.out.println(preparedStatement);
	 	            ResultSet rs = preparedStatement.executeQuery();
	 	            foodreq fr ;
	 	            while(rs.next())
	 	            {
	 	            	
	 	            	fr= new foodreq(
	 	            			rs.getString("Req_id"),
	 	            			rs.getString("Name"),
	 	            			rs.getString("Food_name"),
	 	            			rs.getString("Food_weight"),
	 	            			rs.getString("Center"),
	 	            			rs.getString("Del_date"),
	 	            			rs.getString("Status"),
	 	            			rs.getInt("Req_num")
	 	            			) ;
	 	                req.add(fr);
	 	               
	 	           
	 	            }
	 	           
	 	        }catch(SQLException e){
	 	        
	 	            printSQLException(e);     
	 	        }
	 	        return req;
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


