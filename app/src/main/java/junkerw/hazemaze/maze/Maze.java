package junkerw.hazemaze.maze;

import android.util.Log;
import android.widget.TableLayout;
import android.widget.TableRow;

import junkerw.hazemaze.activities.GameActivity;
import junkerw.hazemaze.activities.MainMenuActivity;
import junkerw.hazemaze.game.Direction;
import junkerw.hazemaze.game.Position;

public class Maze {

	/*
	Maze.size is not the number of fields of the maze but the number of ways
	seperated with walls.
	Maze.dimension is dimension of the matrix including the borders of the
	maze
	The boders have a thicknes of two
	at the end of the maze creation the borders are resized to a thickness of
	one.
	The size of the playable matrix is dimension-2.
	the size of the accesible area is dimension-4 or size*2-1
	*/

    private int size, dimension;

	//private int[][] trace_Maze;
	//private int[][] save_Maze;

    //private int[][] maze = { { 0, 0, 0, 0, 0 }, { 2, 1, 1, 0, 0 },{ 0, 0, 1, 0, 0 }, { 0, 0, 1, 1, 1 }, { 0, 0, 0, 0, 0 } };
//	private int[][] maze;
	private Field[][] maze;
	private Position entrance;
	private Position exit;
	private int rows;
	private int cols;

	public Maze(int size) throws Exception {

		int minSize = 2;
		if (size < minSize) {
			throw new Exception("Chosen size too small should be bigger than " + minSize);
		}
		this.size = size;

		//increase size to dimension for border and walls of maze
		this.dimension = size * 2 + 3;
		this.cols = size * 2 + 1;
		this.rows = size * 2 + 1;

        this.maze = buildMaze();

		//maze = buildMaze(dimension);
	}

	private Field[][] buildMaze() throws Exception {

		maze = new Field[dimension][dimension];

		for (int i = 0; i <= dimension - 1; i++) {
			for (int j = 0; j <= dimension - 1; j++) {
				maze[i][j] = new Space();
			}
		}

		// Maze initialisieren
		for (int i = 3; i <= dimension - 3; i += 2) {
			for (int j = 2; j <= dimension - 3; j++) {
				maze[i][j] = new Wall();
				maze[j][i] = new Wall();
			}
		}
		// borders
		for (int i = 0; i <= 1; i++) {
			for (int j = 0; j <= dimension - 1; j++) {
				maze[i][j] = new Border();
				maze[i + dimension - 2][j] = new Border();
				maze[j][i] = new Border();
				maze[j][i + dimension - 2] = new Border();
			}
		}

		/*
		 * 1=freier nicht betretener Platz 0=normale Wand 2=feste Aussengrenze
		 * 3=begangener Platz 4=Zur�ckverfolgter Platz
		 */
        int cells = size * size;
		int treatedCells = 1;

		Position pos = new Position(2,2);
		entrance = new Position(pos);
		this.setField(pos, new Entrance());

		this.setTreated(pos);
		while (treatedCells < cells) {
//            Log.w("Treated Cells: ", Integer.toString(treatedCells));
            while (inDeadEnd(pos)) {
                pos = stepBack(pos);
            }
            Direction direction = Direction.getRandomDirection();

            if (!behindWallisTreated(pos, direction)) {
                pos.change(direction); // walk inside wall
                breakWall(pos);
				this.getField(pos).setTreated(); // set former wall now space field treated
                pos.change(direction); // walk on empty space behind wall
                setTreated(pos); // set empty space behind wall treated
                treatedCells++;
            }

        }

		exit = new Position(dimension - 3, dimension - 3);
		this.setField(exit, new Exit());

		return maze;
		// Create Exit and Entrance

		//maze[2][2] = 1;
		//maze[dimension - 3][dimension - 3] = 1;

		//maze = new int[dimension - 2][dimension - 2];
		//this.maze = save_Maze;
	}

    private Position stepBack(Position pos) throws Exception{
	    this.getField(pos).setBacktracked(true);
	    Direction[] dirs = Direction.getAllDirections();
	    Position newPos = new Position(pos);
	    Field stepField;
	    for (Direction dir : dirs) {
	        stepField = this.getField(new Position(pos,dir));
	        if(stepField.isFree() && !stepField.isBacktracked()) {
	            newPos = new Position(pos,dir);
//	            this.getField(newPos).setBacktracked(true);
            }
        }
	    if (newPos.equals(pos)) {
	        throw new Exception("Stuck at Position: " + pos.toString());
        }
	    return newPos;
    }

    private boolean inDeadEnd(Position pos) {
	    boolean end = true;
	    Direction[] directions =Direction.getAllDirections();
        for (Direction dir : directions) {
            if (!behindWallisTreated(pos,dir)) {
                end = false;
            }
        }
       return end;
    }

    private void breakWall(Position pos) {
        this.setField(pos, new Space());
    }

    // check if space behind wall is not treated yet
	private boolean behindWallisTreated(Position pos, Direction direction) {
	    //change position twice in direction to jump behind wall
	    Position checkPos = new Position(pos, direction);
	    checkPos.change(direction);
	    return this.getField(checkPos).isTreated();
	}

	private boolean isTreated(Position pos) {
	    return this.getField(pos).isTreated();
    }

	public int getSize() {
		int length = maze.length;
		return length;
	}

	//public int getDimension() {
		//return dimension;
	//}

    public Field[][] getMaze() {
        return maze;
    }

//    public int[] getEntrance() {
//
//        int[] entrance = {dimension-4,dimension-4};
//        return entrance;
//    }
//    public int[] getExit() {
//
//        int[] exit = {1,1};
//        return exit;
//    }

    private void setTreated(Position pos) {
        getField(pos).setTreated();
    }

//	public boolean isWall(int[] coordinates){
//
//		if (maze[coordinates[0]][coordinates[1]] == 0 || maze[coordinates[0]][coordinates[1]] == 2){
//            return false;
//        } else{
//            return true;
//        }
//	}

	private Field getField (Position pos){
	    return maze[pos.getCol()][pos.getRow()];
    }

    private void setField(Position pos, Field field){
	    maze[pos.getCol()][pos.getRow()] = field;
    }
    
    public Position getEntrance(){
		return this.entrance;
	}

	public boolean isWalkable(Position pos){
		return this.getField(pos).isFree();
	}

	public Position getExit() {
		return this.exit;
	}

	public void setVisited(Position pos) {
		this.getField(pos).increaseVisited();
	}

	public int getVisitedNo(Position pos) {
		return this.getField(pos).getVisitedNo();
	}

	public int getTotalVisitedNo() {
		int steps = 0;
		for (int i = 0;i < this.getRows(); i++) {
			for (int j = 0; j < this.getCols(); j++) {
				steps += this.getField(new Position(i,j)).getVisitedNo();
			}
		}
		return steps;
	}

	public int getRows() {
		return rows;
	}


	public int getCols() {
		return cols;
	}

}
