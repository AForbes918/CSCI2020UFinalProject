package startingui;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public class TicTacToeBoard extends StackPane{
	Tile[][] tiles = new Tile[3][3];
	boolean isWin;
	boolean isTie;
	boolean playerOneTurn;
	Player player1;
	Player player2;
	TicTacToeBoard(double size,Player p1, Player p2){
		this.isWin=false;
		this.isTie=false;
		this.playerOneTurn=true;
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				this.tiles[i][j] = new Tile(size);
				this.tiles[i][j].setTranslateX(j*size+size);
				this.tiles[i][j].setTranslateY((i*size)+size);
				getChildren().add(tiles[i][j]);
			}
		}
	}
	
	public boolean checkTie() {
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				if(tiles[i][j].piece.getText().isEmpty()) {
					return false;
				}
			}
		}
		return true;
	}
	public void clearBoard() {
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				tiles[i][j].piece.setText("");
				tiles[i][j].piece.setFill(Color.BLACK);
			}
		}
	}
	public boolean checkBoard(Tile t[][],String text) {
		for(int i=0;i<3;i++) {
			
			if(tiles[i][0].getValue().equals(text)&&tiles[i][1].getValue().equals(text)&&tiles[i][2].getValue().equals(text)){
				tiles[i][0].piece.setFill(Color.RED);
				tiles[i][1].piece.setFill(Color.RED);
				tiles[i][2].piece.setFill(Color.RED);
				return true;
			}
		}
		
		for(int i=0;i<3;i++) {
			if(tiles[0][i].getValue().equals(text)&&tiles[1][i].getValue().equals(text)&&tiles[2][i].getValue().equals(text)){
				tiles[0][i].piece.setFill(Color.RED);
				tiles[1][i].piece.setFill(Color.RED);
				tiles[2][i].piece.setFill(Color.RED);
				return true;
			}
		}
		if(tiles[0][0].getValue().equals(text)&&tiles[1][1].getValue().equals(text)&&tiles[2][2].getValue().equals(text)){
			tiles[0][0].piece.setFill(Color.RED);
			tiles[1][1].piece.setFill(Color.RED);
			tiles[2][2].piece.setFill(Color.RED);
			return true;
		}
		
		if(tiles[0][2].getValue().equals(text)&&tiles[1][1].getValue().equals(text)&&tiles[2][0].getValue().equals(text)){
			tiles[0][2].piece.setFill(Color.RED);
			tiles[1][1].piece.setFill(Color.RED);
			tiles[2][0].piece.setFill(Color.RED);
			return true;
		}
	
		return false;
	}
}