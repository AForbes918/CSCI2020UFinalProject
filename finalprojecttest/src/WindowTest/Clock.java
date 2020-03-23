package WindowTest;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class Clock extends Pane {
    private Timeline animation;
    private int tap = 0;
    private String s = "";

    Label label = new Label("0");

    Clock(){
        label.setFont(javafx.scene.text.Font.font(20));
        getChildren().add(label);
        animation = new Timeline(new KeyFrame(Duration.seconds(1), e->timeLabel()));
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.play();
    }
    public void transX(double xmove){
         label.setTranslateX(xmove);
    }
    public void transY(double ymove){
         label.setTranslateX(ymove);
    }


    private void timeLabel(){

        if (tap > -1) {
            tap++;
        }
            s = tap + "";
            label.setText(s);

    }
}
