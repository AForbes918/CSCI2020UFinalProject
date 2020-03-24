package WindowTest;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class Clock extends Pane {
    Timeline animation;
    int tap;
    int maxTime;
    private String s = "";
    boolean timesUp=false;

    Label label;

    Clock(int maxTime){
    	label=new Label(Integer.toString(maxTime));
    	this.tap=maxTime;
    	this.maxTime=maxTime;
        label.setFont(javafx.scene.text.Font.font(20));
        getChildren().add(label);
        animation = new Timeline(new KeyFrame(Duration.seconds(1), e->timeLabelcountDown()));
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.play();
    }
    Clock(){
    	label=new Label("0");
    	this.tap=0;
        label.setFont(javafx.scene.text.Font.font(20));
        getChildren().add(label);
        animation = new Timeline(new KeyFrame(Duration.seconds(1), e->timeLabelcountUp()));
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.play();
    }
    public void transX(double xmove){
         label.setTranslateX(xmove);
    }
    public void transY(double ymove){
         label.setTranslateX(ymove);
    }

    public void resetTimerDown() {
    	this.tap=maxTime;
    	timesUp=false;
    }
    public void resetTimerUp() {
    	this.tap=0;
    }

    private void timeLabelcountDown() {
        if (tap > 0) {
            tap--;
        }
        else {
        	timesUp=true;
        }
            s = tap + "";
            label.setText(s);
    }
    private void timeLabelcountUp() {
        if (tap > -1) {
            tap++;
        }
            s = tap + "";
            label.setText(s);
    }
}
