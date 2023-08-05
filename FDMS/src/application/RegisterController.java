package application;


import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
//import java.sql.SQLException;
import java.util.ResourceBundle;

import application.Jdbc;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Window;

public class RegisterController {
	
	 @FXML
	    private TextField Age;

	    @FXML
	    private TextField Email;

	    @FXML
	    private TextField Fname;

	    @FXML
	    private PasswordField Pass;

	    @FXML
	    private TextField Pnum;

	    @FXML
	    private Button Sub;

	    @FXML
	    public void register(ActionEvent event) throws SQLException, IOException {

	        Window owner = Sub.getScene().getWindow();

	        System.out.println(Email.getText());
	        System.out.println(Pass.getText());
	        System.out.println(Fname.getText());
	        System.out.println(Age.getText());
	        System.out.println(Pnum.getText());
	       
	        if (Email.getText().isEmpty()) {
	            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
	                "Please enter your Email");
	            return;
	        }
	        
	        if (Fname.getText().isEmpty()) {
	            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
	                "Please enter your Name");
	            return;
	        }

	        if (Pass.getText().isEmpty()) {
	            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
	                "Please enter Passowrd");
	            return;
	        }
	       
	        if (Age.getText().isEmpty()) {
	            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
	                "Please enter an Age");
	            return;
	        }
	        if (Pnum.getText().isEmpty()) {
	            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
	                "Please enter Phone Number");
	            return;
	        }

	        String fullName = Fname.getText();
	        String emailId = Email.getText();
	        String password = Pass.getText();
	        String age = Age.getText();
	        String num = Pnum.getText();

	        if(emailId.endsWith("@northeastern.edu"))
	        {
	              Jdbc jdbc = new Jdbc();
	              jdbc.insertRecord(emailId,fullName, password, age,num);
	        
	            showAlert(Alert.AlertType.CONFIRMATION, owner, "Registration Successful!",
	            "Welcome " + Fname.getText());
	       
	            switchToHome();
	        }
	        else
	        {
	            showAlert(Alert.AlertType.ERROR, owner, "Registration Unuccessful!",
	            "Email must end with @northeastern.edu ");
	            
	        }
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
	
	
	
}