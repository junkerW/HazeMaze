package junkerw.hazemaze.maze;

public class Border extends Field{

    @Override
    public boolean isFree() {
        return false;
    }

    @Override
    public boolean isEntrance() {
        return false;
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
