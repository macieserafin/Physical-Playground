package org.example;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SimulationWindow extends JPanel {

    // referencje do obiektów symulacji
    private Earth earth;
    private Sun sun;

    // skala świata → ekranu
    private float scale = 2f;

    // lista punktów śladu
    private List<Position> trail = new ArrayList<>();

    public SimulationWindow(Earth earth, Sun sun) {
        this.earth = earth;
        this.sun = sun;
        setBackground(Color.BLACK);
    }

    // dodawanie punktu do śladu (wołane co tick)
    public void addTrailPoint() {
        trail.add(new Position(
                earth.position.x,
                earth.position.y,
                earth.position.z
        ));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int cx = getWidth() / 2;
        int cy = getHeight() / 2;

        // === TRAIL ===
        g.setColor(new Color(0, 150, 255, 120));
        for (Position p : trail) {
            int x = cx + (int)(p.x * scale);
            int y = cy - (int)(p.y * scale);
            g.fillRect(x, y, 2, 2);
        }

        // === SUN ===
        g.setColor(Color.YELLOW);
        int sunX = cx + (int)(sun.position.x * scale);
        int sunY = cy - (int)(sun.position.y * scale);
        g.fillOval(sunX - 6, sunY - 6, 12, 12);

        // === EARTH ===
        g.setColor(Color.CYAN);
        int earthX = cx + (int)(earth.position.x * scale);
        int earthY = cy - (int)(earth.position.y * scale);
        g.fillOval(earthX - 4, earthY - 4, 8, 8);
    }
}
