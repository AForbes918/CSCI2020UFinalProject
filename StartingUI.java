/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package startingui;

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
public class StartingUI extends Application  {
    
    @Override
    public void start(Stage primaryStage) {
        int menuX = 600;
        int menuY = 450;
        Label title = new Label("Main Menu");
        Label player1 = new Label("Player 1: ");
        Label player2 = new Label("Player 2: ");
        Button confirmNames = new Button();
        TextField play1Name = new TextField();
        TextField play2Name = new TextField();
        Player user1 = new Player();
        Player user2 = new Player();
        
        
        Button ticTac = new Button();
        Button checkers = new Button();
        Button connectFour = new Button();
        Button battleShip = new Button();
        /**
         * Player Names
         */
        
        player1.setTranslateY(150);
        player1.setTranslateX(-menuX/2.5);
        player1.setFont(Font.font("Arial",18));
        player1.setTextFill(Color.WHITE);
        
        play1Name.setTranslateY(150);
        play1Name.setTranslateX(-menuX/3.75);
        play1Name.setMaxWidth(80);
        
        
        player2.setTranslateY(150);
        player2.setTranslateX(menuX/4);
        player2.setFont(Font.font("Arial",18));
        player2.setTextFill(Color.WHITE);
        
        play2Name.setTranslateY(150);
        play2Name.setTranslateX(menuX/2.5);
        play2Name.setMaxWidth(80);
        
        confirmNames.setText("Create Names");
        confirmNames.setTranslateY(200);
        
        confirmNames.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                user1.setName(play1Name.getText());
                user2.setName(play2Name.getText());
                
            }

        });
        
        
        
        
                
        /**
         * ticTacToe commands
         */
        ticTac.setText("Tic Tac Toe");
        connectFour.setFont(Font.font ("Arial",18));
        
        ticTac.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(user1.getName().equals(user2.getName())) {
                    user1.setName(user1.name+" 1");
                    user2.setName(user2.name+" 2");
                                        
                }
                TicTacToe game = new TicTacToe();
                try {
                    game.start(primaryStage,user1,user2);
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
                primaryStage.close();
                
                
                
                
                
            }
        });
        
        connectFour.setTranslateX(-(menuX/7.5));
        connectFour.setTranslateY(100);
        
        /**
         * Battle Ship commands
         */
        battleShip.setText("  Battle Ship  ");
        battleShip.setFont(Font.font ("Arial",16));
        /**
        battleShip.setOnAction(new EventHandler<ActionEvent>() {

        });
        **/
        battleShip.setTranslateX(menuX/7.5);
        battleShip.setTranslateY(100);
        
        /**
         * Checkers Commands
         */
        checkers.setText("  Checkers  ");
        checkers.setFont(Font.font ("Arial",16));
        /**
        checkers.setOnAction(new EventHandler<ActionEvent>() {

        });
        **/
        checkers.setTranslateX(menuX/2.5);
        checkers.setTranslateY(100);
        
        title.setFont(new Font("Arial", 30));
        title.setTextFill(Color.WHITE);
        
        
        
        
        
        StackPane root = new StackPane();
        StackPane.setAlignment(title, Pos.TOP_CENTER);
        root.setBackground(new Background(new BackgroundFill(Color.rgb(64,64,64), CornerRadii.EMPTY, Insets.EMPTY)));
        
        
        root.getChildren().add(title);
        root.getChildren().add(ticTac);
        root.getChildren().add(checkers);
        root.getChildren().add(connectFour);
        root.getChildren().add(battleShip);
        root.getChildren().add(player1);
        root.getChildren().add(player2);
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
