public class Vector2 {

    double x, y;

    public Vector2(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vector2 add(Vector2 other) {
        return new Vector2(
                x + other.x,
                y + other.y
        );
    }

    public Vector2 subtract(Vector2 other) {
        return new Vector2(
                x - other.x,
                y - other.y
        );
    }

    public double length() {
        return Math.sqrt(x * x + y * y);
    }

    public Vector2 normalize() {
        return new Vector2(x / length(),
                y / length()
        );
    }

    public double dot(Vector2 other) {
        return x * other.x + y * other.y;

    }
}
