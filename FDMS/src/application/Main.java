package application;
	
import java.io.IOException;



import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.*;


public class Main extends Application {
	
	private static Scene scene;
	@Override
	public void start(Stage primaryStage) throws IOException {
		
		    System.out.println(getClass());
		    scene = new Scene(loadFXML("Home"), 1100, 700);
		    Parent root = FXMLLoader.load(getClass().getResource("Home.FXML"));
			//scene = new Scene(root,1100,700);
			
		    scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		
	}
	
	static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }
	
	private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }
	
	public static void main(String[] args) {
		launch(args);
	}
}
