public class Lab00
{
   public static void main(String[] args)
   {
   int x = 5;
   String y = "hello";
   float z = 9.8f;
   System.out.println("x: "+x+" y: "+y+" z: "+z);
   int[] intArray = new int[]{3, 6, -1, 2};
   for (int i = 0; i < intArray.length; i++) {
      System.out.println(intArray[i]);
   }
   int numFound = char_count(y, "l");
   System.out.println("Found: " + numFound);
   for (int i = 1; i < 11; i++) {
      System.out.print(i + " ");
   }
   System.out.print("\n");
   }
   public static int char_count(String s, String c)
   {
   int count = 0;
   for (int i = 0; i < s.length(); i++) {
      char letter = s.charAt(i);
      String m = Character.toString(letter);
      if(m.equals(c)) {
         count++;
      }
   }
   return count;
   }
}
