package junkerw.hazemaze.game;

public class Direction {
    public static final int SOUTH = 2;
    public static final int NORTH = 0;
    public static final int EAST = 3;
    public static final int WEST = 1;

    public int direction;

    public Direction(int dir){
        this.direction = dir;
    }

    public static Direction getRandomDirection(){
        return new Direction((int) (Math.random() * ((4 - 1) + 1)));
    }

    public int getDirection() {
        return this.direction;
    }

    public void setDirection(int dir) {
        this.direction = dir;
    }

    @Override
    public String toString(){
        String out;
        switch (this.direction) {
            case Direction.SOUTH:
                out = "South";
                break;
            case Direction.NORTH:
                out = "North";
            break;
            case Direction.EAST:
                out = "East";
            break;
            case Direction.WEST:
                out = "West";
            break;
            default:
                return "ERROR";
        }
        return out;
    }
    public static Direction getSouth(){
        return new Direction(Direction.SOUTH);
    }
    public static Direction getNorth(){
        return new Direction(Direction.NORTH);
    }
    public static Direction getEast(){
        return new Direction(Direction.EAST);
    }
    public static Direction getWest(){
        return new Direction(Direction.WEST);
    }

    public static Direction[] getAllDirections() {
        Direction[] dirs = {new Direction(Direction.SOUTH), new Direction(Direction.NORTH),  new Direction(Direction.EAST), new Direction(Direction.WEST)};
        return dirs;
    }

    public void rotateLeft(){
        this.setDirection(this.getDirection() < 3 ? this.getDirection() + 1 : 0);
    }
    public void rotateRight(){
        this.setDirection(this.getDirection() > 0 ? this.getDirection() - 1 : 3);
    }
}
