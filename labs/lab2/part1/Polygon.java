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
} 
