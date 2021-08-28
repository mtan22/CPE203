import java.util.*;
public class Polygon {
   private ArrayList<Point> point_list;

   public Polygon(List<Point> points) {
      point_list = new ArrayList<Point>();
      for (int i=0; i<points.size(); i++) {
         point_list.add(points.get(i));
      }
   }
   public List<Point> getPoints(){
      return point_list;
   }
   public double perimeter() {
      double x = 0.0;
      double y = 0.0;
      double polygon_peri = 0.0;
      int size = this.getPoints().size()-1;

      for (int i=0; i <= size; i++) {
         if (i == size){
            x = Math.abs(this.getPoints().get(size).getX()-this.getPoints().get(0).getX());
            y = Math.abs(this.getPoints().get(size).getY()-this.getPoints().get(0).getY());
            polygon_peri += Math.sqrt(Math.pow(x,2)+Math.pow(y,2));
         }
         else {
            x = Math.abs(this.getPoints().get(i+1).getX()-this.getPoints().get(i).getX());
            y = Math.abs(this.getPoints().get(i+1).getY()-this.getPoints().get(i).getY());
            polygon_peri += Math.sqrt(Math.pow(x,2)+Math.pow(y,2));
         }
   }
      return polygon_peri;
  }
}
