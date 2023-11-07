package iteration1;

import java.util.HashMap;
import java.util.List;

public class Registration {
    private Student student;
    private List<Course> courses;

    public Registration(Student student, List<Course> courses) {
        this.student = student;
        this.courses = courses;

    }

    public void showRequest() {
    }

    public void sendRequest(Advisor advisor) {
//        advisor.receiveRequest(this);dD
    }

    public Student getStudent() {
        return student;
    }

    public void approveRequest() {
        System.out.println("Request approved"); //TODO implementation
        student.setHasRequest(false);
    }

    public void rejectRequest() { //TODO implementation
        System.out.println("Request rejected");
    }
}
