import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class DuplicateCounter {  
	private Integer wordCounter;
	private Map<String, Integer> map;
	
	public DuplicateCounter() {
		this.wordCounter = 0;
		this.map = new HashMap<>();
		}
  
	public void count(String dataFile) {
		Scanner fileReader;
		try {
			fileReader = new Scanner(new File(dataFile));
			while(fileReader.hasNextLine()) {
				String line = fileReader.nextLine().trim();
				String[] data = line.split("[\\W]+");
				for(String word : data){
					this.wordCounter = map.get(word);
					this.wordCounter = (this.wordCounter == null) ? 1 : ++this.wordCounter;
					map.put(word, this.wordCounter);
				}
			}
			fileReader.close();
		}
		catch(FileNotFoundException fnfe){
			System.out.println("File cannot be found!");
			System.exit(1);
		}
	}
  
	public void write(String outputFile) {
		FileWriter filew;
		PrintWriter printw;
		try {
			filew = new FileWriter(new File(outputFile));
			printw = new PrintWriter(filew);
			for(Map.Entry<String, Integer> entry : map.entrySet()) {
				printw.write(entry.getKey() + " occurs " + entry.getValue() + " times" + System.lineSeparator());
			}
			System.out.println("Map data written to file");
			printw.flush();
			filew.close();
			printw.close();
		} 
		catch (IOException ex) {
			System.out.println("Error in writing");
			System.exit(1);
		}
	}
}