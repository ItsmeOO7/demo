package application;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Window;

 

   


public class AdminController implements Initializable{
	
	@FXML
	private TableColumn<users, String> Email_ID;

	@FXML
	private TableColumn<users, String> Full_name;

	@FXML
	private TableColumn<users, String> Pass;
	
    @FXML
	private TableColumn<users, String> age;
    
    @FXML
    private TableColumn<users, String> number;
    
    @FXML
    private TableView<users> user_table;
    
    @FXML
    private Button btnAnly;

    @FXML
    private Button btnDash;

    @FXML
    private Button btnLog;
    
    @FXML
    private TableColumn<foodreq, String> Fr_type;

    @FXML
    private TableView<foodreq> Frq_table;
    
    @FXML
    private TableColumn<foodreq, String> R_w;
    
    @FXML
    private TableColumn<foodreq, String> Req_Id;

    @FXML
    private TableColumn<foodreq, String> Req_name;
    
    @FXML
    private TableColumn<foodreq, String> status;
    
    @FXML
    private TableColumn<foodreq, String> F_center;
    
    @FXML
    private TableColumn<foodreq, String> Req_fname;
    
    @FXML
    private TableColumn<foodreq , String> Fr_date;
    @FXML
    private TableColumn<foodreq , Integer> Req_num;
    
    @FXML
    private TextField R_ID;
    
    @FXML
    private Button Apr;
    
    @FXML
    private Button dec;
    
    @FXML
    private TextField E_ID;
    
    @FXML
    private TextField F_nm;
    
    @FXML
    private TextField Uage;

    @FXML
    private TextField Unum;

    @FXML
    private TextField Upass;


    
    
    Jdbc jdbc = new Jdbc();

    
    @FXML
	private void HandleClicks(ActionEvent e ) throws IOException, SQLException{
		
		    if(e.getSource() == btnDash)
		    {
		    	btnAnly.setStyle("-fx-background-color:  #FFDDCC");
		    	btnDash.setStyle("-fx-background-color: #fba177");
		    	
		    	
		  //  tabpn1.setVisible(true);
		  //  pn2.setVisible(false);
		    	
		    }
		    if(e.getSource() == btnAnly) 
		    {   
		    	btnDash.setStyle("-fx-background-color:  #FFDDCC");
		    	btnAnly.setStyle("-fx-background-color: #fba177");
		    	//user_details();
		    	
		   // tabpn1.setVisible(false);
		  //  pn2.setVisible(true);
		    }
		    if(e.getSource() == btnLog) 
		    {   
		    	switchToHome();
		    }
	        
	     }
    
    
    ObservableList<foodreq> feq;
	@FXML
    private void F_details() throws IOException, SQLException {
		 
		 //  Window owner = Donate.getScene().getWindow();
		  // String ID = Welcome.getText();
		  // donation don = new donation();
		   
		   feq =jdbc.getAllReq();
		   
		   

		          
		        
		 
	//	 String check= dn.get(0).getId();
		
		   //jdbc.getRecord(ID);
		 //  System.out.println(check +"hi");	
		   Req_num.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getNum()).asObject());
		   Req_Id.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getId()) );
		   Req_name.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
		   Req_fname.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFoodname()));
		   R_w.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFoodweight()));
		   F_center.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCenter()));
		   Fr_date.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getdate()));
		    status.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStatus()));
		   
		   
		   
		   
	 
		   
		    Frq_table.setItems(feq);
		    
		
		   
		   
		         
	       

    }
	
	public void addUserSelect() {
        users user = user_table.getSelectionModel().getSelectedItem();
        int num = user_table.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }

      
       E_ID.setText(user.getEmail());
       F_nm.setText(user.getFullname());
       Upass.setText(user.getPass());
       Uage.setText(user.getAge());
       Unum.setText(user.getNumber());
        
    }
	
	
	public void addRequestSelect() {
        foodreq frq = Frq_table.getSelectionModel().getSelectedItem();
        int num = Frq_table.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }

      
        R_ID.setText(String.valueOf(frq.getNum()));
        
    }
	
	String stat1="approve";
	String stat2="Decline";
	@FXML
    private void stat( ActionEvent e) throws IOException, SQLException {
		 
		   Window owner = Apr.getScene().getWindow();
		   int ID = Integer.parseInt(R_ID.getText());
	     
	                

	       
	              Jdbc jdbc = new Jdbc();
	              
	              if(e.getSource() == Apr)
	              {
	            	  jdbc.UpdateStat(stat1,ID);
	            	  showAlert(Alert.AlertType.CONFIRMATION, owner, "Updated Successful!" ,"Approved");
	              }
	              
	              if(e.getSource() == dec)
	              {
	            	  jdbc.UpdateStat(stat2,ID);
	            	  showAlert(Alert.AlertType.CONFIRMATION, owner, "Updated Successful!" ,"Declined");
	              }
	            

    }
	
	@FXML
	 private void Updateuser() throws IOException, SQLException {
		 
		   Window owner = Apr.getScene().getWindow();
		   String email = E_ID.getText();
		   String name = F_nm.getText();
		   String Password = Upass.getText();
		   String age = Uage.getText();
		   String number = Unum.getText();
	                

	       
	              Jdbc jdbc = new Jdbc();
	              
	             
	            	  jdbc.UpdateUser(email, name, Password, age, number, email);
	            	  showAlert(Alert.AlertType.CONFIRMATION, owner, "Updated Successful!" ,"Updated Successful!");
	             
	             
	            	  
	             

  }
	
	
	@FXML
	 private void Createuser() throws IOException, SQLException {
		 
		   Window owner = Apr.getScene().getWindow();
		   String email = E_ID.getText();
		   String name = F_nm.getText();
		   String Password = Upass.getText();
		   String age = Uage.getText();
		   String number = Unum.getText();
	                

	       
	              Jdbc jdbc = new Jdbc();
	              
	             
	            	  jdbc.insertRecord(email, name, Password, age, number);
	            	  showAlert(Alert.AlertType.CONFIRMATION, owner, "Successful" ,"User Created Successfully!");
	             
	             
	            	  
	             

 }
	@FXML
	 private void Deleteuser() throws IOException, SQLException {
		 
		   Window owner = Apr.getScene().getWindow();
		   String email = E_ID.getText();
		  
	                

	       
	              Jdbc jdbc = new Jdbc();
	              
	             
	            	  jdbc.DeleteUser(email);
	            	  showAlert(Alert.AlertType.CONFIRMATION, owner, "Successful" ,"User Deleted Successfully !!!");
	             
	             
	            	  
	             

 }
	
	
	private static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }
	
	@FXML
	private void refresh() {
		
		try {
			user =jdbc.getAllUserRecord();
			Email_ID.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmail()) );
			Full_name.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFullname()) );  
			Pass.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPass()));   
			age.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAge()) );  
			number.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNumber()) );  
			   
		 
			   
			user_table.setItems(user);
			    
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
    
    @FXML
    private void switchToHome() throws IOException {
       Main.setRoot("Home");
    }
	
    
    @FXML
    ObservableList<users> user;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
		btnDash.setStyle("-fx-background-color: #fba177");
		
		 try {
			user =jdbc.getAllUserRecord();
			Email_ID.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmail()) );
			Full_name.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFullname()) );  
			Pass.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPass()));   
			age.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAge()) );  
			number.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNumber()) );  
			   
		 
			   
			user_table.setItems(user);
			    
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}