package application;


import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.List;
//import java.sql.SQLException;
import java.util.ResourceBundle;



import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Window;
import javafx.util.Callback;

import application.Jdbc;

public class DashboardController  implements Initializable{
	    
	
        @FXML
        private DatePicker date;

       @FXML
       private DatePicker exdate;
 
       @FXML
       private TextField fname;

	    @FXML
	    private Button Donate;

	    @FXML
	    private TextField Fw;
	    
	    @FXML
	    private Button btnDash;

	    @FXML
	    private Button btnLog;

	    @FXML
	    private Button btnProf;

	    @FXML
	    private AnchorPane pn2;

	    @FXML
	    private AnchorPane tabpn1;
	    
	    @FXML
	     private Label Welcome;
	    
	    @FXML
	    private ComboBox<String> combo;
	    
	    @FXML
	    private ComboBox<String> ucombo;
	    
	    @FXML
	    private ComboBox<String> Ccombo;
	    
	    @FXML
	    private TableColumn<donation , String> Don_date;
	    
	    @FXML
	    private TableColumn<donation , String> Fd_exd;

	    @FXML
	    private TableColumn<donation , String> Fd_name;

	    @FXML
	    private TableColumn<donation , String> Fd_type;

	    @FXML
	    private TableColumn<donation , String> Fd_weight;
	    
	    @FXML
	    private TableColumn<donation , String> don_ID;

	    @FXML
	    private TableView<donation> don_table;
	    
	    @FXML
	    private TableColumn<donation , String> fd_center;
	    
	    
	    @FXML
	    private TextField U_Id;

	    @FXML
	    private TextField U_age;

	    @FXML
	    private TextField U_name;

	    @FXML
	    private PasswordField U_pass;

	    @FXML
	    private TextField U_pn;
	    
	    
	    Jdbc jdbc = new Jdbc();
	    
	    int index =-1;
	    loginData data = loginData.getinstance ();
	 
	    
	  
	    
	    
	@FXML
	private void HandleClicks(ActionEvent e ) throws IOException, SQLException{
		
		    if(e.getSource() == btnDash)
		    {
		    	btnProf.setStyle("-fx-background-color:  #FFDDCC");
		    	btnDash.setStyle("-fx-background-color: #fba177");
		    	
		    	
		    tabpn1.setVisible(true);
		    pn2.setVisible(false);
		    	
		    }
		    if(e.getSource() == btnProf) 
		    {   
		    	btnDash.setStyle("-fx-background-color:  #FFDDCC");
		    	btnProf.setStyle("-fx-background-color: #fba177");
		    	user_details();
		    	
		    tabpn1.setVisible(false);
		    pn2.setVisible(true);
		    }
		    if(e.getSource() == btnLog) 
		    {   
		    	switchToHome();
		    }
	        
	     }
	
	
	@FXML
    private void Donate() throws IOException, SQLException {
		 
		   Window owner = Donate.getScene().getWindow();
		   String ID = Welcome.getText();
		         
	       
	        if (fname.getText().isEmpty()) {
	            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
	                "Please enter Food Name");
	            return;
	        }
	        
	        
	        if (Fw.getText().isEmpty()) {
	            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
	                "Please enter Food weight");
	            return;
	        }
	        
	               
	        String food_type =combo.getSelectionModel().getSelectedItem().toString();
			String unit =ucombo.getSelectionModel().getSelectedItem().toString();
			String center =Ccombo.getSelectionModel().getSelectedItem().toString();
	        String foodName = fname.getText();
	        String foodweight= Fw.getText();
	        LocalDate exDate = exdate.getValue();
	        String myexDate =exDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
	        LocalDate Date = date.getValue();
	        String myDate =Date.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
	        

	       
	              Jdbc jdbc = new Jdbc();
	              jdbc.insertDon(ID,foodName,food_type,foodweight,center,myexDate,myDate);
	        
	            showAlert(Alert.AlertType.CONFIRMATION, owner, "Donation Successful!" ,"Thank You For Donation");
	       
	     
	       
		

    }
	
	
	
	@FXML
    private void up_user() throws IOException, SQLException {
		 
		   Window owner = Donate.getScene().getWindow();
		   String ID = Welcome.getText();
	     
	        String email = U_Id.getText();
	        String name = U_name.getText();
	        String pass = U_pass.getText();
	        String age = U_age.getText();
	        String num = U_pn.getText();
	        

	       
	              Jdbc jdbc = new Jdbc();
	              jdbc.UpdateUser(email, name, pass, age, num,ID);
	        
	            showAlert(Alert.AlertType.CONFIRMATION, owner, "Updated Successful!" ," ");
	       
	     
	       
		

    }
	
	List<users> ud;
	@FXML
	private void user_details() throws IOException, SQLException {
		 String ID = Welcome.getText();
		   
		 ud =jdbc.getUserRecord(ID);
		  Iterator<users> iterate = ud.iterator();
	       
	        while(iterate.hasNext())
	        {   
	                users u = new users();
	                u = iterate.next();
	                U_Id.setText(u.getEmail());
	                U_name.setText(u.getFullname());
	                U_pass.setText(u.getPass());
	                U_age.setText(u.getAge());
	                U_pn.setText(u.getNumber());
	                
	                
	        }
		
		
	}
	
	
	ObservableList<donation> dn;
	@FXML
    private void details() throws IOException, SQLException {
		 
		   Window owner = Donate.getScene().getWindow();
		   String ID = Welcome.getText();
		  // donation don = new donation();
		   
		   dn =jdbc.getRecord(ID);
		   
		   

		          
		        
		 
	//	 String check= dn.get(0).getId();
		
		   //jdbc.getRecord(ID);
		 //  System.out.println(check +"hi");	
		   
		   don_ID.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getId())); 
		   Fd_name.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFoodname()));
		   Fd_type.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFoodtype()));
		   Fd_weight.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFoodweight()));
		   fd_center.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCenter()));
		   Fd_exd.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEXDate()));
		   Don_date.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDate()));
		   
		   
	 
		   
		    don_table.setItems(dn);
		    
		
		   
		   
		         
	       

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
    private void switchToHome() throws IOException {
       Main.setRoot("Home");
    }
	

   
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
	
	Welcome.setText(data.getId());
	
	
    btnDash.setStyle("-fx-background-color: #fba177");
    combo.setItems(FXCollections.observableArrayList("Fruits","Groceries","Drinks"));
    ucombo.setItems(FXCollections.observableArrayList("lb","kg","ltr"));
    Ccombo.setItems(FXCollections.observableArrayList("Copley","Park Street","LMA"));
    
    
    
  
		
	}
	
	
	
}