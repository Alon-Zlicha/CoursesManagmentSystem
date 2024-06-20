package coursesManagment;
public class EmailNotificationStrategy implements NotificationStrategy{
    @Override
    public void sendNotification(String message, Student student) {
        System.out.println("Sending email notification to "+student.getEmail()+": " + message);
    }
}
