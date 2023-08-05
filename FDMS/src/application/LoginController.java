package application;

import java.io.IOException;
import java.net.URL;

import java.util.ResourceBundle;



import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Window;

public class LoginController implements Initializable{

	
	@FXML
	private TextField Uid;
	
	@FXML
    private PasswordField pass;
	
	@FXML 
	private Button log;
	
	 public void login(ActionEvent event) throws IOException {
		 
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
	      
		// switchToDash();
		 System.out.println("working");
	 }
	 
	 private static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
	        Alert alert = new Alert(alertType);
	        alert.setTitle(title);
	        alert.setHeaderText(null);
	        alert.setContentText(message);
	        alert.initOwner(owner);
	        alert.show();
	    }
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
	@FXML
   private void switchToDash() throws IOException{
        Main.setRoot("Dashboard");

    }
	
	
}