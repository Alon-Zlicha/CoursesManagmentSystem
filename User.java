package coursesManagment;
public abstract class User {
    private String id;
    private String username;
    private boolean active;
    public User(String id, String username) {
        this.id=id;
        this.username=username;
        this.active=false;
    }
    public String getId() {
        return id;
    }
    public String getUsername(){
        return username;
    }
    public boolean isActive() {
        return active;
    }
    public void setActive(boolean active) {
        this.active = active;
    }
    public boolean isEqual(User user) {
        return this.id.equals(user.getId());
    }
    public abstract boolean setInactive();
    public abstract String toString();
    public abstract void printCoursesList();
}
