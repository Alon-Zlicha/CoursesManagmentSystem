package coursesManagment;
public class CourseFlyweight {
    private String courseID;
    private String courseName;
    public CourseFlyweight(String courseID, String courseName) {
        this.courseID = courseID;
        this.courseName = courseName;
    }
    public String getCourseID() {
        return courseID;
    }
    public String getCourseName() {
        return courseName;
    }
}
