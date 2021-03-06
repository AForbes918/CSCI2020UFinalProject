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
	int scores[]=new int[4];
	Player(){
		this.name="Player";
		for(int i=0;i<4;i++) {
			this.scores[i]=0;
		}
	}
	Player(String n){
		for(int i=0;i<4;i++) {
			this.scores[i]=0;
		}
		this.name=n;
	}
	
	public void tttPoint() {
		this.scores[0]++;
	} // 4 methods to add to players win in each game if they win
	public void cfourPoint() {
		this.scores[1]++;
	}
	public void checkersPoint() {
		this.scores[2]++;	
	}
	public void minesweeperPoint() {
		this.scores[3]++;
	}

	public void setName(String n) {
		this.name=n;
	} // setting name of player

	public void uploadToFile(File fileName,Player p1, Player p2) throws IOException { // this code will simply write to the
		// database.csv file to make any changes if play wins a game
		String line = "";
		String[] words=null;
		String newText="";
		BufferedWriter bw = null;
		try {
			BufferedReader br = new BufferedReader(new FileReader(fileName)); // bufferreader to read file
			while((line = br.readLine()) != null) { // read till no lines left
				words=line.split(","); // comma dilimeter
				if(p2==null) {
					if(words[0].equals(p1.name)) {
						newText+="";
					}
					else {
						newText+=line+"\n";
					}
				}
				else {
					if(words[0].equals(p1.name)||words[0].equals(p2.name)) {
						newText+="";
					}
					else {
						newText+=line+"\n";
					}
				}
				
			}
			newText += p1.name;
			for(int i=0;i<4;i++) {
				newText+=","+p1.scores[i]; // editing scores
			}
			newText+="\n";
			if(p2!=null) {
				newText += p2.name;
				for(int i=0;i<4;i++) {
					newText +=","+p2.scores[i];
				}
			}		
			newText+="\n";
//			System.out.print(newText);
			bw = new BufferedWriter(new FileWriter(fileName,false));
			bw.write(newText); // writing new scores to csv file
		}
		catch (IOException ex) {
	        ex.printStackTrace();
	    }
		finally {
			bw.flush(); // closing and flushing buffer reader
			bw.close();
		}
	}
	
	public boolean UploadFromFile(File fileName,String playerEntry) { // same logic as UploadToFile, except now reading instead of writing
		// to get scores of the players previous games
		String line = "";
		String[] words=null;
		try {
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			while((line = br.readLine()) != null) {
				words=line.split(",");
				if(words[0].equals(playerEntry)) {
					this.name=words[0];
					for(int i=0;i<4;i++) {
						this.scores[i]=Integer.parseInt(words[i+1]);
					}
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
