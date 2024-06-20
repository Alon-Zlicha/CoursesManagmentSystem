package coursesManagment;
import java.util.Scanner;

public class Lecturer extends User{
    public Lecturer(String id,String name){
        super(id,name);
    }

    @Override
    public String toString() {
        return "Lecturer ID+ "+getId()+" Lecturer name+ "+getUsername();
    }
    public void addCourse(String courseId, String courseName, int capacity,double price, Practitioner practitioner) {
        if(!this.isActive()){
            System.out.println("Lecturer "+getUsername()+" is not active");
        }
        else {
            System.out.println("Choose the type of the course.For Mandatory press 0, for elective press 1, for seminar press 2");
            Scanner scanner = new Scanner(System.in);
            int choice=scanner.nextInt();
            CourseFlyweightFactory.createCourse(choice, courseId, courseName, capacity,price,this, practitioner);
        }
    }
    @Override
    public void printCoursesList(){
        System.out.println("List of the courses that "+getUsername()+" lectures: ");
        for(int i=0;i<Course.getCoursesList().size();i++){
            if(Course.getCoursesList().get(i).getLecturer().getId().equals(getId())){
                System.out.println(Course.getCoursesList().get(i).getCourseName());
            }
        }
    }
    @Override
    public boolean setInactive() {
        if (!this.isActive()) {
            return false;
        }
        CourseIterator iterator=new CourseIteratorList(Course.getCoursesList());
        while (iterator.hasNext()) {
            Course course = iterator.next();
            if (course.getLecturer().isEqual(this)) {
                iterator.remove();
            }
        }
        return true;
    }
}
