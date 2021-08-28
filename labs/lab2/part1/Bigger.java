import java.util.Arrays;
import java.util.*;

public class Bigger {
   public static double whichIsBigger(Circle c, Rectangle r, Polygon p) {
      double circle_peri = Util.perimeter(c);
      double rectangle_peri = Util.perimeter(r);
      List<Point> points_polygon = Arrays.asList(new Point(0.0, 0.0), new Point(3.0, 1.0), new Point(1.0,4.0), new Point(-1.0,4.0));
      double polygon_peri = Util.perimeter(p);
      if (rectangle_peri < polygon_peri && polygon_peri > circle_peri) {
         return polygon_peri;
      }
      if (rectangle_peri > circle_peri && rectangle_peri > polygon_peri) {
         return rectangle_peri;
      }
      else {
         return circle_peri;
      }
   }
}
