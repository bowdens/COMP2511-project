package project.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class BoardLoader {
	
	private String filePath;
	
	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public void loadBoards(ArrayList<Board> boards) {
		File[] dir = new File(getFilePath()).listFiles();
	    for (File file : dir) {
	    	if (file.isFile()) {
		    	Board newBoard = load(file);
		    	boards.add(newBoard);
	    	}
	    }
	}
	
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
