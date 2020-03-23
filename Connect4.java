package startingui;

import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Connect4 extends Application{
	private double tileSize = 100;
	private boolean playerOneTurn = true;
	private boolean isWin = false;
	private boolean isTie = false;
	Connect4Board board[] = new Connect4Board[7];
        private Button returnBack = new Button();
	Scene gameScene;
	Stage stage1;
	Scoreboard scoreboard;
	Player player1=new Player();
	Player player2=new Player();
	public void start(Stage stage,Player player1, Player player2) throws Exception {
		scoreboard = new Scoreboard(player1,player2);
		Pane root = new Pane();
		stage1=stage;
		root.setPrefSize(1000, 900);
		for(int i=0;i<7;i++) {
			board[i] = new Connect4Board(tileSize,i*tileSize+(tileSize*1.5));
			board[i].setTranslateX(i*tileSize+tileSize+50);
			board[i].setTranslateY((tileSize)+100);		
			root.getChildren().add(board[i]);
		}
                    returnBack.setText("Return to Menu");
                    returnBack.setTranslateX(400);
                    returnBack.setTranslateY(850);
                    returnBack.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        try {
                            StartingUI menu = new StartingUI();
                            stage1.close();
                            menu.start(stage1);
                    }   catch (Exception ex) {
                            Logger.getLogger(MineSweeper.class.getName()).log(Level.SEVERE, null, ex);
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
			Rectangle columns = new Rectangle(tileSize,tileSize*6);
			columns.setStroke(Color.BLACK);
			columns.setFill(Color.BLUE);
			columns.setStrokeWidth(5);
			getChildren().addAll(columns);
			for(int i=0;i<6;i++) {
				rows[i] = new Circle();
				rows[i].setRadius(tileSize/3);
				rows[i].setCenterX(x);
				rows[i].setTranslateY((-tileSize*2.5)+(i*tileSize));
				rows[i].setFill(Color.WHITE);
				getChildren().addAll(rows[i]);
			}
			
			setOnMouseClicked(event -> {
				if(event.getButton() == MouseButton.PRIMARY) {
					if(playerOneTurn) {
						if(rowSpace!=-1) {
							drawRed();
							playerOneTurn = !playerOneTurn;
							isWin=checkBoard(board,1);
							if(isWin) {
								try {
									playAgain(stage1,"RED",board,scoreboard.player1);
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
					else{
						if(rowSpace!=-1) {
							drawYellow();
							playerOneTurn = !playerOneTurn;
							isWin=checkBoard(board,2);
							if(isWin) {
								try {
									playAgain(stage1,"YELLOW",board,scoreboard.player1);
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
		private void drawRed() {
			rows[this.rowSpace].setFill(Color.RED);
			values[this.rowSpace]=1;
			this.rowSpace--;
		}
		
		private void drawYellow() {
			rows[this.rowSpace].setFill(Color.YELLOW);
			values[this.rowSpace]=2;
			this.rowSpace--;
		}
		public boolean checkBoard(Connect4Board b[],int v) {
			for(int i=0;i<7;i++) {
				for(int j=0;j<3;j++) {
					if(b[i].values[j]==v&&b[i].values[j+1]==v&&b[i].values[j+2]==v&&b[i].values[j+3]==v){
						return true;
					}
				}
			}
			
			for(int i=0;i<6;i++) {
				for(int j=0;j<2;j++) {
					if(b[j].values[i]==v&&b[j+1].values[i]==v&&b[j+2].values[i]==v&&b[j+3].values[i]==v){
						return true;
					}
				}				
			}
			
			for(int i=0;i<3;i++) {
				for(int j=0;j<2;j++) {
					if(b[i].values[j]==v&&b[i+1].values[j+1]==v&&b[i+2].values[j+2]==v&&b[i+3].values[j+3]==v){
						return true;
					}
				}
			}
			for(int i=6;i>3;i--) {
				for(int j=0;j<2;j++) {
					if(b[i].values[j]==v&&b[i-1].values[j+1]==v&&b[i-2].values[j+2]==v&&b[i-3].values[j+3]==v){
						return true;
					}
				}
			}
			return false;
		}
	}
	public void clearBoard(Connect4Board b[]) {
		for(int i=0;i<7;i++) {
			b[i].rowSpace=5;
			for(int j=0;j<6;j++) {
				b[i].values[j]=0;
				b[i].rows[j].setFill(Color.WHITE);
				
			}
		}
	}
	
	public boolean checkTie(Connect4Board b[]) {
		for(int i=0;i<7;i++) {
			for(int j=0;j<6;j++) {
				if(b[i].values[j]==0) {
					return false;
				}
			}
		}
		return true;
	}
	
public void playAgain(Stage stage,String winner,Connect4Board b[],Player winningPlayer) throws Exception {
		
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Connect 4");
		if(winner.equals("DRAW")) {
			alert.setHeaderText("Draw!");
		}
		else {
			winningPlayer.tttPoint();
			scoreboard.updateScoreBoard();
			alert.setHeaderText(winner+" is the winner!");
		}
		
		alert.setContentText("Would you like to play again?");
		ButtonType buttonYes = new ButtonType("Yes");
		ButtonType buttonQuit = new ButtonType("Quit Game");
		alert.getButtonTypes().setAll(buttonYes, buttonQuit);
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == buttonYes){
		    clearBoard(b);
		} 
		else {
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
