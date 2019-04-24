package junkerw.hazemaze.game;
import junkerw.hazemaze.being.Player;
import junkerw.hazemaze.maze.Maze;

public class Game {
    private static Game game = null;
    private Game(){
        try {
            Maze maze = new Maze(3);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Player player = new Player(new Position(2,2), Direction.getRandomDirection());

    }

    public static Game getGame(){
        if (game == null){
            return new Game();
        } else {
            return game;
        }
    }

    public String inputLeft(){
        return "left";
    }
    public String inputRight(){
        return "right";
    }
    public String inputStraight(){
        return "straight";
    }
}
