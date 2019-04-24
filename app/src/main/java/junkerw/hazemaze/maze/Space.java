package junkerw.hazemaze.maze;

public class Space extends Field {

    private int visitedCount = 0;

    @Override
    public boolean isFree() {
        return true;
    }

    @Override
    public boolean isOccupied() {
        return !isFree();
    }

    @Override
    public boolean isEntrance() {
        return false;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    public int getVisitedCount(){
        return visitedCount;
    }
    public boolean wasVisited() {
        return visitedCount > 0 ? true : false;
    }

}
