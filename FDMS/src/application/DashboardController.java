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

import javafx.beans.property.SimpleIntegerProperty;
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
	    private Button Request;
	    
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
	    private TableColumn<foodreq , String> Fr_date;
	    
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
	    
	    @FXML
	    private DatePicker D_date;
	    
	    @FXML
	    private ComboBox<String> Rcombo;

	    @FXML
	    private ComboBox<String> Reqcombo;
	    
	    @FXML
	    private TextField F_name;
	    
	    @FXML
	    private TextField F_weight;

	    @FXML
	    private Button get_det;

	    @FXML
	    private TextField name;
        
	    
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
	    private TableColumn<foodreq, Integer> Req_num;

	    
	    
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
	       
	        fname.setText("");
	        combo.getSelectionModel().clearSelection();
	        Fw.setText("");
	        ucombo.getSelectionModel().clearSelection();
	        Ccombo.getSelectionModel().clearSelection();
	        exdate.setValue(null);
	        date.setValue(null);
	       
		

    }
	
	
	
	@FXML
    private void up_user() throws IOException, SQLException {
		 
		   Window owner = btnProf.getScene().getWindow();
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
		 
		 //  Window owner = Donate.getScene().getWindow();
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
	
	ObservableList<foodreq> feq;
	@FXML
    private void F_details() throws IOException, SQLException {
		 
		 //  Window owner = Donate.getScene().getWindow();
		   String ID = Welcome.getText();
		  // donation don = new donation();
		   
		   feq =jdbc.getReq(ID);
		   
		   

		          
		        
		 
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
	
	
 String stat ="Pending";
	
	@FXML
    private void Request() throws IOException, SQLException {
		 
		   Window owner = Request.getScene().getWindow();
		   String ID = Welcome.getText();
		         
	       
	        if (name.getText().isEmpty()) {
	            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
	                "Please enter Name");
	            return;
	        }
	        

	        if (F_name.getText().isEmpty()) {
	            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
	                "Please enter Food Name");
	            return;
	        }
	        
	        if (F_weight.getText().isEmpty()) {
	            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
	                "Please enter Food weight");
	            return;
	        }
	        
	               
	        
	        String Name = name.getText();
			String center =Reqcombo.getSelectionModel().getSelectedItem().toString();
	        String foodName = F_name.getText();
	        String foodweight= F_weight.getText();
	       
	        LocalDate Date = D_date.getValue();
	        String myDate =Date.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
	        

	       
	              Jdbc jdbc = new Jdbc();
	              jdbc.RequestFood(ID,Name,foodName,foodweight,center,myDate,stat);
	        
	            showAlert(Alert.AlertType.CONFIRMATION, owner, "Request send Successful!" ,"We will get back to you soon");
	       
	            name.setText("");
		        Reqcombo.getSelectionModel().clearSelection();
		        F_name.setText("");
		        Rcombo.getSelectionModel().clearSelection();
		        F_weight.setText("");
		        D_date.setValue(null);
		        
	       
		

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
    Rcombo.setItems(FXCollections.observableArrayList("lb","kg","ltr"));
    Reqcombo.setItems(FXCollections.observableArrayList("Copley","Park Street","LMA"));
    
    
    
    
  
		
	}
	
	
	
}