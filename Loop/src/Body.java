public class Body {
    Position position;
    Velocity velocity;
    Acceleration acceleration;

    Body(Position position, Velocity velocity, Acceleration acceleration) {
        this.position = position;
        this.velocity = velocity;
        this.acceleration = acceleration;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;

    }

    public String toString(){
        return "Body: " + "position=" + position + ", velocity=" + velocity + ", acceleration=" + acceleration;

    }




}
