public class Util {
   public static double perimeter(Circle c) {
      return (c.getRadius()*Math.PI*2.0);
   }

   public static double perimeter(Rectangle r) {
      double calc1 = (r.getBottomRight().getX()-r.getTopLeft().getX());
      double calc2 = (r.getBottomRight().getY()-r.getTopLeft().getY());
      double length1 = Math.abs(calc1);
      double length2 = Math.abs(calc2);
      return (length1*2 + length2*2);
   }
   
   public static double perimeter(Polygon p) {
      double x = 0.0;
      double y = 0.0;
      double polygon_peri = 0.0;
      int size = p.getPoints().size()-1;
  
      for (int i=0; i <= size; i++) {
         if (i == size){ 
            x = Math.abs(p.getPoints().get(size).getX()-p.getPoints().get(0).getX());
            y = Math.abs(p.getPoints().get(size).getY()-p.getPoints().get(0).getY());
            polygon_peri += Math.sqrt(Math.pow(x,2)+Math.pow(y,2));
         }
         else {
            x = Math.abs(p.getPoints().get(i+1).getX()-p.getPoints().get(i).getX());	
            y = Math.abs(p.getPoints().get(i+1).getY()-p.getPoints().get(i).getY());
            polygon_peri += Math.sqrt(Math.pow(x,2)+Math.pow(y,2));
         }
   }
      return polygon_peri;
   }
}
