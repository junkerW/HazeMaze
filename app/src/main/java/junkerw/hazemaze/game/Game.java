package junkerw.hazemaze.game;

import android.content.res.Resources;

import junkerw.hazemaze.R;
import junkerw.hazemaze.being.Player;
import junkerw.hazemaze.maze.Maze;

public class Game {
    private static Game game = null;
    Maze maze;
    Player player;
    Resources resources;

    public Game(int mazeSize, Resources resources){
        this.resources = resources;
        try {
            maze = new Maze(mazeSize);
            player = new Player(maze.getEntrance(), Direction.getRandomDirection());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Event inputLeft(){
        player.rotateLeft();
        return new Event(Event.TYPE_ROTATING, resources.getString(R.string.messRotLeft));
    }
    public Event inputRight(){
        player.rotateRight();
        return new Event(Event.TYPE_ROTATING, resources.getString(R.string.messRotRight));
    }
    public Event inputStraight(){
        Position pos = player.getPosition();
        Direction dir = player.getHeading();
        if (maze.isWalkable(new Position(pos,dir))) {
            player.move();
            maze.setVisited(player.getPosition());
            if (player.getPosition().equals(maze.getExit())) {
                return new Event(Event.TYPE_EXIT, resources.getString(R.string.messExit));
            } else if (player.getPosition().equals(maze.getEntrance())) {
                return new Event(Event.TYPE_ENTANCE, resources.getString(R.string.messEntr));
            } else {
                return new Event(Event.TYPE_WALKING, resources.getString(R.string.messWalk));

            }
        } else {
            return new Event(Event.TYPE_WALL, resources.getString(R.string.messWall));
        }
    }

    public int getSteps() {
        return maze.getTotalVisitedNo();
    }
}
