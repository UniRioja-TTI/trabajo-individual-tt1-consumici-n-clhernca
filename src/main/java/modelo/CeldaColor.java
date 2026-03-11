package modelo;

public class CeldaColor {
    private int tiempo;
    private int y;
    private int x;
    private String color;

    public CeldaColor(int tiempo, int y, int x, String color) {
        this.tiempo = tiempo;
        this.y = y;
        this.x = x;
        this.color = color;
    }

    public int getTiempo() { return tiempo; }
    public int getY() { return y; }
    public int getX() { return x; }
    public String getColor() { return color; }
}