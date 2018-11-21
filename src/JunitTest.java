import org.junit.Test;

import static org.junit.Assert.*;

public class JunitTest {

    /**
     * Check if the same maze is generated for every run.
     */
    @Test
    public void testMaze(){
        System.out.println("1. Testing the maze of size 4");

        Maze maze1 = new Maze(4, 0);
        maze1.generateMaze();
        assertTrue( maze1.getSize() == 4);

        Maze maze2 = new Maze(4, 0);
        maze2.generateMaze();
        assertTrue( maze2.getSize() == 4);
        // first, check the size is the same
        assertEquals(maze1.getSize(), maze2.getSize());
        Cell[][] cells1 = maze1.getMaze();
        Cell[][] cells2 = maze2.getMaze();
        // second, check for every cell, the wall exists or not is the same
        for(int i = 0; i < cells2.length; i++) {
            for(int j = 0; j < cells2[i].length; j++) {
                assertEquals( cells2[i][j].getLeft(), cells1[i][j].getLeft());
                assertEquals( cells2[i][j].getTop(), cells1[i][j].getTop());
                assertEquals( cells2[i][j].getRight(), cells1[i][j].getRight());
                assertEquals( cells2[i][j].getBottom(), cells1[i][j].getBottom());
            }
        }
    }

    /**
     * Test BFS solution of finding the path and the shortest path
     * Check for the total number of visited cells.
     */
    @Test
    public void testBFS() {
        System.out.println("2. Testing BFS solution of size 4");
        Maze maze = new Maze(4, 0);
        maze.generateMaze();
        int[][] idealAnswer ={
                {0,1,2,5},
                {-1,-1,3,4},
                {-1,-1,-1,6},
                {-1,-1,-1,7}
        };
        BFS bfs = new BFS(maze);
        bfs.BFSearch();
        bfs.findShortestPath();
        bfs.printWithHash();
        assertEquals(bfs.getTotalVisitedCells(), 16);
        Cell[][] actualAnswer = bfs.getBFSWithNumber();
        for(int i = 0; i < actualAnswer.length; i++) {
            for(int j = 0; j < actualAnswer[i].length; j++) {
                assertTrue( actualAnswer[i][j].getCounterBFS() == idealAnswer[i][j]);
            }
        }
    }

    /**
     * Test DFS solution of finding the path and the shortest path
     * Check for the total number of visited cells.
     */
    @Test
    public void testDFS() {
        System.out.println("3. Testing DFS solution of size 4");
        Maze maze = new Maze(4, 0);
        maze.generateMaze();
        int[][] idealAnswer ={
                {0,1,2,5},
                {-1,-1,3,4},
                {-1,-1,-1,6},
                {-1,-1,-1,7}
        };

        DepthFirstSearch dfs = new DepthFirstSearch(maze);
        dfs.dfs();
        assertEquals(dfs.getTotalVisitedCells(), 16);
        Cell[][] actualAnswer = dfs.getDFSWithNumber();
        for(int row = 0; row < actualAnswer.length; row++) {
            for(int col = 0; col < actualAnswer[row].length; col++) {
                assertTrue( actualAnswer[row][col].getCounterDFS() ==  idealAnswer[row][col]);
            }
        }
    }

    /**
     * Testing Maze of size 4
     */
    @Test
    public void MazeSize4(){
        System.out.println("4. Testing the maze of size 4");
        Maze maze = new Maze(4, 0);
        maze.generateMaze();
    }

    /**
     * Testing BFS solution of size 4
     */
    @Test
    public void BFSSize4() {
        System.out.println("5. Testing BFS solution of size 4");
        Maze maze = new Maze(4, 0);
        maze.generateMaze();
        BFS bfs = new BFS(maze);
        bfs.BFSearch();
        bfs.findShortestPath();
        bfs.printWithHash();
    }

    /**
     * Testing DFS solution of size 4
     */
    @Test
    public void DFSSize4() {
        System.out.println("6. Testing DFS solution of size 4");
        Maze maze = new Maze(4, 0);
        maze.generateMaze();
        DepthFirstSearch dfs = new DepthFirstSearch(maze);
        dfs.dfs();
    }

    /**
     * Testing Maze of size 6
     */
    @Test
    public void MazeSize6(){
        System.out.println("7. Testing the maze of size 6");
        Maze maze = new Maze(6, 0);
        maze.generateMaze();
    }

    /**
     * Testing BFS solution of size 6
     */
    @Test
    public void BFSSize6() {
        System.out.println("8. Testing BFS solution of size 6");
        Maze maze = new Maze(6, 0);
        maze.generateMaze();
        BFS bfs = new BFS(maze);
        bfs.BFSearch();
        bfs.findShortestPath();
        bfs.printWithHash();
    }

    /**
     * Testing DFS solution of size 6
     */
    @Test
    public void DFSSize6() {
        System.out.println("9. Testing DFS solution of size 6");
        Maze maze = new Maze(6, 0);
        maze.generateMaze();
        DepthFirstSearch dfs = new DepthFirstSearch(maze);
        dfs.dfs();
    }

    /**
     * Testing Maze of size 8
     */
    @Test
    public void MazeSize8(){
        System.out.println("10. Testing the maze of size 8");
        Maze maze = new Maze(8, 0);
        maze.generateMaze();
    }

    /**
     * Testing BFS solution of size 8
     */
    @Test
    public void BFSSize8() {
        System.out.println("11. Testing BFS solution of size 8");
        Maze maze = new Maze(8, 0);
        maze.generateMaze();
        BFS bfs = new BFS(maze);
        bfs.BFSearch();
        bfs.findShortestPath();
        bfs.printWithHash();
    }

    /**
     * Testing DFS solution of size 8
     */
    @Test
    public void DFSSize8() {
        System.out.println("12. Testing DFS solution of size 8");
        Maze maze = new Maze(8, 0);
        maze.generateMaze();
        DepthFirstSearch dfs = new DepthFirstSearch(maze);
        dfs.dfs();
    }

}