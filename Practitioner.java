package coursesManagment;
import java.util.Scanner;
public class Practitioner  extends User{
    public Practitioner(String id, String name){
        super(id,name);
    }
    @Override
    public String toString(){
        return "Practitioner ID- "+getId()+" Practitioner name- "+getUsername();
    }
    public void addCourse(String courseId, String courseName, int capacity,double price, Lecturer lecturer) {
        if(!this.isActive()){
            System.out.println("Practitioner "+getUsername()+" is not active");
        }
        else {
            System.out.println("Choose the type of the course.For Mandatory press 0, for elective press 1, for seminar press 2");
            Scanner scanner = new Scanner(System.in);
            int choice=scanner.nextInt();
            CourseFlyweightFactory.createCourse(choice, courseId, courseName, capacity,price,lecturer, this);
        }
    }
    @Override
    public void printCoursesList(){
        System.out.println("List of the courses that "+getUsername()+" practices: ");
        for(int i=0;i<Course.getCoursesList().size();i++){
            if(Course.getCoursesList().get(i).getPractitioner().getId().equals(getId())){
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
