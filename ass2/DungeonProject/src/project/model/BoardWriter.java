package project.model;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class BoardWriter {
	
	private String filePath;
	
	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
	public void writeBoard(Board board) {
		File outFile = new File(getFilePath() 
				+ "/" 
				+ board.getName() 
				+ board.getBoardID() + ".txt");
		
		FileOutputStream fout = null;
		ObjectOutputStream oos = null;
				
		try {
			fout = new FileOutputStream(outFile);
			oos = new ObjectOutputStream(fout);
			oos.writeObject(board);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
	}

}