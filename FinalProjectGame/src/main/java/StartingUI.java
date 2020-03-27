import java.io.File;
import java.util.Optional;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 *
 * @author Andre
 */

public class StartingUI extends Application {
	boolean login=false;
    @Override
    public void start(Stage primaryStage) throws Exception{
    	Player player1 = new Player();
    	Player player2 = new Player();
        int menuX = 600;
        int menuY = 450;
        Label title = new Label("Main Menu");
        Label player1label = new Label("Player 1: ");
        Label player2label = new Label("Player 2: ");
        Button confirmNames = new Button();
        TextField play1Name = new TextField();
        TextField play2Name = new TextField();
        File database = new File("../../database/database.csv");
        Button ticTac = new Button();
        Button checkers = new Button();
        Button connectFour = new Button();
        Button mineSweeper = new Button();
        

        /**
         * Player Names
         */
        
        player1label.setTranslateY(150);
        player1label.setTranslateX(-menuX/2.5);
        player1label.setFont(Font.font("Arial",18));
        player1label.setTextFill(Color.WHITE);
        
        play1Name.setTranslateY(150);
        play1Name.setTranslateX(-menuX/3.75);
        play1Name.setMaxWidth(80);
        
        
        player2label.setTranslateY(150);
        player2label.setTranslateX(menuX/4);
        player2label.setFont(Font.font("Arial",18));
        player2label.setTextFill(Color.WHITE);
        
        play2Name.setTranslateY(150);
        play2Name.setTranslateX(menuX/2.5);
        play2Name.setMaxWidth(80);
        
        confirmNames.setText("Create Names");
        confirmNames.setTranslateY(200);
        
                
        /**
         * ticTacToe commands
         */
        ticTac.setText("Tic Tac Toe");
        ticTac.setFont(Font.font ("Arial",16));
        
        ticTac.setOnAction(new EventHandler<ActionEvent>() {// event handler to allow to play tictactoe if they wanna play tictactoe
            @Override
            public void handle(ActionEvent event) {
            	if(login) { //if login successful
            		TicTacToe game = new TicTacToe(); // create tictactoe game
            		try {
            			game.start(primaryStage,player1,player2);
            		} 
            		catch (Exception e) { 
            			// TODO Auto-generated catch block
            			e.printStackTrace();
            		}
            	}
            	else {
					Alert alert = new Alert(AlertType.CONFIRMATION); // else if login unsuccessful
					alert.setTitle("TicTacToe");
					alert.setHeaderText("ERROR!");
					alert.setContentText("You cannot play without signing in!");
					ButtonType confirm = new ButtonType("Okay");
					alert.getButtonTypes().setAll(confirm);
					Optional<ButtonType> result = alert.showAndWait();
				}
            }
        });
        
        ticTac.setTranslateX(-(menuX/2.5));
        ticTac.setTranslateY(100);
        /**
          * Connect Four commands
         */
        connectFour.setText("Connect Four");
        connectFour.setFont(Font.font ("Arial",16));
        
        connectFour.setOnAction(new EventHandler<ActionEvent>() {// event handler to allow to play connect4 if they wanna play Connect4
             @Override
            public void handle(ActionEvent event) {
            	if(login) { //if login successful
            		Connect4 game = new Connect4(); // create connect4 game
            		try {
            			game.start(primaryStage,player1,player2);
            		} 
            		catch (Exception e) { 
					// TODO Auto-generated catch block
            			e.printStackTrace();
            		}
            	}
            	else {
					Alert alert = new Alert(AlertType.CONFIRMATION);// else if login unsuccessful
					alert.setTitle("Connect 4");
					alert.setHeaderText("ERROR!");
					alert.setContentText("You cannot play without signing in!");
					ButtonType confirm = new ButtonType("Okay");
					alert.getButtonTypes().setAll(confirm);
					Optional<ButtonType> result = alert.showAndWait();
				}
            }
        });
        
        connectFour.setTranslateX(-(menuX/7.5));
        connectFour.setTranslateY(100);
        
        /**
         * Battle Ship commands
         */
        mineSweeper.setText("Minesweeper");
        mineSweeper.setFont(Font.font ("Arial",16));
        
        mineSweeper.setOnAction(new EventHandler<ActionEvent>() { // event handler to allow to play minesweeper if they wanna play minesweeper
            @Override
            public void handle(ActionEvent event) {
            	if(login) { // if they login
            		Alert alert = new Alert(AlertType.CONFIRMATION); // inform them that its a one player game using alert box
					alert.setTitle("MineSweeper");
					alert.setHeaderText("One Player Only!");
					alert.setContentText("This game can only be played by one player! Are you sure you would like to continue?");
            		player1.setName(player1.name+" 1");
            		ButtonType confirm = new ButtonType("Continue");
            		ButtonType goBack = new ButtonType("Return to Menu");
					alert.getButtonTypes().setAll(confirm,goBack);
					Optional<ButtonType> result = alert.showAndWait();
					if(result.get()==confirm) {
						MineSweeper game = new MineSweeper(); // after confirmation of alert box create game
	            		try {
							game.start(primaryStage,player1);
						} 
	            		catch (Exception e) { 
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					else {
					
					}
            	}
            	else {
					Alert alert = new Alert(AlertType.CONFIRMATION);// else if login unsuccessful
					alert.setTitle("MineSeeper");
					alert.setHeaderText("ERROR!");
					alert.setContentText("You cannot play without signing in!");
					ButtonType confirm = new ButtonType("Okay");
					alert.getButtonTypes().setAll(confirm);
					Optional<ButtonType> result = alert.showAndWait();
				}
            	
            }

        });
        
        mineSweeper.setTranslateX(menuX/7.5);
        mineSweeper.setTranslateY(100);
        
        /**
         * Checkers Commands
         */
        checkers.setText("  Checkers  ");
        checkers.setFont(Font.font ("Arial",16));
        
        checkers.setOnAction(new EventHandler<ActionEvent>() { // event handler to allow to play checkers if they wanna play checkers
            @Override
            public void handle(ActionEvent event) {
            	if(login) { // if they logged in
            		Checkers game = new Checkers(); // create the checkers game
            		try {
            			game.start(primaryStage,player1,player2);
            		} 
            		catch (Exception e) {
					// TODO Auto-generated catch block
            			e.printStackTrace();
            		}
            	}
            	else {
					Alert alert = new Alert(AlertType.CONFIRMATION);// else if login unsuccessful
					alert.setTitle("Checkers");
					alert.setHeaderText("ERROR!");
					alert.setContentText("You cannot play without signing in!");
					ButtonType confirm = new ButtonType("Okay");
					alert.getButtonTypes().setAll(confirm);
					Optional<ButtonType> result = alert.showAndWait();
				}
            }
        });
        
        checkers.setTranslateX(menuX/2.5);
        checkers.setTranslateY(100);
        
        title.setFont(new Font("Arial", 30));
        title.setTextFill(Color.WHITE);
        
        confirmNames.setOnAction(new EventHandler<ActionEvent>() { // method to determine whether player has had games before
			@Override
			public void handle(ActionEvent event) {
				
				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("Confirm Names");
					
				String checklist="";
				if(play1Name.getText().equals(play2Name.getText())) { //checking if player names are the same
	            	checklist="Profiles cannot be the same!";
	            	alert.setHeaderText("ERROR!");
	            }
				else if(play1Name.getText().equals("")||play2Name.getText().equals("")) { //checking if not name was inputted
					checklist="You must login as a user!";
					alert.setHeaderText("ERROR!");
				}
				else { // load players history if they have played games before
					alert.setHeaderText("Player Loader");
					if(!player1.UploadFromFile(database, play1Name.getText())) {
						player1.setName(play1Name.getText());
						checklist+=play1Name.getText()+" not found! New player file created.\n";
					}
					else {
						checklist+=play1Name.getText()+" was loaded.\n";
					}
					if(!player2.UploadFromFile(database, play2Name.getText())){ // getting history of players games
						player2.setName(play2Name.getText());
						checklist+=play2Name.getText() + " not found! New player file created.";
					}
					else {
						checklist+=play2Name.getText()+" was loaded.";
					}
					login=true; // if login successful
					alert.setContentText(checklist);
					ButtonType confirm = new ButtonType("Okay");
					alert.getButtonTypes().setAll(confirm);
					Optional<ButtonType> result = alert.showAndWait();
				}
			}
        });  
        
        StackPane root = new StackPane();
        StackPane.setAlignment(title, Pos.TOP_CENTER);
        root.setBackground(new Background(new BackgroundFill(Color.rgb(64,64,64), CornerRadii.EMPTY, Insets.EMPTY)));
        
        
        root.getChildren().add(title); // adding childern to stack pane
        root.getChildren().add(ticTac);
        root.getChildren().add(checkers);
        root.getChildren().add(connectFour);
        root.getChildren().add(mineSweeper);
        root.getChildren().add(player1label);
        root.getChildren().add(player2label);
        root.getChildren().add(play1Name);
        root.getChildren().add(play2Name);
        root.getChildren().add(confirmNames);
                
        
        
        Scene scene = new Scene(root, menuX, menuY);
        
        primaryStage.setTitle("Final Project UI");
        primaryStage.setScene(scene);
        primaryStage.show(); // showing window
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
