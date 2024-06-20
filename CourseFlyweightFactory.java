package coursesManagment;
import java.util.HashMap;
import java.util.Map;
public class CourseFlyweightFactory {
    private static Map<String, Course> courses = new HashMap<>();

    public static Course createCourse(int type, String courseId, String courseName,int capacity,double price, Lecturer lecturer, Practitioner practitioner) {
        String key = courseId + "-" + courseName;
        if (!courses.containsKey(key)) {
            if (type==0) {
                courses.put(key, new Mandatory(courseId, courseName, capacity,price,lecturer, practitioner));
            } else if (type==1) {
                courses.put(key, new Elective(courseId, courseName, capacity,price,lecturer, practitioner));
            } else if (type==2) {
                courses.put(key, new Seminar(courseId, courseName, capacity,price, lecturer, practitioner));
            } else {
                System.out.println("Invalid course type specified.");
                return null;
            }
        }
        return courses.get(key);
    }
}
