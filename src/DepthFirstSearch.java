import java.util.ArrayList;

/**
 * use DFS to find the path of finding a maze from start to end.
 * use BFS's back track to find the shortest path of a maze from start to end.
 */
public class DepthFirstSearch {
	private int counter; //counter of each Cell's number by visited order
	private Cell[][] dfsMaze; // each maze room is considered as a Cell
	private int size; // the dimension of the maze
	private CellColor white = CellColor.White; // CellColor white
	private CellColor grey = CellColor.Grey; // CellColor Grey
	private CellColor black = CellColor.Black; // CellColor Black

	/**
	 * constructor of DepthFirstSearch
	 * @param maze Maze object being passed in
	 */
	public DepthFirstSearch(Maze maze) {
		dfsMaze = maze.getMaze();
		counter = -1;
		size = maze.getSize();
	}

	/**
	 * the number of Cell c being visited
	 * @param c a Cell c that wants to set the number of the path
	 */
	private void counter(Cell c) {
		if (counter == 9) {
			counter = 0;
			c.setCounterDFS(0);
		} else {
			counter++;
			c.setCounterDFS(counter);
		}
	}

	/**
	 * set each Cell's color to be white and call other functions to find a path and the shortest path
	 */
	public void dfs() {
		// Initialize each vertex of color white.
		for (int i = 0; i < dfsMaze.length; i++) {
			for (int j = 0; j < dfsMaze[i].length; j++) {
				dfsMaze[i][j].setColor(white);
			}
		}
		//Go through the maze with dfs.
		solveDFS(dfsMaze[0][0]);
		//Find the path from starting cell to ending cell.
		TheDFSPath();
		//Print the dfs path
		printDFSPath();
	}

    /**
     * get the result of the path of DFS with number
     * @return the 2D array of the path order
     */
    public Cell[][] getDFSWithNumber(){
        return this.dfsMaze;
    }

	/**
	 * use DFS to find a path from start to the end in a maze
	 * @param cur current Cell that wants to find the next Cell
	 */
	public void solveDFS(Cell cur) {
		// Set the cell visited to be grey
		cur.setColor(grey);
		// Count the discover time.
		counter(cur);
		// array of cell to hold current cell's neighbor
        ArrayList<Cell> neighbors = NeighborsContained(cur);
        if (neighbors == null) {
			printDFSMaze();
		} else {
			//Go through neighbor array.
			for(int i=0;i<neighbors.size();i++) {
				if(neighbors.get(i)!=null) {
					if(neighbors.get(i).getColor()==white) {
						neighbors.get(i).setParent(cur);
						solveDFS(neighbors.get(i));
					}
				}
			}
		}
		// Cell is completed. 
		cur.setColor(black);
	}

	/**
	 * check neighbors of a Cell c, if the wall is open and neighbor's color is white
	 * @param c any Cell that want's to find neighbors
	 * @return an ArrayList of the neighbors
	 */
	private ArrayList<Cell> NeighborsContained(Cell c) {
        ArrayList<Cell> neighbors = new ArrayList<>();
		//neighbor in the top without the starting cell
        if (!c.getTop() && (c.getRow() != 0 && c.getColumn() != 0)) {
            if (dfsMaze[c.getRow() - 1][c.getColumn()].getColor() == white) {
                neighbors.add(dfsMaze[c.getRow() - 1][c.getColumn()]);
            }
        }
				
		//neighbor in the bottom
		if (!c.getBottom()) {
			//Checking if it is the ending cell
			if (c.getRow() == (size - 1)) {
				return null;
			}
			
			if (dfsMaze[c.getRow() + 1][c.getColumn()].getColor() == white) {
				neighbors.add(dfsMaze[c.getRow() + 1][c.getColumn()]);
			}
		}
		//neighbor in the left
				if (!c.getLeft()) {
					if (dfsMaze[c.getRow()][c.getColumn() - 1].getColor() == white) {
						neighbors.add(dfsMaze[c.getRow()][c.getColumn() - 1]);
					}
				}
		//Neighbor in the right
		if (!c.getRight()) {
			if (dfsMaze[c.getRow()][c.getColumn() + 1].getColor() == white) {
				neighbors.add(dfsMaze[c.getRow()][c.getColumn() + 1]);
			}
		}

		return neighbors;
	}

	/**
	 * use DFS to find a path of a maze
	 */
	public void printDFSMaze() {
		System.out.println();
		System.out.println("DFS Path: ");
		dfsMaze[0][0].setTop(false);
        dfsMaze[size - 1][size - 1].setBottom(false);

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (dfsMaze[i][j].getTop() == true) {
                    System.out.print("+ - ");
                } else
                    System.out.print("+   ");
            }
            System.out.println("+");

            for (int j = 0; j < size; j++) {
                if (dfsMaze[i][j].getLeft() == true) {
                    if (dfsMaze[i][j].getCounterDFS() != -1) {
                        System.out.print("| " + dfsMaze[i][j].getCounterDFS() + " ");
                    } else {
                        System.out.print("|   ");
                    }
                } else if (dfsMaze[i][j].getLeft() == false) {
                    if (dfsMaze[i][j].getCounterDFS() != -1) {
                        System.out.print("  " + dfsMaze[i][j].getCounterDFS() + " ");
                    } else {
                        System.out.print("    ");
                    }
                }
            }
            System.out.println("|");

		}

		// the bottom line
        for (int j = 0; j < size - 1; j++) {
            if (dfsMaze[size - 1][j].getBottom() == true) {
                System.out.print("+ - ");
            } else
                System.out.print("+   ");
        }
        System.out.print("+   +");
    }
	

	/**
	 * use back track to find the shortest path
	 */
	public void TheDFSPath() {
		dfsMaze[0][0].setChild(true);
		Cell end = dfsMaze[size - 1][size - 1];

		while (end.getParent() != null) {
			end.setChild(true);
			end = end.getParent();
		}
	}

	/**
	 * print the shortest path
	 */
	public void printDFSPath() {
		System.out.println();
		System.out.println();
		System.out.println("DFS Path: ");
		for (int i = 0; i < size; i++) {
			System.out.print("+"); // the first "+" for each line
			for (int j = 0; j < size; j++) { // to skip the next line
				// for debug
				// System.out.println("this is the " + i + " row, " + j + " column.");
				if (dfsMaze[i][j].getTop() == true) {
					System.out.print("---+");
				} else {
					System.out.print("   +");
				}
			}
			System.out.println();
			for (int j = 0; j < size; j++) {
				if (dfsMaze[i][j].getLeft() == true) {
					if (dfsMaze[i][j].getChild() != null && dfsMaze[i][j].getChild() != false) {
						System.out.print("| " + "#" + " ");
					} else {
						System.out.print("|   ");
					}
				} else if (dfsMaze[i][j].getLeft() == false) {
					if (dfsMaze[i][j].getChild() != null && dfsMaze[i][j].getChild() != false) {
						System.out.print("  " + "#" + " ");
					} else {
						System.out.print("    ");
					}
				}
			}
			System.out.println("|");
		}
		System.out.print("+");
		for (int j = 0; j < size; j++) {
			if (dfsMaze[size - 1][j].getBottom()) {
				System.out.print("---+");
			} else {
				System.out.print("   +");
			}
		}
		System.out.println();
	}

	/**
	 * get total visited cells
	 * @return total visited cells
	 */
	public int getTotalVisitedCells() {
		return size * size;
	}
}

