package iteration1;

import java.util.List;

public class Registration {
    private Student student;
    private List<Course> courses; //list of selected courses

    public Registration(Student student, List<Course> courses) {
        this.student = student;
        this.courses = courses;
    }

    public void showRequest() {
    }

    public void sendRequest(Advisor advisor) {
//        advisor.receiveRequest(this);dD
    }

    public void approveRequest(Advisor advisor) {
        //TODO this.student ve this.courses kullanarak student ve advisor güncellenecek
        System.out.println("Request approved"); //TODO implementation
        student.setHasRequest(false);
    }

    public void rejectRequest(Advisor advisor) { //TODO implementation
        //TODO this.student ve this.courses kullanarak student ve advisor güncellenecek
        System.out.println("Request rejected");
    }

    public Student getStudent() {
        return student;
    }
}
