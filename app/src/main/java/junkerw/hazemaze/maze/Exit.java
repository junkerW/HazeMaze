package junkerw.hazemaze.maze;

public class Exit extends Space {

    @Override
    public boolean isEntrance() {
        return false;
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
