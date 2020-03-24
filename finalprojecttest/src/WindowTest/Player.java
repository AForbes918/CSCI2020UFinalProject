package WindowTest;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class Player{
	String name;
	int tttScore;
	int cfourScore;
	int checkersScore;
	int minesweeperWins;
	Player(){
		this.name="Player";
		this.tttScore=0;
		this.cfourScore=0;
		this.checkersScore=0;
		this.minesweeperWins=0;
	}
	Player(String n){
		this.name=n;
		this.tttScore=0;
		this.cfourScore=0;
		this.checkersScore=0;
		this.minesweeperWins=0;
	}
	
	public void tttPoint() {
		this.tttScore++;
	}
	public void cfourPoint() {
		this.cfourScore++;
	}
	public void checkersPoint() {
		this.checkersScore++;	
	}
	public void minesweeperPoint() {
		this.minesweeperWins++;
	}
	public void setName(String n) {
		this.name=n;
	}
	public void uploadToFile(File fileName) throws IOException {
		String line = "";
		String[] words=null;
		String newText="";
		Writer bw = null;
		try {
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			bw = new BufferedWriter(new FileWriter(fileName,true));
			while((line = br.readLine()) != null) {
				words=line.split(",");
				System.out.print(this.name);
				if(words[0].equals(this.name)) {
					line="";
				}
				newText+=line;
			}
			newText += this.name+ "," +this.tttScore+ "," +this.cfourScore+ "," +this.checkersScore+ "," +this.minesweeperWins+ "\n";
			bw.write(newText);
		}
		catch (IOException ex) {
	        ex.printStackTrace();
	    }
		finally {
			bw.flush();
	        bw.close();
		}
	}
	
	public boolean UploadFromFile(File fileName,String playerEntry) {
		String line = "";
		String[] words=null;
		try {
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			while((line = br.readLine()) != null) {
				words=line.split(",");
				if(words[0].equals(playerEntry)) {
					this.name=words[0];
					this.tttScore=Integer.parseInt(words[1]);
					this.cfourScore=Integer.parseInt(words[2]);
					this.checkersScore=Integer.parseInt(words[3]);
					this.minesweeperWins=Integer.parseInt(words[4]);
					return true;
				}
			}
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
}
