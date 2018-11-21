/**
 * Cell represent each room of the maze
 */
public class Cell {
    private boolean left, right, top, bottom; // they are the walls of a Cell
    private CellColor color; // each Cell has a color
    private int row; // the row of the cell in the maze
    private int column; // the column of the cell in the maze
    private Cell parent; // cell's last visited cell is the parent of the current cell
    private Boolean child; // if current cell has next cell
    private int counterBFS; // the order number of visited in BFS
    private int counterDFS = -1; // the order number of visited in DFS

    /**
     * Constructor of Cell, initialize evey wall to be true, has every wall
     */
    public Cell(){
        left = true;
        right = true;
        top = true;
        bottom = true;
    }

    /**
     * setter of counterDFS
     * @param count the number wants to be set to
     */
    public void setCounterDFS(int count) {
        counterDFS = count;
    }

    /**
     * getter of counterDFS
     * @return the counter of the counterDFS
     */
    public int getCounterDFS() {
        return this.counterDFS;
    }

    /**
     * getter of child
     * @return if the child exist or not
     */
    public Boolean getChild() {
        return child;
    }

    /**
     * setter of child
     * @param child has child - true
     */
    public void setChild(Boolean child) {
        this.child = child;
    }

    /**
     * getter of counterBFS
     * @return the number of counterBFS
     */
    public int getCounterBFS() {
        return counterBFS;
    }

    /**
     * setter of CounterBFS
     * @param counterBFS the number being passed in for CounterBFS
     */
    public void setCounterBFS(int counterBFS) {
        this.counterBFS = counterBFS;
    }

    /**
     * getter of parent cell
     * @return the parent cell
     */
    public Cell getParent() {
        return parent;
    }

    /**
     * the setter of parent
     * @param parent the cell of parent
     */
    public void setParent(Cell parent) {
        this.parent = parent;
    }

    /**
     * get current row number
     * @return the number of current row
     */
    public int getRow(){
        return this.row;
    }

    /**
     * set the row with row number
     * @param row the row number to set
     */
    public void setRow(int row){
        this.row = row;
    }

    /**
     * get current column number
     * @return the column number
     */
    public int getColumn(){
        return this.column;
    }

    /**
     * set the column number
     * @param column the column number being set
     */
    public void setColumn(int column){
        this.column = column;
    }

    /**
     * get the top of cell
     * @return if exist of top
     */
    public boolean getTop() {
        return top;
    }

    /**
     * set the top wall of a cell
     * @param boo existence of top wall
     */
    public void setTop(Boolean boo){
        this.top = boo;
    }

    /**
     * get the left of cell
     * @return if exist of left
     */
    public boolean getLeft() {
        return left;
    }

    /**
     * set the left wall of a cell
     * @param boo existence of left wall
     */
    public void setLeft(Boolean boo){
        this.left = boo;
    }

    /**
     * get the bottom of cell
     * @return if exist of bottom
     */
    public boolean getBottom() {
        return bottom;
    }

    /**
     * set the bottom wall of a cell
     * @param boo existence of bottom wall
     */
    public void setBottom(Boolean boo){
        this.bottom = boo;
    }

    /**
     * get the right of cell
     * @return if exist of right
     */
    public boolean getRight() {
        return right;
    }

    /**
     * set the right wall of a cell
     * @param boo existence of right wall
     */
    public void setRight(Boolean boo){
        this.right = boo;
    }

    /**
     * get the Cell color of cell
     * @return the color of cell
     */
    public CellColor getColor() {
        return color;
    }

    /**
     * set the color of cell
     * @param color the color of cell
     */
    public void setColor(CellColor color) {
        this.color = color;
    }
}
