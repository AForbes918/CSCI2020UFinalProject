import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class Clock extends Pane { //clock class
    Timeline animation; // instance variables
    int tap;
    int maxTime;
    private String s = "";
    boolean timesUp=false;

    Label label;

    Clock(int maxTime){ // constructer to set instance variable and set fonts, and in case user has a specified time
    	label=new Label(Integer.toString(maxTime));
    	this.tap=maxTime;
    	this.maxTime=maxTime;
        label.setFont(javafx.scene.text.Font.font(20));
        getChildren().add(label);
        animation = new Timeline(new KeyFrame(Duration.seconds(1), e->timeLabelcountDown()));
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.play();
    }
    Clock(){ // default constructor to set time
    	label=new Label("0"); // what will be displayed in the game
    	this.tap=0;
        label.setFont(javafx.scene.text.Font.font(20));
        getChildren().add(label);
        animation = new Timeline(new KeyFrame(Duration.seconds(1), e->timeLabelcountUp())); //animation to count down
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.play();
    }
    public void transX(double xmove){
         label.setTranslateX(xmove);
    } // translating method to put countdown timer in right spot
    public void transY(double ymove){
         label.setTranslateX(ymove);
    }

    public void resetTimerDown() {
    	this.tap=maxTime;
    	timesUp=false;
    }
    public void resetTimerUp() {
    	this.tap=0;
    } // reset timer method

    private void timeLabelcountDown() { // countdown method giving next number to count down
        if (tap > 0) {
            tap--; // allows timer to count down
        }
        else {
        	timesUp=true;
        }
            s = tap + "";
            label.setText(s); // setting new time for timer
    }
    private void timeLabelcountUp() { // as well as a count up method
        if (tap > -1) {
            tap++; //allows timer to count up
        }
            s = tap + "";
            label.setText(s); // setting new time for timer
    }
}
