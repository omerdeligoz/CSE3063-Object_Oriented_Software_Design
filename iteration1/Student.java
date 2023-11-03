package iteration1;

import java.util.ArrayList;

public class Student implements ILogin {
    private int studentID;
    private String name;
    private String password;
    private String userName;
    private Advisor advisor;

    private Transcript transcript;
    private double cgpa;
    private byte gradeLevel;

    @Override
    public boolean login(String userName, String password) {
       if (this.userName.equals(userName) && this.password.equals(password)) {
           return true;
       } else {
           return false;
       }
    }

    protected ArrayList<Course> selectCourses() {
        System.out.println("Select Courses");
        return null;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void addCourseMenu(Student student, ArrayList<Course> courses) {
    }
}
