package junkerw.hazemaze.game;

import android.util.Log;
import android.widget.Button;

import junkerw.hazemaze.maze.Maze;

public class Game {
    private static Game game = null;
    private Game(){
        try {
            for (int i=0; i<100; i++) {
                Maze maze = new Maze(3);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

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
