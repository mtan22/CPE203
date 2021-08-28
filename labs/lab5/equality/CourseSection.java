import java.time.LocalTime;

class CourseSection
{
   private final String prefix;
   private final String number;
   private final int enrollment;
   private final LocalTime startTime;
   private final LocalTime endTime;

   public CourseSection(final String prefix, final String number,
      final int enrollment, final LocalTime startTime, final LocalTime endTime)
   {
      this.prefix = prefix;
      this.number = number;
      this.enrollment = enrollment;
      this.startTime = startTime;
      this.endTime = endTime;
   }

   // additional likely methods not defined since they are not needed for testing
   
   public int hashCode() {
      int sol = 1;
      if (prefix != null) {
         sol = prefix.hashCode() + sol * 31;
      }
      if (number != null) {
         sol = number.hashCode() + sol * 31;
      }
      sol = enrollment + sol * 31;
      if (startTime != null) {
         sol = startTime.hashCode() + sol * 31;
      }
      if (endTime != null) {
         sol = endTime.hashCode() + sol * 31;
      }
      
      return sol;
   } 

   public boolean equals(Object o) {
      if (this == o) {
         return true;
      }
      if (o != null)
      {
         if (getClass() == o.getClass())
         {
            boolean prefixBool;
            boolean numberBool;
            boolean enrollmentBool;
            boolean startTimeBool;
            boolean endTimeBool;

            int otherEnroll = ((CourseSection) o).enrollment;
            String otherPrefix = ((CourseSection) o).prefix;
            String otherNumber = ((CourseSection) o).number;
            LocalTime otherStart = ((CourseSection) o).startTime;
            LocalTime otherEnd = ((CourseSection) o).endTime;

            if (prefix == null) {
               prefixBool = (otherPrefix == null);
            }
            else {
               prefixBool = (prefix.equals(otherPrefix));
            }

            if (number == null) {
               numberBool = (otherNumber == null);
            }
            else {
               numberBool = (number.equals(otherNumber));
            }

            if (startTime == null) {
               startTimeBool = (otherStart == null);
            }
            else {
               startTimeBool = (startTime.equals(otherStart));
            }

            if (endTime == null) {
               endTimeBool = (otherEnd == null);
            }
            else {
               endTimeBool = (endTime.equals(otherEnd));
            }
            enrollmentBool = (enrollment == otherEnroll);
            boolean result = prefixBool && numberBool && enrollmentBool && startTimeBool && endTimeBool;
            return result;
         }
      }
      return false;
   }
}

