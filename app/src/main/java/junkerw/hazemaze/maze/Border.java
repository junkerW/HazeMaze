package junkerw.hazemaze.maze;

public class Border extends Field{
    @Override
    public int getType(int col, int row) {
       return Field.TYPE_BORDER;
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
