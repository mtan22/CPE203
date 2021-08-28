import java.lang.Math;
import java.awt.Point;
import java.awt.Color;

public class Circle implements Shape {
   private double radius;
   private Point center;
   private Color color;

   public Circle(double radius, Point center, Color color) {
      this.radius = radius;
      this.center = center;
      this.color = color;
   }
   
   public double getRadius() {
      return this.radius;
   }

   public void setRadius(double radius) {
      this.radius = radius;
   }

   public Point getCenter() {
      return this.center;
   }

   public boolean equals(Object other) {
      if(other == null) {
         return false;
      }
      if (getClass() != other.getClass()) {
         return false;
      }
      return (((Circle)other).radius) == this.radius && center.equals(((Circle)other).center) && color.equals(((Circle)other).color);
   }

   public void setColor(Color c) {
      this.color = c;
   }
  
   public Color getColor() {
      return color;
   }

   public double getArea() {
      return(Math.PI*this.radius*this.radius);
   }

   public double getPerimeter() {
      return(2*Math.PI*this.radius);
   }

   public void translate(Point p) {
      center.setLocation(center.getX() + p.getX(), center.getY() + p.getY());
   }
   
   
}
