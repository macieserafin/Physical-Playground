package org.example;
import javax.swing.*;


import static java.lang.Math.sqrt;

public class Main {
    public static void main(String[] args) {

        Earth earth = new Earth(10, new Position(100, 150, 0));
        Sun sun = new Sun(10, new Position(0, 0, 0));

        JFrame frame = new JFrame("Solar Simulation");
        SimulationWindow panel = new SimulationWindow(earth, sun);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.add(panel);
        frame.setVisible(true);

        Vector3 dir0 = sun.Direction(earth.position);

        float len0 = (float)Math.sqrt(dir0.x*dir0.x + dir0.y*dir0.y);
        dir0.x /= len0;
        dir0.y /= len0;

        Vector3 tangent = new Vector3(-dir0.y, dir0.x, 0);

        float G0 = 100f;
        float v = (float)Math.sqrt(G0 / len0);

        tangent.x *= v;
        tangent.y *= v;

        earth.VelocityAdd(tangent);

        float dt = 0.1f;
        float G = 100f;
        while (true) {

            Vector3 dir = sun.Direction(earth.position);

            float len = (float)Math.sqrt(dir.x*dir.x + dir.y*dir.y + dir.z*dir.z);

            float minLen = 5f;
            len = Math.max(len, minLen);

            float force = G / (len * len);

            if (len != 0) {
                dir.x = (dir.x / len) * force;
                dir.y = (dir.y / len) * force;
                dir.z = (dir.z / len) * force;
            }

            earth.VelocityAdd(dir);
            earth.update(dt);

            panel.addTrailPoint();
            panel.repaint();
            earth.print();

            try {
                Thread.sleep(16); // ~60 FPS
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}