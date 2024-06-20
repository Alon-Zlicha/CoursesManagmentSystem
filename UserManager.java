package coursesManagment;
public class UserManager {
    private static final int MAX_ACTIVE_USERS=100;
    private static UserManager instance;
    private int activeUserCount;
    private UserManager() {
        activeUserCount = 0;
    }
    public static UserManager getInstance() {
        if (instance==null) {
            instance=new UserManager();
        }
        return instance;
    }
    public void addUser(User user) {
        if (activeUserCount<MAX_ACTIVE_USERS) {
            user.setActive(true);
            activeUserCount++;
        } else {
            System.out.println("Cannot add user "+user.getUsername()+". To many active users");
        }
    }
    public void removeUser(User user) {
            if(user.setInactive()) {
                activeUserCount--;
            }
    }
    public int getActiveUserCount() {
        return activeUserCount;
    }
}