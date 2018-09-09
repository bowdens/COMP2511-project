package project.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class BoardLoader {
	
	private String filePath;
	
	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public void loadBoardFromDir(ArrayList<Board> boards, DungeonCreator creator) {
		File[] dir = new File(getFilePath()).listFiles();
	    for (File file : dir) {
	    	if (file.isFile()) {
		    	Board newBoard = loadBoard(file, creator);
		    	boards.add(newBoard);
	    	}
	    }
	}
	
	private Board loadBoard(File file, DungeonCreator creator) {
		Scanner fileSc = null;
		try {
			fileSc = new Scanner(file);
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
		
		String boardName = fileSc.nextLine();
		int height = fileSc.nextInt();
		int width = fileSc.nextInt();
		Board newBoard = new Board(boardName, height, width);
		
		for (int i = 0; i < height ; i++) {
			for (int j = 0; j < width; j++) {
				if (fileSc.hasNextInt()) {
					int entity = fileSc.nextInt();
					creator.setBoardEntity(newBoard, entity, j, i);
				}
			}
		}
		
		fileSc.close();
		return null;
	}

}
