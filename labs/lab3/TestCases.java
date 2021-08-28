import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;

import java.awt.Color;
import java.awt.Point;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;
import org.junit.Test;

public class TestCases
{
   public static final double DELTA = 0.00001;

   /* some sample tests but you must write more! see lab write up */

   @Test
   public void testCircleGetArea()
   {
      Circle c = new Circle(5.678, new Point(2, 3), Color.BLACK);

      assertEquals(101.2839543, c.getArea(), DELTA);
   }

   @Test
   public void testCircleGetPerimeter()
   {
      Circle c = new Circle(5.678, new Point(2, 3), Color.BLACK);

      assertEquals(35.6759261, c.getPerimeter(), DELTA);
   }
   @Test
   public void testCircleGetColor() {
      Circle c = new Circle(5.678, new Point(2,3), Color.BLACK);
      assertEquals(Color.BLACK, c.getColor());
   }
   
   @Test
   public void testCircleSetColor() {
      Circle c = new Circle(5.678, new Point(2,3), Color.BLACK);
      c.setColor(Color.BLUE);
      assertEquals(Color.BLUE, c.getColor());
   }
   
   @Test
   public void testCircleGetRadius() {
      Circle c = new Circle(5.678, new Point(2,3), Color.BLACK);
      assertEquals(5.678, c.getRadius(), DELTA);
   }

   @Test
   public void testCircleSetRadius() {
      Circle c = new Circle(5.678, new Point(2,3), Color.BLACK);
      c.setRadius(6.443);
      assertEquals(6.443, c.getRadius(), DELTA);
   }
   
   @Test
   public void testCircleGetCenter() {
      Circle c = new Circle(5.678, new Point(2,3), Color.BLACK);
      assertEquals(new Point(2,3), c.getCenter());
   }
   
   @Test
   public void testCircleEquals() {
      Circle c = new Circle(5.678, new Point(2,3), Color.BLACK);
      assertTrue(c.equals(new Circle(5.678, new Point(2,3), Color.BLACK)));
      assertFalse(c.equals(new Circle(5.678, new Point(2,3), Color.BLUE)));
   }

   @Test
   public void testCircleTranslate() {
      Circle c = new Circle(5.678, new Point(2,3), Color.BLACK);
      c.translate(new Point(3,6));
      assertEquals(new Point(5,9), c.getCenter());
   }
   
   @Test
   public void testRectangleGetArea() {
      Rectangle r = new Rectangle(1.0, 4.0, new Point(1,2), Color.BLACK);
      assertEquals(4.0, r.getArea(), DELTA);
   }

   @Test
   public void testRectangleGetPerimeter() {
      Rectangle r = new Rectangle(1.0, 4.0, new Point(1,2), Color.BLACK);
      assertEquals(10.0, r.getPerimeter(), DELTA);
   }
 
   @Test
   public void testRectangleGetColor() {
      Rectangle r = new Rectangle(1.0, 4.0, new Point(1,2), Color.BLACK);
      assertEquals(Color.BLACK, r.getColor());
   }

   @Test
   public void testRectangleSetColor() {
      Rectangle r = new Rectangle(1.0, 4.0, new Point(1,2), Color.BLACK);
      r.setColor(Color.BLUE);
      assertEquals(Color.BLUE, r.getColor());
   }

   @Test
   public void testRectangleGetWidth() {
      Rectangle r = new Rectangle(1.0, 4.0, new Point(1,2), Color.BLACK);
      assertEquals(1.0, r.getWidth(), DELTA);
   }

   @Test
   public void testRectangleSetWidth() {
      Rectangle r = new Rectangle(1.0, 4.0, new Point(1,2), Color.BLACK);
      r.setWidth(4.0);
      assertEquals(4.0, r.getWidth(), DELTA);
   }

   @Test
   public void testRectangleGetHeight() {
      Rectangle r = new Rectangle(1.0, 4.0, new Point(1,2), Color.BLACK);
      assertEquals(4.0, r.getHeight(), DELTA);
   }
  
   @Test
   public void testRectangleSetHeight() {
      Rectangle r = new Rectangle(1.0, 4.0, new Point(1,2), Color.BLACK);
      r.setHeight(10.0);
      assertEquals(10.0, r.getHeight(), DELTA);
   }

   @Test
   public void testRectangleGetTopLeft() {
      Rectangle r = new Rectangle(1.0, 4.0, new Point(1,2), Color.BLACK);
      assertEquals(new Point(1,2), r.getTopLeft());
   }

