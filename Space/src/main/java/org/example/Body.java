package org.example;

public class Body {
    float mass;
    Velocity velocity;
    Position position;



    public Body(float mass, Position position) {
        this.mass = mass;
        this.position = position;
        this.velocity = new Velocity(0, 0, 0);
    }

    public void Body(Velocity velocity){
        this.velocity = velocity;
    }


    public void VelocityAdd(Vector3 vector) {
        velocity.x += vector.x;
        velocity.y += vector.y;
        velocity.z += vector.z;
    }

    public Vector3 Direction(Position positionB){

        return new Vector3((position.x - positionB.x), (position.y - positionB.y), (position.z - positionB.z));
    }

    public void update(float dt){
        position.x += velocity.x * dt;
        position.y += velocity.y * dt;
        position.z += velocity.z * dt;
    }

    public void print(){
        System.out.println("X : " + position.x + " Y : " + position.y +" Z : " + position.z);
    }


}
