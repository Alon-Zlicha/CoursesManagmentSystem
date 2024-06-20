package coursesManagment;
import java.util.ArrayList;
public class CourseIteratorList implements CourseIterator {
    private ArrayList<Course> courses;
    private int position = 0;

    public CourseIteratorList(ArrayList<Course> courses) {
        this.courses = courses;
    }
    @Override
    public boolean hasNext() {
        return position<courses.size();
    }
    @Override
    public Course next() {
        if (!hasNext()) {
            return null;
        }
        Course course=courses.get(position);
        position++;
        return course;
    }
    @Override
    public void remove() {
        if (position==0) {
            throw new IllegalStateException("next() must be called before remove()");
        }
        courses.remove(--position);
    }
}
