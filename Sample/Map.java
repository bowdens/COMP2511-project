/**
* Map.java generates a map via a 2D Array populated with Node class.
* This map is used by the front end for generation.
* 
* Below is the implementation plan
* 1. Create a random path
* 2. Add potential goals from path
* 3. Add potential boxes from goals
* 4. Put player on initial path start spot
* 5. Turn everything that has not been changed to a wall
* (6. Touch up map)
*
*/

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;

public class Map {
    private Node Graph[][]; // 2D array
    private ArrayList<Node> steps; // stores path of steps
    private ArrayList<Node> goals; // stores list of possible goals
    private ArrayList<Node> boxes = new ArrayList<Node>(); // stores our boxes
    private ArrayList<Node> choices = new ArrayList<Node>();
    private ArrayList<Node> adjNodes = new ArrayList<Node> ();
    private ArrayList<Node> boxPath = new ArrayList<Node>();
    private int totalBoxesAdded = 0;
    private int totalGoal; // the amount of goals in the map
    private int mapRowSize; // row size of the map
    private int mapColSize; // col size of the map
    private int prevDir = -1;  // 0 == Upwards, 1 == Downwards, 2 == Left, 3 == Right
    private int backCount = 1; // count from the back of path
    private int startX = -1; // starting x coordinate
    private int startY = -1; // starting y coordinate
    private String difficulty; //difficulty chosen
    private Node destination = null;
    

    /**
    * Constructor for creating a map. This is invoked upon once whenever an
    * instance of a map has to be created.
    */
    public Map(int i, int j, String level) {

            if (level.equalsIgnoreCase("e"))
                    this.totalGoal = 3;
            else if (level.equalsIgnoreCase("m"))
                    this.totalGoal = 4;
            else if (level.equalsIgnoreCase("h"))
                    this.totalGoal = 5;

            // initially all values are open
            Node[][] g = new Node[i][j];
            for (i = 0; i < g.length; i++) {
                    for (j = 0; j < g.length; j++) {
                            g[i][j] = new Node(i , j,'.',false);
                    }
            }
            this.Graph = g;
            this.steps = new ArrayList<Node>();
            this.goals = new ArrayList<Node>();
            this.boxes = new ArrayList<Node>();
            this.mapRowSize = i;
            this.mapColSize = j;
            this.difficulty = level; 
    }

    /**
    * Generates a random sequence of steps
    */
    public void generatePath(){
      
        // Reset
        this.steps.clear();
        this.startX = -1;
        this.startY = -1;


        int id = 1;
        Random ran = new Random();
        

        // Randomly generate starting point
        int i = ran.nextInt(mapRowSize-1); 
        int j = ran.nextInt(mapColSize -1); 
        
        // Stores our starting point
        this.startX = i;
        this.startY = j;
        
        // Sets ID and Step for the initial step only
        Graph[i][j].setType('S');
        Graph[i][j].setStepId(1);
        Graph[i][j].setVisited(true);
        this.steps.add(Graph[i][j]);
        
        int totalStep= Graph.length*5; // change amount of steps
        
        // Adds path by choosing randomly from a set of possible paths to go to
        while(totalStep >0){
            choices.clear();
            choices = getPossibleChoices(i,j);
            //randomly select an adjNode
            int choice =ran.nextInt(choices.size());
            Node next = choices.get(choice);
            next.setType('S');
            // Cannot replace stepId 1 and id cant be greater than number of steps
            if(id < Graph.length*5 && next.getStepId() != 1)
                    next.setStepId(++id);
            next.setVisited(true);
            //move to next
            i = next.getRow();
            j = next.getCol();
            this.steps.add(next);
            totalStep--;
                
        }
    }
    
    
    
