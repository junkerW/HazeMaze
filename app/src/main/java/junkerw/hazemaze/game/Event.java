package junkerw.hazemaze.game;

public class Event {
    public static final int TYPE_ROTATING = 1;
    public static final int TYPE_EXIT   = 2;
    public static final int TYPE_ENTANCE = 3;
    public static final int TYPE_WALKING = 4;
    public static final int TYPE_WALL   = 0;

    private int status;
    private String message;

    public Event(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return this.message;
    }

    public boolean equals(Event event) {
        if (event.getMessage() == this.message && event.getStatus() == this.status) {
            return true;
        } else {
            return false;
        }
    }

}
