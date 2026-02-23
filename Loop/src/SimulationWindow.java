import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SimulationWindow extends JPanel {
    private Body body;
    private float scale = 2f;
    private List<Position> trail = new ArrayList<>();

    public SimulationWindow(Body body) {
        this.body = body;
        setBackground(Color.BLACK);
    }

    public void addTrailPoint() {
        trail.add(new Position(
                body.position.x,
                body.position.y
        ));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int cx = getWidth() / 2;
        int cy = getHeight() / 2;

        g.setColor(Color.CYAN);
        int earthX = cx + (int)(body.position.x * scale);
        int earthY = cy - (int)(body.position.y * scale);
        g.fillOval(earthX - 4, earthY - 4, 20, 20);

        g.setColor(new Color(0, 150, 255, 120));
        for (Position p : trail) {
            int x = cx + (int)(p.x * scale);
            int y = cy - (int)(p.y * scale);
            g.fillRect(x, y, 2, 2);
        }
    }
}
