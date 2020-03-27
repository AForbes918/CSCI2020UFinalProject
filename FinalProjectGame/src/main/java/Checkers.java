import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.io.File;
import java.io.IOException;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
public class Checkers extends Application { //Creating class
        Player player1;  // Instance variables for class
        Player player2;
        Stage stage;
        Scoreboard scoreboard;
        Button returnBack = new Button();
        private boolean playerOneTurn = true;

        public void start(Stage primaryStage) throws Exception {
        	
        }
        public void start(Stage primaryStage, Player one, Player two) throws Exception { // start method for Application
            player1 = one; // setting the name for the players
            player2 = two;
            scoreboard=new Scoreboard(player1,player2,60,player1.name+" turn.",2); // creating instance of scoreboard
            scoreboard.changeColour(Color.RED);
            stage=primaryStage;

	        // Create a GridPane for the checker board
	        int a = 1;
	        GridPane grid = new GridPane();
	        AtomicInteger red = new AtomicInteger(12);
	        AtomicInteger yellow = new AtomicInteger(12);
	
	        returnBack.setText("Return to Menu"); // to allow user to go back to main menu
			returnBack.setOnAction(new EventHandler<ActionEvent>() { // even handler to allow user to go back to menu
				@Override
				public void handle(ActionEvent event) {
					try {
						player1.uploadToFile(new File("../../database/database.csv"),player1,player2);
						StartingUI menu = new StartingUI(); // creating instance of StartingUI which will be open up the main menu again
						stage.close(); // closing game
						menu.start(stage); // opening menu
					}   
					catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			});
	        
	        Group stk = new Group(getgrid()); // creating a group layout that will hold the layout

	        Circle [] arr = pieces(red,yellow); // this will get an array of circles

	        for(int x = 0; x < 24; x++){ //putting each circle in the group layout(Board)
	            stk.getChildren().add(arr[x]);
	        }

	        HBox hBox = new HBox(); //creating an HBox for the buttons

	        Button p1 = new Button("Player 1 wins"); // Buttons to determine which player wins
	        Button p2 = new Button("Player 2 wins");

	        Button turnIndicator = new Button("Switch Turns"); // event Handler for switching buttons

	        p1.setOnAction(e ->{
	        	player1.checkersPoint();
	        	scoreboard.updateScoreBoard();
	        	stk.getChildren().clear();
	            stk.getChildren().add(getgrid());
	            Circle [] brd = pieces(red, yellow);
	            for(int x = 0; x < 24; x++){
	                stk.getChildren().add(brd[x]);
	            }
	        });
	        p2.setOnAction(e ->{
	        	player2.checkersPoint();
				scoreboard.updateScoreBoard();
	        	stk.getChildren().clear();
	            stk.getChildren().add(getgrid());
	            Circle [] brd = pieces(red, yellow);
	            for(int x = 0; x < 24; x++){
	                stk.getChildren().add(brd[x]);
	            }
	        });
	        turnIndicator.setOnAction(e ->{ // This code will switch the score board to indicate the players name
	        	if(scoreboard.clk.timesUp) { // determing if time is up for each player
	        		if(playerOneTurn) {
	        			player2.checkersPoint();
	    				scoreboard.updateScoreBoard();
	        			Alert alert = new Alert(AlertType.CONFIRMATION); // creating an alert box to ask player to play again
	        			alert.setTitle("Checkers");
	        			alert.setHeaderText(player2.name+" wins!");
	        			alert.setContentText("Would you like to play again?");
	        			ButtonType buttonYes = new ButtonType("Yes");
	        			ButtonType buttonQuit = new ButtonType("Quit Game");
	        			alert.getButtonTypes().setAll(buttonYes, buttonQuit);
	        			Optional<ButtonType> result = alert.showAndWait();
	        			if (result.get() == buttonYes){
	        				stk.getChildren().clear();
	        	            stk.getChildren().add(getgrid());
	        	            Circle [] brd = pieces(red, yellow);
	        	            for(int x = 0; x < 24; x++){
	        	                stk.getChildren().add(brd[x]);
	        	            }
	        			} 
	        			else {
	        				try {
								player1.uploadToFile(new File("../../database/database.csv"),player1,player2);
								StartingUI mainMenu = new StartingUI();
		        				stop();
		        				mainMenu.start(stage);
							} catch (IOException e1) {
								e1.printStackTrace();
							} catch (Exception e1) {
								e1.printStackTrace();
							}
	        				
	        			}
	        		}
	        		else{
	        			player1.checkersPoint();
	    	        	scoreboard.updateScoreBoard();
	        			Alert alert = new Alert(AlertType.CONFIRMATION);
	        			alert.setTitle("Checkers");
	        			alert.setHeaderText(player1.name+" wins!");
	        			alert.setContentText("Would you like to play again?");
	        			ButtonType buttonYes = new ButtonType("Yes");
	        			ButtonType buttonQuit = new ButtonType("Quit Game");
	        			alert.getButtonTypes().setAll(buttonYes, buttonQuit);
	        			Optional<ButtonType> result = alert.showAndWait();
	        			if (result.get() == buttonYes){
	        				stk.getChildren().clear();
	        	            stk.getChildren().add(getgrid());
	        	            Circle [] brd = pieces(red, yellow);
	        	            for(int x = 0; x < 24; x++){
	        	                stk.getChildren().add(brd[x]);
	        	            }
	        			} 
	        			else {
	        				try {
								player1.uploadToFile(new File("../../database/database.csv"),player1,player2);
								StartingUI mainMenu = new StartingUI();
		        				stop();
		        				mainMenu.start(stage);
							} catch (IOException e1) {
								e1.printStackTrace();
							} catch (Exception e1) {
								e1.printStackTrace();
							}
	        				
	        			}
	        		}
	        	}
	        	else {
	        		scoreboard.clk.resetTimerDown(); // reset timer incase player wants to play again
		        	playerOneTurn = !playerOneTurn;
		        	if(playerOneTurn) {
		        		scoreboard.changeTurn(player1.name+" turn."); // indicating player turn
						scoreboard.changeColour(Color.YELLOW);
		        	}
		        	else {
		        		scoreboard.changeTurn(player2.name+" turn.");
		        		scoreboard.changeColour(Color.RED);
		        	}
	        	}
	        	
	        });
	        hBox.setSpacing(12); // spacing for hbox buttons
	        hBox.getChildren().addAll(p1,p2,turnIndicator); //adding buttons the borderpane

	        TextField writein = new TextField(); // text field for chat box
	        writein.setPromptText("Say 'Hi'");
	        Button send = new Button ("Send"); // button to send

	        HBox chat = new HBox(writein, send); // adding chat box
	        chat.setSpacing(10);

	        send.setOnAction(e ->{
	        	Client clt = new Client(); // creating instance of client and server
	        	clt.setText(writein.getText());
	        	Server serv = new Server();

				Alert alt = new Alert(AlertType.INFORMATION); // setting an alert box
				alt.setContentText(serv.getRet(clt.getText()));
				alt.setResizable(true);
				alt.show();
			});

	        VBox vBox = new VBox(hBox,chat); //VBox for hbox and chat bot
	        vBox.setSpacing(10);
	        vBox.setMinWidth(300);
	
	        HBox buttons = new HBox();
	
	        buttons.setPadding(new Insets(10,10,10,10));
	//        buttons.setAlignment(Pos.CENTER);
	        buttons.setSpacing(12);
	
	        Button inst = new Button("INSTRUCTIONS"); // button for instructions
	        Button reset = new Button("RESET GAME "); // button to reset game
	
	        reset.setOnAction(e ->{ // event handler for reset button
	            stk.getChildren().clear();
	            stk.getChildren().add(getgrid());
	            Circle [] brd = pieces(red, yellow);
	            for(int x = 0; x < 24; x++){
	                stk.getChildren().add(brd[x]);
	            }
	        });
	        inst.setOnAction( e -> {
	            // Display an alert box to explain game;
	            getAlert();
	        });
	        inst.setTranslateX(375);
	        reset.setTranslateX(550);
	        scoreboard.getChildren().addAll(inst,reset);
	        buttons.getChildren().addAll(scoreboard);

	        BorderPane borderPane = new BorderPane(); // the entire is game is on a borderpane
	        borderPane.setCenter(stk); // adding the pieces to the center
	        borderPane.setRight(vBox); // chat bot and win buttons to the right
	        borderPane.setTop(buttons); // and reset, instructions button to the top of pane
	
	
	//         Create a scene and place it in the stage
	
	        Scene scene = new Scene (borderPane);
	        primaryStage.setTitle("Checkers"); // Set the stage title
	
	        primaryStage.setScene(scene); // Place in scene in the stage
	
	        primaryStage.show(); // Display the stage;
	        getAlert();
        }
	    public static GridPane getgrid(){
	        GridPane grid = new GridPane(); // using grid pane layout
	        // Create 64 rectangles and add to pane
	        int count = 0;
	        double size = 80; // size of rectangle
	
	        for (int i = 0; i < 8; i++) { // creating checker Board and adding to gridpane
	            count++;
	            for (int j = 0; j < 8; j++) {
	                Rectangle r = new Rectangle(size, size);
	                if (count % 2 == 0) {
	                    r.setFill(Color.BLACK);
	                }else{
	                    r.setFill(Color.WHITE);
	                }
	                grid.add(r,j,i);
	                count++;
	            }
	        }
	        return grid;
	    }
	    public Circle[] pieces(AtomicInteger red, AtomicInteger yellow) { // In this code, each circle is placed on the group layout.
        	//as well as giving the circle the ability to move, using a mouse draggeed event. This is Done for all 24 circles.

	        Circle [] arrCirc = new Circle[24];
	        arrCirc[0] = new Circle();
	        arrCirc[0].setCenterY(40);
	        arrCirc[0].setCenterX(120);
	        arrCirc[0].setFill(Color.RED);
	        arrCirc[0].setRadius(25);
	        arrCirc[0].setOnMouseDragged(e->{
	        	if(playerOneTurn) {
	        		double posX = e.getX();
		            double posY = e.getY();
		            if(!(posX > 900)  && posY > 20){
		                arrCirc[0].setCenterX(posX);
		                arrCirc[0].setCenterY(posY);
		                if(arrCirc[0].getCenterY() > 560){
		                    arrCirc[0].setFill(Color.GREEN);
		                }
		                if(arrCirc[0].getCenterX() > 650){
		//                arrCirc[0].removeEventHandler(MouseEvent.MOUSE_PRESSED, setOnMouseDragged);
		                    red.addAndGet(-1);
		                    // ommended this (IDK why)
		                }
		            }
	        	} 
	        });
	        arrCirc[0].setOnMouseClicked(e->{
            	if(e.getButton() == MouseButton.SECONDARY) {
            		arrCirc[0].setFill(Color.TRANSPARENT);
            	}
            });
	
	        for(int x = 1; x < 4; x++){
	            arrCirc[x] = new Circle();
	            arrCirc[x].setCenterY(40);
	            arrCirc[x].setCenterX(arrCirc[x - 1].getCenterX() + 160);
	            arrCirc[x].setFill(Color.RED);
	            arrCirc[x].setRadius(25);
	            int finalX = x;
	            int finalX1 = x;
	            arrCirc[x].setOnMouseDragged(e->{
	            	if(playerOneTurn) {
		                double posX = e.getX();
		                double posY = e.getY();
		                if(!(posX > 900) && posY > 10) {
		
		                    arrCirc[finalX].setCenterX(posX);
		                    arrCirc[finalX].setCenterY(posY);
		
		                    if (arrCirc[finalX].getCenterY() > 560) {
		                        arrCirc[finalX].setFill(Color.GREEN);
		                    }
		                    if (arrCirc[finalX].getCenterX() > 650) {
		                        red.addAndGet(-1);
		                    }
		                }
	            	}
	            });
	            arrCirc[x].setOnMouseClicked(e->{
	            	if(e.getButton() == MouseButton.SECONDARY) {
	            		arrCirc[finalX].setFill(Color.TRANSPARENT);
	            	}
	            });
	        }
	        arrCirc[4] = new Circle();
	        arrCirc[4].setCenterY(120);
	        arrCirc[4].setCenterX(40);
	        arrCirc[4].setFill(Color.RED);
	        arrCirc[4].setRadius(25);
	        arrCirc[4].setOnMouseDragged(e->{
	        	if(playerOneTurn) {
	        		double posX = e.getX();
		            double posY = e.getY();
		            if(!(posX > 900) && posY > 10) {
		                arrCirc[4].setCenterX(posX);
		                arrCirc[4].setCenterY(posY);
		                if (arrCirc[4].getCenterY() > 560) {
		                    arrCirc[4].setFill(Color.GREEN);
		                }
		                if (arrCirc[4].getCenterX() > 650) {
		                    red.addAndGet(-1);
		                }
		            }

	        	}
	        });
	        arrCirc[4].setOnMouseClicked(e->{
            	if(e.getButton() == MouseButton.SECONDARY) {
            		arrCirc[4].setFill(Color.TRANSPARENT);
            	}
            });
	
	        for(int x = 5; x < 8; x++){
	            arrCirc[x] = new Circle();
	            arrCirc[x].setCenterY(arrCirc[4].getCenterY());
	            arrCirc[x].setCenterX(arrCirc[x - 1].getCenterX() + 160);
	            arrCirc[x].setFill(Color.RED);
	            arrCirc[x].setRadius(25);
	            int finalX = x;
	            int finalX1 = x;
	            arrCirc[x].setOnMouseDragged(e->{
	            	if(playerOneTurn) {
		                double posX = e.getX();
		                double posY = e.getY();
		                if(!(posX > 900) && posY > 10) {
		
		                    arrCirc[finalX].setCenterX(posX);
		                    arrCirc[finalX].setCenterY(posY);
		
		                    if (arrCirc[finalX].getCenterY() > 560) {
		                        arrCirc[finalX].setFill(Color.GREEN);
		                    }
		                    if (arrCirc[finalX].getCenterX() > 650) {
		                        red.addAndGet(-1); // javafx would let me do redPecies--; and recommended this (IDK why)
		                    }
		                }
	            	}
	            });
	            arrCirc[x].setOnMouseClicked(e->{
	            	if(e.getButton() == MouseButton.SECONDARY) {
	            		arrCirc[finalX].setFill(Color.TRANSPARENT);
	            	}
	            });
	        }
	        arrCirc[8] = new Circle();
	        arrCirc[8].setCenterY(200);
	        arrCirc[8].setCenterX(120);
	        arrCirc[8].setFill(Color.RED);
	        arrCirc[8].setRadius(25);
	        arrCirc[8].setOnMouseDragged(e->{
	        	if(playerOneTurn) {
		            double posX = e.getX();
		            double posY = e.getY();
		            if(!(posX > 900) && posY > 10) {
		                arrCirc[8].setCenterX(posX);
		                arrCirc[8].setCenterY(posY);
		                if (arrCirc[8].getCenterY() > 560) {
		                    arrCirc[8].setFill(Color.GREEN);
		                }
		                if (arrCirc[8].getCenterX() > 650) {
		                    red.addAndGet(-1); // javafx would let me do redPecies--; and recommended this (IDK why)
		                }
		            }
	        	}
	        });
	        arrCirc[8].setOnMouseClicked(e->{
            	if(e.getButton() == MouseButton.SECONDARY) {
            		arrCirc[8].setFill(Color.TRANSPARENT);
            	}
            });
	
	        for(int x = 9; x < 12; x++){
	        	
	            arrCirc[x] = new Circle();
	            arrCirc[x].setCenterY(arrCirc[8].getCenterY());
	            arrCirc[x].setCenterX(arrCirc[x - 1].getCenterX() + 160);
	            arrCirc[x].setFill(Color.RED);
	            arrCirc[x].setRadius(25);
	            int finalX = x;
	            int finalX1 = x;
	            arrCirc[x].setOnMouseDragged(e->{
	            	if(playerOneTurn) {
	            		double posX = e.getX();
		                double posY = e.getY();
		                if(!(posX > 900) && posY > 10) {
		
		                    arrCirc[finalX].setCenterX(posX);
		                    arrCirc[finalX].setCenterY(posY);
		
		                    if (arrCirc[finalX].getCenterY() > 560) {
		                        arrCirc[finalX].setFill(Color.GREEN);
		                    }
		                    if (arrCirc[finalX].getCenterX() > 650) {
		                        red.addAndGet(-1);
		                    }
		                }
	            	}
	            });
	            arrCirc[x].setOnMouseClicked(e->{
	            	if(e.getButton() == MouseButton.SECONDARY) {
	            		arrCirc[finalX].setFill(Color.TRANSPARENT);
	            	}
	            });
	        }
	        //// Other side ================================================
	        arrCirc[12] = new Circle();
	        arrCirc[12].setCenterY(600);
	        arrCirc[12].setCenterX(40);
	        arrCirc[12].setFill(Color.YELLOW);
	        arrCirc[12].setRadius(25);
	        arrCirc[12].setOnMouseDragged(e->{
	        	if(!playerOneTurn) {
	        		double posX = e.getX();
		            double posY = e.getY();
		            if(!(posX > 900) && posY > 110) {
		                arrCirc[12].setCenterX(posX);
		                arrCirc[12].setCenterY(posY);
		
		                if (arrCirc[12].getCenterY() < 90) {
		                    arrCirc[12].setFill(Color.BLUE);
		                }
		                if (arrCirc[12].getCenterX() > 650) {
		                    yellow.addAndGet(-1);
		
		                }
		            }
	        	}         
	        });
	        arrCirc[12].setOnMouseClicked(e->{
            	if(e.getButton() == MouseButton.SECONDARY) {
            		arrCirc[25].setFill(Color.TRANSPARENT);
            	}
            });
	        for(int x = 13; x < 16; x++){
	            arrCirc[x] = new Circle();
	            arrCirc[x].setCenterY(600);
	            arrCirc[x].setCenterX(arrCirc[x - 1].getCenterX() + 160);
	            arrCirc[x].setFill(Color.YELLOW);
	            arrCirc[x].setRadius(25);
	            int finalX = x;
	            int finalX1 = x;
	            arrCirc[x].setOnMouseDragged(e->{
	            	if(!playerOneTurn) {
	            		double posX = e.getX();
		                double posY = e.getY();
		                if(!(posX > 900) && posY > 10) {
		
		                    arrCirc[finalX].setCenterX(posX);
		                    arrCirc[finalX].setCenterY(posY);
		
		                    if (arrCirc[finalX].getCenterY() < 90) {
		                        arrCirc[finalX].setFill(Color.BLUE);
		                    }
		                    if (arrCirc[finalX].getCenterX() > 650) {
		                        yellow.addAndGet(-1);
		
		                    }
		                }
	            	}  
	            });
	            arrCirc[x].setOnMouseClicked(e->{
	            	if(e.getButton() == MouseButton.SECONDARY) {
	            		 arrCirc[finalX].setFill(Color.TRANSPARENT);
	            	}
	            });
	        }
	        arrCirc[16] = new Circle();
	        arrCirc[16].setCenterY(520);
	        arrCirc[16].setCenterX(120);
	        arrCirc[16].setFill(Color.YELLOW);
	        arrCirc[16].setRadius(25);
	        arrCirc[16].setOnMouseDragged(e->{
	        	if(!playerOneTurn) {
	        		double posX = e.getX();
		            double posY = e.getY();
		            if(!(posX > 900) && posY > 10) {
		                arrCirc[16].setCenterX(posX);
		                arrCirc[16].setCenterY(posY);
		                if (arrCirc[16].getCenterY() < 90) {
		                    arrCirc[16].setFill(Color.BLUE);
		                }
		                if (arrCirc[16].getCenterX() > 650) {
		                    yellow.addAndGet(-1);
		
		                }
		            }
	        	}          
	        });
	        arrCirc[16].setOnMouseClicked(e->{
            	if(e.getButton() == MouseButton.SECONDARY) {
            		arrCirc[16].setFill(Color.TRANSPARENT);
            	}
            });
	        for(int x = 17; x < 20; x++){
	            arrCirc[x] = new Circle();
	            arrCirc[x].setCenterY(520);
	            arrCirc[x].setCenterX(arrCirc[x - 1].getCenterX() + 160);
	            arrCirc[x].setFill(Color.YELLOW);
	            arrCirc[x].setRadius(25);
	            int finalX = x;
	            int finalX1 = x;
	            arrCirc[x].setOnMouseDragged(e->{
	            	if(!playerOneTurn) {
	            		double posX = e.getX();
		                double posY = e.getY();
		                if(!(posX > 900) && posY > 10) {
		
		                    arrCirc[finalX].setCenterX(posX);
		                    arrCirc[finalX].setCenterY(posY);
		                    if (arrCirc[finalX].getCenterY() < 90) {
		                        arrCirc[finalX].setFill(Color.BLUE);
		                    }
		                    if (arrCirc[finalX].getCenterX() > 650) {
		                        yellow.addAndGet(-1);
		
		                    }
		                }
	            	}           
	            });
	            arrCirc[x].setOnMouseClicked(e->{
	            	if(e.getButton() == MouseButton.SECONDARY) {
	            		arrCirc[finalX].setFill(Color.TRANSPARENT);
	            	}
	            });
	        }
	        arrCirc[20] = new Circle();
	        arrCirc[20].setCenterY(440);
	        arrCirc[20].setCenterX(40);
	        arrCirc[20].setFill(Color.YELLOW);
	        arrCirc[20].setRadius(25);
	        arrCirc[20].setOnMouseDragged(e->{
	        	if(!playerOneTurn) {
	        		double posX = e.getX();
		            double posY = e.getY();
		            if(!(posX > 900) && posY > 10) {
		                arrCirc[20].setCenterX(posX);
		                arrCirc[20].setCenterY(posY);
		                if (arrCirc[20].getCenterY() < 90) {
		                    arrCirc[20].setFill(Color.BLUE);
		                }
		                if (arrCirc[20].getCenterX() > 650) {
		                    yellow.addAndGet(-1);
		
		                }
		            }
	        	}           
	        });
	        arrCirc[20].setOnMouseClicked(e->{
            	if(e.getButton() == MouseButton.SECONDARY) {
            		arrCirc[20].setFill(Color.TRANSPARENT);
            	}
            });
	        for(int x = 21; x < 24; x++){
	            arrCirc[x] = new Circle();
	            arrCirc[x].setCenterY(440);
	            arrCirc[x].setCenterX(arrCirc[x - 1].getCenterX() + 160);
	            arrCirc[x].setFill(Color.YELLOW);
	            arrCirc[x].setRadius(25);
	            int finalX = x;
	            int finalX1 = x;
	            arrCirc[x].setOnMouseDragged(e->{
	            	if(!playerOneTurn) {
	            		double posX = e.getX();
		                double posY = e.getY();
		                if(!(posX > 900) && posY > 10) {
		                    arrCirc[finalX].setCenterX(posX);
		                    arrCirc[finalX].setCenterY(posY);
		                    if (arrCirc[finalX].getCenterY() < 90) {
		                        arrCirc[finalX].setFill(Color.BLUE);
		                    }
		                    if (arrCirc[finalX].getCenterX() > 650) {
		                        yellow.addAndGet(-1);
		                    }
		                }
	            	}             
	            });
	            arrCirc[x].setOnMouseClicked(e->{
	            	if(e.getButton() == MouseButton.SECONDARY) {
	            		arrCirc[finalX].setFill(Color.TRANSPARENT);
	            	}
	            });
	        }
	        return arrCirc;
	    }
	    public static void getAlert(){ // creating and alert box that include instructions
	        ButtonType iUnderstand = new ButtonType("I understand");
	        String instructions = "To play checkers, each player must choose a color. Once choosing a colour, the player can only move to the black tiles and " +
	                "CAN NOT MOVE BACK. A king can move back, to get a king get your piece to the other side. The piece will change color to indicate the king. If a piece " +
	                "is killed right click it to eliminate it. At the end of the game indicate the winner. ENJOY";
	
	        Alert alert = new Alert(Alert.AlertType.INFORMATION, instructions, iUnderstand);
	        alert.setTitle("Instructions");
	        alert.setHeaderText("Welcome to Checkers");
	        alert.setResizable(true);
	        alert.getDialogPane().setMinHeight(250);
	        alert.showAndWait();
	
	        if (alert.getResult() == iUnderstand) {
	            //do stuff
	            alert.close();
	        }
	    }
	}
