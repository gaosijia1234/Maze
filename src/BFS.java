// if there is no wall between currentCell and white neighbors,
// then the neighbor are part of the dfs.
// need to mark the end cell that whenever hit the last cell, stop searching. -- by using the row and column maze[size-1][size-1]

import java.util.ArrayList;

/**
 * use BFS to find the path of finding a maze from start to end.
 * use BFS's back track to find the shortest path of a maze from start to end.
 */
public class BFS {
    private Node root; // root of the Node, the first Node
    private int counter; // counter of each Cell's number by visited order
    private Cell[][] cells; // each maze room is considered as a Cell
    private int size; // the dimension of the maze

    /**
     * Constructor of BFS
     * @param maze has-a Maze object
     */
    public BFS(Maze maze) {
        cells = maze.getMaze();
        this.size = maze.getSize();
        counter = -1;
    }

    /**
     * To find the path, mark colors, and mark numbers of each cell
     */
    public void BFSearch(){
        // so that later when print, whatever is -1 will not be printed
        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                cells[i][j].setCounterBFS(-1);
            }
        }
        Cell u = cells[0][0]; // the first cell
        u.setColor(CellColor.Grey);
        Cell v; // any cell can be v
        enqueue(u);
        while(root != null) {
            u = dequeue();
            counter++;
            if (counter == 10){
                counter = 0;
                u.setCounterBFS(0);
            } else {
                u.setCounterBFS(counter);
            }
            ArrayList<Cell> neighbors = findNeighbors(u);
            if (u.getColumn() == size-1 && u.getRow() == size-1){
//            if (neighbors == null) {
                printWithNumber();
                return;
            }
            else {
                for (int i = 0; i < neighbors.size(); i++) {
                    v = neighbors.get(i);
                    if (v.getColor() == CellColor.White) {
                        v.setColor(CellColor.Grey);
                        v.setParent(u);
                        enqueue(v);
                    }
                }
            }
        }
        u.setColor(CellColor.Black);
    }

    /**
     * get the result of the path of BFS with number
     * @return the 2D array of the path order
     */
    public Cell[][] getBFSWithNumber(){
        return cells;
    }

    /**
     * for every cell, find the neighbors of the cell if the wall between them is open
     * @param u any Cell u
     * @return neighbors of the Cell u
     */
    public ArrayList<Cell> findNeighbors(Cell u){
        ArrayList<Cell> cellArrayList = new ArrayList<>(); // ArrayList of neighbors
        int currentRow = u.getRow(); // the Cell u's row number
        int currentColumn = u.getColumn(); // the Cell u's column number
        Cell neighbor = new Cell(); // a neighbor of Cell u

        // if top wall is open, top cell is one of the neighbors
        // and it is not the first row
        // !means no wall, so it means open
        if (!u.getTop() && currentRow != 0){
            neighbor = cells[currentRow - 1][currentColumn];
            neighbor.setRow(currentRow - 1);
            neighbor.setColumn(currentColumn);
            cellArrayList.add(neighbor);
        }

        // if bottom wall is open, bottom cell is one of the neighbors
        if (!u.getBottom() && currentRow!= size-1){
            neighbor = cells[currentRow + 1][currentColumn];
            neighbor.setRow(currentRow + 1);
            neighbor.setColumn(currentColumn);
            cellArrayList.add(neighbor);
        }

        // if left wall is open, left cell is one of the neighbors
        if (!u.getLeft() && currentColumn != 0){
            neighbor = cells[currentRow][currentColumn - 1];
            neighbor.setRow(currentRow);
            neighbor.setColumn(currentColumn - 1);
            cellArrayList.add(neighbor);
        }

        // if right wall is open, right cell is one of the neighbors
        if (!u.getRight() && currentColumn!= size-1){
            neighbor = cells[currentRow][currentColumn + 1];
            neighbor.setRow(currentRow);
            neighbor.setColumn(currentColumn + 1);
            cellArrayList.add(neighbor);
        }
        return cellArrayList;
    }

    /**
     * print the maze with number of each cell, follow a path
     */
    public void printWithNumber() {
        System.out.println();
        System.out.println();
        System.out.println("BFS path:");
        cells[0][0].setTop(false);
        cells[size - 1][size - 1].setBottom(false);

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (cells[i][j].getTop() == true) {
                    System.out.print("+ - ");
                } else
                    System.out.print("+   ");
            }
            System.out.println("+");

            for (int j = 0; j < size; j++) {
                if (cells[i][j].getLeft() == true) {
                    if (cells[i][j].getCounterBFS() != -1) {
                        System.out.print("| " + cells[i][j].getCounterBFS() + " ");
                    } else {
                        System.out.print("|   ");
                    }
                } else if (cells[i][j].getLeft() == false) {
                    if (cells[i][j].getCounterBFS() != -1) {
                        System.out.print("  " + cells[i][j].getCounterBFS() + " ");
                    } else {
                        System.out.print("    ");
                    }
                }
            }
            System.out.println("|");
        }
        for (int j = 0; j < size - 1; j++) {
            if (cells[size - 1][j].getBottom() == true) {
                System.out.print("+ - ");
            } else
                System.out.print("+   ");
        }
        System.out.print("+   +");
    }

    /**
     * from the end back track the BFS to find the shortest path
     */
    public void findShortestPath() {
        cells[0][0].setChild(true);
        Cell end = cells[size - 1][size - 1];

        while (end.getParent() != null) {
            end.setChild(true);
            end = end.getParent();
        }
    }

    /**
     * print the shortest path with "#"
     */
    public void printWithHash() {
        System.out.println();
        System.out.println();
        System.out.println("BFS Path:");
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (cells[i][j].getTop() == true) {
                    System.out.print("+ - ");
                } else
                    System.out.print("+   ");
            }
            System.out.println("+");
            for (int j = 0; j < size; j++) {
                if (cells[i][j].getLeft() == true) {
                    if (cells[i][j].getChild() != null && cells[i][j].getChild() != false) {
                        System.out.print("| " + "#" + " ");
                    } else {
                        System.out.print("|   ");
                    }
                } else if (cells[i][j].getLeft() == false) {
                    if (cells[i][j].getChild() != null && cells[i][j].getChild() != false) {
                        System.out.print("  " + "#" + " ");
                    } else {
                        System.out.print("    ");
                    }
                }
            }
            System.out.println("|");
        }
        for (int j = 0; j < size - 1; j++) {
            if (cells[size - 1][j].getBottom() == true) {
                System.out.print("+ - ");
            } else
                System.out.print("+   ");
        }
        System.out.print("+   +");
    }

    /**
     * Node is for Queue elements
     */
    class Node {
        private Node next = null; // next node
        private Cell cell; // data of the cell

        /**
         * Constructor of Node
         * @param cell cell object of Cell
         */
        public Node(Cell cell) {
            this.cell = cell;
        }

        /**
         * check if current node has next node
         * @return true if has next node, false if doesn't
         */
        public boolean hasNext() {
            if (this.next != null) {
                return true;
            }
            return false;
        }
    }

    /**
     * remove the first element of the queue
     * @return the first element (root element) of the queue
     */
    private Cell dequeue() {
        Node node = root; // a temporary node to save root
        root = root.next;
        return node.cell;
    }

    /**
     * add a new Cell at the end of the queue
     * @param cell Cell cell is the new node
     */
    private void enqueue(Cell cell) {
        Node node = new Node(cell);
        if (root == null) {
            root = node;
        } else {
            while (root.hasNext()) {
                root = root.next;
            }
            root.next = node;
        }
    }

    /**
     * get total visited cells
     * @return total visited cells
     */
    public int getTotalVisitedCells() {
        return size * size;
    }
}