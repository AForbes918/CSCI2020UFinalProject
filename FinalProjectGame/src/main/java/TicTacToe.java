import java.io.File;
import java.util.Optional;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class TicTacToe extends Application{	 // tictactoe game
	private double tileSize = 200;
	private boolean playerOneTurn = true;
	private boolean isWin = false;
	private boolean isTie = false;
	Tile tiles[][] = new Tile[3][3];
	Scene gameScene;
	Stage stage1;
	Scoreboard scoreboard;
	Button returnBack = new Button();
	Player player1;
	Player player2;
	
	public void start(Stage stage,Player p1, Player p2) throws Exception { // start method to start game
		// setting up instance variables
		player1=p1;
		player2=p2;
		scoreboard = new Scoreboard(player1,player2,11,player1.name+" turn.",0);
		int time=10;
		stage1=stage;
		Pane root = new Pane();
		root.setPrefSize(1000, 900);
		returnBack.setText("Return to Menu");
		returnBack.setTranslateX(450);
		returnBack.setTranslateY(850);

		returnBack.setOnAction(new EventHandler<ActionEvent>() { //return back to main menu button event handler
			@Override
			public void handle(ActionEvent event) {
				try {
					player1.uploadToFile(new File("../../database/database.csv"),player1,player2);
					StartingUI menu = new StartingUI();
					stage1.close();
					menu.start(stage1);
				}   catch (Exception ex) {

				}
			}
		}   );
		root.getChildren().add(returnBack);
		
		for(int i=0;i<3;i++) { // creating tiles for tictactoe game
			for(int j=0;j<3;j++) {
				tiles[i][j] = new Tile();
				tiles[i][j].setTranslateX(j*tileSize+tileSize);
				tiles[i][j].setTranslateY((i*tileSize)+tileSize);
				
				root.getChildren().add(tiles[i][j]);
			}
		}
		root.getChildren().add(scoreboard);
		gameScene = new Scene(root);
		stage.setScene(gameScene);
		stage.show();
	}
	
	private class Tile extends StackPane {	
		private Text piece = new Text();
		
		public Tile() {
			Rectangle tile = new Rectangle(tileSize,tileSize);
			tile.setFill(Color.WHITE);
			tile.setStroke(Color.BLACK);
			tile.setStrokeWidth(10);
			piece.setFont(Font.font(72));
			setAlignment(Pos.CENTER);
			getChildren().addAll(tile, piece);

			setOnMouseClicked(event -> { // event handler to allow player to place an X or O
				if(event.getButton() == MouseButton.PRIMARY) { // allows player to place piece
					if(playerOneTurn) {
						if(scoreboard.clk.timesUp) {
							try {
								playAgain(stage1,"Xtimesup",tiles,scoreboard.player2);
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						else if(piece.getText().isEmpty()) { // checking if box is empty, if so player can place piece
							scoreboard.clk.resetTimerDown();
							drawX();
							playerOneTurn = !playerOneTurn; // switching turns
							scoreboard.changeTurn(player2.name+" turn.");
							isWin=checkBoard(tiles,"X");
							if(isWin) {
								try {
									playAgain(stage1,"X",tiles,scoreboard.player1);
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
							isTie=checkTie(tiles); // check for tie
							if(isTie) { // if tie show play again alert box
								try {
									playAgain(stage1,"DRAW",tiles,scoreboard.player1);
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
							
						}					
					}
					else{
						if(scoreboard.clk.timesUp) { // determine if times up for 'O'
							try {
								playAgain(stage1,"Otimesup",tiles,scoreboard.player1);
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						else if(piece.getText().isEmpty()) {
							scoreboard.clk.resetTimerDown();
							drawO();
							playerOneTurn = !playerOneTurn;
							scoreboard.changeTurn(player1.name+" turn."); //indicating player turn change
							isWin=checkBoard(tiles,"O");
							if(isWin) {
								try {
									playAgain(stage1,"O",tiles,scoreboard.player2);
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								
							}
							isTie=checkTie(tiles); //check for tie
							if(isTie) {
								try {
									playAgain(stage1,"DRAW",tiles,scoreboard.player2);
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
						}
					}
				}
			});
		}
		
		private void drawX() {
			piece.setText("X");
		} // making X for X player
		
		private void drawO() {
			piece.setText("O");
		} // making O for O player
		public String getValue() {
			return piece.getText();
		}
	}
	
	public boolean checkBoard(Tile t[][],String text) { // checking for win
		for(int i=0;i<3;i++) {
			
			if(t[i][0].getValue().equals(text)&&t[i][1].getValue().equals(text)&&t[i][2].getValue().equals(text)){
				t[i][0].piece.setFill(Color.RED);
				t[i][1].piece.setFill(Color.RED);
				t[i][2].piece.setFill(Color.RED);
				return true;
			}
		}
		
		for(int i=0;i<3;i++) {
			if(t[0][i].getValue().equals(text)&&t[1][i].getValue().equals(text)&&t[2][i].getValue().equals(text)){
				t[0][i].piece.setFill(Color.RED);
				t[1][i].piece.setFill(Color.RED);
				t[2][i].piece.setFill(Color.RED);
				return true;
			}
		}
		if(t[0][0].getValue().equals(text)&&t[1][1].getValue().equals(text)&&t[2][2].getValue().equals(text)){
			t[0][0].piece.setFill(Color.RED);
			t[1][1].piece.setFill(Color.RED);
			t[2][2].piece.setFill(Color.RED);
			return true;
		}
		
		if(t[0][2].getValue().equals(text)&&t[1][1].getValue().equals(text)&&t[2][0].getValue().equals(text)){
			t[0][2].piece.setFill(Color.RED);
			t[1][1].piece.setFill(Color.RED);
			t[2][0].piece.setFill(Color.RED);
			return true;
		}
	
		return false;
	}
	
	public boolean checkTie(Tile t[][]) { // determine if there is a tie
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				if(t[i][j].piece.getText().isEmpty()) {
					return false;
				}
			}
		}
		return true;
	}
	
	public void playAgain(Stage stage,String winner,Tile t[][],Player winningPlayer) throws Exception {
		// stopping clock and alerting users of winner
		scoreboard.clk.animation.stop();
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Tic Tac Toe");
		//if statements to determine header text for alert box
		if(winner.equals("DRAW")) {  //incase draw
			alert.setHeaderText("Draw!");
		}
		else if(winner.equals("Xtimesup")) { // x runs out of time
			winningPlayer.tttPoint();
			scoreboard.updateScoreBoard();
			alert.setHeaderText("X's time is up! O is the winner!");
		}
		else if(winner.equals("Otimesup")) { // o runs out of time
			winningPlayer.tttPoint();
			scoreboard.updateScoreBoard();
			alert.setHeaderText("O's time is up! X is the winner!");
		}
		else {
			winningPlayer.tttPoint();
			scoreboard.updateScoreBoard();
			alert.setHeaderText(winner+" is the winner!");
		}
		
		alert.setContentText("Would you like to play again?"); //asking if players want to play again
		ButtonType buttonYes = new ButtonType("Yes");
		ButtonType buttonQuit = new ButtonType("Quit Game");
		alert.getButtonTypes().setAll(buttonYes, buttonQuit);
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == buttonYes){
		    clearBoard(t);
		} 
		else {
			player1.uploadToFile(new File("../../database/database.csv"),player1,player2);
			StartingUI mainMenu = new StartingUI();
			stop();
			mainMenu.start(stage1);
		}
	}
	public void clearBoard(Tile t[][]) { // clearing board in case users wanna play againg
		scoreboard.clk.resetTimerDown();
		scoreboard.clk.animation.play();
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				t[i][j].piece.setText("");
				t[i][j].piece.setFill(Color.BLACK);
			}
		}
	}

	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	

	
	
	
	
}