   @Test
   public void testRectangleEquals() {
      Rectangle r = new Rectangle(1.0, 4.0, new Point(1,2), Color.BLACK);
      assertFalse(r.equals(new Rectangle(1.0,4.0, new Point(3,6), Color.BLACK)));
      assertTrue(r.equals(new Rectangle(1.0,4.0, new Point(1,2), Color.BLACK)));
   }
      
   @Test
   public void testRectangleTranslate() {
      Rectangle r = new Rectangle(1.0, 4.0, new Point(1,2), Color.BLACK);
      r.translate(new Point(3,4));
      assertEquals(new Point(4,6), r.getTopLeft());
   }

   @Test
   public void testTriangleGetArea() {
      Triangle t = new Triangle(new Point(1,0), new Point(2,4), new Point(3,0), Color.PINK);
      assertEquals(4.0, t.getArea(), DELTA);
   }
   
   @Test
   public void testTriangleGetPerimeter() {
      Triangle t = new Triangle(new Point(1,0), new Point(2,4), new Point(3,0), Color.PINK);
      assertEquals(10.246211251235321, t.getPerimeter(), DELTA);
   }
   
   @Test
   public void testTriangleGetColor() {
      Triangle t = new Triangle(new Point(1,0), new Point(2,4), new Point(3,0), Color.PINK);
      assertEquals(Color.PINK, t.getColor());
   }

   @Test
   public void testTriangleSetColor() {
      Triangle t = new Triangle(new Point(1,0), new Point(2,4), new Point(3,0), Color.PINK);
      t.setColor(Color.BLACK);
      assertEquals(Color.BLACK, t.getColor());
   }
 
   @Test
   public void testTriangleGetVertexA() {
      Triangle t = new Triangle(new Point(1,0), new Point(2,4), new Point(3,0), Color.PINK);
      assertEquals(new Point(1,0), t.getVertexA());
   }
   
   @Test
   public void testTriangleGetVertexB() {
      Triangle t = new Triangle(new Point(1,0), new Point(2,4), new Point(3,0), Color.PINK);
      assertEquals(new Point(2,4), t.getVertexB());
   }

   @Test
   public void testTriangleGetVertexC() {
      Triangle t = new Triangle(new Point(1,0), new Point(2,4), new Point(3,0), Color.PINK);
      assertEquals(new Point(3,0), t.getVertexC());
   }

   @Test
   public void testTriangleEquals() {
      Triangle t = new Triangle(new Point(1,0), new Point(2,4), new Point(3,0), Color.PINK);
      assertFalse(t.equals(new Triangle(new Point(1,0), new Point(3,4), new Point(3,0), Color.PINK)));
      assertTrue(t.equals(new Triangle(new Point(1,0), new Point(2,4), new Point(3,0), Color.PINK)));
   }

   @Test
   public void testTriangleTranslate() {
      Triangle t = new Triangle(new Point(1,0), new Point(2,4), new Point(3,0), Color.PINK);
      t.translate(new Point(0,1));
      assertEquals(new Point(1,1), t.getVertexA());
      assertEquals(new Point(2,5), t.getVertexB());
      assertEquals(new Point(3,1), t.getVertexC());
   }
   
   @Test
   public void testWorkSpaceAreaOfAllShapes()
   {
      WorkSpace ws = new WorkSpace();

      ws.add(new Rectangle(1.234, 5.678, new Point(2, 3), Color.BLACK));
      ws.add(new Circle(5.678, new Point(2, 3), Color.BLACK));
      ws.add(new Triangle(new Point(0,0), new Point(2,-4), new Point(3, 0), 
                 Color.BLACK));

      assertEquals(114.2906063, ws.getAreaOfAllShapes(), DELTA);
   }

   @Test
   public void testWorkSpaceGetCircles()
   {
      WorkSpace ws = new WorkSpace();
      List<Circle> expected = new LinkedList<>();

      // Have to make sure the same objects go into the WorkSpace as
      // into the expected List since we haven't overriden equals in Circle.
      Circle c1 = new Circle(5.678, new Point(2, 3), Color.BLACK);
      Circle c2 = new Circle(1.11, new Point(-5, -3), Color.RED);

      ws.add(new Rectangle(1.234, 5.678, new Point(2, 3), Color.BLACK));
      ws.add(c1);
      ws.add(new Triangle(new Point(0,0), new Point(2,-4), new Point(3, 0),
                 Color.BLACK));
      ws.add(c2);

      expected.add(c1);
      expected.add(c2);

      // Doesn't matter if the "type" of lists are different (e.g Linked vs
      // Array).  List equals only looks at the objects in the List.
      assertEquals(expected, ws.getCircles());
   }