    /**
     * Adds Adjacent nodes to a list. If the adjacent node has not been visited
     * then we add it multiple times (higher probablity for choice)
     * */
    public ArrayList<Node> getPossibleChoices(int i , int j){
        if((j-1) >= 0 && Graph[i][j-1] !=null){
            if(Graph[i][j-1].isVisited()) {
                    choices.add(Graph[i][j-1]);
            } else {
                    addNTimes(Graph[i][j-1], 20);
            }
        } 
        if((j+1 <= Graph[i].length -1)&& Graph[i][j+1] !=null ) {
            if(Graph[i][j+1].isVisited()) {
                    choices.add(Graph[i][j+1]);
            } else{
                    addNTimes(Graph[i][j+1], 20);
            }
        } 
        if((i-1) >= 0 && Graph[i-1][j] !=null ) {
            if(Graph[i-1][j].isVisited()) {
                    choices.add(Graph[i-1][j]);
            } else {
                    addNTimes(Graph[i-1][j], 20);
            }
        } 
        if((i+1 <= Graph[i].length - 1) && Graph[i+1][j] !=null) {
            if(Graph[i+1][j].isVisited())
                    choices.add(Graph[i+1][j]);
            else{
                    addNTimes(Graph[i+1][j], 20);
            }
        }
        return choices;
    }
    

    /**
    * Adds goals. A path is assumed to have been made first before invoking the 
    * add Gs function. Since this function uses steps to determine where the goals go
    */

    public void addGs() {

        int i=0;
        Node prev = this.steps.get(i);
        Node toInsert = this.steps.remove(0);
        Node curr;
        Node goal;
        int rowLength = this.Graph.length;
        int columnLength = this.Graph[0].length;
         

        for (Node p : this.steps) {
            curr = p;

            // if step goes right
            if (prev.getCol() < curr.getCol()) {

                // check if there is already a step in the node to the right
                if ((curr.getCol() + 1 < rowLength) && (this.Graph[curr.getRow()][curr.getCol()+ 1].getType() != 'S')){
                        goal = this.Graph[curr.getRow()][curr.getCol()+ 1];
                        this.goals.add(this.Graph[curr.getRow()][curr.getCol()+ 1]);
                }
            }

            // if step moved to the left
            if (prev.getCol() > curr.getCol()) {

                if (curr.getCol() - 1 > 0 && this.Graph[curr.getRow()][curr.getCol() - 1].getType() != 'S' ) {
                        goal = this.Graph[curr.getRow()][curr.getCol() - 1];
                        this.goals.add(goal);
                       
                }
            }

            // if step goes down
            if (prev.getRow() < curr.getRow()) {

                if (curr.getRow() + 1 < columnLength
                                && (this.Graph[curr.getRow() + 1][curr.getCol()].getType()) != 'S') {
                        goal=this.Graph[curr.getRow() + 1][curr.getCol()];
                        this.goals.add(goal);
                }
            }

             // if step goes up
            if (prev.getRow() > curr.getRow()) {

                if (curr.getRow() - 1 > 0 && this.Graph[curr.getRow() - 1][curr.getCol()].getType() != 'S') {
                        
                    
                        goal =this.Graph[curr.getRow() - 1][curr.getCol()];
                        this.goals.add(goal);    
                }
            }
            // beging to scan through the next steps
            prev = curr;
        }
        
        this.steps.add(0,toInsert);
       
        // this.goals contains all the potential goals we can put in
        ArrayList <Node> afterScanned = scanGoals();
        this.goals.clear();
        this.goals.addAll(afterScanned);
}
    

    /**
    * Helper function for adding goals. Used within the AddGs () only.
    * Chooses the specific goals to be added to the map and returns that
    * list back
    */
    public ArrayList<Node> scanGoals() {
        
        ArrayList<Node> actualGoalLocation = new ArrayList<Node>();
        
        // scanning through our goals BY INDEX
        for (int index = 0 ; index < this.goals.size(); index++) {
                
            // add 3 goals if easy
            if (this.difficulty.equals("e")) {
                if (index == 0 ) {
                    actualGoalLocation.add(this.goals.get(index)); 
                }
                
                if (index == this.goals.size() - 1) {
                    actualGoalLocation.add(this.goals.get(index)); 
                }

                
                // this area is where i would incorporate difficulty if i wanted to, but maybe jst stick with 3
                if (index == this.goals.size() - this.mapRowSize/2 ) {
                    actualGoalLocation.add(this.goals.get(index));
                }
            }
            
            // add 4 goals if medium
            if (this.difficulty.equals("m")) {

                if (index == 0 ) {
                    actualGoalLocation.add(this.goals.get(index)); 
                }

                if (index == this.goals.size() - 1) {
                    actualGoalLocation.add(this.goals.get(index)); 
                }

                if (index == this.goals.size() - this.mapRowSize/3 ) {
                    actualGoalLocation.add(this.goals.get(index));
                }

                if (index == this.goals.size() - this.mapRowSize/3 + 2) {
                    actualGoalLocation.add(this.goals.get(index));
                }
            }

            // add 5 goals if hard
            if (this.difficulty.equals('h')) {

                    if (index == 0 ) {
                        actualGoalLocation.add(this.goals.get(index)); 
                    }

                    if (index == this.goals.size() - 1) {
                        actualGoalLocation.add(this.goals.get(index)); 
                    }

                    if (index == this.goals.size() - this.mapRowSize/2 - 1 ) {
                        actualGoalLocation.add(this.goals.get(index));
                    }

                    if (index == this.goals.size() - this.mapRowSize/3 + 1) {
                        actualGoalLocation.add(this.goals.get(index));
                    }

                    if (index == this.goals.size() - this.mapRowSize/2  ) {
                        actualGoalLocation.add(this.goals.get(index));
                     }
            }
        
        

            // set the goals
            for (Node a: actualGoalLocation) {
                a.setType('G');
            }   
        }
            return actualGoalLocation;      
    }
    

