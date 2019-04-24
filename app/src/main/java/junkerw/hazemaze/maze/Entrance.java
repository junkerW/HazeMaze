package junkerw.hazemaze.maze;

public class Entrance extends Space {

    @Override
    public boolean isEntrance() {
        return true;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
