package project.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class BoardLoader {
	
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
	 * This method loads all boards from a directory
	 * into the requested ArrayList of boards.
	 * @param boards The ArrayList of boards to load into.
	 */
	public void loadBoards(ArrayList<Board> boards) {
		File[] dir = new File(getFilePath()).listFiles();
	    for (File file : dir) {
	    	if (file.isFile()) {
		    	Board newBoard = load(file);
		    	boards.add(newBoard);
	    	}
	    }
	}
	
	// This method returns a board from a file with the
	// board object. 
	private Board load(File file) {
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		Board newBoard = null;
		
		try {
			fis = new FileInputStream(file);
			ois = new ObjectInputStream(fis);
			newBoard = (Board) ois.readObject();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
		
		return newBoard;
	}
	
}