    /**
    * Adds the boxes by implementing a BFS path system to determine where
    * the boxes should be placed
    * This function is invoked after generatePath() and addGs() are called.
    */
    public void addBoxes(){ 
            
        // copy of steps
        ArrayList<Node> steps = new ArrayList<Node>(); 
        steps.addAll(this.steps);


        // For each goal
        for(Node g: this.goals) {
                                        
            // gets adjacent nodes of the goal and puts it into the priority queue 
            adjNodes = getAdjSteps(g.getRow(), g.getCol());

            Node highestAdj = getHighestAdj(adjNodes);

            // intialising prevDir
            if(g != null && highestAdj != null){
                if(g.getRow() > highestAdj.getRow()) //upwards
                    this.prevDir = 0;
                else if(g.getRow() < highestAdj.getRow()) // downwards
                    this.prevDir = 1;
                else if (g.getCol() > highestAdj.getCol()) // left
                    this.prevDir = 2;
                else if (g.getCol() < highestAdj.getCol()) // right
                    this.prevDir = 3;
            }
                

            adjNodes.clear();
            destination = getStep(1); // returns id of step 1
            
            // Apply BFS to find a box path
            BFS(highestAdj, destination); // highest id adjacent to node to node 1
            // hardcoded for the infinite loop issue
            int i = 0;
            while ( destination.getPrevPathNode() != null  ) {
                
                if (i>10) break;
                destination = destination.getPrevPathNode(); 
                boxPath.add(destination); // due to prevNode going back and forth
              
                // lets limit our boxpath to 10
                i++;
            }
            
            
            Collections.reverse(boxPath); // last elem is destination

            boolean first = true;
            // for(Node p: boxPath) {
            //     if(first) {
            //         System.out.println("BoxPath: ");
            //         first = false;
            //     }
                
            //     System.out.print("id: "  +p.getStepId()+" [" + p.getRow()+"]"+"["+p.getCol()+ "] --> "); 
                         
            // }
            // System.out.println();
            // change the node of to B
            highestAdj = null;

            ArrayList<Node> wallsAround = new ArrayList<Node>();
             // wall count is resetted here
            // only add if there isnt already a box there otherwise keep backtracking the boxPath until a free spot
            int j = 0;
            while(backCount <= boxPath.size()) {
                
                if (j > 10) break;
                j++;
                //System.out.println(backCount);
                int boxRow = boxPath.get(boxPath.size() - backCount).getRow(); // gets the row index of the last position in box path
                int boxCol = boxPath.get(boxPath.size() - backCount).getCol(); // gets the col index of the last position in box path
                int wallCount = 0;
                // get adjacent steps of the box path
                ArrayList<Node> a = getAdjNodes(boxRow, boxCol);
                    

                    for(Node n: a) {
                        // if the node is a wall (which in our case would be a dot)
                        if (n.getType() == '.') {
                            if((wallCount >= 2 &&  a.size() >= 3 )) { // 4 adj nodes and 2 walls
                                wallsAround.add(n);
                                break;
                            }
                             wallCount++;
                        }
                     }
                
                    for (Node w: wallsAround) {
                        w.setType('E');
                    }
                    wallsAround.clear();
                    wallCount = 0;

                // checker to prevent arrayindexoutofbounds
                if (boxRow + 1 == this.mapRowSize || boxRow  == 0) continue;
                if (boxCol + 1 == this.mapColSize || boxCol == 0) continue;
                
                
                
                if(this.Graph[boxRow][boxCol].getType() != 'B'  ) {   
                    // System.out.println("BOX HAS BEEN SET");
                    this.boxes.add(this.Graph[boxRow][boxCol]);
                    this.Graph[boxRow][boxCol].setType('B');
                    totalBoxesAdded++;
                    backCount++; // an attempt to separate boxes
                    break;
                }
                backCount++;
            }
        }
        
        if (this.difficulty.equals("e")) {
            if (totalBoxesAdded != 3) {
            addBoxesAlternative(3 - totalBoxesAdded);
            }  
        } 

        if (this.difficulty.equals("m")) {
            if (totalBoxesAdded != 4) {
            addBoxesAlternative(4 - totalBoxesAdded);
            }  
        }


        if (this.difficulty.equals("h")) {
            if (totalBoxesAdded != 5) {
            addBoxesAlternative(5 - totalBoxesAdded);
            }  
        }   
     }


