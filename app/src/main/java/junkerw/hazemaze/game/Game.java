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

    public Event inputLeft(){
        player.rotateLeft();
        return new Event(Event.TYPE_ROTATING,"Rotating left");
    }
    public Event inputRight(){
        player.rotateRight();
        return new Event(Event.TYPE_ROTATING, "Rotating right");
    }
    public Event inputStraight(){
        Position pos = player.getPosition();
        Direction dir = player.getHeading();
        if (maze.isWalkable(new Position(pos,dir))) {
            player.move();
            maze.setVisited(player.getPosition());
            if (player.getPosition().equals(maze.getExit())) {
                return new Event(Event.TYPE_EXIT, "You found the exit!");
            } else if (player.getPosition().equals(maze.getEntrance())) {
                return new Event(Event.TYPE_ENTANCE, "You are back at the start");
            } else {
                return new Event(Event.TYPE_WALKING, "Making a step");

            }
        } else {
            return new Event(Event.TYPE_WALL, "Wall");
        }
    }

    public int getSteps() {
        return maze.getTotalVisitedNo();
    }
}