   /* HINT - comment out implementation tests for the classes that you have not 
    * yet implemented */
   @Test
   public void testWorkSpaceAdd() {
      WorkSpace ws = new WorkSpace();
      Shape x = new Circle(3.0, new Point(1,3), Color.BLUE);
      ws.add(x);
      assertEquals(x, ws.get(0));
   }

   @Test
   public void testWorkSpaceGet() {
      WorkSpace ws = new WorkSpace();
      Shape x = new Circle(3.0, new Point(1,3), Color.BLUE);
      ws.add(x);
      ws.add(new Circle(2.0, new Point(9,4), Color.BLUE));
      assertEquals(x, ws.get(0));
   }

   @Test
   public void testWorkSpaceSize() {
      WorkSpace ws = new WorkSpace();
      Shape x1 = new Circle(3.0, new Point(1,3), Color.BLUE);
      Shape x2 = new Rectangle(2.0, 5.0, new Point(4,0), Color.PINK);
      Shape x3 = new Triangle(new Point(1,0), new Point(4,2), new Point(3,0), Color.BLUE);
      ws.add(x1);
      ws.add(x2);
      ws.add(x3);
      assertEquals(3, ws.size());
   }

   @Test
   public void testWorkSpaceGetRectangles()
   {
      WorkSpace ws = new WorkSpace();
      List<Rectangle> expected = new LinkedList<>();

      Circle c1 = new Circle(5.678, new Point(2, 3), Color.BLACK);
      Circle c2 = new Circle(1.11, new Point(-5, -3), Color.RED);
      Rectangle r1 = new Rectangle(2.55, 3.4, new Point(2,4), Color.BLACK);
      ws.add(new Rectangle(1.234, 5.678, new Point(2, 3), Color.BLACK));
      ws.add(c1);
      ws.add(new Triangle(new Point(0,0), new Point(2,-4), new Point(3, 0), Color.BLACK));
      ws.add(c2);
      ws.add(r1);
      expected.add(new Rectangle(1.234, 5.678, new Point(2, 3), Color.BLACK));
      expected.add(r1);                                                                                                  assertEquals(expected, ws.getRectangles());
   }

   @Test
   public void testWorkSpaceGetTriangles() {
      WorkSpace ws = new WorkSpace();
      List<Triangle> expected = new LinkedList<>();
      Circle c1 = new Circle(5.678, new Point(2, 3), Color.BLACK);
      Circle c2 = new Circle(1.11, new Point(-5, -3), Color.RED);
      Triangle t1 = new Triangle(new Point(3,0), new Point(-1,2), new Point(9,0), Color.PINK);
      ws.add(new Rectangle(1.234, 5.678, new Point(2, 3), Color.BLACK));
      ws.add(c1);
      ws.add(new Triangle(new Point(0,0), new Point(2,-4), new Point(3, 0), Color.BLACK));
      ws.add(c2);
      ws.add(t1);
      expected.add(new Triangle(new Point(0,0), new Point(2,-4), new Point(3, 0), Color.BLACK));
      expected.add(t1);
      assertEquals(expected, ws.getTriangles());
   }

   @Test
   public void testWorkSpaceGetShapesByColor() {
      WorkSpace ws = new WorkSpace();
      List<Shape> expected = new LinkedList<>();
      Circle c1 = new Circle(5.678, new Point(2, 3), Color.BLACK);
      Circle c2 = new Circle(1.11, new Point(-5, -3), Color.RED);
      Triangle t1 = new Triangle(new Point(3,0), new Point(-1,2), new Point(9,0), Color.PINK);
      ws.add(new Rectangle(1.234, 5.678, new Point(2, 3), Color.BLACK));
      ws.add(c1);
      ws.add(new Triangle(new Point(0,0), new Point(2,-4), new Point(3, 0), Color.BLACK));
      ws.add(c2);
      ws.add(t1);
      expected.add(new Rectangle(1.234, 5.678, new Point(2, 3), Color.BLACK));
      expected.add(c1);
      expected.add(new Triangle(new Point(0,0), new Point(2,-4), new Point(3, 0), Color.BLACK));
      assertEquals(expected, ws.getShapesByColor(Color.BLACK));
   }

