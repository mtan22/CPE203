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
}
