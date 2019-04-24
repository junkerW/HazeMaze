package junkerw.hazemaze.maze;

public class Wall extends Field {
    @Override
    public int getType(int col, int row) {
        return Field.TYPE_WALL;
    }
    @Override
    public boolean isFree() {
        return false;
    }

    @Override
    public boolean isOccupied() {
        return true;
    }
}
