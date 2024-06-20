package coursesManagment;
public class SMSNotificationStrategy implements NotificationStrategy{
    @Override
    public void sendNotification(String message,Student student) {
        System.out.println("Sending SMS notification to "+ student.getPhone()+": "+message);
    }
}