    /**
    * A helper function for boxes which adds the boxes at a random location
    * This is a hard coded version of adding a box, in situations when
    * our box path was not being created.
    */
    public void addBoxesAlternative(int boxesNeeded) {
        
        ArrayList<Node> potentialBoxSpots = new ArrayList<Node> ();
        
        int num = 0;
        // allow the addition of boxes when it is completly surrounded by steps
        
        for (Node s:this.steps) {
            if (s.getRow() + 1 == this.mapRowSize || s.getRow()  == 0) continue;
            if (s.getCol() + 1 == this.mapColSize || s.getCol() == 0) continue;
            
            if (this.Graph[s.getRow() + 1][s.getCol()].getType() == 'S' && 
                this.Graph[s.getRow() -1][s.getCol()].getType() == 'S'  &&
                this.Graph[s.getRow()][s.getCol() + 1].getType() == 'S' &&
                this.Graph[s.getRow()][s.getCol() - 1].getType() == 'S') {
                
                potentialBoxSpots.add(s);
            }   
        }
        
        // for (Node p: potentialBoxSpots) {
        //     System.out.println(p.getRow() + " " + p.getCol());
        // }
        
        int index;
        Random ran = new Random();
        


        while (num < boxesNeeded) {
            index = ran.nextInt(potentialBoxSpots.size());           
            // our box now
            Node n = potentialBoxSpots.get(index);
            
            this.boxes.add(n);
            this.Graph[n.getRow()][n.getCol()].setType('B'); 
            potentialBoxSpots.remove(n);
            num++;
        }
        
        
    }


