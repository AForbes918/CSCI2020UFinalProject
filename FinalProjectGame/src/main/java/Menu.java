import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;

public class Menu extends StackPane{ //menu
	Button exitGame;
	String gameTitle;
	Stage stage1;

	Menu(String g, Stage stage){
		this.stage1=stage; //setting stage
		this.gameTitle=g;
		this.exitGame=new Button();
		this.exitGame.setText("Back to Main Menu");
		exitGame.setTranslateX(700);
		exitGame.setTranslateY(20);
		exitGame.setOnAction(new EventHandler<ActionEvent>() { // creating an alert box to allow user to go bak to main menu
			@Override
			public void handle(ActionEvent event) {
				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle(gameTitle);
				alert.setHeaderText("Quit Game");
				
				alert.setContentText("Are you sure you would like to quit the game and head back to the main menu?");
				ButtonType buttonYes = new ButtonType("Yes"); // yes and no buttons for confirmation
				ButtonType buttonQuit = new ButtonType("No");
				alert.getButtonTypes().setAll(buttonYes, buttonQuit);
				Optional<ButtonType> result = alert.showAndWait();
				if (result.get() == buttonYes){ // checking which button is click and handling accordingly
					StartingUI mainMenu = new StartingUI(); //if yes start the main menu stage
					try {
						mainMenu.start(stage1);
					} catch (Exception e) {
						e.printStackTrace();
					}
				} 
				else {
					
				}
			}
			
		});
		getChildren().add(exitGame);// adding in button to layout
	}
}
