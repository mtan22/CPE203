import java.lang.Math;
import java.awt.Point;
import java.awt.Color;

public class Rectangle implements Shape {
   private double width;
   private double height;
   private Point topLeft;
   private Color color;

   public Rectangle(double width, double height, Point topLeft, Color color) {
      this.width = width;
      this.height = height;
      this.topLeft = topLeft;
      this.color = color;
   }
 
   public double getWidth() {
      return this.width;
   }

   public void setWidth(double width) {
      this.width = width;
   }

   public double getHeight() {
      return this.height;
   }

   public void setHeight(double height) {
      this.height = height;
   }

   public Point getTopLeft() {
      return this.topLeft;
   }

   public boolean equals(Object other) {
      if(other == null) {
         return false;
      }
      if (getClass() != other.getClass()) {
         return false;
      }
      return (((Rectangle)other).width) == width && (((Rectangle)other).height) == height && topLeft.equals(((Rectangle)other).topLeft) && color.equals(((Rectangle)other).color);
   }

   public Color getColor() {
      return this.color;
   }

   public void setColor(Color color) {
      this.color = color;
   }
   
   public double getArea() {
      return(this.width*this.height);
   }

   public double getPerimeter() {
      return(2*this.width+2*this.height);
   }
   
   public void translate(Point p) {
      topLeft.setLocation(topLeft.getX() + p.getX(), topLeft.getY() + p.getY());
   }
}   
