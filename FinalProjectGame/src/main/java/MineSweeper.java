import java.io.File;

/*

@author Adam Green, 100653971
*/

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class MineSweeper extends Application {

  private static final int TILE_SIZE = 40;//tile size
  //dimensions
  private static final int WIDTH = 800;
  private static final int HEIGHT = 600;
  //x and y tiles
  private static final int X_SIZE = WIDTH / TILE_SIZE;
  private static final int Y_SIZE = HEIGHT / TILE_SIZE;
  //grid of tiles
  private Tile[][] grid = new Tile[X_SIZE][Y_SIZE];
  //tiles info
  private int minesPlaced = 0;
  private int tilesClicked = 0;
  private Button returnBack = new Button();
  private Button reset = new Button();

  private Scene scene;
  private Stage window;
  Player player1;
  Scoreboard scoreboard;
  private Parent createBoard() {
	BorderPane full = new BorderPane();
    Pane root = new Pane();
    HBox buttons = new HBox();
    scoreboard.clk.resetTimerUp();
    reset.setText("Reset Board");
    reset.setOnAction(new EventHandler<ActionEvent>(){
    	public void handle(ActionEvent event) {
    		scene.setRoot(createBoard());
    	}
    });
    returnBack.setText("Return to Menu");
    returnBack.setOnAction(new EventHandler<ActionEvent>() {
      public void handle(ActionEvent event) {
        try {
          player1.uploadToFile(new File("../../database/database.csv"),player1,null);
          StartingUI menu = new StartingUI();
          window.close();
          menu.start(window);
        }   
        catch (Exception ex) {
          ex.printStackTrace();
        }
      }
    });

    root.setPrefSize(WIDTH, HEIGHT);
    minesPlaced = 0;
    tilesClicked = 0;
    buttons.setTranslateX(600);
    buttons.setTranslateY(25);
    buttons.getChildren().addAll(returnBack,reset);

    //create Board tiles
    for (int y = 0; y < Y_SIZE; y++) {
      for (int x = 0; x < X_SIZE; x++) {
        Tile tile = new Tile(x, y, Math.random() < 0.2);
        grid[x][y] = tile;
        root.getChildren().add(tile);
      }
    }
    //check each tile for bomb
    for (int y = 0;y < Y_SIZE ; y++) {
      for (int x = 0;x < X_SIZE ; x++) {
        Tile tile = grid[x][y];

        if (tile.hasBomb) {
          minesPlaced += 1;
          continue;
        }
        //check neigbhoring bombs
        long bombs = getNeighbors(tile).stream().filter(t -> t.hasBomb).count();
        if (bombs > 0) {
          tile.bombs = String.valueOf(bombs);
        } else {
          tile.bombs = "";
        }

      }
    }
    scoreboard.getChildren().add(buttons);
    full.setTop(scoreboard);
    full.setCenter(root);
    return full;
  }

  private ArrayList<Tile> getNeighbors(Tile tile) {
    ArrayList<Tile> neighbors = new ArrayList<>();//list of adjacent tiles

    int[] points = new int[] {//neigbhoring tiles
      -1, -1,
      -1, 0,
      -1, 1,
      0, -1,
      0, 1,
      1, -1,
      1, 0,
      1, 1
    };

    for (int i = 0; i < points.length; i++) {
      int nextX = points[i];
      int nextY = points[++i];

      int newX = tile.x + nextX;
      int newY = tile.y + nextY;

      //ignore non existant off edge pieces
      if (newX >= 0 && newX < X_SIZE && newY >= 0 && newY < Y_SIZE) {
        neighbors.add(grid[newX][newY]);
      }
    }

    return neighbors;
  }

    @Override
    public void start(Stage primaryStage) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

  private class Tile extends StackPane { //tiles class
    private int x;
    private int y;
    private boolean hasBomb;
    private boolean isOpen = false;
    private boolean isFlagged = false;
    private String bombs;

    private Rectangle border = new Rectangle(TILE_SIZE - 2, TILE_SIZE - 2);
    private Text text = new Text();

    public Tile(int x, int y, boolean hasBomb) {
      this.x = x;
      this.y = y;
      this.hasBomb = hasBomb;

      border.setFill(Color.GAINSBORO);
      border.setStroke(Color.BLACK);

      text.setFont(Font.font(20));
      text.setText(hasBomb ? "X" : "");
      text.setVisible(false);

      getChildren().addAll(border, text);

      setTranslateX(x * TILE_SIZE);
      setTranslateY(y * TILE_SIZE);
      //tile click
      setOnMouseClicked(e -> {
        open(e);
      });

    }

    public void open(MouseEvent e) {//clicking a tile
      if (isOpen) {
        return;
      }
		  if (e.getButton() == MouseButton.PRIMARY) {//left click
        if (!isFlagged) {
          if (hasBomb) {
            System.out.println("Game ended: Player clicked a mine.");
              try {
                  endGame(false);
              } catch (Exception ex) {

              }
            return;
          }
          tilesClicked += 1;
          isOpen = true;
          text.setText(bombs);
          text.setVisible(true);
          border.setFill(null);
          if (checkWin() == true) {
            System.out.println("Player has won the game!");
              try {
                  endGame(true);
              } catch (Exception ex) {

              }
            return;
          }

          if (bombs.isEmpty()) { //recursive call
            getNeighbors(this).forEach(Tile::open);
          }
        }

      } else {//right click

        if (!isFlagged) {
          text.setText("F");
          text.setStroke(Color.RED);
          text.setFill(Color.RED);
          text.setVisible(true);
          isFlagged = true;
        } else {
          text.setText("");
          text.setStroke(null);
          text.setFill(Color.BLACK);
          text.setVisible(false);
          isFlagged = false;
        }
      }
    }

    public void open() {//auto clicking tiles
      if (isOpen) {
        return;
      }

      if (hasBomb) {
        System.out.println("Game ended: Player clicked a mine.");
          try {
              endGame(false);
          } catch (Exception ex) {

          }
        return;
      }
      tilesClicked += 1;
      isOpen = true;
      text.setText(bombs);
      text.setVisible(true);
      border.setFill(null);

      if (checkWin() == true) {
        System.out.println("Player has won the game!");
          try {
              endGame(true);
          } catch (Exception ex) {

          }
        return;
      }

      if (bombs.isEmpty()) { //recursive call
        getNeighbors(this).forEach(Tile::open);
      }
    }
  }

  public boolean checkWin() {//checks for a win
    if ((X_SIZE*Y_SIZE - tilesClicked) == minesPlaced) {
      player1.minesweeperPoint();
      return true;
    } else {
      return false;
    }
  }

  public void endGame(boolean win) throws Exception { //ending the game
    //show mines
    for (int y = 0; y < Y_SIZE; y++) {
      for (int x = 0; x < X_SIZE; x++) {
        Tile tile = grid[x][y];

        if (tile.hasBomb) {
          tile.text.setText("X");
          tile.text.setStroke(null);
          tile.text.setFill(Color.BLACK);
          tile.text.setVisible(true);
        }
      }
    }

    Alert alert = new Alert(AlertType.CONFIRMATION);
    alert.setTitle("Game Over");
    if (win == true) {
      player1.tttPoint();
	  scoreboard.updateScoreBoard();
      alert.setHeaderText("Congratulations, you win!");
    } else {
      alert.setHeaderText("Sorry, you hit a mine!");
    }
    alert.setContentText("Would you like to play Again?");

    //option to end or reset
    Optional<ButtonType> result = alert.showAndWait();
    if (result.get() == ButtonType.OK){
        // ... user chose OK
        System.out.println("Player has started a new game.");
        scene.setRoot(createBoard());
    } else {
        System.out.println("Player has closed program.");
        //close program

        window.close();
        player1.uploadToFile(new File("../../database/database.csv"),player1,null);
        StartingUI mainMenu = new StartingUI();
			
        mainMenu.start(window);
        
    }

  }


  public void start (Stage stage, Player p1) throws Exception {
	player1=p1;
	scoreboard = new Scoreboard(player1);
    scene = new Scene(createBoard());
    window = stage;
    window.setTitle("MineSweeper");
    //window.initStyle(StageStyle.UTILITY);

    window.setScene(scene);
    window.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
