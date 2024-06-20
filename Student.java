package coursesManagment;
import java.util.ArrayList;
import java.util.Scanner;

public class Student  extends User implements Observer {
    private String email;
    private String phone;
    private NotificationStrategy notificationStrategy;
    private ArrayList<Course> shoppingCart;
    public Student(String id, String username, String email, String phone) {
        super(id, username);
        this.email=email;
        this.phone=phone;
        shoppingCart=new ArrayList<Course>();
        notificationStrategy=null;
    }
    public String getEmail(){
        return email;
    }
    public String getPhone(){
        return phone;
    }
    public void update(Course course, NotificationStrategy notificationStrategy) {
        System.out.println("Updating the student " + getUsername() + " about course " + course.getCourseName());
        this.notificationStrategy = notificationStrategy; // Set notification strategy
        sendNotification("A spot is available for course " + course.getCourseName());
    }
    public NotificationStrategy getNotificationStrategy() {
        return notificationStrategy;
    }
    private void sendNotification(String message) {
        if (notificationStrategy != null) {
            notificationStrategy.sendNotification(message,this);
        }
    }
    public void addToShoppingCart(Course course) {
        if(!this.isActive()){
            System.out.println("Student "+getUsername()+" is not active");
        }
        else if(shoppingCart.contains(course))  {
            System.out.println("Cannot add course "+course.getCourseName()+" to the shopping cart, the course is already in your shopping cart");
        }
        else if(course.getRegisteredStudents().contains(this)){
            System.out.println("Cannot add course "+course.getCourseName()+" to the shopping cart, "+getUsername()+" is already registered to the course");
        }
        else if(course.getRegisteredStudents().size()>=course.getCapacity()){
            System.out.println("The course "+course.getCourseName()+" is already full!");
            System.out.println("If you want to be updated about availble spot in the course "+course.getCourseName()+" via email press 1, via SMS press 0.If you don't want to be updated press other key");
            Scanner scanner=new Scanner(System.in);
            int choice=scanner.nextInt();
            NotificationStrategy nf=null;
            if(choice==1){
                course.addToWaitingList(this);
                nf=new EmailNotificationStrategy();
            }
            else if(choice==0){
                course.addToWaitingList(this);
                nf=new SMSNotificationStrategy();
            }
            notificationStrategy=nf;
        }
        else {
            shoppingCart.add(course);
        }
    }
    public double buyCourses() {
        double totalPrice=0;
        for (int i = 0; i < shoppingCart.size(); i++) {
            shoppingCart.get(i).addStudent(this);
            shoppingCart.get(i).removeFromWaitingList(this);
            totalPrice+=shoppingCart.get(i).getPrice();
        }
        shoppingCart.clear();
        return totalPrice;
    }
    @Override
    public String toString(){
        return "Student ID- "+getId()+" Student name-  "+getUsername()+" Email-  "+email;
    }
    public void printCoursesList(){
        System.out.println("The student "+getUsername()+" is registered to the courses: ");
        for(int i=0;i<Course.getCoursesList().size();i++){
            if(Course.getCoursesList().get(i).getRegisteredStudents().contains(this)){
                System.out.println(Course.getCoursesList().get(i).getCourseName());
            }
        }
    }
    @Override
    public boolean setInactive(){
        if(!this.isActive())
            return false;
        this.setActive(false);
        for(int i=0;i<Course.getCoursesList().size();i++){
            if(Course.getCoursesList().get(i).getRegisteredStudents().contains(this)){
                Course.getCoursesList().get(i).getRegisteredStudents().remove(this);
                Course.getCoursesList().get(i).notifyWaitingList();
            }
            if(Course.getCoursesList().get(i).getWaitingList().contains(this)){
                Course.getCoursesList().get(i).getWaitingList().remove(this);
            }
        }
        return true;
    }
}
