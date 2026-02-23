package org.example;

public class Vector3 {
    float x,y,z;

    Vector3(float x, float y, float z){
        this.x = x;
        this.y = y;
        this.z = z;
    }



    @Override
    public String toString() {
        return "X: " + x + ", Y: " + y + ", Z: " + z;}
}
