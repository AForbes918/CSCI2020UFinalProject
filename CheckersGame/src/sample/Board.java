package sample;

import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public class Board {
    Color clr;
    StackPane stck;
    public Board(boolean color){
        if(color) {
            clr = Color.BLACK;
        }else{
            clr = Color.WHITE;
        }
        stck = new StackPane();
        stck.setBackground(new Background(new BackgroundFill(clr, CornerRadii.EMPTY, Insets.EMPTY)));
    }
    public StackPane getStck(){
        return  stck;
    }
}
