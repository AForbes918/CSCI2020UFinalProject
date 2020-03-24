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
	}
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
	}
	public void uploadToFile(File fileName,Player p1, Player p2) throws IOException {
		String line = "";
		String[] words=null;
		String newText="";
		BufferedWriter bw = null;
		try {
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			while((line = br.readLine()) != null) {
				words=line.split(",");
				if(words[0].equals(p1.name)||words[0].equals(p2.name)) {
					newText+="";
				}
				else {
					newText+=line+"\n";
				}

				
			}
			newText += p1.name;
			for(int i=0;i<4;i++) {
				newText+=","+p1.scores[i];
			}
			newText+="\n";
			
			newText += p2.name;
			for(int i=0;i<4;i++) {
				newText +=","+p2.scores[i];
			}
			newText+="\n";
			bw = new BufferedWriter(new FileWriter(fileName,false));
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
