package coursesManagment;
import java.util.ArrayList;

public abstract class Course {
    private CourseFlyweight flyweight;
    int capacity;
    double price;
    private ArrayList<Student> registeredStudents;
    private ArrayList<Student> waitingList;
    private Lecturer lecturer;
    private Practitioner practitioner;
    private static ArrayList<Course> coursesList= new ArrayList<>();
    public Course(CourseFlyweight flyweight,int capacity,double price,Lecturer lecturer,Practitioner practitioner){
        this.flyweight=flyweight;
        this.capacity=capacity;
        this.price=price;
        this.lecturer=lecturer;
        this.practitioner=practitioner;
        registeredStudents=new ArrayList<>();
        this.waitingList=new ArrayList<>();
        coursesList.add(this);
    }
    public String getCourseID(){
        return flyweight.getCourseID();
    }
    public String getCourseName(){
        return flyweight.getCourseName();
    }
    public Lecturer getLecturer(){
        return lecturer;
    }
    public Practitioner getPractitioner(){
        return practitioner;
    }
    public int getCapacity(){
        return capacity;
    }
    public void addStudent(Student student){
        registeredStudents.add(student);
    }
    public double getPrice(){
        return price;
    }
    public ArrayList<Student> getWaitingList(){
        return waitingList;
    }
    public void addToWaitingList(Student student){
        if(!waitingList.contains(student))
            waitingList.add(student);
    }
    public static ArrayList<Course> getCoursesList(){
        return coursesList;
    }
    public static void removeCourse(Course course){
        coursesList.remove(course);
    }
    public void removeStudent(Student student){
        if (registeredStudents.contains(student)){
            registeredStudents.remove(student);
        }
        else {
            System.out.println("Cannot remove the user because he is not registered to the course ");
            waitingList.add(student);
        }
        if(registeredStudents.size()==(capacity-1)){
            notifyWaitingList();
        }
    }
    public void notifyWaitingList(){
        for (Student student : waitingList) {
            student.update(this,student.getNotificationStrategy());
        }
    }
    public void removeFromWaitingList(Student student){
        waitingList.remove(student);
    }
    public ArrayList<Student> getRegisteredStudents(){
        return registeredStudents;
    }
    public abstract String getCourseType();
    public String toString() {
        return "Course ID- " + getCourseID() + " Course name- " + getCourseName() + " Places left- " + (capacity - registeredStudents.size()) + " Course type- " + getCourseType()+" Lecturer- "+lecturer.getUsername()+" Practitioner- "+practitioner.getUsername();
    }
    public void printRegisteredStudents(){
        for (Student student : registeredStudents) {
            System.out.println(student.getUsername());
        }
    }
}
