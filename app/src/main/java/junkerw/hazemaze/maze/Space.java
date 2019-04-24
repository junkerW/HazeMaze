package junkerw.hazemaze.maze;

public class Space extends Field {

    private int visitedCount = 0;

    @Override
    public int getType(int col, int row) {
        return Field.TYPE_SPACE;
    }

    @Override
    public boolean isFree() {
        return true;
    }

    @Override
    public boolean isOccupied() {
        return false;
    }

    public int getVisitedCount(){
        return visitedCount;
    }
    public boolean wasVisited() {
        return visitedCount > 0 ? true : false;
    }
}
