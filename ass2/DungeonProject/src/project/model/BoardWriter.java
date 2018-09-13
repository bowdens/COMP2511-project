package project.model;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class BoardWriter {
	
	private String filePath;
	
	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
	public void writeBoard(Board board) {
		File outFile = new File(getFilePath() + "/" + board.getName() + ".txt");
		PrintWriter writer = null;
		
		try {
			writer = new PrintWriter(outFile);
		} catch (IOException e) {
			System.err.println("Caught IOException: " +  e.getMessage());
		}
		
		writer.println(board.getName());
		writer.println(board.getHeight());
		writer.println(board.getWidth());
		
		for (int i = 0; i < board.getHeight(); i++) {
			for (int j = 0; j < board.getWidth(); j++) {
				if (i == (board.getWidth() - 1)) {
					writer.print(board.getEntityAt(j, i));
				} else {
					writer.print(board.getEntityAt(j, i) + " ");					
				}
			}
			writer.println();
		}
	}

}
