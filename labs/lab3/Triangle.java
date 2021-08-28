import java.lang.Math;
import java.awt.Color;
import java.awt.Point;

public class Triangle implements Shape {
   private Point a;
   private Point b;
   private Point c;
   private Color color;
   
   public Triangle(Point a, Point b, Point c, Color color) {
      this.a = a;
      this.b = b;
      this.c = c;
      this.color = color; 
   }

   public Point getVertexA() {
      return this.a;
   }

   public Point getVertexB() {
      return this.b;
   }

   public Point getVertexC() {
      return this.c;
   }

   public boolean equals(Object other) {
      if(other == null) {
         return false;
      }
      if (getClass() != other.getClass()) {
         return false;
      }
      return a.equals(((Triangle)other).a) && b.equals(((Triangle)other).b) && c.equals(((Triangle)other).c) && color.equals(((Triangle)other).color);
   }
   
   public Color getColor() {
      return this.color;
   }

   public void setColor(Color color) {
      this.color = color;
   }

   public double getArea() {
      double distance = Math.abs((a.getX() * (b.getY() - c.getY()) + b.getX() * (c.getY() - a.getY()) + c.getX() * (a.getY() - b.getY())));
      return (distance/2);
   }

   public double getPerimeter() {
      double d1 = Math.sqrt(Math.pow(a.getX() - c.getX(), 2) + Math.pow(a.getY() - c.getY(), 2));
      double d2 = Math.sqrt(Math.pow(a.getX() - b.getX(), 2) + Math.pow(a.getY() - b.getY(), 2));
      double d3 = Math.sqrt(Math.pow(c.getX() - b.getX(), 2) + Math.pow(c.getY() - b.getY(), 2));
      double peri = d1+d2+d3;
      return peri;
   }

   public void translate(Point p) {
      a.setLocation(a.getX() + p.getX(), a.getY() + p.getY());
      b.setLocation(b.getX() + p.getX(), b.getY() + p.getY());
      c.setLocation(c.getX() + p.getX(), c.getY() + p.getY());
   }
}
