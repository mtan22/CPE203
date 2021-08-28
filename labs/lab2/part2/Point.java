import java.lang.Math;
public class Point {
    private double x;
    private double y;
    private double x2;
    private double y2;

    public Point(double x, double y) {
       this.x = x;
       this.y = y;
    }

    public double getX() {return x;}
    public double getY() {return y;}
    public double getRadius() {return Math.hypot(x-0, y-0);}
    public double getAngle() {return Math.atan2(y,x);}

    public Point rotate90() {
       double x2 = this.getRadius()*Math.cos(this.getAngle()+Math.PI/2);
       double y2 = this.getRadius()*Math.sin(this.getAngle()+Math.PI/2);
       return new Point(x2, y2);
    }
}
