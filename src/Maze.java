import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

/**
 * Maze of a 2d array
 */
public class Maze {
    private Cell[][] maze; // the maze of a 2D Cell array
    private ArrayList<Cell> neighbors; // the neighbors of any cell
    private int size; // the dimension of a maze
    private Random random;
    private Cell beginningCell; // the first cell
    private Cell endCell; // the last cell

    /**
     * constructor of maze
     * @param size the dimension of the maze
     * @param seed the seed to generate random number
     */
    public Maze(int size, int seed){
        this.size = size;
        random = new Random(seed);
        maze = new Cell[size][size];
    }

    /**
     * to generate maze
     */
    public void generateMaze(){
        Stack<Cell> cellStack = new Stack<>(); // cell stack
        int totalCells = size * size; // the total number of cells

        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                maze[i][j] = new Cell();
                maze[i][j].setColor(CellColor.White);
            }
        }

        beginningCell = maze[0][0];
        endCell = maze[size-1][size-1];
        int visitedCells = 1; // the number of visited cells
        int randomInt; // any random integer

        Cell currentCell = beginningCell;
        while(visitedCells < totalCells){
            neighbors = findNeighbors(currentCell); //find all neighbors of CurrentCell with all walls intact
            if (neighbors.size() > 0){
                // make sure randomInt < neighbors.size()
                randomInt = random.nextInt(neighbors.size());
                // if one or more found choose one at random
                Cell removeCell = neighbors.get(randomInt);
                // knock down the wall between it and CurrentCell
                removeWall(currentCell, removeCell);
                // push CurrentCell location on the CellStack
                cellStack.push(currentCell);
                // make the new cell CurrentCell
                currentCell = removeCell;
//                System.out.println("current cell is at row " + currentCell.getRow() + ", column " + currentCell.getColumn());
                // add 1 to VisitedCells
                visitedCells += 1;
            }
            else {
                // pop the most recent cell entry off the CellStack, and make it CurrentCell
                currentCell = cellStack.pop();
            }
        }

        beginningCell.setTop(false);
        endCell.setBottom(false);
        printMaze();
    }

    /**
     * remove the wall between two cells
     * @param currentCell the cell that is current in
     * @param removeCell the cell contains the wall that will be removed
     */
    public void removeWall(Cell currentCell, Cell removeCell){
        int currentRow = currentCell.getRow(); // the row currentCell
        int currentColumn = currentCell.getColumn(); // the column of currentCell
        int removeRow = removeCell.getRow();
        int removeColumn = removeCell.getColumn();

        // locate the removeCell
        // if top, then delete bottom wall, and update the location of the cell
        if (removeRow == currentRow - 1 && removeColumn == currentColumn){
            currentCell.setTop(false);
            removeCell.setBottom(false);
        }

        // if currentCell's bottom
        if (removeRow == currentRow + 1 && removeColumn == currentColumn){
            currentCell.setBottom(false);
            removeCell.setTop(false);
        }

        // if currentCell's left
        if (removeRow == currentRow && removeColumn == currentColumn - 1){
            currentCell.setLeft(false);
            removeCell.setRight(false);
        }

        // if currentCell's right
        if (removeRow == currentRow && removeColumn == currentColumn + 1){
            currentCell.setRight(false);
            removeCell.setLeft(false);
        }

    }

    /**
     * find neighbors of a cell that is current in
     * @param currentCell the cell is current in
     * @return an ArrayList of neighbors of current cell
     */
    public ArrayList<Cell> findNeighbors(Cell currentCell){
        ArrayList<Cell> cells = new ArrayList<>();
        Cell neighbor = new Cell();
        int currentRow = currentCell.getRow();
        int currentColumn = currentCell.getColumn();

        // check top
        if (currentRow - 1 >= 0 && maze[currentRow - 1][currentColumn] != null && isIntegrate(maze[currentRow - 1][currentColumn])){
            neighbor = maze[currentRow - 1][currentColumn];
            neighbor.setRow(currentRow - 1);
            neighbor.setColumn(currentColumn);
            cells.add(neighbor);
        }

        // check bottom
        if (currentRow < size -1 && maze[currentRow + 1][currentColumn] != null && isIntegrate(maze[currentRow + 1][currentColumn])){
            neighbor = maze[currentRow + 1][currentColumn];
            neighbor.setRow(currentRow + 1);
            neighbor.setColumn(currentColumn);
            cells.add(neighbor);
        }

        // check left
        if (currentColumn - 1 >= 0 && maze[currentRow][currentColumn - 1] != null && isIntegrate(maze[currentRow][currentColumn - 1])){
            neighbor = maze[currentRow][currentColumn - 1];
            neighbor.setRow(currentRow);
            neighbor.setColumn(currentColumn - 1);
            cells.add(neighbor);
        }

        // check right
        if (currentColumn < size-1 && maze[currentRow][currentColumn + 1] != null && isIntegrate(maze[currentRow][currentColumn + 1])){
            neighbor = maze[currentRow][currentColumn + 1];
            neighbor.setRow(currentRow);
            neighbor.setColumn(currentColumn + 1);
            cells.add(neighbor);
        }
        return cells;
    }

    /**
     * check if the cell is integrate with no wall removed
     * @param checkedCell the cell being checked
     * @return
     */
    public boolean isIntegrate(Cell checkedCell){
        if (checkedCell.getTop() && checkedCell.getBottom() && checkedCell.getLeft() && checkedCell.getRight()){
            return true;
        }
        else
            return false;
    }

    /**
     * print the maze
     */
    public void printMaze(){
        // for each cell, only print it's left and top
        // because of the + and -, each line contains 2 * size + 1 symbols

        for (int i = 0; i < size; i++){

            System.out.print("+"); // the first "+" for each line
            for (int j = 0; j < size; j++) { // to skip the next line
                // for debug
//                System.out.println("this is the " + i + " row, " + j + " column.");
                if (maze[i][j].getTop()){
                    System.out.print(" - +");
                }
                else{
                    System.out.print("   +");
                }

            }
            System.out.println();

            System.out.print("|");// the first "|" for each line
            for (int j = 0; j < size; j++){
                if (maze[i][j].getRight()){
                    System.out.print("   |");
                }
                else {
                    System.out.print("    ");
                }

            }
            System.out.println();

        }

        // the bottom line
        System.out.print("+");
        for (int j = 0; j < size; j++){
            if (maze[size-1][j].getBottom()){
                System.out.print(" - +");
            }
            else{
                System.out.print("   +");
            }
        }
        System.out.println();
    }

    /**
     * get the maze
     * @return the maze
     */
    public Cell[][] getMaze() {
        return maze;
    }

    /**
     * getter of the dimension
     * @return the dimension of the maze
     */
    public int getSize() {
        return size;
    }

}
