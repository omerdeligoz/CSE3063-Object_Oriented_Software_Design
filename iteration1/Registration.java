package iteration1;

import java.util.List;

public class Registration {
    private Student student;
    private List<Course> courses; //list of selected courses

    public Registration(Student student, List<Course> courses) {
        this.student = student;
        this.courses = courses;
    }

    public void approveRequest() {
        //TODO this.student ve this.courses kullanarak student ve advisor güncellenecek
        System.out.println("Request approved");
        student.getTranscript().getStudentCourses().addAll(courses);
        for (Course course : courses) {
            student.getTranscript().getCourseGradeMap().put(course, null);
        }
        student.setHasRequest(false);
        student.getDraft().clear();
    }

    public void rejectRequest() { //TODO implementation
        //TODO this.student ve this.courses kullanarak student ve advisor güncellenecek
        System.out.println("Request rejected");
        student.setHasRequest(false);
        student.getDraft().clear();
    }
    public void addRequest(Advisor advisor) {
        advisor.getRequests().add(this);
        student.setHasRequest(true);
        System.out.println("Request sent to advisor");
    }

    public Student getStudent() {
        return student;
    }
}
