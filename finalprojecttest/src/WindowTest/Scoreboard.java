package WindowTest;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Scoreboard extends Pane{
		private Text player1Score;
		private Text player2Score;
		private Text player1Name;
		private Text player2Name;
		private Text turn;
		private Rectangle border;
		Player player1;
		Player player2;
		int score;
		Clock clk;
		Scoreboard(Player p1, Player p2,int maxTime,String firstTurn,int s){
			this.player1=p1;
			this.player2=p2;
			this.score=s;
			player1Score = new Text();
			player2Score = new Text();
			player1Name = new Text();
			player2Name = new Text();
			turn = new Text();
			border = new Rectangle();
			
			player1Name.setText(player1.name);
			player2Name.setText(player2.name);
			player1Name.setFont(Font.font(50));
			player2Name.setFont(Font.font(50));
			
			turn.setText(firstTurn);
			turn.setFont(Font.font(25));
			
			player1Score.setText(Integer.toString(player1.scores[this.score]));
			player2Score.setText(Integer.toString(player2.scores[this.score]));
			player1Score.setFont(Font.font(50));
			player2Score.setFont(Font.font(50));
			
			player1Name.setTranslateX(50);
			player1Name.setTranslateY(50);
			player1Score.setTranslateX(50);
			player1Score.setTranslateY(100);
			
			player2Name.setTranslateX(800);
			player2Name.setTranslateY(50);
			player2Score.setTranslateX(800);
			player2Score.setTranslateY(100);
			
			border.setX(0);
			border.setY(0);
			border.setWidth(1025);
			border.setHeight(175);
			border.setFill(Color.LIGHTGREY);
			
			turn.setTranslateX(425);
			turn.setTranslateY(50);
			
			clk = new Clock(maxTime);
			clk.transX(500);
			getChildren().addAll(border,player1Name,player2Name,clk,player1Score,player2Score,turn);
		}
		Scoreboard(Player p1, Player p2,int s){
			this.player1=p1;
			this.player2=p2;
			this.score=s;
			player1Score = new Text();
			player2Score = new Text();
			player1Name = new Text();
			player2Name = new Text();
			
			player1Name.setText(player1.name);
			player2Name.setText(player2.name);
			player1Name.setFont(Font.font(50));
			player2Name.setFont(Font.font(50));
			
			player1Score.setText(Integer.toString(player1.scores[this.score]));
			player2Score.setText(Integer.toString(player2.scores[this.score]));
			player1Score.setFont(Font.font(50));
			player2Score.setFont(Font.font(50));
			
			player1Name.setTranslateX(50);
			player1Name.setTranslateY(50);
			player1Score.setTranslateX(50);
			player1Score.setTranslateY(100);
			
			player2Name.setTranslateX(800);
			player2Name.setTranslateY(50);
			player2Score.setTranslateX(800);
			player2Score.setTranslateY(100);
			clk = new Clock();
			clk.transX(500);
			getChildren().addAll(player1Name,player2Name,clk,player1Score,player2Score);
		}
		public void updateScoreBoard() {
			player1Score.setText(Integer.toString(player1.scores[this.score]));
			player2Score.setText(Integer.toString(player2.scores[this.score]));
		}
		
		public void changeTurn(String newTurn) {
			turn.setText(newTurn);
		}
		public void changeColour(Color newColor) {
			turn.setFill(newColor);
		}
	}
