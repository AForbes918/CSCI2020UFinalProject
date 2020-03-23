package startingui;

import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Scoreboard extends StackPane{
		private Text player1Score;
		private Text player2Score;
		private Text player1Name;
		private Text player2Name;
		Player player1;
		Player player2;
		Scoreboard(Player p1, Player p2){
			this.player1=p1;
			this.player2=p2;
			
			player1Score = new Text();
			player2Score = new Text();
			player1Name = new Text();
			player2Name = new Text();
			
			player1Name.setText(player1.name);
			player2Name.setText(player2.name);
			player1Name.setFont(Font.font(50));
			player2Name.setFont(Font.font(50));
			
			player1Score.setText(Integer.toString(player1.tttScore));
			player2Score.setText(Integer.toString(player2.tttScore));
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
			getChildren().addAll(player1Name,player2Name,player1Score,player2Score);
		}
		public void updateScoreBoard() {
			player1Score.setText(Integer.toString(player1.tttScore));
			player2Score.setText(Integer.toString(player2.tttScore));
		}
		
	}