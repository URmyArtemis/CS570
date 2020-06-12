package Maze;
import java.util.*;
/**
 * Class that solves maze problems with backtracking.
 * @author Koffman and Wolfgang
 **/
public class Maze implements GridColors {

    /** The maze */
    private TwoDimGrid maze;

    public Maze(TwoDimGrid m) {
        maze = m;
    }

    /** Wrapper method. */
    public boolean findMazePath() {
        return findMazePath(0, 0); // (0, 0) is the start point.
    }

    /**
     * Attempts to find a path through point (x, y).
     * @pre Possible path cells are in BACKGROUND color;
     *      barrier cells are in ABNORMAL color.
     * @post If a path is found, all cells on it are set to the
     *       PATH color; all cells that were visited but are
     *       not on the path are in the TEMPORARY color.
     * @param x The x-coordinate of current point
     * @param y The y-coordinate of current point
     * @return If a path through (x, y) is found, true;
     *         otherwise, false
     */
    public boolean findMazePath(int x, int y) {
    	if (x < 0 || y < 0 || x >= maze.getNCols() || y >= maze.getNRows()) {
    		return false; // Cell is out of bounds.
    	} else if (!maze.getColor(x, y).equals(NON_BACKGROUND)) {
    		return false; // Cell is on barrier or dead end.
    	} else if (x == maze.getNCols() - 1 && y == maze.getNRows() - 1) {
            maze.recolor(x, y, PATH); // Cell is on path
            return true; // and is maze exit.
    	} else{ // Recursive case.
    			// Attempt to find a path from each neighbor. 
    			// Tentatively mark cell as on path.
            maze.recolor(x, y, PATH); // re-color (x,y) to PATH
            if (findMazePath(x - 1, y) || findMazePath(x + 1, y) || findMazePath(x, y + 1) || findMazePath(x, y -1)) {
                return true;
            } else{
                maze.recolor(x, y, TEMPORARY); // Dead end.
                return false;
            }
        }
    }
    /**
     * 
     * @param x,y :(x,y) are the coordinates currently being visited
     * @param result :result is the list of successful paths recorded up to now
     * @param trace :trace is the trace of the current path being explored
     */
    public void findMazePathStackBased(int x, int y, ArrayList<ArrayList<PairInt>> result, Stack<PairInt> trace) {
    	if (x < 0 || y < 0 || x >= maze.getNCols() || y >= maze.getNRows()) {
    		return; // Cell is out of bounds.
    	} else if (!maze.getColor(x, y).equals(NON_BACKGROUND)) {
    		return; // Cell is on barrier or dead end. 
    	} else if (x == maze.getNCols() - 1 && y == maze.getNRows() - 1) { 
    		// if we find 1 exit, push this exit into trace & add result
    		trace.push(new PairInt(x, y)); 
    		ArrayList<PairInt> recursion = new ArrayList<PairInt>();
    		recursion.addAll(trace); // add all to list recursion from trace
    		result.add(recursion);
    		trace.pop(); // Once this point visited we need to pop from stack
    		maze.recolor(x, y, NON_BACKGROUND); // re-color to Non_background for new visit
    		return;
    	} else { 	// Recursive case.
					// Attempt to find a path from each neighbor. 
					// Tentatively mark cell as on path.
    		maze.recolor(x, y, TEMPORARY); // re-color (x,y) to PATH & Cell is on PATH
    		trace.push(new PairInt(x, y)); // push(x,y) into trace
    		findMazePathStackBased(x + 1, y, result, trace);
    		findMazePathStackBased(x - 1, y, result, trace);
    		findMazePathStackBased(x, y + 1, result, trace);
    		findMazePathStackBased(x, y - 1, result, trace);
    		
    		//In the recursive call, after returning from visiting the neighbors, 
    		//you should remove the mark (by coloring the cell with the NON_BACKGROUND color) 
    		//so that this cell may be visited again after backtracking.
    		maze.recolor(x, y, NON_BACKGROUND);
    		trace.pop();
    		return;
    	}
    }
/*
wrapper method
and then defining findAllMazePaths as:
*/
    public ArrayList<ArrayList<PairInt>> findAllMazePaths(int x, int y) {
    	ArrayList<ArrayList<PairInt>> result = new ArrayList<>();
    	Stack<PairInt> trace = new Stack<>();
    	findMazePathStackBased(0, 0, result, trace);
    	return result;
    }
    /*
	Adapt boolean Maze.findMazePath() so that it returns the shortest path in the list of paths.
	The resulting method should be called:
     */
    public ArrayList<PairInt> findMazePathMin(int x, int y) {
    	int index = 0;
    	int count[];
    	int min;
    	// find all possible paths first and select the minimal
    	ArrayList<ArrayList<PairInt>> allPaths;
    	allPaths = findAllMazePaths(x, y);
    	if (allPaths.size() == 0){
    		return null;
    	}else {	
    		count = new int[allPaths.size()];
    		for (int i = 0; i < allPaths.size(); i++) {
    			count[i] = allPaths.get(i).size();
    		}
    		// default minimal is the first
    		min = count[0];
    		for (int i = 1; i < count.length; i++) {
    			if (count[i] < min) {
    			min = count[i];
    			index = i;
    			}	
    		}
    	}
    	// return the shortest path  
    	return allPaths.get(index);
    }
    /*<exercise chapter="5" section="6" type="programming" number="2">*/
    public void resetTemp() {
        maze.recolor(TEMPORARY, BACKGROUND);
    }
    /*</exercise>*/

    /*<exercise chapter="5" section="6" type="programming" number="3">*/
    public void restore() {
        resetTemp();
        maze.recolor(PATH, BACKGROUND);
        maze.recolor(NON_BACKGROUND, BACKGROUND);
    }
    /*</exercise>*/
}
/*</listing>*/