   @Test
   public void testWorkSpaceGetPerimeters() {
      WorkSpace ws = new WorkSpace();
      Circle c1 = new Circle(1.11, new Point(-5, -3), Color.RED);
      Triangle t1 = new Triangle(new Point(3,0), new Point(-1,2), new Point(9,0), Color.PINK);
      Rectangle r1 = new Rectangle(3.0, 1.5, new Point(1,2), Color.BLACK);
      ws.add(c1);
      ws.add(t1);
      ws.add(r1);
      assertEquals(36.644510673154485, ws.getPerimeterOfAllShapes(), DELTA);
   }

   @Test
   public void testCircleImplSpecifics()
      throws NoSuchMethodException
   {
      final List<String> expectedMethodNames = Arrays.asList(
         "getColor", "setColor", "getArea", "getPerimeter", "translate",
         "getRadius", "setRadius", "getCenter", "equals");

      final List<Class> expectedMethodReturns = Arrays.asList(
         Color.class, void.class, double.class, double.class, void.class,
         double.class, void.class, Point.class, boolean.class);

      final List<Class[]> expectedMethodParameters = Arrays.asList(
         new Class[0], new Class[] {Color.class}, new Class[0], new Class[0], new Class[] {Point.class},
         new Class[0], new Class[] {double.class}, new Class[0], new Class[] {Object.class});

      verifyImplSpecifics(Circle.class, expectedMethodNames,
         expectedMethodReturns, expectedMethodParameters);
   }

   @Test
   public void testRectangleImplSpecifics()
      throws NoSuchMethodException
   {
      final List<String> expectedMethodNames = Arrays.asList(
         "getColor", "setColor", "getArea", "getPerimeter", "translate",
         "getWidth", "setWidth", "getHeight", "setHeight", "getTopLeft", "equals");

      final List<Class> expectedMethodReturns = Arrays.asList(
         Color.class, void.class, double.class, double.class, void.class,
         double.class, void.class, double.class, void.class, Point.class, boolean.class);

      final List<Class[]> expectedMethodParameters = Arrays.asList(
         new Class[0], new Class[] {Color.class}, new Class[0], new Class[0], new Class[] {Point.class},
         new Class[0], new Class[] {double.class}, new Class[0], new Class[] {double.class}, 
         new Class[0], new Class[] {Object.class});

      verifyImplSpecifics(Rectangle.class, expectedMethodNames,
         expectedMethodReturns, expectedMethodParameters);
   }

   @Test
   public void testTriangleImplSpecifics()
      throws NoSuchMethodException
   {
      final List<String> expectedMethodNames = Arrays.asList(
         "getColor", "setColor", "getArea", "getPerimeter", "translate",
         "getVertexA", "getVertexB", "getVertexC", "equals");

      final List<Class> expectedMethodReturns = Arrays.asList(
         Color.class, void.class, double.class, double.class, void.class,
         Point.class, Point.class, Point.class, boolean.class);

      final List<Class[]> expectedMethodParameters = Arrays.asList(
         new Class[0], new Class[] {Color.class}, new Class[0], new Class[0], new Class[] {Point.class},
         new Class[0], new Class[0], new Class[0], new Class[] {Object.class});

      verifyImplSpecifics(Triangle.class, expectedMethodNames,
         expectedMethodReturns, expectedMethodParameters);
   }

   private static void verifyImplSpecifics(
      final Class<?> clazz,
      final List<String> expectedMethodNames,
      final List<Class> expectedMethodReturns,
      final List<Class[]> expectedMethodParameters)
      throws NoSuchMethodException
   {
      assertEquals("Unexpected number of public fields",
         0, clazz.getFields().length);

      final List<Method> publicMethods = Arrays.stream(
         clazz.getDeclaredMethods())
            .filter(m -> Modifier.isPublic(m.getModifiers()))
            .collect(Collectors.toList());

      assertEquals("Unexpected number of public methods",
         expectedMethodNames.size(), publicMethods.size());

      assertTrue("Invalid test configuration",
         expectedMethodNames.size() == expectedMethodReturns.size());
      assertTrue("Invalid test configuration",
         expectedMethodNames.size() == expectedMethodParameters.size());

      for (int i = 0; i < expectedMethodNames.size(); i++)
      {
         Method method = clazz.getDeclaredMethod(expectedMethodNames.get(i),
            expectedMethodParameters.get(i));
         assertEquals(expectedMethodReturns.get(i), method.getReturnType());
      }
   }
}
