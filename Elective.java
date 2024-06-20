package coursesManagment;
public class Elective extends Course{
    public Elective(String id,String name,int capacity,double price,Lecturer lecturer,Practitioner practitioner) {
        super(new CourseFlyweight(id,name),capacity,price,lecturer,practitioner);
    }
    @Override
    public String getCourseType(){
        return "Elective";
    }
}
