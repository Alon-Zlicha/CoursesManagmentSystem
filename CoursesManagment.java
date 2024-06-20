package coursesManagment;
public class CoursesManagment {
    public static void main(String[] args) {
        UserManager userManager = UserManager.getInstance();
        Lecturer l1 = new Lecturer("1", "Gabriel Nivash");
        Practitioner p1 = new Practitioner("2", "Doron Mor");
        Student s1 = new Student("3", "Alon Zlicha", "Alonz1414@gmail.com","0523973561");
        Student s2 = new Student("4", "Daniel Cohen", "DanielCo33@gmail.com","0545674833");
        Student s3 = new Student("5", "Rami levy", "Rami1Levy@gmail.com","0504435654");
        l1.addCourse("1", "Algorithms 1", 1, 1000,p1); // not active message
        p1.addCourse("2", "OOP", 35, 500,l1); //not active message
        userManager.addUser(l1);
        userManager.addUser(p1);
        l1.addCourse("1", "Algorithms 1", 1,1000, p1);
        p1.addCourse("2", "OOP", 35,500, l1);
        p1.addCourse("2", "OOP", 35, 500,l1); // flyweight check
        System.out.println("The list of all Courses: ");
        for (Course c : Course.getCoursesList()) {
            System.out.println(c);
        }

        l1.printCoursesList();  // OOP should appear once
        p1.printCoursesList();  // OOP should appear once

        Course algo1=Course.getCoursesList().get(0);
        Course oop=Course.getCoursesList().get(1);

        s1.addToShoppingCart(algo1); // not active message
        userManager.addUser(s1);
        s1.addToShoppingCart(algo1);
        s1.addToShoppingCart(algo1); // error, already in the shopping list
        s1.addToShoppingCart(oop);
        double tp1=s1.buyCourses();
        System.out.println("The total price is - "+tp1); // 1500
        s1.printCoursesList(); // Algo 1 and OOP

        s1.addToShoppingCart(algo1); // error,already registered

        userManager.addUser(s2);
        s2.addToShoppingCart(algo1); // full course, waiting list
        s2.addToShoppingCart(oop);
        double tp2=s2.buyCourses();
        System.out.println("The total price is - "+tp2); // 500
        s2.printCoursesList(); // oop

        userManager.addUser(s3);
        System.out.println("The number of active users is: "+userManager.getActiveUserCount()); // 5
        s3.addToShoppingCart(algo1); // full course, waiting list

        userManager.removeUser(s1);     // making user inactive, remove him from the courses that he registered to and updating waiting list(for student)
        s2.addToShoppingCart(algo1);
        s2.addToShoppingCart(oop); // error,already registered
        double tp2_1=s2.buyCourses();
        System.out.println("The total price is - "+tp2_1); // 1000
        algo1.removeStudent(s2); // update waiting list

        s3.addToShoppingCart(algo1);
        double tp3=s3.buyCourses();
        System.out.println("The total price is - "+tp3); // 1000
        s3.printCoursesList(); // algo1

        System.out.println("The list of registered students in "+algo1.getCourseName()+": ");
        algo1.printRegisteredStudents(); // Rami Levy
        System.out.println("The list of registered students in "+oop.getCourseName()+": ");
        oop.printRegisteredStudents(); // Daniel cohen

        userManager.removeUser(l1); // making user inactive and remove the courses that he teaches(for lecturers and practitioner)
        System.out.println("The list of all Courses: ");
        for (Course c : Course.getCoursesList()) {
            System.out.println(c); // nothing
        }

        System.out.println("The number of active users is: "+userManager.getActiveUserCount()); // 3
    }
}