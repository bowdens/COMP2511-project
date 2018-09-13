package project.model;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class BoardWriter {
	
	private String filePath;
	
	/**
	 * @return the filePath
	 */
	public String getFilePath() {
		return filePath;
	}


	/**
	 * @param filePath the filePath to set
	 */
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}


	/**
	 * This method writes a board to a file with a name 
	 * based on the board's name and ID.
	 * @param board
	 */
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