public class Circle{
   private Point center;
   private double radius;
   Circle(Point c, double r) {
      center = c;
      radius = r;
   }
   public Point getCenter() {
      return center;
   }
   public double getRadius() {
      return radius;
   }
   public double perimeter() {
      return (this.getRadius()*Math.PI*2.0);
   }

}
 
