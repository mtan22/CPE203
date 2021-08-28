public class Rectangle{
   private Point topLeft, bottomRight;
   Rectangle(Point p1, Point p2) {
      topLeft = p1;
      bottomRight = p2;
   }
   public Point getTopLeft(){
      return topLeft;
   }
   public Point getBottomRight() {
      return bottomRight;
   }
   public double perimeter() {
      double calc1 = (this.getBottomRight().getX()-this.getTopLeft().getX());
      double calc2 = (this.getBottomRight().getY()-this.getTopLeft().getY());
      double length1 = Math.abs(calc1);
      double length2 = Math.abs(calc2);
      return (length1*2 + length2*2);
   }
}
