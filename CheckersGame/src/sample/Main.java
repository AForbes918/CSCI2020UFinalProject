package sample;
import javafx.application.Application;
import javafx.event.EventType;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.concurrent.atomic.AtomicInteger;

public class Main extends Application {
    @Override // Override the start method in the application class
    public void start(Stage primaryStage) {
        // Create a GridPane
        GridPane grid = new GridPane();
        AtomicInteger redPecies = new AtomicInteger(12);
        int yellowpecies = 12;



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
        Circle [] arrCirc = new Circle[24];
        arrCirc[0] = new Circle();
        arrCirc[0].setCenterY(40);
        arrCirc[0].setCenterX(120);
        arrCirc[0].setFill(Color.RED);
        arrCirc[0].setRadius(25);
        arrCirc[0].setOnMouseDragged(e->{
            double posX = e.getX();
            double posY = e.getY();
            arrCirc[0].setCenterX(posX);
            arrCirc[0].setCenterY(posY);
            if(arrCirc[0].getCenterY() > 560){
                arrCirc[0].setFill(Color.GREEN);
            }
            if(arrCirc[0].getCenterX() > 650){
//                arrCirc[0].removeEventHandler(MouseEvent.MOUSE_PRESSED, setOnMouseDragged);
                redPecies.addAndGet(-1); // javafx would let me do redPecies--; and recommended this (IDK why)
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
                double posX = e.getX();
                double posY = e.getY();
                double previousX = arrCirc[finalX1].getCenterX();
                double previousY = arrCirc[finalX1].getCenterY();

                arrCirc[finalX].setCenterX(posX);
                arrCirc[finalX].setCenterY(posY);

                if(arrCirc[finalX].getCenterY() > 560){
                    arrCirc[finalX].setFill(Color.GREEN);
                }
                if(arrCirc[finalX].getCenterX() > 650){
                    redPecies.addAndGet(-1); // javafx would let me do redPecies--; and recommended this (IDK why)
                }

            });
        }
        arrCirc[4] = new Circle();
        arrCirc[4].setCenterY(120);
        arrCirc[4].setCenterX(40);
        arrCirc[4].setFill(Color.RED);
        arrCirc[4].setRadius(25);
        arrCirc[4].setOnMouseDragged(e->{
            double posX = e.getX();
            double posY = e.getY();
            arrCirc[4].setCenterX(posX);
            arrCirc[4].setCenterY(posY);
            if(arrCirc[4].getCenterY() > 560){
                arrCirc[4].setFill(Color.GREEN);
            }
            if(arrCirc[4].getCenterX() > 650){
                redPecies.addAndGet(-1); // javafx would let me do redPecies--; and recommended this (IDK why)
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
                double posX = e.getX();
                double posY = e.getY();
                double previousX = arrCirc[finalX1].getCenterX();
                double previousY = arrCirc[finalX1].getCenterY();

                arrCirc[finalX].setCenterX(posX);
                arrCirc[finalX].setCenterY(posY);

                if(arrCirc[finalX].getCenterY() > 560){
                    arrCirc[finalX].setFill(Color.GREEN);
                }
                if(arrCirc[finalX].getCenterX() > 650){
                    redPecies.addAndGet(-1); // javafx would let me do redPecies--; and recommended this (IDK why)
                }

            });
        }
        arrCirc[8] = new Circle();
        arrCirc[8].setCenterY(200);
        arrCirc[8].setCenterX(120);
        arrCirc[8].setFill(Color.RED);
        arrCirc[8].setRadius(25);
        arrCirc[8].setOnMouseDragged(e->{
            double posX = e.getX();
            double posY = e.getY();
            arrCirc[8].setCenterX(posX);
            arrCirc[8].setCenterY(posY);
            if(arrCirc[8].getCenterY() > 560){
                arrCirc[8].setFill(Color.GREEN);
            }
            if(arrCirc[8].getCenterX() > 650){
                redPecies.addAndGet(-1); // javafx would let me do redPecies--; and recommended this (IDK why)
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
                double posX = e.getX();
                double posY = e.getY();
                double previousX = arrCirc[finalX1].getCenterX();
                double previousY = arrCirc[finalX1].getCenterY();

                arrCirc[finalX].setCenterX(posX);
                arrCirc[finalX].setCenterY(posY);

                if(arrCirc[finalX].getCenterY() > 560){
                    arrCirc[finalX].setFill(Color.GREEN);
                }
                if(arrCirc[finalX].getCenterX() > 650){
                    redPecies.addAndGet(-1); // javafx would let me do redPecies--; and recommended this (IDK why)
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
            double posX = e.getX();
            double posY = e.getY();
            arrCirc[12].setCenterX(posX);
            arrCirc[12].setCenterY(posY);

            if(arrCirc[12].getCenterY() < 90){
                arrCirc[12].setFill(Color.BLUE);
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
                double posX = e.getX();
                double posY = e.getY();
                double previousX = arrCirc[finalX1].getCenterX();
                double previousY = arrCirc[finalX1].getCenterY();

                arrCirc[finalX].setCenterX(posX);
                arrCirc[finalX].setCenterY(posY);

                if(arrCirc[finalX].getCenterY() < 90){
                    arrCirc[finalX].setFill(Color.BLUE);
                }

            });
        }
        arrCirc[16] = new Circle();
        arrCirc[16].setCenterY(520);
        arrCirc[16].setCenterX(120);
        arrCirc[16].setFill(Color.YELLOW);
        arrCirc[16].setRadius(25);
        arrCirc[16].setOnMouseDragged(e->{
            double posX = e.getX();
            double posY = e.getY();
            arrCirc[16].setCenterX(posX);
            arrCirc[16].setCenterY(posY);
            if(arrCirc[16].getCenterY() < 90){
                arrCirc[16].setFill(Color.BLUE);
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
                double posX = e.getX();
                double posY = e.getY();
                double previousX = arrCirc[finalX1].getCenterX();
                double previousY = arrCirc[finalX1].getCenterY();

                arrCirc[finalX].setCenterX(posX);
                arrCirc[finalX].setCenterY(posY);
                if(arrCirc[finalX].getCenterY() < 90){
                    arrCirc[finalX].setFill(Color.BLUE);
                }

            });
        }
        arrCirc[20] = new Circle();
        arrCirc[20].setCenterY(440);
        arrCirc[20].setCenterX(40);
        arrCirc[20].setFill(Color.YELLOW);
        arrCirc[20].setRadius(25);
        arrCirc[20].setOnMouseDragged(e->{
            double posX = e.getX();
            double posY = e.getY();
            arrCirc[20].setCenterX(posX);
            arrCirc[20].setCenterY(posY);
            if(arrCirc[20].getCenterY() < 90){
                arrCirc[20].setFill(Color.BLUE);
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
                double posX = e.getX();
                double posY = e.getY();
                double prevY = arrCirc[finalX].getCenterY();
                double prevX = arrCirc[finalX].getCenterX();
                arrCirc[finalX].setCenterX(posX);
                arrCirc[finalX].setCenterY(posY);
                if(arrCirc[finalX].getCenterY() < 90){
                    arrCirc[finalX].setFill(Color.BLUE);
                }

            });
        }


        Group stk = new Group(grid);
        for(int x = 0; x < 24; x++){
            stk.getChildren().add(arrCirc[x]);
        }
        VBox vBox = new VBox();
        vBox.setMinWidth(300);
//        vBox.setBackground(new Background(n));
        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(stk);
        borderPane.setRight(vBox);

//         Create a scene and place it in the stage
        Scene scene = new Scene (borderPane);
        primaryStage.setTitle("Checkers"); // Set the stage title
        primaryStage.setScene(scene); // Place in scene in the stage
        primaryStage.show(); // Display the stage;
    }
}