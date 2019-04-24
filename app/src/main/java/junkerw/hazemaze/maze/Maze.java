//package junkerw.hazemaze.maze;
//
//import android.util.Log;
//
//import junkerw.hazemaze.game.Position;
//
//public class Maze {
//
//	private final int SOUTH = 0;
//	private final int NORTH = 1;
//	private final int EAST = 2;
//	private final int WEST = 3;
//
//	/*
//	Maze.size is not the number of fields of the maze but the number of ways
//	seperated with walls.
//	Maze.dimension is dimension of the matrix including the borders of the
//	maze
//	The boders have a thicknes of two
//	at the end of the maze creation the borders are resized to a thickness of
//	one.
//	The size of the playable matrix is dimension-2.
//	the size of the accesible area is dimension-4 or size*2-1
//	*/
//
//    private int size, dimension;
//
//	//private int[][] trace_Maze;
//	//private int[][] save_Maze;
//
//    //private int[][] maze = { { 0, 0, 0, 0, 0 }, { 2, 1, 1, 0, 0 },{ 0, 0, 1, 0, 0 }, { 0, 0, 1, 1, 1 }, { 0, 0, 0, 0, 0 } };
////	private int[][] maze;
//	private Field[][] maze;
//
//	public Maze(int size){
//
//		this.size = size;
//
//		//increase size to dimension for border and walls of maze
//		this.dimension = size * 2 + 3;
//
//        this.maze = buildMaze();
//
//		//maze = buildMaze(dimension);
//	}
//
//	private Field[][] buildMaze() {
//
//		maze = new Space[dimension][dimension];
//
////		for (int i = 0; i <= dimension - 1; i++) {
////			for (int j = 0; j <= dimension - 1; j++) {
////				maze[i][j]++;
////			}
////		}
//		// Maze initialisieren
//		// walls (Eintrag 0)
//		for (int i = 3; i <= dimension - 3; i += 2) {
//			for (int j = 2; j <= dimension - 3; j++) {
//				maze[i][j] = new Wall();
//				maze[j][i] = new Wall();
//			}
//		}
//		// borders (Eintrag 2)
//		for (int i = 0; i <= 1; i++) {
//			for (int j = 0; j <= dimension - 1; j++) {
//				maze[i][j] = new Border();
//				maze[i + dimension - 2][j] = new Border();
//				maze[j][i] = new Border();
//				maze[j][i + dimension - 2] = new Border();
//			}
//		}
//
//		/*
//		 * 1=freier nicht betretener Platz 0=normale Wand 2=feste Aussengrenze
//		 * 3=begangener Platz 4=Zur�ckverfolgter Platz
//		 */
//        int cells = size * size;
//		int treatedCells = 1;
//
//		Position pos = new Position(2,2);
//
//        int x = 2;
//        int y = 2;
//
//		// Forward Tracking
//		maze[x][y].setTreated();
//		while (treatedCells < cells) {
//			Log.w("Treated Cells: ", Integer.toString(treatedCells));
//            int direction = getRandomDirection();
//
//			if (direction == 1 && maze[x][y - 2].isFree()) {
//				maze[x][y - 1].setTreated();
//				y -= 2;
//				maze[x][y].setTreated();
//				treatedCells++;
//			} else if (direction == 2 && maze[x - 2][y].isFree()) {
//				maze[x - 1][y].setTreated();
//				x -= 2;
//				maze[x][y].setTreated();
//				treatedCells++;
//			} else if (direction == 3 && maze[x][y + 2].isFree()) {
//				maze[x][y + 1].setTreated();
//				y += 2;
//				maze[x][y].setTreated();
//				treatedCells++;
//			} else if (direction == 4 && maze[x + 2][y].isFree()) {
//				maze[x + 1][y].setTreated();
//				x += 2;
//				maze[x][y].setTreated();
//				treatedCells++;
//			}
//
//			// Back Tracking
//			else if (maze[x + 2][y] != 1 && maze[x - 2][y] != 1
//					&& maze[x][y - 2] != 1 && maze[x][y + 2] != 1) {
//
//				maze[x][y] = 4;
//				if (maze[x + 1][y] == 3) {
//					maze[x + 1][y] = 4;
//					x += 2;
//				} else if (maze[x - 1][y] == 3) {
//					maze[x - 1][y] = 4;
//					x -= 2;
//				} else if (maze[x][y + 1] == 3) {
//					maze[x][y + 1] = 4;
//					y += 2;
//				} else if (maze[x][y - 1] == 3) {
//					maze[x][y - 1] = 4;
//					y -= 2;
//				}
//			}
//		}
//		// Nachbearbeitung
//		for (int i = 0; i <= dimension - 1; i++) {
//			for (int j = 0; j <= dimension - 1; j++) {
//				if (maze[i][j] == 4 || maze[i][j] == 3) {
//					maze[i][j] = 1;
//				}
//			}
//		}
//		for (int i = 0; i <= dimension - 1; i++) {
//			for (int j = 0; j <= dimension - 1; j++) {
//				if (maze[i][j] == 2) {
//					maze[i][j] = 0;
//				}
//			}
//		}
//
//		// aus Doppelrand einen machen
//		int[][] save_Maze = new int[dimension - 2][dimension - 2];
//
//        for (int i = 1; i <= dimension - 2; i++) {
//            System.arraycopy(maze[i], 1, save_Maze[i - 1], 0, dimension - 2);
//        }
//
//		return save_Maze;
//		// Eingang und Ausgang hinzuf�gen
//		//maze[2][2] = 1;
//		//maze[dimension - 3][dimension - 3] = 1;
//
//		//maze = new int[dimension - 2][dimension - 2];
//		//this.maze = save_Maze;
//	}
//
//	private boolean isFree(int direction) {
//		switch (direction) {
//			case SOUTH
//				maze[x][y - 2].isFree()
//				break;
//			case NORTH:
//				return NORTH;
//			break;
//			case EAST:
//				return EAST;
//			break;
//			case WEST:
//				return WEST;
//			break;
//			default:
//				return SOUTH;
////		}
//		}
//	}
//
//	private int getRandomDirection(){
//		return 1 + (int) (Math.random() * ((4 - 1) + 1));
////		switch(direction){
////			case 0:
////				return SOUTH;
////				break;
////			case 1:
////				return NORTH;
////				break;
////			case 2:
////				return EAST;
////				break;
////			case 3:
////				return WEST;
////				break;
////			default:
////				return SOUTH;
////		}
//
//	}
//
//	public int getSize() {
//		int length = maze.length;
//		return length;
//	}
//
//	//public int getDimension() {
//		//return dimension;
//	//}
//
//    public Field[][] getMaze() {
//        return maze;
//    }
//
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
//
//	public boolean isWall(int[] coordinates){
//
//		if (maze[coordinates[0]][coordinates[1]] == 0 || maze[coordinates[0]][coordinates[1]] == 2){
//            return false;
//        } else{
//            return true;
//        }
//
//	}
//
//}
