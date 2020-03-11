package startingui;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;


public class Connect4 extends Application {
	final double CellSize = 30;
	final int Columns = 7;
	final int Rows = 7;
	int moves = 0;
	boolean player1 = true;
	
	List<Button> buttons = new ArrayList<>();
	
	@Override
	public void start(Stage primaryStage) {
		
		try {
			VBox rootPane = new VBox();
			HBox buttonPane = new HBox(Columns);
			GridPane board = new GridPane();
			initBoard(board);
			for(Button button : buttons) buttonPane.getChildren().add(button);
			while(moves < 50) {
				pushButton(board);
				for(int i = 0; i < Rows; i++) {
					for(int j = 0; j < Columns; j++) {
						if(checkWin(i, j, player1, board)) {
							System.out.println("Player " + player1 + " wins!");
							break;
						} 
					}
				}
			}
			rootPane.getChildren().addAll(board, buttonPane);
			System.out.println();
			Scene scene = new Scene(rootPane,425,500);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public void pushButton(GridPane board) {
		for(Button button : buttons) {
			button.setOnAction(event->{
				int i = buttons.indexOf(button);
				dropPiece(i, player1, board);
				player1 = !player1;
			});
		}
	}
	
	public void dropPiece(int column, boolean player1, GridPane board) {
		int row = Rows;
		Paint color;
		if(player1) color = Color.RED;
		else color = Color.BLUE;
		while(((Circle)getNodeByRowColumnIndex(row, column, board)).getFill() != Color.TRANSPARENT) {
			if(row == 1) return;
			row--;
		}
		((Circle)getNodeByRowColumnIndex(row, column, board)).setFill(color);
	}
	
	public void initBoard(GridPane board) {
		for(int i = 0; i < Columns; i++) {
			buttons.add(new Button("Drop"));
			for(int j = 0; j < Rows; j++) {
				Circle cell = new Circle(CellSize);
				cell.setStroke(Color.TRANSPARENT);
				cell.setFill(Color.TRANSPARENT);
				board.add(cell, i, j + 1);
			}
		}
		board.setGridLinesVisible(true);
	}
	
	public boolean checkWin(int row, int col, boolean player1, GridPane board) {
		int count;
		Paint color;
		if(player1) color = Color.RED;
		else color = Color.BLUE;
	 	if(moves > 6) {
	 		for(int i = 0; i < 3; i++) {
	 			count = 1;
	 			for(int j = 0; j <= 4; j++) {
	 				switch(i) {
	 				case 0: //Check for 4 pieces horizontally
	 					if(
 							((Circle)getNodeByRowColumnIndex(row, col + j, board)).getFill() == color ||
 							((Circle)getNodeByRowColumnIndex(row, col - j, board)).getFill() == color
	 					) {
	 						count++;
	 					}
	 					break;
	 				case 1: //check for 4 pieces vertically
	 					if(
			            	((Circle)getNodeByRowColumnIndex(row + j, col, board)).getFill() == color ||
			            	((Circle)getNodeByRowColumnIndex(row - j, col, board)).getFill() == color
			            )
	 					{
	 						count++;
	 					}
	 					break;
	 				case 2: //check for 4 pieces diagonally
	 					if(
	 						((Circle)getNodeByRowColumnIndex(row + j, col + j, board)).getFill() == color ||
	 						((Circle)getNodeByRowColumnIndex(row - j, col + j, board)).getFill() == color ||
	 						((Circle)getNodeByRowColumnIndex(row + j, col - j, board)).getFill() == color ||
	 						((Circle)getNodeByRowColumnIndex(row - j, col - j, board)).getFill() == color
		 				) {
	 						count++;
	 					}
	 					break;
	 				}
	 			}
	 			if(count > 4) {
	 				return true;
	 			}
	 		}
	 	}
	 	return false;
	}
	
	public Node getNodeByRowColumnIndex (final int row, final int column, GridPane gridPane) {
	    Node result = null;
	    ObservableList<Node> childrens = gridPane.getChildren();

	    for (Node node : childrens) {
	        if(GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == column) {
	            result = node;
	            break;
	        }
	    }
	    return result;
	}
}
