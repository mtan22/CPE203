import java.lang.Double;
class SimpleIf
{
   public static double max(double x, double y)
   {
      /* TO DO: Write an if statement to determine which
         argument is larger and return that value.
      */
      if (Double.compare(x, y) > 0) {
         return x;
      }
      else {
         return y;
      }
   }
}
