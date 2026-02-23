public class Velocity {
    double x, y;

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public Velocity(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double distance(Velocity velocity) {
        return Math.sqrt(Math.pow(velocity.getX() - x, 2) + Math.pow(velocity.getY() - y, 2));
    }

    public double distance(double x, double y) {
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }

    public String toString() {
        return x + " " + y;
    }

}
