package junkerw.hazemaze.game;
import junkerw.hazemaze.being.Player;
import junkerw.hazemaze.maze.Maze;

public class Game {
    private static Game game = null;
    Maze maze;
    Player player;
    public Game(int mazeSize){

        try {
            maze = new Maze(mazeSize);
            player = new Player(maze.getEntrance(), Direction.getRandomDirection());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String inputLeft(){
        player.rotateLeft();
        return "Rotating left";
    }
    public String inputRight(){
        player.rotateRight();
        return "Rotating right";
    }
    public String inputStraight(){
        Position pos = player.getPosition();
        Direction dir = player.getHeading();
        if (maze.isWalkable(new Position(pos,dir))) {
            player.move();
            if (player.getPosition().equals(maze.getExit())) {
                return "You found the exit!";
            } else if (player.getPosition().equals(maze.getEntrance())) {
                return "You are back at the start";
            } else {
                return "Making a step";

            }
        } else {
            return "Wall";
        }
    }
}
