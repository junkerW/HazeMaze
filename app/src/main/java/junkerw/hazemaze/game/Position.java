package junkerw.hazemaze.game;

public class Position {
    private int row;
    private int col;
    public Position(int row, int col){
        this.col = col;
        this.row = row;
    }

    public Position(Position pos) {
        this.row = pos.getRow();
        this.col = pos.getCol();
    }
    public Position(Position pos, int offsetCol, int offsetRow) {
        this.row = pos.getRow() + offsetRow;
        this.col = pos.getCol() + offsetCol;
    }

    public Position(Position pos, Direction dir) {
        this.row = pos.getRow();
        this.col = pos.getCol();
        this.change(dir);
    }

    public Position change(Direction dir) {
        switch (dir.getDirection()) {
            case Direction.SOUTH:
                this.row++;
                break;
            case Direction.NORTH:
                this.row--;
            break;
            case Direction.EAST:
                this.col++;
            break;
            case Direction.WEST:
                this.col--;
            break;
        }
        return this;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public void moveSouth() {

    }
    public void moveNorth() {

    }
    public void moveWest() {

    }
    public void moveEast() {

    }
}
