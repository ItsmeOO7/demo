package application;

import java.io.IOException;



import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.*;

public class loginData {
	
	private static final loginData instance = new loginData();
    public String Id;
    
    public static loginData getinstance () {
		return instance;
    }
    
    public loginData() {
    }

   
    public String getId() {
		return Id;
	}

	public void setId(String id) {
		this.Id = id;
	}

	
	
    
}
