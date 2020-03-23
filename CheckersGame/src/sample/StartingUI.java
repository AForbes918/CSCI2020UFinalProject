/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample;

import java.awt.Dimension;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 *
 * @author Andre
 */
public class StartingUI extends Application {
    
    @Override
    public void start(Stage primaryStage) throws Exception{
    	Player player1 = new Player();
    	Player player2 = new Player();
        int menuX = 600;
        int menuY = 450;
        Label title = new Label("Main Menu");
        Label player1label = new Label("Player 1: ");
        Label player2label = new Label("Player 2: ");
        Button confirmNames = new Button();
        TextField play1Name = new TextField();
        TextField play2Name = new TextField();
        
        
        Button ticTac = new Button();
        Button checkers = new Button();
        Button connectFour = new Button();
        Button mineSweeper = new Button();

        /**
         * Player Names
         */
        
        player1label.setTranslateY(150);
        player1label.setTranslateX(-menuX/2.5);
        player1label.setFont(Font.font("Arial",18));
        player1label.setTextFill(Color.WHITE);
        
        play1Name.setTranslateY(150);
        play1Name.setTranslateX(-menuX/3.75);
        play1Name.setMaxWidth(80);
        
        
        player2label.setTranslateY(150);
        player2label.setTranslateX(menuX/4);
        player2label.setFont(Font.font("Arial",18));
        player2label.setTextFill(Color.WHITE);
        
        play2Name.setTranslateY(150);
        play2Name.setTranslateX(menuX/2.5);
        play2Name.setMaxWidth(80);
        
        confirmNames.setText("Create Names");
        confirmNames.setTranslateY(200);
        
        confirmNames.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            }

        });
                
        /**
         * ticTacToe commands
         */
        ticTac.setText("Tic Tac Toe");
        connectFour.setFont(Font.font ("Arial",16));
        
        ticTac.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	if(player1.name.equals(player2.name)) {
            		player1.setName(player1.name+" 1");
            		player2.setName(player2.name+" 2");
            	}
            	TicTacToe game = new TicTacToe();
            	try {
					game.start(primaryStage,player1,player2);
				} catch (Exception e) { 
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }

        });
        
        ticTac.setTranslateX(-(menuX/2.5));
        ticTac.setTranslateY(100);
        /**
          * Connect Four commands
         */
        connectFour.setText("Connect Four");
        connectFour.setFont(Font.font ("Arial",16));
        
        connectFour.setOnAction(new EventHandler<ActionEvent>() {
             @Override
            public void handle(ActionEvent event) {
            	if(player1.name.equals(player2.name)) {
            		player1.setName(player1.name+" 1");
            		player2.setName(player2.name+" 2");
            	}
            	Connect4 game = new Connect4();
            	try {
					game.start(primaryStage,player1,player2);
				} catch (Exception e) { 
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });
        
        connectFour.setTranslateX(-(menuX/7.5));
        connectFour.setTranslateY(100);
        
        /**
         * Battle Ship commands
         */
        mineSweeper.setText("Minesweeper");
        mineSweeper.setFont(Font.font ("Arial",16));
        
        mineSweeper.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	if(player1.name.equals(player2.name)) {
            		player1.setName(player1.name+" 1");
            		player2.setName(player2.name+" 2");
            	}
            	MineSweeper game = new MineSweeper();
            	try {
					game.start(primaryStage,player1,player2);
				} catch (Exception e) { 
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }

        });
        
        mineSweeper.setTranslateX(menuX/7.5);
        mineSweeper.setTranslateY(100);
        
        /**
         * Checkers Commands
         */
        checkers.setText("  Checkers  ");
        checkers.setFont(Font.font ("Arial",16));
        
        checkers.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	if(player1.name.equals(player2.name)) {
            		player1.setName(player1.name+" 1");
            		player2.setName(player2.name+" 2");
            	}
            	Checkers game = new Checkers();
            	try {
					game.start(primaryStage,player1,player2);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });
        
        checkers.setTranslateX(menuX/2.5);
        checkers.setTranslateY(100);
        
        title.setFont(new Font("Arial", 30));
        title.setTextFill(Color.WHITE);
        
        confirmNames.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if(!play1Name.getText().equals("")) {
					player1.setName(play1Name.getText());
				}
				if(!play2Name.getText().equals("")) {
					player2.setName(play2Name.getText());
				}
				
			}
        	
        });
        
        
        
        StackPane root = new StackPane();
        StackPane.setAlignment(title, Pos.TOP_CENTER);
        root.setBackground(new Background(new BackgroundFill(Color.rgb(64,64,64), CornerRadii.EMPTY, Insets.EMPTY)));
        
        
        root.getChildren().add(title);
        root.getChildren().add(ticTac);
        root.getChildren().add(checkers);
        root.getChildren().add(connectFour);
        root.getChildren().add(mineSweeper);
        root.getChildren().add(player1label);
        root.getChildren().add(player2label);
        root.getChildren().add(play1Name);
        root.getChildren().add(play2Name);
        root.getChildren().add(confirmNames);
                
        
        
        Scene scene = new Scene(root, menuX, menuY);
        
        primaryStage.setTitle("Final Project UI");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
