package WindowTest;


import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
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

public class Checkers extends Application {
        Player player1;
        Player player2;
        Stage stage;
        Scoreboard scoreboard;
        Button returnBack = new Button();
        private boolean playerOneTurn = true;

        public void start(Stage primaryStage) throws Exception {
        	
        }
        public void start(Stage primaryStage, Player one, Player two) throws Exception {
            player1 = one;
            player2 = two;
            scoreboard=new Scoreboard(player1,player2,60,player1.name+" turn.",2);
            scoreboard.changeColour(Color.RED);
            stage=primaryStage;
	        // Create a GridPane
	        int a = 1;
	        GridPane grid = new GridPane();
	        AtomicInteger red = new AtomicInteger(12);
	        AtomicInteger yellow = new AtomicInteger(12);
	
	        returnBack.setText("Return to Menu");
			returnBack.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					try {
						player1.uploadToFile(new File("C:\\Users\\denni\\eclipse-workspace\\finalprojecttest\\src\\database\\database.csv"),player1,player2);
						StartingUI menu = new StartingUI();
						stage.close();
						menu.start(stage);
					}   
					catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			});
	        
	        Group stk = new Group(getgrid());
	        Circle [] arr = pieces(red,yellow);
	        for(int x = 0; x < 24; x++){
	            stk.getChildren().add(arr[x]);
	        }
	        HBox hBox = new HBox();
	        Button p1 = new Button("Player 1 wins");
	        Button p2 = new Button("Player 2 wins");
	        Button turnIndicator = new Button("Switch Turns");
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
	        turnIndicator.setOnAction(e ->{
	        	if(scoreboard.clk.timesUp) {
	        		if(playerOneTurn) {
	        			player2.checkersPoint();
	    				scoreboard.updateScoreBoard();
	        			Alert alert = new Alert(AlertType.CONFIRMATION);
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
								player1.uploadToFile(new File("C:\\Users\\denni\\eclipse-workspace\\finalprojecttest\\src\\database\\database.csv"),player1,player2);
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
								player1.uploadToFile(new File("C:\\Users\\denni\\eclipse-workspace\\finalprojecttest\\src\\database\\database.csv"),player1,player2);
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
	        		scoreboard.clk.resetTimerDown();
		        	playerOneTurn=!playerOneTurn;
		        	if(playerOneTurn) {
		        		scoreboard.changeTurn(player1.name+" turn.");
		        		scoreboard.changeColour(Color.YELLOW);
		        	}
		        	else {
		        		scoreboard.changeTurn(player2.name+" turn.");
		        		scoreboard.changeColour(Color.RED);
		        	}
	        	}
	        	
	        });
	        hBox.setSpacing(12);
	        hBox.getChildren().addAll(p1,p2,turnIndicator);
	        VBox vBox = new VBox(hBox);
	        vBox.setMinWidth(300);
	
	        HBox buttons = new HBox();
	
	        buttons.setPadding(new Insets(10,10,10,10));
	//        buttons.setAlignment(Pos.CENTER);
	        buttons.setSpacing(12);
	
	        Button inst = new Button("INSTRUCTIONS");
	        Button reset = new Button("RESET GAME ");
	
	        reset.setOnAction(e ->{
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
	        BorderPane borderPane = new BorderPane();
	        borderPane.setCenter(stk);
	        borderPane.setRight(vBox);
	        borderPane.setTop(buttons);
	
	
	//         Create a scene and place it in the stage
	
	        Scene scene = new Scene (borderPane);
	        primaryStage.setTitle("Checkers"); // Set the stage title
	
	        primaryStage.setScene(scene); // Place in scene in the stage
	
	        primaryStage.show(); // Display the stage;
	        getAlert();
        }
	    public static GridPane getgrid(){
	        GridPane grid = new GridPane();
	        // Create 64 rectangles and add to pane
	        int count = 0;
	        double size = 80; // size of rectangle
	
	        for (int i = 0; i < 8; i++) {
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
	    public Circle[] pieces(AtomicInteger red, AtomicInteger yellow) {
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
	    public static void getAlert(){
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