    /**
    * BFS box path implementation, creates a path of all possible box locations
    * If the destination is reachable (should always)
    * our current Node will now point at the destination
    */
    public void BFS(Node s, Node d) {

        // Mark all the vertices as not visited(By default set
        // as false)
        boolean visited[][] = new boolean[this.mapRowSize][this.mapColSize];
        
        // wanna see what happens when u reset prev here
        for (int i = 0; i < this.mapRowSize; i++) {
            for (int j = 0; j< this.mapColSize; j++) {
                this.Graph[i][j].setPrevPathNode(null);
            }
        }
        
        // Create a queue for BFS
        LinkedList<Node> queue = new LinkedList<Node>();
        // Mark the current node as visited and enqueue it
        visited[s.getRow()][s.getCol()]=true;
        queue.add(s);
        // 'i' will be used to get all adjacent vertices of a vertex
        Iterator<Node> i;

        while(queue.size() != 0) {
            // Dequeue a vertex from queue and print it
            Node temp = queue.poll();
 
            // Get all adjacent vertices of the dequeued vertex s
            // If a adjacent has not been visited, then mark it
            // visited and enqueue it
            ArrayList<Node> adjStepsCopy = new ArrayList<Node>();
            adjStepsCopy.addAll(getAdjSteps(temp.getRow(), temp.getCol()));
            for(Iterator<Node> li = adjStepsCopy.iterator(); li.hasNext();)
            {
                Node n = li.next();
                // If this adjacent node is the destination node,
                // then return true
                if (temp.equals(d))
                    return;
 
                // Else, continue to do BFS
                if (!visited[n.getRow()][n.getCol()])
                {
                    // Conditions for not adding to queue
                    if (n.getStepId() > temp.getStepId()) {
                        continue;
                    
                    } else if (n.getRow() == this.mapRowSize-1  || n.getRow() == 0  ) { // if we are on the vertical edges
                        continue;
                    
                   
                    } else if(n.getCol() == this.mapColSize-1 || n.getCol() == 0) { // if we are on horizontal edges
                        continue;
                    
                    } else if (n.getType() == '.' ) {
                        continue;
                    }
                   
                    
                    // NEIGHBOURS
                    ArrayList<Node> adjCurrNodes = getAdjSteps(n.getRow(), n.getCol());

                    //ArrayList<Node> adjCurrNodes = getAdjSteps(n.getRow(), n.getCol());
                    boolean adjWall = false;
                    
                    
                    for(Node adj: adjCurrNodes) {
                        // Conditions for not adding to queue
                        if (adj.getType() == '.' ) { // if we are on the vertical edges
                            adjWall = true;
                            break;
                        }
                    }
                    
                    if(adjWall)
                        continue; 

                    int newDir = -1;
                    // gets new direction 
                    if(getHighestAdj(adjCurrNodes).getRow() > n.getRow()) //upwards
                        newDir = 0;
                    else if(getHighestAdj(adjCurrNodes).getRow() < n.getRow()) // downwards
                        newDir = 1;
                    else if (getHighestAdj(adjCurrNodes).getCol() > n.getCol()) // left
                        newDir = 2;
                    else if (getHighestAdj(adjCurrNodes).getCol() < n.getCol()) // right
                        newDir = 3;                
            
                    // if same direction check if there is a wall in that direction
                    if(newDir == this.prevDir){
                        switch (newDir) {
                            case 0: if(getHighestAdj(adjCurrNodes).getRow() -1 == '.' )
                                    continue;
                                    break;
                            case 1: if(getHighestAdj(adjCurrNodes).getRow() + 1 == '.')
                                    continue;
                                    break;
                            case 2: if(getHighestAdj(adjCurrNodes).getCol() - 1 == '.')
                                    continue;
                                    break;
                            case 3: if(getHighestAdj(adjCurrNodes).getCol() + 1 == '.')
                                    continue;
                                    break;
                        }
                    }
                            visited[n.getRow()][n.getCol()] = true;
                            n.setPrevPathNode(temp);
                            queue.add(n);
                }
            }
        }
        return;
    }


    /**
    * A function which retouches the maps in areas where a box might be blocked.
    * Increases the probability of not creating a map which has no solutions.
    */
    public void heightenProbability() {

        // removes adjacent walls around all boxes
        for (Node n : this.boxes) {
            
            // i don't want to get rid of walls on the side
            if (n.getRow() == this.mapRowSize || n.getRow() == 0 || n.getCol() == this.mapColSize || n.getCol() == 0) {
                continue;
            }
                //change neighbours from wall to S
                for (Node r: this.getAdjNodes(n.getRow(), n.getCol())) {
                    if (this.Graph[r.getRow()][r.getCol()].getType() == '.') 
                        this.Graph[r.getRow()][r.getCol()].setType('S');
                }
        }

        for (Node n: this.goals) {

            if (n.getRow() == this.mapRowSize || n.getRow() == 0 || n.getCol() == this.mapColSize || n.getCol() == 0);
             //change neighbours from wall to S
                for (Node t: this.getAdjNodes(n.getRow(), n.getCol())) {
                    if (this.Graph[t.getRow()][t.getCol()].getType() == '.') 
                        this.Graph[t.getRow()][t.getCol()].setType('S');
                }
        }




        //removes junctions (places where boxes get trapped)
        // scanning column by column
        for (int i = 0; i < this.mapRowSize; i++) {

            for (int j = 0; j< this.mapColSize; j++) {

                // dont do anything if its on an edge
                if (j == 0 || j == this.mapRowSize || j == 1 || j == this.mapRowSize -1 
                    || i ==0  || i == 1 || i == this.mapRowSize || i == this.mapRowSize - 1) continue; 


                    // removes bottom left and top right
                    // other cases can be done, but it will remove too much
                    if (this.Graph[i-1][j-1].getType() == '.' && this.Graph[i+1][j+1].getType() == '.') {
                        if (i - 1 > 0 || j -1 > 0 || i + 1 < this.mapRowSize || j+ 1 < this.mapColSize) {
                            if (this.Graph[i-1][j-1].getType() == '.') {
                                this.Graph[i-1][j-1].setType('S');
                            } 
                            
                        }
                    }
            }
        }
    }







