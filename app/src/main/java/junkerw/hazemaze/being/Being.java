package junkerw.hazemaze.being;

import junkerw.hazemaze.game.Direction;
import junkerw.hazemaze.game.Position;

public class Being {
    private String name;
    private Position position;
    private Direction heading;

    public Being(Position pos, Direction heading) {
        this.position = new Position(pos);
        this.heading = new Direction(heading);
    }

    public void move() {
        this.position.change(this.heading);
    }

    public void move(Direction dir) {
        this.position.change(dir);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Direction getHeading() {
        return heading;
    }

    public void setHeading(Direction direction) {
        this.heading = direction;
    }

    public void rotateLeft() {
        this.heading.rotateLeft();
    }

    public void rotateRight(){
        this.heading.rotateRight();
    }
}
