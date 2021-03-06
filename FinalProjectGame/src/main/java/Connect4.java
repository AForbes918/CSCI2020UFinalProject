import java.io.File;
import java.util.Optional;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Connect4 extends Application{ // creating class
	// instance variables
	private double tileSize = 100;
	private boolean playerOneTurn = true;
	private boolean isWin = false;
	private boolean isTie = false;
	Connect4Board board[] = new Connect4Board[7];
	private Button returnBack = new Button();
	Scene gameScene;
	Stage stage1;
	Scoreboard scoreboard;
	Player player1;
	Player player2;
	Clock clk;

	public void start(Stage stage,Player p1, Player p2) throws Exception { // start method called to start game
		player1=p1;
		player2=p2;
		scoreboard = new Scoreboard(player1,player2,60,player1.name+" turn.",1); // creating scoreboard
		scoreboard.changeColour(Color.RED);
		Pane root = new Pane(); // using pan layout for game
		stage1=stage;
		root.setPrefSize(1000, 900);

		for(int i=0;i<7;i++) { // creating board
			board[i] = new Connect4Board(tileSize,i*tileSize+(tileSize*1.5));
			board[i].setTranslateX(i*tileSize+tileSize+50);
			board[i].setTranslateY((tileSize)+100);		
			root.getChildren().add(board[i]); // adding board to pane layout
		}
		returnBack.setText("Return to Menu"); // adding button to bottom to allow user to go back to main menu
		returnBack.setTranslateX(400);
		returnBack.setTranslateY(850);
		returnBack.setOnAction(new EventHandler<ActionEvent>() { //if player want to return back to main menu
			@Override
			public void handle(ActionEvent event) {
				try {
					player1.uploadToFile(new File("../../database/database.csv"),player1,player2);
					StartingUI menu = new StartingUI();
					stage1.close();
					menu.start(stage1);
				}   catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}   );
		root.getChildren().add(returnBack);
		root.getChildren().add(scoreboard);
		gameScene = new Scene(root);
		stage.setScene(gameScene);
		stage.show();
	}
	private class Connect4Board extends StackPane {
		private double x;
		private int values[] = new int[6];
		public int rowSpace;
		public Circle rows[] = new Circle[6];
		public Connect4Board(double tileSize, double x) {
			this.rowSpace=5;
			this.x=x;
			Rectangle columns = new Rectangle(tileSize,tileSize*6); // columns for the connect 4 slots
			columns.setStroke(Color.BLACK);
			columns.setFill(Color.BLUE);
			columns.setStrokeWidth(5);
			getChildren().addAll(columns);

			for(int i=0;i<6;i++) { // adding circle for each column
				rows[i] = new Circle();
				rows[i].setRadius(tileSize/3);
				rows[i].setCenterX(x);
				rows[i].setTranslateY((-tileSize*2.5)+(i*tileSize));
				rows[i].setFill(Color.WHITE);
				getChildren().addAll(rows[i]);
			}
			
			setOnMouseClicked(event -> { // allowing user to place a piece
				if(event.getButton() == MouseButton.PRIMARY) {
					
					if(playerOneTurn) { // checking if its player ones turn
						if(scoreboard.clk.timesUp) {
							try {
								playAgain(stage1,"Redtimesup",board,scoreboard.player2);
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						else if(rowSpace!=-1) { // checking piece can go in desired row
							scoreboard.clk.resetTimerUp();
							drawRed();
							playerOneTurn = !playerOneTurn; // changing player name
							scoreboard.changeTurn(player2.name+" turn.");
							scoreboard.changeColour(Color.YELLOW);
							isWin=checkBoard(board,1);
							if(isWin) {
								try {
									playAgain(stage1,"RED",board,scoreboard.player1);
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								
							}
							isTie=checkTie(board); // check if tie after user places piece
							if(isTie) {
								try {
									playAgain(stage1,"DRAW",board,scoreboard.player1);
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
						}					
					}
					else{
						if(scoreboard.clk.timesUp) {
							try {
								playAgain(stage1,"Yellowtimesup",board,scoreboard.player1);
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						else if(rowSpace!=-1) { // same as above for the yellow piece this time
							scoreboard.clk.resetTimerUp();
							drawYellow();
							playerOneTurn = !playerOneTurn;
							scoreboard.changeColour(Color.RED);
							scoreboard.changeTurn(player1.name+" turn.");
							isWin=checkBoard(board,2);
							if(isWin) {
								try {
									playAgain(stage1,"YELLOW",board,scoreboard.player2);
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								
							}
							isTie=checkTie(board);
							if(isTie) {
								try {
									playAgain(stage1,"DRAW",board,scoreboard.player2);
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
		private void drawRed() { // creating a Red piece to put inside the connet 4 slot
			rows[this.rowSpace].setFill(Color.RED);
			values[this.rowSpace]=1;
			this.rowSpace--;
		}
		
		private void drawYellow() { // creating a yellow piece to put inside the connet 4 slot
			rows[this.rowSpace].setFill(Color.YELLOW);
			values[this.rowSpace]=2;
			this.rowSpace--;
		}
		public boolean checkBoard(Connect4Board b[],int v)  { // method to determine winner
			for(int i=0;i<7;i++) {
				for(int j=0;j<3;j++) {
					if(b[i].values[j]==v&&b[i].values[j+1]==v&&b[i].values[j+2]==v&&b[i].values[j+3]==v){
						return true;
					}
				}
			}
			
			for(int i=0;i<6;i++) {
				for(int j=0;j<3;j++) {
					if(b[j].values[i]==v&&b[j+1].values[i]==v&&b[j+2].values[i]==v&&b[j+3].values[i]==v){
						return true;
					}
				}				
			}
			
			for(int i=0;i<3;i++) {
				for(int j=0;j<3;j++) {
					if(b[i].values[j]==v&&b[i+1].values[j+1]==v&&b[i+2].values[j+2]==v&&b[i+3].values[j+3]==v){
						return true;
					}
				}
			}
			for(int i=6;i>2;i--) {
				for(int j=0;j<4;j++) {
					if(b[i].values[j]==v&&b[i-1].values[j+1]==v&&b[i-2].values[j+2]==v&&b[i-3].values[j+3]==v){
						return true;
					}
				}
			}
			return false;
		}
	}
	public void clearBoard(Connect4Board b[]) { // clearing board , in case user wants to reset
		scoreboard.clk.resetTimerDown(); // reset timer
		scoreboard.clk.animation.play();
		for(int i=0;i<7;i++) { // and turns each circle white which looks transparent to the user
			b[i].rowSpace=5;
			for(int j=0;j<6;j++) {
				b[i].values[j]=0;
				b[i].rows[j].setFill(Color.WHITE);
				
			}
		}
	}
	
	public boolean checkTie(Connect4Board b[]) {// check if theres a tie
		for(int i=0;i<7;i++) {
			for(int j=0;j<6;j++) {
				if(b[i].values[j]==0) {
					return false;
				}
			}
		}
		return true;
	}
	
public void playAgain(Stage stage,String winner,Connect4Board b[],Player winningPlayer) throws Exception { // play again method
		// to handle if player want to play again
		scoreboard.clk.animation.stop();
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Connect 4");
		if(winner.equals("DRAW")) { // if statements to determine the header for header text
			alert.setHeaderText("Draw!");
		}
		else if(winner.equals("Redtimesup")) {
			alert.setHeaderText("Red's time is up! Yellow is the winner!");
		}
		else if(winner.equals("Yellowtimesup")) {
			alert.setHeaderText("Yellow's time is up! Red is the winner!");
		}
		else { // otherwise determine winner
			winningPlayer.cfourPoint();
			scoreboard.updateScoreBoard();
			alert.setHeaderText(winner+" is the winner!");
		}
		
		alert.setContentText("Would you like to play again?"); // alert box

		ButtonType buttonYes = new ButtonType("Yes");
		ButtonType buttonQuit = new ButtonType("Quit Game");
		alert.getButtonTypes().setAll(buttonYes, buttonQuit);
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == buttonYes){ // checking if player wants to play again
		    clearBoard(b);
		} 
		else { // if player does not want to play again
			player1.uploadToFile(new File("../../database/database.csv"),player1,player2);
			StartingUI mainMenu = new StartingUI();
			stop();
			mainMenu.start(stage1);
		}
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}

}

