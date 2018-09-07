/**
* Node.java contains the node class to be used by Map.java
* Each node represents points in graph to be used in the game.
*/

public class Node  implements Comparable<Node> {
    private int stepId; 
    private int row, col; // row and column of Node (2D array coordinates)
    private char type; 
    private boolean visited;
    private boolean pathVisited;
    private Node prevPathNode;
    private int distance;
    private boolean BFSVisited;
    

    /**
    * Constructor for creating a new node. This is invoked only in Map.java
    * When a new map is initialised a call to this constructor is made.
    */
   	public Node(int row, int col, char c, boolean visited) {
            this.row = row;
            this.col = col;
            this.type = c; // 'S' step, 'B' box, 'P' player, '.' empty 
            this.visited = visited;
            this.pathVisited = false;
            this.distance = 0;
            
    }
    
    @Override
    public int compareTo(Node step){
		if(this.stepId > step.getStepId())
				return -1;
		else
				return 1;
    }
    
    
    /**
    * GETTERS
    */

    public boolean getBFSPathVisited() {
        return this.BFSVisited;
    }

    public Node getPrevPathNode() {
        return this.prevPathNode;
    }

    public boolean getPathVisisted() {
            return this.pathVisited;
    }

    public char getType() {
            return type;
    }

    public int getRow() {
            return row;
    }

    public int getCol() {
            return col;
    }

    public int getDistance() {
        return this.distance;
    }

    public int getStepId() {
            return stepId;
    }

    /**
    * SETTERS
    */
    public void setPrevPathNode(Node n) {
        this.prevPathNode = n;
    }

    public void setBFSPathVisited() {
        this.BFSVisited = true;
    }
    
    public void setPathVisisted() {
            this.pathVisited = true;
    }

    public void setType(char type) {
            this.type = type;
    }
    public boolean isVisited() {
            return visited;
    }
    public void setVisited(boolean visited) {
            this.visited = visited;
    }

    public void setRow(int row) {
            this.row = row;
    }

    public void setCol(int col) {
            this.col = col;
    }

    public void setStepId(int stepId) {
            this.stepId = stepId;
    }
    
    public void setDistance(int distance) {
    	this.distance = distance;
    }

    
	
}