    /**
    * HELPER FUNCTIONS
    */

    /**
    * Graph Getter
    */
    public Node[][] getGraph() {
        return this.Graph;
    }

    /**
    * The call that will be invoked from the front end when creating a map
    */
    public void generateLevel() {
        generatePath();
        addGs();
        addBoxes();
        heightenProbability();
    }


  /**
     * This function returns all the adjacent nodes of the current node
     * in an ArrayList. 
     */
    public ArrayList<Node> getAdjNodes(int i , int j){
        ArrayList<Node> adjNodes = new ArrayList<Node>();
        if((j-1) >= 0 && Graph[i][j-1] !=null){ 
                adjNodes.add(Graph[i][j-1]);
        } 
        if((j+1 <= Graph[i].length -1)&& Graph[i][j+1] !=null ) {
                adjNodes.add(Graph[i][j+1]);
        }

        if((i-1) >= 0 && Graph[i-1][j] !=null ) {
                adjNodes.add(Graph[i-1][j]);
        }

        if((i+1 <= Graph[i].length - 1) && Graph[i+1][j] !=null) {
                adjNodes.add(Graph[i+1][j]);
        }

        return adjNodes;
    }
    
    /**
     * This function returns all the adjacent nodes which contain a 
     * step.
     */
    public ArrayList<Node> getAdjSteps(int i , int j){
        ArrayList<Node> adjSteps = new ArrayList<Node>();
        if((j-1) >= 0 && Graph[i][j-1] !=null){
            if(Graph[i][j-1].getType() == 'S') 
                adjSteps.add(Graph[i][j-1]);
        } 
        if((j+1 <= Graph[i].length -1)&& Graph[i][j+1] !=null ) {
            if(Graph[i][j+1].getType() == 'S') 
                adjSteps.add(Graph[i][j+1]);
        }

        if((i-1) >= 0 && Graph[i-1][j] !=null ) {
            if(Graph[i-1][j].getType() == 'S') 
                adjSteps.add(Graph[i-1][j]);
        }

        if((i+1 <= Graph[i].length - 1) && Graph[i+1][j] !=null) {
            if(Graph[i+1][j].getType() == 'S')
                adjSteps.add(Graph[i+1][j]);
        }

        return adjSteps;
    }



    /**
    * gets the highest adjacent node else returns null
    */
    public Node getHighestAdj(ArrayList<Node> adjNodes) {
        Node highestAdj = null;
        // goes through the queue and chooses the highest step id
        Node prev = adjNodes.get(0);
        for(Node n : adjNodes){
            if(n.getStepId() >= prev.getStepId())
                highestAdj = n; 
            prev = n;
        }

        return highestAdj;
    }
    
    /**
    * Returns the step from steps list given its stepId
    */
    public Node getStep(int id) {
        Node ret = null;
        for(Node n: this.steps) {
            if(n.getStepId() == id)
                ret = n;
        }
        return ret;
    }


    /**
     * print goal list
     */
    public void printGoals(){
        for(Node n: goals){
                System.out.println("["+n.getRow()+"]["+n.getCol()+"]");
        }
    }
    
    /**
    * Prints out the graph
    */
    public void printGraph(){
        for(int i=0;i<Graph.length;i++){
            for(int j=0;j<Graph[0].length;j++){
                if(Graph[i][j].getType() == 'S' && Graph[i][j].getStepId() > 9)
                    System.out.print("  "+Graph[i][j].getStepId()+ " ");
                else if(Graph[i][j].getType() == 'S' && Graph[i][j].getStepId() <= 9)
                    System.out.print("  "+Graph[i][j].getStepId()+ "  ");
                else
                    System.out.print("  "+Graph[i][j].getType()+ "  ");
            }
            System.out.println();
        }
    }


    /**
     * Adds to arraylist n many times
     * */
    public void addNTimes(Node n, int times) {
        int i = 0;
        while( i < times){
                this.choices.add(n);
                i++;
        }
    }
    
    


}
