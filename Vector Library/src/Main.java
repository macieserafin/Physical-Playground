public class Main {
    public static void main(String[] args) {

        Vector2 v1 = new Vector2(4, 3);
        Vector2 v2 = new Vector2(4, 5);

        Vector2 norm = v2.add(v1).normalize();

        System.out.println(norm.x + " " + norm.y);
    }
} 