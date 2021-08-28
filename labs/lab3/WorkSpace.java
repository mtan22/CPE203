import java.util.ArrayList;
import java.util.List;
import java.awt.Point;
import java.awt.Color;
import java.lang.Math;

public class WorkSpace {
   private List<Shape> shapeObjects;

   public WorkSpace() {
      this.shapeObjects = new ArrayList<>();
   }

   public void add(Shape shape) {
      shapeObjects.add(shape);
   }

   public Shape get(int index) {
      return shapeObjects.get(index);
   }

   public int size() {
      return shapeObjects.size();
   }

   public List<Circle> getCircles() {
      List<Circle> circleObjects = new ArrayList<>();
      for (Shape shape:shapeObjects) {
         if (shape instanceof Circle) {
            circleObjects.add((Circle) shape);
         }
      }
      return circleObjects;
   }

   public List<Rectangle> getRectangles() {
      List<Rectangle> rectangleObjects = new ArrayList<>();
      for (Shape shape:shapeObjects) {
         if (shape instanceof Rectangle) {
            rectangleObjects.add((Rectangle) shape);
         }
      }
      return rectangleObjects;
  }

   public List<Triangle> getTriangles() {
      List<Triangle> triangleObjects = new ArrayList<>();
      for (Shape shape:shapeObjects) {
         if (shape instanceof Triangle) {
            triangleObjects.add((Triangle) shape);
         }
      }
      return triangleObjects;
   }
   
   public List<Shape> getShapesByColor(Color color) {
      List<Shape> colors = new ArrayList<>();
      for (Shape shape: shapeObjects) {
         if (shape.getColor().equals(color)) {
            colors.add(shape);
         }
      }
      return colors;
   }

   public double getAreaOfAllShapes() {
      double finalSum = 0;
      for (Shape shape: shapeObjects) {
         finalSum = finalSum + shape.getArea();
      }
      return finalSum;
   }

   public double getPerimeterOfAllShapes() {
      double finalPerimeter = 0;
      for (Shape shape: shapeObjects) {
         finalPerimeter = finalPerimeter + shape.getPerimeter();
      }
      return finalPerimeter;
   }
}   
