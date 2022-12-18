package maze.maze;

import java.util.Objects;

public class Position {
    private int x;
    private int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Position)) return false;

        Position anotherPos = (Position) obj;
        return x == anotherPos.x && y == anotherPos.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
