package junkerw.hazemaze.game;

import android.util.Log;
import android.widget.Button;

//import junkerw.hazemaze.maze.Maze;

public class Game {
    private static Game game = null;
    private Game(){
//        Maze maze = new Maze(10);

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
