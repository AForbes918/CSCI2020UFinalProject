package sample;

import javafx.geometry.Pos;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Tile extends StackPane {	
		Text piece = new Text();
		Player player1;
		Player player2;
		public Tile(double size) {
			Rectangle tile = new Rectangle(size,size);
			tile.setFill(Color.WHITE);
			tile.setStroke(Color.BLACK);
			tile.setStrokeWidth(10);
			piece.setFont(Font.font(72));
			setAlignment(Pos.CENTER);
			getChildren().addAll(tile, piece);
			
			setOnMouseClicked(event -> {
				if(player1.isTurn) {
					drawX();
				}
				else {
					drawO();
				}
			});			
		}
		private void drawX() {
			piece.setText("X");
		}
		
		private void drawO() {
			piece.setText("O");
		}
	
		public String getValue() {
			return piece.getText();
		}
}
