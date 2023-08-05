package application;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.ResourceBundle;



import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Window;
import javafx.scene.layout.StackPane;

/**
 * 
 * 
 */
public class HomeController implements Initializable{
    
	@FXML 
	private Button log;
	
	@FXML
    private TextField Uid;
	
	@FXML
    private PasswordField pass;
	
	loginData data = loginData.getinstance ();
	
    
	//private Parent root;
	
	 public void login(ActionEvent event) throws IOException, SQLException {
		 
		 Window owner = log.getScene().getWindow();

	        System.out.println(Uid.getText());
	        System.out.println(pass.getText());
	        
	        if (Uid.getText().isEmpty()) {
	            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
	                "Please enter your User Id");
	            return;
	        }
	        if (pass.getText().isEmpty()) {
	            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
	                "Please enter a password");
	            return;
	        }
	        
	        String Id = Uid.getText();
	        String password = pass.getText();   
	        Jdbc jdbc = new Jdbc();   
	        
	        boolean check = jdbc.searchRecord(Id, password);
	        if(check)
	        {
	            showAlert(Alert.AlertType.CONFIRMATION, owner, "Login Successful!","Welcome ");
	            
	            data.setId(Uid.getText());	            
	            switchToDash();
	            System.out.println("working");
	            return;
	        }
	        showAlert(Alert.AlertType.ERROR, owner, "Login unsuccessful","Try again");
	        
	        
	        
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
    private void switchToReg() throws IOException {
       Main.setRoot("Register");
    }
    
    @FXML
    private void switchToDash() throws IOException{
       Main.setRoot("Dashboard1");
    
    }
    
    
       
    @FXML
    private void switchToTeam() throws IOException{
        //App.setRoot("team");
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
		
		
	}
    
    
}
