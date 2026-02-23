import javax.swing.*;

public class Main {

    private static Body body = new Body(new Position(0,0), new Velocity(0,0), new Acceleration(40, 40) );
    private static SimulationWindow panel;
    private static KeyHandler keyH = new KeyHandler();




    public static void main(String[] args) {

        JFrame frame = new JFrame("Simulation");
        panel = new SimulationWindow(body);
        frame.add(panel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.setVisible(true);
        frame.setFocusable(true);
        frame.requestFocusInWindow();
        frame.addKeyListener(keyH);

        Main game = new Main();
        game.run();
    }

    private volatile boolean running = true;

    public void run(){
        final double targetFPS = 60.0;
        final double targetFrameTime  = 1.0 / targetFPS;
        double printAcc = 0;
        double acc = 0;
        double stopTime = 10;

        long lastTime = System.nanoTime();

        while(running){
            long now = System.nanoTime();
            double deltaTime = (now - lastTime) / 1_000_000_000.0;
            lastTime = now;

            if (deltaTime > 0.25) deltaTime = 0.25;

            update(deltaTime);

            render();

            printAcc += deltaTime;
            if (printAcc >= 1.0) {
                printAcc -= 1.0;
                System.out.println(body);

            }

            long frameEnd = System.nanoTime();
            double frameTime = (frameEnd - now) / 1_000_000_000.0;
            double sleepTime = targetFrameTime - frameTime;

            if (sleepTime > 0){
                try{
                    Thread.sleep((long) (sleepTime * 1000));
                }catch(InterruptedException ignored){}
            }
            acc += deltaTime;
//            if (acc >= stopTime) {stop();}
        }


    }

    private void update(double deltaTime){

        double vx = body.velocity.getX();
        double vy = body.velocity.getY();

        double ax = body.acceleration.getX();
        double ay = body.acceleration.getY();

            if (keyH.upPressed) {

                double prevVy = vy;
                vy += ay * deltaTime;
                if (prevVy < 0 && vy > 0) vy = 0;

//                body.velocity.setY(vy);
//
//                double y = body.position.getY();
//                y += vy * deltaTime;
//                body.position.setY(y);
            }

            if (keyH.downPressed) {

                double prevVy = vy;
                vy -= ay * deltaTime;
                if (prevVy > 0 && vy < 0) vy = 0;

//                body.velocity.setY(vy);
//
//                double y = body.position.getY();
//                y += vy * deltaTime;
//                body.position.setY(y);
            }

            if (keyH.leftPressed) {

                double prevVx = vx;
                vx -= ax * deltaTime;
                if (prevVx > 0 && vx < 0) vx = 0;

//                body.velocity.setX(vx);
//
//                double x = body.position.getX();
//                x += vx * deltaTime;
//                body.position.setX(x);
            }

            if (keyH.rightPressed) {

                double prevVx = vx;
                vx += ax * deltaTime;
                if (prevVx < 0 && vx > 0) vx = 0;

//                body.velocity.setX(vx);
//
//                double x = body.position.getX();
//                x += vx * deltaTime;
//                body.position.setX(x);
            }

            if (keyH.spacePressed){
                vx = 0;
                vy = 0;
            }

            body.velocity.setX(vx);
            body.velocity.setY(vy);

            double x = body.position.getX();
            double y = body.position.getY();
            x += vx * deltaTime;
            y += vy * deltaTime;
            body.position.setX(x);
            body.position.setY(y);



    }

    private void render(){
        panel.addTrailPoint();
        panel.repaint();

    }

    private void stop(){
        running = false;
    }
}