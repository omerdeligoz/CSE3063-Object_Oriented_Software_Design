package iteration1;

import java.util.ArrayList;
import java.util.List;

public class CourseSection extends Course {
    private String courseSessionCode;
    private String day;
    private int hour;
    private List students = new ArrayList();


    public CourseSection(Course course, String courseSessionCode, String day, int hour) {
        super(course.getCourseName(), course.getCourseCode(), course.getCourseCredit(), course.getSemester());
        this.courseSessionCode = courseSessionCode;
        this.day = day;
        this.hour = hour;
    }

    public String getDay() {
        return day;
    }
}
