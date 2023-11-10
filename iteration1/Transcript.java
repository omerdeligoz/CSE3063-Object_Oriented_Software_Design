package iteration1;

import java.util.HashMap;
import java.util.Map;

public class Transcript {
    Student student;
    double cgpa;
    Map<Course, Grade> courseGradeMap = new HashMap<>();

    public void setStudent(Student student) {
        this.student = student;
    }

    public Map<Course, Grade> getCourseGradeMap() {
        return courseGradeMap;
    }

    public void showTranscript() {
        System.out.println("Showing Transcript ...\n\n");
        //TODO a to string method for transcript
    }

    public void courseStatusCheck(Student student) {
        //TODO
        System.out.println("Course Status  Check ...");
    }
}
