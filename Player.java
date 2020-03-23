package startingui;

public class Player{
	String name;
	int tttScore;
	int cfourScore;
	int checkersScore;
	boolean isTurn;
	Player(){
		this.name="Player";
		this.tttScore=0;
		this.cfourScore=0;
		this.checkersScore=0;
		this.isTurn=false;
	}
	Player(String n,boolean isTurn){
		this.name=n;
		this.tttScore=0;
		this.cfourScore=0;
		this.checkersScore=0;
		this.isTurn=isTurn;
	}
	public void tttPoint() {
		tttScore++;
	}
	public void cfourPoint() {
		cfourScore++;
	}
	public void checkersPoint() {
		checkersScore++;	
	}
	public void swap() {
		isTurn = !isTurn;
	}
	public void setName(String n) {
		this.name=n;
	}
	
}