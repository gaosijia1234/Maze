public class Tester {
    public static void main(String[] args) {
        int seed = 0;
        int size = 4;
        Maze maze = new Maze(size, seed);
        System.out.println("*****************Initial Maze*******************");
     
        System.out.println();
        maze.generateMaze();
        System.out.println();
        System.out.println();

        System.out.println("********************* BFS **********************");
        BFS bfs = new BFS(maze);
        bfs.BFSearch();
        bfs.findShortestPath();
        bfs.printWithHash();
        System.out.println();
        System.out.println();

        System.out.println("********************* DFS **********************");
        DepthFirstSearch dfs = new DepthFirstSearch(maze);
        dfs.dfs();
    }
}
