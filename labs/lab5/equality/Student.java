import java.util.List;
import java.util.Objects;

class Student
{
   private final String surname;
   private final String givenName;
   private final int age;
   private final List<CourseSection> currentCourses;

   public Student(final String surname, final String givenName, final int age,
      final List<CourseSection> currentCourses)
   {
      this.surname = surname;
      this.givenName = givenName;
      this.age = age;
      this.currentCourses = currentCourses;
   }

   public int hashCode() {
      return Objects.hash(surname,givenName,age,currentCourses);
   }

   public boolean equals(Object o){
      if (this == o) {
         return true;
      }
      if(o != null)
      {
         if (getClass() == o.getClass())
         {
            boolean surnameBool = Objects.equals(surname, ((Student)o).surname);
            boolean givenNameBool = Objects.equals(givenName, ((Student)o).givenName);
            boolean ageBool = (this.age == ((Student)o).age);
            boolean currentCoursesBool = Objects.equals(currentCourses, ((Student)o).currentCourses);
            return surnameBool && givenNameBool && ageBool && currentCoursesBool;
         }
      }
      return false;
   }
}
