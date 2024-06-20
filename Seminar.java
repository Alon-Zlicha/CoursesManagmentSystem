package coursesManagment;
public class Seminar extends Course {
    public Seminar(String id, String name, int capacity, double price, Lecturer lecturer, Practitioner practitioner) {
        super(new CourseFlyweight(id, name), capacity, price, lecturer, practitioner);
    }
    @Override
    public String getCourseType() {
        return "Seminar";
    }
}
