package junkerw.hazemaze.maze;

public abstract class Field {

//    public static final int TYPE_WALL = 0;
//    public static final int  TYPE_SPACE = 1;
//    public static final int TYPE_BORDER = 2;
    private boolean treated;
    private boolean backtracked;

//    public abstract int getType(int col, int row);

    public boolean isTreated() {
        return this.isOccupied() || this.treated;
    }
    public void setTreated(boolean treated) {
        this.treated = treated;
    }
    public void setTreated() {
        this.treated = true;
    }

    public abstract boolean isFree();

    public boolean isOccupied() {
        return !isFree();
    }

    public boolean isBacktracked() {
        return this.isOccupied() || backtracked;
    }

    public void setBacktracked(boolean backtracked) {
        this.backtracked = backtracked;
    }

    public abstract boolean isEntrance();

    public abstract boolean isExit();


}
